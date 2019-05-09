// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

public final class CookieUtil
{
    private CookieUtil() {
    }
    
    public static String getCookieUrl(final String s, final Boolean b) {
        Preconditions.checkNotEmpty(s);
        String s2;
        if (zza(b)) {
            s2 = "https";
        }
        else {
            s2 = "http";
        }
        return new StringBuilder(String.valueOf(s2).length() + 3 + String.valueOf(s).length()).append(s2).append("://").append(s).toString();
    }
    
    public static String getCookieValue(final String s, final String s2, final String s3, final String s4, final Boolean b, final Boolean b2, final Long n) {
        final StringBuilder append = new StringBuilder(s).append('=');
        if (!TextUtils.isEmpty((CharSequence)s2)) {
            append.append(s2);
        }
        if (zza(b)) {
            append.append(";HttpOnly");
        }
        if (zza(b2)) {
            append.append(";Secure");
        }
        if (!TextUtils.isEmpty((CharSequence)s3)) {
            append.append(";Domain=").append(s3);
        }
        if (!TextUtils.isEmpty((CharSequence)s4)) {
            append.append(";Path=").append(s4);
        }
        if (n != null && n > 0L) {
            append.append(";Max-Age=").append(n);
        }
        return append.toString();
    }
    
    private static boolean zza(final Boolean b) {
        return b != null && b;
    }
}
