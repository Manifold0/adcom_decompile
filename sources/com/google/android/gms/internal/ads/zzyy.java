package com.google.android.gms.internal.ads;

final class zzyy implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyy(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
