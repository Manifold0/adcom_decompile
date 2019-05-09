// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class a implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    a(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.s != null) {
            this.a.e.d("AppLovinAdView", "Detaching expanded ad: " + this.a.s.a());
            this.a.t = this.a.s;
            this.a.s = null;
            this.a.a(this.a.f);
        }
    }
}
