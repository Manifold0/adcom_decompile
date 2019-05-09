package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfo extends zzbfc<zzbfo> {
    private static volatile zzbfo[] zzecw;
    public byte[] zzecx;
    public byte[] zzecy;

    public zzbfo() {
        this.zzecx = null;
        this.zzecy = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzbfo[] zzagt() {
        if (zzecw == null) {
            synchronized (zzbfg.zzebs) {
                if (zzecw == null) {
                    zzecw = new zzbfo[0];
                }
            }
        }
        return zzecw;
    }

    public final /* synthetic */ zzbfi zza(zzbez zzbez) throws IOException {
        while (true) {
            int zzabk = zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzecx = zzbez.readBytes();
                    continue;
                case 18:
                    this.zzecy = zzbez.readBytes();
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
        zzbfa.zza(1, this.zzecx);
        if (this.zzecy != null) {
            zzbfa.zza(2, this.zzecy);
        }
        super.zza(zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr() + zzbfa.zzb(1, this.zzecx);
        return this.zzecy != null ? zzr + zzbfa.zzb(2, this.zzecy) : zzr;
    }
}
