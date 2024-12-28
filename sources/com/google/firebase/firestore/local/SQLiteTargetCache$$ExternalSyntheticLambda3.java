package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SQLiteTargetCache$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ SQLiteTargetCache f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ SQLiteTargetCache$$ExternalSyntheticLambda3(SQLiteTargetCache sQLiteTargetCache, Consumer consumer) {
        this.f$0 = sQLiteTargetCache;
        this.f$1 = consumer;
    }

    public final void accept(Object obj) {
        this.f$0.m399lambda$forEachTarget$1$comgooglefirebasefirestorelocalSQLiteTargetCache(this.f$1, (Cursor) obj);
    }
}
