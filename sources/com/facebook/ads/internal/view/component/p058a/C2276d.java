package com.facebook.ads.internal.view.component.p058a;

import android.net.Uri;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p027a.C1851j;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.component.a.d */
public final class C2276d {
    /* renamed from: a */
    public static C2259c m5833a(C2279e c2279e) {
        C2259c c2289j;
        C1876h a = c2279e.m5860k() == 1 ? c2279e.m5856g().m4306b().m4212a() : c2279e.m5856g().m4306b().m4213b();
        C1880l c1880l = (C1880l) c2279e.m5856g().m4308d().get(0);
        double a2 = (double) C2274a.m5829a(c1880l);
        boolean d = ((C1880l) c2279e.m5856g().m4308d().get(0)).m4318d();
        boolean a3 = C2274a.m5831a(c2279e.m5860k(), c2279e.m5859j(), a2);
        C1842b a4 = C1843c.m4142a(c2279e.m5850a(), c2279e.m5851b(), "", Uri.parse(((C1880l) c2279e.m5856g().m4308d().get(0)).m4316b().m4295a()), new HashMap());
        if (d && a4 != null && (a4 instanceof C1851j)) {
            c2289j = c2279e.m5860k() == 1 ? new C2289j(c2279e, a) : new C2288i(c2279e, a);
        } else if (a3) {
            c2289j = new C2275b(c2279e, a, c2279e.m5860k() == 2);
        } else {
            c2289j = new C2280f(c2279e, C2274a.m5830a(a2), a);
        }
        c2289j.mo5560a(c1880l, c2279e.m5856g().m4307c(), a2);
        return c2289j;
    }
}
