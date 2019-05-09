// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class dy extends dl
{
    public static final dn c;
    public static final eb d;
    public static final Long e;
    public static final Long f;
    public static final Long g;
    public static final Long h;
    public static final Integer i;
    public static final Integer j;
    public static final Integer k;
    public static final Long l;
    public static final Long m;
    public final eg A;
    public final String B;
    public final String C;
    public final ef D;
    public final String E;
    public final String F;
    public final String G;
    public final List H;
    public final String I;
    public final Integer J;
    public final Long K;
    public final Long L;
    public final eb n;
    public final String o;
    public final Long p;
    public final Long q;
    public final String r;
    public final Long s;
    public final Long t;
    public final ed u;
    public final dx v;
    public final ek w;
    public final Integer x;
    public final Integer y;
    public final ea z;
    
    static {
        c = new b();
        d = eb.APP;
        e = 0L;
        f = 0L;
        g = 0L;
        h = 0L;
        i = 0;
        j = 0;
        k = 0;
        l = 0L;
        m = 0L;
    }
    
    public dy(final eb n, final String o, final Long p26, final Long q, final String r, final Long s, final Long t, final ed u, final dx v, final ek w, final Integer x, final Integer y, final ea z, final eg a, final String b, final String c, final ef d, final String e, final String f, final String g, final List list, final String i, final Integer j, final Long k, final Long l, final hx hx) {
        super(dy.c, hx);
        this.n = n;
        this.o = o;
        this.p = p26;
        this.q = q;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.w = w;
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
        this.H = ds.a("values", list);
        this.I = i;
        this.J = j;
        this.K = k;
        this.L = l;
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof dy)) {
                return false;
            }
            final dy dy = (dy)o;
            if (!this.a().equals(dy.a()) || !this.n.equals(dy.n) || !this.o.equals(dy.o) || !this.p.equals(dy.p) || !ds.a(this.q, dy.q) || !ds.a(this.r, dy.r) || !ds.a(this.s, dy.s) || !ds.a(this.t, dy.t) || !ds.a(this.u, dy.u) || !ds.a(this.v, dy.v) || !ds.a(this.w, dy.w) || !ds.a(this.x, dy.x) || !ds.a(this.y, dy.y) || !ds.a(this.z, dy.z) || !ds.a(this.A, dy.A) || !ds.a(this.B, dy.B) || !ds.a(this.C, dy.C) || !ds.a(this.D, dy.D) || !ds.a(this.E, dy.E) || !ds.a(this.F, dy.F) || !ds.a(this.G, dy.G) || !this.H.equals(dy.H) || !ds.a(this.I, dy.I) || !ds.a(this.J, dy.J) || !ds.a(this.K, dy.K) || !ds.a(this.L, dy.L)) {
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
            final int hashCode3 = this.n.hashCode();
            final int hashCode4 = this.o.hashCode();
            final int hashCode5 = this.p.hashCode();
            int hashCode6;
            if (this.q != null) {
                hashCode6 = this.q.hashCode();
            }
            else {
                hashCode6 = 0;
            }
            int hashCode7;
            if (this.r != null) {
                hashCode7 = this.r.hashCode();
            }
            else {
                hashCode7 = 0;
            }
            int hashCode8;
            if (this.s != null) {
                hashCode8 = this.s.hashCode();
            }
            else {
                hashCode8 = 0;
            }
            int hashCode9;
            if (this.t != null) {
                hashCode9 = this.t.hashCode();
            }
            else {
                hashCode9 = 0;
            }
            int hashCode10;
            if (this.u != null) {
                hashCode10 = this.u.hashCode();
            }
            else {
                hashCode10 = 0;
            }
            int hashCode11;
            if (this.v != null) {
                hashCode11 = this.v.hashCode();
            }
            else {
                hashCode11 = 0;
            }
            int hashCode12;
            if (this.w != null) {
                hashCode12 = this.w.hashCode();
            }
            else {
                hashCode12 = 0;
            }
            int hashCode13;
            if (this.x != null) {
                hashCode13 = this.x.hashCode();
            }
            else {
                hashCode13 = 0;
            }
            int hashCode14;
            if (this.y != null) {
                hashCode14 = this.y.hashCode();
            }
            else {
                hashCode14 = 0;
            }
            int hashCode15;
            if (this.z != null) {
                hashCode15 = this.z.hashCode();
            }
            else {
                hashCode15 = 0;
            }
            int hashCode16;
            if (this.A != null) {
                hashCode16 = this.A.hashCode();
            }
            else {
                hashCode16 = 0;
            }
            int hashCode17;
            if (this.B != null) {
                hashCode17 = this.B.hashCode();
            }
            else {
                hashCode17 = 0;
            }
            int hashCode18;
            if (this.C != null) {
                hashCode18 = this.C.hashCode();
            }
            else {
                hashCode18 = 0;
            }
            int hashCode19;
            if (this.D != null) {
                hashCode19 = this.D.hashCode();
            }
            else {
                hashCode19 = 0;
            }
            int hashCode20;
            if (this.E != null) {
                hashCode20 = this.E.hashCode();
            }
            else {
                hashCode20 = 0;
            }
            int hashCode21;
            if (this.F != null) {
                hashCode21 = this.F.hashCode();
            }
            else {
                hashCode21 = 0;
            }
            int hashCode22;
            if (this.G != null) {
                hashCode22 = this.G.hashCode();
            }
            else {
                hashCode22 = 0;
            }
            final int hashCode23 = this.H.hashCode();
            int hashCode24;
            if (this.I != null) {
                hashCode24 = this.I.hashCode();
            }
            else {
                hashCode24 = 0;
            }
            int hashCode25;
            if (this.J != null) {
                hashCode25 = this.J.hashCode();
            }
            else {
                hashCode25 = 0;
            }
            int hashCode26;
            if (this.K != null) {
                hashCode26 = this.K.hashCode();
            }
            else {
                hashCode26 = 0;
            }
            if (this.L != null) {
                hashCode = this.L.hashCode();
            }
            b = (hashCode26 + (hashCode25 + (hashCode24 + ((hashCode22 + (hashCode21 + (hashCode20 + (hashCode19 + (hashCode18 + (hashCode17 + (hashCode16 + (hashCode15 + (hashCode14 + (hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + (hashCode8 + (hashCode7 + (hashCode6 + (((hashCode2 * 37 + hashCode3) * 37 + hashCode4) * 37 + hashCode5) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37) * 37 + hashCode23) * 37) * 37) * 37) * 37 + hashCode;
            super.b = b;
        }
        return b;
    }
    
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(", type=").append(this.n);
        sb.append(", name=").append(this.o);
        sb.append(", time=").append(this.p);
        if (this.q != null) {
            sb.append(", systemTime=").append(this.q);
        }
        if (this.r != null) {
            sb.append(", instanceId=").append(this.r);
        }
        if (this.s != null) {
            sb.append(", elapsedRealtime=").append(this.s);
        }
        if (this.t != null) {
            sb.append(", duration=").append(this.t);
        }
        if (this.u != null) {
            sb.append(", info=").append(this.u);
        }
        if (this.v != null) {
            sb.append(", app=").append(this.v);
        }
        if (this.w != null) {
            sb.append(", user=").append(this.w);
        }
        if (this.x != null) {
            sb.append(", xxx_session_seq=").append(this.x);
        }
        if (this.y != null) {
            sb.append(", eventSeq=").append(this.y);
        }
        if (this.z != null) {
            sb.append(", eventPrev=").append(this.z);
        }
        if (this.A != null) {
            sb.append(", purchase=").append(this.A);
        }
        if (this.B != null) {
            sb.append(", exception=").append(this.B);
        }
        if (this.C != null) {
            sb.append(", metaBase=").append(this.C);
        }
        if (this.D != null) {
            sb.append(", meta=").append(this.D);
        }
        if (this.E != null) {
            sb.append(", category=").append(this.E);
        }
        if (this.F != null) {
            sb.append(", p1=").append(this.F);
        }
        if (this.G != null) {
            sb.append(", p2=").append(this.G);
        }
        if (!this.H.isEmpty()) {
            sb.append(", values=").append(this.H);
        }
        if (this.I != null) {
            sb.append(", dimensions=").append(this.I);
        }
        if (this.J != null) {
            sb.append(", count=").append(this.J);
        }
        if (this.K != null) {
            sb.append(", firstTime=").append(this.K);
        }
        if (this.L != null) {
            sb.append(", lastTime=").append(this.L);
        }
        return sb.replace(0, 2, "Event{").append('}').toString();
    }
    
    public static final class a extends dl.a
    {
        public Long A;
        public eb c;
        public String d;
        public Long e;
        public Long f;
        public String g;
        public Long h;
        public Long i;
        public ed j;
        public dx k;
        public ek l;
        public Integer m;
        public Integer n;
        public ea o;
        public eg p;
        public String q;
        public String r;
        public ef s;
        public String t;
        public String u;
        public String v;
        public List w;
        public String x;
        public Integer y;
        public Long z;
        
        public a() {
            this.w = ds.a();
        }
        
        public final dy b() {
            if (this.c == null || this.d == null || this.e == null) {
                throw ds.a(this.c, "type", this.d, "name", this.e, "time");
            }
            return new dy(this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, super.a());
        }
    }
    
    static final class b extends dn
    {
        b() {
            super(dk.c, dy.class);
        }
        
        private static dy b(final do do1) {
            final dy.a a = new dy.a();
            final long a2 = do1.a();
            while (true) {
                final int b = do1.b();
                if (b == -1) {
                    break;
                }
                switch (b) {
                    default: {
                        final dk c = do1.c();
                        ((dl.a)a).a(b, c, c.a().a(do1));
                        continue;
                    }
                    case 1: {
                        try {
                            a.c = (eb)eb.ADAPTER.a(do1);
                        }
                        catch (dn.a a3) {
                            ((dl.a)a).a(b, dk.a, (long)a3.a);
                        }
                        continue;
                    }
                    case 2: {
                        a.d = (String)dn.p.a(do1);
                        continue;
                    }
                    case 3: {
                        a.e = (Long)dn.i.a(do1);
                        continue;
                    }
                    case 19: {
                        a.f = (Long)dn.i.a(do1);
                        continue;
                    }
                    case 20: {
                        a.g = (String)dn.p.a(do1);
                        continue;
                    }
                    case 21: {
                        a.h = (Long)dn.i.a(do1);
                        continue;
                    }
                    case 4: {
                        a.i = (Long)dn.i.a(do1);
                        continue;
                    }
                    case 5: {
                        a.j = (ed)ed.c.a(do1);
                        continue;
                    }
                    case 6: {
                        a.k = (dx)dx.c.a(do1);
                        continue;
                    }
                    case 7: {
                        a.l = (ek)ek.c.a(do1);
                        continue;
                    }
                    case 8: {
                        a.m = (Integer)dn.d.a(do1);
                        continue;
                    }
                    case 9: {
                        a.n = (Integer)dn.d.a(do1);
                        continue;
                    }
                    case 10: {
                        a.o = (ea)ea.c.a(do1);
                        continue;
                    }
                    case 11: {
                        a.p = (eg)eg.c.a(do1);
                        continue;
                    }
                    case 12: {
                        a.q = (String)dn.p.a(do1);
                        continue;
                    }
                    case 13: {
                        a.r = (String)dn.p.a(do1);
                        continue;
                    }
                    case 18: {
                        a.s = (ef)ef.c.a(do1);
                        continue;
                    }
                    case 14: {
                        a.t = (String)dn.p.a(do1);
                        continue;
                    }
                    case 15: {
                        a.u = (String)dn.p.a(do1);
                        continue;
                    }
                    case 16: {
                        a.v = (String)dn.p.a(do1);
                        continue;
                    }
                    case 17: {
                        a.w.add(ec.c.a(do1));
                        continue;
                    }
                    case 22: {
                        a.x = (String)dn.p.a(do1);
                        continue;
                    }
                    case 23: {
                        a.y = (Integer)dn.d.a(do1);
                        continue;
                    }
                    case 24: {
                        a.z = (Long)dn.i.a(do1);
                        continue;
                    }
                    case 25: {
                        a.A = (Long)dn.i.a(do1);
                        continue;
                    }
                }
            }
            do1.a(a2);
            return a.b();
        }
    }
}
