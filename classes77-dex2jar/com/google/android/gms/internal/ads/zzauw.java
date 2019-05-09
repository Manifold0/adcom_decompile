// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzauw implements zzaug<zzatz>
{
    private final zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        zzawi zzu;
        try {
            zzu = zzawi.zzu(zzbah);
            if (!(zzu instanceof zzawi)) {
                throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", (Throwable)zzbbu);
        }
        final zzawi zzawi = zzu;
        zzazq.zzj(zzawi.getVersion(), 0);
        if (zzawi.zzwv().size() != 32) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        }
        return new zzaym(zzawi.zzwv().toByteArray());
    }
    
    private static zzawi zzwl() throws GeneralSecurityException {
        return (zzawi)zzawi.zzxn().zzap(0).zzv(zzbah.zzo(zzazl.zzbh(32))).zzadi();
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        return (zzbcu)zzwl();
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        return (zzbcu)zzwl();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").zzai(((zzazy)zzwl()).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
