// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

@Deprecated
public interface FileUploadPreferences
{
    public static final int BATTERY_USAGE_CHARGING_ONLY = 257;
    public static final int BATTERY_USAGE_UNRESTRICTED = 256;
    public static final int NETWORK_TYPE_ANY = 1;
    public static final int NETWORK_TYPE_WIFI_ONLY = 2;
    public static final int PREFERENCE_VALUE_UNKNOWN = 0;
    
    int getBatteryUsagePreference();
    
    int getNetworkTypePreference();
    
    boolean isRoamingAllowed();
    
    void setBatteryUsagePreference(final int p0);
    
    void setNetworkTypePreference(final int p0);
    
    void setRoamingAllowed(final boolean p0);
}
