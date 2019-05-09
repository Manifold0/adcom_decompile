// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class k
{
    public static String a(Map<String, String> iterator) {
        final JSONObject jsonObject = new JSONObject();
        if (iterator != null) {
            iterator = ((Map<Object, Object>)iterator).entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<Object, Object> entry = iterator.next();
                try {
                    jsonObject.put((String)entry.getKey(), entry.getValue());
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return jsonObject.toString();
    }
    
    public static String a(final JSONObject jsonObject, final String s) {
        final String optString = jsonObject.optString(s, (String)null);
        if ("null".equals(optString)) {
            return null;
        }
        return optString;
    }
}
