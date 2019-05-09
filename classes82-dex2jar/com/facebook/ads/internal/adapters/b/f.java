// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import java.util.Iterator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;
import java.util.Map;
import java.util.List;

public class f extends a
{
    private g a;
    private c b;
    private List<q> c;
    private String d;
    private String e;
    private String f;
    
    private f(final g a, final Map<String, String> map, final c b, final List<q> c) {
        this.a = a;
        this.b = b;
        this.c = c;
        String d;
        if (map.containsKey("background_color")) {
            d = map.get("background_color");
        }
        else {
            d = "#FF23272F";
        }
        this.d = d;
        String f;
        if (map.containsKey("timer_text_color")) {
            f = map.get("timer_text_color");
        }
        else {
            f = "#FFFFFF";
        }
        this.f = f;
        String e;
        if (map.containsKey("title_text_color")) {
            e = map.get("title_text_color");
        }
        else {
            e = "#FFFFFF";
        }
        this.e = e;
    }
    
    public static f a(final JSONObject jsonObject) {
        final g a = g.a(jsonObject.optJSONObject("translations"));
        final JSONObject optJSONObject = jsonObject.optJSONObject("layout");
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (optJSONObject != null) {
            a(optJSONObject, hashMap, "background_color");
            a(optJSONObject, hashMap, "timer_text_color");
            a(optJSONObject, hashMap, "title_text_color");
        }
        final c a2 = c.a(jsonObject.optJSONObject("ad_config"));
        final JSONArray optJSONArray = jsonObject.optJSONArray("choosable_ads");
        final ArrayList<q> list = new ArrayList<q>();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); ++i) {
                final q a3 = q.a(optJSONArray.optJSONObject(i));
                a3.a(true);
                list.add(a3);
            }
        }
        return new f(a, hashMap, a2, list);
    }
    
    private static void a(final JSONObject jsonObject, final HashMap<String, String> hashMap, final String s) {
        final String optString = jsonObject.optString(s);
        if (!TextUtils.isEmpty((CharSequence)optString)) {
            hashMap.put(s, optString);
        }
    }
    
    @Nullable
    @Override
    public String a() {
        if (this.c.isEmpty()) {
            return null;
        }
        return this.c.get(0).a();
    }
    
    @Override
    public void b(final String s) {
        super.b(s);
        final Iterator<q> iterator = this.c.iterator();
        while (iterator.hasNext()) {
            iterator.next().b(s);
        }
    }
    
    public g e() {
        return this.a;
    }
    
    public String f() {
        return this.d;
    }
    
    public String g() {
        return this.f;
    }
    
    public String h() {
        return this.e;
    }
    
    public c i() {
        return this.b;
    }
    
    public List<q> j() {
        return this.c;
    }
}
