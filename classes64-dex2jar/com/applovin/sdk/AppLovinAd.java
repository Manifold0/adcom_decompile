// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

public interface AppLovinAd
{
    long getAdIdNumber();
    
    String getAdValue(final String p0);
    
    AppLovinAdSize getSize();
    
    AppLovinAdType getType();
    
    String getZoneId();
    
    boolean isVideoAd();
}
