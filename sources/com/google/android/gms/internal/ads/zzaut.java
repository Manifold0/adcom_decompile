package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzaut implements zzaug<zzazi> {
    zzaut() {
    }

    private static void zza(zzavw zzavw) throws GeneralSecurityException {
        if (zzavw.zzxb() < 12 || zzavw.zzxb() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    private final zzayh zze(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzavs zzl = zzavs.zzl(zzbah);
            if (zzl instanceof zzavs) {
                zzl = zzl;
                zzazq.zzj(zzl.getVersion(), 0);
                zzazq.zzbi(zzl.zzwv().size());
                zza(zzl.zzwu());
                return new zzayh(zzl.zzwv().toByteArray(), zzl.zzwu().zzxb());
            }
            throw new GeneralSecurityException("expected AesCtrKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah zzbah) throws GeneralSecurityException {
        return zze(zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavs) {
            zzavs zzavs = (zzavs) zzbcu;
            zzazq.zzj(zzavs.getVersion(), 0);
            zzazq.zzbi(zzavs.zzwv().size());
            zza(zzavs.zzwu());
            return new zzayh(zzavs.zzwv().toByteArray(), zzavs.zzwu().zzxb());
        }
        throw new GeneralSecurityException("expected AesCtrKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzavu.zzn(zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzavu) {
            zzavu zzavu = (zzavu) zzbcu;
            zzazq.zzbi(zzavu.getKeySize());
            zza(zzavu.zzwu());
            return zzavs.zzww().zzc(zzavu.zzwu()).zzm(zzbah.zzo(zzazl.zzbh(zzavu.getKeySize()))).zzam(0).zzadi();
        }
        throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzavs) zzb(zzbah)).zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
