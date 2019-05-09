// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.adview.AppLovinAdView;
import android.net.Uri;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinPostbackListener;

class r implements AppLovinPostbackListener
{
    final /* synthetic */ AdViewControllerImpl a;
    final /* synthetic */ Uri b;
    final /* synthetic */ an c;
    final /* synthetic */ AppLovinAdView d;
    final /* synthetic */ AppLovinAdServiceImpl e;
    
    r(final AppLovinAdServiceImpl e, final AdViewControllerImpl a, final Uri b, final an c, final AppLovinAdView d) {
        this.e = e;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void onPostbackFailure(final String s, final int n) {
        this.e.c.post((Runnable)new t(this));
    }
    
    @Override
    public void onPostbackSuccess(final String s) {
        this.e.c.post((Runnable)new s(this));
    }
}
