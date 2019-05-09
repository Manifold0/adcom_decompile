// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.view.animation.TranslateAnimation;
import android.view.animation.Animation;

public final class am
{
    protected int a;
    protected float b;
    protected int c;
    protected float d;
    protected int e;
    protected float f;
    protected int g;
    protected float h;
    
    public am() {
        this.a = 0;
        this.b = 0.0f;
        this.c = 0;
        this.d = 0.0f;
        this.e = 0;
        this.f = 0.0f;
        this.g = 0;
        this.h = 0.0f;
    }
    
    public final Animation a() {
        return (Animation)new TranslateAnimation(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }
    
    public final am a(final float b) {
        this.a = 1;
        this.b = b;
        return this;
    }
    
    public final am b(final float f) {
        this.e = 1;
        this.f = f;
        return this;
    }
}
