// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;

public final class aj extends ai
{
    private final AnimationSet b;
    
    public aj() {
        super((Animation)new AnimationSet(true));
        this.b = (AnimationSet)this.a;
    }
    
    public final aj a(final Animation animation) {
        this.b.addAnimation(animation);
        return this;
    }
}
