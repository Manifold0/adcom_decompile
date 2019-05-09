package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import com.moat.analytics.mobile.vng.p013a.p015b.C0820a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.vng.f */
class C0830f {
    @Nullable
    /* renamed from: a */
    private static WebAdTracker f1367a;
    /* renamed from: b */
    private static WeakReference<Activity> f1368b = new WeakReference(null);

    C0830f() {
    }

    /* renamed from: a */
    private static void m1468a() {
        if (f1367a != null) {
            C0858p.m1577a(3, "GMAInterstitialHelper", f1368b.get(), "Stopping to track GMA interstitial");
            f1367a.stopTracking();
            f1367a = null;
        }
    }

    /* renamed from: a */
    static void m1469a(Activity activity) {
        try {
            if (C0879w.m1610a().f1478a != C0878d.OFF) {
                if (!C0830f.m1471b(activity)) {
                    C0830f.m1468a();
                    f1368b = new WeakReference(null);
                } else if (f1368b.get() == null || f1368b.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        C0820a a = ab.m1458a((ViewGroup) decorView);
                        if (a.m1437c()) {
                            f1368b = new WeakReference(activity);
                            C0830f.m1470a((WebView) a.m1435b());
                            return;
                        }
                        C0858p.m1577a(3, "GMAInterstitialHelper", (Object) activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: a */
    private static void m1470a(WebView webView) {
        C0858p.m1577a(3, "GMAInterstitialHelper", f1368b.get(), "Starting to track GMA interstitial");
        f1367a = MoatFactory.create().createWebAdTracker(webView);
        f1367a.startTracking();
    }

    /* renamed from: b */
    private static boolean m1471b(Activity activity) {
        String name = activity.getClass().getName();
        C0858p.m1577a(3, "GMAInterstitialHelper", (Object) activity, "Activity name: " + name);
        return name.contains(AdActivity.CLASS_NAME);
    }
}
