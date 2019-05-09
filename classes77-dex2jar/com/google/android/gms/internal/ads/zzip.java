// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzip extends zzbfc<zzip>
{
    private Integer zzaol;
    private Integer zzaom;
    
    public zzip() {
        this.zzaol = null;
        this.zzaom = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaol != null) {
            zzbfa.zzm(1, (int)this.zzaol);
        }
        if (this.zzaom != null) {
            zzbfa.zzm(2, (int)this.zzaom);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaol != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzaol);
        }
        int n2 = zzr;
        if (this.zzaom != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzaom);
        }
        return n2;
    }
}
