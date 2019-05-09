package com.tapjoy.internal;

import com.tapjoy.internal.dl.C2863a;
import java.util.List;

public final class ei extends dl {
    /* renamed from: c */
    public static final dn f7589c = new C2900b();
    /* renamed from: d */
    public final List f7590d;

    /* renamed from: com.tapjoy.internal.ei$a */
    public static final class C2899a extends C2863a {
        /* renamed from: c */
        public List f7588c = ds.m7476a();

        /* renamed from: b */
        public final ei m7637b() {
            return new ei(this.f7588c, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.ei$b */
    static final class C2900b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            ei eiVar = (ei) obj;
            return eh.f7582c.m7390a().mo6216a(1, eiVar.f7590d) + eiVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            ei eiVar = (ei) obj;
            eh.f7582c.m7390a().mo6217a(dpVar, 1, eiVar.f7590d);
            dpVar.m7469a(eiVar.m7406a());
        }

        C2900b() {
            super(dk.LENGTH_DELIMITED, ei.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2899a c2899a = new C2899a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2899a.f7588c.add(eh.f7582c.mo6214a(c2874do));
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2899a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2899a.m7637b();
            }
        }
    }

    public ei(List list) {
        this(list, hx.f8185b);
    }

    public ei(List list, hx hxVar) {
        super(f7589c, hxVar);
        this.f7590d = ds.m7477a("pushes", list);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ei)) {
            return false;
        }
        ei eiVar = (ei) other;
        if (m7406a().equals(eiVar.m7406a()) && this.f7590d.equals(eiVar.f7590d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (m7406a().hashCode() * 37) + this.f7590d.hashCode();
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.f7590d.isEmpty()) {
            stringBuilder.append(", pushes=").append(this.f7590d);
        }
        return stringBuilder.replace(0, 2, "PushList{").append('}').toString();
    }
}
