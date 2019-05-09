// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import java.util.Map;

public interface AppLovinAdRewardListener
{
    void userDeclinedToViewAd(final AppLovinAd p0);
    
    void userOverQuota(final AppLovinAd p0, final Map<String, String> p1);
    
    void userRewardRejected(final AppLovinAd p0, final Map<String, String> p1);
    
    void userRewardVerified(final AppLovinAd p0, final Map<String, String> p1);
    
    void validationRequestFailed(final AppLovinAd p0, final int p1);
}
