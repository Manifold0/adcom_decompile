// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.net.Uri;
import com.applovin.impl.sdk.bv;
import com.applovin.adview.AppLovinAdView;
import android.content.DialogInterface$OnDismissListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.sdk.ee;
import android.view.View;
import com.applovin.impl.sdk.gd;
import android.app.Activity;
import com.applovin.impl.sdk.m;

class b implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    b(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.s == null && this.a.p instanceof m && this.a.l != null) {
            final m m = (m)this.a.p;
            Object o;
            if (!((o = this.a.a) instanceof Activity)) {
                o = gd.a((View)this.a.l, this.a.c);
            }
            if (!(o instanceof Activity)) {
                this.a.e.userError("AppLovinAdView", "Unable to expand ad. No Activity found.");
                final Uri g = m.g();
                final ee ee = new ee(this.a.c);
                if (g != null && ee.ah()) {
                    this.a.d.trackAndLaunchClick(m, this.a.h, this.a.getParentView(), this.a, g);
                    if (this.a.i != null) {
                        this.a.i.b();
                    }
                }
                this.a.l.a("javascript:al_onFailedExpand();");
                return;
            }
            if (this.a.b != null) {
                this.a.b.removeView((View)this.a.l);
            }
            this.a.s = new ar(m, this.a.h, this.a.l, (Activity)o, this.a.c);
            this.a.s.setOnDismissListener((DialogInterface$OnDismissListener)new c(this));
            this.a.s.show();
            bv.a(this.a.B, this.a.p, (AppLovinAdView)this.a.b, this.a.c);
            if (this.a.i != null) {
                this.a.i.d();
            }
        }
    }
}
