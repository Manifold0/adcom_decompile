package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfs extends zzbfc<zzbfs> {
    private byte[] zzedg;
    private Integer zzedj;
    private byte[] zzedk;

    public zzbfs() {
        this.zzedj = null;
        this.zzedk = null;
        this.zzedg = null;
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
                    this.zzedj = Integer.valueOf(zzbez.zzabn());
                    continue;
                case 18:
                    this.zzedk = zzbez.readBytes();
                    continue;
                case 26:
                    this.zzedg = zzbez.readBytes();
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
        if (this.zzedj != null) {
            zzbfa.zzm(1, this.zzedj.intValue());
        }
        if (this.zzedk != null) {
            zzbfa.zza(2, this.zzedk);
        }
        if (this.zzedg != null) {
            zzbfa.zza(3, this.zzedg);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzedj != null) {
            zzr += zzbfa.zzq(1, this.zzedj.intValue());
        }
        if (this.zzedk != null) {
            zzr += zzbfa.zzb(2, this.zzedk);
        }
        return this.zzedg != null ? zzr + zzbfa.zzb(3, this.zzedg) : zzr;
    }
}
