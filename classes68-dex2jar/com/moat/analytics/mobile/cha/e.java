// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import com.moat.analytics.mobile.cha.base.functional.Optional;
import android.view.View;
import android.webkit.WebView;
import android.view.ViewGroup;
import android.support.annotation.Nullable;
import android.app.Activity;
import java.lang.ref.WeakReference;

final class e
{
    private static WeakReference<Activity> \u02ca;
    @Nullable
    private static WebAdTracker \u02cb;
    
    static {
        e.\u02ca = new WeakReference<Activity>(null);
    }
    
    static void \u02ce(final Activity activity) {
        Label_0171: {
            try {
                if (t.\u02cf().\u02ce == t.a.\u0971) {
                    return;
                }
                final String name = activity.getClass().getName();
                a.\u02cf(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
                if (!name.contains("com.google.android.gms.ads.AdActivity")) {
                    break Label_0171;
                }
                if (e.\u02ca.get() != null && e.\u02ca.get() == activity) {
                    return;
                }
                final View decorView = activity.getWindow().getDecorView();
                if (!(decorView instanceof ViewGroup)) {
                    return;
                }
                final Optional<WebView> \u02ca = x.\u02ca((ViewGroup)decorView, true);
                if (\u02ca.isPresent()) {
                    e.\u02ca = new WeakReference<Activity>(activity);
                    final WebView webView = \u02ca.get();
                    a.\u02cf(3, "GMAInterstitialHelper", e.\u02ca.get(), "Starting to track GMA interstitial");
                    (e.\u02cb = MoatFactory.create().createWebAdTracker(webView)).startTracking();
                    return;
                }
            }
            catch (Exception ex) {
                o.\u02ce(ex);
                return;
            }
            a.\u02cf(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
            return;
        }
        if (e.\u02cb != null) {
            a.\u02cf(3, "GMAInterstitialHelper", e.\u02ca.get(), "Stopping to track GMA interstitial");
            e.\u02cb.stopTracking();
            e.\u02cb = null;
        }
        e.\u02ca = new WeakReference<Activity>(null);
    }
}
