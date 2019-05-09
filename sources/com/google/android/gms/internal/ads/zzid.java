package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzid extends zzbfc<zzid> {
    private String zzacp;
    private zzic[] zzamh;
    private Integer zzami;

    public zzid() {
        this.zzacp = null;
        this.zzamh = zzic.zzhr();
        this.zzami = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzid zzg(zzbez zzbez) throws IOException {
        int zzb;
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzacp = zzbez.readString();
                    continue;
                case 18:
                    zzb = zzbfl.zzb(zzbez, 18);
                    zzabk = this.zzamh == null ? 0 : this.zzamh.length;
                    Object obj = new zzic[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzamh, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzic();
                        zzbez.zza(obj[zzabk]);
                        zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzic();
                    zzbez.zza(obj[zzabk]);
                    this.zzamh = obj;
                    continue;
                case 24:
                    zzb = zzbez.getPosition();
                    try {
                        this.zzami = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
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
        return zzg(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzacp != null) {
            zzbfa.zzf(1, this.zzacp);
        }
        if (this.zzamh != null && this.zzamh.length > 0) {
            for (zzbfi zzbfi : this.zzamh) {
                if (zzbfi != null) {
                    zzbfa.zza(2, zzbfi);
                }
            }
        }
        if (this.zzami != null) {
            zzbfa.zzm(3, this.zzami.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzacp != null) {
            zzr += zzbfa.zzg(1, this.zzacp);
        }
        if (this.zzamh != null && this.zzamh.length > 0) {
            int i = zzr;
            for (zzbfi zzbfi : this.zzamh) {
                if (zzbfi != null) {
                    i += zzbfa.zzb(2, zzbfi);
                }
            }
            zzr = i;
        }
        return this.zzami != null ? zzr + zzbfa.zzq(3, this.zzami.intValue()) : zzr;
    }
}
