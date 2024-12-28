package com.google.android.gms.wallet;

import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.google.android.gms.internal.wallet.zzh;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolvableResult;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzc<TResult extends AutoResolvableResult> implements OnCompleteListener<TResult>, Runnable {
    static final Handler zza = new zzh(Looper.getMainLooper());
    static final SparseArray<zzc<?>> zzb = new SparseArray<>(2);
    private static final AtomicInteger zzd = new AtomicInteger();
    int zzc;
    private zzd zze;
    private Task<TResult> zzf;

    zzc() {
    }

    public static <TResult extends AutoResolvableResult> zzc<TResult> zza(Task<TResult> task) {
        zzc<TResult> zzc2 = new zzc<>();
        int incrementAndGet = zzd.incrementAndGet();
        zzc2.zzc = incrementAndGet;
        zzb.put(incrementAndGet, zzc2);
        zza.postDelayed(zzc2, AutoResolveHelper.zzb);
        task.addOnCompleteListener(zzc2);
        return zzc2;
    }

    private final void zzd() {
        if (this.zzf != null && this.zze != null) {
            zzb.delete(this.zzc);
            zza.removeCallbacks(this);
            zzd zzd2 = this.zze;
            if (zzd2 != null) {
                zzd2.zzb(this.zzf);
            }
        }
    }

    public final void onComplete(Task<TResult> task) {
        this.zzf = task;
        zzd();
    }

    public final void run() {
        zzb.delete(this.zzc);
    }

    public final void zzb(zzd zzd2) {
        if (this.zze == zzd2) {
            this.zze = null;
        }
    }

    public final void zzc(zzd zzd2) {
        this.zze = zzd2;
        zzd();
    }
}
