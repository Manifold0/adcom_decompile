package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.iro.C0803t.C0800c;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.iro.e */
final class C0760e {
    /* renamed from: ˊ */
    private static WeakReference<Activity> f1159 = new WeakReference(null);
    @Nullable
    /* renamed from: ˏ */
    private static WebAdTracker f1160;

    C0760e() {
    }

    /* renamed from: ˊ */
    static void m1262(Activity activity) {
        try {
            if (C0803t.m1393().f1298 != C0800c.f1285) {
                String name = activity.getClass().getName();
                C0756b.m1234(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
                if (!name.contains(AdActivity.CLASS_NAME)) {
                    if (f1160 != null) {
                        C0756b.m1234(3, "GMAInterstitialHelper", f1159.get(), "Stopping to track GMA interstitial");
                        f1160.stopTracking();
                        f1160 = null;
                    }
                    f1159 = new WeakReference(null);
                } else if (f1159.get() == null || f1159.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        Optional ˋ = C0804v.m1404((ViewGroup) decorView, true);
                        if (ˋ.isPresent()) {
                            f1159 = new WeakReference(activity);
                            WebView webView = (WebView) ˋ.get();
                            C0756b.m1234(3, "GMAInterstitialHelper", f1159.get(), "Starting to track GMA interstitial");
                            WebAdTracker createWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
                            f1160 = createWebAdTracker;
                            createWebAdTracker.startTracking();
                            return;
                        }
                        C0756b.m1234(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }
}
