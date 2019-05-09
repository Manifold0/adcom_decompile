// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfn extends zzbfc<zzbfn>
{
    public String zzcnd;
    
    public zzbfn() {
        this.zzcnd = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzcnd != null) {
            zzbfa.zzf(1, this.zzcnd);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzcnd != null) {
            zzr += zzbfa.zzg(1, this.zzcnd);
        }
        return zzr;
    }
}
