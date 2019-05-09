// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzix extends zzbfc<zzix>
{
    private Integer zzanu;
    private zziw zzapn;
    private zzis zzapo;
    private zzit zzapq;
    
    public zzix() {
        this.zzapq = null;
        this.zzanu = null;
        this.zzapn = null;
        this.zzapo = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzix zzu(final zzbez zzbez) throws IOException {
    Label_0065:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0065;
                    }
                    continue;
                }
                case 0: {
                    break Label_0065;
                }
                case 10: {
                    if (this.zzapq == null) {
                        this.zzapq = new zzit();
                    }
                    zzbez.zza((zzbfi)this.zzapq);
                    continue;
                }
                case 16: {
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
                case 26: {
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbez.zza((zzbfi)this.zzapn);
                    continue;
                }
                case 34: {
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
        if (this.zzapq != null) {
            zzbfa.zza(1, (zzbfi)this.zzapq);
        }
        if (this.zzanu != null) {
            zzbfa.zzm(2, (int)this.zzanu);
        }
        if (this.zzapn != null) {
            zzbfa.zza(3, (zzbfi)this.zzapn);
        }
        if (this.zzapo != null) {
            zzbfa.zza(4, (zzbfi)this.zzapo);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzapq != null) {
            zzr = n + zzbfa.zzb(1, (zzbfi)this.zzapq);
        }
        int n2 = zzr;
        if (this.zzanu != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzanu);
        }
        int n3 = n2;
        if (this.zzapn != null) {
            n3 = n2 + zzbfa.zzb(3, (zzbfi)this.zzapn);
        }
        int n4 = n3;
        if (this.zzapo != null) {
            n4 = n3 + zzbfa.zzb(4, (zzbfi)this.zzapo);
        }
        return n4;
    }
}
