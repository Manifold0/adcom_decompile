package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbft extends zzbfc<zzbft> {
    public String mimeType;
    public Integer zzamf;
    public byte[] zzedl;

    public zzbft() {
        this.zzamf = null;
        this.mimeType = null;
        this.zzedl = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzbft zzab(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = zzbez.getPosition();
                    try {
                        int zzabn = zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 1) {
                            throw new IllegalArgumentException(zzabn + " is not a valid enum Type");
                        }
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e) {
                        zzbez.zzdc(position);
                        zza(zzbez, zzabk);
                        break;
                    }
                case 18:
                    this.mimeType = zzbez.readString();
                    continue;
                case 26:
                    this.zzedl = zzbez.readBytes();
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
        return zzab(zzbez);
    }

    public final void zza(zzbfa zzbfa) throws IOException {
        if (this.zzamf != null) {
            zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            zzbfa.zzf(2, this.mimeType);
        }
        if (this.zzedl != null) {
            zzbfa.zza(3, this.zzedl);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            zzr += zzbfa.zzg(2, this.mimeType);
        }
        return this.zzedl != null ? zzr + zzbfa.zzb(3, this.zzedl) : zzr;
    }
}
