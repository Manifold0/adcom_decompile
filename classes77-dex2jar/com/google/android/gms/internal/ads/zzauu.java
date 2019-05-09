// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class zzauu implements zzaug<zzatz>
{
    private final zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        zzavy zzo;
        try {
            zzo = zzavy.zzo(zzbah);
            if (!(zzo instanceof zzavy)) {
                throw new GeneralSecurityException("expected AesEaxKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", (Throwable)zzbbu);
        }
        final zzavy zzavy = zzo;
        zzazq.zzj(zzavy.getVersion(), 0);
        zzazq.zzbi(zzavy.zzwv().size());
        if (zzavy.zzxe().zzxb() != 12 && zzavy.zzxe().zzxb() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        return new zzayi(zzavy.zzwv().toByteArray(), zzavy.zzxe().zzxb());
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzawa.zzq(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzawa)) {
            throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
        }
        final zzawa zzawa = (zzawa)zzbcu;
        zzazq.zzbi(zzawa.getKeySize());
        if (zzawa.zzxe().zzxb() != 12 && zzawa.zzxe().zzxb() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
        return (zzbcu)zzavy.zzxf().zzp(zzbah.zzo(zzazl.zzbh(zzawa.getKeySize()))).zzb(zzawa.zzxe()).zzan(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesEaxKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
