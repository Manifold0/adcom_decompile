// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import com.moat.analytics.mobile.vng.a.b.a;
import android.view.View;
import android.webkit.WebView;
import android.view.ViewGroup;
import android.app.Activity;
import java.lang.ref.WeakReference;
import android.support.annotation.Nullable;

class f
{
    @Nullable
    private static WebAdTracker a;
    private static WeakReference<Activity> b;
    
    static {
        f.b = new WeakReference<Activity>(null);
    }
    
    private static void a() {
        if (f.a != null) {
            p.a(3, "GMAInterstitialHelper", f.b.get(), "Stopping to track GMA interstitial");
            f.a.stopTracking();
            f.a = null;
        }
    }
    
    static void a(final Activity activity) {
        Label_0107: {
            try {
                if (w.a().a == w.d.a) {
                    return;
                }
                if (!b(activity)) {
                    break Label_0107;
                }
                if (f.b.get() != null && f.b.get() == activity) {
                    return;
                }
                final View decorView = activity.getWindow().getDecorView();
                if (!(decorView instanceof ViewGroup)) {
                    return;
                }
                final a<WebView> a = ab.a((ViewGroup)decorView);
                if (a.c()) {
                    f.b = new WeakReference<Activity>(activity);
                    a(a.b());
                    return;
                }
            }
            catch (Exception ex) {
                m.a(ex);
                return;
            }
            p.a(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
            return;
        }
        a();
        f.b = new WeakReference<Activity>(null);
    }
    
    private static void a(final WebView webView) {
        p.a(3, "GMAInterstitialHelper", f.b.get(), "Starting to track GMA interstitial");
        (f.a = MoatFactory.create().createWebAdTracker(webView)).startTracking();
    }
    
    private static boolean b(final Activity activity) {
        final String name = activity.getClass().getName();
        p.a(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
        return name.contains("com.google.android.gms.ads.AdActivity");
    }
}
