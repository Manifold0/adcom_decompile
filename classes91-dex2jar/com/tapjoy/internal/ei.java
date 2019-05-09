// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class ei extends dl
{
    public static final dn c;
    public final List d;
    
    static {
        c = new b();
    }
    
    public ei(final List list) {
        this(list, hx.b);
    }
    
    public ei(final List list, final hx hx) {
        super(ei.c, hx);
        this.d = ds.a("pushes", list);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ei)) {
                return false;
            }
            final ei ei = (ei)o;
            if (!this.a().equals(ei.a()) || !this.d.equals(ei.d)) {
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
            sb.append(", pushes=").append(this.d);
        }
        return sb.replace(0, 2, "PushList{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public List c;
        
        public a() {
            this.c = ds.a();
        }
        
        public final ei b() {
            return new ei(this.c, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ei.class);
        }
    }
}
