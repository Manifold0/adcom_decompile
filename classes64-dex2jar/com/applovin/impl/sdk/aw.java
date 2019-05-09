// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

class aw
{
    private final AppLovinSdkImpl a;
    private final Map<String, Long> b;
    
    aw(final AppLovinSdkImpl a) {
        this.b = new HashMap<String, Long>();
        if (a == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
    }
    
    private void d() {
        try {
            this.a.put(ef.i, this.b().toString());
        }
        catch (Throwable t) {
            this.a.getLogger().e("GlobalStatsManager", "Unable to save stats", t);
        }
    }
    
    long a(final String s) {
        return this.a(s, 1L);
    }
    
    long a(final String s, long n) {
        synchronized (this.b) {
            Long value;
            if ((value = this.b.get(s)) == null) {
                value = 0L;
            }
            n += value;
            this.b.put(s, n);
            // monitorexit(this.b)
            this.d();
            return n;
        }
    }
    
    void a() {
        synchronized (this.b) {
            this.b.clear();
            // monitorexit(this.b)
            this.d();
        }
    }
    
    long b(final String s) {
        synchronized (this.b) {
            Long value;
            if ((value = this.b.get(s)) == null) {
                value = 0L;
            }
            return value;
        }
    }
    
    JSONObject b() throws JSONException {
        synchronized (this.b) {
            final JSONObject jsonObject = new JSONObject();
            for (final Map.Entry<String, Long> entry : this.b.entrySet()) {
                jsonObject.put((String)entry.getKey(), (Object)entry.getValue());
            }
        }
        // monitorexit(map)
        return;
    }
    
    void b(final String s, final long n) {
        synchronized (this.b) {
            this.b.put(s, n);
            // monitorexit(this.b)
            this.d();
        }
    }
    
    void c() {
        final String s = this.a.get(ef.i, "{}");
        try {
            final JSONObject jsonObject = new JSONObject(s);
            synchronized (this.b) {
                final Iterator keys = jsonObject.keys();
                while (keys.hasNext()) {
                    try {
                        final String s2 = keys.next();
                        this.b.put(s2, jsonObject.getLong(s2));
                    }
                    catch (JSONException ex) {}
                }
            }
        }
        catch (Throwable t) {
            this.a.getLogger().e("GlobalStatsManager", "Unable to load stats", t);
        }
    }
    
    void c(final String s) {
        synchronized (this.b) {
            this.b.remove(s);
            // monitorexit(this.b)
            this.d();
        }
    }
}
