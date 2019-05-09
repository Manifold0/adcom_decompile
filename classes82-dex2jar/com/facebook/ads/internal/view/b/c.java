// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import java.util.HashMap;
import java.util.Map;

public class c
{
    public final String a;
    public final long b;
    public final long c;
    public final long d;
    public final long e;
    public final long f;
    public final long g;
    public final long h;
    
    private c(final String a, final long b, final long c, final long d, final long e, final long f, final long g, final long h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public Map<String, String> a() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(7);
        hashMap.put("initial_url", this.a);
        hashMap.put("handler_time_ms", String.valueOf(this.b));
        hashMap.put("load_start_ms", String.valueOf(this.c));
        hashMap.put("response_end_ms", String.valueOf(this.d));
        hashMap.put("dom_content_loaded_ms", String.valueOf(this.e));
        hashMap.put("scroll_ready_ms", String.valueOf(this.f));
        hashMap.put("load_finish_ms", String.valueOf(this.g));
        hashMap.put("session_finish_ms", String.valueOf(this.h));
        return hashMap;
    }
    
    public static class a
    {
        private final String a;
        private long b;
        private long c;
        private long d;
        private long e;
        private long f;
        private long g;
        private long h;
        
        public a(final String a) {
            this.b = -1L;
            this.c = -1L;
            this.d = -1L;
            this.e = -1L;
            this.f = -1L;
            this.g = -1L;
            this.h = -1L;
            this.a = a;
        }
        
        public a a(final long b) {
            this.b = b;
            return this;
        }
        
        public c a() {
            return new c(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, null);
        }
        
        public a b(final long c) {
            this.c = c;
            return this;
        }
        
        public a c(final long d) {
            this.d = d;
            return this;
        }
        
        public a d(final long e) {
            this.e = e;
            return this;
        }
        
        public a e(final long f) {
            this.f = f;
            return this;
        }
        
        public a f(final long g) {
            this.g = g;
            return this;
        }
        
        public a g(final long h) {
            this.h = h;
            return this;
        }
    }
}
