package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzif extends zzbfc<zzif> {
    private Integer zzamo;
    private zzis zzamp;
    private zzis zzamq;
    private zzis zzamr;
    private zzis[] zzams;
    private Integer zzamt;

    public zzif() {
        this.zzamo = null;
        this.zzamp = null;
        this.zzamq = null;
        this.zzamr = null;
        this.zzams = zzis.zzht();
        this.zzamt = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzamo = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 18:
                    if (this.zzamp == null) {
                        this.zzamp = new zzis();
                    }
                    zzbez.zza(this.zzamp);
                    continue;
                case 26:
                    if (this.zzamq == null) {
                        this.zzamq = new zzis();
                    }
                    zzbez.zza(this.zzamq);
                    continue;
                case 34:
                    if (this.zzamr == null) {
                        this.zzamr = new zzis();
                    }
                    zzbez.zza(this.zzamr);
                    continue;
                case 42:
                    int zzb = zzbfl.zzb(zzbez, 42);
                    zzabk = this.zzams == null ? 0 : this.zzams.length;
                    Object obj = new zzis[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzams, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzis();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzis();
                    zzbez.zza(obj[zzabk]);
                    this.zzams = obj;
                    continue;
                case 48:
                    this.zzamt = Integer.valueOf(zzbez.zzacc());
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

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzamo != null) {
            zzbfa.zzm(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            zzbfa.zza(2, this.zzamp);
        }
        if (this.zzamq != null) {
            zzbfa.zza(3, this.zzamq);
        }
        if (this.zzamr != null) {
            zzbfa.zza(4, this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            for (zzbfi zzbfi : this.zzams) {
                if (zzbfi != null) {
                    zzbfa.zza(5, zzbfi);
                }
            }
        }
        if (this.zzamt != null) {
            zzbfa.zzm(6, this.zzamt.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamo != null) {
            zzr += zzbfa.zzq(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            zzr += zzbfa.zzb(2, this.zzamp);
        }
        if (this.zzamq != null) {
            zzr += zzbfa.zzb(3, this.zzamq);
        }
        if (this.zzamr != null) {
            zzr += zzbfa.zzb(4, this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            int i = zzr;
            for (zzbfi zzbfi : this.zzams) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(5, zzbfi);
                }
            }
            zzr = i;
        }
        return this.zzamt != null ? zzr + zzbfa.zzq(6, this.zzamt.intValue()) : zzr;
    }
}
