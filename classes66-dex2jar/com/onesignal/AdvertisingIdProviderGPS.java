// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;

class AdvertisingIdProviderGPS implements AdvertisingIdentifierProvider
{
    private static String lastValue;
    
    static String getLastValue() {
        return AdvertisingIdProviderGPS.lastValue;
    }
    
    @Override
    public String getIdentifier(final Context context) {
        try {
            final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                AdvertisingIdProviderGPS.lastValue = "OptedOut";
            }
            else {
                AdvertisingIdProviderGPS.lastValue = advertisingIdInfo.getId();
            }
            return AdvertisingIdProviderGPS.lastValue;
        }
        catch (Throwable t) {
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Error getting Google Ad id: ", t);
            return null;
        }
    }
}
