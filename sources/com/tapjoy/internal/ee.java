package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;

public final class ee extends dl {
    /* renamed from: c */
    public static final dn f7535c = new C2892b();
    /* renamed from: d */
    public final ed f7536d;
    /* renamed from: e */
    public final dx f7537e;
    /* renamed from: f */
    public final ek f7538f;

    /* renamed from: com.tapjoy.internal.ee$a */
    public static final class C2891a extends C2863a {
        /* renamed from: c */
        public ed f7532c;
        /* renamed from: d */
        public dx f7533d;
        /* renamed from: e */
        public ek f7534e;

        /* renamed from: b */
        public final ee m7620b() {
            return new ee(this.f7532c, this.f7533d, this.f7534e, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.ee$b */
    static final class C2892b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            int a;
            int i = 0;
            ee eeVar = (ee) obj;
            int a2 = eeVar.f7536d != null ? ed.f7511c.mo6216a(1, eeVar.f7536d) : 0;
            if (eeVar.f7537e != null) {
                a = dx.f7406c.mo6216a(2, eeVar.f7537e);
            } else {
                a = 0;
            }
            a2 += a;
            if (eeVar.f7538f != null) {
                i = ek.f7620c.mo6216a(3, eeVar.f7538f);
            }
            return (a2 + i) + eeVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ee eeVar = (ee) obj;
            if (eeVar.f7536d != null) {
                ed.f7511c.mo6217a(dpVar, 1, eeVar.f7536d);
            }
            if (eeVar.f7537e != null) {
                dx.f7406c.mo6217a(dpVar, 2, eeVar.f7537e);
            }
            if (eeVar.f7538f != null) {
                ek.f7620c.mo6217a(dpVar, 3, eeVar.f7538f);
            }
            dpVar.m7469a(eeVar.m7406a());
        }

        C2892b() {
            super(dk.LENGTH_DELIMITED, ee.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2891a c2891a = new C2891a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2891a.f7532c = (ed) ed.f7511c.mo6214a(c2874do);
                            break;
                        case 2:
                            c2891a.f7533d = (dx) dx.f7406c.mo6214a(c2874do);
                            break;
                        case 3:
                            c2891a.f7534e = (ek) ek.f7620c.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2891a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2891a.m7620b();
            }
        }
    }

    public ee(ed edVar, dx dxVar, ek ekVar) {
        this(edVar, dxVar, ekVar, hx.f8185b);
    }

    public ee(ed edVar, dx dxVar, ek ekVar, hx hxVar) {
        super(f7535c, hxVar);
        this.f7536d = edVar;
        this.f7537e = dxVar;
        this.f7538f = ekVar;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ee)) {
            return false;
        }
        ee eeVar = (ee) other;
        if (m7406a().equals(eeVar.m7406a()) && ds.m7478a(this.f7536d, eeVar.f7536d) && ds.m7478a(this.f7537e, eeVar.f7537e) && ds.m7478a(this.f7538f, eeVar.f7538f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int i2 = this.f7364b;
        if (i2 != 0) {
            return i2;
        }
        int hashCode = ((this.f7536d != null ? this.f7536d.hashCode() : 0) + (m7406a().hashCode() * 37)) * 37;
        if (this.f7537e != null) {
            i2 = this.f7537e.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.f7538f != null) {
            i = this.f7538f.hashCode();
        }
        i2 += i;
        this.f7364b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f7536d != null) {
            stringBuilder.append(", info=").append(this.f7536d);
        }
        if (this.f7537e != null) {
            stringBuilder.append(", app=").append(this.f7537e);
        }
        if (this.f7538f != null) {
            stringBuilder.append(", user=").append(this.f7538f);
        }
        return stringBuilder.replace(0, 2, "InfoSet{").append('}').toString();
    }
}
