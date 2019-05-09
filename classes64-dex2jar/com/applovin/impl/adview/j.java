// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.impl.sdk.g;
import com.applovin.impl.sdk.ar;
import com.applovin.sdk.AppLovinAdSize;
import android.view.View;

class j implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    private j(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.p != null) {
            if (this.a.l == null) {
                this.a.c.getLogger().userError("AppLovinAdView", "Unable to render advertisement for ad #" + this.a.p.getAdIdNumber() + ". Please make sure you are not calling AppLovinAdView.destroy() prematurely.");
                return;
            }
            this.a.e.d("AppLovinAdView", "Rendering advertisement ad for #" + this.a.p.getAdIdNumber() + " over placement: \"" + this.a.h + "\"...");
            b((View)this.a.l, this.a.p.getSize());
            this.a.l.a(this.a.p, this.a.h);
            if (this.a.p.getSize() != AppLovinAdSize.INTERSTITIAL && !this.a.x && !(this.a.p instanceof ar)) {
                this.a.i = new g(this.a.p, this.a.c);
                this.a.i.a();
                this.a.l.a(this.a.i);
            }
        }
    }
}
