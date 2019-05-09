package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;

public final class dx extends dl {
    /* renamed from: c */
    public static final dn f7406c = new C2878b();
    /* renamed from: d */
    public static final Integer f7407d = Integer.valueOf(0);
    /* renamed from: e */
    public final String f7408e;
    /* renamed from: f */
    public final Integer f7409f;
    /* renamed from: g */
    public final String f7410g;
    /* renamed from: h */
    public final String f7411h;
    /* renamed from: i */
    public final String f7412i;

    /* renamed from: com.tapjoy.internal.dx$a */
    public static final class C2877a extends C2863a {
        /* renamed from: c */
        public String f7401c;
        /* renamed from: d */
        public Integer f7402d;
        /* renamed from: e */
        public String f7403e;
        /* renamed from: f */
        public String f7404f;
        /* renamed from: g */
        public String f7405g;

        /* renamed from: b */
        public final dx m7591b() {
            return new dx(this.f7401c, this.f7402d, this.f7403e, this.f7404f, this.f7405g, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.dx$b */
    static final class C2878b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            int a;
            int i = 0;
            dx dxVar = (dx) obj;
            int a2 = dxVar.f7408e != null ? dn.f7349p.mo6216a(1, dxVar.f7408e) : 0;
            if (dxVar.f7409f != null) {
                a = dn.f7337d.mo6216a(2, dxVar.f7409f);
            } else {
                a = 0;
            }
            a += a2;
            if (dxVar.f7410g != null) {
                a2 = dn.f7349p.mo6216a(3, dxVar.f7410g);
            } else {
                a2 = 0;
            }
            a += a2;
            if (dxVar.f7411h != null) {
                a2 = dn.f7349p.mo6216a(4, dxVar.f7411h);
            } else {
                a2 = 0;
            }
            a2 += a;
            if (dxVar.f7412i != null) {
                i = dn.f7349p.mo6216a(5, dxVar.f7412i);
            }
            return (a2 + i) + dxVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dx dxVar = (dx) obj;
            if (dxVar.f7408e != null) {
                dn.f7349p.mo6217a(dpVar, 1, dxVar.f7408e);
            }
            if (dxVar.f7409f != null) {
                dn.f7337d.mo6217a(dpVar, 2, dxVar.f7409f);
            }
            if (dxVar.f7410g != null) {
                dn.f7349p.mo6217a(dpVar, 3, dxVar.f7410g);
            }
            if (dxVar.f7411h != null) {
                dn.f7349p.mo6217a(dpVar, 4, dxVar.f7411h);
            }
            if (dxVar.f7412i != null) {
                dn.f7349p.mo6217a(dpVar, 5, dxVar.f7412i);
            }
            dpVar.m7469a(dxVar.m7406a());
        }

        C2878b() {
            super(dk.LENGTH_DELIMITED, dx.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2877a c2877a = new C2877a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2877a.f7401c = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 2:
                            c2877a.f7402d = (Integer) dn.f7337d.mo6214a(c2874do);
                            break;
                        case 3:
                            c2877a.f7403e = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 4:
                            c2877a.f7404f = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 5:
                            c2877a.f7405g = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2877a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2877a.m7591b();
            }
        }
    }

    public dx(String str, Integer num, String str2, String str3, String str4, hx hxVar) {
        super(f7406c, hxVar);
        this.f7408e = str;
        this.f7409f = num;
        this.f7410g = str2;
        this.f7411h = str3;
        this.f7412i = str4;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof dx)) {
            return false;
        }
        dx dxVar = (dx) other;
        if (m7406a().equals(dxVar.m7406a()) && ds.m7478a(this.f7408e, dxVar.f7408e) && ds.m7478a(this.f7409f, dxVar.f7409f) && ds.m7478a(this.f7410g, dxVar.f7410g) && ds.m7478a(this.f7411h, dxVar.f7411h) && ds.m7478a(this.f7412i, dxVar.f7412i)) {
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
        int hashCode = ((this.f7408e != null ? this.f7408e.hashCode() : 0) + (m7406a().hashCode() * 37)) * 37;
        if (this.f7409f != null) {
            i2 = this.f7409f.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.f7410g != null) {
            i2 = this.f7410g.hashCode();
        } else {
            i2 = 0;
        }
        hashCode = (i2 + hashCode) * 37;
        if (this.f7411h != null) {
            i2 = this.f7411h.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.f7412i != null) {
            i = this.f7412i.hashCode();
        }
        i2 += i;
        this.f7364b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f7408e != null) {
            stringBuilder.append(", pkgVer=").append(this.f7408e);
        }
        if (this.f7409f != null) {
            stringBuilder.append(", pkgRev=").append(this.f7409f);
        }
        if (this.f7410g != null) {
            stringBuilder.append(", dataVer=").append(this.f7410g);
        }
        if (this.f7411h != null) {
            stringBuilder.append(", installer=").append(this.f7411h);
        }
        if (this.f7412i != null) {
            stringBuilder.append(", store=").append(this.f7412i);
        }
        return stringBuilder.replace(0, 2, "App{").append('}').toString();
    }
}
