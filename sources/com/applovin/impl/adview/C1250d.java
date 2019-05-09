package com.applovin.impl.adview;

/* renamed from: com.applovin.impl.adview.d */
class C1250d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1883a;

    C1250d(AdViewControllerImpl adViewControllerImpl) {
        this.f1883a = adViewControllerImpl;
    }

    public void run() {
        this.f1883a.m1957d();
        if (this.f1883a.f1680b != null && this.f1883a.f1690l != null && this.f1883a.f1690l.getParent() == null) {
            this.f1883a.f1680b.addView(this.f1883a.f1690l);
            AdViewControllerImpl.m1953b(this.f1883a.f1690l, this.f1883a.f1694p.getSize());
        }
    }
}
