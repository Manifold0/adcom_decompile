// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzis extends zzbfc<zzis>
{
    private static volatile zzis[] zzaoq;
    private Integer zzaor;
    private Integer zzaos;
    
    public zzis() {
        this.zzaor = null;
        this.zzaos = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public static zzis[] zzht() {
        Label_0027: {
            if (zzis.zzaoq != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zzis.zzaoq == null) {
                    zzis.zzaoq = new zzis[0];
                }
                return zzis.zzaoq;
            }
        }
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaor != null) {
            zzbfa.zzm(1, (int)this.zzaor);
        }
        if (this.zzaos != null) {
            zzbfa.zzm(2, (int)this.zzaos);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaor != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzaor);
        }
        int n2 = zzr;
        if (this.zzaos != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzaos);
        }
        return n2;
    }
}
