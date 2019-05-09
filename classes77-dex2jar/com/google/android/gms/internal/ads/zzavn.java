// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class zzavn
{
    public static final zzayf zzdht;
    private static final zzayf zzdhu;
    
    static {
        zzdht = (zzayf)zzayf.zzaam().zzej("TINK_MAC_1_0_0").zzb(zzaub.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzadi();
        zzdhu = (zzayf)((zzayf.zza)zzayf.zzaam().zza((zzbbo)zzavn.zzdht)).zzej("TINK_MAC_1_1_0").zzadi();
        try {
            zzwk();
        }
        catch (GeneralSecurityException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void zzwk() throws GeneralSecurityException {
        zzauo.zza("TinkMac", (zzaua<Object>)new zzavm());
    }
}
