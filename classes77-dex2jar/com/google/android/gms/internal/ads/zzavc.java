// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzavc
{
    public static final zzayf zzdht;
    private static final zzayf zzdhu;
    
    static {
        zzdht = (zzayf)((zzayf.zza)zzayf.zzaam().zza((zzbbo)zzaur.zzdht)).zzb(zzaub.zza("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).zzb(zzaub.zza("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).zzej("TINK_HYBRID_1_0_0").zzadi();
        zzdhu = (zzayf)((zzayf.zza)zzayf.zzaam().zza((zzbbo)zzavc.zzdht)).zzej("TINK_HYBRID_1_1_0").zzadi();
        try {
            zzauo.zza("TinkHybridEncrypt", (zzaua<Object>)new zzave());
            zzauo.zza("TinkHybridDecrypt", (zzaua<Object>)new zzavd());
            zzaur.zzwk();
        }
        catch (GeneralSecurityException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
}
