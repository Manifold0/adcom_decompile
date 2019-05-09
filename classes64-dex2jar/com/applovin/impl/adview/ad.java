// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebSettings$PluginState;
import android.webkit.WebSettings;

class ad implements Runnable
{
    final /* synthetic */ WebSettings a;
    final /* synthetic */ WebSettings$PluginState b;
    final /* synthetic */ n c;
    
    ad(final n c, final WebSettings a, final WebSettings$PluginState b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.a.setPluginState(this.b);
    }
}
