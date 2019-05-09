// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.properties;

import com.unity3d.services.core.log.DeviceLog;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.MalformedURLException;
import android.content.Context;
import java.io.File;
import com.unity3d.services.IUnityServicesListener;
import com.unity3d.services.core.cache.CacheDirectory;

public class SdkProperties
{
    private static final String CACHE_DIR_NAME = "UnityAdsCache";
    private static final String LOCAL_CACHE_FILE_PREFIX = "UnityAdsCache-";
    private static final String LOCAL_STORAGE_FILE_PREFIX = "UnityAdsStorage-";
    private static CacheDirectory _cacheDirectory;
    private static String _configUrl;
    private static boolean _debugMode;
    private static long _initializationTime;
    private static boolean _initialized;
    private static IUnityServicesListener _listener;
    private static boolean _reinitialized;
    private static boolean _testMode;
    
    static {
        SdkProperties._configUrl = getDefaultConfigUrl("release");
        SdkProperties._cacheDirectory = null;
        SdkProperties._initializationTime = 0L;
        SdkProperties._initialized = false;
        SdkProperties._reinitialized = false;
        SdkProperties._testMode = false;
        SdkProperties._debugMode = false;
    }
    
    public static File getCacheDirectory() {
        return getCacheDirectory(ClientProperties.getApplicationContext());
    }
    
    public static File getCacheDirectory(final Context context) {
        if (SdkProperties._cacheDirectory == null) {
            setCacheDirectory(new CacheDirectory("UnityAdsCache"));
        }
        return SdkProperties._cacheDirectory.getCacheDirectory(context);
    }
    
    public static String getCacheDirectoryName() {
        return "UnityAdsCache";
    }
    
    public static CacheDirectory getCacheDirectoryObject() {
        return SdkProperties._cacheDirectory;
    }
    
    public static String getCacheFilePrefix() {
        return "UnityAdsCache-";
    }
    
    public static String getConfigUrl() {
        return SdkProperties._configUrl;
    }
    
    public static boolean getDebugMode() {
        return SdkProperties._debugMode;
    }
    
    public static String getDefaultConfigUrl(final String s) {
        return "https://config.unityads.unity3d.com/webview/" + getWebViewBranch() + "/" + s + "/config.json";
    }
    
    public static long getInitializationTime() {
        return SdkProperties._initializationTime;
    }
    
    public static IUnityServicesListener getListener() {
        return SdkProperties._listener;
    }
    
    public static String getLocalStorageFilePrefix() {
        return "UnityAdsStorage-";
    }
    
    public static String getLocalWebViewFile() {
        return getCacheDirectory().getAbsolutePath() + "/UnityAdsWebApp.html";
    }
    
    public static int getVersionCode() {
        return 3000;
    }
    
    public static String getVersionName() {
        return "3.0.0";
    }
    
    private static String getWebViewBranch() {
        return getVersionName();
    }
    
    public static boolean isInitialized() {
        return SdkProperties._initialized;
    }
    
    public static boolean isReinitialized() {
        return SdkProperties._reinitialized;
    }
    
    public static boolean isTestMode() {
        return SdkProperties._testMode;
    }
    
    public static void setCacheDirectory(final CacheDirectory cacheDirectory) {
        SdkProperties._cacheDirectory = cacheDirectory;
    }
    
    public static void setConfigUrl(final String configUrl) throws URISyntaxException, MalformedURLException {
        if (configUrl == null) {
            throw new MalformedURLException();
        }
        if (!configUrl.startsWith("http://") && !configUrl.startsWith("https://")) {
            throw new MalformedURLException();
        }
        new URL(configUrl).toURI();
        SdkProperties._configUrl = configUrl;
    }
    
    public static void setDebugMode(final boolean debugMode) {
        SdkProperties._debugMode = debugMode;
        if (debugMode) {
            DeviceLog.setLogLevel(8);
            return;
        }
        DeviceLog.setLogLevel(4);
    }
    
    public static void setInitializationTime(final long initializationTime) {
        SdkProperties._initializationTime = initializationTime;
    }
    
    public static void setInitialized(final boolean initialized) {
        SdkProperties._initialized = initialized;
    }
    
    public static void setListener(final IUnityServicesListener listener) {
        SdkProperties._listener = listener;
    }
    
    public static void setReinitialized(final boolean reinitialized) {
        SdkProperties._reinitialized = reinitialized;
    }
    
    public static void setTestMode(final boolean testMode) {
        SdkProperties._testMode = testMode;
    }
}
