package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinLogger;
import org.json.JSONObject;

abstract class fs<T> extends dx implements af<T> {
    /* renamed from: a */
    private final String f2271a;
    /* renamed from: b */
    private final T f2272b;
    /* renamed from: h */
    protected ae f2273h;
    /* renamed from: i */
    private final af<T> f2274i;
    /* renamed from: j */
    private String f2275j;
    /* renamed from: k */
    private String f2276k;
    /* renamed from: l */
    private JSONObject f2277l;
    /* renamed from: m */
    private int f2278m;
    /* renamed from: n */
    private boolean f2279n = true;
    /* renamed from: o */
    private int f2280o = 1;
    /* renamed from: p */
    private long f2281p;
    /* renamed from: q */
    private ec<String> f2282q = null;
    /* renamed from: r */
    private ec<String> f2283r = null;

    fs(String str, T t, String str2, AppLovinSdkImpl appLovinSdkImpl) {
        super(str2, appLovinSdkImpl);
        this.f2271a = str;
        this.f2272b = t;
        this.f2278m = ((Integer) appLovinSdkImpl.get(ea.f2426w)).intValue();
        this.f2281p = ((Long) appLovinSdkImpl.get(ea.f2423t)).longValue();
        this.f2273h = new ae();
        this.f2274i = new ft(this, str2, appLovinSdkImpl);
    }

    /* renamed from: c */
    private <T> void m2489c(ec<T> ecVar) {
        if (ecVar != null) {
            ed settingsManager = this.d.getSettingsManager();
            settingsManager.m2668a((ec) ecVar, ecVar.m2662c());
            settingsManager.m2667a();
        }
    }

    /* renamed from: a */
    public void mo4128a(int i) {
    }

    /* renamed from: a */
    public void m2494a(long j) {
        this.f2281p = j;
    }

    /* renamed from: a */
    public void m2495a(ec<String> ecVar) {
        this.f2282q = ecVar;
    }

    /* renamed from: a */
    public void mo4129a(T t, int i) {
    }

    /* renamed from: a */
    public void m2497a(String str) {
        this.f2275j = str;
    }

    /* renamed from: a */
    public void m2498a(JSONObject jSONObject) {
        this.f2277l = jSONObject;
    }

    /* renamed from: a */
    public void m2499a(boolean z) {
        this.f2279n = z;
    }

    /* renamed from: b */
    public void m2500b(int i) {
        this.f2278m = i;
    }

    /* renamed from: b */
    public void m2501b(ec<String> ecVar) {
        this.f2283r = ecVar;
    }

    /* renamed from: b */
    public void m2502b(String str) {
        this.f2276k = str;
    }

    /* renamed from: c */
    public void m2503c(int i) {
        this.f2280o = i;
    }

    public void run() {
        ad connectionManager = this.d.getConnectionManager();
        if (!this.d.m2144e() && !this.d.isEnabled()) {
            this.e.mo4173e(m2482a(), "AppLovin SDK is disabled: please check your connection");
            this.e.mo4173e(AppLovinLogger.SDK_TAG, "AppLovin SDK is disabled: please check your connection");
            mo4128a(-22);
        } else if (TextUtils.isEmpty(this.f2275j) || this.f2275j.length() < 4) {
            this.e.mo4173e(m2482a(), "Task has an invalid or null request endpoint.");
            mo4128a(-900);
        } else {
            String str = this.f2271a;
            if (TextUtils.isEmpty(str)) {
                str = this.f2277l == null ? "GET" : "POST";
            }
            connectionManager.m2223a(this.f2275j, str, this.f2278m, this.f2277l, this.f2272b, this.f2279n, this.f2273h, this.f2274i);
        }
    }
}
