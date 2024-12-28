package androidx.browser.trusted;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

public final class TrustedWebActivityServiceConnectionPool {
    private static final String TAG = "TWAConnectionPool";
    private final Map<Uri, ConnectionHolder> mConnections = new HashMap();
    private final Context mContext;

    private TrustedWebActivityServiceConnectionPool(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static TrustedWebActivityServiceConnectionPool create(Context context) {
        return new TrustedWebActivityServiceConnectionPool(context);
    }

    public ListenableFuture<TrustedWebActivityServiceConnection> connect(Uri scope, Set<Token> possiblePackages, Executor executor) {
        ConnectionHolder connection = this.mConnections.get(scope);
        if (connection != null) {
            return connection.getServiceWrapper();
        }
        Intent bindServiceIntent = createServiceIntent(this.mContext, scope, possiblePackages, true);
        if (bindServiceIntent == null) {
            return FutureUtils.immediateFailedFuture(new IllegalArgumentException("No service exists for scope"));
        }
        ConnectionHolder newConnection = new ConnectionHolder(new TrustedWebActivityServiceConnectionPool$$ExternalSyntheticLambda0(this, scope));
        this.mConnections.put(scope, newConnection);
        new BindToServiceAsyncTask(this.mContext, bindServiceIntent, newConnection).executeOnExecutor(executor, new Void[0]);
        return newConnection.getServiceWrapper();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$connect$0$androidx-browser-trusted-TrustedWebActivityServiceConnectionPool  reason: not valid java name */
    public /* synthetic */ void m880lambda$connect$0$androidxbrowsertrustedTrustedWebActivityServiceConnectionPool(Uri scope) {
        this.mConnections.remove(scope);
    }

    static class BindToServiceAsyncTask extends AsyncTask<Void, Void, Exception> {
        private final Context mAppContext;
        private final ConnectionHolder mConnection;
        private final Intent mIntent;

        BindToServiceAsyncTask(Context context, Intent intent, ConnectionHolder connection) {
            this.mAppContext = context.getApplicationContext();
            this.mIntent = intent;
            this.mConnection = connection;
        }

        /* access modifiers changed from: protected */
        public Exception doInBackground(Void... voids) {
            try {
                if (this.mAppContext.bindService(this.mIntent, this.mConnection, FragmentTransaction.TRANSIT_FRAGMENT_OPEN)) {
                    return null;
                }
                this.mAppContext.unbindService(this.mConnection);
                return new IllegalStateException("Could not bind to the service");
            } catch (SecurityException e) {
                Log.w(TrustedWebActivityServiceConnectionPool.TAG, "SecurityException while binding.", e);
                return e;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Exception bindingException) {
            if (bindingException != null) {
                this.mConnection.cancel(bindingException);
            }
        }
    }

    public boolean serviceExistsForScope(Uri scope, Set<Token> possiblePackages) {
        if (this.mConnections.get(scope) == null && createServiceIntent(this.mContext, scope, possiblePackages, false) == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void unbindAllConnections() {
        for (ConnectionHolder connection : this.mConnections.values()) {
            this.mContext.unbindService(connection);
        }
        this.mConnections.clear();
    }

    private Intent createServiceIntent(Context appContext, Uri scope, Set<Token> possiblePackages, boolean shouldLog) {
        if (possiblePackages == null || possiblePackages.size() == 0) {
            return null;
        }
        Intent scopeResolutionIntent = new Intent();
        scopeResolutionIntent.setData(scope);
        scopeResolutionIntent.setAction("android.intent.action.VIEW");
        String resolvedPackage = null;
        for (ResolveInfo info : appContext.getPackageManager().queryIntentActivities(scopeResolutionIntent, 65536)) {
            String packageName = info.activityInfo.packageName;
            Iterator<Token> it = possiblePackages.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().matches(packageName, appContext.getPackageManager())) {
                        resolvedPackage = packageName;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (resolvedPackage == null) {
            if (shouldLog) {
                Log.w(TAG, "No TWA candidates for " + scope + " have been registered.");
            }
            return null;
        }
        Intent serviceResolutionIntent = new Intent();
        serviceResolutionIntent.setPackage(resolvedPackage);
        serviceResolutionIntent.setAction(TrustedWebActivityService.ACTION_TRUSTED_WEB_ACTIVITY_SERVICE);
        ResolveInfo info2 = appContext.getPackageManager().resolveService(serviceResolutionIntent, 131072);
        if (info2 == null) {
            if (shouldLog) {
                Log.w(TAG, "Could not find TWAService for " + resolvedPackage);
            }
            return null;
        }
        if (shouldLog) {
            Log.i(TAG, "Found " + info2.serviceInfo.name + " to handle request for " + scope);
        }
        Intent finalIntent = new Intent();
        finalIntent.setComponent(new ComponentName(resolvedPackage, info2.serviceInfo.name));
        return finalIntent;
    }
}
