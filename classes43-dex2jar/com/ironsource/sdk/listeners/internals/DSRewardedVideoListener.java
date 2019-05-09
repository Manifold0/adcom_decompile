// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners.internals;

public interface DSRewardedVideoListener extends DSAdProductListener
{
    void onRVAdCredited(final String p0, final int p1);
    
    void onRVNoMoreOffers(final String p0);
    
    void onRVShowFail(final String p0, final String p1);
}
