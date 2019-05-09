// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzif extends zzbfc<zzif>
{
    private Integer zzamo;
    private zzis zzamp;
    private zzis zzamq;
    private zzis zzamr;
    private zzis[] zzams;
    private Integer zzamt;
    
    public zzif() {
        this.zzamo = null;
        this.zzamp = null;
        this.zzamq = null;
        this.zzamr = null;
        this.zzams = zzis.zzht();
        this.zzamt = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamo != null) {
            zzbfa.zzm(1, (int)this.zzamo);
        }
        if (this.zzamp != null) {
            zzbfa.zza(2, (zzbfi)this.zzamp);
        }
        if (this.zzamq != null) {
            zzbfa.zza(3, (zzbfi)this.zzamq);
        }
        if (this.zzamr != null) {
            zzbfa.zza(4, (zzbfi)this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            for (int i = 0; i < this.zzams.length; ++i) {
                final zzis zzis = this.zzams[i];
                if (zzis != null) {
                    zzbfa.zza(5, (zzbfi)zzis);
                }
            }
        }
        if (this.zzamt != null) {
            zzbfa.zzm(6, (int)this.zzamt);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzamo != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzamo);
        }
        int n2 = zzr;
        if (this.zzamp != null) {
            n2 = zzr + zzbfa.zzb(2, (zzbfi)this.zzamp);
        }
        int n3 = n2;
        if (this.zzamq != null) {
            n3 = n2 + zzbfa.zzb(3, (zzbfi)this.zzamq);
        }
        int n4 = n3;
        if (this.zzamr != null) {
            n4 = n3 + zzbfa.zzb(4, (zzbfi)this.zzamr);
        }
        int n5 = n4;
        if (this.zzams != null) {
            n5 = n4;
            if (this.zzams.length > 0) {
                int n6;
                for (int i = 0; i < this.zzams.length; ++i, n4 = n6) {
                    final zzis zzis = this.zzams[i];
                    n6 = n4;
                    if (zzis != null) {
                        n6 = n4 + zzbfa.zzb(5, (zzbfi)zzis);
                    }
                }
                n5 = n4;
            }
        }
        int n7 = n5;
        if (this.zzamt != null) {
            n7 = n5 + zzbfa.zzq(6, (int)this.zzamt);
        }
        return n7;
    }
}
