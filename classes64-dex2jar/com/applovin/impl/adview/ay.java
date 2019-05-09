// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class ay implements Animation$AnimationListener
{
    final /* synthetic */ ax a;
    
    ay(final ax a) {
        this.a = a;
    }
    
    public void onAnimationEnd(final Animation animation) {
        this.a.a.h.setClickable(true);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}
