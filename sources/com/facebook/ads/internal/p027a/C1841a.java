package com.facebook.ads.internal.p027a;

import android.support.annotation.Nullable;

/* renamed from: com.facebook.ads.internal.a.a */
public enum C1841a {
    CANNOT_OPEN,
    CANNOT_TRACK;

    /* renamed from: a */
    public static boolean m4139a(@Nullable C1841a c1841a) {
        return CANNOT_OPEN.equals(c1841a) || CANNOT_TRACK.equals(c1841a);
    }
}
