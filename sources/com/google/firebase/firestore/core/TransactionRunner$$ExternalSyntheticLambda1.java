package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class TransactionRunner$$ExternalSyntheticLambda1 implements OnCompleteListener {
    public final /* synthetic */ TransactionRunner f$0;
    public final /* synthetic */ Task f$1;

    public /* synthetic */ TransactionRunner$$ExternalSyntheticLambda1(TransactionRunner transactionRunner, Task task) {
        this.f$0 = transactionRunner;
        this.f$1 = task;
    }

    public final void onComplete(Task task) {
        this.f$0.m339lambda$runWithBackoff$0$comgooglefirebasefirestorecoreTransactionRunner(this.f$1, task);
    }
}
