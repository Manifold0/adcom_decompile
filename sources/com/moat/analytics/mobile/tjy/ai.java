package com.moat.analytics.mobile.tjy;

import android.media.MediaPlayer;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;
import java.lang.reflect.Method;
import java.util.Map;

class ai implements bc {
    /* renamed from: a */
    private static final C2749a f6648a;
    /* renamed from: b */
    private static final C2749a f6649b;
    /* renamed from: c */
    private static final C2749a f6650c;
    /* renamed from: d */
    private static final C2749a f6651d;
    /* renamed from: e */
    private static final C2749a f6652e;

    static {
        C2749a a;
        Exception e;
        C2749a a2 = C2749a.m6883a();
        C2749a a3 = C2749a.m6883a();
        C2749a a4 = C2749a.m6883a();
        C2749a a5 = C2749a.m6883a();
        C2749a a6 = C2749a.m6883a();
        try {
            Class cls = NativeVideoTracker.class;
            Method method = cls.getMethod("setDebug", new Class[]{Boolean.TYPE});
            Method method2 = cls.getMethod("trackVideoAd", new Class[]{Map.class, MediaPlayer.class, View.class});
            Method method3 = cls.getMethod("changeTargetView", new Class[]{View.class});
            Method method4 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            Method method5 = cls.getMethod("dispatchEvent", new Class[]{Map.class});
            a2 = C2749a.m6884a(method);
            a3 = C2749a.m6884a(method2);
            a4 = C2749a.m6884a(method3);
            a = C2749a.m6884a(method4);
            try {
                a5 = C2749a.m6884a(method5);
            } catch (NoSuchMethodException e2) {
                e = e2;
                C2747a.m6882a(e);
                f6648a = a2;
                f6649b = a3;
                f6650c = a4;
                f6651d = a;
                f6652e = a5;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            a = a6;
            e = exception;
            C2747a.m6882a(e);
            f6648a = a2;
            f6649b = a3;
            f6650c = a4;
            f6651d = a;
            f6652e = a5;
        }
        f6648a = a2;
        f6649b = a3;
        f6650c = a4;
        f6651d = a;
        f6652e = a5;
    }

    ai() {
    }

    /* renamed from: a */
    public Class mo6083a() {
        return NativeVideoTracker.class;
    }
}
