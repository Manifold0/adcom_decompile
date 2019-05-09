package com.applovin.impl.adview;

class bp implements Runnable {
    /* renamed from: a */
    final /* synthetic */ az f1814a;

    bp(az azVar) {
        this.f1814a = azVar;
    }

    public void run() {
        C1260n adWebView = ((AdViewControllerImpl) this.f1814a.f1531a.getAdViewController()).getAdWebView();
        if (adWebView != null) {
            adWebView.m2115a("javascript:al_onPoststitialShow();");
        }
    }
}
