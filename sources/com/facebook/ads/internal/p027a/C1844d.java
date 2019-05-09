package com.facebook.ads.internal.p027a;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: com.facebook.ads.internal.a.d */
public enum C1844d {
    NONE,
    INSTALLED,
    NOT_INSTALLED;

    /* renamed from: a */
    public static C1844d m4145a(String str) {
        if (TextUtils.isEmpty(str)) {
            return NONE;
        }
        try {
            return C1844d.valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }
}
