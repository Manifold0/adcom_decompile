package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

class bd implements Runnable {
    /* renamed from: a */
    final /* synthetic */ az f1796a;

    bd(az azVar) {
        this.f1796a = azVar;
    }

    public void run() {
        try {
            if (this.f1796a.f1541k) {
                this.f1796a.f1555y.setVisibility(0);
                return;
            }
            this.f1796a.f1541k = true;
            if (this.f1796a.m1737n() && this.f1796a.f1556z != null) {
                this.f1796a.f1556z.setVisibility(0);
                this.f1796a.f1556z.bringToFront();
            }
            this.f1796a.f1555y.setVisibility(0);
            this.f1796a.f1555y.bringToFront();
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) this.f1796a.settingsProxy.m2703d());
            alphaAnimation.setRepeatCount(0);
            this.f1796a.f1555y.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.f1796a.dismiss();
        }
    }
}
