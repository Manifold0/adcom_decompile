// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebSettings;

class s implements Runnable
{
    final /* synthetic */ WebSettings a;
    final /* synthetic */ Boolean b;
    final /* synthetic */ n c;
    
    s(final n c, final WebSettings a, final Boolean b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.setSaveFormData((boolean)this.b);
    }
}
