package com.google.firebase.firestore;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AggregateQuery$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ AggregateQuery f$0;
    public final /* synthetic */ TaskCompletionSource f$1;

    public /* synthetic */ AggregateQuery$$ExternalSyntheticLambda0(AggregateQuery aggregateQuery, TaskCompletionSource taskCompletionSource) {
        this.f$0 = aggregateQuery;
        this.f$1 = taskCompletionSource;
    }

    public final Object then(Task task) {
        return this.f$0.m300lambda$get$0$comgooglefirebasefirestoreAggregateQuery(this.f$1, task);
    }
}
