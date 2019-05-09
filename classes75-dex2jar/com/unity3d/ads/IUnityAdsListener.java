// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads;

public interface IUnityAdsListener
{
    void onUnityAdsError(final UnityAds.UnityAdsError p0, final String p1);
    
    void onUnityAdsFinish(final String p0, final UnityAds.FinishState p1);
    
    void onUnityAdsReady(final String p0);
    
    void onUnityAdsStart(final String p0);
}
