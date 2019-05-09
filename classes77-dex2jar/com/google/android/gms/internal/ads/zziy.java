// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziy extends zzbfc<zziy>
{
    private Integer zzanu;
    private Integer zzape;
    private Integer zzapf;
    private zziw zzapn;
    private Integer zzapr;
    
    public zziy() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzape = null;
        this.zzapf = null;
        this.zzapr = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zziy zzv(final zzbez zzbez) throws IOException {
    Label_0073:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0073;
                    }
                    continue;
                }
                case 0: {
                    break Label_0073;
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
        return n5;
    }
}
