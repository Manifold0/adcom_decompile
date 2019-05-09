// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.AlphaAnimation;

class bd implements Runnable
{
    final /* synthetic */ az a;
    
    bd(final az a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (this.a.k) {
                this.a.y.setVisibility(0);
                return;
            }
            this.a.k = true;
            if (this.a.n() && this.a.z != null) {
                this.a.z.setVisibility(0);
                this.a.z.bringToFront();
            }
            this.a.y.setVisibility(0);
            this.a.y.bringToFront();
            final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long)this.a.settingsProxy.d());
            alphaAnimation.setRepeatCount(0);
            this.a.y.startAnimation((Animation)alphaAnimation);
        }
        catch (Throwable t) {
            this.a.dismiss();
        }
    }
}
