// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.security.GeneralSecurityException;

final class zzavj implements zzayn
{
    private final String zzdic;
    private final int zzdid;
    private zzawe zzdie;
    private zzavo zzdif;
    private int zzdig;
    
    zzavj(final zzaxn zzaxn) throws GeneralSecurityException {
        this.zzdic = zzaxn.zzyw();
        if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                final zzawg zzt = zzawg.zzt(zzaxn.zzyx());
                this.zzdie = (zzawe)zzauo.zzb(zzaxn);
                this.zzdid = zzt.getKeySize();
                return;
            }
            catch (zzbbu zzbbu) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", (Throwable)zzbbu);
            }
        }
        if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                final zzavq zzj = zzavq.zzj(zzaxn.zzyx());
                this.zzdif = (zzavo)zzauo.zzb(zzaxn);
                this.zzdig = zzj.zzwr().getKeySize();
                this.zzdid = zzj.zzws().getKeySize() + this.zzdig;
                return;
            }
            catch (zzbbu zzbbu2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", (Throwable)zzbbu2);
            }
        }
        final String value = String.valueOf(this.zzdic);
        String concat;
        if (value.length() != 0) {
            concat = "unsupported AEAD DEM key type: ".concat(value);
        }
        else {
            concat = new String("unsupported AEAD DEM key type: ");
        }
        throw new GeneralSecurityException(concat);
    }
    
    @Override
    public final zzatz zzi(byte[] copyOfRange) throws GeneralSecurityException {
        if (copyOfRange.length != this.zzdid) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            return zzauo.zzb(this.zzdic, (zzbcu)((zzawe.zza)zzawe.zzxk().zza((zzbbo)this.zzdie)).zzs(zzbah.zzc(copyOfRange, 0, this.zzdid)).zzadi());
        }
        if (this.zzdic.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            final byte[] copyOfRange2 = Arrays.copyOfRange(copyOfRange, 0, this.zzdig);
            copyOfRange = Arrays.copyOfRange(copyOfRange, this.zzdig, this.zzdid);
            return (zzatz)zzauo.zzb(this.zzdic, (zzbcu)zzavo.zzwp().zzal(this.zzdif.getVersion()).zzb((zzavs)((zzavs.zza)zzavs.zzww().zza((zzbbo)this.zzdif.zzwn())).zzm(zzbah.zzo(copyOfRange2)).zzadi()).zzb((zzaxc)((zzaxc.zza)zzaxc.zzyn().zza((zzbbo)this.zzdif.zzwo())).zzaf(zzbah.zzo(copyOfRange)).zzadi()).zzadi());
        }
        throw new GeneralSecurityException("unknown DEM key type");
    }
    
    @Override
    public final int zzwm() {
        return this.zzdid;
    }
}
