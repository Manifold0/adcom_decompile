// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.spec.ECPoint;
import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.ECPrivateKeySpec;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.GeneralSecurityException;

final class zzava implements zzaug<zzaue>
{
    private final zzaue zzf(final zzbah zzbah) throws GeneralSecurityException {
        zzaws zzx;
        try {
            zzx = zzaws.zzx(zzbah);
            if (!(zzx instanceof zzaws)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", (Throwable)zzbbu);
        }
        final zzaws zzaws = zzx;
        zzazq.zzj(zzaws.getVersion(), 0);
        zzavh.zza(zzaws.zzxz().zzxs());
        final zzawq zzxs = zzaws.zzxz().zzxs();
        final zzaww zzxu = zzxs.zzxu();
        return new zzayo((ECPrivateKey)zzayy.zzdof.zzek("EC").generatePrivate(new ECPrivateKeySpec(new BigInteger(1, zzaws.zzwv().toByteArray()), zzayt.zza(zzavh.zza(zzxu.zzyh())))), zzxu.zzyj().toByteArray(), zzavh.zza(zzxu.zzyi()), zzavh.zza(zzxs.zzxw()), new zzavj(zzxs.zzxv().zzxp()));
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzawo.zzw(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzawo)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
        }
        final zzawo zzawo = (zzawo)zzbcu;
        zzavh.zza(zzawo.zzxs());
        final KeyPair zza = zzayt.zza(zzayt.zza(zzavh.zza(zzawo.zzxs().zzxu().zzyh())));
        final ECPublicKey ecPublicKey = (ECPublicKey)zza.getPublic();
        final ECPrivateKey ecPrivateKey = (ECPrivateKey)zza.getPrivate();
        final ECPoint w = ecPublicKey.getW();
        return (zzbcu)zzaws.zzya().zzar(0).zzb((zzawu)zzawu.zzye().zzas(0).zzc(zzawo.zzxs()).zzac(zzbah.zzo(w.getAffineX().toByteArray())).zzad(zzbah.zzo(w.getAffineY().toByteArray())).zzadi()).zzy(zzbah.zzo(ecPrivateKey.getS().toByteArray())).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdky).zzadi();
    }
}
