package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.IAccountAccessor;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
public class AccountAccessor extends IAccountAccessor.Stub {
    public static Account getAccountBinderSafe(IAccountAccessor accountAccessor) {
        Account account = null;
        if (accountAccessor != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                account = accountAccessor.zzb();
            } catch (RemoteException e) {
                Log.w("AccountAccessor", "Remote account accessor probably died");
            } catch (Throwable th) {
                Binder.restoreCallingIdentity(clearCallingIdentity);
                throw th;
            }
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
        return account;
    }

    public final boolean equals(Object obj) {
        throw null;
    }

    public final Account zzb() {
        throw null;
    }
}
