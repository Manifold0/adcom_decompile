package com.applovin.impl.sdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class aw {
    /* renamed from: a */
    private final AppLovinSdkImpl f2080a;
    /* renamed from: b */
    private final Map<String, Long> f2081b = new HashMap();

    aw(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.f2080a = appLovinSdkImpl;
    }

    /* renamed from: d */
    private void m2309d() {
        try {
            this.f2080a.put(ef.f2448i, m2314b().toString());
        } catch (Throwable th) {
            this.f2080a.getLogger().mo4174e("GlobalStatsManager", "Unable to save stats", th);
        }
    }

    /* renamed from: a */
    long m2310a(String str) {
        return m2311a(str, 1);
    }

    /* renamed from: a */
    long m2311a(String str, long j) {
        long longValue;
        synchronized (this.f2081b) {
            Long l = (Long) this.f2081b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue() + j;
            this.f2081b.put(str, Long.valueOf(longValue));
        }
        m2309d();
        return longValue;
    }

    /* renamed from: a */
    void m2312a() {
        synchronized (this.f2081b) {
            this.f2081b.clear();
        }
        m2309d();
    }

    /* renamed from: b */
    long m2313b(String str) {
        long longValue;
        synchronized (this.f2081b) {
            Long l = (Long) this.f2081b.get(str);
            if (l == null) {
                l = Long.valueOf(0);
            }
            longValue = l.longValue();
        }
        return longValue;
    }

    /* renamed from: b */
    JSONObject m2314b() throws JSONException {
        JSONObject jSONObject;
        synchronized (this.f2081b) {
            jSONObject = new JSONObject();
            for (Entry entry : this.f2081b.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    void m2315b(String str, long j) {
        synchronized (this.f2081b) {
            this.f2081b.put(str, Long.valueOf(j));
        }
        m2309d();
    }

    /* renamed from: c */
    void m2316c() {
        try {
            JSONObject jSONObject = new JSONObject((String) this.f2080a.get(ef.f2448i, "{}"));
            synchronized (this.f2081b) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    try {
                        String str = (String) keys.next();
                        this.f2081b.put(str, Long.valueOf(jSONObject.getLong(str)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Throwable th) {
            this.f2080a.getLogger().mo4174e("GlobalStatsManager", "Unable to load stats", th);
        }
    }

    /* renamed from: c */
    void m2317c(String str) {
        synchronized (this.f2081b) {
            this.f2081b.remove(str);
        }
        m2309d();
    }
}
