package com.google.firebase.firestore.remote;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.RunAggregationQueryRequest;
import com.google.firestore.v1.RunAggregationQueryResponse;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Value;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList(new String[]{"date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"}));
    /* access modifiers changed from: private */
    public final FirestoreChannel channel;
    private final DatabaseInfo databaseInfo;
    /* access modifiers changed from: private */
    public final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    public Datastore(DatabaseInfo databaseInfo2, AsyncQueue workerQueue2, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        this.databaseInfo = databaseInfo2;
        this.workerQueue = workerQueue2;
        this.serializer = new RemoteSerializer(databaseInfo2.getDatabaseId());
        this.channel = initializeChannel(databaseInfo2, workerQueue2, authProvider, appCheckProvider, context, metadataProvider);
    }

    /* access modifiers changed from: package-private */
    public FirestoreChannel initializeChannel(DatabaseInfo databaseInfo2, AsyncQueue workerQueue2, CredentialsProvider<User> authProvider, CredentialsProvider<String> appCheckProvider, Context context, GrpcMetadataProvider metadataProvider) {
        return new FirestoreChannel(workerQueue2, context, authProvider, appCheckProvider, databaseInfo2, metadataProvider);
    }

    /* access modifiers changed from: package-private */
    public void shutdown() {
        this.channel.shutdown();
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    /* access modifiers changed from: package-private */
    public DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    /* access modifiers changed from: package-private */
    public WatchStream createWatchStream(WatchStream.Callback listener) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    /* access modifiers changed from: package-private */
    public WriteStream createWriteStream(WriteStream.Callback listener) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, listener);
    }

    public Task<List<MutationResult>> commit(List<Mutation> mutations) {
        CommitRequest.Builder builder = CommitRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (Mutation mutation : mutations) {
            builder.addWrites(this.serializer.encodeMutation(mutation));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), (CommitRequest) builder.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$commit$0$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ List m411lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse response = (CommitResponse) task.getResult();
        SnapshotVersion commitVersion = this.serializer.decodeVersion(response.getCommitTime());
        int count = response.getWriteResultsCount();
        ArrayList<MutationResult> results = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            results.add(this.serializer.decodeMutationResult(response.getWriteResults(i), commitVersion));
        }
        return results;
    }

    public Task<List<MutableDocument>> lookup(final List<DocumentKey> keys) {
        BatchGetDocumentsRequest.Builder builder = BatchGetDocumentsRequest.newBuilder();
        builder.setDatabase(this.serializer.databaseName());
        for (DocumentKey key : keys) {
            builder.addDocuments(this.serializer.encodeKey(key));
        }
        final List<BatchGetDocumentsResponse> responses = new ArrayList<>();
        final TaskCompletionSource<List<MutableDocument>> completionSource = new TaskCompletionSource<>();
        this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), (BatchGetDocumentsRequest) builder.build(), new FirestoreChannel.StreamingListener<BatchGetDocumentsResponse>() {
            public void onMessage(BatchGetDocumentsResponse message) {
                responses.add(message);
                if (responses.size() == keys.size()) {
                    Map<DocumentKey, MutableDocument> resultMap = new HashMap<>();
                    for (BatchGetDocumentsResponse response : responses) {
                        MutableDocument doc = Datastore.this.serializer.decodeMaybeDocument(response);
                        resultMap.put(doc.getKey(), doc);
                    }
                    List<MutableDocument> results = new ArrayList<>();
                    for (DocumentKey key : keys) {
                        results.add(resultMap.get(key));
                    }
                    completionSource.trySetResult(results);
                }
            }

            public void onClose(Status status) {
                if (status.isOk()) {
                    completionSource.trySetResult(Collections.emptyList());
                    return;
                }
                FirebaseFirestoreException exception = Util.exceptionFromStatus(status);
                if (exception.getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                    Datastore.this.channel.invalidateToken();
                }
                completionSource.trySetException(exception);
            }
        });
        return completionSource.getTask();
    }

    public Task<Long> runCountQuery(Query query) {
        Target.QueryTarget encodedQueryTarget = this.serializer.encodeQueryTarget(query.toTarget());
        StructuredAggregationQuery.Builder structuredAggregationQuery = StructuredAggregationQuery.newBuilder();
        structuredAggregationQuery.setStructuredQuery(encodedQueryTarget.getStructuredQuery());
        StructuredAggregationQuery.Aggregation.Builder aggregation = StructuredAggregationQuery.Aggregation.newBuilder();
        aggregation.setCount(StructuredAggregationQuery.Aggregation.Count.getDefaultInstance());
        aggregation.setAlias("count_alias");
        structuredAggregationQuery.addAggregations(aggregation);
        RunAggregationQueryRequest.Builder request = RunAggregationQueryRequest.newBuilder();
        request.setParent(encodedQueryTarget.getParent());
        request.setStructuredAggregationQuery(structuredAggregationQuery);
        return this.channel.runRpc(FirestoreGrpc.getRunAggregationQueryMethod(), (RunAggregationQueryRequest) request.build()).continueWith(this.workerQueue.getExecutor(), new Datastore$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$runCountQuery$1$com-google-firebase-firestore-remote-Datastore  reason: not valid java name */
    public /* synthetic */ Long m412lambda$runCountQuery$1$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        Map<String, Value> aggregateFieldsByAlias = ((RunAggregationQueryResponse) task.getResult()).getResult().getAggregateFieldsMap();
        boolean z = true;
        Assert.hardAssert(aggregateFieldsByAlias.size() == 1, "aggregateFieldsByAlias.size()==" + aggregateFieldsByAlias.size(), new Object[0]);
        Value countValue = aggregateFieldsByAlias.get("count_alias");
        Assert.hardAssert(countValue != null, "countValue == null", new Object[0]);
        if (countValue.getValueTypeCase() != Value.ValueTypeCase.INTEGER_VALUE) {
            z = false;
        }
        Assert.hardAssert(z, "countValue.getValueTypeCase() == " + countValue.getValueTypeCase(), new Object[0]);
        return Long.valueOf(countValue.getIntegerValue());
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    /* renamed from: com.google.firebase.firestore.remote.Datastore$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = iArr;
            try {
                iArr[FirebaseFirestoreException.Code.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (AnonymousClass2.$SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[code.ordinal()]) {
            case 1:
                throw new IllegalArgumentException("Treated status OK as error");
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        Status.Code code = status.getCode();
        Throwable t = status.getCause();
        boolean hasCipherError = false;
        if ((t instanceof SSLHandshakeException) && t.getMessage().contains("no ciphers available")) {
            hasCipherError = true;
        }
        return Build.VERSION.SDK_INT < 21 && code.equals(Status.Code.UNAVAILABLE) && hasCipherError;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
