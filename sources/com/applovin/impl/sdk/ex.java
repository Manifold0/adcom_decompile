package com.applovin.impl.sdk;

import android.graphics.Point;
import android.text.TextUtils;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.mediation.AppLovinMediationAdapterInfo;
import com.applovin.mediation.AppLovinMediationAdapterStats;
import com.applovin.mediation.AppLovinMediationAdapterStatus;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import com.kongregate.android.internal.sdk.C0498e;
import com.tapjoy.TapjoyConstants;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.hockeyapp.android.UpdateActivity;
import org.json.JSONObject;

class ex extends dx {
    /* renamed from: a */
    private final C1287n f2483a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f2484b;
    /* renamed from: h */
    private boolean f2485h = false;

    ex(C1287n c1287n, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("FetchNextAd", appLovinSdkImpl);
        this.f2483a = c1287n;
        this.f2484b = appLovinAdLoadListener;
    }

    /* renamed from: a */
    private String m2804a(AppLovinMediationAdapterInfo appLovinMediationAdapterInfo) {
        return appLovinMediationAdapterInfo == null ? null : appLovinMediationAdapterInfo.getVersion();
    }

    /* renamed from: a */
    private void m2805a(aw awVar) {
        if (System.currentTimeMillis() - awVar.m2313b("ad_session_start") > TimeUnit.MINUTES.toMillis((long) ((Integer) this.d.get(ea.f2429z)).intValue())) {
            awVar.m2315b("ad_session_start", System.currentTimeMillis());
            awVar.m2317c("ad_imp_session");
        }
    }

    /* renamed from: b */
    private String m2808b() {
        String str = "custom_size,launch_app";
        return (ab.m2203c() && ab.m2198a(AppLovinInterstitialActivity.class, this.f)) ? str + ",video" : str;
    }

    /* renamed from: b */
    private void m2809b(int i) {
        this.e.mo4173e(this.c, "Unable to fetch " + this.f2483a + " ad: server returned " + i);
        try {
            mo4168a(i);
        } catch (Throwable th) {
            this.e.userError(this.c, "Unable process a failure to recieve an ad", th);
        }
        ag.m2245b(i, this.d);
    }

    /* renamed from: b */
    private void m2810b(JSONObject jSONObject) {
        ag.m2240a(jSONObject, this.d);
        this.d.m2148i();
        dx a = mo4167a(jSONObject);
        if (((Boolean) this.d.get(ea.cC)).booleanValue()) {
            this.d.getTaskManager().m2854a(a);
        } else {
            this.d.getTaskManager().m2855a(a, fe.MAIN);
        }
        ag.m2246b(jSONObject, this.d);
    }

    /* renamed from: f */
    private void m2811f(Map<String, String> map) {
        if (this.d.getSettings().isTestAdsEnabled()) {
            map.put("test_ads", Boolean.toString(true));
        }
        map.put("api_did", this.d.get(ea.f2409f));
        map.put("sdk_key", this.d.getSdkKey());
        map.put("sdk_version", AppLovinSdk.VERSION);
        map.put(TapjoyConstants.TJC_APP_VERSION_NAME, gd.m2953c(this.d.getDataCollector().m2269d().f2033b));
        map.put(C0498e.f496m, Integer.toString(89));
        String str = (String) this.d.get(ea.f2385I);
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("plugin_version", str);
        }
        str = this.d.getMediationProvider();
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("mediation_provider", gd.m2953c(str));
        }
        map.put("accept", m2808b());
        map.put("v1", Boolean.toString(ab.m2199a("android.permission.WRITE_EXTERNAL_STORAGE", this.f)));
        map.put("v2", Boolean.toString(ab.m2198a(AppLovinInterstitialActivity.class, this.f)));
        map.put("v3", Boolean.toString(ab.m2197a(this.f)));
        map.put("v4", Boolean.toString(ab.m2201b(this.f)));
        map.put("preloading", String.valueOf(this.f2485h));
        map.put("format", UpdateActivity.EXTRA_JSON);
        ah dataCollector = this.d.getDataCollector();
        map.put("ia", Long.toString(dataCollector.m2269d().f2036e));
        map.put("installer_name", dataCollector.m2269d().f2035d);
    }

    /* renamed from: g */
    private void m2812g(Map<String, String> map) {
        if (((Boolean) this.d.get(ea.f2394R)).booleanValue()) {
            aw a = this.d.m2139a();
            map.put("li", String.valueOf(a.m2313b("ad_imp")));
            map.put("si", String.valueOf(a.m2313b("ad_imp_session")));
        }
        map.put("sc", this.d.get(ea.f2377A));
    }

    /* renamed from: h */
    private void m2813h(Map<String, String> map) {
        if (this.d.isSessionTrackingEnabled()) {
            map.put("pnr", Boolean.toString(this.d.m2149j()));
        }
    }

    /* renamed from: i */
    private void m2814i(Map<String, String> map) {
        Map a = C1273a.m2185a(this.d);
        if (a.isEmpty()) {
            try {
                m2815j(a);
                C1273a.m2187a(a, this.d);
            } catch (Throwable e) {
                this.e.mo4174e(this.c, "Unable to populate device information", e);
            }
        }
        try {
            m2816k(a);
        } catch (Throwable e2) {
            this.e.mo4174e(this.c, "Unable to populate ephemeral device information", e2);
        }
        map.putAll(a);
        map.put("network", ag.m2234a(this.d));
        m2818m(map);
        map.put("vz", gd.m2938a(this.d.getApplicationContext().getPackageName(), this.d));
    }

    /* renamed from: j */
    private void m2815j(Map<String, String> map) {
        am a = this.d.getDataCollector().m2265a();
        map.put("brand", gd.m2953c(a.f2042d));
        map.put("brand_name", gd.m2953c(a.f2043e));
        map.put("hardware", gd.m2953c(a.f2044f));
        map.put("carrier", gd.m2953c(a.f2048j));
        map.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, gd.m2953c(a.f2047i));
        map.put("locale", gd.m2953c(a.f2049k.toString()));
        map.put("model", gd.m2953c(a.f2039a));
        map.put("os", gd.m2953c(a.f2040b));
        map.put(TapjoyConstants.TJC_PLATFORM, gd.m2953c(a.f2041c));
        map.put("revision", gd.m2953c(a.f2045g));
        map.put("orientation_lock", a.f2050l);
        map.put("tz_offset", String.valueOf(a.f2053o));
        map.put("wvvc", String.valueOf(a.f2054p));
        map.put("adns", String.valueOf(a.f2051m));
        map.put("adnsd", String.valueOf(a.f2052n));
        map.put("sim", a.f2059u ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put("gy", String.valueOf(a.f2060v));
        m2817l(map);
    }

    /* renamed from: k */
    private void m2816k(Map<String, String> map) {
        am c = this.d.getDataCollector().m2268c();
        al alVar = c.f2056r;
        if (alVar != null) {
            map.put("act", String.valueOf(alVar.f2037a));
            map.put("acm", String.valueOf(alVar.f2038b));
        }
        map.put("adr", c.f2055q ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put("volume", String.valueOf(c.f2057s));
        String str = c.f2058t;
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("ua", gd.m2953c(str));
        }
        Boolean bool = c.f2061w;
        if (bool != null) {
            map.put("huc", bool.toString());
        }
        bool = c.f2062x;
        if (bool != null) {
            map.put("aru", bool.toString());
        }
        m2817l(map);
        m2819n(map);
    }

    /* renamed from: l */
    private void m2817l(Map<String, String> map) {
        Point c = ab.m2202c(this.d.getApplicationContext());
        map.put("dx", Integer.toString(c.x));
        map.put("dy", Integer.toString(c.y));
    }

    /* renamed from: m */
    private void m2818m(Map<String, String> map) {
        aj e = this.d.getDataCollector().m2270e();
        String str = e.f2031b;
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(e.f2030a));
    }

    /* renamed from: n */
    private void m2819n(Map<String, String> map) {
        Collection<AppLovinMediationAdapterInfo> adapterInfo = this.d.getMediationService().getAdapterInfo();
        if (!(adapterInfo == null || adapterInfo.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (AppLovinMediationAdapterInfo appLovinMediationAdapterInfo : adapterInfo) {
                if (appLovinMediationAdapterInfo.getStatus() == AppLovinMediationAdapterStatus.READY) {
                    stringBuilder.append(appLovinMediationAdapterInfo.getName());
                    Object a = m2804a(appLovinMediationAdapterInfo);
                    if (!TextUtils.isEmpty(a)) {
                        stringBuilder.append(":");
                        stringBuilder.append(a);
                    }
                    stringBuilder.append(",");
                }
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
            if (stringBuilder.length() > 0) {
                map.put("aa", stringBuilder.toString());
            }
        }
        AppLovinMediationAdapterStats lastAdapterStats = this.d.getMediationService().getLastAdapterStats();
        if (lastAdapterStats != null) {
            map.put("lman", lastAdapterStats.getAdapterName());
            map.put("lmat", String.valueOf(lastAdapterStats.getLastAdLoadMillis()));
        }
    }

    /* renamed from: a */
    protected dx mo4167a(JSONObject jSONObject) {
        return new fj(jSONObject, this.f2483a, this.f2484b, this.d);
    }

    /* renamed from: a */
    protected void mo4168a(int i) {
        if (this.f2484b == null) {
            return;
        }
        if (this.f2484b instanceof at) {
            ((at) this.f2484b).mo4133a(this.f2483a, i);
        } else {
            this.f2484b.failedToReceiveAd(i);
        }
    }

    /* renamed from: a */
    void mo4166a(Map<String, String> map) {
        map.put("zone_id", gd.m2953c(this.f2483a.m3051a()));
    }

    /* renamed from: a */
    void m2823a(boolean z) {
        this.f2485h = z;
    }

    /* renamed from: b */
    protected String mo4169b(Map<String, String> map) {
        return ag.m2243b("3.0/ad", map, this.d);
    }

    /* renamed from: c */
    protected String mo4170c(Map<String, String> map) {
        return ag.m2248d("3.0/ad", map, this.d);
    }

    /* renamed from: d */
    protected void m2826d(Map<String, String> map) {
        m2814i(map);
        m2812g(map);
        m2811f(map);
        mo4171e(map);
        mo4166a((Map) map);
        m2813h(map);
    }

    /* renamed from: e */
    void mo4171e(Map<String, String> map) {
    }

    public void run() {
        if (this.f2485h) {
            this.e.mo4172d(this.c, "Preloading next ad of zone: " + this.f2483a);
        } else {
            this.e.mo4172d(this.c, "Fetching next ad of zone: " + this.f2483a);
        }
        aw a = this.d.m2139a();
        a.m2310a("ad_req");
        m2805a(a);
        try {
            fs eyVar = new ey(this, "GET", new JSONObject(), "RepeatFetchNextAd", this.d);
            Map hashMap = new HashMap();
            m2826d(hashMap);
            eyVar.m2497a(mo4169b(hashMap));
            eyVar.m2502b(mo4170c(hashMap));
            eyVar.m2500b(((Integer) this.d.get(ea.f2427x)).intValue());
            eyVar.m2503c(((Integer) this.d.get(ea.f2414k)).intValue());
            eyVar.m2495a(ea.f2417n);
            eyVar.m2501b(ea.f2421r);
            eyVar.run();
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Unable to fetch ad " + this.f2483a, th);
            m2809b(0);
        }
    }
}
