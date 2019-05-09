package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzib extends zzbfc<zzib> {
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

    private final zzib zze(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int position;
            Object obj;
            switch (zzabk) {
                case 0:
                    break;
                case 56:
                    position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 9) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum AdInitiater");
                        }
                        this.zzalt = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 64:
                    position = zzbez.getPosition();
                    try {
                        this.zzalu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 74:
                    if (this.zzalv == null) {
                        this.zzalv = new zzid();
                    }
                    zzbez.zza(this.zzalv);
                    continue;
                case 82:
                    if (this.zzalw == null) {
                        this.zzalw = new zzie();
                    }
                    zzbez.zza(this.zzalw);
                    continue;
                case 90:
                    position = zzbfl.zzb(zzbez, 90);
                    zzabk = this.zzalx == null ? 0 : this.zzalx.length;
                    obj = new zzic[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzalx, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzic();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzic();
                    zzbez.zza(obj[zzabk]);
                    this.zzalx = obj;
                    continue;
                case 98:
                    if (this.zzaly == null) {
                        this.zzaly = new zzif();
                    }
                    zzbez.zza(this.zzaly);
                    continue;
                case 106:
                    if (this.zzalz == null) {
                        this.zzalz = new zzio();
                    }
                    zzbez.zza(this.zzalz);
                    continue;
                case 114:
                    if (this.zzama == null) {
                        this.zzama = new zzin();
                    }
                    zzbez.zza(this.zzama);
                    continue;
                case 122:
                    if (this.zzamb == null) {
                        this.zzamb = new zzik();
                    }
                    zzbez.zza(this.zzamb);
                    continue;
                case 130:
                    if (this.zzamc == null) {
                        this.zzamc = new zzil();
                    }
                    zzbez.zza(this.zzamc);
                    continue;
                case 138:
                    position = zzbfl.zzb(zzbez, 138);
                    zzabk = this.zzamd == null ? 0 : this.zzamd.length;
                    obj = new zziu[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzamd, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zziu();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zziu();
                    zzbez.zza(obj[zzabk]);
                    this.zzamd = obj;
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
        return zze(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        int i = 0;
        if (this.zzalt != null) {
            zzbfa.zzm(7, this.zzalt.intValue());
        }
        if (this.zzalu != null) {
            zzbfa.zzm(8, this.zzalu.intValue());
        }
        if (this.zzalv != null) {
            zzbfa.zza(9, this.zzalv);
        }
        if (this.zzalw != null) {
            zzbfa.zza(10, this.zzalw);
        }
        if (this.zzalx != null && this.zzalx.length > 0) {
            for (zzbfi zzbfi : this.zzalx) {
                if (zzbfi != null) {
                    zzbfa.zza(11, zzbfi);
                }
            }
        }
        if (this.zzaly != null) {
            zzbfa.zza(12, this.zzaly);
        }
        if (this.zzalz != null) {
            zzbfa.zza(13, this.zzalz);
        }
        if (this.zzama != null) {
            zzbfa.zza(14, this.zzama);
        }
        if (this.zzamb != null) {
            zzbfa.zza(15, this.zzamb);
        }
        if (this.zzamc != null) {
            zzbfa.zza(16, this.zzamc);
        }
        if (this.zzamd != null && this.zzamd.length > 0) {
            while (i < this.zzamd.length) {
                zzbfi zzbfi2 = this.zzamd[i];
                if (zzbfi2 != null) {
                    zzbfa.zza(17, zzbfi2);
                }
                i++;
            }
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzalt != null) {
            zzr += zzbfa.zzq(7, this.zzalt.intValue());
        }
        if (this.zzalu != null) {
            zzr += zzbfa.zzq(8, this.zzalu.intValue());
        }
        if (this.zzalv != null) {
            zzr += zzbfa.zzb(9, this.zzalv);
        }
        if (this.zzalw != null) {
            zzr += zzbfa.zzb(10, this.zzalw);
        }
        if (this.zzalx != null && this.zzalx.length > 0) {
            int i2 = zzr;
            for (zzbfi zzbfi : this.zzalx) {
                if (zzbfi != null) {
                    i2 += zzbfa.zzb(11, zzbfi);
                }
            }
            zzr = i2;
        }
        if (this.zzaly != null) {
            zzr += zzbfa.zzb(12, this.zzaly);
        }
        if (this.zzalz != null) {
            zzr += zzbfa.zzb(13, this.zzalz);
        }
        if (this.zzama != null) {
            zzr += zzbfa.zzb(14, this.zzama);
        }
        if (this.zzamb != null) {
            zzr += zzbfa.zzb(15, this.zzamb);
        }
        if (this.zzamc != null) {
            zzr += zzbfa.zzb(16, this.zzamc);
        }
        if (this.zzamd != null && this.zzamd.length > 0) {
            while (i < this.zzamd.length) {
                zzbfi zzbfi2 = this.zzamd[i];
                if (zzbfi2 != null) {
                    zzr += zzbfa.zzb(17, zzbfi2);
                }
                i++;
            }
        }
        return zzr;
    }
}
