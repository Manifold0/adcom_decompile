// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzie extends zzbfc<zzie>
{
    private String zzamj;
    private zzic[] zzamk;
    private Integer zzaml;
    private Integer zzamm;
    private Integer zzamn;
    
    public zzie() {
        this.zzamj = null;
        this.zzamk = zzic.zzhr();
        this.zzaml = null;
        this.zzamm = null;
        this.zzamn = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzie zzh(final zzbez zzbez) throws IOException {
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
                case 10: {
                    this.zzamj = zzbez.readString();
                    continue;
                }
                case 18: {
                    final int zzb = zzbfl.zzb(zzbez, 18);
                    int length;
                    if (this.zzamk == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzamk.length;
                    }
                    final zzic[] zzamk = new zzic[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzamk, 0, zzamk, 0, length);
                        i = length;
                    }
                    while (i < zzamk.length - 1) {
                        zzbez.zza((zzbfi)(zzamk[i] = new zzic()));
                        zzbez.zzabk();
                        ++i;
                    }
                    zzbez.zza((zzbfi)(zzamk[i] = new zzic()));
                    this.zzamk = zzamk;
                    continue;
                }
                case 24: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzaml = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 32: {
                    final int position2 = zzbez.getPosition();
                    try {
                        this.zzamm = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex2) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 40: {
                    final int position3 = zzbez.getPosition();
                    try {
                        this.zzamn = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex3) {
                        zzbez.zzdc(position3);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        if (this.zzamj != null) {
            zzbfa.zzf(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            for (int i = 0; i < this.zzamk.length; ++i) {
                final zzic zzic = this.zzamk[i];
                if (zzic != null) {
                    zzbfa.zza(2, (zzbfi)zzic);
                }
            }
        }
        if (this.zzaml != null) {
            zzbfa.zzm(3, (int)this.zzaml);
        }
        if (this.zzamm != null) {
            zzbfa.zzm(4, (int)this.zzamm);
        }
        if (this.zzamn != null) {
            zzbfa.zzm(5, (int)this.zzamn);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        int zzr;
        final int n = zzr = super.zzr();
        if (this.zzamj != null) {
            zzr = n + zzbfa.zzg(1, this.zzamj);
        }
        int n2 = zzr;
        if (this.zzamk != null) {
            n2 = zzr;
            if (this.zzamk.length > 0) {
                int n3;
                for (int i = 0; i < this.zzamk.length; ++i, zzr = n3) {
                    final zzic zzic = this.zzamk[i];
                    n3 = zzr;
                    if (zzic != null) {
                        n3 = zzr + zzbfa.zzb(2, (zzbfi)zzic);
                    }
                }
                n2 = zzr;
            }
        }
        int n4 = n2;
        if (this.zzaml != null) {
            n4 = n2 + zzbfa.zzq(3, (int)this.zzaml);
        }
        int n5 = n4;
        if (this.zzamm != null) {
            n5 = n4 + zzbfa.zzq(4, (int)this.zzamm);
        }
        int n6 = n5;
        if (this.zzamn != null) {
            n6 = n5 + zzbfa.zzq(5, (int)this.zzamn);
        }
        return n6;
    }
}
