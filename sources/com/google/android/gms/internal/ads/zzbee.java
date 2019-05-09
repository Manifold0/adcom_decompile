package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzbee<T, B> {
    zzbee() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzbah zzbah);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzbey zzbey) throws IOException;

    abstract boolean zza(zzbdl zzbdl);

    final boolean zza(B b, zzbdl zzbdl) throws IOException {
        int tag = zzbdl.getTag();
        int i = tag >>> 3;
        switch (tag & 7) {
            case 0:
                zza((Object) b, i, zzbdl.zzabm());
                return true;
            case 1:
                zzb(b, i, zzbdl.zzabo());
                return true;
            case 2:
                zza((Object) b, i, zzbdl.zzabs());
                return true;
            case 3:
                Object zzagb = zzagb();
                int i2 = (i << 3) | 4;
                while (zzbdl.zzaci() != Integer.MAX_VALUE) {
                    if (!zza(zzagb, zzbdl)) {
                        if (i2 == zzbdl.getTag()) {
                            throw zzbbu.zzadp();
                        }
                        zza((Object) b, i, zzv(zzagb));
                        return true;
                    }
                }
                if (i2 == zzbdl.getTag()) {
                    zza((Object) b, i, zzv(zzagb));
                    return true;
                }
                throw zzbbu.zzadp();
            case 4:
                return false;
            case 5:
                zzc(b, i, zzbdl.zzabp());
                return true;
            default:
                throw zzbbu.zzadq();
        }
    }

    abstract T zzac(Object obj);

    abstract B zzad(Object obj);

    abstract int zzae(T t);

    abstract B zzagb();

    abstract void zzb(B b, int i, long j);

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzbey zzbey) throws IOException;

    abstract void zze(Object obj, T t);

    abstract void zzf(Object obj, B b);

    abstract T zzg(T t, T t2);

    abstract void zzo(Object obj);

    abstract T zzv(B b);

    abstract int zzy(T t);
}
