package com.google.android.gms.internal.ads;

final class zzyu implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzyu(zzyq zzyq) {
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
