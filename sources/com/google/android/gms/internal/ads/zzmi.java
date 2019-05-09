package com.google.android.gms.internal.ads;

final class zzmi implements Runnable {
    private final /* synthetic */ zzmh zzatj;

    zzmi(zzmh zzmh) {
        this.zzatj = zzmh;
    }

    public final void run() {
        if (this.zzatj.zzati.zzxs != null) {
            try {
                this.zzatj.zzati.zzxs.onAdFailedToLoad(1);
            } catch (Throwable e) {
                zzane.zzc("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
