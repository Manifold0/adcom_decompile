// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.configuration;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.SdkProperties;
import java.lang.annotation.Annotation;
import android.webkit.JavascriptInterface;
import android.os.Build$VERSION;
import java.lang.reflect.Method;

public class EnvironmentCheck
{
    private static boolean hasJavascriptInterface(final Method method) {
        if (Build$VERSION.SDK_INT >= 17) {
            final Annotation[] annotations = method.getAnnotations();
            if (annotations != null) {
                for (int length = annotations.length, i = 0; i < length; ++i) {
                    if (annotations[i] instanceof JavascriptInterface) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }
    
    public static boolean isEnvironmentOk() {
        return testProGuard() && testCacheDirectory();
    }
    
    public static boolean testCacheDirectory() {
        if (SdkProperties.getCacheDirectory() != null) {
            DeviceLog.debug("Unity Ads cache directory check OK");
            return true;
        }
        DeviceLog.error("Unity Ads cache directory check fail: no working cache directory available");
        return false;
    }
    
    public static boolean testProGuard() {
        try {
            final Class<?> forName = Class.forName("com.unity3d.services.core.webview.bridge.WebViewBridgeInterface");
            final Method method = forName.getMethod("handleInvocation", String.class);
            final Method method2 = forName.getMethod("handleCallback", String.class, String.class, String.class);
            if (hasJavascriptInterface(method) && hasJavascriptInterface(method2)) {
                DeviceLog.debug("Unity Ads ProGuard check OK");
                return true;
            }
            DeviceLog.error("Unity Ads ProGuard check fail: missing @JavascriptInterface annotations in Unity Ads web bridge");
            return false;
        }
        catch (ClassNotFoundException ex) {
            DeviceLog.exception("Unity Ads ProGuard check fail: Unity Ads web bridge class not found", ex);
            return false;
        }
        catch (NoSuchMethodException ex2) {
            DeviceLog.exception("Unity Ads ProGuard check fail: Unity Ads web bridge methods not found", ex2);
            return false;
        }
        catch (Exception ex3) {
            DeviceLog.exception("Unknown exception during Unity Ads ProGuard check: " + ex3.getMessage(), ex3);
            return true;
        }
    }
}
