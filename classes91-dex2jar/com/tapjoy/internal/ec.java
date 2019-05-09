// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ec extends dl
{
    public static final dn c;
    public static final Long d;
    public final String e;
    public final Long f;
    
    static {
        c = new b();
        d = 0L;
    }
    
    public ec(final String s, final Long n) {
        this(s, n, hx.b);
    }
    
    public ec(final String e, final Long f, final hx hx) {
        super(ec.c, hx);
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ec)) {
                return false;
            }
            final ec ec = (ec)o;
            if (!this.a().equals(ec.a()) || !this.e.equals(ec.e) || !this.f.equals(ec.f)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int b;
        if ((b = super.b) == 0) {
            b = (this.a().hashCode() * 37 + this.e.hashCode()) * 37 + this.f.hashCode();
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(", name=").append(this.e);
        sb.append(", value=").append(this.f);
        return sb.replace(0, 2, "EventValue{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public Long d;
        
        public final ec b() {
            if (this.c == null || this.d == null) {
                throw ds.a(this.c, "name", this.d, "value");
            }
            return new ec(this.c, this.d, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ec.class);
        }
    }
}
