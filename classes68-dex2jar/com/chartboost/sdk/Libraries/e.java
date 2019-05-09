// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import org.json.JSONException;
import com.chartboost.sdk.Tracking.a;
import org.json.JSONObject;

public class e
{
    public static a a(final String s, final Object o) {
        return new a(s, o);
    }
    
    public static JSONObject a(JSONObject optJSONObject, final String... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s = array[i];
            if (optJSONObject == null) {
                break;
            }
            optJSONObject = optJSONObject.optJSONObject(s);
        }
        return optJSONObject;
    }
    
    public static JSONObject a(final a... array) {
        final JSONObject jsonObject = new JSONObject();
        for (int length = array.length, i = 0; i < length; ++i) {
            final a a = array[i];
            a(jsonObject, a.a, a.b);
        }
        return jsonObject;
    }
    
    public static void a(final JSONObject jsonObject, final String s, final Object o) {
        try {
            jsonObject.put(s, o);
        }
        catch (JSONException ex) {
            com.chartboost.sdk.Tracking.a.a(e.class, "put (" + s + ")", (Exception)ex);
        }
    }
    
    public static class a
    {
        final String a;
        final Object b;
        
        public a(final String a, final Object b) {
            this.a = a;
            this.b = b;
        }
    }
}
