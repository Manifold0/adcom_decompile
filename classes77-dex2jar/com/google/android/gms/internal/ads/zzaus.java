// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class zzaus implements zzaug<zzatz>
{
    private static final Logger logger;
    
    static {
        logger = Logger.getLogger(zzaus.class.getName());
    }
    
    zzaus() throws GeneralSecurityException {
        zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzaug<Object>)new zzaut());
    }
    
    private final zzatz zzd(final zzbah zzbah) throws GeneralSecurityException {
        zzavo zzi;
        try {
            zzi = zzavo.zzi(zzbah);
            if (!(zzi instanceof zzavo)) {
                throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", (Throwable)zzbbu);
        }
        final zzavo zzavo = zzi;
        zzazq.zzj(zzavo.getVersion(), 0);
        return new zzayx((zzazi)zzauo.zzb("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzbcu)zzavo.zzwn()), (zzauk)zzauo.zzb("type.googleapis.com/google.crypto.tink.HmacKey", (zzbcu)zzavo.zzwo()), zzavo.zzwo().zzym().zzyt());
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzavq.zzj(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzavq)) {
            throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
        }
        final zzavq zzavq = (zzavq)zzbcu;
        return (zzbcu)zzavo.zzwp().zzb((zzavs)zzauo.zza("type.googleapis.com/google.crypto.tink.AesCtrKey", (zzbcu)zzavq.zzwr())).zzb((zzaxc)zzauo.zza("type.googleapis.com/google.crypto.tink.HmacKey", (zzbcu)zzavq.zzws())).zzal(0).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
