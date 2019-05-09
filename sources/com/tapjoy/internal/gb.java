package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.tapjoy.internal.dy.C2879a;
import com.tapjoy.internal.ea.C2884a;
import com.tapjoy.internal.eg.C2895a;
import java.util.Map;
import java.util.Map.Entry;

public final class gb {
    /* renamed from: a */
    final gf f7838a;
    /* renamed from: b */
    final ga f7839b;
    /* renamed from: c */
    long f7840c;
    /* renamed from: d */
    private int f7841d = 1;
    /* renamed from: e */
    private final C2884a f7842e = new C2884a();

    gb(gf gfVar, ga gaVar) {
        this.f7838a = gfVar;
        this.f7839b = gaVar;
    }

    /* renamed from: a */
    public final void m7825a(String str, String str2, double d, String str3, String str4, String str5) {
        gf gfVar = this.f7838a;
        synchronized (gfVar) {
            int b;
            double a;
            Editor a2 = gfVar.f7894c.m7972a();
            if (str2.equals(gfVar.f7894c.f7942l.m8219a())) {
                b = gfVar.f7894c.f7943m.m8210b() + 1;
                gfVar.f7894c.f7943m.m8206a(a2, b);
                a = gfVar.f7894c.f7944n.m8203a() + d;
                gfVar.f7894c.f7944n.m8205a(a2, a);
                a2.commit();
            } else {
                gfVar.f7894c.f7942l.m8218a(a2, str2);
                gfVar.f7894c.f7943m.m8206a(a2, 1);
                gfVar.f7894c.f7944n.m8205a(a2, d);
                gfVar.f7894c.f7945o.m8212a(a2);
                gfVar.f7894c.f7946p.m8204a(a2);
                a2.commit();
                gfVar.f7893b.f7605l = str2;
                gfVar.f7893b.f7608o = null;
                gfVar.f7893b.f7609p = null;
                b = 1;
                a = d;
            }
            gfVar.f7893b.f7606m = Integer.valueOf(b);
            gfVar.f7893b.f7607n = Double.valueOf(a);
        }
        C2879a a3 = m7823a(eb.APP, "purchase");
        C2895a c2895a = new C2895a();
        c2895a.f7546c = str;
        if (str2 != null) {
            c2895a.f7549f = str2;
        }
        c2895a.f7548e = Double.valueOf(d);
        if (str5 != null) {
            c2895a.f7556m = str5;
        }
        if (str3 != null) {
            c2895a.f7558o = str3;
        }
        if (str4 != null) {
            c2895a.f7559p = str4;
        }
        a3.f7427p = c2895a.m7628b();
        m7824a(a3);
        gf gfVar2 = this.f7838a;
        long longValue = a3.f7416e.longValue();
        synchronized (gfVar2) {
            Editor a4 = gfVar2.f7894c.m7972a();
            gfVar2.f7894c.f7945o.m8213a(a4, longValue);
            gfVar2.f7894c.f7946p.m8205a(a4, d);
            a4.commit();
            gfVar2.f7893b.f7608o = Long.valueOf(longValue);
            gfVar2.f7893b.f7609p = Double.valueOf(d);
        }
    }

    /* renamed from: a */
    public final void m7827a(String str, String str2, String str3, String str4, Map map) {
        C2879a a = m7823a(eb.CUSTOM, str2);
        a.f7431t = str;
        a.f7432u = str3;
        a.f7433v = str4;
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                a.f7434w.add(new ec((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        m7824a(a);
    }

    /* renamed from: a */
    public final void m7826a(String str, String str2, int i, long j, long j2, Map map) {
        C2879a a = m7823a(eb.USAGES, str);
        a.f7435x = str2;
        a.f7436y = Integer.valueOf(i);
        a.f7437z = Long.valueOf(j);
        a.f7413A = Long.valueOf(j2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                a.f7434w.add(new ec((String) entry.getKey(), (Long) entry.getValue()));
            }
        }
        m7824a(a);
    }

    /* renamed from: a */
    public final C2879a m7823a(eb ebVar, String str) {
        ee b = this.f7838a.m7932b();
        C2879a c2879a = new C2879a();
        c2879a.f7418g = gf.f7891a;
        c2879a.f7414c = ebVar;
        c2879a.f7415d = str;
        if (C2999y.m8234c()) {
            c2879a.f7416e = Long.valueOf(C2999y.m8233b());
            c2879a.f7417f = Long.valueOf(System.currentTimeMillis());
        } else {
            c2879a.f7416e = Long.valueOf(System.currentTimeMillis());
            c2879a.f7419h = Long.valueOf(SystemClock.elapsedRealtime());
        }
        c2879a.f7421j = b.f7536d;
        c2879a.f7422k = b.f7537e;
        c2879a.f7423l = b.f7538f;
        return c2879a;
    }

    /* renamed from: a */
    public final synchronized void m7824a(C2879a c2879a) {
        if (c2879a.f7414c != eb.USAGES) {
            int i = this.f7841d;
            this.f7841d = i + 1;
            c2879a.f7425n = Integer.valueOf(i);
            if (this.f7842e.f7478c != null) {
                c2879a.f7426o = this.f7842e.m7604b();
            }
            this.f7842e.f7478c = c2879a.f7414c;
            this.f7842e.f7479d = c2879a.f7415d;
            this.f7842e.f7480e = c2879a.f7431t;
        }
        ga gaVar = this.f7839b;
        dy b = c2879a.m7595b();
        try {
            go goVar = gaVar.f7833a;
            synchronized (goVar.f7962a) {
                try {
                    goVar.f7963b.add(b);
                } catch (Exception e) {
                    goVar.m7977a();
                    try {
                        goVar.f7963b.add(b);
                    } catch (Exception e2) {
                    }
                }
            }
            if (gaVar.f7834b == null) {
                gaVar.f7833a.flush();
            } else if (fz.f7823a || b.f7461n != eb.CUSTOM) {
                gaVar.m7822a(true);
            } else {
                gaVar.m7822a(false);
            }
        } catch (Exception e3) {
        }
    }
}
