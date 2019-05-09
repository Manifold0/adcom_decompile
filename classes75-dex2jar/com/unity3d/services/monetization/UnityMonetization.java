// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization;

import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.UnityServices;
import com.unity3d.services.core.log.DeviceLog;
import android.app.Activity;
import com.unity3d.services.monetization.core.placementcontent.PlacementContents;
import com.unity3d.services.monetization.placementcontent.core.PlacementContent;
import com.unity3d.services.monetization.core.properties.ClientProperties;

public class UnityMonetization
{
    public static IUnityMonetizationListener getListener() {
        return ClientProperties.getListener();
    }
    
    public static PlacementContent getPlacementContent(final String s) {
        return PlacementContents.getPlacementContent(s);
    }
    
    public static <T extends PlacementContent> T getPlacementContent(final String s, final Class<T> clazz) {
        return PlacementContents.getPlacementContent(s, clazz);
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityMonetizationListener unityMonetizationListener) {
        initialize(activity, s, unityMonetizationListener, false);
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityMonetizationListener listener, final boolean b) {
        DeviceLog.entered();
        if (listener != null) {
            setListener(listener);
        }
        ClientProperties.setMonetizationEnabled(true);
        UnityServices.initialize(activity, s, listener, b);
    }
    
    public static boolean isReady(final String s) {
        return PlacementContents.isReady(s);
    }
    
    public static void setListener(final IUnityMonetizationListener listener) {
        ClientProperties.setListener(listener);
    }
    
    public enum PlacementContentState
    {
        DISABLED, 
        NOT_AVAILABLE, 
        NO_FILL, 
        READY, 
        WAITING;
    }
}
