package com.chartboost.sdk.Model;

import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1377e.C1376a;
import com.chartboost.sdk.Tracking.C1391a;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Model.b */
public class C1387b {
    /* renamed from: a */
    public final String f2748a;
    /* renamed from: b */
    public final String f2749b;
    /* renamed from: c */
    public final String f2750c;

    public C1387b(String str, String str2, String str3) {
        this.f2748a = str;
        this.f2749b = str2;
        this.f2750c = str3;
    }

    /* renamed from: b */
    private static Map<String, C1387b> m3167b(JSONObject jSONObject) throws JSONException {
        Map<String, C1387b> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            Iterator keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String str2 = (String) keys2.next();
                JSONObject jSONObject3 = jSONObject2.getJSONObject(str2);
                hashMap.put(str2, new C1387b(str, jSONObject3.getString("filename"), jSONObject3.getString("url")));
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static Map<String, C1387b> m3164a(JSONObject jSONObject) {
        Map<String, C1387b> hashMap = new HashMap();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("videos");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("id");
                    hashMap.put(string, new C1387b("videos", string, jSONObject2.getString("video")));
                } catch (Exception e) {
                    C1391a.m3206a(C1387b.class, "deserializeNativeVideos (file)", e);
                }
            }
        } catch (Exception e2) {
            C1391a.m3206a(C1387b.class, "deserializeNativeVideos (videos array)", e2);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static JSONObject m3166a(JSONArray jSONArray) throws JSONException {
        JSONObject a = C1377e.m3130a(new C1376a[0]);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String optString = jSONObject.optString("name");
            String optString2 = jSONObject.optString("type");
            Object optString3 = jSONObject.optString("value");
            String optString4 = jSONObject.optString("param");
            if (!optString2.equals("param") && optString4.isEmpty()) {
                jSONObject = a.optJSONObject(optString2);
                if (jSONObject == null) {
                    jSONObject = C1377e.m3130a(new C1376a[0]);
                    a.put(optString2, jSONObject);
                }
                jSONObject.put(optString2.equals(String.HTML) ? "body" : optString, C1377e.m3130a(C1377e.m3128a("filename", (Object) optString), C1377e.m3128a("url", optString3)));
            }
        }
        return a;
    }

    /* renamed from: a */
    public static Map<String, C1387b> m3165a(JSONObject jSONObject, int i) {
        Map<String, C1387b> hashMap = new HashMap();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("cache_assets");
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONArray optJSONArray;
                int i2;
                if (str.equals("templates")) {
                    optJSONArray = jSONObject2.optJSONArray("templates");
                    if (optJSONArray != null) {
                        int min = Math.min(i, optJSONArray.length());
                        for (i2 = 0; i2 < min; i2++) {
                            for (Entry value : C1387b.m3167b(C1387b.m3166a(optJSONArray.getJSONObject(i2).getJSONArray(MessengerShareContentUtility.ELEMENTS))).entrySet()) {
                                C1387b c1387b = (C1387b) value.getValue();
                                hashMap.put(c1387b.f2749b, c1387b);
                            }
                        }
                    }
                } else {
                    optJSONArray = jSONObject2.getJSONArray(str);
                    for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject jSONObject3 = optJSONArray.getJSONObject(i2);
                        String string = jSONObject3.getString("name");
                        hashMap.put(string, new C1387b(str, string, jSONObject3.getString("value")));
                    }
                }
            }
        } catch (Exception e) {
            C1391a.m3206a(C1387b.class, "v2PrefetchToAssets", e);
        }
        return hashMap;
    }

    /* renamed from: a */
    public File m3168a(File file) {
        return new File(file, this.f2748a + "/" + this.f2749b);
    }
}
