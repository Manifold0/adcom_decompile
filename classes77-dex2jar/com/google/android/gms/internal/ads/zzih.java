// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzih extends zzbfc<zzih>
{
    private Integer zzanc;
    private zzit zzand;
    private String zzane;
    private String zzanf;
    
    public zzih() {
        this.zzanc = null;
        this.zzand = null;
        this.zzane = null;
        this.zzanf = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzih zzj(final zzbez zzbez) throws IOException {
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
                case 40: {
                    final int position = zzbez.getPosition();
                    try {
                        final int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(new StringBuilder(40).append(zzacc).append(" is not a valid enum Platform").toString());
                        }
                        this.zzanc = zzacc;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 50: {
                    if (this.zzand == null) {
                        this.zzand = new zzit();
                    }
                    zzbez.zza((zzbfi)this.zzand);
                    continue;
                }
                case 58: {
                    this.zzane = zzbez.readString();
                    continue;
                }
                case 66: {
                    this.zzanf = zzbez.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzanc != null) {
            zzbfa.zzm(5, (int)this.zzanc);
        }
        if (this.zzand != null) {
            zzbfa.zza(6, (zzbfi)this.zzand);
        }
        if (this.zzane != null) {
            zzbfa.zzf(7, this.zzane);
        }
        if (this.zzanf != null) {
            zzbfa.zzf(8, this.zzanf);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzanc != null) {
            zzr = n + zzbfa.zzq(5, (int)this.zzanc);
        }
        int n2 = zzr;
        if (this.zzand != null) {
            n2 = zzr + zzbfa.zzb(6, (zzbfi)this.zzand);
        }
        int n3 = n2;
        if (this.zzane != null) {
            n3 = n2 + zzbfa.zzg(7, this.zzane);
        }
        int n4 = n3;
        if (this.zzanf != null) {
            n4 = n3 + zzbfa.zzg(8, this.zzanf);
        }
        return n4;
    }
}
