package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfm extends zzbfc<zzbfm> {
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

    private final zzbfm zzaa(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzb;
            Object obj;
            int zzabn;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.url = zzbez.readString();
                    continue;
                case 18:
                    this.zzech = zzbez.readString();
                    continue;
                case 26:
                    this.zzeci = zzbez.readString();
                    continue;
                case 34:
                    zzb = zzbfl.zzb(zzbez, 34);
                    zzabk = this.zzeck == null ? 0 : this.zzeck.length;
                    obj = new zzbfu[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzeck, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzbfu();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzbfu();
                    zzbez.zza(obj[zzabk]);
                    this.zzeck = obj;
                    continue;
                case 40:
                    this.zzecn = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 50:
                    zzb = zzbfl.zzb(zzbez, 50);
                    zzabk = this.zzeco == null ? 0 : this.zzeco.length;
                    obj = new String[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzeco, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.readString();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.readString();
                    this.zzeco = obj;
                    continue;
                case 58:
                    this.zzecp = zzbez.readString();
                    continue;
                case 64:
                    this.zzecq = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 72:
                    this.zzecr = Boolean.valueOf(zzbez.zzabq());
                    continue;
                case 80:
                    zzb = zzbez.getPosition();
                    try {
                        zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 9) {
                            throw new IllegalArgumentException(zzabn + " is not a valid enum ReportType");
                        }
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 88:
                    zzb = zzbez.getPosition();
                    try {
                        zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 4) {
                            throw new IllegalArgumentException(zzabn + " is not a valid enum Verdict");
                        }
                        this.zzecg = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 98:
                    if (this.zzecj == null) {
                        this.zzecj = new zzbfn();
                    }
                    zzbez.zza(this.zzecj);
                    continue;
                case 106:
                    this.zzecl = zzbez.readString();
                    continue;
                case 114:
                    if (this.zzecm == null) {
                        this.zzecm = new zzbft();
                    }
                    zzbez.zza(this.zzecm);
                    continue;
                case 122:
                    this.zzecs = zzbez.readBytes();
                    continue;
                case 138:
                    if (this.zzect == null) {
                        this.zzect = new zzbfv();
                    }
                    zzbez.zza(this.zzect);
                    continue;
                case 162:
                    zzb = zzbfl.zzb(zzbez, 162);
                    zzabk = this.zzecu == null ? 0 : this.zzecu.length;
                    obj = new String[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzecu, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.readString();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.readString();
                    this.zzecu = obj;
                    continue;
                case 170:
                    zzb = zzbfl.zzb(zzbez, 170);
                    zzabk = this.zzecv == null ? 0 : this.zzecv.length;
                    obj = new String[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzecv, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.readString();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.readString();
                    this.zzecv = obj;
                    continue;
                default:
                    if (!super.zza(zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        return zzaa(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        int i = 0;
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
            for (zzbfi zzbfi : this.zzeck) {
                if (zzbfi != null) {
                    zzbfa.zza(4, zzbfi);
                }
            }
        }
        if (this.zzecn != null) {
            zzbfa.zzf(5, this.zzecn.booleanValue());
        }
        if (this.zzeco != null && this.zzeco.length > 0) {
            for (String str : this.zzeco) {
                if (str != null) {
                    zzbfa.zzf(6, str);
                }
            }
        }
        if (this.zzecp != null) {
            zzbfa.zzf(7, this.zzecp);
        }
        if (this.zzecq != null) {
            zzbfa.zzf(8, this.zzecq.booleanValue());
        }
        if (this.zzecr != null) {
            zzbfa.zzf(9, this.zzecr.booleanValue());
        }
        if (this.zzamf != null) {
            zzbfa.zzm(10, this.zzamf.intValue());
        }
        if (this.zzecg != null) {
            zzbfa.zzm(11, this.zzecg.intValue());
        }
        if (this.zzecj != null) {
            zzbfa.zza(12, this.zzecj);
        }
        if (this.zzecl != null) {
            zzbfa.zzf(13, this.zzecl);
        }
        if (this.zzecm != null) {
            zzbfa.zza(14, this.zzecm);
        }
        if (this.zzecs != null) {
            zzbfa.zza(15, this.zzecs);
        }
        if (this.zzect != null) {
            zzbfa.zza(17, this.zzect);
        }
        if (this.zzecu != null && this.zzecu.length > 0) {
            for (String str2 : this.zzecu) {
                if (str2 != null) {
                    zzbfa.zzf(20, str2);
                }
            }
        }
        if (this.zzecv != null && this.zzecv.length > 0) {
            while (i < this.zzecv.length) {
                String str3 = this.zzecv[i];
                if (str3 != null) {
                    zzbfa.zzf(21, str3);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int zzr = super.zzr();
        if (this.url != null) {
            zzr += zzbfa.zzg(1, this.url);
        }
        if (this.zzech != null) {
            zzr += zzbfa.zzg(2, this.zzech);
        }
        if (this.zzeci != null) {
            zzr += zzbfa.zzg(3, this.zzeci);
        }
        if (this.zzeck != null && this.zzeck.length > 0) {
            i = zzr;
            for (zzbfi zzbfi : this.zzeck) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(4, zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzecn != null) {
            this.zzecn.booleanValue();
            zzr += zzbfa.zzcd(5) + 1;
        }
        if (this.zzeco != null && this.zzeco.length > 0) {
            i2 = 0;
            i3 = 0;
            for (String str : this.zzeco) {
                if (str != null) {
                    i3++;
                    i2 += zzbfa.zzeo(str);
                }
            }
            zzr = (zzr + i2) + (i3 * 1);
        }
        if (this.zzecp != null) {
            zzr += zzbfa.zzg(7, this.zzecp);
        }
        if (this.zzecq != null) {
            this.zzecq.booleanValue();
            zzr += zzbfa.zzcd(8) + 1;
        }
        if (this.zzecr != null) {
            this.zzecr.booleanValue();
            zzr += zzbfa.zzcd(9) + 1;
        }
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(10, this.zzamf.intValue());
        }
        if (this.zzecg != null) {
            zzr += zzbfa.zzq(11, this.zzecg.intValue());
        }
        if (this.zzecj != null) {
            zzr += zzbfa.zzb(12, this.zzecj);
        }
        if (this.zzecl != null) {
            zzr += zzbfa.zzg(13, this.zzecl);
        }
        if (this.zzecm != null) {
            zzr += zzbfa.zzb(14, this.zzecm);
        }
        if (this.zzecs != null) {
            zzr += zzbfa.zzb(15, this.zzecs);
        }
        if (this.zzect != null) {
            zzr += zzbfa.zzb(17, this.zzect);
        }
        if (this.zzecu != null && this.zzecu.length > 0) {
            i2 = 0;
            i3 = 0;
            for (String str2 : this.zzecu) {
                if (str2 != null) {
                    i3++;
                    i2 += zzbfa.zzeo(str2);
                }
            }
            zzr = (zzr + i2) + (i3 * 2);
        }
        if (this.zzecv == null || this.zzecv.length <= 0) {
            return zzr;
        }
        i = 0;
        i2 = 0;
        while (i4 < this.zzecv.length) {
            String str3 = this.zzecv[i4];
            if (str3 != null) {
                i2++;
                i += zzbfa.zzeo(str3);
            }
            i4++;
        }
        return (zzr + i) + (i2 * 2);
    }
}
