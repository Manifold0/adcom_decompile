package com.applovin.impl.adview;

import com.facebook.ads.AudienceNetworkActivity;

/* renamed from: com.applovin.impl.adview.i */
class C1255i implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1890a;

    private C1255i(AdViewControllerImpl adViewControllerImpl) {
        this.f1890a = adViewControllerImpl;
    }

    public void run() {
        if (this.f1890a.f1690l != null) {
            try {
                this.f1890a.f1690l.loadDataWithBaseURL("/", "<html></html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null, "");
            } catch (Exception e) {
            }
        }
    }
}
