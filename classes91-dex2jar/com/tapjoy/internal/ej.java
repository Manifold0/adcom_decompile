// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class ej extends dl
{
    public static final dn c;
    public final List d;
    
    static {
        c = new b();
    }
    
    public ej(final List list) {
        this(list, hx.b);
    }
    
    public ej(final List list, final hx hx) {
        super(ej.c, hx);
        this.d = ds.a("elements", list);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ej)) {
                return false;
            }
            final ej ej = (ej)o;
            if (!this.a().equals(ej.a()) || !this.d.equals(ej.d)) {
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
            sb.append(", elements=").append(this.d);
        }
        return sb.replace(0, 2, "StringList{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public List c;
        
        public a() {
            this.c = ds.a();
        }
        
        public final ej b() {
            return new ej(this.c, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ej.class);
        }
    }
}
