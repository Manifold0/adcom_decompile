package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;

public final class ef extends dl {
    /* renamed from: c */
    public static final dn f7542c = new C2894b();
    /* renamed from: d */
    public final String f7543d;
    /* renamed from: e */
    public final String f7544e;
    /* renamed from: f */
    public final String f7545f;

    /* renamed from: com.tapjoy.internal.ef$a */
    public static final class C2893a extends C2863a {
        /* renamed from: c */
        public String f7539c;
        /* renamed from: d */
        public String f7540d;
        /* renamed from: e */
        public String f7541e;

        /* renamed from: b */
        public final ef m7624b() {
            return new ef(this.f7539c, this.f7540d, this.f7541e, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.ef$b */
    static final class C2894b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            int a;
            int i = 0;
            ef efVar = (ef) obj;
            int a2 = efVar.f7543d != null ? dn.f7349p.mo6216a(1, efVar.f7543d) : 0;
            if (efVar.f7544e != null) {
                a = dn.f7349p.mo6216a(2, efVar.f7544e);
            } else {
                a = 0;
            }
            a2 += a;
            if (efVar.f7545f != null) {
                i = dn.f7349p.mo6216a(3, efVar.f7545f);
            }
            return (a2 + i) + efVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ef efVar = (ef) obj;
            if (efVar.f7543d != null) {
                dn.f7349p.mo6217a(dpVar, 1, efVar.f7543d);
            }
            if (efVar.f7544e != null) {
                dn.f7349p.mo6217a(dpVar, 2, efVar.f7544e);
            }
            if (efVar.f7545f != null) {
                dn.f7349p.mo6217a(dpVar, 3, efVar.f7545f);
            }
            dpVar.m7469a(efVar.m7406a());
        }

        C2894b() {
            super(dk.LENGTH_DELIMITED, ef.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2893a c2893a = new C2893a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2893a.f7539c = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 2:
                            c2893a.f7540d = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 3:
                            c2893a.f7541e = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2893a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2893a.m7624b();
            }
        }
    }

    public ef(String str, String str2, String str3) {
        this(str, str2, str3, hx.f8185b);
    }

    public ef(String str, String str2, String str3, hx hxVar) {
        super(f7542c, hxVar);
        this.f7543d = str;
        this.f7544e = str2;
        this.f7545f = str3;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ef)) {
            return false;
        }
        ef efVar = (ef) other;
        if (m7406a().equals(efVar.m7406a()) && ds.m7478a(this.f7543d, efVar.f7543d) && ds.m7478a(this.f7544e, efVar.f7544e) && ds.m7478a(this.f7545f, efVar.f7545f)) {
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
        int hashCode = ((this.f7543d != null ? this.f7543d.hashCode() : 0) + (m7406a().hashCode() * 37)) * 37;
        if (this.f7544e != null) {
            i2 = this.f7544e.hashCode();
        } else {
            i2 = 0;
        }
        i2 = (i2 + hashCode) * 37;
        if (this.f7545f != null) {
            i = this.f7545f.hashCode();
        }
        i2 += i;
        this.f7364b = i2;
        return i2;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.f7543d != null) {
            stringBuilder.append(", fq7Change=").append(this.f7543d);
        }
        if (this.f7544e != null) {
            stringBuilder.append(", fq30Change=").append(this.f7544e);
        }
        if (this.f7545f != null) {
            stringBuilder.append(", pushId=").append(this.f7545f);
        }
        return stringBuilder.replace(0, 2, "Meta{").append('}').toString();
    }
}
