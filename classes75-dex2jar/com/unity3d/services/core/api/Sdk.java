// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.configuration.InitializeThread;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Sdk
{
    @WebViewExposed
    public static void getDebugMode(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(SdkProperties.getDebugMode());
    }
    
    @WebViewExposed
    public static void initComplete(final WebViewCallback webViewCallback) {
        DeviceLog.debug("Web Application initialized");
        SdkProperties.setInitialized(true);
        WebViewApp.getCurrentApp().setWebAppInitialized(true);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void loadComplete(final WebViewCallback webViewCallback) {
        DeviceLog.debug("Web Application loaded");
        WebViewApp.getCurrentApp().setWebAppLoaded(true);
        webViewCallback.invoke(ClientProperties.getGameId(), SdkProperties.isTestMode(), ClientProperties.getAppName(), ClientProperties.getAppVersion(), SdkProperties.getVersionCode(), SdkProperties.getVersionName(), ClientProperties.isAppDebuggable(), WebViewApp.getCurrentApp().getConfiguration().getConfigUrl(), WebViewApp.getCurrentApp().getConfiguration().getWebViewUrl(), WebViewApp.getCurrentApp().getConfiguration().getWebViewHash(), WebViewApp.getCurrentApp().getConfiguration().getWebViewVersion(), SdkProperties.getInitializationTime(), SdkProperties.isReinitialized());
    }
    
    @WebViewExposed
    public static void logDebug(final String s, final WebViewCallback webViewCallback) {
        DeviceLog.debug(s);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void logError(final String s, final WebViewCallback webViewCallback) {
        DeviceLog.error(s);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void logInfo(final String s, final WebViewCallback webViewCallback) {
        DeviceLog.info(s);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void logWarning(final String s, final WebViewCallback webViewCallback) {
        DeviceLog.warning(s);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void reinitialize(final WebViewCallback webViewCallback) {
        SdkProperties.setReinitialized(true);
        InitializeThread.initialize(WebViewApp.getCurrentApp().getConfiguration());
    }
    
    @WebViewExposed
    public static void setDebugMode(final Boolean b, final WebViewCallback webViewCallback) {
        SdkProperties.setDebugMode(b);
        webViewCallback.invoke(new Object[0]);
    }
}
