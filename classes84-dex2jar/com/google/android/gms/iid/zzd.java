// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import android.content.BroadcastReceiver$PendingResult;
import android.content.Intent;

final class zzd
{
    final Intent intent;
    private final BroadcastReceiver$PendingResult zziby;
    private boolean zzibz;
    private final ScheduledFuture<?> zzica;
    
    zzd(final Intent intent, final BroadcastReceiver$PendingResult zziby, final ScheduledExecutorService scheduledExecutorService) {
        this.zzibz = false;
        this.intent = intent;
        this.zziby = zziby;
        this.zzica = scheduledExecutorService.schedule(new zze(this, intent), 9500L, TimeUnit.MILLISECONDS);
    }
    
    final void finish() {
        synchronized (this) {
            if (!this.zzibz) {
                this.zziby.finish();
                this.zzica.cancel(false);
                this.zzibz = true;
            }
        }
    }
}
