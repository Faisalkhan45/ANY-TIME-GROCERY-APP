package com.google.firebase.firestore;

import com.google.firebase.firestore.Transaction;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FirebaseFirestore$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ FirebaseFirestore f$0;
    public final /* synthetic */ Transaction.Function f$1;
    public final /* synthetic */ com.google.firebase.firestore.core.Transaction f$2;

    public /* synthetic */ FirebaseFirestore$$ExternalSyntheticLambda0(FirebaseFirestore firebaseFirestore, Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) {
        this.f$0 = firebaseFirestore;
        this.f$1 = function;
        this.f$2 = transaction;
    }

    public final Object call() {
        return this.f$0.m306lambda$runTransaction$0$comgooglefirebasefirestoreFirebaseFirestore(this.f$1, this.f$2);
    }
}
