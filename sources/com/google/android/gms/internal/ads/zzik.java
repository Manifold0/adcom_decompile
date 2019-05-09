package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzik extends zzbfc<zzik> {
    private int[] zzans;
    private Integer zzanu;

    public zzik() {
        this.zzanu = null;
        this.zzans = zzbfl.zzeby;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzik zzm(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            int position;
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    position = zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 16:
                    position = zzbfl.zzb(zzbez, 16);
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
                case 18:
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
        return zzm(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int zzm : this.zzans) {
                zzbfa.zzm(2, zzm);
            }
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        if (this.zzans == null || this.zzans.length <= 0) {
            return zzr;
        }
        int i2 = 0;
        while (i < this.zzans.length) {
            i2 += zzbfa.zzce(this.zzans[i]);
            i++;
        }
        return (zzr + i2) + (this.zzans.length * 1);
    }
}
