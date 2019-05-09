package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim> {
    private Integer zzamf;
    private Integer zzanx;

    public zzim() {
        this.zzamf = null;
        this.zzanx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzim zzo(zzbez zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = zzbez.zzabk();
            int zzacc;
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    position = zzbez.getPosition();
                    try {
                        zzacc = zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum NetworkType");
                        }
                        this.zzamf = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 16:
                    zzacc = zzbez.getPosition();
                    try {
                        position = zzbez.zzacc();
                        if ((position < 0 || position > 2) && (position < 4 || position > 4)) {
                            throw new IllegalArgumentException(position + " is not a valid enum CellularNetworkType");
                        }
                        this.zzanx = Integer.valueOf(position);
                        continue;
                    } catch (IllegalArgumentException e2) {
                        zzbez.zzdc(zzacc);
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
        return zzo(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.zzanx != null) {
            zzbfa.zzm(2, this.zzanx.intValue());
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        return this.zzanx != null ? zzr + zzbfa.zzq(2, this.zzanx.intValue()) : zzr;
    }
}
