// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfv extends zzbfc<zzbfv>
{
    public String zzedv;
    public Long zzedw;
    public Boolean zzedx;
    
    public zzbfv() {
        this.zzedv = null;
        this.zzedw = null;
        this.zzedx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzedv != null) {
            zzbfa.zzf(1, this.zzedv);
        }
        if (this.zzedw != null) {
            zzbfa.zzi(2, (long)this.zzedw);
        }
        if (this.zzedx != null) {
            zzbfa.zzf(3, (boolean)this.zzedx);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzedv != null) {
            zzr = n + zzbfa.zzg(1, this.zzedv);
        }
        int n2 = zzr;
        if (this.zzedw != null) {
            n2 = zzr + zzbfa.zzd(2, (long)this.zzedw);
        }
        int n3 = n2;
        if (this.zzedx != null) {
            this.zzedx;
            n3 = n2 + (zzbfa.zzcd(3) + 1);
        }
        return n3;
    }
}
