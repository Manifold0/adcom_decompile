package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.e */
class C1278e {
    /* renamed from: a */
    private final String f2372a;
    /* renamed from: b */
    private final String f2373b;
    /* renamed from: c */
    private final String f2374c;
    /* renamed from: d */
    private final long f2375d;
    /* renamed from: e */
    private final Map<String, Long> f2376e;

    private C1278e(String str, String str2, String str3) {
        this.f2376e = new HashMap();
        this.f2372a = str;
        this.f2373b = str2;
        this.f2374c = str3;
        this.f2375d = System.currentTimeMillis();
    }

    /* renamed from: a */
    private JSONObject m2653a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pk", this.f2372a);
        jSONObject.put("ts", this.f2375d);
        if (!TextUtils.isEmpty(this.f2373b)) {
            jSONObject.put("sk1", this.f2373b);
        }
        if (!TextUtils.isEmpty(this.f2374c)) {
            jSONObject.put("sk2", this.f2374c);
        }
        for (Entry entry : this.f2376e.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    /* renamed from: b */
    private String m2654b() throws JSONException {
        return m2653a().toString();
    }

    /* renamed from: a */
    void m2655a(String str, long j) {
        this.f2376e.put(str, Long.valueOf(j));
    }

    public String toString() {
        return "[AdEventStats pk: " + this.f2372a + ", size: " + this.f2376e.size() + RequestParameters.RIGHT_BRACKETS;
    }
}
