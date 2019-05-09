// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ed extends dl
{
    public static final dn c;
    public static final Integer d;
    public static final Integer e;
    public static final Integer f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final Integer m;
    public final Integer n;
    public final Integer o;
    public final String p;
    public final String q;
    public final String r;
    public final String s;
    public final String t;
    public final String u;
    public final String v;
    public final String w;
    
    static {
        c = new b();
        d = 0;
        e = 0;
        f = 0;
    }
    
    public ed(final String g, final String h, final String i, final String j, final String k, final String l, final Integer m, final Integer n, final Integer o, final String p18, final String q, final String r, final String s, final String t, final String u, final String v, final String w, final hx hx) {
        super(ed.c, hx);
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p18;
        this.q = q;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ed)) {
                return false;
            }
            final ed ed = (ed)o;
            if (!this.a().equals(ed.a()) || !ds.a(this.g, ed.g) || !ds.a(this.h, ed.h) || !ds.a(this.i, ed.i) || !ds.a(this.j, ed.j) || !ds.a(this.k, ed.k) || !ds.a(this.l, ed.l) || !ds.a(this.m, ed.m) || !ds.a(this.n, ed.n) || !ds.a(this.o, ed.o) || !ds.a(this.p, ed.p) || !ds.a(this.q, ed.q) || !ds.a(this.r, ed.r) || !ds.a(this.s, ed.s) || !ds.a(this.t, ed.t) || !ds.a(this.u, ed.u) || !ds.a(this.v, ed.v) || !ds.a(this.w, ed.w)) {
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
            if (this.g != null) {
                hashCode3 = this.g.hashCode();
            }
            else {
                hashCode3 = 0;
            }
            int hashCode4;
            if (this.h != null) {
                hashCode4 = this.h.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            int hashCode5;
            if (this.i != null) {
                hashCode5 = this.i.hashCode();
            }
            else {
                hashCode5 = 0;
            }
            int hashCode6;
            if (this.j != null) {
                hashCode6 = this.j.hashCode();
            }
            else {
                hashCode6 = 0;
            }
            int hashCode7;
            if (this.k != null) {
                hashCode7 = this.k.hashCode();
            }
            else {
                hashCode7 = 0;
            }
            int hashCode8;
            if (this.l != null) {
                hashCode8 = this.l.hashCode();
            }
            else {
                hashCode8 = 0;
            }
            int hashCode9;
            if (this.m != null) {
                hashCode9 = this.m.hashCode();
            }
            else {
                hashCode9 = 0;
            }
            int hashCode10;
            if (this.n != null) {
                hashCode10 = this.n.hashCode();
            }
            else {
                hashCode10 = 0;
            }
            int hashCode11;
            if (this.o != null) {
                hashCode11 = this.o.hashCode();
            }
            else {
                hashCode11 = 0;
            }
            int hashCode12;
            if (this.p != null) {
                hashCode12 = this.p.hashCode();
            }
            else {
                hashCode12 = 0;
            }
            int hashCode13;
            if (this.q != null) {
                hashCode13 = this.q.hashCode();
            }
            else {
                hashCode13 = 0;
            }
            int hashCode14;
            if (this.r != null) {
                hashCode14 = this.r.hashCode();
            }
            else {
                hashCode14 = 0;
            }
            int hashCode15;
            if (this.s != null) {
                hashCode15 = this.s.hashCode();
            }
            else {
                hashCode15 = 0;
            }
            int hashCode16;
            if (this.t != null) {
                hashCode16 = this.t.hashCode();
            }
            else {
                hashCode16 = 0;
            }
            int hashCode17;
            if (this.u != null) {
                hashCode17 = this.u.hashCode();
            }
            else {
                hashCode17 = 0;
            }
            int hashCode18;
            if (this.v != null) {
                hashCode18 = this.v.hashCode();
            }
            else {
                hashCode18 = 0;
            }
            if (this.w != null) {
                hashCode = this.w.hashCode();
            }
            b = (hashCode18 + (hashCode17 + (hashCode16 + (hashCode15 + (hashCode14 + (hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + (hashCode8 + (hashCode7 + (hashCode6 + (hashCode5 + (hashCode4 + (hashCode3 + hashCode2 * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.g != null) {
            sb.append(", mac=").append(this.g);
        }
        if (this.h != null) {
            sb.append(", deviceId=").append(this.h);
        }
        if (this.i != null) {
            sb.append(", deviceMaker=").append(this.i);
        }
        if (this.j != null) {
            sb.append(", deviceModel=").append(this.j);
        }
        if (this.k != null) {
            sb.append(", osName=").append(this.k);
        }
        if (this.l != null) {
            sb.append(", osVer=").append(this.l);
        }
        if (this.m != null) {
            sb.append(", displayD=").append(this.m);
        }
        if (this.n != null) {
            sb.append(", displayW=").append(this.n);
        }
        if (this.o != null) {
            sb.append(", displayH=").append(this.o);
        }
        if (this.p != null) {
            sb.append(", locale=").append(this.p);
        }
        if (this.q != null) {
            sb.append(", timezone=").append(this.q);
        }
        if (this.r != null) {
            sb.append(", pkgId=").append(this.r);
        }
        if (this.s != null) {
            sb.append(", pkgSign=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", sdk=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", countrySim=").append(this.u);
        }
        if (this.v != null) {
            sb.append(", countryNet=").append(this.v);
        }
        if (this.w != null) {
            sb.append(", imei=").append(this.w);
        }
        return sb.replace(0, 2, "Info{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public Integer i;
        public Integer j;
        public Integer k;
        public String l;
        public String m;
        public String n;
        public String o;
        public String p;
        public String q;
        public String r;
        public String s;
        
        public final ed b() {
            return new ed(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ed.class);
        }
    }
}
