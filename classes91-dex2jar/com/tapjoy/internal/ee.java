// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ee extends dl
{
    public static final dn c;
    public final ed d;
    public final dx e;
    public final ek f;
    
    static {
        c = new b();
    }
    
    public ee(final ed ed, final dx dx, final ek ek) {
        this(ed, dx, ek, hx.b);
    }
    
    public ee(final ed d, final dx e, final ek f, final hx hx) {
        super(ee.c, hx);
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ee)) {
                return false;
            }
            final ee ee = (ee)o;
            if (!this.a().equals(ee.a()) || !ds.a(this.d, ee.d) || !ds.a(this.e, ee.e) || !ds.a(this.f, ee.f)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public final int hashCode() {
        int hashCode = 0;
        int b;
        if ((b = super.b) == 0) {
            final int hashCode2 = this.a().hashCode();
            int hashCode3;
            if (this.d != null) {
                hashCode3 = this.d.hashCode();
            }
            else {
                hashCode3 = 0;
            }
            int hashCode4;
            if (this.e != null) {
                hashCode4 = this.e.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            if (this.f != null) {
                hashCode = this.f.hashCode();
            }
            b = (hashCode4 + (hashCode3 + hashCode2 * 37) * 37) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.d != null) {
            sb.append(", info=").append(this.d);
        }
        if (this.e != null) {
            sb.append(", app=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", user=").append(this.f);
        }
        return sb.replace(0, 2, "InfoSet{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public ed c;
        public dx d;
        public ek e;
        
        public final ee b() {
            return new ee(this.c, this.d, this.e, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ee.class);
        }
    }
}
