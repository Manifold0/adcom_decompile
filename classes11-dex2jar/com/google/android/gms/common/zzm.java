// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.util.concurrent.Callable;
import android.support.annotation.NonNull;
import javax.annotation.Nullable;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
class zzm
{
    private static final zzm zzac;
    private final Throwable cause;
    final boolean zzad;
    private final String zzae;
    
    static {
        zzac = new zzm(true, null, null);
    }
    
    zzm(final boolean zzad, @Nullable final String zzae, @Nullable final Throwable cause) {
        this.zzad = zzad;
        this.zzae = zzae;
        this.cause = cause;
    }
    
    static zzm zza(@NonNull final String s, @NonNull final Throwable t) {
        return new zzm(false, s, t);
    }
    
    static zzm zza(final Callable<String> callable) {
        return new zzo(callable, null);
    }
    
    static zzm zzb(@NonNull final String s) {
        return new zzm(false, s, null);
    }
    
    static String zzc(final String s, final zze zze, final boolean b, final boolean b2) {
        String s2;
        if (b2) {
            s2 = "debug cert rejected";
        }
        else {
            s2 = "not whitelisted";
        }
        return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", s2, s, Hex.bytesToStringLowercase(AndroidUtilsLight.zzj("SHA-1").digest(zze.getBytes())), b, "12451009.false");
    }
    
    static zzm zze() {
        return zzm.zzac;
    }
    
    @Nullable
    String getErrorMessage() {
        return this.zzae;
    }
    
    final void zzf() {
        if (!this.zzad && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.cause == null) {
                Log.d("GoogleCertificatesRslt", this.getErrorMessage());
                return;
            }
            Log.d("GoogleCertificatesRslt", this.getErrorMessage(), this.cause);
        }
    }
}
