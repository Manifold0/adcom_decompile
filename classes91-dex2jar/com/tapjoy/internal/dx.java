// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class dx extends dl
{
    public static final dn c;
    public static final Integer d;
    public final String e;
    public final Integer f;
    public final String g;
    public final String h;
    public final String i;
    
    static {
        c = new b();
        d = 0;
    }
    
    public dx(final String e, final Integer f, final String g, final String h, final String i, final hx hx) {
        super(dx.c, hx);
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof dx)) {
                return false;
            }
            final dx dx = (dx)o;
            if (!this.a().equals(dx.a()) || !ds.a(this.e, dx.e) || !ds.a(this.f, dx.f) || !ds.a(this.g, dx.g) || !ds.a(this.h, dx.h) || !ds.a(this.i, dx.i)) {
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
            if (this.e != null) {
                hashCode3 = this.e.hashCode();
            }
            else {
                hashCode3 = 0;
            }
            int hashCode4;
            if (this.f != null) {
                hashCode4 = this.f.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            int hashCode5;
            if (this.g != null) {
                hashCode5 = this.g.hashCode();
            }
            else {
                hashCode5 = 0;
            }
            int hashCode6;
            if (this.h != null) {
                hashCode6 = this.h.hashCode();
            }
            else {
                hashCode6 = 0;
            }
            if (this.i != null) {
                hashCode = this.i.hashCode();
            }
            b = (hashCode6 + (hashCode5 + (hashCode4 + (hashCode3 + hashCode2 * 37) * 37) * 37) * 37) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.e != null) {
            sb.append(", pkgVer=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", pkgRev=").append(this.f);
        }
        if (this.g != null) {
            sb.append(", dataVer=").append(this.g);
        }
        if (this.h != null) {
            sb.append(", installer=").append(this.h);
        }
        if (this.i != null) {
            sb.append(", store=").append(this.i);
        }
        return sb.replace(0, 2, "App{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public Integer d;
        public String e;
        public String f;
        public String g;
        
        public final dx b() {
            return new dx(this.c, this.d, this.e, this.f, this.g, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, dx.class);
        }
    }
}
