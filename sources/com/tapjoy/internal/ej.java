package com.tapjoy.internal;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.internal.dl.C2863a;
import java.util.List;

public final class ej extends dl {
    /* renamed from: c */
    public static final dn f7592c = new C2902b();
    /* renamed from: d */
    public final List f7593d;

    /* renamed from: com.tapjoy.internal.ej$a */
    public static final class C2901a extends C2863a {
        /* renamed from: c */
        public List f7591c = ds.m7476a();

        /* renamed from: b */
        public final ej m7641b() {
            return new ej(this.f7591c, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.ej$b */
    static final class C2902b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            ej ejVar = (ej) obj;
            return dn.f7349p.m7390a().mo6216a(1, ejVar.f7593d) + ejVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ej ejVar = (ej) obj;
            dn.f7349p.m7390a().mo6217a(dpVar, 1, ejVar.f7593d);
            dpVar.m7469a(ejVar.m7406a());
        }

        C2902b() {
            super(dk.LENGTH_DELIMITED, ej.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2901a c2901a = new C2901a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2901a.f7591c.add(dn.f7349p.mo6214a(c2874do));
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2901a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2901a.m7641b();
            }
        }
    }

    public ej(List list) {
        this(list, hx.f8185b);
    }

    public ej(List list, hx hxVar) {
        super(f7592c, hxVar);
        this.f7593d = ds.m7477a(MessengerShareContentUtility.ELEMENTS, list);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ej)) {
            return false;
        }
        ej ejVar = (ej) other;
        if (m7406a().equals(ejVar.m7406a()) && this.f7593d.equals(ejVar.f7593d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (m7406a().hashCode() * 37) + this.f7593d.hashCode();
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.f7593d.isEmpty()) {
            stringBuilder.append(", elements=").append(this.f7593d);
        }
        return stringBuilder.replace(0, 2, "StringList{").append('}').toString();
    }
}
