// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class ek extends dl
{
    public static final dn c;
    public static final Long d;
    public static final Integer e;
    public static final Integer f;
    public static final Integer g;
    public static final Long h;
    public static final Long i;
    public static final Long j;
    public static final Integer k;
    public static final Double l;
    public static final Long m;
    public static final Double n;
    public static final Boolean o;
    public static final Integer p;
    public static final Integer q;
    public static final Boolean r;
    public final Long A;
    public final String B;
    public final Integer C;
    public final Double D;
    public final Long E;
    public final Double F;
    public final String G;
    public final Boolean H;
    public final String I;
    public final Integer J;
    public final Integer K;
    public final String L;
    public final String M;
    public final String N;
    public final String O;
    public final String P;
    public final List Q;
    public final Boolean R;
    public final Long s;
    public final String t;
    public final Integer u;
    public final Integer v;
    public final List w;
    public final Integer x;
    public final Long y;
    public final Long z;
    
    static {
        c = new b();
        d = 0L;
        e = 0;
        f = 0;
        g = 0;
        h = 0L;
        i = 0L;
        j = 0L;
        k = 0;
        l = 0.0;
        m = 0L;
        n = 0.0;
        o = false;
        p = 0;
        q = 0;
        r = false;
    }
    
    public ek(final Long s, final String t, final Integer u, final Integer v, final List list, final Integer x, final Long y, final Long z, final Long a, final String b, final Integer c, final Double d, final Long e, final Double f, final String g, final Boolean h, final String i, final Integer j, final Integer k, final String l, final String m, final String n, final String o, final String p27, final List list2, final Boolean r, final hx hx) {
        super(ek.c, hx);
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = ds.a("pushes", list);
        this.x = x;
        this.y = y;
        this.z = z;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.E = e;
        this.F = f;
        this.G = g;
        this.H = h;
        this.I = i;
        this.J = j;
        this.K = k;
        this.L = l;
        this.M = m;
        this.N = n;
        this.O = o;
        this.P = p27;
        this.Q = ds.a("tags", list2);
        this.R = r;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof ek)) {
                return false;
            }
            final ek ek = (ek)o;
            if (!this.a().equals(ek.a()) || !ds.a(this.s, ek.s) || !ds.a(this.t, ek.t) || !ds.a(this.u, ek.u) || !ds.a(this.v, ek.v) || !this.w.equals(ek.w) || !ds.a(this.x, ek.x) || !ds.a(this.y, ek.y) || !ds.a(this.z, ek.z) || !ds.a(this.A, ek.A) || !ds.a(this.B, ek.B) || !ds.a(this.C, ek.C) || !ds.a(this.D, ek.D) || !ds.a(this.E, ek.E) || !ds.a(this.F, ek.F) || !ds.a(this.G, ek.G) || !ds.a(this.H, ek.H) || !ds.a(this.I, ek.I) || !ds.a(this.J, ek.J) || !ds.a(this.K, ek.K) || !ds.a(this.L, ek.L) || !ds.a(this.M, ek.M) || !ds.a(this.N, ek.N) || !ds.a(this.O, ek.O) || !ds.a(this.P, ek.P) || !this.Q.equals(ek.Q) || !ds.a(this.R, ek.R)) {
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
            if (this.s != null) {
                hashCode3 = this.s.hashCode();
            }
            else {
                hashCode3 = 0;
            }
            int hashCode4;
            if (this.t != null) {
                hashCode4 = this.t.hashCode();
            }
            else {
                hashCode4 = 0;
            }
            int hashCode5;
            if (this.u != null) {
                hashCode5 = this.u.hashCode();
            }
            else {
                hashCode5 = 0;
            }
            int hashCode6;
            if (this.v != null) {
                hashCode6 = this.v.hashCode();
            }
            else {
                hashCode6 = 0;
            }
            final int hashCode7 = this.w.hashCode();
            int hashCode8;
            if (this.x != null) {
                hashCode8 = this.x.hashCode();
            }
            else {
                hashCode8 = 0;
            }
            int hashCode9;
            if (this.y != null) {
                hashCode9 = this.y.hashCode();
            }
            else {
                hashCode9 = 0;
            }
            int hashCode10;
            if (this.z != null) {
                hashCode10 = this.z.hashCode();
            }
            else {
                hashCode10 = 0;
            }
            int hashCode11;
            if (this.A != null) {
                hashCode11 = this.A.hashCode();
            }
            else {
                hashCode11 = 0;
            }
            int hashCode12;
            if (this.B != null) {
                hashCode12 = this.B.hashCode();
            }
            else {
                hashCode12 = 0;
            }
            int hashCode13;
            if (this.C != null) {
                hashCode13 = this.C.hashCode();
            }
            else {
                hashCode13 = 0;
            }
            int hashCode14;
            if (this.D != null) {
                hashCode14 = this.D.hashCode();
            }
            else {
                hashCode14 = 0;
            }
            int hashCode15;
            if (this.E != null) {
                hashCode15 = this.E.hashCode();
            }
            else {
                hashCode15 = 0;
            }
            int hashCode16;
            if (this.F != null) {
                hashCode16 = this.F.hashCode();
            }
            else {
                hashCode16 = 0;
            }
            int hashCode17;
            if (this.G != null) {
                hashCode17 = this.G.hashCode();
            }
            else {
                hashCode17 = 0;
            }
            int hashCode18;
            if (this.H != null) {
                hashCode18 = this.H.hashCode();
            }
            else {
                hashCode18 = 0;
            }
            int hashCode19;
            if (this.I != null) {
                hashCode19 = this.I.hashCode();
            }
            else {
                hashCode19 = 0;
            }
            int hashCode20;
            if (this.J != null) {
                hashCode20 = this.J.hashCode();
            }
            else {
                hashCode20 = 0;
            }
            int hashCode21;
            if (this.K != null) {
                hashCode21 = this.K.hashCode();
            }
            else {
                hashCode21 = 0;
            }
            int hashCode22;
            if (this.L != null) {
                hashCode22 = this.L.hashCode();
            }
            else {
                hashCode22 = 0;
            }
            int hashCode23;
            if (this.M != null) {
                hashCode23 = this.M.hashCode();
            }
            else {
                hashCode23 = 0;
            }
            int hashCode24;
            if (this.N != null) {
                hashCode24 = this.N.hashCode();
            }
            else {
                hashCode24 = 0;
            }
            int hashCode25;
            if (this.O != null) {
                hashCode25 = this.O.hashCode();
            }
            else {
                hashCode25 = 0;
            }
            int hashCode26;
            if (this.P != null) {
                hashCode26 = this.P.hashCode();
            }
            else {
                hashCode26 = 0;
            }
            final int hashCode27 = this.Q.hashCode();
            if (this.R != null) {
                hashCode = this.R.hashCode();
            }
            b = ((hashCode26 + (hashCode25 + (hashCode24 + (hashCode23 + (hashCode22 + (hashCode21 + (hashCode20 + (hashCode19 + (hashCode18 + (hashCode17 + (hashCode16 + (hashCode15 + (hashCode14 + (hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + (hashCode8 + ((hashCode6 + (hashCode5 + (hashCode4 + (hashCode3 + hashCode2 * 37) * 37) * 37) * 37) * 37 + hashCode7) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37 + hashCode27) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.s != null) {
            sb.append(", installed=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", referrer=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", fq7=").append(this.u);
        }
        if (this.v != null) {
            sb.append(", fq30=").append(this.v);
        }
        if (!this.w.isEmpty()) {
            sb.append(", pushes=").append(this.w);
        }
        if (this.x != null) {
            sb.append(", sessionTotalCount=").append(this.x);
        }
        if (this.y != null) {
            sb.append(", sessionTotalDuration=").append(this.y);
        }
        if (this.z != null) {
            sb.append(", sessionLastTime=").append(this.z);
        }
        if (this.A != null) {
            sb.append(", sessionLastDuration=").append(this.A);
        }
        if (this.B != null) {
            sb.append(", purchaseCurrency=").append(this.B);
        }
        if (this.C != null) {
            sb.append(", purchaseTotalCount=").append(this.C);
        }
        if (this.D != null) {
            sb.append(", purchaseTotalPrice=").append(this.D);
        }
        if (this.E != null) {
            sb.append(", purchaseLastTime=").append(this.E);
        }
        if (this.F != null) {
            sb.append(", purchaseLastPrice=").append(this.F);
        }
        if (this.G != null) {
            sb.append(", idfa=").append(this.G);
        }
        if (this.H != null) {
            sb.append(", idfaOptout=").append(this.H);
        }
        if (this.I != null) {
            sb.append(", userId=").append(this.I);
        }
        if (this.J != null) {
            sb.append(", userLevel=").append(this.J);
        }
        if (this.K != null) {
            sb.append(", friendCount=").append(this.K);
        }
        if (this.L != null) {
            sb.append(", uv1=").append(this.L);
        }
        if (this.M != null) {
            sb.append(", uv2=").append(this.M);
        }
        if (this.N != null) {
            sb.append(", uv3=").append(this.N);
        }
        if (this.O != null) {
            sb.append(", uv4=").append(this.O);
        }
        if (this.P != null) {
            sb.append(", uv5=").append(this.P);
        }
        if (!this.Q.isEmpty()) {
            sb.append(", tags=").append(this.Q);
        }
        if (this.R != null) {
            sb.append(", pushOptout=").append(this.R);
        }
        return sb.replace(0, 2, "User{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public List A;
        public Boolean B;
        public Long c;
        public String d;
        public Integer e;
        public Integer f;
        public List g;
        public Integer h;
        public Long i;
        public Long j;
        public Long k;
        public String l;
        public Integer m;
        public Double n;
        public Long o;
        public Double p;
        public String q;
        public Boolean r;
        public String s;
        public Integer t;
        public Integer u;
        public String v;
        public String w;
        public String x;
        public String y;
        public String z;
        
        public a() {
            this.g = ds.a();
            this.A = ds.a();
        }
        
        public final ek b() {
            return new ek(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, ek.class);
        }
    }
}
