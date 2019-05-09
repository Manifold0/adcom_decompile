// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzaur
{
    public static final zzayf zzdht;
    private static final zzayf zzdhu;
    
    static {
        zzdht = (zzayf)((zzayf.zza)zzayf.zzaam().zza((zzbbo)zzavn.zzdht)).zzb(zzaub.zza("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).zzb(zzaub.zza("TinkAead", "Aead", "AesEaxKey", 0, true)).zzb(zzaub.zza("TinkAead", "Aead", "AesGcmKey", 0, true)).zzb(zzaub.zza("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).zzb(zzaub.zza("TinkAead", "Aead", "KmsAeadKey", 0, true)).zzb(zzaub.zza("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).zzej("TINK_AEAD_1_0_0").zzadi();
        zzdhu = (zzayf)((zzayf.zza)zzayf.zzaam().zza((zzbbo)zzaur.zzdht)).zzej("TINK_AEAD_1_1_0").zzadi();
        try {
            zzwk();
        }
        catch (GeneralSecurityException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void zzwk() throws GeneralSecurityException {
        zzauo.zza("TinkAead", (zzaua<Object>)new zzauq());
        zzavn.zzwk();
    }
}
