// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import com.chartboost.sdk.Model.CBError;

public abstract class ChartboostDelegate implements a
{
    @Override
    public void didCacheInPlay(final String s) {
    }
    
    @Override
    public void didCacheInterstitial(final String s) {
    }
    
    public void didCacheMoreApps(final String s) {
    }
    
    @Override
    public void didCacheRewardedVideo(final String s) {
    }
    
    @Override
    public void didClickInterstitial(final String s) {
    }
    
    public void didClickMoreApps(final String s) {
    }
    
    @Override
    public void didClickRewardedVideo(final String s) {
    }
    
    @Override
    public void didCloseInterstitial(final String s) {
    }
    
    public void didCloseMoreApps(final String s) {
    }
    
    @Override
    public void didCloseRewardedVideo(final String s) {
    }
    
    @Override
    public void didCompleteRewardedVideo(final String s, final int n) {
    }
    
    @Override
    public void didDismissInterstitial(final String s) {
    }
    
    public void didDismissMoreApps(final String s) {
    }
    
    @Override
    public void didDismissRewardedVideo(final String s) {
    }
    
    @Override
    public void didDisplayInterstitial(final String s) {
    }
    
    public void didDisplayMoreApps(final String s) {
    }
    
    @Override
    public void didDisplayRewardedVideo(final String s) {
    }
    
    @Override
    public void didFailToLoadInPlay(final String s, final CBError.CBImpressionError cbImpressionError) {
    }
    
    @Override
    public void didFailToLoadInterstitial(final String s, final CBError.CBImpressionError cbImpressionError) {
    }
    
    @Override
    public void didFailToLoadMoreApps(final String s, final CBError.CBImpressionError cbImpressionError) {
    }
    
    @Override
    public void didFailToLoadRewardedVideo(final String s, final CBError.CBImpressionError cbImpressionError) {
    }
    
    @Override
    public void didFailToRecordClick(final String s, final CBError.CBClickError cbClickError) {
    }
    
    @Override
    public void didInitialize() {
    }
    
    @Override
    public boolean shouldDisplayInterstitial(final String s) {
        return true;
    }
    
    public boolean shouldDisplayMoreApps(final String s) {
        return true;
    }
    
    @Override
    public boolean shouldDisplayRewardedVideo(final String s) {
        return true;
    }
    
    @Override
    public boolean shouldRequestInterstitial(final String s) {
        return true;
    }
    
    public boolean shouldRequestMoreApps(final String s) {
        return true;
    }
    
    @Override
    public void willDisplayInterstitial(final String s) {
    }
    
    @Override
    public void willDisplayVideo(final String s) {
    }
}
