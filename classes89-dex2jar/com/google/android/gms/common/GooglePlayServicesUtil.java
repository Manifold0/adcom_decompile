// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.DialogRedirect;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import android.content.res.Resources;
import com.google.android.gms.common.util.VisibleForTesting;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.app.Dialog;
import android.app.Activity;

public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight
{
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
    
    private GooglePlayServicesUtil() {
    }
    
    @Deprecated
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2) {
        return getErrorDialog(n, activity, n2, null);
    }
    
    @Deprecated
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        int n3 = n;
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating((Context)activity, n)) {
            n3 = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, n3, n2, dialogInterface$OnCancelListener);
    }
    
    @Deprecated
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(n, context, n2);
    }
    
    @Deprecated
    @VisibleForTesting
    public static String getErrorString(final int n) {
        return GooglePlayServicesUtilLight.getErrorString(n);
    }
    
    public static Context getRemoteContext(final Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }
    
    public static Resources getRemoteResource(final Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }
    
    @Deprecated
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(final Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }
    
    @Deprecated
    @KeepForSdk
    public static int isGooglePlayServicesAvailable(final Context context, final int n) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, n);
    }
    
    @Deprecated
    public static boolean isUserRecoverableError(final int n) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(n);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2) {
        return showErrorDialogFragment(n, activity, n2, null);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return showErrorDialogFragment(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        int n3 = n;
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating((Context)activity, n)) {
            n3 = 18;
        }
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (fragment == null) {
            return instance.showErrorDialogFragment(activity, n3, n2, dialogInterface$OnCancelListener);
        }
        final Dialog zaa = GoogleApiAvailability.zaa((Context)activity, n3, DialogRedirect.getInstance(fragment, GoogleApiAvailability.getInstance().getErrorResolutionIntent((Context)activity, n3, "d"), n2), dialogInterface$OnCancelListener);
        if (zaa == null) {
            return false;
        }
        GoogleApiAvailability.zaa(activity, zaa, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
        return true;
    }
    
    @Deprecated
    public static void showErrorNotification(final int n, final Context context) {
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, n) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, n)) {
            instance.zaa(context);
            return;
        }
        instance.showErrorNotification(context, n);
    }
}
