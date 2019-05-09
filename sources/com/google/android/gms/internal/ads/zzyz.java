package com.google.android.gms.internal.ads;

final class zzyz implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyz(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdClosed();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
