package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;

public final class eh extends dl {
    /* renamed from: c */
    public static final dn f7582c = new C2898b();
    /* renamed from: d */
    public static final Long f7583d = Long.valueOf(0);
    /* renamed from: e */
    public static final Long f7584e = Long.valueOf(0);
    /* renamed from: f */
    public final String f7585f;
    /* renamed from: g */
    public final Long f7586g;
    /* renamed from: h */
    public final Long f7587h;

    /* renamed from: com.tapjoy.internal.eh$a */
    public static final class C2897a extends C2863a {
        /* renamed from: c */
        public String f7579c;
        /* renamed from: d */
        public Long f7580d;
        /* renamed from: e */
        public Long f7581e;

        /* renamed from: b */
        public final eh m7632b() {
            if (this.f7579c != null && this.f7580d != null) {
                return new eh(this.f7579c, this.f7580d, this.f7581e, super.m7405a());
            }
            throw ds.m7475a(this.f7579c, "id", this.f7580d, "received");
        }
    }

    /* renamed from: com.tapjoy.internal.eh$b */
    static final class C2898b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            eh ehVar = (eh) obj;
            return ((ehVar.f7587h != null ? dn.f7342i.mo6216a(3, ehVar.f7587h) : 0) + (dn.f7342i.mo6216a(2, ehVar.f7586g) + dn.f7349p.mo6216a(1, ehVar.f7585f))) + ehVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            eh ehVar = (eh) obj;
            dn.f7349p.mo6217a(dpVar, 1, ehVar.f7585f);
            dn.f7342i.mo6217a(dpVar, 2, ehVar.f7586g);
            if (ehVar.f7587h != null) {
                dn.f7342i.mo6217a(dpVar, 3, ehVar.f7587h);
            }
            dpVar.m7469a(ehVar.m7406a());
        }

        C2898b() {
            super(dk.LENGTH_DELIMITED, eh.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2897a c2897a = new C2897a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2897a.f7579c = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 2:
                            c2897a.f7580d = (Long) dn.f7342i.mo6214a(c2874do);
                            break;
                        case 3:
                            c2897a.f7581e = (Long) dn.f7342i.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2897a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2897a.m7632b();
            }
        }
    }

    public eh(String str, Long l) {
        this(str, l, null, hx.f8185b);
    }

    public eh(String str, Long l, Long l2, hx hxVar) {
        super(f7582c, hxVar);
        this.f7585f = str;
        this.f7586g = l;
        this.f7587h = l2;
    }

    /* renamed from: b */
    public final C2897a m7636b() {
        C2897a c2897a = new C2897a();
        c2897a.f7579c = this.f7585f;
        c2897a.f7580d = this.f7586g;
        c2897a.f7581e = this.f7587h;
        c2897a.m7404a(m7406a());
        return c2897a;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof eh)) {
            return false;
        }
        eh ehVar = (eh) other;
        if (m7406a().equals(ehVar.m7406a()) && this.f7585f.equals(ehVar.f7585f) && this.f7586g.equals(ehVar.f7586g) && ds.m7478a(this.f7587h, ehVar.f7587h)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (this.f7587h != null ? this.f7587h.hashCode() : 0) + (((((m7406a().hashCode() * 37) + this.f7585f.hashCode()) * 37) + this.f7586g.hashCode()) * 37);
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", id=").append(this.f7585f);
        stringBuilder.append(", received=").append(this.f7586g);
        if (this.f7587h != null) {
            stringBuilder.append(", clicked=").append(this.f7587h);
        }
        return stringBuilder.replace(0, 2, "Push{").append('}').toString();
    }
}
