// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import java.util.List;

public interface AppLovinAdService
{
    public static final String URI_AD_SERVICE = "/adservice";
    @Deprecated
    public static final String URI_API_SERVICE = "/api";
    public static final String URI_CLOSE_AD = "/adservice/close_ad";
    public static final String URI_CONTRACT_AD = "/adservice/contract_ad";
    public static final String URI_EXPAND_AD = "/adservice/expand_ad";
    @Deprecated
    public static final String URI_LAUNCH_APP = "/launch";
    @Deprecated
    public static final String URI_NEXT_AD = "/adservice/next_ad";
    
    @Deprecated
    void addAdUpdateListener(final AppLovinAdUpdateListener p0);
    
    void addAdUpdateListener(final AppLovinAdUpdateListener p0, final AppLovinAdSize p1);
    
    @Deprecated
    boolean hasPreloadedAd(final AppLovinAdSize p0);
    
    @Deprecated
    boolean hasPreloadedAdForZoneId(final String p0);
    
    void loadNextAd(final AppLovinAdSize p0, final AppLovinAdLoadListener p1);
    
    void loadNextAdForAdToken(final String p0, final AppLovinAdLoadListener p1);
    
    void loadNextAdForZoneId(final String p0, final AppLovinAdLoadListener p1);
    
    void loadNextAdForZoneIds(final List<String> p0, final AppLovinAdLoadListener p1);
    
    void loadNextMediatedAd(final AppLovinAdSize p0, final AppLovinAdLoadListener p1);
    
    @Deprecated
    void preloadAd(final AppLovinAdSize p0);
    
    @Deprecated
    void preloadAdForZoneId(final String p0);
    
    void removeAdUpdateListener(final AppLovinAdUpdateListener p0, final AppLovinAdSize p1);
}
