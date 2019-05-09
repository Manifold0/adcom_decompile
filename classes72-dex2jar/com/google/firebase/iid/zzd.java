// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import android.content.BroadcastReceiver$PendingResult;
import android.content.Intent;

final class zzd
{
    final Intent intent;
    private final BroadcastReceiver$PendingResult zzp;
    private boolean zzq;
    private final ScheduledFuture<?> zzr;
    
    zzd(final Intent intent, final BroadcastReceiver$PendingResult zzp, final ScheduledExecutorService scheduledExecutorService) {
        this.zzq = false;
        this.intent = intent;
        this.zzp = zzp;
        this.zzr = scheduledExecutorService.schedule(new zze(this, intent), 9000L, TimeUnit.MILLISECONDS);
    }
    
    final void finish() {
        synchronized (this) {
            if (!this.zzq) {
                this.zzp.finish();
                this.zzr.cancel(false);
                this.zzq = true;
            }
        }
    }
}
