package com.unity3d.services.core.api;

import com.unity3d.services.core.configuration.InitializeThread;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class Sdk {
    @WebViewExposed
    public static void loadComplete(WebViewCallback callback) {
        DeviceLog.debug("Web Application loaded");
        WebViewApp.getCurrentApp().setWebAppLoaded(true);
        callback.invoke(ClientProperties.getGameId(), Boolean.valueOf(SdkProperties.isTestMode()), ClientProperties.getAppName(), ClientProperties.getAppVersion(), Integer.valueOf(SdkProperties.getVersionCode()), SdkProperties.getVersionName(), Boolean.valueOf(ClientProperties.isAppDebuggable()), WebViewApp.getCurrentApp().getConfiguration().getConfigUrl(), WebViewApp.getCurrentApp().getConfiguration().getWebViewUrl(), WebViewApp.getCurrentApp().getConfiguration().getWebViewHash(), WebViewApp.getCurrentApp().getConfiguration().getWebViewVersion(), Long.valueOf(SdkProperties.getInitializationTime()), Boolean.valueOf(SdkProperties.isReinitialized()));
    }

    @WebViewExposed
    public static void initComplete(WebViewCallback callback) {
        DeviceLog.debug("Web Application initialized");
        SdkProperties.setInitialized(true);
        WebViewApp.getCurrentApp().setWebAppInitialized(true);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setDebugMode(Boolean debugMode, WebViewCallback callback) {
        SdkProperties.setDebugMode(debugMode.booleanValue());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getDebugMode(WebViewCallback callback) {
        callback.invoke(Boolean.valueOf(SdkProperties.getDebugMode()));
    }

    @WebViewExposed
    public static void logError(String message, WebViewCallback callback) {
        DeviceLog.error(message);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logWarning(String message, WebViewCallback callback) {
        DeviceLog.warning(message);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logInfo(String message, WebViewCallback callback) {
        DeviceLog.info(message);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void logDebug(String message, WebViewCallback callback) {
        DeviceLog.debug(message);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void reinitialize(WebViewCallback callback) {
        SdkProperties.setReinitialized(true);
        InitializeThread.initialize(WebViewApp.getCurrentApp().getConfiguration());
    }
}
