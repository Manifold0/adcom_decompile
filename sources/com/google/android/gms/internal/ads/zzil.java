package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzil extends zzbfc<zzil> {
    private zzis zzant;
    private Integer zzanu;
    private zzij zzanv;
    private zzir[] zzanw;

    public zzil() {
        this.zzanv = null;
        this.zzanw = zzir.zzhs();
        this.zzanu = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzil zzn(zzbez zzbez) throws IOException {
        int zzb;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzanv == null) {
                        this.zzanv = new zzij();
                    }
                    zzbez.zza(this.zzanv);
                    continue;
                case 18:
                    zzb = zzbfl.zzb(zzbez, 18);
                    zzabk = this.zzanw == null ? 0 : this.zzanw.length;
                    Object obj = new zzir[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzanw, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzir();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzir();
                    zzbez.zza(obj[zzabk]);
                    this.zzanw = obj;
                    continue;
                case 24:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(zzb);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 34:
                    if (this.zzant == null) {
                        this.zzant = new zzis();
                    }
                    zzbez.zza(this.zzant);
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
        return zzn(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanv != null) {
            zzbfa.zza(1, this.zzanv);
        }
        if (this.zzanw != null && this.zzanw.length > 0) {
            for (zzbfi zzbfi : this.zzanw) {
                if (zzbfi != null) {
                    zzbfa.zza(2, zzbfi);
                }
            }
        }
        if (this.zzanu != null) {
            zzbfa.zzm(3, this.zzanu.intValue());
        }
        if (this.zzant != null) {
            zzbfa.zza(4, this.zzant);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanv != null) {
            zzr += zzbfa.zzb(1, this.zzanv);
        }
        if (this.zzanw != null && this.zzanw.length > 0) {
            int i = zzr;
            for (zzbfi zzbfi : this.zzanw) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(2, zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(3, this.zzanu.intValue());
        }
        return this.zzant != null ? zzr + zzbfa.zzb(4, this.zzant) : zzr;
    }
}
