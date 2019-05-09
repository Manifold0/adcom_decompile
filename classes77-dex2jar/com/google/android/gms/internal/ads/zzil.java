// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzil extends zzbfc<zzil>
{
    private zzis zzant;
    private Integer zzanu;
    private zzij zzanv;
    private zzir[] zzanw;
    
    public zzil() {
        this.zzanv = null;
        this.zzanw = zzir.zzhs();
        this.zzanu = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzil zzn(final zzbez zzbez) throws IOException {
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
                    if (this.zzanv == null) {
                        this.zzanv = new zzij();
                    }
                    zzbez.zza((zzbfi)this.zzanv);
                    continue;
                }
                case 18: {
                    final int zzb = zzbfl.zzb(zzbez, 18);
                    int length;
                    if (this.zzanw == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzanw.length;
                    }
                    final zzir[] zzanw = new zzir[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzanw, 0, zzanw, 0, length);
                        i = length;
                    }
                    while (i < zzanw.length - 1) {
                        zzbez.zza((zzbfi)(zzanw[i] = new zzir()));
                        zzbez.zzabk();
                        ++i;
                    }
                    zzbez.zza((zzbfi)(zzanw[i] = new zzir()));
                    this.zzanw = zzanw;
                    continue;
                }
                case 24: {
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
                case 34: {
                    if (this.zzant == null) {
                        this.zzant = new zzis();
                    }
                    zzbez.zza((zzbfi)this.zzant);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzanv != null) {
            zzbfa.zza(1, (zzbfi)this.zzanv);
        }
        if (this.zzanw != null && this.zzanw.length > 0) {
            for (int i = 0; i < this.zzanw.length; ++i) {
                final zzir zzir = this.zzanw[i];
                if (zzir != null) {
                    zzbfa.zza(2, (zzbfi)zzir);
                }
            }
        }
        if (this.zzanu != null) {
            zzbfa.zzm(3, (int)this.zzanu);
        }
        if (this.zzant != null) {
            zzbfa.zza(4, (zzbfi)this.zzant);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzanv != null) {
            zzr = n + zzbfa.zzb(1, (zzbfi)this.zzanv);
        }
        int n2 = zzr;
        if (this.zzanw != null) {
            n2 = zzr;
            if (this.zzanw.length > 0) {
                int n3;
                for (int i = 0; i < this.zzanw.length; ++i, zzr = n3) {
                    final zzir zzir = this.zzanw[i];
                    n3 = zzr;
                    if (zzir != null) {
                        n3 = zzr + zzbfa.zzb(2, (zzbfi)zzir);
                    }
                }
                n2 = zzr;
            }
        }
        int n4 = n2;
        if (this.zzanu != null) {
            n4 = n2 + zzbfa.zzq(3, (int)this.zzanu);
        }
        int n5 = n4;
        if (this.zzant != null) {
            n5 = n4 + zzbfa.zzb(4, (zzbfi)this.zzant);
        }
        return n5;
    }
}
