package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;

public final class ec extends dl {
    /* renamed from: c */
    public static final dn f7490c = new C2888b();
    /* renamed from: d */
    public static final Long f7491d = Long.valueOf(0);
    /* renamed from: e */
    public final String f7492e;
    /* renamed from: f */
    public final Long f7493f;

    /* renamed from: com.tapjoy.internal.ec$a */
    public static final class C2887a extends C2863a {
        /* renamed from: c */
        public String f7488c;
        /* renamed from: d */
        public Long f7489d;

        /* renamed from: b */
        public final ec m7612b() {
            if (this.f7488c != null && this.f7489d != null) {
                return new ec(this.f7488c, this.f7489d, super.m7405a());
            }
            throw ds.m7475a(this.f7488c, "name", this.f7489d, "value");
        }
    }

    /* renamed from: com.tapjoy.internal.ec$b */
    static final class C2888b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            ec ecVar = (ec) obj;
            return (dn.f7349p.mo6216a(1, ecVar.f7492e) + dn.f7342i.mo6216a(2, ecVar.f7493f)) + ecVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ec ecVar = (ec) obj;
            dn.f7349p.mo6217a(dpVar, 1, ecVar.f7492e);
            dn.f7342i.mo6217a(dpVar, 2, ecVar.f7493f);
            dpVar.m7469a(ecVar.m7406a());
        }

        C2888b() {
            super(dk.LENGTH_DELIMITED, ec.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2887a c2887a = new C2887a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2887a.f7488c = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 2:
                            c2887a.f7489d = (Long) dn.f7342i.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2887a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2887a.m7612b();
            }
        }
    }

    public ec(String str, Long l) {
        this(str, l, hx.f8185b);
    }

    public ec(String str, Long l, hx hxVar) {
        super(f7490c, hxVar);
        this.f7492e = str;
        this.f7493f = l;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ec)) {
            return false;
        }
        ec ecVar = (ec) other;
        if (m7406a().equals(ecVar.m7406a()) && this.f7492e.equals(ecVar.f7492e) && this.f7493f.equals(ecVar.f7493f)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (((m7406a().hashCode() * 37) + this.f7492e.hashCode()) * 37) + this.f7493f.hashCode();
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", name=").append(this.f7492e);
        stringBuilder.append(", value=").append(this.f7493f);
        return stringBuilder.replace(0, 2, "EventValue{").append('}').toString();
    }
}
