package com.google.android.gms.auth;

import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.gms.common.internal.Preconditions;

public final class CookieUtil {
    private CookieUtil() {
    }

    private static boolean zza(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    public static String getCookieUrl(String str, Boolean bool) {
        Preconditions.checkNotEmpty(str);
        String str2 = zza(bool) ? Constants.SCHEME : "http";
        return new StringBuilder((String.valueOf(str2).length() + 3) + String.valueOf(str).length()).append(str2).append("://").append(str).toString();
    }

    public static String getCookieValue(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Long l) {
        StringBuilder append = new StringBuilder(str).append('=');
        if (!TextUtils.isEmpty(str2)) {
            append.append(str2);
        }
        if (zza(bool)) {
            append.append(";HttpOnly");
        }
        if (zza(bool2)) {
            append.append(";Secure");
        }
        if (!TextUtils.isEmpty(str3)) {
            append.append(";Domain=").append(str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            append.append(";Path=").append(str4);
        }
        if (l != null && l.longValue() > 0) {
            append.append(";Max-Age=").append(l);
        }
        return append.toString();
    }
}
