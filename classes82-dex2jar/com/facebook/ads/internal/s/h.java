// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

import java.util.HashMap;
import java.util.Map;

public class h
{
    private boolean a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    
    public Map<String, String> a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("is_timeout", Boolean.toString(this.a));
        hashMap.put("ad_count", Integer.toString(this.b));
        hashMap.put("default_ad_index", Integer.toString(this.c));
        hashMap.put("selected_ad_index", Integer.toString(this.d));
        hashMap.put("elapsed_time_from_timer_ms", Integer.toString(this.e));
        hashMap.put("countdown_time_ms", Integer.toString(this.f));
        return hashMap;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public void a(final boolean a) {
        this.a = a;
    }
    
    public void b(final int c) {
        this.c = c;
    }
    
    public void c(final int d) {
        this.d = d;
    }
    
    public void d(final int e) {
        this.e = e;
    }
    
    public void e(final int f) {
        this.f = f;
    }
}
