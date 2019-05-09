package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.h */
class C1254h implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1889a;

    private C1254h(AdViewControllerImpl adViewControllerImpl) {
        this.f1889a = adViewControllerImpl;
    }

    public void run() {
        if (this.f1889a.f1690l != null) {
            this.f1889a.f1690l.setVisibility(8);
        }
    }
}
