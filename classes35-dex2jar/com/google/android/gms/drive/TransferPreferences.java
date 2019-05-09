// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

public interface TransferPreferences
{
    public static final int BATTERY_USAGE_CHARGING_ONLY = 257;
    public static final int BATTERY_USAGE_UNKNOWN = 0;
    public static final int BATTERY_USAGE_UNRESTRICTED = 256;
    public static final int NETWORK_TYPE_ANY = 1;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI_ONLY = 2;
    
    int getBatteryUsagePreference();
    
    int getNetworkPreference();
    
    boolean isRoamingAllowed();
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface BatteryUsage {
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }
}
