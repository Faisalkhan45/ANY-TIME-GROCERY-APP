package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteMutationQueue$$ExternalSyntheticLambda7 implements Consumer {
    public final /* synthetic */ SQLiteMutationQueue f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ SQLiteMutationQueue$$ExternalSyntheticLambda7(SQLiteMutationQueue sQLiteMutationQueue, List list, int i) {
        this.f$0 = sQLiteMutationQueue;
        this.f$1 = list;
        this.f$2 = i;
    }

    public final void accept(Object obj) {
        this.f$0.m378lambda$getAllMutationBatchesAffectingQuery$10$comgooglefirebasefirestorelocalSQLiteMutationQueue(this.f$1, this.f$2, (Cursor) obj);
    }
}
