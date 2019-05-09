// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;

public class bu
{
    public static float a(final JSONObject jsonObject, final String s, final float n, final AppLovinSdk appLovinSdk) {
        float n2 = n;
        if (jsonObject == null) {
            return n2;
        }
        n2 = n;
        if (!jsonObject.has(s)) {
            return n2;
        }
        try {
            final double double1 = jsonObject.getDouble(s);
            n2 = n;
            if (-3.4028234663852886E38 < double1) {
                n2 = n;
                if (double1 < 3.4028234663852886E38) {
                    n2 = (float)double1;
                }
            }
            return n2;
        }
        catch (JSONException ex) {
            n2 = n;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve float property for key = " + s);
                return n;
            }
            return n2;
        }
    }
    
    public static int a(final JSONObject jsonObject, final String s, final int n, final AppLovinSdk appLovinSdk) {
        int int1 = n;
        if (jsonObject == null) {
            return int1;
        }
        int1 = n;
        if (!jsonObject.has(s)) {
            return int1;
        }
        try {
            int1 = jsonObject.getInt(s);
            return int1;
        }
        catch (JSONException ex) {
            int1 = n;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve int property for key = " + s);
                return n;
            }
            return int1;
        }
    }
    
    public static long a(final JSONObject jsonObject, final String s, final long n, final AppLovinSdk appLovinSdk) {
        long long1 = n;
        if (jsonObject == null) {
            return long1;
        }
        long1 = n;
        if (!jsonObject.has(s)) {
            return long1;
        }
        try {
            long1 = jsonObject.getLong(s);
            return long1;
        }
        catch (JSONException ex) {
            long1 = n;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve int property for key = " + s);
                return n;
            }
            return long1;
        }
    }
    
    public static Boolean a(final JSONObject jsonObject, final String s, final Boolean b, final AppLovinSdk appLovinSdk) {
        boolean b2 = true;
        Boolean value = b;
        if (jsonObject == null) {
            return value;
        }
        value = b;
        if (!jsonObject.has(s)) {
            return value;
        }
        try {
            value = jsonObject.getBoolean(s);
            return value;
        }
        catch (JSONException ex) {
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().w("JsonUtils", "Unable to parse boolean for key = " + s + "... Attempting to parse it as an int");
            }
            int n;
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (a(jsonObject, s, n, appLovinSdk) <= 0) {
                b2 = false;
            }
            return b2;
        }
    }
    
    private static Object a(final Object o) throws JSONException {
        Object o2;
        if (o == JSONObject.NULL) {
            o2 = null;
        }
        else {
            if (o instanceof JSONObject) {
                return a((JSONObject)o);
            }
            o2 = o;
            if (o instanceof JSONArray) {
                return a((JSONArray)o);
            }
        }
        return o2;
    }
    
    public static String a(final JSONObject jsonObject, final String s, final String s2, final AppLovinSdk appLovinSdk) {
        String string = s2;
        if (jsonObject == null) {
            return string;
        }
        string = s2;
        if (!jsonObject.has(s)) {
            return string;
        }
        try {
            string = jsonObject.getString(s);
            return string;
        }
        catch (JSONException ex) {
            string = s2;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve string property for key = " + s);
                return s2;
            }
            return string;
        }
    }
    
    public static List a(final JSONArray jsonArray) throws JSONException {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(a(jsonArray.get(i)));
        }
        return list;
    }
    
    public static Map<String, String> a(final JSONObject jsonObject) throws JSONException {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            hashMap.put(s, a(jsonObject.get(s)).toString());
        }
        return hashMap;
    }
    
    public static JSONArray a(final JSONObject jsonObject, final String s, final JSONArray jsonArray, final AppLovinSdk appLovinSdk) {
        JSONArray jsonArray2 = jsonArray;
        if (jsonObject == null) {
            return jsonArray2;
        }
        jsonArray2 = jsonArray;
        if (!jsonObject.has(s)) {
            return jsonArray2;
        }
        try {
            jsonArray2 = jsonObject.getJSONArray(s);
            return jsonArray2;
        }
        catch (JSONException ex) {
            jsonArray2 = jsonArray;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve JSON array for key = " + s);
                return jsonArray;
            }
            return jsonArray2;
        }
    }
    
    static JSONObject a(final Map<String, ?> map) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final Map.Entry<String, V> entry : map.entrySet()) {
            jsonObject.put((String)entry.getKey(), (Object)entry.getValue());
        }
        return jsonObject;
    }
    
    public static JSONObject a(final JSONArray jsonArray, final int n, final JSONObject jsonObject, final AppLovinSdk appLovinSdk) {
        JSONObject jsonObject2 = jsonObject;
        if (jsonArray == null) {
            return jsonObject2;
        }
        jsonObject2 = jsonObject;
        if (n >= jsonArray.length()) {
            return jsonObject2;
        }
        try {
            jsonObject2 = jsonArray.getJSONObject(n);
            return jsonObject2;
        }
        catch (JSONException ex) {
            jsonObject2 = jsonObject;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve JSON object from array for index = " + n);
                return jsonObject;
            }
            return jsonObject2;
        }
    }
    
    public static JSONObject a(final JSONObject jsonObject, final String s, final JSONObject jsonObject2, final AppLovinSdk appLovinSdk) {
        JSONObject jsonObject3 = jsonObject2;
        if (jsonObject == null) {
            return jsonObject3;
        }
        jsonObject3 = jsonObject2;
        if (!jsonObject.has(s)) {
            return jsonObject3;
        }
        try {
            jsonObject3 = jsonObject.getJSONObject(s);
            return jsonObject3;
        }
        catch (JSONException ex) {
            jsonObject3 = jsonObject2;
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to retrieve JSON property for key = " + s);
                return jsonObject2;
            }
            return jsonObject3;
        }
    }
    
    public static boolean a(final JSONObject jsonObject, final String s) {
        return jsonObject != null && jsonObject.has(s);
    }
    
    public static void b(final JSONObject jsonObject, final String s, final long n, final AppLovinSdk appLovinSdk) {
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(s, n);
        }
        catch (JSONException ex) {
            if (appLovinSdk != null) {
                appLovinSdk.getLogger().e("JsonUtils", "Failed to put long property for key = " + s);
            }
        }
    }
}
