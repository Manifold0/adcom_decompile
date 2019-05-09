// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import com.applovin.mediation.AppLovinMediationErrorCode;
import com.applovin.sdk.AppLovinAd;
import com.applovin.mediation.AppLovinMediatedAdInfo;
import com.applovin.mediation.AppLovinMediationDisplayListener;

class cv implements AppLovinMediationDisplayListener
{
    final /* synthetic */ cu a;
    
    cv(final cu a) {
        this.a = a;
    }
    
    @Override
    public void adClicked(final AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.a.a.c(this.a.b);
    }
    
    @Override
    public void adDisplayed(final AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.a.a.a(this.a.b);
    }
    
    @Override
    public void adHidden(final AppLovinMediatedAdInfo appLovinMediatedAdInfo) {
        this.a.a.b(this.a.b);
    }
    
    @Override
    public void failedToDisplayAd(final AppLovinMediatedAdInfo appLovinMediatedAdInfo, final AppLovinMediationErrorCode appLovinMediationErrorCode) {
        this.a.a.b(this.a.b);
    }
    
    @Override
    public void rewardRejected(final AppLovinMediatedAdInfo appLovinMediatedAdInfo, final Map<String, String> map) {
        this.a.a.b(map, this.a.b);
    }
    
    @Override
    public void rewardVerified(final AppLovinMediatedAdInfo appLovinMediatedAdInfo, final Map<String, String> map) {
        this.a.a.a(map, this.a.b);
    }
}
