package com.tapjoy.internal;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public final class am {
    /* renamed from: a */
    protected int f7192a = 0;
    /* renamed from: b */
    protected float f7193b = 0.0f;
    /* renamed from: c */
    protected int f7194c = 0;
    /* renamed from: d */
    protected float f7195d = 0.0f;
    /* renamed from: e */
    protected int f7196e = 0;
    /* renamed from: f */
    protected float f7197f = 0.0f;
    /* renamed from: g */
    protected int f7198g = 0;
    /* renamed from: h */
    protected float f7199h = 0.0f;

    /* renamed from: a */
    public final Animation m7166a() {
        return new TranslateAnimation(this.f7192a, this.f7193b, this.f7194c, this.f7195d, this.f7196e, this.f7197f, this.f7198g, this.f7199h);
    }

    /* renamed from: a */
    public final am m7167a(float f) {
        this.f7192a = 1;
        this.f7193b = f;
        return this;
    }

    /* renamed from: b */
    public final am m7168b(float f) {
        this.f7196e = 1;
        this.f7197f = f;
        return this;
    }
}
