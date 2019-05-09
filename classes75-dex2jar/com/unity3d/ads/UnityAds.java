// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads;

import com.unity3d.services.UnityServices;
import android.app.Activity;
import com.unity3d.services.ads.UnityAdsImplementation;

public final class UnityAds
{
    public static boolean getDebugMode() {
        return UnityAdsImplementation.getDebugMode();
    }
    
    public static IUnityAdsListener getListener() {
        return UnityAdsImplementation.getListener();
    }
    
    public static PlacementState getPlacementState() {
        return UnityAdsImplementation.getPlacementState();
    }
    
    public static PlacementState getPlacementState(final String s) {
        return UnityAdsImplementation.getPlacementState(s);
    }
    
    public static String getVersion() {
        return UnityAdsImplementation.getVersion();
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityAdsListener unityAdsListener) {
        initialize(activity, s, unityAdsListener, false);
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityAdsListener unityAdsListener, final boolean b) {
        UnityAdsImplementation.initialize(activity, s, unityAdsListener, b);
    }
    
    public static boolean isInitialized() {
        return UnityServices.isInitialized();
    }
    
    public static boolean isReady() {
        return UnityAdsImplementation.isReady();
    }
    
    public static boolean isReady(final String s) {
        return UnityAdsImplementation.isReady(s);
    }
    
    public static boolean isSupported() {
        return UnityAdsImplementation.isSupported();
    }
    
    public static void setDebugMode(final boolean debugMode) {
        UnityAdsImplementation.setDebugMode(debugMode);
    }
    
    public static void setListener(final IUnityAdsListener listener) {
        UnityAdsImplementation.setListener(listener);
    }
    
    public static void show(final Activity activity) {
        UnityAdsImplementation.show(activity);
    }
    
    public static void show(final Activity activity, final String s) {
        UnityAdsImplementation.show(activity, s);
    }
    
    public enum FinishState
    {
        COMPLETED, 
        ERROR, 
        SKIPPED;
    }
    
    public enum PlacementState
    {
        DISABLED, 
        NOT_AVAILABLE, 
        NO_FILL, 
        READY, 
        WAITING;
    }
    
    public enum UnityAdsError
    {
        AD_BLOCKER_DETECTED, 
        DEVICE_ID_ERROR, 
        FILE_IO_ERROR, 
        INITIALIZE_FAILED, 
        INIT_SANITY_CHECK_FAIL, 
        INTERNAL_ERROR, 
        INVALID_ARGUMENT, 
        NOT_INITIALIZED, 
        SHOW_ERROR, 
        VIDEO_PLAYER_ERROR;
    }
}
