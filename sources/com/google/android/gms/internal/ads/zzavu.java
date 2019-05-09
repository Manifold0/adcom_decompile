package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzavu extends zzbbo<zzavu, zza> implements zzbcw {
    private static volatile zzbdf<zzavu> zzakh;
    private static final zzavu zzdis = new zzavu();
    private zzavw zzdio;
    private int zzdir;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavu, zza> implements zzbcw {
        private zza() {
            super(zzavu.zzdis);
        }
    }

    static {
        zzbbo.zza(zzavu.class, zzdis);
    }

    private zzavu() {
    }

    public static zzavu zzn(zzbah zzbah) throws zzbbu {
        return (zzavu) zzbbo.zza(zzdis, zzbah);
    }

    public static zzavu zzwz() {
        return zzdis;
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavv.zzakf[i - 1]) {
            case 1:
                return new zzavu();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdio", "zzdir"};
                return zza(zzdis, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzdis;
            case 5:
                zzbdf zzbdf = zzakh;
                if (zzbdf != null) {
                    return zzbdf;
                }
                Object obj3;
                synchronized (zzavu.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdis);
                        zzakh = obj3;
                    }
                }
                return obj3;
            case 6:
                return Byte.valueOf((byte) 1);
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final zzavw zzwu() {
        return this.zzdio == null ? zzavw.zzxc() : this.zzdio;
    }
}
