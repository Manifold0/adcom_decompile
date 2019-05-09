package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbfc<M extends zzbfc<M>> extends zzbfi {
    protected zzbfe zzebk;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzbfc zzbfc = (zzbfc) super.zzago();
        zzbfg.zza(this, zzbfc);
        return zzbfc;
    }

    public void zza(zzbfa zzbfa) throws IOException {
        if (this.zzebk != null) {
            for (int i = 0; i < this.zzebk.size(); i++) {
                this.zzebk.zzdg(i).zza(zzbfa);
            }
        }
    }

    protected final boolean zza(zzbez zzbez, int i) throws IOException {
        int position = zzbez.getPosition();
        if (!zzbez.zzbq(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzbfk zzbfk = new zzbfk(i, zzbez.zzab(position, zzbez.getPosition() - position));
        zzbff zzbff = null;
        if (this.zzebk == null) {
            this.zzebk = new zzbfe();
        } else {
            zzbff = this.zzebk.zzdf(i2);
        }
        if (zzbff == null) {
            zzbff = new zzbff();
            this.zzebk.zza(i2, zzbff);
        }
        zzbff.zza(zzbfk);
        return true;
    }

    public final /* synthetic */ zzbfi zzago() throws CloneNotSupportedException {
        return (zzbfc) clone();
    }

    protected int zzr() {
        int i = 0;
        if (this.zzebk == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzebk.size()) {
            i2 += this.zzebk.zzdg(i).zzr();
            i++;
        }
        return i2;
    }
}
