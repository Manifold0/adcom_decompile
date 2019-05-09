package com.google.android.gms.common;

import android.content.Context;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzn;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class zzc {
    private static volatile zzm zzn;
    private static final Object zzo = new Object();
    private static Context zzp;

    static synchronized void zza(Context context) {
        synchronized (zzc.class) {
            if (zzp != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzp = context.getApplicationContext();
            }
        }
    }

    static zzm zza(String str, zze zze, boolean z, boolean z2) {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            zzm zzb = zzb(str, zze, z, z2);
            return zzb;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    private static zzm zzb(String str, zze zze, boolean z, boolean z2) {
        try {
            if (zzn == null) {
                Preconditions.checkNotNull(zzp);
                synchronized (zzo) {
                    if (zzn == null) {
                        zzn = zzn.zzc(DynamiteModule.load(zzp, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                    }
                }
            }
            Preconditions.checkNotNull(zzp);
            try {
                if (zzn.zza(new zzk(str, zze, z, z2), ObjectWrapper.wrap(zzp.getPackageManager()))) {
                    return zzm.zze();
                }
                return zzm.zza(new zzd(z, str, zze));
            } catch (Throwable e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzm.zza("module call", e);
            }
        } catch (Throwable e2) {
            Throwable th = e2;
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", th);
            String str2 = "module init: ";
            String valueOf = String.valueOf(th.getMessage());
            return zzm.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
        }
    }

    static final /* synthetic */ String zza(boolean z, String str, zze zze) throws Exception {
        boolean z2 = true;
        if (z || !zzb(str, zze, true, false).zzad) {
            z2 = false;
        }
        return zzm.zzc(str, zze, z, z2);
    }
}
