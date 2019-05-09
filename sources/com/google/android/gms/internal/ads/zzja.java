package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzja extends zzbfc<zzja> {
    private Integer zzanu;
    private Integer zzape;
    private Integer zzapf;
    private zziw zzapn;
    private Integer zzapr;
    private Long zzaps;

    public zzja() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzape = null;
        this.zzapf = null;
        this.zzapr = null;
        this.zzaps = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzja zzx(zzbez zzbez) throws IOException {
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
                case 24:
                    this.zzape = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 32:
                    this.zzapf = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 40:
                    this.zzapr = Integer.valueOf(zzbez.zzacc());
                    continue;
                case 48:
                    this.zzaps = Long.valueOf(zzbez.zzacd());
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
        return zzx(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanu != null) {
            zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzbfa.zza(2, this.zzapn);
        }
        if (this.zzape != null) {
            zzbfa.zzm(3, this.zzape.intValue());
        }
        if (this.zzapf != null) {
            zzbfa.zzm(4, this.zzapf.intValue());
        }
        if (this.zzapr != null) {
            zzbfa.zzm(5, this.zzapr.intValue());
        }
        if (this.zzaps != null) {
            zzbfa.zza(6, this.zzaps.longValue());
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
        if (this.zzape != null) {
            zzr += zzbfa.zzq(3, this.zzape.intValue());
        }
        if (this.zzapf != null) {
            zzr += zzbfa.zzq(4, this.zzapf.intValue());
        }
        if (this.zzapr != null) {
            zzr += zzbfa.zzq(5, this.zzapr.intValue());
        }
        return this.zzaps != null ? zzr + zzbfa.zze(6, this.zzaps.longValue()) : zzr;
    }
}
