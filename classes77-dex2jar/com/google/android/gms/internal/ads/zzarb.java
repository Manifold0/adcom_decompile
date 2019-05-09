// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzn;

final class zzarb implements zzn
{
    private zzn zzbnc;
    private zzaqw zzdcj;
    
    public zzarb(final zzaqw zzdcj, final zzn zzbnc) {
        this.zzdcj = zzdcj;
        this.zzbnc = zzbnc;
    }
    
    @Override
    public final void onPause() {
    }
    
    @Override
    public final void onResume() {
    }
    
    @Override
    public final void zzcb() {
        this.zzbnc.zzcb();
        this.zzdcj.zzty();
    }
    
    @Override
    public final void zzcc() {
        this.zzbnc.zzcc();
        this.zzdcj.zzno();
    }
}
