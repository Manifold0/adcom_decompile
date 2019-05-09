package com.google.android.gms.internal.drive;

import java.io.IOException;

public abstract class zzir<M extends zzir<M>> extends zzix {
    protected zzit zzmw;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzir zzir = (zzir) super.zzbi();
        zziv.zza(this, zzir);
        return zzir;
    }

    public void zza(zzip zzip) throws IOException {
        if (this.zzmw != null) {
            for (int i = 0; i < this.zzmw.size(); i++) {
                this.zzmw.zzs(i).zza(zzip);
            }
        }
    }

    protected final boolean zza(zzio zzio, int i) throws IOException {
        int position = zzio.getPosition();
        if (!zzio.zzk(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zziz zziz = new zziz(i, zzio.zza(position, zzio.getPosition() - position));
        zziu zziu = null;
        if (this.zzmw == null) {
            this.zzmw = new zzit();
        } else {
            zziu = this.zzmw.zzr(i2);
        }
        if (zziu == null) {
            zziu = new zziu();
            this.zzmw.zza(i2, zziu);
        }
        zziu.zza(zziz);
        return true;
    }

    protected int zzaq() {
        int i = 0;
        if (this.zzmw == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzmw.size()) {
            i2 += this.zzmw.zzs(i).zzaq();
            i++;
        }
        return i2;
    }

    public final /* synthetic */ zzix zzbi() throws CloneNotSupportedException {
        return (zzir) clone();
    }
}
