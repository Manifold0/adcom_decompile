// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzaux implements zzaug<zzatz>
{
    private static zzatz zzc(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzaxv)) {
            throw new GeneralSecurityException("expected KmsAeadKey proto");
        }
        final zzaxv zzaxv = (zzaxv)zzbcu;
        zzazq.zzj(zzaxv.getVersion(), 0);
        final String zzaab = zzaxv.zzzy().zzaab();
        return zzauj.zzdx(zzaab).zzdw(zzaab);
    }
    
    private static zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return zzc((zzbcu)zzaxv.zzaj(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzaxx.zzak(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzaxx)) {
            throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
        }
        return (zzbcu)zzaxv.zzzz().zzb((zzaxx)zzbcu).zzbe(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.KmsAeadKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdla).zzadi();
    }
}
