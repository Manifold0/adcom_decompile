package com.moat.analytics.mobile.tjy;

import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import com.moat.analytics.mobile.tjy.base.functional.C2749a;

class bk implements bc {
    /* renamed from: a */
    private static final C2749a f6703a;

    static {
        C2749a a = C2749a.m6883a();
        try {
            a = C2749a.m6884a(WebAdTracker.class.getMethod("track", new Class[0]));
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
        f6703a = a;
    }

    bk() {
    }

    /* renamed from: a */
    public Class mo6083a() {
        return WebAdTracker.class;
    }
}
