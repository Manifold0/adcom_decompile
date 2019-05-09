// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

import android.text.TextUtils;
import java.util.Iterator;
import com.facebook.ads.internal.w.b.k;
import java.util.HashMap;
import java.util.Map;

public class a
{
    private final String a;
    private final double b;
    private final double c;
    private final String d;
    private final Map<String, String> e;
    private final f f;
    private final g g;
    private final boolean h;
    
    public a(final String a, final double c, final String d, final Map<String, String> map, final f f, final g g, final boolean h) {
        this.a = a;
        this.b = System.currentTimeMillis() / 1000.0;
        this.c = c;
        this.d = d;
        this.f = f;
        this.g = g;
        this.h = h;
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (map != null && !map.isEmpty()) {
            hashMap.putAll((Map<?, ?>)map);
        }
        if (this.f()) {
            hashMap.put("analog", k.a(com.facebook.ads.internal.l.a.a()));
        }
        this.e = a(hashMap);
    }
    
    private static Map<String, String> a(final Map<String, String> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            final String s = entry.getKey();
            final String s2 = entry.getValue();
            if (s2 != null) {
                hashMap.put(s, s2);
            }
        }
        return hashMap;
    }
    
    public String a() {
        return this.a;
    }
    
    public double b() {
        return this.b;
    }
    
    public double c() {
        return this.c;
    }
    
    public String d() {
        return this.d;
    }
    
    public Map<String, String> e() {
        return this.e;
    }
    
    final boolean f() {
        return this.f == com.facebook.ads.internal.s.f.a;
    }
    
    final boolean g() {
        return !TextUtils.isEmpty((CharSequence)this.a);
    }
    
    public f h() {
        return this.f;
    }
    
    public g i() {
        return this.g;
    }
    
    public static class a
    {
        private String a;
        private double b;
        private String c;
        private Map<String, String> d;
        private f e;
        private g f;
        private boolean g;
        
        public a a(final double b) {
            this.b = b;
            return this;
        }
        
        public a a(final f e) {
            this.e = e;
            return this;
        }
        
        public a a(final g f) {
            this.f = f;
            return this;
        }
        
        public a a(final String a) {
            this.a = a;
            return this;
        }
        
        public a a(final Map<String, String> d) {
            this.d = d;
            return this;
        }
        
        public a a(final boolean g) {
            this.g = g;
            return this;
        }
        
        public com.facebook.ads.internal.s.a a() {
            return new com.facebook.ads.internal.s.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g);
        }
        
        public a b(final String c) {
            this.c = c;
            return this;
        }
    }
}
