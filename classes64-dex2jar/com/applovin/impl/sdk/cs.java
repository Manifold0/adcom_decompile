// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationLoadListener;

class cs implements AppLovinMediationLoadListener
{
    final /* synthetic */ cr a;
    
    cs(final cr a) {
        this.a = a;
    }
    
    @Override
    public void adLoaded(final AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.a.c.d.d("MediationAdapterWrapper", "Successfully loaded " + this.a.a);
        this.a.c.a(appLovinMediatedAdInfo, this.a.b);
    }
    
    @Override
    public void failedToLoadAd(final AppLovinMediationErrorCode appLovinMediationErrorCode) {
        AppLovinMediationErrorCode network_UNSPECIFIED = appLovinMediationErrorCode;
        if (appLovinMediationErrorCode == null) {
            network_UNSPECIFIED = AppLovinMediationErrorCode.NETWORK_UNSPECIFIED;
        }
        this.a.c.d.e("MediationAdapterWrapper", "Failed to load " + this.a.a + ": " + network_UNSPECIFIED);
        this.a.c.a(network_UNSPECIFIED.getErrorCode(), this.a.b);
    }
}
