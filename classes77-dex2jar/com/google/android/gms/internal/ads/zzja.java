// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzja extends zzbfc<zzja>
{
    private Integer zzanu;
    private Integer zzape;
    private Integer zzapf;
    private zziw zzapn;
    private Integer zzapr;
    private Long zzaps;
    
    public zzja() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzape = null;
        this.zzapf = null;
        this.zzapr = null;
        this.zzaps = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzja zzx(final zzbez zzbez) throws IOException {
    Label_0081:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0081;
                    }
                    continue;
                }
                case 0: {
                    break Label_0081;
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
                case 24: {
                    this.zzape = zzbez.zzacc();
                    continue;
                }
                case 32: {
                    this.zzapf = zzbez.zzacc();
                    continue;
                }
                case 40: {
                    this.zzapr = zzbez.zzacc();
                    continue;
                }
                case 48: {
                    this.zzaps = zzbez.zzacd();
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
        if (this.zzape != null) {
            zzbfa.zzm(3, (int)this.zzape);
        }
        if (this.zzapf != null) {
            zzbfa.zzm(4, (int)this.zzapf);
        }
        if (this.zzapr != null) {
            zzbfa.zzm(5, (int)this.zzapr);
        }
        if (this.zzaps != null) {
            zzbfa.zza(6, (long)this.zzaps);
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
        if (this.zzape != null) {
            n3 = n2 + zzbfa.zzq(3, (int)this.zzape);
        }
        int n4 = n3;
        if (this.zzapf != null) {
            n4 = n3 + zzbfa.zzq(4, (int)this.zzapf);
        }
        int n5 = n4;
        if (this.zzapr != null) {
            n5 = n4 + zzbfa.zzq(5, (int)this.zzapr);
        }
        int n6 = n5;
        if (this.zzaps != null) {
            n6 = n5 + zzbfa.zze(6, (long)this.zzaps);
        }
        return n6;
    }
}
