package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfv extends zzbfc<zzbfv> {
    public String zzedv;
    public Long zzedw;
    public Boolean zzedx;

    public zzbfv() {
        this.zzedv = null;
        this.zzedw = null;
        this.zzedx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzedv = zzbez.readString();
                    continue;
                case 16:
                    this.zzedw = Long.valueOf(zzbez.zzabm());
                    continue;
                case 24:
                    this.zzedx = Boolean.valueOf(zzbez.zzabq());
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
        if (this.zzedv != null) {
            zzbfa.zzf(1, this.zzedv);
        }
        if (this.zzedw != null) {
            zzbfa.zzi(2, this.zzedw.longValue());
        }
        if (this.zzedx != null) {
            zzbfa.zzf(3, this.zzedx.booleanValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzedv != null) {
            zzr += zzbfa.zzg(1, this.zzedv);
        }
        if (this.zzedw != null) {
            zzr += zzbfa.zzd(2, this.zzedw.longValue());
        }
        if (this.zzedx == null) {
            return zzr;
        }
        this.zzedx.booleanValue();
        return zzr + (zzbfa.zzcd(3) + 1);
    }
}
