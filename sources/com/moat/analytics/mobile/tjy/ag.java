package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.reflect.Method;
import java.util.Map;

class ag implements bc {
    /* renamed from: a */
    private static final C2749a f6628a;
    /* renamed from: b */
    private static final C2749a f6629b;

    static {
        C2749a a;
        Exception e;
        C2749a a2 = C2749a.m6883a();
        C2749a a3 = C2749a.m6883a();
        try {
            Method method = NativeDisplayTracker.class.getMethod("track", new Class[]{Map.class});
            Method method2 = NativeDisplayTracker.class.getMethod("stopTracking", new Class[0]);
            a = C2749a.m6884a(method);
            try {
                a3 = C2749a.m6884a(method2);
            } catch (NoSuchMethodException e2) {
                e = e2;
                C2747a.m6882a(e);
                f6628a = a;
                f6629b = a3;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a2;
            e = exception;
            C2747a.m6882a(e);
            f6628a = a;
            f6629b = a3;
        }
        f6628a = a;
        f6629b = a3;
    }

    ag() {
    }

    /* renamed from: a */
    public Class mo6083a() {
        return NativeDisplayTracker.class;
    }
}
