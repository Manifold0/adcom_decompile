// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;

public interface AppLovinAdViewEventListener
{
    void adClosedFullscreen(final AppLovinAd p0, final AppLovinAdView p1);
    
    void adFailedToDisplay(final AppLovinAd p0, final AppLovinAdView p1, final AppLovinAdViewDisplayErrorCode p2);
    
    void adLeftApplication(final AppLovinAd p0, final AppLovinAdView p1);
    
    void adOpenedFullscreen(final AppLovinAd p0, final AppLovinAdView p1);
}
