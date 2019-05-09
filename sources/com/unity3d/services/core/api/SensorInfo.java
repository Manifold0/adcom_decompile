package com.unity3d.services.core.api;

import com.unity3d.services.core.sensorinfo.SensorInfoError;
import com.unity3d.services.core.sensorinfo.SensorInfoListener;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class SensorInfo {
    @WebViewExposed
    public static void startAccelerometerUpdates(Integer sensorDelay, WebViewCallback callback) {
        callback.invoke(Boolean.valueOf(SensorInfoListener.startAccelerometerListener(sensorDelay.intValue())));
    }

    @WebViewExposed
    public static void stopAccelerometerUpdates(WebViewCallback callback) {
        SensorInfoListener.stopAccelerometerListener();
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void isAccelerometerActive(WebViewCallback callback) {
        callback.invoke(Boolean.valueOf(SensorInfoListener.isAccelerometerListenerActive()));
    }

    @WebViewExposed
    public static void getAccelerometerData(WebViewCallback callback) {
        if (SensorInfoListener.getAccelerometerData() != null) {
            callback.invoke(SensorInfoListener.getAccelerometerData());
            return;
        }
        callback.error(SensorInfoError.ACCELEROMETER_DATA_NOT_AVAILABLE, new Object[0]);
    }
}
