package com.applovin.impl.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class ed {
    /* renamed from: a */
    private final AppLovinSdkImpl f2434a;
    /* renamed from: b */
    private final AppLovinLogger f2435b;
    /* renamed from: c */
    private final Context f2436c;
    /* renamed from: d */
    private final SharedPreferences f2437d;
    /* renamed from: e */
    private final Object[] f2438e = new Object[ea.m2658b()];

    ed(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2434a = appLovinSdkImpl;
        this.f2435b = appLovinSdkImpl.getLogger();
        this.f2436c = appLovinSdkImpl.getApplicationContext();
        this.f2437d = this.f2436c.getSharedPreferences("com.applovin.sdk.1", 0);
    }

    /* renamed from: a */
    private static Object m2663a(String str, JSONObject jSONObject, Object obj) throws JSONException {
        if (obj instanceof Boolean) {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        }
        if (obj instanceof Float) {
            return Float.valueOf((float) jSONObject.getDouble(str));
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(jSONObject.getInt(str));
        }
        if (obj instanceof Long) {
            return Long.valueOf(jSONObject.getLong(str));
        }
        if (obj instanceof String) {
            return jSONObject.getString(str);
        }
        throw new RuntimeException("SDK Error: unknown value type: " + obj.getClass());
    }

    /* renamed from: d */
    private String m2664d() {
        return "com.applovin.sdk." + gd.m2937a(this.f2434a.getSdkKey()) + ".";
    }

    /* renamed from: a */
    public ec<?> m2665a(String str, ec<?> ecVar) {
        for (ec<?> ecVar2 : ea.m2657a()) {
            if (ecVar2.m2661b().equals(str)) {
                return ecVar2;
            }
        }
        return ecVar;
    }

    /* renamed from: a */
    public <T> T m2666a(ec<T> ecVar) {
        if (ecVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        }
        T a;
        synchronized (this.f2438e) {
            try {
                Object obj = this.f2438e[ecVar.m2659a()];
                if (obj != null) {
                    a = ecVar.m2660a(obj);
                } else {
                    a = ecVar.m2662c();
                }
            } catch (Throwable th) {
                this.f2434a.getLogger().mo4173e("SettingsManager", "Unable to retrieve value for setting " + ecVar.m2661b() + "; using default...");
                a = ecVar.m2662c();
            }
        }
        return a;
    }

    /* renamed from: a */
    void m2667a() {
        if (this.f2436c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.f2435b.mo4175i("SettingsManager", "Saving settings with the application...");
        String d = m2664d();
        synchronized (this.f2438e) {
            for (ec ecVar : ea.m2657a()) {
                Object obj = this.f2438e[ecVar.m2659a()];
                if (obj != null) {
                    this.f2434a.put(d + ecVar.m2661b(), obj, this.f2437d);
                }
            }
        }
        this.f2435b.mo4172d("SettingsManager", "Settings saved with the application.");
    }

    /* renamed from: a */
    public <T> void m2668a(ec<?> ecVar, Object obj) {
        if (ecVar == null) {
            throw new IllegalArgumentException("No setting type specified");
        } else if (obj == null) {
            throw new IllegalArgumentException("No new value specified");
        } else {
            synchronized (this.f2438e) {
                this.f2438e[ecVar.m2659a()] = obj;
            }
            this.f2435b.mo4172d("SettingsManager", "Setting update: " + ecVar.m2661b() + " set to \"" + obj + "\"");
        }
    }

    /* renamed from: a */
    void m2669a(AppLovinSdkSettings appLovinSdkSettings) {
        long j = 0;
        boolean z = false;
        this.f2435b.mo4175i("SettingsManager", "Loading user-defined settings...");
        if (appLovinSdkSettings != null) {
            synchronized (this.f2438e) {
                String autoPreloadSizes;
                if (((Boolean) this.f2434a.get(ea.f2415l)).booleanValue()) {
                    this.f2438e[ea.f2415l.m2659a()] = Boolean.valueOf(appLovinSdkSettings.isVerboseLoggingEnabled());
                }
                long bannerAdRefreshSeconds = appLovinSdkSettings.getBannerAdRefreshSeconds();
                if (bannerAdRefreshSeconds >= 0) {
                    if (bannerAdRefreshSeconds > 0) {
                        j = Math.max(30, bannerAdRefreshSeconds);
                    }
                    this.f2438e[ea.f2380D.m2659a()] = Long.valueOf(j);
                    this.f2438e[ea.f2379C.m2659a()] = Boolean.valueOf(true);
                } else if (bannerAdRefreshSeconds == -1) {
                    this.f2438e[ea.f2379C.m2659a()] = Boolean.valueOf(false);
                }
                if (((Boolean) this.f2434a.get(ea.f2406d)).booleanValue()) {
                    autoPreloadSizes = appLovinSdkSettings.getAutoPreloadSizes();
                    if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                        autoPreloadSizes = "NONE";
                    }
                    if (autoPreloadSizes.equals("NONE")) {
                        this.f2438e[ea.f2390N.m2659a()] = "";
                        this.f2438e[ea.f2391O.m2659a()] = "";
                    } else {
                        this.f2438e[ea.f2390N.m2659a()] = autoPreloadSizes;
                        this.f2438e[ea.f2391O.m2659a()] = autoPreloadSizes;
                    }
                }
                if (((Boolean) this.f2434a.get(ea.f2408e)).booleanValue()) {
                    boolean z2;
                    boolean z3;
                    autoPreloadSizes = appLovinSdkSettings.getAutoPreloadTypes();
                    if (!AppLovinSdkUtils.isValidString(autoPreloadSizes)) {
                        autoPreloadSizes = "NONE";
                    }
                    if ("NONE".equals(autoPreloadSizes)) {
                        z2 = false;
                        z3 = false;
                    } else {
                        z2 = false;
                        z3 = false;
                        for (String autoPreloadSizes2 : aa.m2193a(autoPreloadSizes2)) {
                            boolean z4;
                            if (autoPreloadSizes2.equals(AppLovinAdType.REGULAR.getLabel())) {
                                z4 = z;
                                z = z2;
                                z2 = true;
                            } else if (autoPreloadSizes2.equals(AppLovinAdType.INCENTIVIZED.getLabel()) || autoPreloadSizes2.contains("INCENT") || autoPreloadSizes2.contains("REWARD")) {
                                z4 = z;
                                z2 = z3;
                                z = true;
                            } else if (autoPreloadSizes2.equals(AppLovinAdType.NATIVE.getLabel())) {
                                z4 = true;
                                z = z2;
                                z2 = z3;
                            } else {
                                z4 = z;
                                z = z2;
                                z2 = z3;
                            }
                            z3 = z2;
                            z2 = z;
                            z = z4;
                        }
                    }
                    if (!z3) {
                        this.f2438e[ea.f2390N.m2659a()] = "";
                        this.f2438e[ea.f2391O.m2659a()] = "";
                    }
                    this.f2438e[ea.f2392P.m2659a()] = Boolean.valueOf(z2);
                    this.f2438e[ea.f2393Q.m2659a()] = Boolean.valueOf(z2);
                    this.f2438e[ea.bt.m2659a()] = Boolean.valueOf(z);
                }
                if (appLovinSdkSettings instanceof bt) {
                    for (Entry entry : ((bt) appLovinSdkSettings).m2383b().entrySet()) {
                        this.f2438e[((ec) entry.getKey()).m2659a()] = entry.getValue();
                    }
                }
            }
        }
    }

    /* renamed from: a */
    void m2670a(JSONObject jSONObject) {
        this.f2435b.mo4172d("SettingsManager", "Loading settings from JSON array...");
        synchronized (this.f2438e) {
            String str = "";
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                str = (String) keys.next();
                if (str != null && str.length() > 0) {
                    try {
                        ec a = m2665a(str, null);
                        if (a != null) {
                            Object a2 = m2663a(str, jSONObject, a.m2662c());
                            this.f2438e[a.m2659a()] = a2;
                            this.f2435b.mo4172d("SettingsManager", "Setting update: " + a.m2661b() + " set to \"" + a2 + "\"");
                        } else {
                            this.f2435b.mo4178w("SettingsManager", "Unknown setting recieved: " + str);
                        }
                    } catch (Throwable e) {
                        this.f2435b.mo4174e("SettingsManager", "Unable to parse JSON settings array", e);
                    } catch (Throwable e2) {
                        this.f2435b.mo4174e("SettingsManager", "Unable to convert setting object ", e2);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public List<String> m2671b(ec<String> ecVar) {
        return aa.m2193a((String) m2666a((ec) ecVar));
    }

    /* renamed from: b */
    void m2672b() {
        if (this.f2436c == null) {
            throw new IllegalArgumentException("No context specified");
        }
        this.f2435b.mo4175i("SettingsManager", "Loading settings saved with the application...");
        String d = m2664d();
        synchronized (this.f2438e) {
            for (ec ecVar : ea.m2657a()) {
                try {
                    String str = d + ecVar.m2661b();
                    Object c = ecVar.m2662c();
                    c = this.f2434a.get(str, c, c.getClass(), this.f2437d);
                    if (c != null) {
                        this.f2438e[ecVar.m2659a()] = c;
                    } else {
                        this.f2435b.mo4173e("SettingsManager", "Unable to find value for setting: " + str);
                    }
                } catch (Throwable e) {
                    this.f2435b.mo4174e("SettingsManager", "Unable to load \"" + ecVar.m2661b() + "\"", e);
                }
            }
        }
    }

    /* renamed from: c */
    void m2673c() {
        synchronized (this.f2438e) {
            Arrays.fill(this.f2438e, null);
        }
        this.f2434a.clear(this.f2437d);
    }
}
