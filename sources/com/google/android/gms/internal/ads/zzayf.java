package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;
import java.util.List;

public final class zzayf extends zzbbo<zzayf, zza> implements zzbcw {
    private static volatile zzbdf<zzayf> zzakh;
    private static final zzayf zzdmt = new zzayf();
    private int zzdlq;
    private String zzdmr = "";
    private zzbbt<zzaxp> zzdms = zzadd();

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzayf, zza> implements zzbcw {
        private zza() {
            super(zzayf.zzdmt);
        }

        public final zza zzb(zzaxp zzaxp) {
            zzadh();
            ((zzayf) this.zzdtx).zza(zzaxp);
            return this;
        }

        public final zza zzej(String str) {
            zzadh();
            ((zzayf) this.zzdtx).zzei(str);
            return this;
        }
    }

    static {
        zzbbo.zza(zzayf.class, zzdmt);
    }

    private zzayf() {
    }

    private final void zza(zzaxp zzaxp) {
        if (zzaxp == null) {
            throw new NullPointerException();
        }
        if (!this.zzdms.zzaay()) {
            zzbbt zzbbt = this.zzdms;
            int size = zzbbt.size();
            this.zzdms = zzbbt.zzbm(size == 0 ? 10 : size << 1);
        }
        this.zzdms.add(zzaxp);
    }

    public static zza zzaam() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdmt.zza(zze.zzdue, null, null));
    }

    private final void zzei(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdmr = str;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzayg.zzakf[i - 1]) {
            case 1:
                return new zzayf();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdlq", "zzdmr", "zzdms", zzaxp.class};
                return zza(zzdmt, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", objArr);
            case 4:
                return zzdmt;
            case 5:
                zzbdf zzbdf = zzakh;
                if (zzbdf != null) {
                    return zzbdf;
                }
                Object obj3;
                synchronized (zzayf.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdmt);
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

    public final List<zzaxp> zzaal() {
        return this.zzdms;
    }
}
