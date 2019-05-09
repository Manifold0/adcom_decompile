// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.security.GeneralSecurityException;

public final class zzaul
{
    @Deprecated
    public static final zzauh zzh(final byte[] array) throws GeneralSecurityException {
        zzaxr zzj;
        try {
            zzj = zzaxr.zzj(array);
            for (final zzaxr.zzb zzb : zzj.zzzl()) {
                if (zzb.zzzp().zzyy() == zzaxi.zzb.zzdkw || zzb.zzzp().zzyy() == zzaxi.zzb.zzdkx || zzb.zzzp().zzyy() == zzaxi.zzb.zzdky) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzauh.zza(zzj);
        }
        catch (zzbbu zzbbu) {
            throw new GeneralSecurityException("invalid keyset");
        }
        return zzauh.zza(zzj);
    }
}
