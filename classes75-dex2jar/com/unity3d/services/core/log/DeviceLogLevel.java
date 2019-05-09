// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.log;

public class DeviceLogLevel
{
    private static final String LOG_TAG = "UnityAds";
    private String _receivingMethodName;
    
    public DeviceLogLevel(final String receivingMethodName) {
        this._receivingMethodName = null;
        this._receivingMethodName = receivingMethodName;
    }
    
    public String getLogTag() {
        return "UnityAds";
    }
    
    public String getReceivingMethodName() {
        return this._receivingMethodName;
    }
}
