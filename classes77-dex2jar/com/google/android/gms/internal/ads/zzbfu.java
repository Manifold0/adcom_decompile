// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfu extends zzbfc<zzbfu>
{
    private static volatile zzbfu[] zzedm;
    public String url;
    public Integer zzedn;
    public zzbfp zzedo;
    private zzbfr zzedp;
    private Integer zzedq;
    private int[] zzedr;
    private String zzeds;
    public Integer zzedt;
    public String[] zzedu;
    
    public zzbfu() {
        this.zzedn = null;
        this.url = null;
        this.zzedo = null;
        this.zzedp = null;
        this.zzedq = null;
        this.zzedr = zzbfl.zzeby;
        this.zzeds = null;
        this.zzedt = null;
        this.zzedu = zzbfl.zzecd;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzbfu zzac(final zzbez zzbez) throws IOException {
    Label_0113:
        while (true) {
            final int zzabk = zzbez.zzabk();
            switch (zzabk) {
                default: {
                    if (!super.zza(zzbez, zzabk)) {
                        break Label_0113;
                    }
                    continue;
                }
                case 0: {
                    break Label_0113;
                }
                case 8: {
                    this.zzedn = zzbez.zzabn();
                    continue;
                }
                case 18: {
                    this.url = zzbez.readString();
                    continue;
                }
                case 26: {
                    if (this.zzedo == null) {
                        this.zzedo = new zzbfp();
                    }
                    zzbez.zza((zzbfi)this.zzedo);
                    continue;
                }
                case 34: {
                    if (this.zzedp == null) {
                        this.zzedp = new zzbfr();
                    }
                    zzbez.zza((zzbfi)this.zzedp);
                    continue;
                }
                case 40: {
                    this.zzedq = zzbez.zzabn();
                    continue;
                }
                case 48: {
                    final int zzb = zzbfl.zzb(zzbez, 48);
                    int length;
                    if (this.zzedr == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzedr.length;
                    }
                    final int[] zzedr = new int[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzedr, 0, zzedr, 0, length);
                        i = length;
                    }
                    while (i < zzedr.length - 1) {
                        zzedr[i] = zzbez.zzabn();
                        zzbez.zzabk();
                        ++i;
                    }
                    zzedr[i] = zzbez.zzabn();
                    this.zzedr = zzedr;
                    continue;
                }
                case 50: {
                    final int zzbr = zzbez.zzbr(zzbez.zzacc());
                    final int position = zzbez.getPosition();
                    int n = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzabn();
                        ++n;
                    }
                    zzbez.zzdc(position);
                    int length2;
                    if (this.zzedr == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzedr.length;
                    }
                    final int[] zzedr2 = new int[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.zzedr, 0, zzedr2, 0, length2);
                        j = length2;
                    }
                    while (j < zzedr2.length) {
                        zzedr2[j] = zzbez.zzabn();
                        ++j;
                    }
                    this.zzedr = zzedr2;
                    zzbez.zzbs(zzbr);
                    continue;
                }
                case 58: {
                    this.zzeds = zzbez.readString();
                    continue;
                }
                case 64: {
                    final int position2 = zzbez.getPosition();
                    try {
                        final int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 3) {
                            throw new IllegalArgumentException(new StringBuilder(46).append(zzabn).append(" is not a valid enum AdResourceType").toString());
                        }
                        this.zzedt = zzabn;
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position2);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 74: {
                    final int zzb2 = zzbfl.zzb(zzbez, 74);
                    int length3;
                    if (this.zzedu == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zzedu.length;
                    }
                    final String[] zzedu = new String[zzb2 + length3];
                    int k = length3;
                    if (length3 != 0) {
                        System.arraycopy(this.zzedu, 0, zzedu, 0, length3);
                        k = length3;
                    }
                    while (k < zzedu.length - 1) {
                        zzedu[k] = zzbez.readString();
                        zzbez.zzabk();
                        ++k;
                    }
                    zzedu[k] = zzbez.readString();
                    this.zzedu = zzedu;
                    continue;
                }
            }
        }
        return this;
    }
    
    public static zzbfu[] zzagu() {
        Label_0027: {
            if (zzbfu.zzedm != null) {
                break Label_0027;
            }
            synchronized (zzbfg.zzebs) {
                if (zzbfu.zzedm == null) {
                    zzbfu.zzedm = new zzbfu[0];
                }
                return zzbfu.zzedm;
            }
        }
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        final int n = 0;
        zzbfa.zzm(1, (int)this.zzedn);
        if (this.url != null) {
            zzbfa.zzf(2, this.url);
        }
        if (this.zzedo != null) {
            zzbfa.zza(3, (zzbfi)this.zzedo);
        }
        if (this.zzedp != null) {
            zzbfa.zza(4, (zzbfi)this.zzedp);
        }
        if (this.zzedq != null) {
            zzbfa.zzm(5, (int)this.zzedq);
        }
        if (this.zzedr != null && this.zzedr.length > 0) {
            for (int i = 0; i < this.zzedr.length; ++i) {
                zzbfa.zzm(6, this.zzedr[i]);
            }
        }
        if (this.zzeds != null) {
            zzbfa.zzf(7, this.zzeds);
        }
        if (this.zzedt != null) {
            zzbfa.zzm(8, (int)this.zzedt);
        }
        if (this.zzedu != null && this.zzedu.length > 0) {
            for (int j = n; j < this.zzedu.length; ++j) {
                final String s = this.zzedu[j];
                if (s != null) {
                    zzbfa.zzf(9, s);
                }
            }
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int n3;
        final int n2 = n3 = super.zzr() + zzbfa.zzq(1, (int)this.zzedn);
        if (this.url != null) {
            n3 = n2 + zzbfa.zzg(2, this.url);
        }
        int n4 = n3;
        if (this.zzedo != null) {
            n4 = n3 + zzbfa.zzb(3, (zzbfi)this.zzedo);
        }
        int n5 = n4;
        if (this.zzedp != null) {
            n5 = n4 + zzbfa.zzb(4, (zzbfi)this.zzedp);
        }
        int n6 = n5;
        if (this.zzedq != null) {
            n6 = n5 + zzbfa.zzq(5, (int)this.zzedq);
        }
        int n7 = n6;
        if (this.zzedr != null) {
            n7 = n6;
            if (this.zzedr.length > 0) {
                int i = 0;
                int n8 = 0;
                while (i < this.zzedr.length) {
                    n8 += zzbfa.zzce(this.zzedr[i]);
                    ++i;
                }
                n7 = n6 + n8 + this.zzedr.length * 1;
            }
        }
        int n9 = n7;
        if (this.zzeds != null) {
            n9 = n7 + zzbfa.zzg(7, this.zzeds);
        }
        int n10 = n9;
        if (this.zzedt != null) {
            n10 = n9 + zzbfa.zzq(8, (int)this.zzedt);
        }
        int n11 = n10;
        if (this.zzedu != null) {
            n11 = n10;
            if (this.zzedu.length > 0) {
                int n12 = 0;
                int n13 = 0;
                int n14;
                int n15;
                for (int j = n; j < this.zzedu.length; ++j, n12 = n14, n13 = n15) {
                    final String s = this.zzedu[j];
                    n14 = n12;
                    n15 = n13;
                    if (s != null) {
                        n15 = n13 + 1;
                        n14 = n12 + zzbfa.zzeo(s);
                    }
                }
                n11 = n10 + n12 + n13 * 1;
            }
        }
        return n11;
    }
}
