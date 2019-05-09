// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.u;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.ads.internal.m.a;
import com.facebook.ads.internal.m.c;
import org.json.JSONObject;

public class d
{
    private static d a;
    
    static {
        d.a = new d();
    }
    
    public static d a() {
        synchronized (d.class) {
            return d.a;
        }
    }
    
    private f a(final JSONObject jsonObject) {
        int i = 0;
        final JSONObject jsonObject2 = jsonObject.getJSONArray("placements").getJSONObject(0);
        final c c = new c(com.facebook.ads.internal.m.d.a(jsonObject2.getJSONObject("definition")), jsonObject2.optString("feature_config"), jsonObject2.optString("ad_reporting_config"));
        if (jsonObject2.has("ads")) {
            for (JSONArray jsonArray = jsonObject2.getJSONArray("ads"); i < jsonArray.length(); ++i) {
                final JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                c.a(new a(jsonObject3.optString("adapter"), jsonObject3.optString("data_model_type"), jsonObject3.optJSONObject("data"), jsonObject3.optJSONArray("trackers")));
            }
        }
        return new f(c, jsonObject.optString("server_request_id"), jsonObject.optString("server_response"), jsonObject.optString("an_validation_uuid"));
    }
    
    private g b(final JSONObject jsonObject) {
        try {
            final JSONObject jsonObject2 = jsonObject.getJSONArray("placements").getJSONObject(0);
            return new g(jsonObject.optString("message", ""), jsonObject.optInt("code", 0), new c(com.facebook.ads.internal.m.d.a(jsonObject2.getJSONObject("definition")), jsonObject2.optString("feature_config"), jsonObject2.optString("ad_reporting_config")));
        }
        catch (JSONException ex) {
            return this.c(jsonObject);
        }
    }
    
    private g c(final JSONObject jsonObject) {
        return new g(jsonObject.optString("message", ""), jsonObject.optInt("code", 0), null);
    }
    
    public e a(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final JSONObject jsonObject = new JSONObject(s);
            final String optString = jsonObject.optString("type");
            switch (optString) {
                default: {
                    final JSONObject optJSONObject = jsonObject.optJSONObject("error");
                    if (optJSONObject != null) {
                        return this.c(optJSONObject);
                    }
                    break;
                }
                case "ads": {
                    return this.a(jsonObject);
                }
                case "error": {
                    return this.b(jsonObject);
                }
            }
        }
        return new e(e.a.a);
    }
}
