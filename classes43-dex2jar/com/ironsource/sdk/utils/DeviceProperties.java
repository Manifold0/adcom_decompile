// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import com.ironsource.environment.DeviceStatus;
import android.content.Context;

public class DeviceProperties
{
    private static DeviceProperties mInstance;
    private int mDeviceApiLevel;
    private String mDeviceCarrier;
    private String mDeviceModel;
    private String mDeviceOem;
    private String mDeviceOsType;
    private String mDeviceOsVersion;
    
    static {
        DeviceProperties.mInstance = null;
    }
    
    private DeviceProperties(final Context context) {
        this.mDeviceOem = DeviceStatus.getDeviceOEM();
        this.mDeviceModel = DeviceStatus.getDeviceModel();
        this.mDeviceOsType = DeviceStatus.getDeviceOs();
        this.mDeviceOsVersion = DeviceStatus.getAndroidOsVersion();
        this.mDeviceApiLevel = DeviceStatus.getAndroidAPIVersion();
        this.mDeviceCarrier = DeviceStatus.getMobileCarrier(context);
    }
    
    public static DeviceProperties getInstance(final Context context) {
        if (DeviceProperties.mInstance == null) {
            DeviceProperties.mInstance = new DeviceProperties(context);
        }
        return DeviceProperties.mInstance;
    }
    
    public static String getSupersonicSdkVersion() {
        return "5.58";
    }
    
    public static void release() {
        DeviceProperties.mInstance = null;
    }
    
    public int getDeviceApiLevel() {
        return this.mDeviceApiLevel;
    }
    
    public String getDeviceCarrier() {
        return this.mDeviceCarrier;
    }
    
    public String getDeviceModel() {
        return this.mDeviceModel;
    }
    
    public String getDeviceOem() {
        return this.mDeviceOem;
    }
    
    public String getDeviceOsType() {
        return this.mDeviceOsType;
    }
    
    public String getDeviceOsVersion() {
        return this.mDeviceOsVersion;
    }
    
    public float getDeviceVolume(final Context context) {
        return DeviceStatus.getSystemVolumePercent(context);
    }
}
