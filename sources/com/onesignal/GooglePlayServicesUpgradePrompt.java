package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent.CanceledException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.GoogleApiAvailability;

class GooglePlayServicesUpgradePrompt {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    /* renamed from: com.onesignal.GooglePlayServicesUpgradePrompt$1 */
    static class C13171 implements Runnable {

        /* renamed from: com.onesignal.GooglePlayServicesUpgradePrompt$1$1 */
        class C13151 implements OnClickListener {
            C13151() {
            }

            public void onClick(DialogInterface dialog, int which) {
                OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", true);
            }
        }

        C13171() {
        }

        public void run() {
            final Activity activity = ActivityLifecycleHandler.curActivity;
            if (activity != null && !OneSignal.mInitBuilder.mDisableGmsMissingPrompt) {
                String alertBodyText = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.");
                String alertButtonUpdate = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_update", "Update");
                String alertButtonSkip = OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_skip", "Skip");
                new Builder(activity).setMessage(alertBodyText).setPositiveButton(alertButtonUpdate, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GooglePlayServicesUpgradePrompt.OpenPlayStoreToApp(activity);
                    }
                }).setNegativeButton(alertButtonSkip, new C13151()).setNeutralButton(OSUtils.getResourceString(activity, "onesignal_gms_missing_alert_button_close", "Close"), null).create().show();
            }
        }
    }

    GooglePlayServicesUpgradePrompt() {
    }

    static boolean isGMSInstalledAndEnabled() {
        try {
            return OneSignal.appContext.getPackageManager().getPackageInfo("com.google.android.gms", 128).applicationInfo.enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static boolean isGooglePlayStoreInstalled() {
        try {
            PackageManager pm = OneSignal.appContext.getPackageManager();
            String label = (String) pm.getPackageInfo("com.google.android.gms", 128).applicationInfo.loadLabel(pm);
            if (label == null || label.equals("Market")) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static void ShowUpdateGPSDialog() {
        if (!isGMSInstalledAndEnabled() && isGooglePlayStoreInstalled() && !OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_DO_NOT_SHOW_MISSING_GPS", false)) {
            OSUtils.runOnMainUIThread(new C13171());
        }
    }

    private static void OpenPlayStoreToApp(Activity activity) {
        try {
            GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
            apiAvailability.getErrorResolutionPendingIntent(activity, apiAvailability.isGooglePlayServicesAvailable(OneSignal.appContext), 9000).send();
        } catch (CanceledException e) {
        } catch (Throwable e2) {
            e2.printStackTrace();
        }
    }
}
