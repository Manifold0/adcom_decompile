package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzir extends zzbfc<zzir> {
    private static volatile zzir[] zzaop;
    private String zzanq;
    private Integer zzanr;
    private zzis zzant;

    public zzir() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzir[] zzhs() {
        if (zzaop == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaop == null) {
                    zzaop = new zzir[0];
                }
            }
        }
        return zzaop;
    }

    private final zzir zzr(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzanq = zzbez.readString();
                    continue;
                case 16:
                    int position = zzbez.getPosition();
                    try {
                        this.zzanr = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 26:
                    if (this.zzant == null) {
                        this.zzant = new zzis();
                    }
                    zzbez.zza(this.zzant);
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
        return zzr(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzanq != null) {
            zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzbfa.zzm(2, this.zzanr.intValue());
        }
        if (this.zzant != null) {
            zzbfa.zza(3, this.zzant);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanq != null) {
            zzr += zzbfa.zzg(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzr += zzbfa.zzq(2, this.zzanr.intValue());
        }
        return this.zzant != null ? zzr + zzbfa.zzb(3, this.zzant) : zzr;
    }
}
