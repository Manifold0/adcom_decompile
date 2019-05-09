package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.cha.C1536t.C1532a;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.cha.e */
final class C1493e {
    /* renamed from: ˊ */
    private static WeakReference<Activity> f3465 = new WeakReference(null);
    @Nullable
    /* renamed from: ˋ */
    private static WebAdTracker f3466;

    C1493e() {
    }

    /* renamed from: ˎ */
    static void m3752(Activity activity) {
        try {
            if (C1536t.m3887().f3610 != C1532a.f3593) {
                String name = activity.getClass().getName();
                C1487a.m3715(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
                if (!name.contains(AdActivity.CLASS_NAME)) {
                    if (f3466 != null) {
                        C1487a.m3715(3, "GMAInterstitialHelper", f3465.get(), "Stopping to track GMA interstitial");
                        f3466.stopTracking();
                        f3466 = null;
                    }
                    f3465 = new WeakReference(null);
                } else if (f3465.get() == null || f3465.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        Optional ˊ = C1544x.m3910((ViewGroup) decorView, true);
                        if (ˊ.isPresent()) {
                            f3465 = new WeakReference(activity);
                            WebView webView = (WebView) ˊ.get();
                            C1487a.m3715(3, "GMAInterstitialHelper", f3465.get(), "Starting to track GMA interstitial");
                            WebAdTracker createWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
                            f3466 = createWebAdTracker;
                            createWebAdTracker.startTracking();
                            return;
                        }
                        C1487a.m3715(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }
}
