// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAd;
import android.util.AttributeSet;
import com.applovin.sdk.AppLovinSdk;
import android.content.Context;
import com.applovin.sdk.AppLovinAdSize;

public interface AdViewController
{
    void contractAd();
    
    void destroy();
    
    void expandAd();
    
    AppLovinAdSize getSize();
    
    String getZoneId();
    
    void initializeAdView(final AppLovinAdView p0, final Context p1, final AppLovinAdSize p2, final String p3, final AppLovinSdk p4, final AttributeSet p5);
    
    boolean isAdReadyToDisplay();
    
    boolean isAutoDestroy();
    
    void loadNextAd();
    
    void onDetachedFromWindow();
    
    void onVisibilityChanged(final int p0);
    
    void pause();
    
    void renderAd(final AppLovinAd p0);
    
    @Deprecated
    void renderAd(final AppLovinAd p0, final String p1);
    
    void resume();
    
    void setAdClickListener(final AppLovinAdClickListener p0);
    
    void setAdDisplayListener(final AppLovinAdDisplayListener p0);
    
    void setAdLoadListener(final AppLovinAdLoadListener p0);
    
    void setAdVideoPlaybackListener(final AppLovinAdVideoPlaybackListener p0);
    
    void setAdViewEventListener(final AppLovinAdViewEventListener p0);
    
    void setAutoDestroy(final boolean p0);
}
