package com.unity3d.ads.mediation;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds.PlacementState;

public interface IUnityAdsExtendedListener extends IUnityAdsListener {
    void onUnityAdsClick(String str);

    void onUnityAdsPlacementStateChanged(String str, PlacementState placementState, PlacementState placementState2);
}
