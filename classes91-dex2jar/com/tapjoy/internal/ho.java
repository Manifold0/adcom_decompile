// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;

public final class ho extends hl
{
    private final ed c;
    private final dx d;
    private final ek e;
    private final String f;
    
    private ho(final ed c, final dx d, final ek e, final String f) {
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public ho(final ee ee, final String s) {
        this(ee.d, ee.e, ee.f, s);
    }
    
    @Override
    public final String c() {
        return "api/v1/tokens";
    }
    
    @Override
    public final Map e() {
        final Map e = super.e();
        e.put("info", new br(gs.a(this.c)));
        e.put("app", new br(gs.a(this.d)));
        e.put("user", new br(gs.a(this.e)));
        if (!aq.a(this.f)) {
            e.put("push_token", this.f);
        }
        return e;
    }
}
