package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import org.json.JSONObject;

public final class aq extends C1227q {
    /* renamed from: e */
    private AppLovinAd f2071e;
    /* renamed from: f */
    private final C1287n f2072f;

    public aq(C1287n c1287n, AppLovinSdkImpl appLovinSdkImpl) {
        super(new JSONObject(), new JSONObject(), appLovinSdkImpl);
        this.f2072f = c1287n;
    }

    /* renamed from: c */
    private AppLovinAd m2271c() {
        return (AppLovinAd) this.c.m2142c().mo4141c(this.f2072f);
    }

    /* renamed from: d */
    private String m2272d() {
        C1287n t = mo3997t();
        return (t == null || t.m3064m()) ? null : t.m3051a();
    }

    /* renamed from: a */
    AppLovinAd mo4000a() {
        return this.f2071e;
    }

    /* renamed from: a */
    void m2274a(AppLovinAd appLovinAd) {
        this.f2071e = appLovinAd;
    }

    /* renamed from: b */
    AppLovinAd m2275b() {
        return this.f2071e != null ? this.f2071e : m2271c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppLovinAd b = m2275b();
        return b != null ? b.equals(obj) : super.equals(obj);
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ boolean mo3992f() {
        return super.mo3992f();
    }

    public long getAdIdNumber() {
        long j = 0;
        try {
            AppLovinAd b = m2275b();
            if (b != null) {
                j = b.getAdIdNumber();
            }
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad id number", th);
        }
        return j;
    }

    public /* bridge */ /* synthetic */ String getAdValue(String str) {
        return super.getAdValue(str);
    }

    public AppLovinAdSize getSize() {
        AppLovinAdSize appLovinAdSize = AppLovinAdSize.INTERSTITIAL;
        try {
            appLovinAdSize = mo3997t().m3053b();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad size", th);
        }
        return appLovinAdSize;
    }

    public AppLovinAdType getType() {
        AppLovinAdType appLovinAdType = AppLovinAdType.REGULAR;
        try {
            appLovinAdType = mo3997t().m3054c();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to retrieve ad type", th);
        }
        return appLovinAdType;
    }

    public String getZoneId() {
        String str = null;
        try {
            if (!this.f2072f.m3064m()) {
                str = this.f2072f.m3051a();
            }
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return zone id", th);
        }
        return str;
    }

    public int hashCode() {
        AppLovinAd b = m2275b();
        return b != null ? b.hashCode() : super.hashCode();
    }

    public boolean isVideoAd() {
        boolean z = false;
        try {
            AppLovinAd b = m2275b();
            if (b != null) {
                z = b.isVideoAd();
            }
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return whether ad is video ad", th);
        }
        return z;
    }

    /* renamed from: l */
    public /* bridge */ /* synthetic */ long mo3994l() {
        return super.mo3994l();
    }

    /* renamed from: m */
    public C1288o mo3995m() {
        C1288o c1288o = C1288o.DIRECT;
        try {
            c1288o = mo3997t().m3055d();
        } catch (Throwable th) {
            Log.e("AppLovinAd", "Failed to return ad mediation type", th);
        }
        return c1288o;
    }

    /* renamed from: n */
    public /* bridge */ /* synthetic */ String mo3996n() {
        return super.mo3996n();
    }

    /* renamed from: t */
    public C1287n mo3997t() {
        C1227q c1227q = (C1227q) m2275b();
        return c1227q != null ? c1227q.mo3997t() : this.f2072f;
    }

    public String toString() {
        return "[AppLovinAd #" + getAdIdNumber() + " adType=" + getType() + ", adSize=" + getSize() + ", zoneId=" + m2272d() + RequestParameters.RIGHT_BRACKETS;
    }
}
