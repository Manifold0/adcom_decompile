// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.f;

import java.util.Collection;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class b
{
    private final List<String> a;
    private final List<String> b;
    
    public b() {
        this.a = new ArrayList<String>();
        this.b = new ArrayList<String>();
    }
    
    public void a() {
        this.a.add("start");
    }
    
    public void a(final int n) {
        this.b.add(String.valueOf(n));
    }
    
    public void a(final a a) {
        this.a.add(a.a() + "_end");
    }
    
    public void a(final a a, final int n) {
        this.a.add(a.a() + "_" + n);
    }
    
    public void b() {
        this.a.add("why_am_i_seeing_this");
    }
    
    public void c() {
        this.a.add("manage_ad_preferences");
    }
    
    public Map<String, String> d() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("user_journey", new JSONArray((Collection)this.a).toString());
        hashMap.put("options_selected", new JSONArray((Collection)this.b).toString());
        return hashMap;
    }
    
    public boolean e() {
        return !this.a.isEmpty() || !this.b.isEmpty();
    }
    
    public void f() {
        this.a.clear();
        this.b.clear();
    }
    
    public enum a
    {
        a("report"), 
        b("hide"), 
        c("none");
        
        private final String d;
        
        private a(final String d) {
            this.d = d;
        }
        
        String a() {
            return this.d;
        }
    }
}
