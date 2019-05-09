package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzawu extends zzbbo<zzawu, zza> implements zzbcw {
    private static volatile zzbdf<zzawu> zzakh;
    private static final zzawu zzdjt = new zzawu();
    private int zzdih;
    private zzawq zzdjj;
    private zzbah zzdjr = zzbah.zzdpq;
    private zzbah zzdjs = zzbah.zzdpq;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawu, zza> implements zzbcw {
        private zza() {
            super(zzawu.zzdjt);
        }

        public final zza zzac(zzbah zzbah) {
            zzadh();
            ((zzawu) this.zzdtx).zzz(zzbah);
            return this;
        }

        public final zza zzad(zzbah zzbah) {
            zzadh();
            ((zzawu) this.zzdtx).zzaa(zzbah);
            return this;
        }

        public final zza zzas(int i) {
            zzadh();
            ((zzawu) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzc(zzawq zzawq) {
            zzadh();
            ((zzawu) this.zzdtx).zzb(zzawq);
            return this;
        }
    }

    static {
        zzbbo.zza(zzawu.class, zzdjt);
    }

    private zzawu() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zzaa(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdjs = zzbah;
    }

    public static zzawu zzab(zzbah zzbah) throws zzbbu {
        return (zzawu) zzbbo.zza(zzdjt, zzbah);
    }

    private final void zzb(zzawq zzawq) {
        if (zzawq == null) {
            throw new NullPointerException();
        }
        this.zzdjj = zzawq;
    }

    public static zza zzye() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdjt.zza(zze.zzdue, null, null));
    }

    public static zzawu zzyf() {
        return zzdjt;
    }

    private final void zzz(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdjr = zzbah;
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawv.zzakf[i - 1]) {
            case 1:
                return new zzawu();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdjj", "zzdjr", "zzdjs"};
                return zza(zzdjt, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n\u0004\n", objArr);
            case 4:
                return zzdjt;
            case 5:
                zzbdf zzbdf = zzakh;
                if (zzbdf != null) {
                    return zzbdf;
                }
                Object obj3;
                synchronized (zzawu.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjt);
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

    public final zzawq zzxs() {
        return this.zzdjj == null ? zzawq.zzxx() : this.zzdjj;
    }

    public final zzbah zzyc() {
        return this.zzdjr;
    }

    public final zzbah zzyd() {
        return this.zzdjs;
    }
}
