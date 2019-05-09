package com.applovin.impl.adview;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class bl implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ View f1808a;
    /* renamed from: b */
    final /* synthetic */ boolean f1809b;
    /* renamed from: c */
    final /* synthetic */ az f1810c;

    bl(az azVar, View view, boolean z) {
        this.f1810c = azVar;
        this.f1808a = view;
        this.f1809b = z;
    }

    public void onAnimationEnd(Animation animation) {
        if (!this.f1809b) {
            this.f1808a.setVisibility(4);
        }
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        this.f1808a.setVisibility(0);
    }
}
