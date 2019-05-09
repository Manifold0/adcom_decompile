package com.google.android.gms.internal.drive;

import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;

public final class zzhn extends zzir<zzhn> {
    public int versionCode;
    public String zzab;
    public long zzac;
    public int zzad;
    public long zzf;

    public zzhn() {
        this.versionCode = 1;
        this.zzab = "";
        this.zzac = -1;
        this.zzf = -1;
        this.zzad = -1;
        this.zzmw = null;
        this.zznf = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhn)) {
            return false;
        }
        zzhn zzhn = (zzhn) obj;
        if (this.versionCode != zzhn.versionCode) {
            return false;
        }
        if (this.zzab == null) {
            if (zzhn.zzab != null) {
                return false;
            }
        } else if (!this.zzab.equals(zzhn.zzab)) {
            return false;
        }
        return this.zzac != zzhn.zzac ? false : this.zzf != zzhn.zzf ? false : this.zzad != zzhn.zzad ? false : (this.zzmw == null || this.zzmw.isEmpty()) ? zzhn.zzmw == null || zzhn.zzmw.isEmpty() : this.zzmw.equals(zzhn.zzmw);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.zzab == null ? 0 : this.zzab.hashCode()) + ((((getClass().getName().hashCode() + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + this.versionCode) * 31)) * 31) + ((int) (this.zzac ^ (this.zzac >>> 32)))) * 31) + ((int) (this.zzf ^ (this.zzf >>> 32)))) * 31) + this.zzad) * 31;
        if (!(this.zzmw == null || this.zzmw.isEmpty())) {
            i = this.zzmw.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzix zza(zzio zzio) throws IOException {
        while (true) {
            int zzbd = zzio.zzbd();
            long zzbf;
            switch (zzbd) {
                case 0:
                    break;
                case 8:
                    this.versionCode = zzio.zzbe();
                    continue;
                case 18:
                    this.zzab = zzio.readString();
                    continue;
                case 24:
                    zzbf = zzio.zzbf();
                    this.zzac = (-(zzbf & 1)) ^ (zzbf >>> 1);
                    continue;
                case 32:
                    zzbf = zzio.zzbf();
                    this.zzf = (-(zzbf & 1)) ^ (zzbf >>> 1);
                    continue;
                case 40:
                    this.zzad = zzio.zzbe();
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
        zzip.zzb(1, this.versionCode);
        String str = this.zzab;
        zzip.zzd(2, 2);
        zzip.zzh(str);
        zzip.zza(3, this.zzac);
        zzip.zza(4, this.zzf);
        if (this.zzad != -1) {
            zzip.zzb(5, this.zzad);
        }
        super.zza(zzip);
    }

    protected final int zzaq() {
        int zzaq = (((super.zzaq() + zzip.zzc(1, this.versionCode)) + (zzip.zzi(this.zzab) + zzip.zzo(2))) + zzip.zzb(3, this.zzac)) + zzip.zzb(4, this.zzf);
        return this.zzad != -1 ? zzaq + zzip.zzc(5, this.zzad) : zzaq;
    }
}
