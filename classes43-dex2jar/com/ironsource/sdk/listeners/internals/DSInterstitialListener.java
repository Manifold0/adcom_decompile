// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners.internals;

public interface DSInterstitialListener extends DSAdProductListener
{
    void onInterstitialLoadFailed(final String p0, final String p1);
    
    void onInterstitialLoadSuccess(final String p0);
    
    void onInterstitialShowFailed(final String p0, final String p1);
    
    void onInterstitialShowSuccess(final String p0);
}
