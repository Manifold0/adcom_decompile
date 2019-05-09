// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzauz implements zzaug<zzatz>
{
    private final zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        zzaxz zzal;
        try {
            zzal = zzaxz.zzal(zzbah);
            if (!(zzal instanceof zzaxz)) {
                throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", (Throwable)zzbbu);
        }
        final zzaxz zzaxz = zzal;
        zzazq.zzj(zzaxz.getVersion(), 0);
        final String zzaah = zzaxz.zzaae().zzaah();
        return new zzauy(zzaxz.zzaae().zzaai(), zzauj.zzdx(zzaah).zzdw(zzaah));
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzayb.zzam(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzayb)) {
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
        }
        return (zzbcu)zzaxz.zzaaf().zzb((zzayb)zzbcu).zzbf(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdla).zzadi();
    }
}
