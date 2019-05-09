package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bu {
    /* renamed from: a */
    public static float m2384a(JSONObject jSONObject, String str, float f, AppLovinSdk appLovinSdk) {
        if (jSONObject == null || !jSONObject.has(str)) {
            return f;
        }
        try {
            double d = jSONObject.getDouble(str);
            return (-3.4028234663852886E38d >= d || d >= 3.4028234663852886E38d) ? f : (float) d;
        } catch (JSONException e) {
            if (appLovinSdk == null) {
                return f;
            }
            appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve float property for key = " + str);
            return f;
        }
    }

    /* renamed from: a */
    public static int m2385a(JSONObject jSONObject, String str, int i, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                i = jSONObject.getInt(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve int property for key = " + str);
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public static long m2386a(JSONObject jSONObject, String str, long j, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                j = jSONObject.getLong(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve int property for key = " + str);
                }
            }
        }
        return j;
    }

    /* renamed from: a */
    public static Boolean m2387a(JSONObject jSONObject, String str, Boolean bool, AppLovinSdk appLovinSdk) {
        boolean z = true;
        if (jSONObject == null || !jSONObject.has(str)) {
            return bool;
        }
        try {
            return Boolean.valueOf(jSONObject.getBoolean(str));
        } catch (JSONException e) {
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().mo4178w("JsonUtils", "Unable to parse boolean for key = " + str + "... Attempting to parse it as an int");
            }
            if (m2385a(jSONObject, str, bool.booleanValue() ? 1 : 0, appLovinSdk) <= 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    }

    /* renamed from: a */
    private static Object m2388a(Object obj) throws JSONException {
        return obj == JSONObject.NULL ? null : obj instanceof JSONObject ? m2391a((JSONObject) obj) : obj instanceof JSONArray ? m2390a((JSONArray) obj) : obj;
    }

    /* renamed from: a */
    public static String m2389a(JSONObject jSONObject, String str, String str2, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                str2 = jSONObject.getString(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve string property for key = " + str);
                }
            }
        }
        return str2;
    }

    /* renamed from: a */
    public static List m2390a(JSONArray jSONArray) throws JSONException {
        List arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(m2388a(jSONArray.get(i)));
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Map<String, String> m2391a(JSONObject jSONObject) throws JSONException {
        Map<String, String> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            hashMap.put(str, m2388a(jSONObject.get(str)).toString());
        }
        return hashMap;
    }

    /* renamed from: a */
    public static JSONArray m2392a(JSONObject jSONObject, String str, JSONArray jSONArray, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve JSON array for key = " + str);
                }
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    static JSONObject m2393a(Map<String, ?> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            jSONObject.put((String) entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static JSONObject m2394a(JSONArray jSONArray, int i, JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        if (jSONArray != null && i < jSONArray.length()) {
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve JSON object from array for index = " + i);
                }
            }
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static JSONObject m2395a(JSONObject jSONObject, String str, JSONObject jSONObject2, AppLovinSdk appLovinSdk) {
        if (jSONObject != null && jSONObject.has(str)) {
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to retrieve JSON property for key = " + str);
                }
            }
        }
        return jSONObject2;
    }

    /* renamed from: a */
    public static boolean m2396a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    /* renamed from: b */
    public static void m2397b(JSONObject jSONObject, String str, long j, AppLovinSdk appLovinSdk) {
        if (jSONObject != null) {
            try {
                jSONObject.put(str, j);
            } catch (JSONException e) {
                if (appLovinSdk != null) {
                    appLovinSdk.getLogger().mo4173e("JsonUtils", "Failed to put long property for key = " + str);
                }
            }
        }
    }
}
