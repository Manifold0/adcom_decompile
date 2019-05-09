// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.moat.analytics.mobile.cha.MoatFactory;
import android.webkit.WebView;
import com.moat.analytics.mobile.cha.MoatAnalytics;
import com.moat.analytics.mobile.cha.MoatOptions;
import android.app.Application;
import com.chartboost.sdk.Libraries.CBLogging;
import com.moat.analytics.mobile.cha.WebAdTracker;

public class r implements q
{
    private static String b;
    WebAdTracker a;
    
    static {
        r.b = r.class.getSimpleName();
    }
    
    public r() {
        this.a = null;
    }
    
    @Override
    public void a() {
        if (this.a != null) {
            CBLogging.a(r.b, "start MOAT tracker");
            this.a.startTracking();
        }
    }
    
    @Override
    public void a(final Application application, final boolean loggingEnabled, final boolean disableLocationServices, final boolean disableAdIdCollection) {
        final boolean b = true;
        CBLogging.a(r.b, "start MOAT provider, Debugging Enabled: " + loggingEnabled + "Location Enabled:" + !disableLocationServices + "idfaCollectionEnabled:" + (!disableAdIdCollection && b));
        final MoatOptions moatOptions = new MoatOptions();
        moatOptions.disableLocationServices = disableLocationServices;
        moatOptions.disableAdIdCollection = disableAdIdCollection;
        moatOptions.loggingEnabled = loggingEnabled;
        MoatAnalytics.getInstance().start(moatOptions, application);
    }
    
    @Override
    public void a(final WebView webView) {
        this.a = MoatFactory.create().createWebAdTracker(webView);
    }
    
    @Override
    public void b() {
        if (this.a != null) {
            CBLogging.a(r.b, "stop MOAT tracker");
            this.a.stopTracking();
            this.a = null;
        }
    }
}
