package com.facebook.ads.internal.view.component.p058a;

import android.net.Uri;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p027a.C1850i;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.component.a.g */
public class C2281g {
    /* renamed from: a */
    public static C2286l m5865a(C2279e c2279e, int i, int i2, boolean z) {
        C1876h a = c2279e.m5860k() == 1 ? c2279e.m5856g().m4306b().m4212a() : c2279e.m5856g().m4306b().m4213b();
        C1880l c1880l = (C1880l) c2279e.m5856g().m4308d().get(0);
        C1842b a2 = C1843c.m4143a(c2279e.m5850a(), c2279e.m5851b(), c2279e.m5856g().m4307c(), Uri.parse(((C1880l) c2279e.m5856g().m4308d().get(0)).m4316b().m4295a()), new HashMap(), false, true);
        if (!((C1880l) c2279e.m5856g().m4308d().get(0)).m4318d() || !(a2 instanceof C1850i)) {
            return null;
        }
        C2286l c2287h = new C2287h(c2279e, a, i, i2, (C1850i) a2, z);
        c2287h.m5894a(c1880l);
        return c2287h;
    }
}
