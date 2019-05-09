package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.games.GamesStatusCodes;

public class as {
    /* renamed from: a */
    public static boolean m3415a(Context context) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (!instance.isUserResolvableError(isGooglePlayServicesAvailable) || !(context instanceof Activity)) {
            return false;
        }
        instance.getErrorDialog((Activity) context, isGooglePlayServicesAvailable, GamesStatusCodes.STATUS_VIDEO_NOT_ACTIVE).show();
        return true;
    }
}
