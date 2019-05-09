package com.facebook.ads.internal.adapters.p030b;

import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.adapters.b.e */
public class C1873e implements Serializable {
    private static final long serialVersionUID = 5306126965868117466L;
    /* renamed from: a */
    private final String f3935a;
    /* renamed from: b */
    private final String f3936b;
    /* renamed from: c */
    private final String f3937c;
    /* renamed from: d */
    private final String f3938d;
    /* renamed from: e */
    private final String f3939e;
    /* renamed from: f */
    private final String f3940f;
    /* renamed from: g */
    private final C1871a f3941g;

    /* renamed from: com.facebook.ads.internal.adapters.b.e$a */
    public enum C1871a {
        CONTEXTUAL_APP("contextual_app"),
        PAGE_POST("page_post");
        
        /* renamed from: c */
        private final String f3927c;

        private C1871a(String str) {
            this.f3927c = str;
        }

        /* renamed from: a */
        public static C1871a m4249a(String str) {
            Object obj = -1;
            switch (str.hashCode()) {
                case 883765328:
                    if (str.equals("page_post")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1434358835:
                    if (str.equals("contextual_app")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return CONTEXTUAL_APP;
                default:
                    return PAGE_POST;
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.b.e$b */
    public static class C1872b {
        /* renamed from: a */
        private String f3928a;
        /* renamed from: b */
        private String f3929b;
        /* renamed from: c */
        private String f3930c;
        /* renamed from: d */
        private String f3931d;
        /* renamed from: e */
        private String f3932e;
        /* renamed from: f */
        private String f3933f;
        /* renamed from: g */
        private C1871a f3934g;

        /* renamed from: a */
        C1872b m4257a(String str) {
            this.f3928a = str;
            return this;
        }

        /* renamed from: a */
        C1873e m4258a() {
            return new C1873e();
        }

        /* renamed from: b */
        C1872b m4259b(String str) {
            this.f3929b = str;
            return this;
        }

        /* renamed from: c */
        C1872b m4260c(String str) {
            this.f3930c = str;
            return this;
        }

        /* renamed from: d */
        C1872b m4261d(String str) {
            this.f3931d = str;
            return this;
        }

        /* renamed from: e */
        C1872b m4262e(String str) {
            this.f3932e = str;
            return this;
        }

        /* renamed from: f */
        C1872b m4263f(String str) {
            this.f3933f = str;
            return this;
        }

        /* renamed from: g */
        C1872b m4264g(String str) {
            this.f3934g = C1871a.m4249a(str);
            return this;
        }
    }

    private C1873e(C1872b c1872b) {
        this.f3935a = c1872b.f3928a;
        this.f3936b = c1872b.f3929b;
        this.f3937c = c1872b.f3930c;
        this.f3938d = c1872b.f3931d;
        this.f3939e = c1872b.f3932e;
        this.f3940f = c1872b.f3933f;
        this.f3941g = c1872b.f3934g;
    }

    /* renamed from: a */
    public String m4265a() {
        return this.f3935a;
    }

    /* renamed from: b */
    public String m4266b() {
        return this.f3936b;
    }

    /* renamed from: c */
    public String m4267c() {
        return this.f3937c;
    }

    /* renamed from: d */
    public String m4268d() {
        return this.f3938d;
    }

    /* renamed from: e */
    public String m4269e() {
        return this.f3939e;
    }

    /* renamed from: f */
    public C1871a m4270f() {
        return this.f3941g;
    }

    /* renamed from: g */
    public String m4271g() {
        return this.f3940f;
    }
}
