// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import org.json.JSONObject;
import android.app.Activity;

public interface InterstitialAdapterApi
{
    void addInterstitialListener(final InterstitialSmashListener p0);
    
    void initInterstitial(final Activity p0, final String p1, final String p2, final JSONObject p3, final InterstitialSmashListener p4);
    
    boolean isInterstitialReady(final JSONObject p0);
    
    void loadInterstitial(final JSONObject p0, final InterstitialSmashListener p1);
    
    void removeInterstitialListener(final InterstitialSmashListener p0);
    
    void showInterstitial(final JSONObject p0, final InterstitialSmashListener p1);
}
