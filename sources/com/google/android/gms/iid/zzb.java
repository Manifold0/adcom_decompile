package com.google.android.gms.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.internal.zzcwq;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class zzb extends Service {
    private final Object mLock = new Object();
    @VisibleForTesting
    final ExecutorService zzibs = Executors.newSingleThreadExecutor();
    private Binder zzibt;
    private int zzibu;
    private int zzibv = 0;

    private final void zzh(Intent intent) {
        if (intent != null) {
            zzcwq.completeWakefulIntent(intent);
        }
        synchronized (this.mLock) {
            this.zzibv--;
            if (this.zzibv == 0) {
                stopSelfResult(this.zzibu);
            }
        }
    }

    public abstract void handleIntent(Intent intent);

    public final synchronized IBinder onBind(Intent intent) {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "Service received bind request");
        }
        if (this.zzibt == null) {
            this.zzibt = new zzf(this);
        }
        return this.zzibt;
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        synchronized (this.mLock) {
            this.zzibu = i2;
            this.zzibv++;
        }
        if (intent == null) {
            zzh(intent);
            return 2;
        }
        this.zzibs.execute(new zzc(this, intent, intent));
        return 3;
    }
}
