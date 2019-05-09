package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.events.OpenFileCallback;

final /* synthetic */ class zzdn implements zzdg {
    private final zzdk zzgj;
    private final zzfb zzgm;

    zzdn(zzdk zzdk, zzfb zzfb) {
        this.zzgj = zzdk;
        this.zzgm = zzfb;
    }

    public final void accept(Object obj) {
        this.zzgj.zza(this.zzgm, (OpenFileCallback) obj);
    }
}
