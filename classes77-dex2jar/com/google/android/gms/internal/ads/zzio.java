// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzio extends zzbfc<zzio>
{
    private Integer zzaoa;
    private Integer zzaob;
    private Integer zzaoc;
    private Integer zzaod;
    private Integer zzaoe;
    private Integer zzaof;
    private Integer zzaog;
    private Integer zzaoh;
    private Integer zzaoi;
    private Integer zzaoj;
    private zzip zzaok;
    
    public zzio() {
        this.zzaoa = null;
        this.zzaob = null;
        this.zzaoc = null;
        this.zzaod = null;
        this.zzaoe = null;
        this.zzaof = null;
        this.zzaog = null;
        this.zzaoh = null;
        this.zzaoi = null;
        this.zzaoj = null;
        this.zzaok = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzio zzq(final zzbez zzbez) throws IOException {
    Label_0121:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0121;
                    }
                    continue;
                }
                case 0: {
                    break Label_0121;
                }
                case 8: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzaoa = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 16: {
                    final int position2 = zzbez.getPosition();
                    try {
                        this.zzaob = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex2) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 24: {
                    this.zzaoc = zzbez.zzacc();
                    continue;
                }
                case 32: {
                    this.zzaod = zzbez.zzacc();
                    continue;
                }
                case 40: {
                    this.zzaoe = zzbez.zzacc();
                    continue;
                }
                case 48: {
                    this.zzaof = zzbez.zzacc();
                    continue;
                }
                case 56: {
                    this.zzaog = zzbez.zzacc();
                    continue;
                }
                case 64: {
                    this.zzaoh = zzbez.zzacc();
                    continue;
                }
                case 72: {
                    this.zzaoi = zzbez.zzacc();
                    continue;
                }
                case 80: {
                    this.zzaoj = zzbez.zzacc();
                    continue;
                }
                case 90: {
                    if (this.zzaok == null) {
                        this.zzaok = new zzip();
                    }
                    zzbez.zza((zzbfi)this.zzaok);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzaoa != null) {
            zzbfa.zzm(1, (int)this.zzaoa);
        }
        if (this.zzaob != null) {
            zzbfa.zzm(2, (int)this.zzaob);
        }
        if (this.zzaoc != null) {
            zzbfa.zzm(3, (int)this.zzaoc);
        }
        if (this.zzaod != null) {
            zzbfa.zzm(4, (int)this.zzaod);
        }
        if (this.zzaoe != null) {
            zzbfa.zzm(5, (int)this.zzaoe);
        }
        if (this.zzaof != null) {
            zzbfa.zzm(6, (int)this.zzaof);
        }
        if (this.zzaog != null) {
            zzbfa.zzm(7, (int)this.zzaog);
        }
        if (this.zzaoh != null) {
            zzbfa.zzm(8, (int)this.zzaoh);
        }
        if (this.zzaoi != null) {
            zzbfa.zzm(9, (int)this.zzaoi);
        }
        if (this.zzaoj != null) {
            zzbfa.zzm(10, (int)this.zzaoj);
        }
        if (this.zzaok != null) {
            zzbfa.zza(11, (zzbfi)this.zzaok);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzaoa != null) {
            zzr = n + zzbfa.zzq(1, (int)this.zzaoa);
        }
        int n2 = zzr;
        if (this.zzaob != null) {
            n2 = zzr + zzbfa.zzq(2, (int)this.zzaob);
        }
        int n3 = n2;
        if (this.zzaoc != null) {
            n3 = n2 + zzbfa.zzq(3, (int)this.zzaoc);
        }
        int n4 = n3;
        if (this.zzaod != null) {
            n4 = n3 + zzbfa.zzq(4, (int)this.zzaod);
        }
        int n5 = n4;
        if (this.zzaoe != null) {
            n5 = n4 + zzbfa.zzq(5, (int)this.zzaoe);
        }
        int n6 = n5;
        if (this.zzaof != null) {
            n6 = n5 + zzbfa.zzq(6, (int)this.zzaof);
        }
        int n7 = n6;
        if (this.zzaog != null) {
            n7 = n6 + zzbfa.zzq(7, (int)this.zzaog);
        }
        int n8 = n7;
        if (this.zzaoh != null) {
            n8 = n7 + zzbfa.zzq(8, (int)this.zzaoh);
        }
        int n9 = n8;
        if (this.zzaoi != null) {
            n9 = n8 + zzbfa.zzq(9, (int)this.zzaoi);
        }
        int n10 = n9;
        if (this.zzaoj != null) {
            n10 = n9 + zzbfa.zzq(10, (int)this.zzaoj);
        }
        int n11 = n10;
        if (this.zzaok != null) {
            n11 = n10 + zzbfa.zzb(11, (zzbfi)this.zzaok);
        }
        return n11;
    }
}
