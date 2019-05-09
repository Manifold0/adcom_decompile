// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class eh extends dl
{
    public static final dn c;
    public static final Long d;
    public static final Long e;
    public final String f;
    public final Long g;
    public final Long h;
    
    static {
        c = new b();
        d = 0L;
        e = 0L;
    }
    
    public eh(final String s, final Long n) {
        this(s, n, null, hx.b);
    }
    
    public eh(final String f, final Long g, final Long h, final hx hx) {
        super(eh.c, hx);
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    public final a b() {
        final a a = new a();
        a.c = this.f;
        a.d = this.g;
        a.e = this.h;
        ((dl.a)a).a(this.a());
        return a;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof eh)) {
                return false;
            }
            final eh eh = (eh)o;
            if (!this.a().equals(eh.a()) || !this.f.equals(eh.f) || !this.g.equals(eh.g) || !ds.a(this.h, eh.h)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int b;
        if ((b = super.b) == 0) {
            final int hashCode = this.a().hashCode();
            final int hashCode2 = this.f.hashCode();
            final int hashCode3 = this.g.hashCode();
            int hashCode4;
            if (this.h != null) {
                hashCode4 = this.h.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            b = hashCode4 + ((hashCode * 37 + hashCode2) * 37 + hashCode3) * 37;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(", id=").append(this.f);
        sb.append(", received=").append(this.g);
        if (this.h != null) {
            sb.append(", clicked=").append(this.h);
        }
        return sb.replace(0, 2, "Push{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public Long d;
        public Long e;
        
        public final eh b() {
            if (this.c == null || this.d == null) {
                throw ds.a(this.c, "id", this.d, "received");
            }
            return new eh(this.c, this.d, this.e, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, eh.class);
        }
    }
}
