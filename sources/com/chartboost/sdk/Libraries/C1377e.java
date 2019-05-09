package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Tracking.C1391a;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.e */
public class C1377e {

    /* renamed from: com.chartboost.sdk.Libraries.e$a */
    public static class C1376a {
        /* renamed from: a */
        final String f2688a;
        /* renamed from: b */
        final Object f2689b;

        public C1376a(String str, Object obj) {
            this.f2688a = str;
            this.f2689b = obj;
        }
    }

    /* renamed from: a */
    public static JSONObject m3129a(JSONObject jSONObject, String... strArr) {
        for (String str : strArr) {
            if (jSONObject == null) {
                break;
            }
            jSONObject = jSONObject.optJSONObject(str);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m3131a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (Exception e) {
            C1391a.m3206a(C1377e.class, "put (" + str + ")", e);
        }
    }

    /* renamed from: a */
    public static JSONObject m3130a(C1376a... c1376aArr) {
        JSONObject jSONObject = new JSONObject();
        for (C1376a c1376a : c1376aArr) {
            C1377e.m3131a(jSONObject, c1376a.f2688a, c1376a.f2689b);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static C1376a m3128a(String str, Object obj) {
        return new C1376a(str, obj);
    }
}
