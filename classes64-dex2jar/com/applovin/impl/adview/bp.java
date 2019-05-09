// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bp implements Runnable
{
    final /* synthetic */ az a;
    
    bp(final az a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        final n adWebView = ((AdViewControllerImpl)this.a.a.getAdViewController()).getAdWebView();
        if (adWebView != null) {
            adWebView.a("javascript:al_onPoststitialShow();");
        }
    }
}
