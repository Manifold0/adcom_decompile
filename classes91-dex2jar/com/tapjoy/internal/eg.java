// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class eg extends dl
{
    public static final dn c;
    public static final Integer d;
    public static final Double e;
    public static final Integer f;
    public static final Long g;
    public final String h;
    public final Integer i;
    public final Double j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final Integer p;
    public final Long q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;
    
    static {
        c = new b();
        d = 1;
        e = 0.0;
        f = 0;
        g = 0L;
    }
    
    public eg(final String h, final Integer i, final Double j, final String k, final String l, final String m, final String n, final String o, final Integer p15, final Long q, final String r, final String s, final String t, final String u, final hx hx) {
        super(eg.c, hx);
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p15;
        this.q = q;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof eg)) {
                return false;
            }
            final eg eg = (eg)o;
            if (!this.a().equals(eg.a()) || !this.h.equals(eg.h) || !ds.a(this.i, eg.i) || !ds.a(this.j, eg.j) || !ds.a(this.k, eg.k) || !ds.a(this.l, eg.l) || !ds.a(this.m, eg.m) || !ds.a(this.n, eg.n) || !ds.a(this.o, eg.o) || !ds.a(this.p, eg.p) || !ds.a(this.q, eg.q) || !ds.a(this.r, eg.r) || !ds.a(this.s, eg.s) || !ds.a(this.t, eg.t) || !ds.a(this.u, eg.u)) {
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
            final int hashCode3 = this.h.hashCode();
            int hashCode4;
            if (this.i != null) {
                hashCode4 = this.i.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            int hashCode5;
            if (this.j != null) {
                hashCode5 = this.j.hashCode();
            }
            else {
                hashCode5 = 0;
            }
            int hashCode6;
            if (this.k != null) {
                hashCode6 = this.k.hashCode();
            }
            else {
                hashCode6 = 0;
            }
            int hashCode7;
            if (this.l != null) {
                hashCode7 = this.l.hashCode();
            }
            else {
                hashCode7 = 0;
            }
            int hashCode8;
            if (this.m != null) {
                hashCode8 = this.m.hashCode();
            }
            else {
                hashCode8 = 0;
            }
            int hashCode9;
            if (this.n != null) {
                hashCode9 = this.n.hashCode();
            }
            else {
                hashCode9 = 0;
            }
            int hashCode10;
            if (this.o != null) {
                hashCode10 = this.o.hashCode();
            }
            else {
                hashCode10 = 0;
            }
            int hashCode11;
            if (this.p != null) {
                hashCode11 = this.p.hashCode();
            }
            else {
                hashCode11 = 0;
            }
            int hashCode12;
            if (this.q != null) {
                hashCode12 = this.q.hashCode();
            }
            else {
                hashCode12 = 0;
            }
            int hashCode13;
            if (this.r != null) {
                hashCode13 = this.r.hashCode();
            }
            else {
                hashCode13 = 0;
            }
            int hashCode14;
            if (this.s != null) {
                hashCode14 = this.s.hashCode();
            }
            else {
                hashCode14 = 0;
            }
            int hashCode15;
            if (this.t != null) {
                hashCode15 = this.t.hashCode();
            }
            else {
                hashCode15 = 0;
            }
            if (this.u != null) {
                hashCode = this.u.hashCode();
            }
            b = (hashCode15 + (hashCode14 + (hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + (hashCode8 + (hashCode7 + (hashCode6 + (hashCode5 + (hashCode4 + (hashCode2 * 37 + hashCode3) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(", productId=").append(this.h);
        if (this.i != null) {
            sb.append(", productQuantity=").append(this.i);
        }
        if (this.j != null) {
            sb.append(", productPrice=").append(this.j);
        }
        if (this.k != null) {
            sb.append(", productPriceCurrency=").append(this.k);
        }
        if (this.l != null) {
            sb.append(", productType=").append(this.l);
        }
        if (this.m != null) {
            sb.append(", productTitle=").append(this.m);
        }
        if (this.n != null) {
            sb.append(", productDescription=").append(this.n);
        }
        if (this.o != null) {
            sb.append(", transactionId=").append(this.o);
        }
        if (this.p != null) {
            sb.append(", transactionState=").append(this.p);
        }
        if (this.q != null) {
            sb.append(", transactionDate=").append(this.q);
        }
        if (this.r != null) {
            sb.append(", campaignId=").append(this.r);
        }
        if (this.s != null) {
            sb.append(", currencyPrice=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", receipt=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", signature=").append(this.u);
        }
        return sb.replace(0, 2, "Purchase{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public Integer d;
        public Double e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public Integer k;
        public Long l;
        public String m;
        public String n;
        public String o;
        public String p;
        
        public final eg b() {
            if (this.c == null) {
                throw ds.a(new Object[] { this.c, "productId" });
            }
            return new eg(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, eg.class);
        }
    }
}
