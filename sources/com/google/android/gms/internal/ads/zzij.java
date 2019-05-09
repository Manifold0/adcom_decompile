package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzij extends zzbfc<zzij> {
    private String zzanq;
    private Integer zzanr;
    private int[] zzans;
    private zzis zzant;

    public zzij() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzans = zzbfl.zzeby;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzij zzl(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int position;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzanq = zzbez.readString();
                    continue;
                case 16:
                    position = zzbez.getPosition();
                    try {
                        this.zzanr = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 24:
                    position = zzbfl.zzb(zzbez, 24);
                    zzabk = this.zzans == null ? 0 : this.zzans.length;
                    Object obj = new int[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzans, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = zzbez.zzacc();
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = zzbez.zzacc();
                    this.zzans = obj;
                    continue;
                case 26:
                    int zzbr = zzbez.zzbr(zzbez.zzacc());
                    position = zzbez.getPosition();
                    zzabk = 0;
                    while (zzbez.zzagn() > 0) {
                        zzbez.zzacc();
                        zzabk++;
                    }
                    zzbez.zzdc(position);
                    position = this.zzans == null ? 0 : this.zzans.length;
                    Object obj2 = new int[(zzabk + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzans, 0, obj2, 0, position);
                    }
                    while (position < obj2.length) {
                        obj2[position] = zzbez.zzacc();
                        position++;
                    }
                    this.zzans = obj2;
                    zzbez.zzbs(zzbr);
                    continue;
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
        return zzl(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanq != null) {
            zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzbfa.zzm(2, this.zzanr.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int zzm : this.zzans) {
                zzbfa.zzm(3, zzm);
            }
        }
        if (this.zzant != null) {
            zzbfa.zza(4, this.zzant);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzanq != null) {
            zzr += zzbfa.zzg(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzr += zzbfa.zzq(2, this.zzanr.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            int i2 = 0;
            while (i < this.zzans.length) {
                i2 += zzbfa.zzce(this.zzans[i]);
                i++;
            }
            zzr = (zzr + i2) + (this.zzans.length * 1);
        }
        return this.zzant != null ? zzr + zzbfa.zzb(4, this.zzant) : zzr;
    }
}
