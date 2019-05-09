// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziq extends zzbfc<zziq>
{
    private Integer zzaon;
    private Integer zzaoo;
    
    public zziq() {
        this.zzaon = null;
        this.zzaoo = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaon != null) {
            zzbfa.zzm(1, (int)this.zzaon);
        }
        if (this.zzaoo != null) {
            zzbfa.zzm(2, (int)this.zzaoo);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaon != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzaon);
        }
        int n2 = zzr;
        if (this.zzaoo != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzaoo);
        }
        return n2;
    }
}
