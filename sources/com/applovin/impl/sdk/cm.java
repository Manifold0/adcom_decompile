package com.applovin.impl.sdk;

import android.content.pm.ApplicationInfo;
import com.applovin.mediation.AppLovinMediationAdapterConfig;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

class cm implements AppLovinMediationAdapterConfig {
    /* renamed from: a */
    private final AppLovinSdkImpl f2218a;
    /* renamed from: b */
    private final AppLovinLogger f2219b;
    /* renamed from: c */
    private final String f2220c;
    /* renamed from: d */
    private final String f2221d;
    /* renamed from: e */
    private final Object f2222e = new Object();
    /* renamed from: f */
    private Map<String, String> f2223f;
    /* renamed from: g */
    private final Set<String> f2224g = new HashSet(5);
    /* renamed from: h */
    private final Map<String, String> f2225h = new HashMap(5);

    cm(String str, AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (str == null) {
            throw new IllegalArgumentException("No classname specified");
        } else {
            this.f2218a = appLovinSdkImpl;
            this.f2219b = appLovinSdkImpl.getLogger();
            this.f2220c = str.toLowerCase();
            this.f2221d = "applovin.mediation." + str + ":config";
        }
    }

    /* renamed from: a */
    private Map<String, String> m2434a() {
        Throwable th;
        Map<String, String> a;
        try {
            String str = (String) this.f2218a.get(new ef(this.f2221d, String.class));
            if (str == null || str.isEmpty()) {
                this.f2219b.mo4172d("MediationAdapterConfigWrapper", "Last known config for '" + this.f2220c + "' is missing");
                return null;
            }
            a = bu.m2391a(new JSONObject(str));
            try {
                this.f2219b.mo4172d("MediationAdapterConfigWrapper", "Last known config for '" + this.f2220c + "' is: " + a);
                return a;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = null;
            th = th4;
            this.f2219b.mo4174e("MediationAdapterConfigWrapper", "Unable to load the last known configuration for " + this.f2220c, th);
            return a;
        }
    }

    /* renamed from: a */
    void m2435a(Map<String, String> map) {
        synchronized (this.f2222e) {
            if (map != null) {
                if (!map.isEmpty()) {
                    this.f2223f = map;
                    m2436b(map);
                }
            }
            this.f2223f = m2434a();
        }
    }

    /* renamed from: b */
    void m2436b(Map<String, String> map) {
        if (map != null) {
            try {
                String jSONObject = bu.m2393a((Map) map).toString();
                this.f2218a.put(new ef(this.f2221d, String.class), jSONObject);
                synchronized (this.f2222e) {
                    this.f2223f = map;
                }
            } catch (Throwable th) {
                this.f2219b.mo4174e("MediationAdapterConfigWrapper", "Unable to save the last known configuration for " + this.f2220c, th);
            }
        }
    }

    public Boolean getBoolean(String str, Boolean bool) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        return string != null ? Boolean.valueOf(Boolean.parseBoolean(string)) : bool;
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, Boolean.valueOf(false)).booleanValue();
    }

    public int getInt(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        if (string != null && gd.m2956d(string)) {
            try {
                i = Integer.parseInt(string);
            } catch (Throwable e) {
                this.f2219b.mo4179w("MediationAdapterConfigWrapper", "Unable to parse int for " + str, e);
            }
        }
        return i;
    }

    public long getLong(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        String string = getString(str, null);
        if (string != null) {
            try {
                string = string.replace("L", "").trim();
                if (gd.m2956d(string)) {
                    j = Long.parseLong(string);
                }
            } catch (Throwable e) {
                this.f2219b.mo4179w("MediationAdapterConfigWrapper", "Unable to parse long for " + str, e);
            }
        }
        return j;
    }

    public String getString(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("No key specified");
        }
        synchronized (this.f2222e) {
            String str3;
            if (this.f2223f == null || !this.f2223f.containsKey(str)) {
                String str4 = "applovin.mediation." + this.f2220c + ":" + str;
                if (this.f2225h.containsKey(str4)) {
                    str3 = (String) this.f2225h.get(str4);
                    return str3;
                } else if (this.f2224g.contains(str4)) {
                    return str2;
                } else {
                    try {
                        ApplicationInfo applicationInfo = this.f2218a.getApplicationContext().getPackageManager().getApplicationInfo(this.f2218a.getApplicationContext().getPackageName(), 128);
                        if (applicationInfo.metaData != null) {
                            str3 = String.valueOf(applicationInfo.metaData.get(str4));
                            if (str3 != null) {
                                this.f2225h.put(str4, str3);
                                return str3;
                            }
                            this.f2224g.add(str4);
                        }
                    } catch (Throwable th) {
                        this.f2219b.mo4174e("MediationAdapterConfigWrapper", "Unable to load " + str + "from AndroidManifest.xml", th);
                    }
                    return str2;
                }
            }
            str3 = (String) this.f2223f.get(str);
            return str3;
        }
    }
}
