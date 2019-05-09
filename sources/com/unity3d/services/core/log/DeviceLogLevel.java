package com.unity3d.services.core.log;

public class DeviceLogLevel {
    private static final String LOG_TAG = "UnityAds";
    private String _receivingMethodName = null;

    public DeviceLogLevel(String receivingMethodName) {
        this._receivingMethodName = receivingMethodName;
    }

    public String getLogTag() {
        return LOG_TAG;
    }

    public String getReceivingMethodName() {
        return this._receivingMethodName;
    }
}
