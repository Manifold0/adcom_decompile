// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaly extends zzav
{
    private final /* synthetic */ byte[] zzctk;
    private final /* synthetic */ Map zzctl;
    private final /* synthetic */ zzamy zzctm;
    
    zzaly(final zzalt zzalt, final int n, final String s, final zzz zzz, final zzy zzy, final byte[] zzctk, final Map zzctl, final zzamy zzctm) {
        this.zzctk = zzctk;
        this.zzctl = zzctl;
        this.zzctm = zzctm;
        super(n, s, zzz, zzy);
    }
    
    @Override
    public final Map<String, String> getHeaders() throws zza {
        if (this.zzctl == null) {
            return super.getHeaders();
        }
        return (Map<String, String>)this.zzctl;
    }
    
    @Override
    public final byte[] zzg() throws zza {
        if (this.zzctk == null) {
            return super.zzg();
        }
        return this.zzctk;
    }
    
    @Override
    protected final void zzh(final String s) {
        this.zzctm.zzdg(s);
        super.zzh(s);
    }
}
