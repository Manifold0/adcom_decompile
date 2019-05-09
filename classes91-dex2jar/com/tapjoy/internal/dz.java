// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class dz extends dl
{
    public static final dn c;
    public final List d;
    
    static {
        c = new b();
    }
    
    public dz(final List list, final hx hx) {
        super(dz.c, hx);
        this.d = ds.a("events", list);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof dz)) {
                return false;
            }
            final dz dz = (dz)o;
            if (!this.a().equals(dz.a()) || !this.d.equals(dz.d)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int b;
        if ((b = super.b) == 0) {
            b = this.a().hashCode() * 37 + this.d.hashCode();
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (!this.d.isEmpty()) {
            sb.append(", events=").append(this.d);
        }
        return sb.replace(0, 2, "EventBatch{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public List c;
        
        public a() {
            this.c = ds.a();
        }
        
        public final dz b() {
            return new dz(this.c, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, dz.class);
        }
    }
}
