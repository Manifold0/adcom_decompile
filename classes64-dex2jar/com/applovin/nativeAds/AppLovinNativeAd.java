// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.nativeAds;

import com.applovin.sdk.AppLovinPostbackListener;
import android.content.Context;

public interface AppLovinNativeAd
{
    long getAdId();
    
    String getCaptionText();
    
    @Deprecated
    String getClickUrl();
    
    String getCtaText();
    
    String getDescriptionText();
    
    String getIconUrl();
    
    String getImageUrl();
    
    String getImpressionTrackingUrl();
    
    float getStarRating();
    
    String getTitle();
    
    String getVideoEndTrackingUrl(final int p0, final boolean p1);
    
    String getVideoStartTrackingUrl();
    
    String getVideoUrl();
    
    String getZoneId();
    
    boolean isImagePrecached();
    
    boolean isVideoPrecached();
    
    void launchClickTarget(final Context p0);
    
    void trackImpression();
    
    void trackImpression(final AppLovinPostbackListener p0);
}
