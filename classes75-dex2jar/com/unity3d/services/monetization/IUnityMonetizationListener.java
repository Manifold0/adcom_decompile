// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization;

import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import com.unity3d.services.IUnityServicesListener;

public interface IUnityMonetizationListener extends IUnityServicesListener
{
    void onPlacementContentReady(final String p0, final PlacementContent p1);
    
    void onPlacementContentStateChange(final String p0, final PlacementContent p1, final UnityMonetization.PlacementContentState p2, final UnityMonetization.PlacementContentState p3);
}
