package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

class be implements Runnable {
    /* renamed from: a */
    final /* synthetic */ az f1797a;

    be(az azVar) {
        this.f1797a = azVar;
    }

    public void run() {
        try {
            if (!this.f1797a.f1542l && this.f1797a.f1521A != null) {
                this.f1797a.f1542l = true;
                this.f1797a.f1521A.setVisibility(0);
                Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long) this.f1797a.settingsProxy.m2703d());
                alphaAnimation.setRepeatCount(0);
                this.f1797a.f1521A.startAnimation(alphaAnimation);
                if (this.f1797a.m1737n() && this.f1797a.f1522B != null) {
                    this.f1797a.f1522B.setVisibility(0);
                    this.f1797a.f1522B.bringToFront();
                }
            }
        } catch (Throwable th) {
            this.f1797a.logger.mo4178w("InterActivity", "Unable to show skip button: " + th);
        }
    }
}
