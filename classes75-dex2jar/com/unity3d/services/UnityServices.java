// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services;

import android.os.Build$VERSION;
import com.unity3d.services.core.configuration.InitializeThread;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.configuration.EnvironmentCheck;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.log.DeviceLog;
import android.app.Activity;
import com.unity3d.services.core.properties.SdkProperties;

public class UnityServices
{
    private static boolean _configurationInitialized;
    
    static {
        UnityServices._configurationInitialized = false;
    }
    
    public static boolean getDebugMode() {
        return SdkProperties.getDebugMode();
    }
    
    public static String getVersion() {
        return SdkProperties.getVersionName();
    }
    
    public static void initialize(final Activity activity, final String s, final IUnityServicesListener unityServicesListener) {
        initialize(activity, s, unityServicesListener, false);
    }
    
    public static void initialize(final Activity activity, final String gameId, final IUnityServicesListener listener, final boolean testMode) {
        DeviceLog.entered();
        if (UnityServices._configurationInitialized) {
            if (ClientProperties.getGameId() != null && !ClientProperties.getGameId().equals(gameId)) {
                DeviceLog.warning("You are trying to re-initialize with a different gameId");
            }
        }
        else {
            UnityServices._configurationInitialized = true;
            if (!isSupported()) {
                DeviceLog.error("Error while initializing Unity Services: device is not supported");
                return;
            }
            SdkProperties.setInitializationTime(System.currentTimeMillis());
            if (gameId == null || gameId.length() == 0) {
                DeviceLog.error("Error while initializing Unity Services: empty game ID, halting Unity Ads init");
                if (listener != null) {
                    listener.onUnityServicesError(UnityServicesError.INVALID_ARGUMENT, "Empty game ID");
                }
            }
            else if (activity == null) {
                DeviceLog.error("Error while initializing Unity Services: null activity, halting Unity Ads init");
                if (listener != null) {
                    listener.onUnityServicesError(UnityServicesError.INVALID_ARGUMENT, "Null activity");
                }
            }
            else {
                if (testMode) {
                    DeviceLog.info("Initializing Unity Services " + SdkProperties.getVersionName() + " (" + SdkProperties.getVersionCode() + ") with game id " + gameId + " in test mode");
                }
                else {
                    DeviceLog.info("Initializing Unity Services " + SdkProperties.getVersionName() + " (" + SdkProperties.getVersionCode() + ") with game id " + gameId + " in production mode");
                }
                SdkProperties.setDebugMode(SdkProperties.getDebugMode());
                SdkProperties.setListener(listener);
                ClientProperties.setGameId(gameId);
                ClientProperties.setApplicationContext(activity.getApplicationContext());
                ClientProperties.setApplication(activity.getApplication());
                SdkProperties.setTestMode(testMode);
                if (EnvironmentCheck.isEnvironmentOk()) {
                    DeviceLog.info("Unity Services environment check OK");
                    InitializeThread.initialize(new Configuration());
                    return;
                }
                DeviceLog.error("Error during Unity Services environment check, halting Unity Services init");
                if (listener != null) {
                    listener.onUnityServicesError(UnityServicesError.INIT_SANITY_CHECK_FAIL, "Unity Services init environment check failed");
                }
            }
        }
    }
    
    public static boolean isInitialized() {
        return SdkProperties.isInitialized();
    }
    
    public static boolean isSupported() {
        return Build$VERSION.SDK_INT >= 16;
    }
    
    public static void setDebugMode(final boolean debugMode) {
        SdkProperties.setDebugMode(debugMode);
    }
    
    public enum UnityServicesError
    {
        INIT_SANITY_CHECK_FAIL, 
        INVALID_ARGUMENT;
    }
}
