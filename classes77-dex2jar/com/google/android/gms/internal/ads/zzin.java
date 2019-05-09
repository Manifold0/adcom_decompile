// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzin extends zzbfc<zzin>
{
    private Integer zzany;
    private zzis zzanz;
    
    public zzin() {
        this.zzany = null;
        this.zzanz = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzin zzp(final zzbez zzbez) throws IOException {
    Label_0049:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0049;
                    }
                    continue;
                }
                case 0: {
                    break Label_0049;
                }
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzany = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 18: {
                    if (this.zzanz == null) {
                        this.zzanz = new zzis();
                    }
                    zzbez.zza((zzbfi)this.zzanz);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzany != null) {
            zzbfa.zzm(1, (int)this.zzany);
        }
        if (this.zzanz != null) {
            zzbfa.zza(2, (zzbfi)this.zzanz);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzany != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzany);
        }
        int n2 = zzr;
        if (this.zzanz != null) {
            n2 = zzr + zzbfa.zzb(2, (zzbfi)this.zzanz);
        }
        return n2;
    }
}
