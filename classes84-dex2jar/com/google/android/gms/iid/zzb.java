// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import android.util.Log;
import android.os.IBinder;
import com.google.android.gms.internal.zzcwq;
import android.content.Intent;
import java.util.concurrent.Executors;
import android.os.Binder;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.ExecutorService;
import android.app.Service;

public abstract class zzb extends Service
{
    private final Object mLock;
    @VisibleForTesting
    final ExecutorService zzibs;
    private Binder zzibt;
    private int zzibu;
    private int zzibv;
    
    public zzb() {
        this.zzibs = Executors.newSingleThreadExecutor();
        this.mLock = new Object();
        this.zzibv = 0;
    }
    
    private final void zzh(final Intent intent) {
        if (intent != null) {
            zzcwq.completeWakefulIntent(intent);
        }
        synchronized (this.mLock) {
            --this.zzibv;
            if (this.zzibv == 0) {
                this.stopSelfResult(this.zzibu);
            }
        }
    }
    
    public abstract void handleIntent(final Intent p0);
    
    public final IBinder onBind(final Intent intent) {
        synchronized (this) {
            if (Log.isLoggable("EnhancedIntentService", 3)) {
                Log.d("EnhancedIntentService", "Service received bind request");
            }
            if (this.zzibt == null) {
                this.zzibt = new zzf(this);
            }
            return (IBinder)this.zzibt;
        }
    }
    
    public final int onStartCommand(final Intent intent, final int n, final int zzibu) {
        synchronized (this.mLock) {
            this.zzibu = zzibu;
            ++this.zzibv;
            // monitorexit(this.mLock)
            if (intent == null) {
                this.zzh(intent);
                return 2;
            }
        }
        final Intent intent2;
        this.zzibs.execute(new zzc(this, intent2, intent2));
        return 3;
    }
}
