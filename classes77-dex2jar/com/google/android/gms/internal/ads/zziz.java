// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziz extends zzbfc<zziz>
{
    private Integer zzanu;
    private zziw zzapn;
    private zzis zzapo;
    
    public zziz() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzapo = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zziz zzw(final zzbez zzbez) throws IOException {
    Label_0057:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0057;
                    }
                    continue;
                }
                case 0: {
                    break Label_0057;
                }
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzanu = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 18: {
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbez.zza((zzbfi)this.zzapn);
                    continue;
                }
                case 26: {
                    if (this.zzapo == null) {
                        this.zzapo = new zzis();
                    }
                    zzbez.zza((zzbfi)this.zzapo);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, (int)this.zzanu);
        }
        if (this.zzapn != null) {
            zzbfa.zza(2, (zzbfi)this.zzapn);
        }
        if (this.zzapo != null) {
            zzbfa.zza(3, (zzbfi)this.zzapo);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzanu != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzanu);
        }
        int n2 = zzr;
        if (this.zzapn != null) {
            n2 = zzr + zzbfa.zzb(2, (zzbfi)this.zzapn);
        }
        int n3 = n2;
        if (this.zzapo != null) {
            n3 = n2 + zzbfa.zzb(3, (zzbfi)this.zzapo);
        }
        return n3;
    }
}
