package com.applovin.impl.adview;

import android.content.Context;
import com.applovin.sdk.AppLovinSdk;

public class ch extends ak {
    /* renamed from: c */
    private float f1856c = 30.0f;
    /* renamed from: d */
    private float f1857d = 1.0f;

    public ch(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
    }

    /* renamed from: a */
    public void m2072a(float f) {
        this.f1857d = f;
    }

    /* renamed from: a */
    public void mo4047a(int i) {
        m2072a(((float) i) / this.f1856c);
    }
}
