// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.AlphaAnimation;
import com.applovin.impl.sdk.ee;

class ax implements Runnable
{
    final /* synthetic */ ar a;
    
    ax(final ar a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (this.a.h == null) {
                this.a.c();
            }
            this.a.h.setVisibility(0);
            this.a.h.bringToFront();
            final ee ee = new ee(this.a.b);
            final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(ee.Q());
            alphaAnimation.setAnimationListener((Animation$AnimationListener)new ay(this));
            this.a.h.startAnimation((Animation)alphaAnimation);
        }
        catch (Throwable t) {
            this.a.c.e("ExpandedAdDialog", "Unable to fade in close button", t);
            this.a.c();
        }
    }
}
