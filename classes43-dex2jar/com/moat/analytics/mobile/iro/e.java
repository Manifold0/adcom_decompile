// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import com.moat.analytics.mobile.iro.base.functional.Optional;
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
    private static WebAdTracker \u02cf;
    
    static {
        e.\u02ca = new WeakReference<Activity>(null);
    }
    
    static void \u02ca(final Activity activity) {
        Label_0171: {
            try {
                if (t.\u02cb().\u02cb == t.c.\u02ca) {
                    return;
                }
                final String name = activity.getClass().getName();
                b.\u02cf(3, "GMAInterstitialHelper", activity, "Activity name: " + name);
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
                final Optional<WebView> \u02cb = v.\u02cb((ViewGroup)decorView, true);
                if (\u02cb.isPresent()) {
                    e.\u02ca = new WeakReference<Activity>(activity);
                    final WebView webView = \u02cb.get();
                    b.\u02cf(3, "GMAInterstitialHelper", e.\u02ca.get(), "Starting to track GMA interstitial");
                    (e.\u02cf = MoatFactory.create().createWebAdTracker(webView)).startTracking();
                    return;
                }
            }
            catch (Exception ex) {
                o.\u0971(ex);
                return;
            }
            b.\u02cf(3, "GMAInterstitialHelper", activity, "Sorry, no WebView in this activity");
            return;
        }
        if (e.\u02cf != null) {
            b.\u02cf(3, "GMAInterstitialHelper", e.\u02ca.get(), "Stopping to track GMA interstitial");
            e.\u02cf.stopTracking();
            e.\u02cf = null;
        }
        e.\u02ca = new WeakReference<Activity>(null);
    }
}
