// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.lang.ref.WeakReference;

final class zzpf
{
    private final WeakReference<zzaqw> zzbjg;
    private String zzbjh;
    
    public zzpf(final zzaqw zzaqw) {
        this.zzbjg = new WeakReference<zzaqw>(zzaqw);
    }
    
    public final void zza(final zzacm zzacm) {
        zzacm.zza("/loadHtml", new zzpg(this, zzacm));
        zzacm.zza("/showOverlay", new zzpi(this, zzacm));
        zzacm.zza("/hideOverlay", new zzpj(this, zzacm));
        final zzaqw zzaqw = this.zzbjg.get();
        if (zzaqw != null) {
            zzaqw.zza("/sendMessageToSdk", new zzpk(this, zzacm));
        }
    }
}
