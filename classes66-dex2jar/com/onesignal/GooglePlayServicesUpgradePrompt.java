// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.PendingIntent$CanceledException;
import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import android.app.Activity;

class GooglePlayServicesUpgradePrompt
{
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    
    private static void OpenPlayStoreToApp(final Activity activity) {
        try {
            final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
            instance.getErrorResolutionPendingIntent((Context)activity, instance.isGooglePlayServicesAvailable(OneSignal.appContext), 9000).send();
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        catch (PendingIntent$CanceledException ex) {}
    }
    
    static void ShowUpdateGPSDialog() {
        if (!isGMSInstalledAndEnabled() && isGooglePlayStoreInstalled() && !OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", false)) {
            OSUtils.runOnMainUIThread(new Runnable() {
                @Override
                public void run() {
                    final Activity curActivity = ActivityLifecycleHandler.curActivity;
                    if (curActivity == null || OneSignal.mInitBuilder.mDisableGmsMissingPrompt) {
                        return;
                    }
                    new AlertDialog$Builder((Context)curActivity).setMessage((CharSequence)OSUtils.getResourceString((Context)curActivity, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.")).setPositiveButton((CharSequence)OSUtils.getResourceString((Context)curActivity, "onesignal_gms_missing_alert_button_update", "Update"), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            OpenPlayStoreToApp(curActivity);
                        }
                    }).setNegativeButton((CharSequence)OSUtils.getResourceString((Context)curActivity, "onesignal_gms_missing_alert_button_skip", "Skip"), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", true);
                        }
                    }).setNeutralButton((CharSequence)OSUtils.getResourceString((Context)curActivity, "onesignal_gms_missing_alert_button_close", "Close"), (DialogInterface$OnClickListener)null).create().show();
                }
            });
        }
    }
    
    static boolean isGMSInstalledAndEnabled() {
        try {
            return OneSignal.appContext.getPackageManager().getPackageInfo("com.google.android.gms", 128).applicationInfo.enabled;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    private static boolean isGooglePlayStoreInstalled() {
        final boolean b = false;
        try {
            final PackageManager packageManager = OneSignal.appContext.getPackageManager();
            final String s = (String)packageManager.getPackageInfo("com.google.android.gms", 128).applicationInfo.loadLabel(packageManager);
            boolean b2 = b;
            if (s != null) {
                final boolean equals = s.equals("Market");
                b2 = b;
                if (!equals) {
                    b2 = true;
                }
            }
            return b2;
        }
        catch (Throwable t) {
            return false;
        }
    }
}
