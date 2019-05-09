package com.applovin.impl.sdk;

import android.content.Context;

public class ac {
    /* renamed from: a */
    public static Boolean m2209a(Context context) {
        return m2210a(ef.f2446g, context);
    }

    /* renamed from: a */
    private static Boolean m2210a(ef<Boolean> efVar, Context context) {
        return (Boolean) eg.m2733b((ef) efVar, null, context);
    }

    /* renamed from: a */
    private static boolean m2211a(ef<Boolean> efVar, Boolean bool, Context context) {
        Boolean a = m2210a((ef) efVar, context);
        eg.m2731a((ef) efVar, (Object) bool, context);
        return a == null || a != bool;
    }

    /* renamed from: a */
    public static boolean m2212a(boolean z, Context context) {
        return m2211a(ef.f2446g, Boolean.valueOf(z), context);
    }

    /* renamed from: b */
    public static Boolean m2213b(Context context) {
        return m2210a(ef.f2447h, context);
    }

    /* renamed from: b */
    public static boolean m2214b(boolean z, Context context) {
        return m2211a(ef.f2447h, Boolean.valueOf(z), context);
    }
}
