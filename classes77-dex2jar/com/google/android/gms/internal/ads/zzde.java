// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

final class zzde
{
    static zzauf zzso;
    
    static boolean zzb(final zzcz zzcz) throws IllegalAccessException, InvocationTargetException {
        if (zzde.zzso != null) {
            return true;
        }
        final String s = (String)zzkb.zzik().zzd(zznk.zzbax);
        Label_0084: {
            if (s != null) {
                final String s2 = s;
                if (s.length() != 0) {
                    break Label_0084;
                }
            }
            String s3;
            if (zzcz == null) {
                s3 = null;
            }
            else {
                final Method zza = zzcz.zza("4o7tecxtkw7XaNt5hPj+0H1LvOi0SgxCIJTY9VcbazM/HSl/sFlxBFwnc8glnvoB", "RgSY6YxU2k1vLXOV3vapBnQwJDzYDlmX50wbm2tDcnw=");
                if (zza == null) {
                    s3 = null;
                }
                else {
                    s3 = (String)zza.invoke(null, new Object[0]);
                }
            }
            final String s2 = s3;
            if (s3 == null) {
                return false;
            }
            try {
                final byte[] zza2 = zzbi.zza(s2, true);
                try {
                    final zzauh zzh = zzaul.zzh(zza2);
                    for (final zzaxp zzaxp : zzavc.zzdht.zzaal()) {
                        if (zzaxp.zzyw().isEmpty()) {
                            throw new GeneralSecurityException("Missing type_url.");
                        }
                        if (zzaxp.zzze().isEmpty()) {
                            throw new GeneralSecurityException("Missing primitive_name.");
                        }
                        if (zzaxp.zzzh().isEmpty()) {
                            throw new GeneralSecurityException("Missing catalogue_name.");
                        }
                        zzauo.zza(zzaxp.zzyw(), (zzaug<Object>)zzauo.zzdy(zzaxp.zzzh()).zzb(zzaxp.zzyw(), zzaxp.zzze(), zzaxp.zzzf()), zzaxp.zzzg());
                    }
                    zzde.zzso = zzavf.zza(zzh);
                    return zzde.zzso != null;
                }
                catch (GeneralSecurityException ex) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex2) {
                return false;
            }
        }
    }
}
