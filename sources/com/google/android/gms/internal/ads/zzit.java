package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzit extends zzbfc<zzit> {
    public Integer zzaot;
    public Integer zzaou;
    public Integer zzaov;

    public zzit() {
        this.zzaot = null;
        this.zzaou = null;
        this.zzaov = null;
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
                    this.zzaot = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 16:
                    this.zzaou = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 24:
                    this.zzaov = Integer.valueOf(zzbez.zzacc());
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
        if (this.zzaot != null) {
            zzbfa.zzm(1, this.zzaot.intValue());
        }
        if (this.zzaou != null) {
            zzbfa.zzm(2, this.zzaou.intValue());
        }
        if (this.zzaov != null) {
            zzbfa.zzm(3, this.zzaov.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaot != null) {
            zzr += zzbfa.zzq(1, this.zzaot.intValue());
        }
        if (this.zzaou != null) {
            zzr += zzbfa.zzq(2, this.zzaou.intValue());
        }
        return this.zzaov != null ? zzr + zzbfa.zzq(3, this.zzaov.intValue()) : zzr;
    }
}
