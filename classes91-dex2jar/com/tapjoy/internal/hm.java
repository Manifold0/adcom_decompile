// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;
import java.util.List;
import android.content.Context;

public final class hm extends hl
{
    public final String c;
    public boolean d;
    private final gc e;
    private final ed f;
    private final dx g;
    private final ek h;
    private Context i;
    
    public hm(final gc e, final ed f, final dx g, final ek h, final String c, final Context i) {
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.c = c;
        this.d = false;
        this.i = i;
    }
    
    @Override
    public final String c() {
        return "placement";
    }
    
    @Override
    public final Map e() {
        final Map e = super.e();
        e.put("info", new br(gs.a(this.f)));
        e.put("app", new br(gs.a(this.g)));
        e.put("user", new br(gs.a(this.h)));
        e.put("placement", this.c);
        return e;
    }
    
    public static final class a
    {
        public gj a;
        public final List b;
        
        public a(final gj a, final List b) {
            this.a = a;
            this.b = b;
        }
    }
}
