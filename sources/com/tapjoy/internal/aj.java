package com.tapjoy.internal;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public final class aj extends ai {
    /* renamed from: b */
    private final AnimationSet f7174b = ((AnimationSet) this.a);

    public aj() {
        super(new AnimationSet(true));
    }

    /* renamed from: a */
    public final aj m7164a(Animation animation) {
        this.f7174b.addAnimation(animation);
        return this;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Animation mo6175a() {
        return this.f7174b;
    }
}
