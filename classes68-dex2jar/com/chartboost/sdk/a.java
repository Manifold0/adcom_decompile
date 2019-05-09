// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import com.chartboost.sdk.Model.CBError;

public interface a
{
    void didCacheInPlay(final String p0);
    
    void didCacheInterstitial(final String p0);
    
    void didCacheRewardedVideo(final String p0);
    
    void didClickInterstitial(final String p0);
    
    void didClickRewardedVideo(final String p0);
    
    void didCloseInterstitial(final String p0);
    
    void didCloseRewardedVideo(final String p0);
    
    void didCompleteRewardedVideo(final String p0, final int p1);
    
    void didDismissInterstitial(final String p0);
    
    void didDismissRewardedVideo(final String p0);
    
    void didDisplayInterstitial(final String p0);
    
    void didDisplayRewardedVideo(final String p0);
    
    void didFailToLoadInPlay(final String p0, final CBError.CBImpressionError p1);
    
    void didFailToLoadInterstitial(final String p0, final CBError.CBImpressionError p1);
    
    void didFailToLoadMoreApps(final String p0, final CBError.CBImpressionError p1);
    
    void didFailToLoadRewardedVideo(final String p0, final CBError.CBImpressionError p1);
    
    void didFailToRecordClick(final String p0, final CBError.CBClickError p1);
    
    void didInitialize();
    
    boolean shouldDisplayInterstitial(final String p0);
    
    boolean shouldDisplayRewardedVideo(final String p0);
    
    boolean shouldRequestInterstitial(final String p0);
    
    void willDisplayInterstitial(final String p0);
    
    void willDisplayVideo(final String p0);
}
