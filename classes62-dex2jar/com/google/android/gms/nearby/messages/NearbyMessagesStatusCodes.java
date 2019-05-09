// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.api.CommonStatusCodes;

public class NearbyMessagesStatusCodes extends CommonStatusCodes
{
    public static final int APP_NOT_OPTED_IN = 2802;
    public static final int APP_QUOTA_LIMIT_REACHED = 2804;
    public static final int BLE_ADVERTISING_UNSUPPORTED = 2821;
    public static final int BLE_SCANNING_UNSUPPORTED = 2822;
    public static final int BLUETOOTH_OFF = 2820;
    public static final int DISALLOWED_CALLING_CONTEXT = 2803;
    public static final int FORBIDDEN = 2806;
    public static final int MISSING_PERMISSIONS = 2807;
    public static final int NOT_AUTHORIZED = 2805;
    public static final int TOO_MANY_PENDING_INTENTS = 2801;
    
    private NearbyMessagesStatusCodes() {
    }
    
    public static String getStatusCodeString(final int n) {
        switch (n) {
            default: {
                return CommonStatusCodes.getStatusCodeString(n);
            }
            case 2801: {
                return "TOO_MANY_PENDING_INTENTS";
            }
            case 2802: {
                return "APP_NOT_OPTED_IN";
            }
            case 2803: {
                return "DISALLOWED_CALLING_CONTEXT";
            }
            case 2820: {
                return "BLUETOOTH_OFF";
            }
            case 2821: {
                return "BLE_ADVERTISING_UNSUPPORTED";
            }
            case 2822: {
                return "BLE_SCANNING_UNSUPPORTED";
            }
            case 2805: {
                return "NOT_AUTHORIZED";
            }
            case 2806: {
                return "FORBIDDEN";
            }
        }
    }
}
