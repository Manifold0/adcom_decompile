package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjb extends zzbfc<zzjb> {
    private Integer zzanu;
    private zziw zzapn;
    private zzis zzapo;

    public zzjb() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzapo = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzjb zzy(zzbez zzbez) throws IOException {
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
                case 26:
                    if (this.zzapo == null) {
                        this.zzapo = new zzis();
                    }
                    zzbez.zza(this.zzapo);
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
        return zzy(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzbfa.zza(2, this.zzapn);
        }
        if (this.zzapo != null) {
            zzbfa.zza(3, this.zzapo);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzr += zzbfa.zzb(2, this.zzapn);
        }
        return this.zzapo != null ? zzr + zzbfa.zzb(3, this.zzapo) : zzr;
    }
}
