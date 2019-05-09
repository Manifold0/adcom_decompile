// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.mediation;

import com.unity3d.ads.UnityAds;
import com.unity3d.ads.IUnityAdsListener;

public interface IUnityAdsExtendedListener extends IUnityAdsListener
{
    void onUnityAdsClick(final String p0);
    
    void onUnityAdsPlacementStateChanged(final String p0, final UnityAds.PlacementState p1, final UnityAds.PlacementState p2);
}
