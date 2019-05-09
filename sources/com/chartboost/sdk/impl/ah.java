package com.chartboost.sdk.impl;

import android.os.Handler;
import com.chartboost.sdk.Libraries.C1382i;
import java.util.concurrent.Executor;

public class ah {
    /* renamed from: a */
    private final Executor f2997a;
    /* renamed from: b */
    private final Executor f2998b;
    /* renamed from: c */
    private final ao f2999c;
    /* renamed from: d */
    private final ai f3000d;
    /* renamed from: e */
    private final C1382i f3001e;
    /* renamed from: f */
    private final Handler f3002f;

    public ah(Executor executor, ao aoVar, ai aiVar, C1382i c1382i, Handler handler, Executor executor2) {
        this.f2997a = executor2;
        this.f2998b = executor;
        this.f2999c = aoVar;
        this.f3000d = aiVar;
        this.f3001e = c1382i;
        this.f3002f = handler;
    }

    /* renamed from: a */
    public <T> void m3371a(ad<T> adVar) {
        this.f2997a.execute(new an(this.f2998b, this.f2999c, this.f3000d, this.f3001e, this.f3002f, adVar));
    }
}
