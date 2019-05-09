package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaws extends zzbbo<zzaws, zza> implements zzbcw {
    private static volatile zzbdf<zzaws> zzakh;
    private static final zzaws zzdjq = new zzaws();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzawu zzdjp;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaws, zza> implements zzbcw {
        private zza() {
            super(zzaws.zzdjq);
        }

        public final zza zzar(int i) {
            zzadh();
            ((zzaws) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzb(zzawu zzawu) {
            zzadh();
            ((zzaws) this.zzdtx).zza(zzawu);
            return this;
        }

        public final zza zzy(zzbah zzbah) {
            zzadh();
            ((zzaws) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzbbo.zza(zzaws.class, zzdjq);
    }

    private zzaws() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzawu zzawu) {
        if (zzawu == null) {
            throw new NullPointerException();
        }
        this.zzdjp = zzawu;
    }

    private final void zzk(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzbah;
    }

    public static zzaws zzx(zzbah zzbah) throws zzbbu {
        return (zzaws) zzbbo.zza(zzdjq, zzbah);
    }

    public static zza zzya() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdjq.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawt.zzakf[i - 1]) {
            case 1:
                return new zzaws();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdjp", "zzdip"};
                return zza(zzdjq, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzdjq;
            case 5:
                zzbdf zzbdf = zzakh;
                if (zzbdf != null) {
                    return zzbdf;
                }
                Object obj3;
                synchronized (zzaws.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjq);
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

    public final zzbah zzwv() {
        return this.zzdip;
    }

    public final zzawu zzxz() {
        return this.zzdjp == null ? zzawu.zzyf() : this.zzdjp;
    }
}
