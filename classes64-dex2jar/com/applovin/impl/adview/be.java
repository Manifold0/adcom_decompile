// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;

class be implements Runnable
{
    final /* synthetic */ az a;
    
    be(final az a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (!this.a.l && this.a.A != null) {
                this.a.l = true;
                this.a.A.setVisibility(0);
                final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation.setDuration((long)this.a.settingsProxy.d());
                alphaAnimation.setRepeatCount(0);
                this.a.A.startAnimation((Animation)alphaAnimation);
                if (this.a.n() && this.a.B != null) {
                    this.a.B.setVisibility(0);
                    this.a.B.bringToFront();
                }
            }
        }
        catch (Throwable t) {
            this.a.logger.w("InterActivity", "Unable to show skip button: " + t);
        }
    }
}
