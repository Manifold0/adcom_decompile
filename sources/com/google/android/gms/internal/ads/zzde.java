package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

final class zzde {
    static zzauf zzso;

    static boolean zzb(zzcz zzcz) throws IllegalAccessException, InvocationTargetException {
        if (zzso != null) {
            return true;
        }
        String str = (String) zzkb.zzik().zzd(zznk.zzbax);
        if (str == null || str.length() == 0) {
            if (zzcz == null) {
                str = null;
            } else {
                Method zza = zzcz.zza("4o7tecxtkw7XaNt5hPj+0H1LvOi0SgxCIJTY9VcbazM/HSl/sFlxBFwnc8glnvoB", "RgSY6YxU2k1vLXOV3vapBnQwJDzYDlmX50wbm2tDcnw=");
                str = zza == null ? null : (String) zza.invoke(null, new Object[0]);
            }
            if (str == null) {
                return false;
            }
        }
        try {
            try {
                zzauh zzh = zzaul.zzh(zzbi.zza(str, true));
                for (zzaxp zzaxp : zzavc.zzdht.zzaal()) {
                    if (zzaxp.zzyw().isEmpty()) {
                        throw new GeneralSecurityException("Missing type_url.");
                    } else if (zzaxp.zzze().isEmpty()) {
                        throw new GeneralSecurityException("Missing primitive_name.");
                    } else if (zzaxp.zzzh().isEmpty()) {
                        throw new GeneralSecurityException("Missing catalogue_name.");
                    } else {
                        zzauo.zza(zzaxp.zzyw(), zzauo.zzdy(zzaxp.zzzh()).zzb(zzaxp.zzyw(), zzaxp.zzze(), zzaxp.zzzf()), zzaxp.zzzg());
                    }
                }
                zzso = zzavf.zza(zzh);
                return zzso != null;
            } catch (GeneralSecurityException e) {
                return false;
            }
        } catch (IllegalArgumentException e2) {
            return false;
        }
    }
}
