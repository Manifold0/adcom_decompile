package com.facebook.ads.internal.p032d;

import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.d.c */
public class C1989c implements Serializable {
    /* renamed from: a */
    private C1988a f4388a;
    /* renamed from: b */
    private C1988a f4389b;

    /* renamed from: com.facebook.ads.internal.d.c$a */
    public static class C1988a implements Serializable {
        /* renamed from: a */
        private double f4377a;
        /* renamed from: b */
        private double f4378b;
        /* renamed from: c */
        private double f4379c;
        /* renamed from: d */
        private double f4380d;
        /* renamed from: e */
        private double f4381e;
        /* renamed from: f */
        private double f4382f;
        /* renamed from: g */
        private double f4383g;
        /* renamed from: h */
        private int f4384h;
        /* renamed from: i */
        private double f4385i;
        /* renamed from: j */
        private double f4386j;
        /* renamed from: k */
        private double f4387k;

        public C1988a(double d) {
            this.f4381e = d;
        }

        /* renamed from: a */
        public void m4752a() {
            this.f4377a = 0.0d;
            this.f4379c = 0.0d;
            this.f4380d = 0.0d;
            this.f4382f = 0.0d;
            this.f4384h = 0;
            this.f4385i = 0.0d;
            this.f4386j = 1.0d;
            this.f4387k = 0.0d;
        }

        /* renamed from: a */
        public void m4753a(double d, double d2) {
            this.f4384h++;
            this.f4385i += d;
            this.f4379c = d2;
            this.f4387k += d2 * d;
            this.f4377a = this.f4387k / this.f4385i;
            this.f4386j = Math.min(this.f4386j, d2);
            this.f4382f = Math.max(this.f4382f, d2);
            if (d2 >= this.f4381e) {
                this.f4380d += d;
                this.f4378b += d;
                this.f4383g = Math.max(this.f4383g, this.f4378b);
                return;
            }
            this.f4378b = 0.0d;
        }

        /* renamed from: b */
        public void m4754b() {
            this.f4378b = 0.0d;
        }

        /* renamed from: c */
        public double m4755c() {
            return this.f4384h == 0 ? 0.0d : this.f4386j;
        }

        /* renamed from: d */
        public double m4756d() {
            return this.f4377a;
        }

        /* renamed from: e */
        public double m4757e() {
            return this.f4382f;
        }

        /* renamed from: f */
        public double m4758f() {
            return this.f4385i;
        }

        /* renamed from: g */
        public double m4759g() {
            return this.f4380d;
        }

        /* renamed from: h */
        public double m4760h() {
            return this.f4383g;
        }
    }

    public C1989c() {
        this(0.5d, 0.5d);
    }

    public C1989c(double d) {
        this(d, 0.5d);
    }

    public C1989c(double d, double d2) {
        this.f4388a = new C1988a(d);
        this.f4389b = new C1988a(d2);
        m4761a();
    }

    /* renamed from: a */
    void m4761a() {
        this.f4388a.m4752a();
        this.f4389b.m4752a();
    }

    /* renamed from: a */
    void m4762a(double d, double d2) {
        this.f4388a.m4753a(d, d2);
    }

    /* renamed from: b */
    void m4763b() {
        this.f4388a.m4754b();
        this.f4389b.m4754b();
    }

    /* renamed from: b */
    void m4764b(double d, double d2) {
        this.f4389b.m4753a(d, d2);
    }

    /* renamed from: c */
    public C1988a m4765c() {
        return this.f4388a;
    }

    /* renamed from: d */
    public C1988a m4766d() {
        return this.f4389b;
    }
}
