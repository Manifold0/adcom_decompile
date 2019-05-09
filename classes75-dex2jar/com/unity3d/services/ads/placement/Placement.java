// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.placement;

import com.unity3d.ads.UnityAds;
import java.util.HashMap;

public class Placement
{
    private static String _defaultBannerPlacement;
    private static String _defaultPlacement;
    private static HashMap<String, UnityAds.PlacementState> _placementReadyMap;
    
    private static UnityAds.PlacementState currentState(final String s) {
        if (Placement._placementReadyMap == null || !Placement._placementReadyMap.containsKey(s)) {
            return UnityAds.PlacementState.NOT_AVAILABLE;
        }
        return Placement._placementReadyMap.get(s);
    }
    
    public static String getDefaultBannerPlacement() {
        return Placement._defaultBannerPlacement;
    }
    
    public static String getDefaultPlacement() {
        return Placement._defaultPlacement;
    }
    
    public static UnityAds.PlacementState getPlacementState() {
        if (Placement._defaultPlacement == null) {
            return UnityAds.PlacementState.NOT_AVAILABLE;
        }
        return getPlacementState(Placement._defaultPlacement);
    }
    
    public static UnityAds.PlacementState getPlacementState(final String s) {
        return currentState(s);
    }
    
    public static boolean isReady() {
        return getPlacementState() == UnityAds.PlacementState.READY;
    }
    
    public static boolean isReady(final String s) {
        return getPlacementState(s) == UnityAds.PlacementState.READY;
    }
    
    public static void reset() {
        Placement._placementReadyMap = null;
        Placement._defaultPlacement = null;
    }
    
    public static void setDefaultBannerPlacement(final String defaultBannerPlacement) {
        Placement._defaultBannerPlacement = defaultBannerPlacement;
    }
    
    public static void setDefaultPlacement(final String defaultPlacement) {
        Placement._defaultPlacement = defaultPlacement;
    }
    
    public static void setPlacementState(final String s, final String s2) {
        if (Placement._placementReadyMap == null) {
            Placement._placementReadyMap = new HashMap<String, UnityAds.PlacementState>();
        }
        Placement._placementReadyMap.put(s, UnityAds.PlacementState.valueOf(s2));
    }
}
