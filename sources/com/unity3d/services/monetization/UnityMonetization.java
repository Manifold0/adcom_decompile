package com.unity3d.services.monetization;

import android.app.Activity;
import com.unity3d.services.UnityServices;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.monetization.core.placementcontent.PlacementContents;
import com.unity3d.services.monetization.core.properties.ClientProperties;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;

public class UnityMonetization {

    public enum PlacementContentState {
        READY,
        NOT_AVAILABLE,
        DISABLED,
        WAITING,
        NO_FILL
    }

    public static void setListener(IUnityMonetizationListener listener) {
        ClientProperties.setListener(listener);
    }

    public static IUnityMonetizationListener getListener() {
        return ClientProperties.getListener();
    }

    public static boolean isReady(String placementId) {
        return PlacementContents.isReady(placementId);
    }

    public static PlacementContent getPlacementContent(String placementId) {
        return PlacementContents.getPlacementContent(placementId);
    }

    public static <T extends PlacementContent> T getPlacementContent(String placementId, Class<T> asClass) {
        return PlacementContents.getPlacementContent(placementId, asClass);
    }

    public static void initialize(Activity activity, String gameId, IUnityMonetizationListener listener) {
        initialize(activity, gameId, listener, false);
    }

    public static void initialize(Activity activity, String gameId, IUnityMonetizationListener listener, boolean testMode) {
        DeviceLog.entered();
        if (listener != null) {
            setListener(listener);
        }
        ClientProperties.setMonetizationEnabled(true);
        UnityServices.initialize(activity, gameId, listener, testMode);
    }
}
