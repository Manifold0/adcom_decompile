// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import com.unity3d.services.core.webview.bridge.WebViewExposed;
import org.json.JSONObject;
import com.unity3d.services.core.sensorinfo.SensorInfoError;
import com.unity3d.services.core.sensorinfo.SensorInfoListener;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class SensorInfo
{
    @WebViewExposed
    public static void getAccelerometerData(final WebViewCallback webViewCallback) {
        final JSONObject accelerometerData = SensorInfoListener.getAccelerometerData();
        if (accelerometerData != null) {
            webViewCallback.invoke(accelerometerData);
            return;
        }
        webViewCallback.error(SensorInfoError.ACCELEROMETER_DATA_NOT_AVAILABLE, new Object[0]);
    }
    
    @WebViewExposed
    public static void isAccelerometerActive(final WebViewCallback webViewCallback) {
        webViewCallback.invoke(SensorInfoListener.isAccelerometerListenerActive());
    }
    
    @WebViewExposed
    public static void startAccelerometerUpdates(final Integer n, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(SensorInfoListener.startAccelerometerListener(n));
    }
    
    @WebViewExposed
    public static void stopAccelerometerUpdates(final WebViewCallback webViewCallback) {
        SensorInfoListener.stopAccelerometerListener();
        webViewCallback.invoke(new Object[0]);
    }
}
