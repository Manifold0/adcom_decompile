package com.tapjoy.internal;

import com.ironsource.eventsmodule.DataBaseEventsStorage.EventEntry;
import com.tapjoy.internal.dz.C2881a;
import java.util.Map;

public final class hn extends hl {
    /* renamed from: c */
    private final C2881a f8139c = new C2881a();
    /* renamed from: d */
    private eb f8140d = null;

    /* renamed from: c */
    public final String mo6338c() {
        return this.f8140d == eb.USAGES ? "api/v1/usages" : "api/v1/cevs";
    }

    /* renamed from: a */
    public final boolean m8077a(dy dyVar) {
        if (this.f8140d == null) {
            this.f8140d = dyVar.f7461n;
        } else if (dyVar.f7461n != this.f8140d) {
            return false;
        }
        this.f8139c.f7474c.add(dyVar);
        return true;
    }

    /* renamed from: g */
    public final int m8080g() {
        return this.f8139c.f7474c.size();
    }

    /* renamed from: e */
    public final Map mo6336e() {
        Map e = super.mo6336e();
        e.put(EventEntry.TABLE_NAME, new br(gs.m7992a(this.f8139c.m7600b())));
        return e;
    }
}
