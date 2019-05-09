package com.google.android.gms.internal.ads;

final class zzyx implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyx(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdOpened();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
