// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebSettings;

class w implements Runnable
{
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;
    
    w(final n c, final WebSettings a, final Boolean b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.setAllowUniversalAccessFromFileURLs((boolean)this.b);
    }
}
