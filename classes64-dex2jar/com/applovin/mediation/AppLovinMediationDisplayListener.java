// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

import java.util.Map;

public interface AppLovinMediationDisplayListener
{
    void adClicked(final AppLovinMediatedAdInfo p0);
    
    void adDisplayed(final AppLovinMediatedAdInfo p0);
    
    void adHidden(final AppLovinMediatedAdInfo p0);
    
    void failedToDisplayAd(final AppLovinMediatedAdInfo p0, final AppLovinMediationErrorCode p1);
    
    void rewardRejected(final AppLovinMediatedAdInfo p0, final Map<String, String> p1);
    
    void rewardVerified(final AppLovinMediatedAdInfo p0, final Map<String, String> p1);
}
