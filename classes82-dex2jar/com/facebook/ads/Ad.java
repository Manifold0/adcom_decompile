// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

public interface Ad
{
    void destroy();
    
    String getPlacementId();
    
    boolean isAdInvalidated();
    
    void loadAd();
    
    void loadAdFromBid(final String p0);
}
