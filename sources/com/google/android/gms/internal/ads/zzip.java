package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzip extends zzbfc<zzip> {
    private Integer zzaol;
    private Integer zzaom;

    public zzip() {
        this.zzaol = null;
        this.zzaom = null;
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
                    this.zzaol = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 16:
                    this.zzaom = Integer.valueOf(zzbez.zzacc());
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
        if (this.zzaol != null) {
            zzbfa.zzm(1, this.zzaol.intValue());
        }
        if (this.zzaom != null) {
            zzbfa.zzm(2, this.zzaom.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaol != null) {
            zzr += zzbfa.zzq(1, this.zzaol.intValue());
        }
        return this.zzaom != null ? zzr + zzbfa.zzq(2, this.zzaom.intValue()) : zzr;
    }
}
