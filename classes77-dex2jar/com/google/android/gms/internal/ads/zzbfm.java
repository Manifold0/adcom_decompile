// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfm extends zzbfc<zzbfm>
{
    public String url;
    public Integer zzamf;
    private Integer zzecg;
    public String zzech;
    private String zzeci;
    public zzbfn zzecj;
    public zzbfu[] zzeck;
    public String zzecl;
    public zzbft zzecm;
    private Boolean zzecn;
    private String[] zzeco;
    private String zzecp;
    private Boolean zzecq;
    private Boolean zzecr;
    private byte[] zzecs;
    public zzbfv zzect;
    public String[] zzecu;
    public String[] zzecv;
    
    public zzbfm() {
        this.zzamf = null;
        this.zzecg = null;
        this.url = null;
        this.zzech = null;
        this.zzeci = null;
        this.zzecj = null;
        this.zzeck = zzbfu.zzagu();
        this.zzecl = null;
        this.zzecm = null;
        this.zzecn = null;
        this.zzeco = zzbfl.zzecd;
        this.zzecp = null;
        this.zzecq = null;
        this.zzecr = null;
        this.zzecs = null;
        this.zzect = null;
        this.zzecu = zzbfl.zzecd;
        this.zzecv = zzbfl.zzecd;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzbfm zzaa(final zzbez zzbez) throws IOException {
    Label_0177:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0177;
                    }
                    continue;
                }
                case 0: {
                    break Label_0177;
                }
                case 10: {
                    this.url = zzbez.readString();
                    continue;
                }
                case 18: {
                    this.zzech = zzbez.readString();
                    continue;
                }
                case 26: {
                    this.zzeci = zzbez.readString();
                    continue;
                }
                case 34: {
                    final int zzb = zzbfl.zzb(zzbez, 34);
                    int length;
                    if (this.zzeck == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzeck.length;
                    }
                    final zzbfu[] zzeck = new zzbfu[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzeck, 0, zzeck, 0, length);
                        i = length;
                    }
                    while (i < zzeck.length - 1) {
                        zzbez.zza((zzbfi)(zzeck[i] = new zzbfu()));
                        zzbez.zzabk();
                        ++i;
                    }
                    zzbez.zza((zzbfi)(zzeck[i] = new zzbfu()));
                    this.zzeck = zzeck;
                    continue;
                }
                case 40: {
                    this.zzecn = zzbez.zzabq();
                    continue;
                }
                case 50: {
                    final int zzb2 = zzbfl.zzb(zzbez, 50);
                    int length2;
                    if (this.zzeco == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzeco.length;
                    }
                    final String[] zzeco = new String[zzb2 + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.zzeco, 0, zzeco, 0, length2);
                        j = length2;
                    }
                    while (j < zzeco.length - 1) {
                        zzeco[j] = zzbez.readString();
                        zzbez.zzabk();
                        ++j;
                    }
                    zzeco[j] = zzbez.readString();
                    this.zzeco = zzeco;
                    continue;
                }
                case 58: {
                    this.zzecp = zzbez.readString();
                    continue;
                }
                case 64: {
                    this.zzecq = zzbez.zzabq();
                    continue;
                }
                case 72: {
                    this.zzecr = zzbez.zzabq();
                    continue;
                }
                case 80: {
                    final int position = zzbez.getPosition();
                    try {
                        final int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 9) {
                            throw new IllegalArgumentException(new StringBuilder(42).append(zzabn).append(" is not a valid enum ReportType").toString());
                        }
                        this.zzamf = zzabn;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 88: {
                    final int position2 = zzbez.getPosition();
                    try {
                        final int zzabn2 = zzbez.zzabn();
                        if (zzabn2 < 0 || zzabn2 > 4) {
                            throw new IllegalArgumentException(new StringBuilder(39).append(zzabn2).append(" is not a valid enum Verdict").toString());
                        }
                        this.zzecg = zzabn2;
                    }
                    catch (IllegalArgumentException ex2) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 98: {
                    if (this.zzecj == null) {
                        this.zzecj = new zzbfn();
                    }
                    zzbez.zza((zzbfi)this.zzecj);
                    continue;
                }
                case 106: {
                    this.zzecl = zzbez.readString();
                    continue;
                }
                case 114: {
                    if (this.zzecm == null) {
                        this.zzecm = new zzbft();
                    }
                    zzbez.zza((zzbfi)this.zzecm);
                    continue;
                }
                case 122: {
                    this.zzecs = zzbez.readBytes();
                    continue;
                }
                case 138: {
                    if (this.zzect == null) {
                        this.zzect = new zzbfv();
                    }
                    zzbez.zza((zzbfi)this.zzect);
                    continue;
                }
                case 162: {
                    final int zzb3 = zzbfl.zzb(zzbez, 162);
                    int length3;
                    if (this.zzecu == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zzecu.length;
                    }
                    final String[] zzecu = new String[zzb3 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.zzecu, 0, zzecu, 0, length3);
                        k = length3;
                    }
                    while (k < zzecu.length - 1) {
                        zzecu[k] = zzbez.readString();
                        zzbez.zzabk();
                        ++k;
                    }
                    zzecu[k] = zzbez.readString();
                    this.zzecu = zzecu;
                    continue;
                }
                case 170: {
                    final int zzb4 = zzbfl.zzb(zzbez, 170);
                    int length4;
                    if (this.zzecv == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.zzecv.length;
                    }
                    final String[] zzecv = new String[zzb4 + length4];
                    int l = length4;
                    if (length4 != 0) {
                        System.arraycopy(this.zzecv, 0, zzecv, 0, length4);
                        l = length4;
                    }
                    while (l < zzecv.length - 1) {
                        zzecv[l] = zzbez.readString();
                        zzbez.zzabk();
                        ++l;
                    }
                    zzecv[l] = zzbez.readString();
                    this.zzecv = zzecv;
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        final int n = 0;
        if (this.url != null) {
            zzbfa.zzf(1, this.url);
        }
        if (this.zzech != null) {
            zzbfa.zzf(2, this.zzech);
        }
        if (this.zzeci != null) {
            zzbfa.zzf(3, this.zzeci);
        }
        if (this.zzeck != null && this.zzeck.length > 0) {
            for (int i = 0; i < this.zzeck.length; ++i) {
                final zzbfu zzbfu = this.zzeck[i];
                if (zzbfu != null) {
                    zzbfa.zza(4, (zzbfi)zzbfu);
                }
            }
        }
        if (this.zzecn != null) {
            zzbfa.zzf(5, (boolean)this.zzecn);
        }
        if (this.zzeco != null && this.zzeco.length > 0) {
            for (int j = 0; j < this.zzeco.length; ++j) {
                final String s = this.zzeco[j];
                if (s != null) {
                    zzbfa.zzf(6, s);
                }
            }
        }
        if (this.zzecp != null) {
            zzbfa.zzf(7, this.zzecp);
        }
        if (this.zzecq != null) {
            zzbfa.zzf(8, (boolean)this.zzecq);
        }
        if (this.zzecr != null) {
            zzbfa.zzf(9, (boolean)this.zzecr);
        }
        if (this.zzamf != null) {
            zzbfa.zzm(10, (int)this.zzamf);
        }
        if (this.zzecg != null) {
            zzbfa.zzm(11, (int)this.zzecg);
        }
        if (this.zzecj != null) {
            zzbfa.zza(12, (zzbfi)this.zzecj);
        }
        if (this.zzecl != null) {
            zzbfa.zzf(13, this.zzecl);
        }
        if (this.zzecm != null) {
            zzbfa.zza(14, (zzbfi)this.zzecm);
        }
        if (this.zzecs != null) {
            zzbfa.zza(15, this.zzecs);
        }
        if (this.zzect != null) {
            zzbfa.zza(17, (zzbfi)this.zzect);
        }
        if (this.zzecu != null && this.zzecu.length > 0) {
            for (int k = 0; k < this.zzecu.length; ++k) {
                final String s2 = this.zzecu[k];
                if (s2 != null) {
                    zzbfa.zzf(20, s2);
                }
            }
        }
        if (this.zzecv != null && this.zzecv.length > 0) {
            for (int l = n; l < this.zzecv.length; ++l) {
                final String s3 = this.zzecv[l];
                if (s3 != null) {
                    zzbfa.zzf(21, s3);
                }
            }
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int zzr;
        final int n2 = zzr = super.zzr();
        if (this.url != null) {
            zzr = n2 + zzbfa.zzg(1, this.url);
        }
        int n3 = zzr;
        if (this.zzech != null) {
            n3 = zzr + zzbfa.zzg(2, this.zzech);
        }
        int n4 = n3;
        if (this.zzeci != null) {
            n4 = n3 + zzbfa.zzg(3, this.zzeci);
        }
        int n5 = n4;
        if (this.zzeck != null) {
            n5 = n4;
            if (this.zzeck.length > 0) {
                int n6;
                for (int i = 0; i < this.zzeck.length; ++i, n4 = n6) {
                    final zzbfu zzbfu = this.zzeck[i];
                    n6 = n4;
                    if (zzbfu != null) {
                        n6 = n4 + zzbfa.zzb(4, (zzbfi)zzbfu);
                    }
                }
                n5 = n4;
            }
        }
        int n7 = n5;
        if (this.zzecn != null) {
            this.zzecn;
            n7 = n5 + (zzbfa.zzcd(5) + 1);
        }
        int n8 = n7;
        if (this.zzeco != null) {
            n8 = n7;
            if (this.zzeco.length > 0) {
                int j = 0;
                int n9 = 0;
                int n10 = 0;
                while (j < this.zzeco.length) {
                    final String s = this.zzeco[j];
                    int n11 = n9;
                    int n12 = n10;
                    if (s != null) {
                        n12 = n10 + 1;
                        n11 = n9 + zzbfa.zzeo(s);
                    }
                    ++j;
                    n9 = n11;
                    n10 = n12;
                }
                n8 = n7 + n9 + n10 * 1;
            }
        }
        int n13 = n8;
        if (this.zzecp != null) {
            n13 = n8 + zzbfa.zzg(7, this.zzecp);
        }
        int n14 = n13;
        if (this.zzecq != null) {
            this.zzecq;
            n14 = n13 + (zzbfa.zzcd(8) + 1);
        }
        int n15 = n14;
        if (this.zzecr != null) {
            this.zzecr;
            n15 = n14 + (zzbfa.zzcd(9) + 1);
        }
        int n16 = n15;
        if (this.zzamf != null) {
            n16 = n15 + zzbfa.zzq(10, (int)this.zzamf);
        }
        int n17 = n16;
        if (this.zzecg != null) {
            n17 = n16 + zzbfa.zzq(11, (int)this.zzecg);
        }
        int n18 = n17;
        if (this.zzecj != null) {
            n18 = n17 + zzbfa.zzb(12, (zzbfi)this.zzecj);
        }
        int n19 = n18;
        if (this.zzecl != null) {
            n19 = n18 + zzbfa.zzg(13, this.zzecl);
        }
        int n20 = n19;
        if (this.zzecm != null) {
            n20 = n19 + zzbfa.zzb(14, (zzbfi)this.zzecm);
        }
        int n21 = n20;
        if (this.zzecs != null) {
            n21 = n20 + zzbfa.zzb(15, this.zzecs);
        }
        int n22 = n21;
        if (this.zzect != null) {
            n22 = n21 + zzbfa.zzb(17, (zzbfi)this.zzect);
        }
        int n23 = n22;
        if (this.zzecu != null) {
            n23 = n22;
            if (this.zzecu.length > 0) {
                int k = 0;
                int n24 = 0;
                int n25 = 0;
                while (k < this.zzecu.length) {
                    final String s2 = this.zzecu[k];
                    int n26 = n24;
                    int n27 = n25;
                    if (s2 != null) {
                        n27 = n25 + 1;
                        n26 = n24 + zzbfa.zzeo(s2);
                    }
                    ++k;
                    n24 = n26;
                    n25 = n27;
                }
                n23 = n22 + n24 + n25 * 2;
            }
        }
        int n28 = n23;
        if (this.zzecv != null) {
            n28 = n23;
            if (this.zzecv.length > 0) {
                int n29 = 0;
                int n30 = 0;
                int n31;
                int n32;
                for (int l = n; l < this.zzecv.length; ++l, n29 = n31, n30 = n32) {
                    final String s3 = this.zzecv[l];
                    n31 = n29;
                    n32 = n30;
                    if (s3 != null) {
                        n32 = n30 + 1;
                        n31 = n29 + zzbfa.zzeo(s3);
                    }
                }
                n28 = n23 + n29 + n30 * 2;
            }
        }
        return n28;
    }
}
