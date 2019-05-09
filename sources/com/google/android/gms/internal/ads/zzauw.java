package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class zzauw implements zzaug<zzatz> {
    zzauw() {
    }

    private final zzatz zzd(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzawi zzu = zzawi.zzu(zzbah);
            if (zzu instanceof zzawi) {
                zzu = zzu;
                zzazq.zzj(zzu.getVersion(), 0);
                if (zzu.zzwv().size() == 32) {
                    return new zzaym(zzu.zzwv().toByteArray());
                }
                throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
            }
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    private static zzawi zzwl() throws GeneralSecurityException {
        return (zzawi) zzawi.zzxn().zzap(0).zzv(zzbah.zzo(zzazl.zzbh(32))).zzadi();
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah zzbah) throws GeneralSecurityException {
        return zzd(zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawi) {
            zzawi zzawi = (zzawi) zzbcu;
            zzazq.zzj(zzawi.getVersion(), 0);
            if (zzawi.zzwv().size() == 32) {
                return new zzaym(zzawi.zzwv().toByteArray());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
        throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        return zzwl();
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(zzwl().zzaav()).zzb(zzb.SYMMETRIC).zzadi();
    }
}
