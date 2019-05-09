// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAd;

public final class ar implements AppLovinAd
{
    public String a() {
        return "<html><head></head><body></body></html>";
    }
    
    @Override
    public long getAdIdNumber() {
        return 0L;
    }
    
    @Override
    public String getAdValue(final String s) {
        return null;
    }
    
    @Override
    public AppLovinAdSize getSize() {
        return AppLovinAdSize.BANNER;
    }
    
    @Override
    public AppLovinAdType getType() {
        return AppLovinAdType.REGULAR;
    }
    
    @Override
    public String getZoneId() {
        return null;
    }
    
    @Override
    public boolean isVideoAd() {
        return false;
    }
}
