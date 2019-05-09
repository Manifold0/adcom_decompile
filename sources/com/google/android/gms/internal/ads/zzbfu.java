package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfu extends zzbfc<zzbfu> {
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

    private final zzbfu zzac(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzb;
            Object obj;
            int zzbr;
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzedn = Integer.valueOf(zzbez.zzabn());
                    continue;
                case 18:
                    this.url = zzbez.readString();
                    continue;
                case 26:
                    if (this.zzedo == null) {
                        this.zzedo = new zzbfp();
                    }
                    zzbez.zza(this.zzedo);
                    continue;
                case 34:
                    if (this.zzedp == null) {
                        this.zzedp = new zzbfr();
                    }
                    zzbez.zza(this.zzedp);
                    continue;
                case 40:
                    this.zzedq = Integer.valueOf(zzbez.zzabn());
                    continue;
                case 48:
                    zzb = zzbfl.zzb(zzbez, 48);
                    zzabk = this.zzedr == null ? 0 : this.zzedr.length;
                    obj = new int[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzedr, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.zzabn();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.zzabn();
                    this.zzedr = obj;
                    continue;
                case 50:
                    zzbr = zzbez.zzbr(zzbez.zzacc());
                    zzb = zzbez.getPosition();
                    zzabk = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzabn();
                        zzabk++;
                    }
                    zzbez.zzdc(zzb);
                    zzb = this.zzedr == null ? 0 : this.zzedr.length;
                    Object obj2 = new int[(zzabk + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzedr, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = zzbez.zzabn();
                        zzb++;
                    }
                    this.zzedr = obj2;
                    zzbez.zzbs(zzbr);
                    continue;
                case 58:
                    this.zzeds = zzbez.readString();
                    continue;
                case 64:
                    zzb = zzbez.getPosition();
                    try {
                        zzbr = zzbez.zzabn();
                        if (zzbr < 0 || zzbr > 3) {
                            throw new IllegalArgumentException(zzbr + " is not a valid enum AdResourceType");
                        }
                        this.zzedt = Integer.valueOf(zzbr);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 74:
                    zzb = zzbfl.zzb(zzbez, 74);
                    zzabk = this.zzedu == null ? 0 : this.zzedu.length;
                    obj = new String[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzedu, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.readString();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.readString();
                    this.zzedu = obj;
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

    public static zzbfu[] zzagu() {
        if (zzedm == null) {
            synchronized (zzbfg.zzebs) {
                if (zzedm == null) {
                    zzedm = new zzbfu[0];
                }
            }
        }
        return zzedm;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        return zzac(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        int i = 0;
        zzbfa.zzm(1, this.zzedn.intValue());
        if (this.url != null) {
            zzbfa.zzf(2, this.url);
        }
        if (this.zzedo != null) {
            zzbfa.zza(3, this.zzedo);
        }
        if (this.zzedp != null) {
            zzbfa.zza(4, this.zzedp);
        }
        if (this.zzedq != null) {
            zzbfa.zzm(5, this.zzedq.intValue());
        }
        if (this.zzedr != null && this.zzedr.length > 0) {
            for (int zzm : this.zzedr) {
                zzbfa.zzm(6, zzm);
            }
        }
        if (this.zzeds != null) {
            zzbfa.zzf(7, this.zzeds);
        }
        if (this.zzedt != null) {
            zzbfa.zzm(8, this.zzedt.intValue());
        }
        if (this.zzedu != null && this.zzedu.length > 0) {
            while (i < this.zzedu.length) {
                String str = this.zzedu[i];
                if (str != null) {
                    zzbfa.zzf(9, str);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i;
        int i2;
        int i3 = 0;
        int zzr = super.zzr() + zzbfa.zzq(1, this.zzedn.intValue());
        if (this.url != null) {
            zzr += zzbfa.zzg(2, this.url);
        }
        if (this.zzedo != null) {
            zzr += zzbfa.zzb(3, this.zzedo);
        }
        if (this.zzedp != null) {
            zzr += zzbfa.zzb(4, this.zzedp);
        }
        if (this.zzedq != null) {
            zzr += zzbfa.zzq(5, this.zzedq.intValue());
        }
        if (this.zzedr != null && this.zzedr.length > 0) {
            i = 0;
            for (int zzce : this.zzedr) {
                i += zzbfa.zzce(zzce);
            }
            zzr = (zzr + i) + (this.zzedr.length * 1);
        }
        if (this.zzeds != null) {
            zzr += zzbfa.zzg(7, this.zzeds);
        }
        if (this.zzedt != null) {
            zzr += zzbfa.zzq(8, this.zzedt.intValue());
        }
        if (this.zzedu == null || this.zzedu.length <= 0) {
            return zzr;
        }
        i2 = 0;
        i = 0;
        while (i3 < this.zzedu.length) {
            String str = this.zzedu[i3];
            if (str != null) {
                i++;
                i2 += zzbfa.zzeo(str);
            }
            i3++;
        }
        return (zzr + i2) + (i * 1);
    }
}
