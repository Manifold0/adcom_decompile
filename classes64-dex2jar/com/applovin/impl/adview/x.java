// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebSettings;

class x implements Runnable
{
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Integer b;
    final /* synthetic */ n c;
    
    x(final n c, final WebSettings a, final Integer b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.setMixedContentMode((int)this.b);
    }
}
