package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.CompositeFilter;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.firebase.firestore.index.IndexByteEncoder;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.local.IndexManager;
import com.google.firebase.firestore.local.MemoryIndexManager;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.TargetIndexMatcher;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.LogicUtils;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.v1.Value;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

final class SQLiteIndexManager implements IndexManager {
    private static final byte[] EMPTY_BYTES_VALUE = new byte[0];
    private static final String TAG = SQLiteIndexManager.class.getSimpleName();
    private final MemoryIndexManager.MemoryCollectionParentIndex collectionParentsCache = new MemoryIndexManager.MemoryCollectionParentIndex();
    private final SQLitePersistence db;
    private final Map<String, Map<Integer, FieldIndex>> memoizedIndexes = new HashMap();
    private int memoizedMaxIndexId = -1;
    private long memoizedMaxSequenceNumber = -1;
    private final Queue<FieldIndex> nextIndexToUpdate = new PriorityQueue(10, new SQLiteIndexManager$$ExternalSyntheticLambda3());
    private final LocalSerializer serializer;
    private boolean started = false;
    private final Map<Target, List<Target>> targetToDnfSubTargets = new HashMap();
    private final String uid;

    static /* synthetic */ int lambda$new$0(FieldIndex l, FieldIndex r) {
        int sequenceCmp = Long.compare(l.getIndexState().getSequenceNumber(), r.getIndexState().getSequenceNumber());
        if (sequenceCmp == 0) {
            return l.getCollectionGroup().compareTo(r.getCollectionGroup());
        }
        return sequenceCmp;
    }

    SQLiteIndexManager(SQLitePersistence persistence, LocalSerializer serializer2, User user) {
        this.db = persistence;
        this.serializer = serializer2;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public void start() {
        Map<Integer, FieldIndex.IndexState> indexStates = new HashMap<>();
        this.db.query("SELECT index_id, sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id FROM index_state WHERE uid = ?").binding(this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda6(indexStates));
        this.db.query("SELECT index_id, collection_group, index_proto FROM index_configuration").forEach(new SQLiteIndexManager$$ExternalSyntheticLambda7(this, indexStates));
        this.started = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$2$com-google-firebase-firestore-local-SQLiteIndexManager  reason: not valid java name */
    public /* synthetic */ void m371lambda$start$2$comgooglefirebasefirestorelocalSQLiteIndexManager(Map indexStates, Cursor row) {
        FieldIndex.IndexState indexState;
        try {
            int indexId = row.getInt(0);
            String collectionGroup = row.getString(1);
            List<FieldIndex.Segment> segments = this.serializer.decodeFieldIndexSegments(Index.parseFrom(row.getBlob(2)));
            if (indexStates.containsKey(Integer.valueOf(indexId))) {
                indexState = (FieldIndex.IndexState) indexStates.get(Integer.valueOf(indexId));
            } else {
                indexState = FieldIndex.INITIAL_STATE;
            }
            memoizeIndex(FieldIndex.create(indexId, collectionGroup, segments, indexState));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Failed to decode index: " + e, new Object[0]);
        }
    }

    public void addToCollectionParentIndex(ResourcePath collectionPath) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Assert.hardAssert(collectionPath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(collectionPath)) {
            this.db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", collectionPath.getLastSegment(), EncodedPath.encode((ResourcePath) collectionPath.popLast()));
        }
    }

    public List<ResourcePath> getCollectionParents(String collectionId) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        ArrayList<ResourcePath> parentPaths = new ArrayList<>();
        this.db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(collectionId).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda4(parentPaths));
        return parentPaths;
    }

    public void addFieldIndex(FieldIndex index) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        int nextIndexId = this.memoizedMaxIndexId + 1;
        FieldIndex index2 = FieldIndex.create(nextIndexId, index.getCollectionGroup(), index.getSegments(), index.getIndexState());
        this.db.execute("INSERT INTO index_configuration (index_id, collection_group, index_proto) VALUES(?, ?, ?)", Integer.valueOf(nextIndexId), index2.getCollectionGroup(), encodeSegments(index2));
        memoizeIndex(index2);
    }

    public void deleteFieldIndex(FieldIndex index) {
        this.db.execute("DELETE FROM index_configuration WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.db.execute("DELETE FROM index_entries WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.db.execute("DELETE FROM index_state WHERE index_id = ?", Integer.valueOf(index.getIndexId()));
        this.nextIndexToUpdate.remove(index);
        Map<Integer, FieldIndex> collectionIndices = this.memoizedIndexes.get(index.getCollectionGroup());
        if (collectionIndices != null) {
            collectionIndices.remove(Integer.valueOf(index.getIndexId()));
        }
    }

    public String getNextCollectionGroupToUpdate() {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        FieldIndex nextIndex = this.nextIndexToUpdate.peek();
        if (nextIndex != null) {
            return nextIndex.getCollectionGroup();
        }
        return null;
    }

    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> documents) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Iterator<Map.Entry<DocumentKey, Document>> it = documents.iterator();
        while (it.hasNext()) {
            Map.Entry<DocumentKey, Document> entry = it.next();
            for (FieldIndex fieldIndex : getFieldIndexes(entry.getKey().getCollectionGroup())) {
                SortedSet<IndexEntry> existingEntries = getExistingIndexEntries(entry.getKey(), fieldIndex);
                SortedSet<IndexEntry> newEntries = computeIndexEntries(entry.getValue(), fieldIndex);
                if (!existingEntries.equals(newEntries)) {
                    updateEntries(entry.getValue(), existingEntries, newEntries);
                }
            }
        }
    }

    private void updateEntries(Document document, SortedSet<IndexEntry> existingEntries, SortedSet<IndexEntry> newEntries) {
        Logger.debug(TAG, "Updating index entries for document '%s'", document.getKey());
        Util.diffCollections(existingEntries, newEntries, new SQLiteIndexManager$$ExternalSyntheticLambda1(this, document), new SQLiteIndexManager$$ExternalSyntheticLambda2(this, document));
    }

    public Collection<FieldIndex> getFieldIndexes(String collectionGroup) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Map<Integer, FieldIndex> indexes = this.memoizedIndexes.get(collectionGroup);
        return indexes == null ? Collections.emptyList() : indexes.values();
    }

    public Collection<FieldIndex> getFieldIndexes() {
        List<FieldIndex> allIndices = new ArrayList<>();
        for (Map<Integer, FieldIndex> indices : this.memoizedIndexes.values()) {
            allIndices.addAll(indices.values());
        }
        return allIndices;
    }

    private FieldIndex.IndexOffset getMinOffset(Collection<FieldIndex> fieldIndexes) {
        Assert.hardAssert(!fieldIndexes.isEmpty(), "Found empty index group when looking for least recent index offset.", new Object[0]);
        Iterator<FieldIndex> it = fieldIndexes.iterator();
        FieldIndex.IndexOffset minOffset = it.next().getIndexState().getOffset();
        int maxBatchId = minOffset.getLargestBatchId();
        while (it.hasNext()) {
            FieldIndex.IndexOffset newOffset = it.next().getIndexState().getOffset();
            if (newOffset.compareTo(minOffset) < 0) {
                minOffset = newOffset;
            }
            maxBatchId = Math.max(newOffset.getLargestBatchId(), maxBatchId);
        }
        return FieldIndex.IndexOffset.create(minOffset.getReadTime(), minOffset.getDocumentKey(), maxBatchId);
    }

    public FieldIndex.IndexOffset getMinOffset(String collectionGroup) {
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(collectionGroup);
        Assert.hardAssert(!fieldIndexes.isEmpty(), "minOffset was called for collection without indexes", new Object[0]);
        return getMinOffset(fieldIndexes);
    }

    public IndexManager.IndexType getIndexType(Target target) {
        IndexManager.IndexType result = IndexManager.IndexType.FULL;
        List<Target> subTargets = getSubTargets(target);
        Iterator<Target> it = subTargets.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Target subTarget = it.next();
            FieldIndex index = getFieldIndex(subTarget);
            if (index == null) {
                result = IndexManager.IndexType.NONE;
                break;
            } else if (index.getSegments().size() < subTarget.getSegmentCount()) {
                result = IndexManager.IndexType.PARTIAL;
            }
        }
        if (!target.hasLimit() || subTargets.size() <= 1 || result != IndexManager.IndexType.FULL) {
            return result;
        }
        return IndexManager.IndexType.PARTIAL;
    }

    public FieldIndex.IndexOffset getMinOffset(Target target) {
        List<FieldIndex> fieldIndexes = new ArrayList<>();
        for (Target subTarget : getSubTargets(target)) {
            FieldIndex index = getFieldIndex(subTarget);
            if (index != null) {
                fieldIndexes.add(index);
            }
        }
        return getMinOffset((Collection<FieldIndex>) fieldIndexes);
    }

    private List<Target> getSubTargets(Target target) {
        if (this.targetToDnfSubTargets.containsKey(target)) {
            return this.targetToDnfSubTargets.get(target);
        }
        List<Target> subTargets = new ArrayList<>();
        if (target.getFilters().isEmpty()) {
            subTargets.add(target);
        } else {
            for (Filter term : LogicUtils.getDnfTerms(new CompositeFilter(target.getFilters(), CompositeFilter.Operator.AND))) {
                subTargets.add(new Target(target.getPath(), target.getCollectionGroup(), term.getFilters(), target.getOrderBy(), target.getLimit(), target.getStartAt(), target.getEndAt()));
            }
        }
        this.targetToDnfSubTargets.put(target, subTargets);
        return subTargets;
    }

    private void memoizeIndex(FieldIndex fieldIndex) {
        Map<Integer, FieldIndex> existingIndexes = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (existingIndexes == null) {
            existingIndexes = new HashMap<>();
            this.memoizedIndexes.put(fieldIndex.getCollectionGroup(), existingIndexes);
        }
        FieldIndex existingIndex = existingIndexes.get(Integer.valueOf(fieldIndex.getIndexId()));
        if (existingIndex != null) {
            this.nextIndexToUpdate.remove(existingIndex);
        }
        existingIndexes.put(Integer.valueOf(fieldIndex.getIndexId()), fieldIndex);
        this.nextIndexToUpdate.add(fieldIndex);
        this.memoizedMaxIndexId = Math.max(this.memoizedMaxIndexId, fieldIndex.getIndexId());
        this.memoizedMaxSequenceNumber = Math.max(this.memoizedMaxSequenceNumber, fieldIndex.getIndexState().getSequenceNumber());
    }

    private SortedSet<IndexEntry> computeIndexEntries(Document document, FieldIndex fieldIndex) {
        SortedSet<IndexEntry> result = new TreeSet<>();
        byte[] directionalValue = encodeDirectionalElements(fieldIndex, document);
        if (directionalValue == null) {
            return result;
        }
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null) {
            Value value = document.getField(arraySegment.getFieldPath());
            if (Values.isArray(value)) {
                for (Value arrayValue : value.getArrayValue().getValuesList()) {
                    result.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), encodeSingleElement(arrayValue), directionalValue));
                }
            }
        } else {
            result.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), new byte[0], directionalValue));
        }
        return result;
    }

    /* access modifiers changed from: private */
    /* renamed from: addIndexEntry */
    public void m372lambda$updateEntries$4$comgooglefirebasefirestorelocalSQLiteIndexManager(Document document, IndexEntry indexEntry) {
        this.db.execute("INSERT INTO index_entries (index_id, uid, array_value, directional_value, document_key) VALUES(?, ?, ?, ?, ?)", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    /* access modifiers changed from: private */
    /* renamed from: deleteIndexEntry */
    public void m373lambda$updateEntries$5$comgooglefirebasefirestorelocalSQLiteIndexManager(Document document, IndexEntry indexEntry) {
        this.db.execute("DELETE FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value = ? AND document_key = ?", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    private SortedSet<IndexEntry> getExistingIndexEntries(DocumentKey documentKey, FieldIndex fieldIndex) {
        SortedSet<IndexEntry> results = new TreeSet<>();
        this.db.query("SELECT array_value, directional_value FROM index_entries WHERE index_id = ? AND document_key = ? AND uid = ?").binding(Integer.valueOf(fieldIndex.getIndexId()), documentKey.toString(), this.uid).forEach(new SQLiteIndexManager$$ExternalSyntheticLambda5(results, fieldIndex, documentKey));
        return results;
    }

    public List<DocumentKey> getDocumentsMatchingTarget(Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        List<String> subQueries = new ArrayList<>();
        List<Object> bindings = new ArrayList<>();
        List<Pair<Target, FieldIndex>> indexes = new ArrayList<>();
        for (Target subTarget : getSubTargets(target)) {
            FieldIndex fieldIndex = getFieldIndex(subTarget);
            if (fieldIndex == null) {
                return null;
            }
            indexes.add(Pair.create(subTarget, fieldIndex));
        }
        for (Pair<Target, FieldIndex> pair : indexes) {
            Target subTarget2 = (Target) pair.first;
            FieldIndex fieldIndex2 = (FieldIndex) pair.second;
            List<Value> arrayValues = subTarget2.getArrayValues(fieldIndex2);
            Collection<Value> notInValues = subTarget2.getNotInValues(fieldIndex2);
            Bound lowerBound = subTarget2.getLowerBound(fieldIndex2);
            Bound upperBound = subTarget2.getUpperBound(fieldIndex2);
            if (Logger.isDebugEnabled()) {
                Logger.debug(TAG, "Using index '%s' to execute '%s' (Arrays: %s, Lower bound: %s, Upper bound: %s)", fieldIndex2, subTarget2, arrayValues, lowerBound, upperBound);
            }
            Bound bound = upperBound;
            Bound bound2 = lowerBound;
            Collection<Value> collection = notInValues;
            FieldIndex fieldIndex3 = fieldIndex2;
            Target target2 = subTarget2;
            Pair<Target, FieldIndex> pair2 = pair;
            Object[] subQueryAndBindings = generateQueryAndBindings(subTarget2, fieldIndex2.getIndexId(), arrayValues, encodeBound(fieldIndex2, subTarget2, lowerBound), lowerBound.isInclusive() ? ">=" : ">", encodeBound(fieldIndex2, subTarget2, upperBound), upperBound.isInclusive() ? "<=" : "<", encodeValues(fieldIndex2, subTarget2, notInValues));
            subQueries.add(String.valueOf(subQueryAndBindings[0]));
            bindings.addAll(Arrays.asList(subQueryAndBindings).subList(1, subQueryAndBindings.length));
        }
        String queryString = "SELECT DISTINCT document_key FROM (" + (TextUtils.join(" UNION ", subQueries) + "ORDER BY directional_value, document_key " + (target.getKeyOrder().equals(OrderBy.Direction.ASCENDING) ? "asc " : "desc ")) + ")";
        if (target.hasLimit()) {
            queryString = queryString + " LIMIT " + target.getLimit();
        }
        Assert.hardAssert(bindings.size() < 1000, "Cannot perform query with more than 999 bind elements", new Object[0]);
        SQLitePersistence.Query query = this.db.query(queryString).binding(bindings.toArray());
        List<DocumentKey> result = new ArrayList<>();
        query.forEach(new SQLiteIndexManager$$ExternalSyntheticLambda0(result));
        Logger.debug(TAG, "Index scan returned %s documents", Integer.valueOf(result.size()));
        return result;
    }

    private Object[] generateQueryAndBindings(Target target, int indexId, List<Value> arrayValues, Object[] lowerBounds, String lowerBoundOp, Object[] upperBounds, String upperBoundOp, Object[] notIn) {
        StringBuilder sql;
        Object[] objArr = notIn;
        int statementCount = (arrayValues != null ? arrayValues.size() : 1) * Math.max(lowerBounds.length, upperBounds.length);
        StringBuilder statement = new StringBuilder();
        statement.append("SELECT document_key, directional_value FROM index_entries ");
        statement.append("WHERE index_id = ? AND uid = ? ");
        statement.append("AND array_value = ? ");
        statement.append("AND directional_value ").append(lowerBoundOp).append(" ? ");
        statement.append("AND directional_value ").append(upperBoundOp).append(" ? ");
        StringBuilder sql2 = Util.repeatSequence(statement, statementCount, " UNION ");
        if (objArr != null) {
            StringBuilder sql3 = new StringBuilder("SELECT document_key, directional_value FROM (").append(sql2);
            sql3.append(") WHERE directional_value NOT IN (");
            sql3.append(Util.repeatSequence("?", objArr.length, ", "));
            sql3.append(")");
            sql = sql3;
        } else {
            sql = sql2;
        }
        Object[] bindArgs = fillBounds(statementCount, indexId, arrayValues, lowerBounds, upperBounds, notIn);
        List<Object> result = new ArrayList<>();
        result.add(sql.toString());
        result.addAll(Arrays.asList(bindArgs));
        return result.toArray();
    }

    private Object[] fillBounds(int statementCount, int indexId, List<Value> arrayValues, Object[] lowerBounds, Object[] upperBounds, Object[] notInValues) {
        byte[] bArr;
        int statementsPerArrayValue = statementCount / (arrayValues != null ? arrayValues.size() : 1);
        int i = 0;
        Object[] bindArgs = new Object[((statementCount * 5) + (notInValues != null ? notInValues.length : 0))];
        int offset = 0;
        int i2 = 0;
        while (i2 < statementCount) {
            int offset2 = offset + 1;
            bindArgs[offset] = Integer.valueOf(indexId);
            int offset3 = offset2 + 1;
            bindArgs[offset2] = this.uid;
            int offset4 = offset3 + 1;
            if (arrayValues != null) {
                bArr = encodeSingleElement(arrayValues.get(i2 / statementsPerArrayValue));
            } else {
                bArr = EMPTY_BYTES_VALUE;
            }
            bindArgs[offset3] = bArr;
            int offset5 = offset4 + 1;
            bindArgs[offset4] = lowerBounds[i2 % statementsPerArrayValue];
            bindArgs[offset5] = upperBounds[i2 % statementsPerArrayValue];
            i2++;
            offset = offset5 + 1;
        }
        if (notInValues != null) {
            int length = notInValues.length;
            while (i < length) {
                bindArgs[offset] = notInValues[i];
                i++;
                offset++;
            }
        }
        return bindArgs;
    }

    private FieldIndex getFieldIndex(Target target) {
        String collectionGroup;
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        TargetIndexMatcher targetIndexMatcher = new TargetIndexMatcher(target);
        if (target.getCollectionGroup() != null) {
            collectionGroup = target.getCollectionGroup();
        } else {
            collectionGroup = target.getPath().getLastSegment();
        }
        Collection<FieldIndex> collectionIndexes = getFieldIndexes(collectionGroup);
        if (collectionIndexes.isEmpty()) {
            return null;
        }
        FieldIndex matchingIndex = null;
        for (FieldIndex fieldIndex : collectionIndexes) {
            if (targetIndexMatcher.servedByIndex(fieldIndex) && (matchingIndex == null || fieldIndex.getSegments().size() > matchingIndex.getSegments().size())) {
                matchingIndex = fieldIndex;
            }
        }
        return matchingIndex;
    }

    private byte[] encodeDirectionalElements(FieldIndex fieldIndex, Document document) {
        IndexByteEncoder encoder = new IndexByteEncoder();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value field = document.getField(segment.getFieldPath());
            if (field == null) {
                return null;
            }
            FirestoreIndexValueWriter.INSTANCE.writeIndexValue(field, encoder.forKind(segment.getKind()));
        }
        return encoder.getEncodedBytes();
    }

    private byte[] encodeSingleElement(Value value) {
        IndexByteEncoder encoder = new IndexByteEncoder();
        FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, encoder.forKind(FieldIndex.Segment.Kind.ASCENDING));
        return encoder.getEncodedBytes();
    }

    private Object[] encodeValues(FieldIndex fieldIndex, Target target, Collection<Value> values) {
        if (values == null) {
            return null;
        }
        List<IndexByteEncoder> encoders = new ArrayList<>();
        encoders.add(new IndexByteEncoder());
        Iterator<Value> position = values.iterator();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value value = position.next();
            for (IndexByteEncoder encoder : encoders) {
                if (!isInFilter(target, segment.getFieldPath()) || !Values.isArray(value)) {
                    FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, encoder.forKind(segment.getKind()));
                } else {
                    encoders = expandIndexValues(encoders, segment, value);
                }
            }
        }
        return getEncodedBytes(encoders);
    }

    private Object[] encodeBound(FieldIndex fieldIndex, Target target, Bound bound) {
        return encodeValues(fieldIndex, target, bound.getPosition());
    }

    private Object[] getEncodedBytes(List<IndexByteEncoder> encoders) {
        Object[] result = new Object[encoders.size()];
        for (int i = 0; i < encoders.size(); i++) {
            result[i] = encoders.get(i).getEncodedBytes();
        }
        return result;
    }

    private List<IndexByteEncoder> expandIndexValues(List<IndexByteEncoder> encoders, FieldIndex.Segment segment, Value value) {
        List<IndexByteEncoder> prefixes = new ArrayList<>(encoders);
        List<IndexByteEncoder> results = new ArrayList<>();
        for (Value arrayElement : value.getArrayValue().getValuesList()) {
            for (IndexByteEncoder prefix : prefixes) {
                IndexByteEncoder clonedEncoder = new IndexByteEncoder();
                clonedEncoder.seed(prefix.getEncodedBytes());
                FirestoreIndexValueWriter.INSTANCE.writeIndexValue(arrayElement, clonedEncoder.forKind(segment.getKind()));
                results.add(clonedEncoder);
            }
        }
        return results;
    }

    private boolean isInFilter(Target target, FieldPath fieldPath) {
        for (Filter filter : target.getFilters()) {
            if ((filter instanceof FieldFilter) && ((FieldFilter) filter).getField().equals(fieldPath)) {
                FieldFilter.Operator operator = ((FieldFilter) filter).getOperator();
                if (operator.equals(FieldFilter.Operator.IN) || operator.equals(FieldFilter.Operator.NOT_IN)) {
                    return true;
                }
            }
        }
        return false;
    }

    private byte[] encodeSegments(FieldIndex fieldIndex) {
        return this.serializer.encodeFieldIndexSegments(fieldIndex.getSegments()).toByteArray();
    }

    public void updateCollectionGroup(String collectionGroup, FieldIndex.IndexOffset offset) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        this.memoizedMaxSequenceNumber++;
        for (FieldIndex fieldIndex : getFieldIndexes(collectionGroup)) {
            FieldIndex updatedIndex = FieldIndex.create(fieldIndex.getIndexId(), fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), FieldIndex.IndexState.create(this.memoizedMaxSequenceNumber, offset));
            this.db.execute("REPLACE INTO index_state (index_id, uid,  sequence_number, read_time_seconds, read_time_nanos, document_key, largest_batch_id) VALUES(?, ?, ?, ?, ?, ?, ?)", Integer.valueOf(fieldIndex.getIndexId()), this.uid, Long.valueOf(this.memoizedMaxSequenceNumber), Long.valueOf(offset.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(offset.getReadTime().getTimestamp().getNanoseconds()), EncodedPath.encode(offset.getDocumentKey().getPath()), Integer.valueOf(offset.getLargestBatchId()));
            memoizeIndex(updatedIndex);
        }
    }
}
