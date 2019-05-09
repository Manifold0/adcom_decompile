// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import org.json.JSONException;
import com.tapjoy.TapjoyLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ep extends et
{
    private static final String c;
    
    static {
        c = ep.class.getSimpleName();
    }
    
    public ep(final String s, final String s2) {
        super(s, s2, "ad");
    }
    
    public static Map a(JSONObject jsonObject) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (jsonObject != null) {
            try {
                jsonObject = jsonObject.getJSONObject("dimensions");
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    hashMap.put(s, jsonObject.get(s));
                }
            }
            catch (JSONException ex) {
                TapjoyLog.d(ep.c, "Unable to getAdUnitDimensions. Invalid params: " + ex);
            }
        }
        return hashMap;
    }
    
    public static Map b(JSONObject jsonObject) {
        final HashMap<String, Long> hashMap = new HashMap<String, Long>();
        if (jsonObject != null) {
            try {
                jsonObject = jsonObject.getJSONObject("values");
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    hashMap.put(s, jsonObject.getLong(s));
                }
            }
            catch (JSONException ex) {
                TapjoyLog.d(ep.c, "Unable to getAdUnitValues. Invalid params: " + ex);
            }
        }
        return hashMap;
    }
    
    public final fi.a a(final String s, final JSONObject jsonObject) {
        return this.a(s, a(jsonObject), b(jsonObject));
    }
    
    public final fi.a b(final String s, final JSONObject jsonObject) {
        return this.b(s, a(jsonObject), b(jsonObject));
    }
}
