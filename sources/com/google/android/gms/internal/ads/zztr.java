package com.google.android.gms.internal.ads;

final class zztr implements Runnable {
    private final /* synthetic */ zzts zzbob;
    private final /* synthetic */ zztt zzboc;

    zztr(zzst zzst, zzts zzts, zztt zztt) {
        this.zzbob = zzts;
        this.zzboc = zztt;
    }

    public final void run() {
        try {
            this.zzbob.zzb(this.zzboc);
        } catch (Throwable e) {
            zzakb.zzc("Could not propagate interstitial ad event.", e);
        }
    }
}
