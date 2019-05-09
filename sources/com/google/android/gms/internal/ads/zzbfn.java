package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfn extends zzbfc<zzbfn> {
    public String zzcnd;

    public zzbfn() {
        this.zzcnd = null;
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
                    this.zzcnd = zzbez.readString();
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
        if (this.zzcnd != null) {
            zzbfa.zzf(1, this.zzcnd);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        return this.zzcnd != null ? zzr + zzbfa.zzg(1, this.zzcnd) : zzr;
    }
}
