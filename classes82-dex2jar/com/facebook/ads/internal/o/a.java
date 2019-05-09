// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.o;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import android.support.annotation.Nullable;
import java.util.Map;

public class a
{
    public static String a;
    private String b;
    private Map<String, Object> c;
    private int d;
    private String e;
    
    static {
        com.facebook.ads.internal.o.a.a = null;
    }
    
    public a(final String b, final Map<String, Object> c, final int d, final String e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public static a a(@Nullable final Throwable t, @Nullable String a) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (t != null) {
            hashMap.put("ex", t.getClass().getSimpleName());
            hashMap.put("ex_msg", t.getMessage());
        }
        final int n = (int)(System.currentTimeMillis() / 1000L);
        if (a == null) {
            a = a.a;
        }
        return new a("error", (Map<String, Object>)hashMap, n, a);
    }
    
    public JSONObject a() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", (Object)this.b);
            jsonObject.put("data", (Object)new JSONObject((Map)this.c));
            jsonObject.put("time", this.d);
            jsonObject.put("request_id", (Object)this.e);
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
}
