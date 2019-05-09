// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

final class zzavk implements zzaug<zzauk>
{
    private static void zza(final zzaxg zzaxg) throws GeneralSecurityException {
        if (zzaxg.zzyt() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch (zzavl.zzdhz[zzaxg.zzys().ordinal()]) {
            default: {
                throw new GeneralSecurityException("unknown hash type");
            }
            case 1: {
                if (zzaxg.zzyt() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 2: {
                if (zzaxg.zzyt() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
            case 3: {
                if (zzaxg.zzyt() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                break;
            }
        }
    }
    
    private final zzauk zzh(final zzbah zzbah) throws GeneralSecurityException {
        zzaxc zzae;
        try {
            zzae = zzaxc.zzae(zzbah);
            if (!(zzae instanceof zzaxc)) {
                throw new GeneralSecurityException("expected HmacKey proto");
            }
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", (Throwable)zzbbu);
        }
        final zzaxc zzaxc = zzae;
        zzazq.zzj(zzaxc.getVersion(), 0);
        if (zzaxc.zzwv().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzaxc.zzym());
        final zzaxa zzys = zzaxc.zzym().zzys();
        final SecretKeySpec secretKeySpec = new SecretKeySpec(zzaxc.zzwv().toByteArray(), "HMAC");
        final int zzyt = zzaxc.zzym().zzyt();
        zzazj zzazj = null;
        switch (zzavl.zzdhz[zzys.ordinal()]) {
            case 1: {
                zzazj = new zzazj("HMACSHA1", secretKeySpec, zzyt);
                break;
            }
            case 2: {
                zzazj = new zzazj("HMACSHA256", secretKeySpec, zzyt);
                break;
            }
            case 3: {
                zzazj = new zzazj("HMACSHA512", secretKeySpec, zzyt);
                break;
            }
            default: {
                throw new GeneralSecurityException("unknown hash");
            }
        }
        return zzazj;
    }
    
    @Override
    public final int getVersion() {
        return 0;
    }
    
    @Override
    public final zzbcu zzb(final zzbah zzbah) throws GeneralSecurityException {
        try {
            return this.zzb((zzbcu)zzaxe.zzag(zzbah));
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", (Throwable)zzbbu);
        }
    }
    
    @Override
    public final zzbcu zzb(final zzbcu zzbcu) throws GeneralSecurityException {
        if (!(zzbcu instanceof zzaxe)) {
            throw new GeneralSecurityException("expected HmacKeyFormat proto");
        }
        final zzaxe zzaxe = (zzaxe)zzbcu;
        if (zzaxe.getKeySize() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zza(zzaxe.zzym());
        return (zzbcu)zzaxc.zzyn().zzav(0).zzc(zzaxe.zzym()).zzaf(zzbah.zzo(zzazl.zzbh(zzaxe.getKeySize()))).zzadi();
    }
    
    @Override
    public final zzaxi zzc(final zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi)zzaxi.zzyz().zzeb("type.googleapis.com/google.crypto.tink.HmacKey").zzai(((zzazy)this.zzb(zzbah)).zzaav()).zzb(zzaxi.zzb.zzdkx).zzadi();
    }
}
