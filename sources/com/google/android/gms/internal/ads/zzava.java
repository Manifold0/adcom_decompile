package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class zzava implements zzaug<zzaue> {
    zzava() {
    }

    private final zzaue zzf(zzbah zzbah) throws GeneralSecurityException {
        try {
            zzaws zzx = zzaws.zzx(zzbah);
            if (zzx instanceof zzaws) {
                zzx = zzx;
                zzazq.zzj(zzx.getVersion(), 0);
                zzavh.zza(zzx.zzxz().zzxs());
                zzawq zzxs = zzx.zzxz().zzxs();
                zzaww zzxu = zzxs.zzxu();
                zzayv zza = zzavh.zza(zzxu.zzyh());
                byte[] toByteArray = zzx.zzwv().toByteArray();
                return new zzayo((ECPrivateKey) ((KeyFactory) zzayy.zzdof.zzek("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, toByteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
            }
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    public final int getVersion() {
        return 0;
    }

    public final /* synthetic */ Object zza(zzbah zzbah) throws GeneralSecurityException {
        return zzf(zzbah);
    }

    public final /* synthetic */ Object zza(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzaws) {
            zzaws zzaws = (zzaws) zzbcu;
            zzazq.zzj(zzaws.getVersion(), 0);
            zzavh.zza(zzaws.zzxz().zzxs());
            zzawq zzxs = zzaws.zzxz().zzxs();
            zzaww zzxu = zzxs.zzxu();
            zzayv zza = zzavh.zza(zzxu.zzyh());
            byte[] toByteArray = zzaws.zzwv().toByteArray();
            return new zzayo((ECPrivateKey) ((KeyFactory) zzayy.zzdof.zzek("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, toByteArray), zzayt.zza(zza))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
    }

    public final zzbcu zzb(zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzb(zzawo.zzw(zzbah));
        } catch (Throwable e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    public final zzbcu zzb(zzbcu zzbcu) throws GeneralSecurityException {
        if (zzbcu instanceof zzawo) {
            zzawo zzawo = (zzawo) zzbcu;
            zzavh.zza(zzawo.zzxs());
            KeyPair zza = zzayt.zza(zzayt.zza(zzavh.zza(zzawo.zzxs().zzxu().zzyh())));
            ECPublicKey eCPublicKey = (ECPublicKey) zza.getPublic();
            ECPrivateKey eCPrivateKey = (ECPrivateKey) zza.getPrivate();
            ECPoint w = eCPublicKey.getW();
            return zzaws.zzya().zzar(0).zzb((zzawu) zzawu.zzye().zzas(0).zzc(zzawo.zzxs()).zzac(zzbah.zzo(w.getAffineX().toByteArray())).zzad(zzbah.zzo(w.getAffineY().toByteArray())).zzadi()).zzy(zzbah.zzo(eCPrivateKey.getS().toByteArray())).zzadi();
        }
        throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
    }

    public final zzaxi zzc(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzaws) zzb(zzbah)).zzaav()).zzb(zzb.ASYMMETRIC_PRIVATE).zzadi();
    }
}
