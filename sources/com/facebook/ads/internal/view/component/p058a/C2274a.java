package com.facebook.ads.internal.view.component.p058a;

import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2295a;

/* renamed from: com.facebook.ads.internal.view.component.a.a */
public class C2274a {
    /* renamed from: a */
    private static final int f5356a = C2600x.f6419a.heightPixels;
    /* renamed from: b */
    private static final int f5357b = C2600x.f6419a.widthPixels;

    /* renamed from: a */
    public static float m5829a(C1880l c1880l) {
        int h = c1880l.m4317c().m4246h();
        int i = c1880l.m4317c().m4247i();
        return i > 0 ? ((float) h) / ((float) i) : -1.0f;
    }

    /* renamed from: a */
    public static boolean m5830a(double d) {
        return d < 0.9d;
    }

    /* renamed from: a */
    public static boolean m5831a(int i, int i2, double d) {
        if (i != 2) {
            if (!((f5356a - i2) - ((C2600x.m6678a(16) + (C2295a.a * 2)) + (C2280f.a * 2)) < ((int) (((double) (f5357b - (C2280f.a * 2))) / d)))) {
                return false;
            }
        }
        return true;
    }
}
