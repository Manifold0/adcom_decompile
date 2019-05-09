package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzavy extends zzbbo<zzavy, zza> implements zzbcw {
    private static volatile zzbdf<zzavy> zzakh;
    private static final zzavy zzdiw = new zzavy();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzawc zzdiv;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavy, zza> implements zzbcw {
        private zza() {
            super(zzavy.zzdiw);
        }

        public final zza zzan(int i) {
            zzadh();
            ((zzavy) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzb(zzawc zzawc) {
            zzadh();
            ((zzavy) this.zzdtx).zza(zzawc);
            return this;
        }

        public final zza zzp(zzbah zzbah) {
            zzadh();
            ((zzavy) this.zzdtx).zzk(zzbah);
            return this;
        }
    }

    static {
        zzbbo.zza(zzavy.class, zzdiw);
    }

    private zzavy() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzawc zzawc) {
        if (zzawc == null) {
            throw new NullPointerException();
        }
        this.zzdiv = zzawc;
    }

    private final void zzk(zzbah zzbah) {
        if (zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdip = zzbah;
    }

    public static zzavy zzo(zzbah zzbah) throws zzbbu {
        return (zzavy) zzbbo.zza(zzdiw, zzbah);
    }

    public static zza zzxf() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdiw.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavz.zzakf[i - 1]) {
            case 1:
                return new zzavy();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdiv", "zzdip"};
                return zza(zzdiw, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzdiw;
            case 5:
                zzbdf zzbdf = zzakh;
                if (zzbdf != null) {
                    return zzbdf;
                }
                Object obj3;
                synchronized (zzavy.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdiw);
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

    public final zzawc zzxe() {
        return this.zzdiv == null ? zzawc.zzxi() : this.zzdiv;
    }
}
