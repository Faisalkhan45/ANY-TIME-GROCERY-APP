package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.Immutable;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable(containerOf = {"R", "C", "V"})
final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final int[] cellColumnIndices;
    private final int[] cellRowIndices;
    /* access modifiers changed from: private */
    public final int[] columnCounts;
    /* access modifiers changed from: private */
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, ImmutableMap<R, V>> columnMap;
    /* access modifiers changed from: private */
    public final int[] rowCounts;
    /* access modifiers changed from: private */
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, ImmutableMap<C, V>> rowMap;
    /* access modifiers changed from: private */
    public final V[][] values;

    DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> cellList, ImmutableSet<R> rowSpace, ImmutableSet<C> columnSpace) {
        int size = rowSpace.size();
        int[] iArr = new int[2];
        iArr[1] = columnSpace.size();
        iArr[0] = size;
        this.values = (Object[][]) Array.newInstance(Object.class, iArr);
        ImmutableMap<R, Integer> indexMap = Maps.indexMap(rowSpace);
        this.rowKeyToIndex = indexMap;
        ImmutableMap<C, Integer> indexMap2 = Maps.indexMap(columnSpace);
        this.columnKeyToIndex = indexMap2;
        this.rowCounts = new int[indexMap.size()];
        this.columnCounts = new int[indexMap2.size()];
        int[] cellRowIndices2 = new int[cellList.size()];
        int[] cellColumnIndices2 = new int[cellList.size()];
        for (int i = 0; i < cellList.size(); i++) {
            Table.Cell<R, C, V> cell = (Table.Cell) cellList.get(i);
            R rowKey = cell.getRowKey();
            C columnKey = cell.getColumnKey();
            int rowIndex = ((Integer) Objects.requireNonNull(this.rowKeyToIndex.get(rowKey))).intValue();
            int columnIndex = ((Integer) Objects.requireNonNull(this.columnKeyToIndex.get(columnKey))).intValue();
            checkNoDuplicate(rowKey, columnKey, this.values[rowIndex][columnIndex], cell.getValue());
            this.values[rowIndex][columnIndex] = cell.getValue();
            int[] iArr2 = this.rowCounts;
            iArr2[rowIndex] = iArr2[rowIndex] + 1;
            int[] iArr3 = this.columnCounts;
            iArr3[columnIndex] = iArr3[columnIndex] + 1;
            cellRowIndices2[i] = rowIndex;
            cellColumnIndices2[i] = columnIndex;
        }
        ImmutableList<Table.Cell<R, C, V>> immutableList = cellList;
        this.cellRowIndices = cellRowIndices2;
        this.cellColumnIndices = cellColumnIndices2;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    private static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        private final int size;

        /* access modifiers changed from: package-private */
        @CheckForNull
        public abstract V getValue(int i);

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap<K, Integer> keyToIndex();

        ImmutableArrayMap(int size2) {
            this.size = size2;
        }

        private boolean isFull() {
            return this.size == keyToIndex().size();
        }

        /* access modifiers changed from: package-private */
        public K getKey(int index) {
            return keyToIndex().keySet().asList().get(index);
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<K> createKeySet() {
            return isFull() ? keyToIndex().keySet() : super.createKeySet();
        }

        public int size() {
            return this.size;
        }

        @CheckForNull
        public V get(@CheckForNull Object key) {
            Integer keyIndex = (Integer) keyToIndex().get(key);
            if (keyIndex == null) {
                return null;
            }
            return getValue(keyIndex.intValue());
        }

        /* access modifiers changed from: package-private */
        public UnmodifiableIterator<Map.Entry<K, V>> entryIterator() {
            return new AbstractIterator<Map.Entry<K, V>>() {
                private int index = -1;
                private final int maxIndex;

                {
                    this.maxIndex = ImmutableArrayMap.this.keyToIndex().size();
                }

                /* access modifiers changed from: protected */
                @CheckForNull
                public Map.Entry<K, V> computeNext() {
                    int i = this.index;
                    while (true) {
                        this.index = i + 1;
                        int i2 = this.index;
                        if (i2 >= this.maxIndex) {
                            return (Map.Entry) endOfData();
                        }
                        V value = ImmutableArrayMap.this.getValue(i2);
                        if (value != null) {
                            return Maps.immutableEntry(ImmutableArrayMap.this.getKey(this.index), value);
                        }
                        i = this.index;
                    }
                }
            };
        }
    }

    private final class Row extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        Row(int rowIndex2) {
            super(DenseImmutableTable.this.rowCounts[rowIndex2]);
            this.rowIndex = rowIndex2;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[this.rowIndex][keyIndex];
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }
    }

    private final class Column extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        Column(int columnIndex2) {
            super(DenseImmutableTable.this.columnCounts[columnIndex2]);
            this.columnIndex = columnIndex2;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public V getValue(int keyIndex) {
            return DenseImmutableTable.this.values[keyIndex][this.columnIndex];
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return true;
        }
    }

    private final class RowMap extends ImmutableArrayMap<R, ImmutableMap<C, V>> {
        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<R, Integer> keyToIndex() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<C, V> getValue(int keyIndex) {
            return new Row(keyIndex);
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }
    }

    private final class ColumnMap extends ImmutableArrayMap<C, ImmutableMap<R, V>> {
        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<C, Integer> keyToIndex() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<R, V> getValue(int keyIndex) {
            return new Column(keyIndex);
        }

        /* access modifiers changed from: package-private */
        public boolean isPartialView() {
            return false;
        }
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.copyOf(this.columnMap);
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.copyOf(this.rowMap);
    }

    @CheckForNull
    public V get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
        Integer rowIndex = this.rowKeyToIndex.get(rowKey);
        Integer columnIndex = this.columnKeyToIndex.get(columnKey);
        if (rowIndex == null || columnIndex == null) {
            return null;
        }
        return this.values[rowIndex.intValue()][columnIndex.intValue()];
    }

    public int size() {
        return this.cellRowIndices.length;
    }

    /* access modifiers changed from: package-private */
    public Table.Cell<R, C, V> getCell(int index) {
        int rowIndex = this.cellRowIndices[index];
        int columnIndex = this.cellColumnIndices[index];
        return cellOf(rowKeySet().asList().get(rowIndex), columnKeySet().asList().get(columnIndex), Objects.requireNonNull(this.values[rowIndex][columnIndex]));
    }

    /* access modifiers changed from: package-private */
    public V getValue(int index) {
        return Objects.requireNonNull(this.values[this.cellRowIndices[index]][this.cellColumnIndices[index]]);
    }

    /* access modifiers changed from: package-private */
    public ImmutableTable.SerializedForm createSerializedForm() {
        return ImmutableTable.SerializedForm.create(this, this.cellRowIndices, this.cellColumnIndices);
    }
}
