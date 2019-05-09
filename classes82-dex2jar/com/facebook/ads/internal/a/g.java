// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import org.json.JSONArray;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.List;

public class g
{
    private final String a;
    private final String b;
    private final String c;
    private final List<String> d;
    private final String e;
    private final String f;
    
    private g(final String a, final String b, final String c, final List<String> d, final String e, final String f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public static g a(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final String optString = jsonObject.optString("package");
        final String optString2 = jsonObject.optString("appsite");
        final String optString3 = jsonObject.optString("appsite_url");
        final JSONArray optJSONArray = jsonObject.optJSONArray("key_hashes");
        final ArrayList<String> list = new ArrayList<String>();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); ++i) {
                list.add(optJSONArray.optString(i));
            }
        }
        return new g(optString, optString2, optString3, list, jsonObject.optString("market_uri"), jsonObject.optString("fallback_url"));
    }
    
    public String a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
    
    public String c() {
        return this.c;
    }
}
