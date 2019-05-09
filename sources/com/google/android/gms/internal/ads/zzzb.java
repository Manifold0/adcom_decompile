package com.google.android.gms.internal.ads;

final class zzzb implements Runnable {
    private final /* synthetic */ zzyq zzbvd;

    zzzb(zzyq zzyq) {
        this.zzbvd = zzyq;
    }

    public final void run() {
        try {
            this.zzbvd.zzbuu.onAdLeftApplication();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }
}
