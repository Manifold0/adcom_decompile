package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzab {
    @GuardedBy("MessengerIpcClient.class")
    private static zzab zzbt;
    private final ScheduledExecutorService zzbu;
    @GuardedBy("this")
    private zzad zzbv = new zzad();
    @GuardedBy("this")
    private int zzbw = 1;
    private final Context zzx;

    public static synchronized zzab zzc(Context context) {
        zzab zzab;
        synchronized (zzab.class) {
            if (zzbt == null) {
                zzbt = new zzab(context, Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("MessengerIpcClient")));
            }
            zzab = zzbt;
        }
        return zzab;
    }

    @VisibleForTesting
    private zzab(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbu = scheduledExecutorService;
        this.zzx = context.getApplicationContext();
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza(new zzaj(zzx(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza(new zzam(zzx(), 1, bundle));
    }

    private final synchronized <T> Task<T> zza(zzak<T> zzak) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzak);
            Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(valueOf).length() + 9).append("Queueing ").append(valueOf).toString());
        }
        if (!this.zzbv.zzb(zzak)) {
            this.zzbv = new zzad();
            this.zzbv.zzb(zzak);
        }
        return zzak.zzcg.getTask();
    }

    private final synchronized int zzx() {
        int i;
        i = this.zzbw;
        this.zzbw = i + 1;
        return i;
    }
}
