// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzib extends zzbfc<zzib>
{
    public Integer zzalt;
    private Integer zzalu;
    private zzid zzalv;
    public zzie zzalw;
    private zzic[] zzalx;
    private zzif zzaly;
    private zzio zzalz;
    private zzin zzama;
    private zzik zzamb;
    private zzil zzamc;
    private zziu[] zzamd;
    
    public zzib() {
        this.zzalt = null;
        this.zzalu = null;
        this.zzalv = null;
        this.zzalw = null;
        this.zzalx = zzic.zzhr();
        this.zzaly = null;
        this.zzalz = null;
        this.zzama = null;
        this.zzamb = null;
        this.zzamc = null;
        this.zzamd = zziu.zzhu();
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzib zze(final zzbez zzbez) throws IOException {
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
                case 56: {
                    final int position = zzbez.getPosition();
                    try {
                        final int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 9) {
                            throw new IllegalArgumentException(new StringBuilder(43).append(zzacc).append(" is not a valid enum AdInitiater").toString());
                        }
                        this.zzalt = zzacc;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 64: {
                    final int position2 = zzbez.getPosition();
                    try {
                        this.zzalu = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex2) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 74: {
                    if (this.zzalv == null) {
                        this.zzalv = new zzid();
                    }
                    zzbez.zza((zzbfi)this.zzalv);
                    continue;
                }
                case 82: {
                    if (this.zzalw == null) {
                        this.zzalw = new zzie();
                    }
                    zzbez.zza((zzbfi)this.zzalw);
                    continue;
                }
                case 90: {
                    final int zzb = zzbfl.zzb(zzbez, 90);
                    int length;
                    if (this.zzalx == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzalx.length;
                    }
                    final zzic[] zzalx = new zzic[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzalx, 0, zzalx, 0, length);
                        i = length;
                    }
                    while (i < zzalx.length - 1) {
                        zzbez.zza((zzbfi)(zzalx[i] = new zzic()));
                        zzbez.zzabk();
                        ++i;
                    }
                    zzbez.zza((zzbfi)(zzalx[i] = new zzic()));
                    this.zzalx = zzalx;
                    continue;
                }
                case 98: {
                    if (this.zzaly == null) {
                        this.zzaly = new zzif();
                    }
                    zzbez.zza((zzbfi)this.zzaly);
                    continue;
                }
                case 106: {
                    if (this.zzalz == null) {
                        this.zzalz = new zzio();
                    }
                    zzbez.zza((zzbfi)this.zzalz);
                    continue;
                }
                case 114: {
                    if (this.zzama == null) {
                        this.zzama = new zzin();
                    }
                    zzbez.zza((zzbfi)this.zzama);
                    continue;
                }
                case 122: {
                    if (this.zzamb == null) {
                        this.zzamb = new zzik();
                    }
                    zzbez.zza((zzbfi)this.zzamb);
                    continue;
                }
                case 130: {
                    if (this.zzamc == null) {
                        this.zzamc = new zzil();
                    }
                    zzbez.zza((zzbfi)this.zzamc);
                    continue;
                }
                case 138: {
                    final int zzb2 = zzbfl.zzb(zzbez, 138);
                    int length2;
                    if (this.zzamd == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzamd.length;
                    }
                    final zziu[] zzamd = new zziu[zzb2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.zzamd, 0, zzamd, 0, length2);
                        j = length2;
                    }
                    while (j < zzamd.length - 1) {
                        zzbez.zza((zzbfi)(zzamd[j] = new zziu()));
                        zzbez.zzabk();
                        ++j;
                    }
                    zzbez.zza((zzbfi)(zzamd[j] = new zziu()));
                    this.zzamd = zzamd;
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        final int n = 0;
        if (this.zzalt != null) {
            zzbfa.zzm(7, (int)this.zzalt);
        }
        if (this.zzalu != null) {
            zzbfa.zzm(8, (int)this.zzalu);
        }
        if (this.zzalv != null) {
            zzbfa.zza(9, (zzbfi)this.zzalv);
        }
        if (this.zzalw != null) {
            zzbfa.zza(10, (zzbfi)this.zzalw);
        }
        if (this.zzalx != null && this.zzalx.length > 0) {
            for (int i = 0; i < this.zzalx.length; ++i) {
                final zzic zzic = this.zzalx[i];
                if (zzic != null) {
                    zzbfa.zza(11, (zzbfi)zzic);
                }
            }
        }
        if (this.zzaly != null) {
            zzbfa.zza(12, (zzbfi)this.zzaly);
        }
        if (this.zzalz != null) {
            zzbfa.zza(13, (zzbfi)this.zzalz);
        }
        if (this.zzama != null) {
            zzbfa.zza(14, (zzbfi)this.zzama);
        }
        if (this.zzamb != null) {
            zzbfa.zza(15, (zzbfi)this.zzamb);
        }
        if (this.zzamc != null) {
            zzbfa.zza(16, (zzbfi)this.zzamc);
        }
        if (this.zzamd != null && this.zzamd.length > 0) {
            for (int j = n; j < this.zzamd.length; ++j) {
                final zziu zziu = this.zzamd[j];
                if (zziu != null) {
                    zzbfa.zza(17, (zzbfi)zziu);
                }
            }
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int zzr;
        final int n2 = zzr = super.zzr();
        if (this.zzalt != null) {
            zzr = n2 + zzbfa.zzq(7, (int)this.zzalt);
        }
        int n3 = zzr;
        if (this.zzalu != null) {
            n3 = zzr + zzbfa.zzq(8, (int)this.zzalu);
        }
        int n4 = n3;
        if (this.zzalv != null) {
            n4 = n3 + zzbfa.zzb(9, (zzbfi)this.zzalv);
        }
        int n5 = n4;
        if (this.zzalw != null) {
            n5 = n4 + zzbfa.zzb(10, (zzbfi)this.zzalw);
        }
        int n6 = n5;
        if (this.zzalx != null) {
            n6 = n5;
            if (this.zzalx.length > 0) {
                int n7;
                for (int i = 0; i < this.zzalx.length; ++i, n5 = n7) {
                    final zzic zzic = this.zzalx[i];
                    n7 = n5;
                    if (zzic != null) {
                        n7 = n5 + zzbfa.zzb(11, (zzbfi)zzic);
                    }
                }
                n6 = n5;
            }
        }
        int n8 = n6;
        if (this.zzaly != null) {
            n8 = n6 + zzbfa.zzb(12, (zzbfi)this.zzaly);
        }
        int n9 = n8;
        if (this.zzalz != null) {
            n9 = n8 + zzbfa.zzb(13, (zzbfi)this.zzalz);
        }
        int n10 = n9;
        if (this.zzama != null) {
            n10 = n9 + zzbfa.zzb(14, (zzbfi)this.zzama);
        }
        int n11 = n10;
        if (this.zzamb != null) {
            n11 = n10 + zzbfa.zzb(15, (zzbfi)this.zzamb);
        }
        int n12 = n11;
        if (this.zzamc != null) {
            n12 = n11 + zzbfa.zzb(16, (zzbfi)this.zzamc);
        }
        int n13 = n12;
        if (this.zzamd != null) {
            n13 = n12;
            if (this.zzamd.length > 0) {
                int n14 = n;
                while (true) {
                    n13 = n12;
                    if (n14 >= this.zzamd.length) {
                        break;
                    }
                    final zziu zziu = this.zzamd[n14];
                    int n15 = n12;
                    if (zziu != null) {
                        n15 = n12 + zzbfa.zzb(17, (zzbfi)zziu);
                    }
                    ++n14;
                    n12 = n15;
                }
            }
        }
        return n13;
    }
}
