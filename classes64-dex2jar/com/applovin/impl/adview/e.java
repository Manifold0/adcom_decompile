// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;

class e implements Runnable
{
    final /* synthetic */ AppLovinAd a;
    final /* synthetic */ AdViewControllerImpl b;
    
    e(final AdViewControllerImpl b, final AppLovinAd a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        try {
            if (this.b.z != null) {
                this.b.z.adReceived(this.a);
            }
        }
        catch (Throwable t) {
            this.b.e.userError("AppLovinSdk", "Exception while running ad load callback: " + t.getMessage());
        }
    }
}
