// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class f implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ AdViewControllerImpl b;
    
    f(final AdViewControllerImpl b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (this.b.z != null) {
                this.b.z.failedToReceiveAd(this.a);
            }
        }
        catch (Throwable t) {
            this.b.e.userError("AppLovinAdView", "Exception while running app load  callback", t);
        }
    }
}
