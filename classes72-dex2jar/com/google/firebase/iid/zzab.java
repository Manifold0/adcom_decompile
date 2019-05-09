// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Bundle;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import android.support.annotation.VisibleForTesting;
import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.concurrent.GuardedBy;

public final class zzab
{
    @GuardedBy("MessengerIpcClient.class")
    private static zzab zzbt;
    private final ScheduledExecutorService zzbu;
    @GuardedBy("this")
    private zzad zzbv;
    @GuardedBy("this")
    private int zzbw;
    private final Context zzx;
    
    @VisibleForTesting
    private zzab(final Context context, final ScheduledExecutorService zzbu) {
        this.zzbv = new zzad(this, null);
        this.zzbw = 1;
        this.zzbu = zzbu;
        this.zzx = context.getApplicationContext();
    }
    
    private final <T> Task<T> zza(final zzak<T> zzak) {
        synchronized (this) {
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                final String value = String.valueOf(zzak);
                Log.d("MessengerIpcClient", new StringBuilder(String.valueOf(value).length() + 9).append("Queueing ").append(value).toString());
            }
            if (!this.zzbv.zzb(zzak)) {
                (this.zzbv = new zzad(this, null)).zzb(zzak);
            }
            return (Task<T>)zzak.zzcg.getTask();
        }
    }
    
    public static zzab zzc(final Context context) {
        synchronized (zzab.class) {
            if (zzab.zzbt == null) {
                zzab.zzbt = new zzab(context, Executors.newSingleThreadScheduledExecutor((ThreadFactory)new NamedThreadFactory("MessengerIpcClient")));
            }
            return zzab.zzbt;
        }
    }
    
    private final int zzx() {
        synchronized (this) {
            return this.zzbw++;
        }
    }
    
    public final Task<Void> zza(final int n, final Bundle bundle) {
        return this.zza((zzak<Void>)new zzaj(this.zzx(), 2, bundle));
    }
    
    public final Task<Bundle> zzb(final int n, final Bundle bundle) {
        return this.zza((zzak<Bundle>)new zzam(this.zzx(), 1, bundle));
    }
}
