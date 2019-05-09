package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzoq;

final class zzbi implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzoq zzww;

    zzbi(zzbc zzbc, zzoq zzoq) {
        this.zzaaf = zzbc;
        this.zzww = zzoq;
    }

    public final void run() {
        try {
            if (this.zzaaf.zzvw.zzadf != null) {
                this.zzaaf.zzvw.zzadf.zza(this.zzww);
                this.zzaaf.zzb(this.zzww.zzka());
            }
        } catch (Throwable e) {
            zzakb.zzd("#007 Could not call remote method.", e);
        }
    }
}
