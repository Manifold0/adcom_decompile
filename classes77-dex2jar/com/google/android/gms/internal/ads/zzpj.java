// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzpj implements zzv<Object>
{
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;
    
    zzpj(final zzpf zzbjj, final zzacm zzbji) {
        this.zzbjj = zzbjj;
        this.zzbji = zzbji;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final zzaqw zzaqw = (zzaqw)this.zzbjj.zzbjg.get();
        if (zzaqw == null) {
            this.zzbji.zzb("/hideOverlay", this);
            return;
        }
        zzaqw.getView().setVisibility(8);
    }
}
