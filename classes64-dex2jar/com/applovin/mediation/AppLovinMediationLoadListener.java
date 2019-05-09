// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

public interface AppLovinMediationLoadListener
{
    void adLoaded(final AppLovinMediatedAdInfo p0);
    
    void failedToLoadAd(final AppLovinMediationErrorCode p0);
}
