package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzaji;

final class zzbd implements Runnable {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ zzaji zzwg;

    zzbd(zzbc zzbc, zzaji zzaji) {
        this.zzaaf = zzbc;
        this.zzwg = zzaji;
    }

    public final void run() {
        this.zzaaf.zzb(new zzajh(this.zzwg, null, null, null, null, null, null, null));
    }
}
