// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import android.support.annotation.Nullable;
import android.util.Log;

@zzadh
public class zzane
{
    public static void e(final String s) {
        if (isLoggable(6)) {
            Log.e("Ads", s);
        }
    }
    
    public static boolean isLoggable(final int n) {
        return n >= 5 || Log.isLoggable("Ads", n);
    }
    
    public static void zza(final String s, final Throwable t) {
        if (isLoggable(3)) {
            Log.d("Ads", s, t);
        }
    }
    
    public static void zzb(final String s, final Throwable t) {
        if (isLoggable(6)) {
            Log.e("Ads", s, t);
        }
    }
    
    public static void zzc(final String s, final Throwable t) {
        if (isLoggable(5)) {
            Log.w("Ads", s, t);
        }
    }
    
    public static void zzck(final String s) {
        if (isLoggable(3)) {
            Log.d("Ads", s);
        }
    }
    
    public static void zzd(final String s, @Nullable final Throwable t) {
        if (isLoggable(5)) {
            if (t == null) {
                zzdk(zzdl(s));
                return;
            }
            zzc(zzdl(s), t);
        }
    }
    
    public static void zzdj(final String s) {
        if (isLoggable(4)) {
            Log.i("Ads", s);
        }
    }
    
    public static void zzdk(final String s) {
        if (isLoggable(5)) {
            Log.w("Ads", s);
        }
    }
    
    @VisibleForTesting
    private static String zzdl(final String s) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String string = s;
        if (stackTrace.length >= 4) {
            string = new StringBuilder(String.valueOf(s).length() + 13).append(s).append(" @").append(stackTrace[3].getLineNumber()).toString();
        }
        return string;
    }
    
    public static void zzdm(final String s) {
        zzd(s, null);
    }
}
