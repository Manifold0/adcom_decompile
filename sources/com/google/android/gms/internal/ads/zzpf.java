package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

final class zzpf {
    private final WeakReference<zzaqw> zzbjg;
    private String zzbjh;

    public zzpf(zzaqw zzaqw) {
        this.zzbjg = new WeakReference(zzaqw);
    }

    public final void zza(zzacm zzacm) {
        zzacm.zza("/loadHtml", new zzpg(this, zzacm));
        zzacm.zza("/showOverlay", new zzpi(this, zzacm));
        zzacm.zza("/hideOverlay", new zzpj(this, zzacm));
        zzaqw zzaqw = (zzaqw) this.zzbjg.get();
        if (zzaqw != null) {
            zzaqw.zza("/sendMessageToSdk", new zzpk(this, zzacm));
        }
    }
}
