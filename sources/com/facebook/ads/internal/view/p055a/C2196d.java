package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.adapters.C1938p.C1937b;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.C1921a.C1789a;

/* renamed from: com.facebook.ads.internal.view.a.d */
public class C2196d {
    /* renamed from: a */
    private static final int f5089a = ((int) (C2600x.f6420b * 200.0f));
    /* renamed from: b */
    private static final int f5090b = ((int) (C2600x.f6420b * 200.0f));
    /* renamed from: c */
    private static final int f5091c = ((int) (50.0f * C2600x.f6420b));

    /* renamed from: a */
    public static C1937b m5671a(@Nullable NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return C1937b.NO_NATIVE_AD_LAYOUT;
        }
        int width = nativeAdLayout.getWidth();
        int height = nativeAdLayout.getHeight();
        Object obj = ((width < f5089a || height < f5089a) && (width < f5090b || height < f5091c)) ? 1 : null;
        return obj != null ? C1937b.TOO_SMALL : C1937b.AVAILABLE;
    }

    @Nullable
    /* renamed from: a */
    public static C2195c m5672a(Context context, C2085c c2085c, String str, @Nullable NativeAdLayout nativeAdLayout) {
        if (nativeAdLayout == null) {
            return null;
        }
        int width = nativeAdLayout.getWidth();
        int height = nativeAdLayout.getHeight();
        return (width < f5089a || height < f5089a) ? (width < f5090b || height < f5091c) ? null : new C2209h(context, c2085c, str, width, height) : new C2219k(context, c2085c, str, width, height);
    }

    /* renamed from: a */
    public static C2195c m5673a(Context context, C2085c c2085c, String str, C1921a c1921a, C1789a c1789a) {
        return new C2202g(context, c2085c, str, c1921a, c1789a);
    }
}
