package com.facebook.ads.internal.p039i;

import android.content.Context;
import android.support.annotation.Nullable;

/* renamed from: com.facebook.ads.internal.i.a */
public final class C2018a {
    @Nullable
    /* renamed from: a */
    private static Context f4473a;

    @Nullable
    /* renamed from: a */
    public static Context m4870a() {
        return f4473a;
    }

    /* renamed from: a */
    public static void m4871a(Context context) {
        if (context != null) {
            f4473a = context.getApplicationContext();
        }
    }
}
