// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ef extends dl
{
    public static final dn c;
    public final String d;
    public final String e;
    public final String f;
    
    static {
        c = new b();
    }
    
    public ef(final String s, final String s2, final String s3) {
        this(s, s2, s3, hx.b);
    }
    
    public ef(final String d, final String e, final String f, final hx hx) {
        super(ef.c, hx);
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ef)) {
                return false;
            }
            final ef ef = (ef)o;
            if (!this.a().equals(ef.a()) || !ds.a(this.d, ef.d) || !ds.a(this.e, ef.e) || !ds.a(this.f, ef.f)) {
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
            sb.append(", fq7Change=").append(this.d);
        }
        if (this.e != null) {
            sb.append(", fq30Change=").append(this.e);
        }
        if (this.f != null) {
            sb.append(", pushId=").append(this.f);
        }
        return sb.replace(0, 2, "Meta{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public String d;
        public String e;
        
        public final ef b() {
            return new ef(this.c, this.d, this.e, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ef.class);
        }
    }
}
