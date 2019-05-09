package com.facebook.ads.internal.p028u;

import android.text.TextUtils;
import com.facebook.ads.internal.p028u.C2132e.C2131a;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p043m.C2047d;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.u.d */
public class C2130d {
    /* renamed from: a */
    private static C2130d f4936a = new C2130d();

    /* renamed from: a */
    public static synchronized C2130d m5425a() {
        C2130d c2130d;
        synchronized (C2130d.class) {
            c2130d = f4936a;
        }
        return c2130d;
    }

    /* renamed from: a */
    private C2133f m5426a(JSONObject jSONObject) {
        int i = 0;
        JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
        C2046c c2046c = new C2046c(C2047d.m4979a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config"));
        if (jSONObject2.has("ads")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("ads");
            while (i < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                c2046c.m4971a(new C2044a(jSONObject3.optString("adapter"), jSONObject3.optString("data_model_type"), jSONObject3.optJSONObject("data"), jSONObject3.optJSONArray("trackers")));
                i++;
            }
        }
        return new C2133f(c2046c, jSONObject.optString("server_request_id"), jSONObject.optString("server_response"), jSONObject.optString("an_validation_uuid"));
    }

    /* renamed from: b */
    private C2134g m5427b(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONArray("placements").getJSONObject(0);
            return new C2134g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), new C2046c(C2047d.m4979a(jSONObject2.getJSONObject("definition")), jSONObject2.optString("feature_config"), jSONObject2.optString("ad_reporting_config")));
        } catch (JSONException e) {
            return m5428c(jSONObject);
        }
    }

    /* renamed from: c */
    private C2134g m5428c(JSONObject jSONObject) {
        return new C2134g(jSONObject.optString("message", ""), jSONObject.optInt("code", 0), null);
    }

    /* renamed from: a */
    public C2132e m5429a(String str) {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("type");
            Object obj = -1;
            switch (optString.hashCode()) {
                case 96432:
                    if (optString.equals("ads")) {
                        obj = null;
                        break;
                    }
                    break;
                case 96784904:
                    if (optString.equals("error")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return m5426a(jSONObject);
                case 1:
                    return m5427b(jSONObject);
                default:
                    JSONObject optJSONObject = jSONObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return m5428c(optJSONObject);
                    }
                    break;
            }
        }
        return new C2132e(C2131a.UNKNOWN);
    }
}
