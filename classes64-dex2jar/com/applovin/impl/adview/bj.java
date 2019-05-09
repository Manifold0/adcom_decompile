// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;

class bj implements Runnable
{
    final /* synthetic */ cm a;
    final /* synthetic */ az b;
    
    bj(final az b, final cm a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.a((View)this.b.G, false, this.a.h());
    }
}
