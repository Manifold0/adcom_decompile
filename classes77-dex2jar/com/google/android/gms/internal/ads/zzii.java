// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzii extends zzbfc<zzii>
{
    private Integer zzang;
    public String zzanh;
    private Integer zzani;
    private Integer zzanj;
    private zzit zzank;
    public long[] zzanl;
    public zzig zzanm;
    private zzih zzann;
    private zzim zzano;
    public zzib zzanp;
    
    public zzii() {
        this.zzang = null;
        this.zzanh = null;
        this.zzani = null;
        this.zzanj = null;
        this.zzank = null;
        this.zzanl = zzbfl.zzebz;
        this.zzanm = null;
        this.zzann = null;
        this.zzano = null;
        this.zzanp = null;
        this.zzebk = null;
        this.zzebt = -1;
    }
    
    private final zzii zzk(final zzbez zzbez) throws IOException {
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
                case 72: {
                    this.zzang = zzbez.zzacc();
                    continue;
                }
                case 82: {
                    this.zzanh = zzbez.readString();
                    continue;
                }
                case 88: {
                    this.zzani = zzbez.zzacc();
                    continue;
                }
                case 96: {
                    final int position = zzbez.getPosition();
                    try {
                        this.zzanj = zzia.zzd(zzbez.zzacc());
                    }
                    catch (IllegalArgumentException ex) {
                        zzbez.zzdc(position);
                        this.zza(zzbez, zzabk);
                    }
                    continue;
                }
                case 106: {
                    if (this.zzank == null) {
                        this.zzank = new zzit();
                    }
                    zzbez.zza((zzbfi)this.zzank);
                    continue;
                }
                case 112: {
                    final int zzb = zzbfl.zzb(zzbez, 112);
                    int length;
                    if (this.zzanl == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzanl.length;
                    }
                    final long[] zzanl = new long[zzb + length];
                    int i = length;
                    if (length != 0) {
                        System.arraycopy(this.zzanl, 0, zzanl, 0, length);
                        i = length;
                    }
                    while (i < zzanl.length - 1) {
                        zzanl[i] = zzbez.zzacd();
                        zzbez.zzabk();
                        ++i;
                    }
                    zzanl[i] = zzbez.zzacd();
                    this.zzanl = zzanl;
                    continue;
                }
                case 114: {
                    final int zzbr = zzbez.zzbr(zzbez.zzacc());
                    final int position2 = zzbez.getPosition();
                    int n = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzacd();
                        ++n;
                    }
                    zzbez.zzdc(position2);
                    int length2;
                    if (this.zzanl == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzanl.length;
                    }
                    final long[] zzanl2 = new long[n + length2];
                    int j = length2;
                    if (length2 != 0) {
                        System.arraycopy(this.zzanl, 0, zzanl2, 0, length2);
                        j = length2;
                    }
                    while (j < zzanl2.length) {
                        zzanl2[j] = zzbez.zzacd();
                        ++j;
                    }
                    this.zzanl = zzanl2;
                    zzbez.zzbs(zzbr);
                    continue;
                }
                case 122: {
                    if (this.zzanm == null) {
                        this.zzanm = new zzig();
                    }
                    zzbez.zza((zzbfi)this.zzanm);
                    continue;
                }
                case 130: {
                    if (this.zzann == null) {
                        this.zzann = new zzih();
                    }
                    zzbez.zza((zzbfi)this.zzann);
                    continue;
                }
                case 138: {
                    if (this.zzano == null) {
                        this.zzano = new zzim();
                    }
                    zzbez.zza((zzbfi)this.zzano);
                    continue;
                }
                case 146: {
                    if (this.zzanp == null) {
                        this.zzanp = new zzib();
                    }
                    zzbez.zza((zzbfi)this.zzanp);
                    continue;
                }
            }
        }
        return this;
    }
    
    public final void zza(final zzbfa zzbfa) throws IOException {
        int i = 0;
        if (this.zzang != null) {
            zzbfa.zzm(9, (int)this.zzang);
        }
        if (this.zzanh != null) {
            zzbfa.zzf(10, this.zzanh);
        }
        if (this.zzani != null) {
            final int intValue = this.zzani;
            zzbfa.zzl(11, 0);
            zzbfa.zzde(intValue);
        }
        if (this.zzanj != null) {
            zzbfa.zzm(12, (int)this.zzanj);
        }
        if (this.zzank != null) {
            zzbfa.zza(13, (zzbfi)this.zzank);
        }
        if (this.zzanl != null && this.zzanl.length > 0) {
            while (i < this.zzanl.length) {
                zzbfa.zza(14, this.zzanl[i]);
                ++i;
            }
        }
        if (this.zzanm != null) {
            zzbfa.zza(15, (zzbfi)this.zzanm);
        }
        if (this.zzann != null) {
            zzbfa.zza(16, (zzbfi)this.zzann);
        }
        if (this.zzano != null) {
            zzbfa.zza(17, (zzbfi)this.zzano);
        }
        if (this.zzanp != null) {
            zzbfa.zza(18, (zzbfi)this.zzanp);
        }
        super.zza(zzbfa);
    }
    
    protected final int zzr() {
        final int n = 0;
        int zzr;
        final int n2 = zzr = super.zzr();
        if (this.zzang != null) {
            zzr = n2 + zzbfa.zzq(9, (int)this.zzang);
        }
        int n3 = zzr;
        if (this.zzanh != null) {
            n3 = zzr + zzbfa.zzg(10, this.zzanh);
        }
        int n4 = n3;
        if (this.zzani != null) {
            n4 = n3 + (zzbfa.zzcl((int)this.zzani) + zzbfa.zzcd(11));
        }
        int n5 = n4;
        if (this.zzanj != null) {
            n5 = n4 + zzbfa.zzq(12, (int)this.zzanj);
        }
        int n6 = n5;
        if (this.zzank != null) {
            n6 = n5 + zzbfa.zzb(13, (zzbfi)this.zzank);
        }
        int n7 = n6;
        if (this.zzanl != null) {
            n7 = n6;
            if (this.zzanl.length > 0) {
                int n8 = 0;
                for (int i = n; i < this.zzanl.length; ++i) {
                    n8 += zzbfa.zzy(this.zzanl[i]);
                }
                n7 = n6 + n8 + this.zzanl.length * 1;
            }
        }
        int n9 = n7;
        if (this.zzanm != null) {
            n9 = n7 + zzbfa.zzb(15, (zzbfi)this.zzanm);
        }
        int n10 = n9;
        if (this.zzann != null) {
            n10 = n9 + zzbfa.zzb(16, (zzbfi)this.zzann);
        }
        int n11 = n10;
        if (this.zzano != null) {
            n11 = n10 + zzbfa.zzb(17, (zzbfi)this.zzano);
        }
        int n12 = n11;
        if (this.zzanp != null) {
            n12 = n11 + zzbfa.zzb(18, (zzbfi)this.zzanp);
        }
        return n12;
    }
}
