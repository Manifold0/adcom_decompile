// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzauv implements zzaug<zzatz>
{
    private final zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        zzawe zzr;
        try {
            zzr = zzawe.zzr(zzbah);
            if (!(zzr instanceof zzawe)) {
                throw new GeneralSecurityException("expected AesGcmKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
        final zzawe zzawe = zzr;
        zzazq.zzj(zzawe.getVersion(), 0);
        zzazq.zzbi(zzawe.zzwv().size());
        return new zzayj(zzawe.zzwv().toByteArray());
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzawg.zzt(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzawg)) {
            throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
        }
        final zzawg zzawg = (zzawg)zzbcu;
        zzazq.zzbi(zzawg.getKeySize());
        return (zzbcu)zzawe.zzxk().zzs(zzbah.zzo(zzazl.zzbh(zzawg.getKeySize()))).zzao(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesGcmKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
