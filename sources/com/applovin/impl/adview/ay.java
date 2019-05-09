package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class ay implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ ax f1791a;

    ay(ax axVar) {
        this.f1791a = axVar;
    }

    public void onAnimationEnd(Animation animation) {
        this.f1791a.f1790a.f1784h.setClickable(true);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
    }
}
