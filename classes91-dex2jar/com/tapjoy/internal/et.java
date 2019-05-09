// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import com.tapjoy.TapjoyLog;
import java.util.HashMap;
import java.util.Map;

public abstract class et
{
    private static final String c;
    public final Map a;
    public final Map b;
    
    static {
        c = et.class.getSimpleName();
    }
    
    protected et(final String s, final String s2, final String s3) {
        this.a = new HashMap();
        this.b = new HashMap();
        this.a.put("placement", s);
        this.a.put("placement_type", s2);
        this.a.put("content_type", s3);
    }
    
    public final fi.a a() {
        return this.a("Content.rendered", null, null);
    }
    
    protected final fi.a a(final String s, final Map map, final Map map2) {
        final fi.a b = fi.e(s).a().a(this.a).a(map).b(map2);
        this.b.put(s, b);
        return b;
    }
    
    public final void a(final String s, final Object o) {
        this.a.put(s, o);
    }
    
    public final fi.a b() {
        return this.b("Content.rendered", null, null);
    }
    
    protected final fi.a b(final String s, final Map map, final Map map2) {
        fi.a a;
        if (!aq.a(s)) {
            a = this.b.remove(s);
        }
        else {
            a = null;
        }
        if (a == null) {
            TapjoyLog.e(et.c, "Error when calling endTrackingEvent -- " + s + " tracking has not been started.");
            return a;
        }
        a.a(this.a).a(map).b(map2).b().c();
        return a;
    }
}
