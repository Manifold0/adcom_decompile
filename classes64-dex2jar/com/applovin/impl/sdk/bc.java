// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class bc implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ ba b;
    
    bc(final ba b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.b.failedToReceiveAd(this.a);
    }
}
