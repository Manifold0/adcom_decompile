// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import android.content.Intent;
import java.util.Iterator;
import org.json.JSONException;
import java.util.HashMap;
import org.json.JSONArray;
import com.facebook.ads.internal.w.b.k;
import org.json.JSONObject;
import com.facebook.ads.internal.o.c;
import android.os.Bundle;
import java.util.Map;
import java.util.Collection;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.a.e;

public class l implements a
{
    private final String a;
    private final String b;
    private final d c;
    private final Collection<String> d;
    private final Map<String, String> e;
    private final String f;
    private final int g;
    private final int h;
    private final int i;
    private final String j;
    
    private l(final String a, final String b, final d c, final Collection<String> d, final Map<String, String> e, final String f, final int g, final int h, final int i, final String j) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
    }
    
    public static l a(final Bundle bundle) {
        return new l(c.a(bundle.getByteArray("markup")), null, d.a, null, null, bundle.getString("request_id"), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString("ct"));
    }
    
    public static l a(JSONObject optJSONObject) {
        int int1 = 0;
        if (optJSONObject == null) {
            return null;
        }
        final String optString = optJSONObject.optString("markup");
        final String optString2 = optJSONObject.optString("activation_command");
        final String optString3 = optJSONObject.optString("request_id");
        final String a = k.a(optJSONObject, "ct");
        final d a2 = d.a(optJSONObject.optString("invalidation_behavior"));
        Collection<String> a3;
        HashMap<String, String> hashMap;
        while (true) {
            try {
                final JSONArray jsonArray = new JSONArray(optJSONObject.optString("detection_strings"));
                a3 = e.a(jsonArray);
                optJSONObject = optJSONObject.optJSONObject("metadata");
                hashMap = (HashMap<String, String>)new HashMap<Object, String>();
                if (optJSONObject != null) {
                    final Iterator keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        final String s = keys.next();
                        hashMap.put(s, optJSONObject.optString(s));
                    }
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                final JSONArray jsonArray = null;
                continue;
            }
            break;
        }
        int int2 = 1000;
        int int3;
        if (hashMap.containsKey("viewability_check_initial_delay")) {
            int3 = Integer.parseInt(hashMap.get("viewability_check_initial_delay"));
        }
        else {
            int3 = 0;
        }
        if (hashMap.containsKey("viewability_check_interval")) {
            int2 = Integer.parseInt(hashMap.get("viewability_check_interval"));
        }
        if (hashMap.containsKey("skip_after_seconds")) {
            int1 = Integer.parseInt((String)hashMap.get("skip_after_seconds"));
        }
        return new l(optString, optString2, a2, a3, hashMap, optString3, int3, int2, int1, a);
    }
    
    public static l b(final Intent intent) {
        return new l(c.a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), d.a, null, null, intent.getStringExtra("request_id"), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra("skipAfterSeconds", 0), intent.getStringExtra("ct"));
    }
    
    @Override
    public d a() {
        return this.c;
    }
    
    public void a(final Intent intent) {
        intent.putExtra("markup", com.facebook.ads.internal.o.c.a(this.a));
        intent.putExtra("activation_command", this.b);
        intent.putExtra("request_id", this.f);
        intent.putExtra("viewability_check_initial_delay", this.g);
        intent.putExtra("viewability_check_interval", this.h);
        intent.putExtra("skipAfterSeconds", this.i);
        intent.putExtra("ct", this.j);
    }
    
    @Override
    public Collection<String> b() {
        return this.d;
    }
    
    public String c() {
        return this.a;
    }
    
    public String d() {
        return this.b;
    }
    
    public Map<String, String> e() {
        return this.e;
    }
    
    public int f() {
        return this.g;
    }
    
    public int g() {
        return this.h;
    }
    
    @Override
    public String getClientToken() {
        return this.j;
    }
    
    public Bundle h() {
        final Bundle bundle = new Bundle();
        bundle.putByteArray("markup", com.facebook.ads.internal.o.c.a(this.a));
        bundle.putString("request_id", this.f);
        bundle.putInt("viewability_check_initial_delay", this.g);
        bundle.putInt("viewability_check_interval", this.h);
        bundle.putInt("skip_after_seconds", this.i);
        bundle.putString("ct", this.j);
        return bundle;
    }
}
