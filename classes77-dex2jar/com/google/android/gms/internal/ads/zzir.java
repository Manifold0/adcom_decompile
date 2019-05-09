// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzir extends zzbfc<zzir>
{
    private static volatile zzir[] zzaop;
    private String zzanq;
    private Integer zzanr;
    private zzis zzant;
    
    public zzir() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    public static zzir[] zzhs() {
        Label_0027: {
            if (zzir.zzaop != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zzir.zzaop == null) {
                    zzir.zzaop = new zzir[0];
                }
                return zzir.zzaop;
            }
        }
    }
    
    private final zzir zzr(final zzbez zzbez) throws IOException {
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
                case 10: {
                    this.zzanq = zzbez.readString();
                    continue;
                }
                case 16: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzanr = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 26: {
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
        if (this.zzanq != null) {
            zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzbfa.zzm(2, (int)this.zzanr);
        }
        if (this.zzant != null) {
            zzbfa.zza(3, (zzbfi)this.zzant);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzanq != null) {
            zzr = n + zzbfa.zzg(1, this.zzanq);
        }
        int n2 = zzr;
        if (this.zzanr != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzanr);
        }
        int n3 = n2;
        if (this.zzant != null) {
            n3 = n2 + zzbfa.zzb(3, (zzbfi)this.zzant);
        }
        return n3;
    }
}
