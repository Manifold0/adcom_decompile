package com.facebook.ads.internal.p025w.p026b;

import java.util.Random;

/* renamed from: com.facebook.ads.internal.w.b.q */
public class C2588q {
    /* renamed from: a */
    public static String m6654a(int i) {
        return (i > 0 && new Random().nextFloat() < 1.0f / ((float) i)) ? C2589s.m6657a(Thread.currentThread().getStackTrace()) : null;
    }
}
