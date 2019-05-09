// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.os.Looper;
import java.util.concurrent.CountDownLatch;

final class zzh extends Thread
{
    private final /* synthetic */ CountDownLatch zzcl;
    private final /* synthetic */ DriveEventService zzcm;
    
    zzh(final DriveEventService zzcm, final CountDownLatch zzcl) {
        this.zzcm = zzcm;
        this.zzcl = zzcl;
    }
    
    @Override
    public final void run() {
        try {
            Looper.prepare();
            this.zzcm.zzci = new DriveEventService.zza(this.zzcm, null);
            this.zzcm.zzcj = false;
            this.zzcl.countDown();
            Looper.loop();
        }
        finally {
            if (this.zzcm.zzch != null) {
                this.zzcm.zzch.countDown();
            }
        }
    }
}
