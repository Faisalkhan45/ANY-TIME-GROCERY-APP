package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;

public class AggregateQuery {
    private final Query query;

    AggregateQuery(Query query2) {
        this.query = query2;
    }

    public Query getQuery() {
        return this.query;
    }

    public Task<AggregateQuerySnapshot> get(AggregateSource source) {
        Preconditions.checkNotNull(source, "AggregateSource must not be null");
        TaskCompletionSource<AggregateQuerySnapshot> tcs = new TaskCompletionSource<>();
        this.query.firestore.getClient().runCountQuery(this.query.query).continueWith(Executors.DIRECT_EXECUTOR, new AggregateQuery$$ExternalSyntheticLambda0(this, tcs));
        return tcs.getTask();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$get$0$com-google-firebase-firestore-AggregateQuery  reason: not valid java name */
    public /* synthetic */ Object m300lambda$get$0$comgooglefirebasefirestoreAggregateQuery(TaskCompletionSource tcs, Task task) throws Exception {
        if (task.isSuccessful()) {
            tcs.setResult(new AggregateQuerySnapshot(this, ((Long) task.getResult()).longValue()));
            return null;
        }
        tcs.setException(task.getException());
        return null;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof AggregateQuery)) {
            return false;
        }
        return this.query.equals(((AggregateQuery) object).query);
    }

    public int hashCode() {
        return this.query.hashCode();
    }
}
