package com.applovin.impl.adview;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.applovin.impl.sdk.ee;

class ax implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ar f1790a;

    ax(ar arVar) {
        this.f1790a = arVar;
    }

    public void run() {
        try {
            if (this.f1790a.f1784h == null) {
                this.f1790a.m2030c();
            }
            this.f1790a.f1784h.setVisibility(0);
            this.f1790a.f1784h.bringToFront();
            ee eeVar = new ee(this.f1790a.f1778b);
            Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(eeVar.m2690Q());
            alphaAnimation.setAnimationListener(new ay(this));
            this.f1790a.f1784h.startAnimation(alphaAnimation);
        } catch (Throwable th) {
            this.f1790a.f1779c.mo4174e("ExpandedAdDialog", "Unable to fade in close button", th);
            this.f1790a.m2030c();
        }
    }
}
