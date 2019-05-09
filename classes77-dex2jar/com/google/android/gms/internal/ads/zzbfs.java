// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfs extends zzbfc<zzbfs>
{
    private byte[] zzedg;
    private Integer zzedj;
    private byte[] zzedk;
    
    public zzbfs() {
        this.zzedj = null;
        this.zzedk = null;
        this.zzedg = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzedj != null) {
            zzbfa.zzm(1, (int)this.zzedj);
        }
        if (this.zzedk != null) {
            zzbfa.zza(2, this.zzedk);
        }
        if (this.zzedg != null) {
            zzbfa.zza(3, this.zzedg);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzedj != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzedj);
        }
        int n2 = zzr;
        if (this.zzedk != null) {
            n2 = zzr + zzbfa.zzb(2, this.zzedk);
        }
        int n3 = n2;
        if (this.zzedg != null) {
            n3 = n2 + zzbfa.zzb(3, this.zzedg);
        }
        return n3;
    }
}
