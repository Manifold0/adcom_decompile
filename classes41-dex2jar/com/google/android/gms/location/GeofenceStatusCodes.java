// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class GeofenceStatusCodes extends CommonStatusCodes
{
    public static final int GEOFENCE_NOT_AVAILABLE = 1000;
    public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
    public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
    
    private GeofenceStatusCodes() {
    }
    
    public static String getStatusCodeString(final int n) {
        switch (n) {
            default: {
                return CommonStatusCodes.getStatusCodeString(n);
            }
            case 1000: {
                return "GEOFENCE_NOT_AVAILABLE";
            }
            case 1001: {
                return "GEOFENCE_TOO_MANY_GEOFENCES";
            }
            case 1002: {
                return "GEOFENCE_TOO_MANY_PENDING_INTENTS";
            }
        }
    }
}
