package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.C1286m;
import com.applovin.impl.sdk.bv;
import com.applovin.impl.sdk.ee;
import com.applovin.impl.sdk.gd;

/* renamed from: com.applovin.impl.adview.b */
class C1248b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ AdViewControllerImpl f1792a;

    C1248b(AdViewControllerImpl adViewControllerImpl) {
        this.f1792a = adViewControllerImpl;
    }

    public void run() {
        if (this.f1792a.f1697s == null && (this.f1792a.f1694p instanceof C1286m) && this.f1792a.f1690l != null) {
            C1286m c1286m = (C1286m) this.f1792a.f1694p;
            Context f = this.f1792a.f1679a;
            if (!(f instanceof Activity)) {
                f = gd.m2933a(this.f1792a.f1690l, this.f1792a.f1681c);
            }
            if (f instanceof Activity) {
                if (this.f1792a.f1680b != null) {
                    this.f1792a.f1680b.removeView(this.f1792a.f1690l);
                }
                this.f1792a.f1697s = new ar(c1286m, this.f1792a.f1686h, this.f1792a.f1690l, (Activity) f, this.f1792a.f1681c);
                this.f1792a.f1697s.setOnDismissListener(new C1249c(this));
                this.f1792a.f1697s.show();
                bv.m2399a(this.f1792a.f1676B, this.f1792a.f1694p, (AppLovinAdView) this.f1792a.f1680b, this.f1792a.f1681c);
                if (this.f1792a.f1687i != null) {
                    this.f1792a.f1687i.m2915d();
                    return;
                }
                return;
            }
            this.f1792a.f1683e.userError("AppLovinAdView", "Unable to expand ad. No Activity found.");
            Uri g = c1286m.mo4003g();
            ee eeVar = new ee(this.f1792a.f1681c);
            if (g != null && eeVar.ah()) {
                this.f1792a.f1682d.trackAndLaunchClick(c1286m, this.f1792a.f1686h, this.f1792a.getParentView(), this.f1792a, g);
                if (this.f1792a.f1687i != null) {
                    this.f1792a.f1687i.m2911b();
                }
            }
            this.f1792a.f1690l.m2115a("javascript:al_onFailedExpand();");
        }
    }
}
