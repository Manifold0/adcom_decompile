// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import com.kongregate.android.internal.util.j;
import java.util.HashMap;
import java.util.Map;

public class e
{
    public static final String a = "add";
    public static final String b = "max";
    public static final String c = "min";
    public static final String d = "replace";
    private String e;
    private Map<String, Object> f;
    
    public e(final String e) {
        this.e = e;
        this.f = new HashMap<String, Object>();
    }
    
    public Map<String, Object> a() {
        j.a("EventTracker: reseting event: " + this.e);
        final HashMap<String, Object> hashMap = new HashMap<String, Object>(this.f);
        this.f.clear();
        return hashMap;
    }
    
    public void a(final String s, final Number n, final String s2) {
        j.a("EventTracker updating numeric field: " + s + " : " + n + " : " + s2);
        final Number value = this.f.get(s);
        Number value2;
        if (value instanceof Number) {
            value2 = value;
        }
        else {
            value2 = 0;
        }
        if ("add".equals(s2)) {
            if (value instanceof Double || n instanceof Double) {
                this.f.put(s, value2.doubleValue() + n.doubleValue());
                return;
            }
            this.f.put(s, value2.longValue() + n.longValue());
        }
        else if ("max".equals(s2)) {
            if (n.doubleValue() > value2.doubleValue()) {
                this.f.put(s, n);
                return;
            }
            this.f.put(s, value2);
        }
        else if ("min".equals(s2)) {
            if (n.doubleValue() < value2.doubleValue()) {
                this.f.put(s, n);
                return;
            }
            this.f.put(s, value2);
        }
        else {
            if ("replace".equals(s2)) {
                this.f.put(s, n);
                return;
            }
            j.c("unrecognized operator for number: " + s2);
        }
    }
    
    public void a(final String s, final Object o) {
        j.a("EventTracker replace field: " + s + " : " + o);
        this.f.put(s, o);
    }
    
    public String b() {
        return this.e;
    }
    
    public void b(final String s, final Object o) {
        if (!this.f.containsKey(s)) {
            j.a("EventTracker setting field: " + s + " : " + o);
            this.f.put(s, o);
            return;
        }
        j.a("EventTracker field already set, not updating: " + s);
    }
}
