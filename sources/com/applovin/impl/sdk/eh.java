package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.internal.NativeProtocol;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

class eh extends dx {
    eh(AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }

    /* renamed from: a */
    private void m2743a(JSONObject jSONObject) {
        try {
            JSONObject a = ag.m2238a(jSONObject);
            ed settingsManager = this.d.getSettingsManager();
            settingsManager.m2668a(ea.f2409f, a.getString("device_id"));
            settingsManager.m2668a(ea.f2411h, a.getString("device_token"));
            settingsManager.m2668a(ea.f2410g, a.getString("publisher_id"));
            settingsManager.m2667a();
            ag.m2240a(a, this.d);
            this.d.m2148i();
            ag.m2246b(a, this.d);
            if (a.has("adserver_parameters")) {
                settingsManager.m2668a(ea.f2378B, a.getJSONObject("adserver_parameters").toString());
            }
            String a2 = bu.m2389a(a, "latest_version", "", this.d);
            if (m2744a(a2)) {
                this.d.getLogger().userError("ALSdk", "Please integrate the latest version of the AppLovin SDK (" + a2 + "). Not doing so can negatively impact your eCPMs!");
            }
        } catch (Throwable th) {
            this.e.mo4174e(this.c, "Unable to parse API response", th);
        }
    }

    /* renamed from: a */
    private boolean m2744a(String str) {
        try {
            if (AppLovinSdkUtils.isValidString(str) && !AppLovinSdk.VERSION.equals(str)) {
                String str2 = "\\.";
                List a = aa.m2194a(str, "\\.");
                List a2 = aa.m2194a(AppLovinSdk.VERSION, "\\.");
                if (a.size() == 3 && a2.size() == 3) {
                    for (int i = 0; i < 3; i++) {
                        int parseInt = Integer.parseInt((String) a2.get(i));
                        int parseInt2 = Integer.parseInt((String) a.get(i));
                        if (parseInt < parseInt2) {
                            return true;
                        }
                        if (parseInt > parseInt2) {
                            return false;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            this.d.getLogger().mo4174e(this.c, "Encountered exception while checking if current version is outdated", th);
        }
        return false;
    }

    /* renamed from: b */
    private void m2745b(JSONObject jSONObject) throws JSONException {
        ah dataCollector = this.d.getDataCollector();
        ak d = dataCollector.m2269d();
        am a = dataCollector.m2265a();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("model", a.f2039a);
        jSONObject2.put("os", a.f2040b);
        jSONObject2.put("brand", a.f2042d);
        jSONObject2.put("brand_name", a.f2043e);
        jSONObject2.put("hardware", a.f2044f);
        jSONObject2.put("sdk_version", a.f2046h);
        jSONObject2.put("revision", a.f2045g);
        jSONObject2.put("adns", (double) a.f2051m);
        jSONObject2.put("adnsd", a.f2052n);
        jSONObject2.put("gy", a.f2060v);
        jSONObject2.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, a.f2047i);
        jSONObject2.put("carrier", a.f2048j);
        jSONObject2.put("orientation_lock", a.f2050l);
        jSONObject2.put("tz_offset", a.f2053o);
        jSONObject2.put("adr", a.f2055q ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        jSONObject2.put("wvvc", a.f2054p);
        jSONObject2.put("volume", a.f2057s);
        jSONObject2.put("type", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
        jSONObject2.put("sim", a.f2059u ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        m2748e(jSONObject2);
        Boolean bool = a.f2061w;
        if (bool != null) {
            jSONObject2.put("huc", bool.toString());
        }
        bool = a.f2062x;
        if (bool != null) {
            jSONObject2.put("aru", bool.toString());
        }
        al alVar = a.f2056r;
        if (alVar != null) {
            jSONObject2.put("act", alVar.f2037a);
            jSONObject2.put("acm", alVar.f2038b);
        }
        String str = a.f2058t;
        if (AppLovinSdkUtils.isValidString(str)) {
            jSONObject2.put("ua", gd.m2953c(str));
        }
        Locale locale = a.f2049k;
        if (locale != null) {
            jSONObject2.put("locale", gd.m2953c(locale.toString()));
        }
        jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("package_name", d.f2034c);
        jSONObject3.put("installer_name", d.f2035d);
        jSONObject3.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, d.f2032a);
        jSONObject3.put(TapjoyConstants.TJC_APP_VERSION_NAME, d.f2033b);
        jSONObject3.put("installed_at", d.f2036e);
        jSONObject3.put("applovin_sdk_version", AppLovinSdk.VERSION);
        jSONObject3.put("ic", this.d.isInitializedInMainActivity());
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f);
        str = (String) this.d.get(ef.f2440a, null, defaultSharedPreferences);
        if (AppLovinSdkUtils.isValidString(str)) {
            jSONObject3.put("first_install", str);
            if (str.equalsIgnoreCase(Boolean.toString(true))) {
                this.d.put(ef.f2440a, Boolean.toString(false), defaultSharedPreferences);
            }
        }
        str = (String) this.d.get(ea.f2385I);
        if (str != null && str.length() > 0) {
            jSONObject3.put("plugin_version", str);
        }
        jSONObject.put("app_info", jSONObject3);
    }

    /* renamed from: c */
    private void m2746c(JSONObject jSONObject) throws JSONException {
        if (((Boolean) this.d.get(ea.f2394R)).booleanValue()) {
            jSONObject.put("stats", this.d.m2139a().m2314b());
        }
    }

    /* renamed from: d */
    private void m2747d(JSONObject jSONObject) {
        fs eiVar = new ei(this, "POST", new JSONObject(), "Repeat" + this.c, this.d);
        eiVar.m2497a(ag.m2237a("device", null, this.d));
        eiVar.m2502b(ag.m2247c("device", null, this.d));
        eiVar.m2498a(jSONObject);
        eiVar.m2503c(((Integer) this.d.get(ea.f2412i)).intValue());
        eiVar.m2495a(ea.f2416m);
        eiVar.m2501b(ea.f2420q);
        eiVar.run();
    }

    /* renamed from: e */
    private void m2748e(JSONObject jSONObject) {
        try {
            aj e = this.d.getDataCollector().m2270e();
            String str = e.f2031b;
            if (AppLovinSdkUtils.isValidString(str)) {
                jSONObject.put("idfa", str);
            }
            jSONObject.put("dnt", Boolean.toString(e.f2030a));
        } catch (Throwable th) {
            this.e.mo4174e(m2482a(), "Failed to populate advertising info", th);
        }
    }

    public void run() {
        try {
            this.e.mo4175i(this.c, "Submitting user data...");
            JSONObject jSONObject = new JSONObject();
            m2745b(jSONObject);
            m2746c(jSONObject);
            jSONObject.put("sc", this.d.get(ea.f2377A));
            m2747d(jSONObject);
        } catch (Throwable e) {
            this.e.mo4174e(this.c, "Unable to build JSON message with collected data", e);
        }
    }
}
