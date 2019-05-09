// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.impl.sdk.ee;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinAdService;
import java.lang.ref.WeakReference;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinAdLoadListener;

class k implements AppLovinAdLoadListener, AppLovinAdUpdateListener
{
    private AdViewControllerImpl a;
    private WeakReference<AdViewControllerImpl> b;
    private final AppLovinAdService c;
    private final AppLovinLogger d;
    
    k(final AdViewControllerImpl a, final AppLovinSdk appLovinSdk) {
        if (a == null) {
            throw new IllegalArgumentException("No view specified");
        }
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.d = appLovinSdk.getLogger();
        this.c = appLovinSdk.getAdService();
        if (new ee(appLovinSdk).ap()) {
            this.a = a;
            return;
        }
        this.b = new WeakReference<AdViewControllerImpl>(a);
    }
    
    private AdViewControllerImpl a() {
        if (this.a != null) {
            return this.a;
        }
        return this.b.get();
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        final AdViewControllerImpl a = this.a();
        if (a != null) {
            a.a(appLovinAd);
            return;
        }
        this.d.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was received");
    }
    
    @Override
    public void adUpdated(final AppLovinAd appLovinAd) {
        final AdViewControllerImpl a = this.a();
        if (a != null) {
            a.a(appLovinAd);
            return;
        }
        this.c.removeAdUpdateListener(this, appLovinAd.getSize());
        this.d.userError("AppLovinAdView", "Ad view has been garbage collected by the time an ad was updated");
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        final AdViewControllerImpl a = this.a();
        if (a != null) {
            a.a(n);
        }
    }
    
    @Override
    public String toString() {
        return "[AdViewController listener: " + this.hashCode() + "]";
    }
}
