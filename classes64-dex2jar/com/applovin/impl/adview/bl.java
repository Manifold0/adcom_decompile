// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.animation.Animation;
import android.view.View;
import android.view.animation.Animation$AnimationListener;

class bl implements Animation$AnimationListener
{
    final /* synthetic */ View a;
    final /* synthetic */ boolean b;
    final /* synthetic */ az c;
    
    bl(final az c, final View a, final boolean b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void onAnimationEnd(final Animation animation) {
        if (!this.b) {
            this.a.setVisibility(4);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        this.a.setVisibility(0);
    }
}
