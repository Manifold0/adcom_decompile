package com.google.android.gms.common;

import android.support.annotation.NonNull;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
class zzm {
    private static final zzm zzac = new zzm(true, null, null);
    private final Throwable cause;
    final boolean zzad;
    private final String zzae;

    zzm(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.zzad = z;
        this.zzae = str;
        this.cause = th;
    }

    static zzm zze() {
        return zzac;
    }

    static zzm zza(Callable<String> callable) {
        return new zzo(callable, null);
    }

    static zzm zzb(@NonNull String str) {
        return new zzm(false, str, null);
    }

    static zzm zza(@NonNull String str, @NonNull Throwable th) {
        return new zzm(false, str, th);
    }

    @Nullable
    String getErrorMessage() {
        return this.zzae;
    }

    final void zzf() {
        if (!this.zzad && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.cause != null) {
                Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
            } else {
                Log.d("GoogleCertificatesRslt", getErrorMessage());
            }
        }
    }

    static String zzc(String str, zze zze, boolean z, boolean z2) {
        String str2 = z2 ? "debug cert rejected" : "not whitelisted";
        return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", new Object[]{str2, str, Hex.bytesToStringLowercase(AndroidUtilsLight.zzj(Constants.SHA1).digest(zze.getBytes())), Boolean.valueOf(z), "12451009.false"});
    }
}
