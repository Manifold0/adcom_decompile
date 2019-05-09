package com.unity3d.services.monetization;

import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.monetization.UnityMonetization.PlacementContentState;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;

public interface IUnityMonetizationListener extends IUnityServicesListener {
    void onPlacementContentReady(String str, PlacementContent placementContent);

    void onPlacementContentStateChange(String str, PlacementContent placementContent, PlacementContentState placementContentState, PlacementContentState placementContentState2);
}
