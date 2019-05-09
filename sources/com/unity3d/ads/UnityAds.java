package com.unity3d.ads;

import android.app.Activity;
import com.unity3d.services.UnityServices;
import com.unity3d.services.ads.UnityAdsImplementation;

public final class UnityAds {

    public enum FinishState {
        ERROR,
        SKIPPED,
        COMPLETED
    }

    public enum PlacementState {
        READY,
        NOT_AVAILABLE,
        DISABLED,
        WAITING,
        NO_FILL
    }

    public enum UnityAdsError {
        NOT_INITIALIZED,
        INITIALIZE_FAILED,
        INVALID_ARGUMENT,
        VIDEO_PLAYER_ERROR,
        INIT_SANITY_CHECK_FAIL,
        AD_BLOCKER_DETECTED,
        FILE_IO_ERROR,
        DEVICE_ID_ERROR,
        SHOW_ERROR,
        INTERNAL_ERROR
    }

    public static void initialize(Activity activity, String gameId, IUnityAdsListener listener) {
        initialize(activity, gameId, listener, false);
    }

    public static void initialize(Activity activity, String gameId, IUnityAdsListener listener, boolean testMode) {
        UnityAdsImplementation.initialize(activity, gameId, listener, testMode);
    }

    public static boolean isInitialized() {
        return UnityServices.isInitialized();
    }

    public static void setListener(IUnityAdsListener listener) {
        UnityAdsImplementation.setListener(listener);
    }

    public static IUnityAdsListener getListener() {
        return UnityAdsImplementation.getListener();
    }

    public static boolean isSupported() {
        return UnityAdsImplementation.isSupported();
    }

    public static String getVersion() {
        return UnityAdsImplementation.getVersion();
    }

    public static boolean isReady() {
        return UnityAdsImplementation.isReady();
    }

    public static boolean isReady(String placementId) {
        return UnityAdsImplementation.isReady(placementId);
    }

    public static PlacementState getPlacementState() {
        return UnityAdsImplementation.getPlacementState();
    }

    public static PlacementState getPlacementState(String placementId) {
        return UnityAdsImplementation.getPlacementState(placementId);
    }

    public static void show(Activity activity) {
        UnityAdsImplementation.show(activity);
    }

    public static void show(Activity activity, String placementId) {
        UnityAdsImplementation.show(activity, placementId);
    }

    public static void setDebugMode(boolean debugMode) {
        UnityAdsImplementation.setDebugMode(debugMode);
    }

    public static boolean getDebugMode() {
        return UnityAdsImplementation.getDebugMode();
    }
}
