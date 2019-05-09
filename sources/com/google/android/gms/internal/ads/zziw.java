package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziw extends zzbfc<zziw> {
    private Integer zzapp;

    public zziw() {
        this.zzapp = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zziw zzt(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 3) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum VideoErrorCode");
                        }
                        this.zzapp = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
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
        return zzt(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzapp != null) {
            zzbfa.zzm(1, this.zzapp.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        return this.zzapp != null ? zzr + zzbfa.zzq(1, this.zzapp.intValue()) : zzr;
    }
}
