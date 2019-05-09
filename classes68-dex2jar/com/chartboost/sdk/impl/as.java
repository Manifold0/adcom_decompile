// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.app.Activity;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;

public class as
{
    public static boolean a(final Context context) {
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        final int googlePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable != 0) {
            if (!instance.isUserResolvableError(googlePlayServicesAvailable) || !(context instanceof Activity)) {
                return false;
            }
            instance.getErrorDialog((Activity)context, googlePlayServicesAvailable, 9000).show();
        }
        return true;
    }
}
