// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class aa implements Runnable
{
    final /* synthetic */ n a;
    
    aa(final n a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.loadUrl("about:blank");
    }
}
