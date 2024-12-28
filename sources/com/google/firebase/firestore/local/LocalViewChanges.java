package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;

public final class LocalViewChanges {
    private final ImmutableSortedSet<DocumentKey> added;
    private final boolean fromCache;
    private final ImmutableSortedSet<DocumentKey> removed;
    private final int targetId;

    public static LocalViewChanges fromViewSnapshot(int targetId2, ViewSnapshot snapshot) {
        ImmutableSortedSet<DocumentKey> addedKeys = new ImmutableSortedSet<>(new ArrayList(), DocumentKey.comparator());
        ImmutableSortedSet<DocumentKey> removedKeys = new ImmutableSortedSet<>(new ArrayList(), DocumentKey.comparator());
        for (DocumentViewChange docChange : snapshot.getChanges()) {
            switch (AnonymousClass1.$SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[docChange.getType().ordinal()]) {
                case 1:
                    addedKeys = addedKeys.insert(docChange.getDocument().getKey());
                    break;
                case 2:
                    removedKeys = removedKeys.insert(docChange.getDocument().getKey());
                    break;
            }
        }
        return new LocalViewChanges(targetId2, snapshot.isFromCache(), addedKeys, removedKeys);
    }

    /* renamed from: com.google.firebase.firestore.local.LocalViewChanges$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public LocalViewChanges(int targetId2, boolean fromCache2, ImmutableSortedSet<DocumentKey> added2, ImmutableSortedSet<DocumentKey> removed2) {
        this.targetId = targetId2;
        this.fromCache = fromCache2;
        this.added = added2;
        this.removed = removed2;
    }

    public int getTargetId() {
        return this.targetId;
    }

    public boolean isFromCache() {
        return this.fromCache;
    }

    public ImmutableSortedSet<DocumentKey> getAdded() {
        return this.added;
    }

    public ImmutableSortedSet<DocumentKey> getRemoved() {
        return this.removed;
    }
}
