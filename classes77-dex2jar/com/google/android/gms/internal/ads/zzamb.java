// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzamb extends zzr<zzp>
{
    private final zzaoj<zzp> zzctn;
    private final Map<String, String> zzcto;
    private final zzamy zzctp;
    
    public zzamb(final String s, final zzaoj<zzp> zzaoj) {
        this(s, null, zzaoj);
    }
    
    private zzamb(final String s, final Map<String, String> map, final zzaoj<zzp> zzctn) {
        super(0, s, new zzamc(zzctn));
        this.zzcto = null;
        this.zzctn = zzctn;
        (this.zzctp = new zzamy()).zza(s, "GET", (Map)null, (byte[])null);
    }
    
    @Override
    protected final zzx<zzp> zza(final zzp zzp) {
        return zzx.zza(zzp, com.google.android.gms.internal.ads.zzap.zzb(zzp));
    }
}
