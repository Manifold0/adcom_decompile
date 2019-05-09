// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.nio.charset.Charset;

final class zzaup
{
    private static final Charset UTF_8;
    
    static {
        UTF_8 = Charset.forName("UTF-8");
    }
    
    public static zzaxt zzb(final zzaxr zzaxr) {
        final zzaxt.zza zzbb = zzaxt.zzzu().zzbb(zzaxr.zzzk());
        for (final zzaxr.zzb zzb : zzaxr.zzzl()) {
            zzbb.zzb((zzaxt.zzb)zzaxt.zzb.zzzw().zzeh(zzb.zzzp().zzyw()).zzb(zzb.zzzq()).zzb(zzb.zzzs()).zzbd(zzb.zzzr()).zzadi());
        }
        return (zzaxt)zzbb.zzadi();
    }
    
    public static void zzc(final zzaxr zzaxr) throws GeneralSecurityException {
        if (zzaxr.zzzm() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        final int zzzk = zzaxr.zzzk();
        final Iterator<zzaxr.zzb> iterator = zzaxr.zzzl().iterator();
        boolean b = true;
        int n = 0;
        while (iterator.hasNext()) {
            final zzaxr.zzb zzb = iterator.next();
            if (!zzb.zzzo()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", zzb.zzzr()));
            }
            if (zzb.zzzs() == zzayd.zzdmk) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", zzb.zzzr()));
            }
            if (zzb.zzzq() == zzaxl.zzdld) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", zzb.zzzr()));
            }
            int n2 = n;
            if (zzb.zzzq() == zzaxl.zzdle) {
                n2 = n;
                if (zzb.zzzr() == zzzk) {
                    if (n != 0) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    n2 = 1;
                }
            }
            if (zzb.zzzp().zzyy() != zzaxi.zzb.zzdkz) {
                b = false;
            }
            n = n2;
        }
        if (n == 0 && !b) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
