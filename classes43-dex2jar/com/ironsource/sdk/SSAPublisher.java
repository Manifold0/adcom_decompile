// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk;

import android.app.Activity;
import org.json.JSONObject;
import com.ironsource.sdk.listeners.OnRewardedVideoListener;
import com.ironsource.sdk.listeners.OnInterstitialListener;
import java.util.Map;
import com.ironsource.sdk.listeners.OnOfferWallListener;

public interface SSAPublisher
{
    void getOfferWallCredits(final String p0, final String p1, final OnOfferWallListener p2);
    
    void initInterstitial(final String p0, final String p1, final String p2, final Map<String, String> p3, final OnInterstitialListener p4);
    
    void initOfferWall(final String p0, final String p1, final Map<String, String> p2, final OnOfferWallListener p3);
    
    void initRewardedVideo(final String p0, final String p1, final String p2, final Map<String, String> p3, final OnRewardedVideoListener p4);
    
    boolean isInterstitialAdAvailable(final String p0);
    
    void loadInterstitial(final JSONObject p0);
    
    void onPause(final Activity p0);
    
    void onResume(final Activity p0);
    
    void release(final Activity p0);
    
    void setMediationState(final String p0, final String p1, final int p2);
    
    void showInterstitial(final JSONObject p0);
    
    void showOfferWall(final Map<String, String> p0);
    
    void showRewardedVideo(final JSONObject p0);
    
    void updateConsentInfo(final JSONObject p0);
}
