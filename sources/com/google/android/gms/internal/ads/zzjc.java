package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjc extends zzbfc<zzjc> {
    private Integer zzanu;
    private zziw zzapn;

    public zzjc() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzjc zzz(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    zzbez.zza(this.zzapn);
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
        return zzz(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzbfa.zza(2, this.zzapn);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        return this.zzapn != null ? zzr + zzbfa.zzb(2, this.zzapn) : zzr;
    }
}
