// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.adview;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdClickListener;

public interface AppLovinInterstitialAdDialog
{
    void dismiss();
    
    boolean isAdReadyToDisplay();
    
    boolean isShowing();
    
    void setAdClickListener(final AppLovinAdClickListener p0);
    
    void setAdDisplayListener(final AppLovinAdDisplayListener p0);
    
    void setAdLoadListener(final AppLovinAdLoadListener p0);
    
    void setAdVideoPlaybackListener(final AppLovinAdVideoPlaybackListener p0);
    
    void show();
    
    @Deprecated
    void show(final String p0);
    
    void showAndRender(final AppLovinAd p0);
    
    @Deprecated
    void showAndRender(final AppLovinAd p0, final String p1);
}
