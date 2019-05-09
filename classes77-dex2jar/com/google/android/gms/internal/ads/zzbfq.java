// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfq extends zzbfc<zzbfq>
{
    private byte[] zzede;
    private byte[] zzedf;
    private byte[] zzedg;
    
    public zzbfq() {
        this.zzede = null;
        this.zzedf = null;
        this.zzedg = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzede != null) {
            zzbfa.zza(1, this.zzede);
        }
        if (this.zzedf != null) {
            zzbfa.zza(2, this.zzedf);
        }
        if (this.zzedg != null) {
            zzbfa.zza(3, this.zzedg);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzede != null) {
            zzr = n + zzbfa.zzb(1, this.zzede);
        }
        int n2 = zzr;
        if (this.zzedf != null) {
            n2 = zzr + zzbfa.zzb(2, this.zzedf);
        }
        int n3 = n2;
        if (this.zzedg != null) {
            n3 = n2 + zzbfa.zzb(3, this.zzedg);
        }
        return n3;
    }
}
