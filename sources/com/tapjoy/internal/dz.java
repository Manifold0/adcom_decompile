package com.tapjoy.internal;

import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.tapjoy.internal.dl.C2863a;
import java.util.List;

public final class dz extends dl {
    /* renamed from: c */
    public static final dn f7475c = new C2882b();
    /* renamed from: d */
    public final List f7476d;

    /* renamed from: com.tapjoy.internal.dz$a */
    public static final class C2881a extends C2863a {
        /* renamed from: c */
        public List f7474c = ds.m7476a();

        /* renamed from: b */
        public final dz m7600b() {
            return new dz(this.f7474c, super.m7405a());
        }
    }

    /* renamed from: com.tapjoy.internal.dz$b */
    static final class C2882b extends dn {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            dz dzVar = (dz) obj;
            return dy.f7438c.m7390a().mo6216a(1, dzVar.f7476d) + dzVar.m7406a().mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dz dzVar = (dz) obj;
            dy.f7438c.m7390a().mo6217a(dpVar, 1, dzVar.f7476d);
            dpVar.m7469a(dzVar.m7406a());
        }

        C2882b() {
            super(dk.LENGTH_DELIMITED, dz.class);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            C2881a c2881a = new C2881a();
            long a = c2874do.m7455a();
            while (true) {
                int b = c2874do.m7457b();
                if (b != -1) {
                    switch (b) {
                        case 1:
                            c2881a.f7474c.add(dy.f7438c.mo6214a(c2874do));
                            break;
                        default:
                            dk c = c2874do.m7458c();
                            c2881a.m7403a(b, c, c.m7402a().mo6214a(c2874do));
                            break;
                    }
                }
                c2874do.m7456a(a);
                return c2881a.m7600b();
            }
        }
    }

    public dz(List list, hx hxVar) {
        super(f7475c, hxVar);
        this.f7476d = ds.m7477a(EventEntry.TABLE_NAME, list);
    }

    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof dz)) {
            return false;
        }
        dz dzVar = (dz) other;
        if (m7406a().equals(dzVar.m7406a()) && this.f7476d.equals(dzVar.f7476d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f7364b;
        if (i != 0) {
            return i;
        }
        i = (m7406a().hashCode() * 37) + this.f7476d.hashCode();
        this.f7364b = i;
        return i;
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!this.f7476d.isEmpty()) {
            stringBuilder.append(", events=").append(this.f7476d);
        }
        return stringBuilder.replace(0, 2, "EventBatch{").append('}').toString();
    }
}
