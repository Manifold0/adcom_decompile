// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places.internal;

import android.os.Build$VERSION;
import android.content.Context;

public class ScannerFactory
{
    public static final int OS_VERSION_JELLY_BEAN_MR1 = 17;
    public static final int OS_VERSION_JELLY_BEAN_MR2 = 18;
    public static final int OS_VERSION_LOLLIPOP = 21;
    
    public static BleScanner newBleScanner(final Context context, final LocationPackageRequestParams locationPackageRequestParams) {
        if (Build$VERSION.SDK_INT >= 21) {
            return new BleScannerImpl(context, locationPackageRequestParams);
        }
        return new BleScannerLegacy();
    }
    
    public static LocationScanner newLocationScanner(final Context context, final LocationPackageRequestParams locationPackageRequestParams) {
        return new LocationScannerImpl(context, locationPackageRequestParams);
    }
    
    public static WifiScanner newWifiScanner(final Context context, final LocationPackageRequestParams locationPackageRequestParams) {
        return new WifiScannerImpl(context, locationPackageRequestParams);
    }
}
