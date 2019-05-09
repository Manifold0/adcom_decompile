package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfq extends zzbfc<zzbfq> {
    private byte[] zzede;
    private byte[] zzedf;
    private byte[] zzedg;

    public zzbfq() {
        this.zzede = null;
        this.zzedf = null;
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
                case 10:
                    this.zzede = zzbez.readBytes();
                    continue;
                case 18:
                    this.zzedf = zzbez.readBytes();
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
        if (this.zzede != null) {
            zzbfa.zza(1, this.zzede);
        }
        if (this.zzedf != null) {
            zzbfa.zza(2, this.zzedf);
        }
        if (this.zzedg != null) {
            zzbfa.zza(3, this.zzedg);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzede != null) {
            zzr += zzbfa.zzb(1, this.zzede);
        }
        if (this.zzedf != null) {
            zzr += zzbfa.zzb(2, this.zzedf);
        }
        return this.zzedg != null ? zzr + zzbfa.zzb(3, this.zzedg) : zzr;
    }
}
