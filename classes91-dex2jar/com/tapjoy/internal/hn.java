// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;

public final class hn extends hl
{
    private final dz.a c;
    private eb d;
    
    public hn() {
        this.c = new dz.a();
        this.d = null;
    }
    
    public final boolean a(final dy dy) {
        if (this.d == null) {
            this.d = dy.n;
        }
        else if (dy.n != this.d) {
            return false;
        }
        this.c.c.add(dy);
        return true;
    }
    
    @Override
    public final String c() {
        if (this.d == eb.USAGES) {
            return "api/v1/usages";
        }
        return "api/v1/cevs";
    }
    
    @Override
    public final Map e() {
        final Map e = super.e();
        e.put("events", new br(gs.a(this.c.b())));
        return e;
    }
    
    public final int g() {
        return this.c.c.size();
    }
}
