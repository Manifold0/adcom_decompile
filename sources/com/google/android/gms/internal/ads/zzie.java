package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzie extends zzbfc<zzie> {
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

    private final zzie zzh(zzbez zzbez) throws IOException {
        int zzb;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzamj = zzbez.readString();
                    continue;
                case 18:
                    zzb = zzbfl.zzb(zzbez, 18);
                    zzabk = this.zzamk == null ? 0 : this.zzamk.length;
                    Object obj = new zzic[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzamk, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzic();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzic();
                    zzbez.zza(obj[zzabk]);
                    this.zzamk = obj;
                    continue;
                case 24:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzaml = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 32:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzamm = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 40:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzamn = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e3) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
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
        return zzh(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzamj != null) {
            zzbfa.zzf(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            for (zzbfi zzbfi : this.zzamk) {
                if (zzbfi != null) {
                    zzbfa.zza(2, zzbfi);
                }
            }
        }
        if (this.zzaml != null) {
            zzbfa.zzm(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            zzbfa.zzm(4, this.zzamm.intValue());
        }
        if (this.zzamn != null) {
            zzbfa.zzm(5, this.zzamn.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamj != null) {
            zzr += zzbfa.zzg(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            int i = zzr;
            for (zzbfi zzbfi : this.zzamk) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(2, zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzaml != null) {
            zzr += zzbfa.zzq(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            zzr += zzbfa.zzq(4, this.zzamm.intValue());
        }
        return this.zzamn != null ? zzr + zzbfa.zzq(5, this.zzamn.intValue()) : zzr;
    }
}
