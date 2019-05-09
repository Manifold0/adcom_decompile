package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxr.zzb;
import com.google.android.gms.internal.ads.zzbbo.zza;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class zzaup {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static zzaxt zzb(zzaxr zzaxr) {
        zza zzbb = zzaxt.zzzu().zzbb(zzaxr.zzzk());
        for (zzb zzb : zzaxr.zzzl()) {
            zzbb.zzb((zzaxt.zzb) zzaxt.zzb.zzzw().zzeh(zzb.zzzp().zzyw()).zzb(zzb.zzzq()).zzb(zzb.zzzs()).zzbd(zzb.zzzr()).zzadi());
        }
        return (zzaxt) zzbb.zzadi();
    }

    public static void zzc(zzaxr zzaxr) throws GeneralSecurityException {
        if (zzaxr.zzzm() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        int zzzk = zzaxr.zzzk();
        int i = 1;
        int i2 = 0;
        for (zzb zzb : zzaxr.zzzl()) {
            if (!zzb.zzzo()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(zzb.zzzr())}));
            } else if (zzb.zzzs() == zzayd.UNKNOWN_PREFIX) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(zzb.zzzr())}));
            } else if (zzb.zzzq() == zzaxl.UNKNOWN_STATUS) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(zzb.zzzr())}));
            } else {
                if (zzb.zzzq() == zzaxl.ENABLED && zzb.zzzr() == zzzk) {
                    if (i2 != 0) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    i2 = 1;
                }
                i = zzb.zzzp().zzyy() != zzaxi.zzb.ASYMMETRIC_PUBLIC ? 0 : i;
            }
        }
        if (i2 == 0 && i == 0) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
