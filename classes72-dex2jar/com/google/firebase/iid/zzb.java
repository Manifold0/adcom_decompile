// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.content.Intent;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import android.os.Binder;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.ExecutorService;
import android.app.Service;

public abstract class zzb extends Service
{
    private final Object lock;
    @VisibleForTesting
    final ExecutorService zzi;
    private Binder zzj;
    private int zzk;
    private int zzl;
    
    public zzb() {
        final String value = String.valueOf(this.getClass().getSimpleName());
        String concat;
        if (value.length() != 0) {
            concat = "Firebase-".concat(value);
        }
        else {
            concat = new String("Firebase-");
        }
        this.zzi = Executors.newSingleThreadExecutor((ThreadFactory)new NamedThreadFactory(concat));
        this.lock = new Object();
        this.zzl = 0;
    }
    
    private final void zza(final Intent intent) {
        if (intent != null) {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
        synchronized (this.lock) {
            --this.zzl;
            if (this.zzl == 0) {
                this.stopSelfResult(this.zzk);
            }
        }
    }
    
    public final IBinder onBind(final Intent intent) {
        synchronized (this) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "Service received bind request");
            }
            if (this.zzj == null) {
                this.zzj = new zzf(this);
            }
            return (IBinder)this.zzj;
        }
    }
    
    public final int onStartCommand(final Intent intent, final int n, final int zzk) {
        Object o = this.lock;
        synchronized (o) {
            this.zzk = zzk;
            ++this.zzl;
            // monitorexit(o)
            o = this.zzb(intent);
            if (o == null) {
                this.zza(intent);
                return 2;
            }
        }
        final Intent intent2;
        if (this.zzc((Intent)o)) {
            this.zza(intent2);
            return 2;
        }
        this.zzi.execute(new zzc(this, (Intent)o, intent2));
        return 3;
    }
    
    protected Intent zzb(final Intent intent) {
        return intent;
    }
    
    public boolean zzc(final Intent intent) {
        return false;
    }
    
    public abstract void zzd(final Intent p0);
}
