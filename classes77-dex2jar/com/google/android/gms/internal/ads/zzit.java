// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzit extends zzbfc<zzit>
{
    public Integer zzaot;
    public Integer zzaou;
    public Integer zzaov;
    
    public zzit() {
        this.zzaot = null;
        this.zzaou = null;
        this.zzaov = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaot != null) {
            zzbfa.zzm(1, (int)this.zzaot);
        }
        if (this.zzaou != null) {
            zzbfa.zzm(2, (int)this.zzaou);
        }
        if (this.zzaov != null) {
            zzbfa.zzm(3, (int)this.zzaov);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaot != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzaot);
        }
        int n2 = zzr;
        if (this.zzaou != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzaou);
        }
        int n3 = n2;
        if (this.zzaov != null) {
            n3 = n2 + zzbfa.zzq(3, (int)this.zzaov);
        }
        return n3;
    }
}
