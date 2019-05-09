// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzaut implements zzaug<zzazi>
{
    private static void zza(final zzavw zzavw) throws GeneralSecurityException {
        if (zzavw.zzxb() < 12 || zzavw.zzxb() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }
    
    private final zzayh zze(final zzbah zzbah) throws GeneralSecurityException {
        zzavs zzl;
        try {
            zzl = zzavs.zzl(zzbah);
            if (!(zzl instanceof zzavs)) {
                throw new GeneralSecurityException("expected AesCtrKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", (Throwable)zzbbu);
        }
        final zzavs zzavs = zzl;
        zzazq.zzj(zzavs.getVersion(), 0);
        zzazq.zzbi(zzavs.zzwv().size());
        zza(zzavs.zzwu());
        return new zzayh(zzavs.zzwv().toByteArray(), zzavs.zzwu().zzxb());
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzavu.zzn(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzavu)) {
            throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
        }
        final zzavu zzavu = (zzavu)zzbcu;
        zzazq.zzbi(zzavu.getKeySize());
        zza(zzavu.zzwu());
        return (zzbcu)zzavs.zzww().zzc(zzavu.zzwu()).zzm(zzbah.zzo(zzazl.zzbh(zzavu.getKeySize()))).zzam(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
