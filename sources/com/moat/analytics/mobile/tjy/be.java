package com.moat.analytics.mobile.tjy;

import android.view.View;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.reflect.Method;
import java.util.Map;

class be implements bc {
    /* renamed from: a */
    private static final C2749a f6687a;
    /* renamed from: b */
    private static final C2749a f6688b;
    /* renamed from: c */
    private static final C2749a f6689c;
    /* renamed from: d */
    private static final C2749a f6690d;

    static {
        C2749a a;
        Exception e;
        C2749a a2 = C2749a.m6883a();
        C2749a a3 = C2749a.m6883a();
        C2749a a4 = C2749a.m6883a();
        C2749a a5 = C2749a.m6883a();
        try {
            Class cls = ReactiveVideoTracker.class;
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, View.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{MoatAdEvent.class});
            a2 = C2749a.m6884a(method);
            a3 = C2749a.m6884a(method2);
            a = C2749a.m6884a(method3);
            try {
                a5 = C2749a.m6884a(method4);
            } catch (NoSuchMethodException e2) {
                e = e2;
                C2747a.m6882a(e);
                f6687a = a2;
                f6688b = a3;
                f6689c = a;
                f6690d = a5;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a4;
            e = exception;
            C2747a.m6882a(e);
            f6687a = a2;
            f6688b = a3;
            f6689c = a;
            f6690d = a5;
        }
        f6687a = a2;
        f6688b = a3;
        f6689c = a;
        f6690d = a5;
    }

    be() {
    }

    /* renamed from: a */
    public Class mo6083a() {
        return ReactiveVideoTracker.class;
    }
}
