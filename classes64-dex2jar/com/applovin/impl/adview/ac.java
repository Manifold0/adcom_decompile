// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebView;

class ac implements Runnable
{
    final /* synthetic */ n a;
    
    ac(final n a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        WebView.setWebContentsDebuggingEnabled(true);
    }
}
