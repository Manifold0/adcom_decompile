package com.chartboost.sdk.impl;

import android.app.Application;
import android.webkit.WebView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.moat.analytics.mobile.cha.MoatAnalytics;
import com.moat.analytics.mobile.cha.MoatFactory;
import com.moat.analytics.mobile.cha.MoatOptions;
import com.moat.analytics.mobile.cha.WebAdTracker;

/* renamed from: com.chartboost.sdk.impl.r */
public class C1453r implements C1452q {
    /* renamed from: b */
    private static String f3319b = C1453r.class.getSimpleName();
    /* renamed from: a */
    WebAdTracker f3320a = null;

    /* renamed from: a */
    public void mo4313a(Application application, boolean z, boolean z2, boolean z3) {
        boolean z4 = true;
        String str = f3319b;
        StringBuilder append = new StringBuilder().append("start MOAT provider, Debugging Enabled: ").append(z).append("Location Enabled:").append(!z2).append("idfaCollectionEnabled:");
        if (z3) {
            z4 = false;
        }
        CBLogging.m3097a(str, append.append(z4).toString());
        MoatOptions moatOptions = new MoatOptions();
        moatOptions.disableLocationServices = z2;
        moatOptions.disableAdIdCollection = z3;
        moatOptions.loggingEnabled = z;
        MoatAnalytics.getInstance().start(moatOptions, application);
    }

    /* renamed from: a */
    public void mo4314a(WebView webView) {
        this.f3320a = MoatFactory.create().createWebAdTracker(webView);
    }

    /* renamed from: a */
    public void mo4312a() {
        if (this.f3320a != null) {
            CBLogging.m3097a(f3319b, "start MOAT tracker");
            this.f3320a.startTracking();
        }
    }

    /* renamed from: b */
    public void mo4315b() {
        if (this.f3320a != null) {
            CBLogging.m3097a(f3319b, "stop MOAT tracker");
            this.f3320a.stopTracking();
            this.f3320a = null;
        }
    }
}
