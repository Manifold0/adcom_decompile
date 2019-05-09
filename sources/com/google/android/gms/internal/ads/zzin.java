package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzin extends zzbfc<zzin> {
    private Integer zzany;
    private zzis zzanz;

    public zzin() {
        this.zzany = null;
        this.zzanz = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzin zzp(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        this.zzany = Integer.valueOf(zzia.zzd(zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzanz == null) {
                        this.zzanz = new zzis();
                    }
                    zzbez.zza(this.zzanz);
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
        return zzp(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzany != null) {
            zzbfa.zzm(1, this.zzany.intValue());
        }
        if (this.zzanz != null) {
            zzbfa.zza(2, this.zzanz);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzany != null) {
            zzr += zzbfa.zzq(1, this.zzany.intValue());
        }
        return this.zzanz != null ? zzr + zzbfa.zzb(2, this.zzanz) : zzr;
    }
}
