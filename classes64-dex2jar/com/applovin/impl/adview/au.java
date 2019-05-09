// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;

class au implements Runnable
{
    final /* synthetic */ ar a;
    
    au(final ar a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.g.removeView((View)this.a.d);
        ar.c(this.a);
    }
}
