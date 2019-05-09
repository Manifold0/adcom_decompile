package com.facebook.ads.internal.protocol;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.protocol.f */
public class C2071f {
    /* renamed from: a */
    private static final Map<C2069d, C2070e> f4678a = new HashMap();

    static {
        f4678a.put(C2069d.RECTANGLE_HEIGHT_250, C2070e.WEBVIEW_BANNER_250);
        f4678a.put(C2069d.BANNER_HEIGHT_90, C2070e.WEBVIEW_BANNER_90);
        f4678a.put(C2069d.BANNER_HEIGHT_50, C2070e.WEBVIEW_BANNER_50);
    }

    /* renamed from: a */
    public static C2070e m5048a(DisplayMetrics displayMetrics) {
        int i = (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
        int i2 = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        Object obj = (i < 640 || i2 < 640) ? null : 1;
        return obj != null ? C2070e.WEBVIEW_INTERSTITIAL_TABLET : i2 > i ? C2070e.WEBVIEW_INTERSTITIAL_VERTICAL : C2070e.WEBVIEW_INTERSTITIAL_HORIZONTAL;
    }

    /* renamed from: a */
    public static C2070e m5049a(C2069d c2069d) {
        C2070e c2070e = (C2070e) f4678a.get(c2069d);
        return c2070e == null ? C2070e.WEBVIEW_BANNER_LEGACY : c2070e;
    }

    /* renamed from: a */
    public static void m5050a(DisplayMetrics displayMetrics, View view, C2069d c2069d) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= c2069d.m5045a() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) c2069d.m5045a()) * displayMetrics.density)), (int) Math.ceil((double) (((float) c2069d.m5046b()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }
}
