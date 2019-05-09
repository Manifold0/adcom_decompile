package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.util.Iterator;
import java.util.LinkedHashSet;

class fb extends dx {
    fb(AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskInitializeSdk", appLovinSdkImpl);
        this.g = true;
    }

    /* renamed from: a */
    private void m2839a(ec<String> ecVar, C1288o c1288o) {
        String str = (String) this.d.get((ec) ecVar);
        if (str.length() > 0) {
            for (String str2 : aa.m2193a(str2)) {
                AppLovinAdSize fromString = AppLovinAdSize.fromString(str2);
                if (fromString != null) {
                    this.d.m2142c().mo4145g(C1287n.m3035a(fromString, AppLovinAdType.REGULAR, c1288o, this.d));
                    if (AppLovinAdSize.INTERSTITIAL.getLabel().equals(fromString.getLabel())) {
                        m2840b(c1288o == C1288o.DIRECT ? ea.f2392P : ea.f2393Q, c1288o);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m2840b(ec<Boolean> ecVar, C1288o c1288o) {
        if (((Boolean) this.d.get((ec) ecVar)).booleanValue()) {
            this.d.m2142c().mo4145g(C1287n.m3035a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, c1288o, this.d));
        }
    }

    /* renamed from: b */
    private boolean m2841b() {
        if (ah.m2251a("android.permission.INTERNET", this.f)) {
            return true;
        }
        this.e.userError(m2482a(), "Unable to enable AppLovin SDK: no android.permission.INTERNET");
        return false;
    }

    /* renamed from: c */
    private void m2842c() {
        this.d.getTaskManager().m2856a(new eh(this.d), fe.MAIN, 500);
    }

    /* renamed from: d */
    private void m2843d() {
        m2844e();
        m2839a(ea.f2390N, C1288o.DIRECT);
        m2839a(ea.f2391O, C1288o.INDIRECT);
        m2845f();
    }

    /* renamed from: e */
    private void m2844e() {
        LinkedHashSet b = this.d.getZoneManager().m3072b();
        if (!b.isEmpty()) {
            this.e.mo4172d(m2482a(), "Scheduling preload(s) for " + b.size() + " zone(s)");
            Iterator it = b.iterator();
            while (it.hasNext()) {
                C1287n c1287n = (C1287n) it.next();
                if (c1287n.m3056e()) {
                    this.d.getNativeAdService().m2589a(c1287n);
                } else {
                    this.d.getAdService().preloadAds(c1287n);
                }
            }
        }
    }

    /* renamed from: f */
    private void m2845f() {
        if (((Boolean) this.d.get(ea.bt)).booleanValue()) {
            this.d.m2143d().mo4145g(C1287n.m3049j(this.d));
        }
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        this.e.mo4172d(m2482a(), "Initializing AppLovin SDK 8.0.1...");
        try {
            if (m2841b()) {
                aw a = this.d.m2139a();
                a.m2316c();
                a.m2317c("ad_imp_session");
                C1273a.m2188b(this.d);
                this.d.getFileManager().m2308d(this.f);
                this.d.getFileManager().m2307c(this.f);
                this.d.getMediationService().m2181a();
                m2843d();
                this.d.m2141b().m2419a();
                this.d.m2148i();
                m2842c();
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f);
                if (!AppLovinSdkUtils.isValidString((String) this.d.get(ef.f2440a, null, defaultSharedPreferences))) {
                    this.d.put(ef.f2440a, Boolean.toString(true), defaultSharedPreferences);
                }
                this.d.m2140a(true);
                this.d.getPersistentPostbackManager().m2608a();
                this.d.getEventService().trackEvent("landing");
            } else {
                this.d.m2140a(false);
                this.e.userError(m2482a(), "Couldn't initialize the AppLovin SDK due to missing INTERNET permission");
            }
            this.e.mo4172d(m2482a(), "AppLovin SDK 8.0.1 initialization " + (this.d.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : ParametersKeys.FAILED) + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (Throwable th) {
            Throwable th2 = th;
            this.e.mo4172d(m2482a(), "AppLovin SDK 8.0.1 initialization " + (this.d.isEnabled() ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : ParametersKeys.FAILED) + " in " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
    }
}
