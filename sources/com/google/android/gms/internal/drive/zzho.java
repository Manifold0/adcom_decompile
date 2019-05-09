package com.google.android.gms.internal.drive;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;

public final class zzho extends zzir<zzho> {
    public long zzac;
    public long zzf;

    public zzho() {
        this.zzac = -1;
        this.zzf = -1;
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzho)) {
            return false;
        }
        zzho zzho = (zzho) obj;
        return this.zzac != zzho.zzac ? false : this.zzf != zzho.zzf ? false : (this.zzmw == null || this.zzmw.isEmpty()) ? zzho.zzmw == null || zzho.zzmw.isEmpty() : this.zzmw.equals(zzho.zzmw);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + ((int) (this.zzac ^ (this.zzac >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31;
        int hashCode2 = (this.zzmw == null || this.zzmw.isEmpty()) ? 0 : this.zzmw.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzix zza(zzio zzio) throws IOException {
        while (true) {
            int zzbd = zzio.zzbd();
            long zzbf;
            switch (zzbd) {
                case 0:
                    break;
                case 8:
                    zzbf = zzio.zzbf();
                    this.zzac = (-(zzbf & 1)) ^ (zzbf >>> 1);
                    continue;
                case 16:
                    zzbf = zzio.zzbf();
                    this.zzf = (-(zzbf & 1)) ^ (zzbf >>> 1);
                    continue;
                default:
                    if (!super.zza(zzio, zzbd)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzip zzip) throws IOException {
        zzip.zza(1, this.zzac);
        zzip.zza(2, this.zzf);
        super.zza(zzip);
    }

    protected final int zzaq() {
        return (super.zzaq() + zzip.zzb(1, this.zzac)) + zzip.zzb(2, this.zzf);
    }
}
