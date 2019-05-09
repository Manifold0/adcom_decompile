package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.io.IOException;
import java.util.Arrays;

public final class zzbef {
    private static final zzbef zzdyx = new zzbef(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzdpi;
    private int zzdtu;
    private Object[] zzdwh;
    private int[] zzdyy;

    private zzbef() {
        this(0, new int[8], new Object[8], true);
    }

    private zzbef(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzdtu = -1;
        this.count = i;
        this.zzdyy = iArr;
        this.zzdwh = objArr;
        this.zzdpi = z;
    }

    static zzbef zza(zzbef zzbef, zzbef zzbef2) {
        int i = zzbef.count + zzbef2.count;
        Object copyOf = Arrays.copyOf(zzbef.zzdyy, i);
        System.arraycopy(zzbef2.zzdyy, 0, copyOf, zzbef.count, zzbef2.count);
        Object copyOf2 = Arrays.copyOf(zzbef.zzdwh, i);
        System.arraycopy(zzbef2.zzdwh, 0, copyOf2, zzbef.count, zzbef2.count);
        return new zzbef(i, copyOf, copyOf2, true);
    }

    public static zzbef zzagc() {
        return zzdyx;
    }

    static zzbef zzagd() {
        return new zzbef();
    }

    private static void zzb(int i, Object obj, zzbey zzbey) throws IOException {
        int i2 = i >>> 3;
        switch (i & 7) {
            case 0:
                zzbey.zzi(i2, ((Long) obj).longValue());
                return;
            case 1:
                zzbey.zzc(i2, ((Long) obj).longValue());
                return;
            case 2:
                zzbey.zza(i2, (zzbah) obj);
                return;
            case 3:
                if (zzbey.zzacn() == zze.zzdul) {
                    zzbey.zzcm(i2);
                    ((zzbef) obj).zzb(zzbey);
                    zzbey.zzcn(i2);
                    return;
                }
                zzbey.zzcn(i2);
                ((zzbef) obj).zzb(zzbey);
                zzbey.zzcm(i2);
                return;
            case 5:
                zzbey.zzp(i2, ((Integer) obj).intValue());
                return;
            default:
                throw new RuntimeException(zzbbu.zzadq());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzbef)) {
            return false;
        }
        zzbef zzbef = (zzbef) obj;
        if (this.count == zzbef.count) {
            int i;
            boolean z;
            int[] iArr = this.zzdyy;
            int[] iArr2 = zzbef.zzdyy;
            int i2 = this.count;
            for (i = 0; i < i2; i++) {
                if (iArr[i] != iArr2[i]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                Object[] objArr = this.zzdwh;
                Object[] objArr2 = zzbef.zzdwh;
                i2 = this.count;
                for (i = 0; i < i2; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = 17;
        int i3 = 0;
        int i4 = (this.count + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        int[] iArr = this.zzdyy;
        int i5 = 17;
        for (i = 0; i < this.count; i++) {
            i5 = (i5 * 31) + iArr[i];
        }
        i = (i4 + i5) * 31;
        Object[] objArr = this.zzdwh;
        while (i3 < this.count) {
            i2 = (i2 * 31) + objArr[i3].hashCode();
            i3++;
        }
        return i + i2;
    }

    final void zza(zzbey zzbey) throws IOException {
        int i;
        if (zzbey.zzacn() == zze.zzdum) {
            for (i = this.count - 1; i >= 0; i--) {
                zzbey.zza(this.zzdyy[i] >>> 3, this.zzdwh[i]);
            }
            return;
        }
        for (i = 0; i < this.count; i++) {
            zzbey.zza(this.zzdyy[i] >>> 3, this.zzdwh[i]);
        }
    }

    final void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzbcx.zza(stringBuilder, i, String.valueOf(this.zzdyy[i2] >>> 3), this.zzdwh[i2]);
        }
    }

    public final void zzaaz() {
        this.zzdpi = false;
    }

    public final int zzacw() {
        int i = this.zzdtu;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzdyy[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        i += zzbav.zze(i4, ((Long) this.zzdwh[i2]).longValue());
                        break;
                    case 1:
                        i += zzbav.zzg(i4, ((Long) this.zzdwh[i2]).longValue());
                        break;
                    case 2:
                        i += zzbav.zzc(i4, (zzbah) this.zzdwh[i2]);
                        break;
                    case 3:
                        i += ((zzbef) this.zzdwh[i2]).zzacw() + (zzbav.zzcd(i4) << 1);
                        break;
                    case 5:
                        i += zzbav.zzt(i4, ((Integer) this.zzdwh[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzbbu.zzadq());
                }
            }
            this.zzdtu = i;
        }
        return i;
    }

    public final int zzage() {
        int i = this.zzdtu;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                i += zzbav.zzd(this.zzdyy[i2] >>> 3, (zzbah) this.zzdwh[i2]);
            }
            this.zzdtu = i;
        }
        return i;
    }

    final void zzb(int i, Object obj) {
        if (this.zzdpi) {
            if (this.count == this.zzdyy.length) {
                int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
                this.zzdyy = Arrays.copyOf(this.zzdyy, i2);
                this.zzdwh = Arrays.copyOf(this.zzdwh, i2);
            }
            this.zzdyy[this.count] = i;
            this.zzdwh[this.count] = obj;
            this.count++;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public final void zzb(zzbey zzbey) throws IOException {
        if (this.count != 0) {
            int i;
            if (zzbey.zzacn() == zze.zzdul) {
                for (i = 0; i < this.count; i++) {
                    zzb(this.zzdyy[i], this.zzdwh[i], zzbey);
                }
                return;
            }
            for (i = this.count - 1; i >= 0; i--) {
                zzb(this.zzdyy[i], this.zzdwh[i], zzbey);
            }
        }
    }
}
