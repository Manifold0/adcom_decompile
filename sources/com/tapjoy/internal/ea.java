package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;
import com.tapjoy.internal.dn.C2873a;

public final class ea extends dl {
    /* renamed from: c */
    public static final dn f7481c = new C2885b();
    /* renamed from: d */
    public static final eb f7482d = eb.APP;
    /* renamed from: e */
    public final eb f7483e;
    /* renamed from: f */
    public final String f7484f;
    /* renamed from: g */
    public final String f7485g;

    /* renamed from: com.tapjoy.internal.ea$a */
    public static final class C2884a extends C2863a {
        /* renamed from: c */
        public eb f7478c;
        /* renamed from: d */
        public String f7479d;
        /* renamed from: e */
        public String f7480e;

        /* renamed from: b */
        public final ea m7604b() {
            if (this.f7478c != null && this.f7479d != null) {
                return new ea(this.f7478c, this.f7479d, this.f7480e, super.m7405a());
            }
            throw ds.m7475a(this.f7478c, "type", this.f7479d, "name");
        }
    }

    /* renamed from: com.tapjoy.internal.ea$b */
    static final class C2885b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            ea eaVar = (ea) obj;
            return ((eaVar.f7485g != null ? dn.f7349p.mo6216a(3, eaVar.f7485g) : 0) + (dn.f7349p.mo6216a(2, eaVar.f7484f) + eb.ADAPTER.mo6216a(1, eaVar.f7483e))) + eaVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return C2885b.m7605b(c2874do);
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ea eaVar = (ea) obj;
            eb.ADAPTER.mo6217a(dpVar, 1, eaVar.f7483e);
            dn.f7349p.mo6217a(dpVar, 2, eaVar.f7484f);
            if (eaVar.f7485g != null) {
                dn.f7349p.mo6217a(dpVar, 3, eaVar.f7485g);
            }
            dpVar.m7469a(eaVar.m7406a());
        }

        C2885b() {
            super(dk.LENGTH_DELIMITED, ea.class);
        }

        /* renamed from: b */
        private static ea m7605b(C2874do c2874do) {
            C2884a c2884a = new C2884a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            try {
                                c2884a.f7478c = (eb) eb.ADAPTER.mo6214a(c2874do);
                                break;
                            } catch (C2873a e) {
                                c2884a.m7403a(b, dk.VARINT, Long.valueOf((long) e.f7368a));
                                break;
                            }
                        case 2:
                            c2884a.f7479d = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        case 3:
                            c2884a.f7480e = (String) dn.f7349p.mo6214a(c2874do);
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2884a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2884a.m7604b();
            }
        }
    }

    public ea(eb ebVar, String str, String str2, hx hxVar) {
        super(f7481c, hxVar);
        this.f7483e = ebVar;
        this.f7484f = str;
        this.f7485g = str2;
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ea)) {
            return false;
        }
        ea eaVar = (ea) other;
        if (m7406a().equals(eaVar.m7406a()) && this.f7483e.equals(eaVar.f7483e) && this.f7484f.equals(eaVar.f7484f) && ds.m7478a(this.f7485g, eaVar.f7485g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (this.f7485g != null ? this.f7485g.hashCode() : 0) + (((((m7406a().hashCode() * 37) + this.f7483e.hashCode()) * 37) + this.f7484f.hashCode()) * 37);
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(", type=").append(this.f7483e);
        stringBuilder.append(", name=").append(this.f7484f);
        if (this.f7485g != null) {
            stringBuilder.append(", category=").append(this.f7485g);
        }
        return stringBuilder.replace(0, 2, "EventGroup{").append('}').toString();
    }
}
