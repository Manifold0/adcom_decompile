package com.google.android.gms.drive.events;

import android.os.Looper;
import java.util.concurrent.CountDownLatch;

final class zzh extends Thread {
    private final /* synthetic */ CountDownLatch zzcl;
    private final /* synthetic */ DriveEventService zzcm;

    zzh(DriveEventService driveEventService, CountDownLatch countDownLatch) {
        this.zzcm = driveEventService;
        this.zzcl = countDownLatch;
    }

    public final void run() {
        try {
            Looper.prepare();
            this.zzcm.zzci = new zza();
            this.zzcm.zzcj = false;
            this.zzcl.countDown();
            Looper.loop();
        } finally {
            if (this.zzcm.zzch != null) {
                this.zzcm.zzch.countDown();
            }
        }
    }
}
