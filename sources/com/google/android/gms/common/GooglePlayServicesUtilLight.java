package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.zzb;
import com.onesignal.OneSignalDbContract.NotificationTable;
import com.tonyodev.fetch.FetchService;
import java.util.concurrent.atomic.AtomicBoolean;

@ShowFirstParty
@KeepForSdk
public class GooglePlayServicesUtilLight {
    @KeepForSdk
    static final int GMS_AVAILABILITY_NOTIFICATION_ID = 10436;
    @KeepForSdk
    static final int GMS_GENERAL_ERROR_NOTIFICATION_ID = 39789;
    @KeepForSdk
    public static final String GOOGLE_PLAY_GAMES_PACKAGE = "com.google.android.play.games";
    @KeepForSdk
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    @VisibleForTesting
    static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
    @VisibleForTesting
    private static boolean zzah = false;
    @VisibleForTesting
    private static boolean zzai = false;
    private static boolean zzaj = false;
    @VisibleForTesting
    private static boolean zzak = false;
    private static final AtomicBoolean zzal = new AtomicBoolean();

    @ShowFirstParty
    @KeepForSdk
    public static void enableUsingApkIndependentContext() {
        zzal.set(true);
    }

    @KeepForSdk
    GooglePlayServicesUtilLight() {
    }

    @KeepForSdk
    @Deprecated
    @VisibleForTesting
    public static String getErrorString(int i) {
        return ConnectionResult.zza(i);
    }

    @KeepForSdk
    @Deprecated
    @HideFirstParty
    public static int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @KeepForSdk
    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int i) {
        try {
            context.getResources().getString(C0954R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!("com.google.android.gms".equals(context.getPackageName()) || zzal.get())) {
            int zzd = zzp.zzd(context);
            if (zzd == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzd != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException(new StringBuilder(FetchService.ACTION_LOGGING).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(GOOGLE_PLAY_SERVICES_VERSION_CODE).append(" but found ").append(zzd).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
        boolean z = (DeviceProperties.isWearableWithoutPlayStore(context) || DeviceProperties.zzf(context)) ? false : true;
        return zza(context, z, i);
    }

    @VisibleForTesting
    private static int zza(Context context, boolean z, int i) {
        Preconditions.checkArgument(i >= 0);
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;
        if (z) {
            try {
                packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
            } catch (NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Google Play Store is missing.");
                return 9;
            }
        }
        try {
            PackageInfo packageInfo2 = packageManager.getPackageInfo("com.google.android.gms", 64);
            GoogleSignatureVerifier.getInstance(context);
            if (!GoogleSignatureVerifier.zza(packageInfo2, true)) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            } else if (z && (!GoogleSignatureVerifier.zza(r0, true) || !r0.signatures[0].equals(packageInfo2.signatures[0]))) {
                Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                return 9;
            } else if (zzb.zzc(packageInfo2.versionCode) < zzb.zzc(i)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + i + " but found " + packageInfo2.versionCode);
                return 2;
            } else {
                ApplicationInfo applicationInfo = packageInfo2.applicationInfo;
                if (applicationInfo == null) {
                    try {
                        applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                    } catch (Throwable e2) {
                        Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                        return 1;
                    }
                }
                if (applicationInfo.enabled) {
                    return 0;
                }
                return 3;
            }
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @KeepForSdk
    @Deprecated
    public static void ensurePlayServicesAvailable(Context context, int i) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, i);
        if (isGooglePlayServicesAvailable != 0) {
            Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (errorResolutionIntent == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
        }
    }

    @KeepForSdk
    @Deprecated
    public static boolean isGooglePlayServicesUid(Context context, int i) {
        return UidVerifier.isGooglePlayServicesUid(context, i);
    }

    @TargetApi(19)
    @KeepForSdk
    @Deprecated
    public static boolean uidHasPackageName(Context context, int i, String str) {
        return UidVerifier.uidHasPackageName(context, i, str);
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static Intent getGooglePlayServicesAvailabilityRecoveryIntent(int i) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(null, i, null);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.internal.ShowFirstParty
    @com.google.android.gms.common.annotation.KeepForSdk
    public static boolean honorsDebugCertificates(android.content.Context r5) {
        /*
        r0 = 0;
        r1 = 1;
        r2 = zzak;
        if (r2 != 0) goto L_0x002a;
    L_0x0006:
        r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r5);	 Catch:{ NameNotFoundException -> 0x003a }
        r3 = "com.google.android.gms";
        r4 = 64;
        r2 = r2.getPackageInfo(r3, r4);	 Catch:{ NameNotFoundException -> 0x003a }
        com.google.android.gms.common.GoogleSignatureVerifier.getInstance(r5);	 Catch:{ NameNotFoundException -> 0x003a }
        if (r2 == 0) goto L_0x0036;
    L_0x0017:
        r3 = 0;
        r3 = com.google.android.gms.common.GoogleSignatureVerifier.zza(r2, r3);	 Catch:{ NameNotFoundException -> 0x003a }
        if (r3 != 0) goto L_0x0036;
    L_0x001e:
        r3 = 1;
        r2 = com.google.android.gms.common.GoogleSignatureVerifier.zza(r2, r3);	 Catch:{ NameNotFoundException -> 0x003a }
        if (r2 == 0) goto L_0x0036;
    L_0x0025:
        r2 = 1;
        zzaj = r2;	 Catch:{ NameNotFoundException -> 0x003a }
    L_0x0028:
        zzak = r1;
    L_0x002a:
        r2 = zzaj;
        if (r2 != 0) goto L_0x0034;
    L_0x002e:
        r2 = com.google.android.gms.common.util.DeviceProperties.isUserBuild();
        if (r2 != 0) goto L_0x0035;
    L_0x0034:
        r0 = r1;
    L_0x0035:
        return r0;
    L_0x0036:
        r2 = 0;
        zzaj = r2;	 Catch:{ NameNotFoundException -> 0x003a }
        goto L_0x0028;
    L_0x003a:
        r2 = move-exception;
        r3 = "GooglePlayServicesUtil";
        r4 = "Cannot find Google Play services package name.";
        android.util.Log.w(r3, r4, r2);	 Catch:{ all -> 0x0045 }
        zzak = r1;
        goto L_0x002a;
    L_0x0045:
        r0 = move-exception;
        zzak = r1;
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesUtilLight.honorsDebugCertificates(android.content.Context):boolean");
    }

    @KeepForSdk
    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return GoogleApiAvailabilityLight.getInstance().getErrorResolutionPendingIntent(context, i, i2);
    }

    @KeepForSdk
    @Deprecated
    public static void cancelAvailabilityErrorNotifications(Context context) {
        if (!sCanceledAvailabilityNotification.getAndSet(true)) {
            try {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationTable.TABLE_NAME);
                if (notificationManager != null) {
                    notificationManager.cancel(GMS_AVAILABILITY_NOTIFICATION_ID);
                }
            } catch (SecurityException e) {
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @KeepForSdk
    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @KeepForSdk
    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static int getClientVersion(Context context) {
        Preconditions.checkState(true);
        return ClientLibraryUtils.getClientVersion(context, context.getPackageName());
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static int getApkVersion(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return i;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    @VisibleForTesting
    public static boolean isSidewinderDevice(Context context) {
        return DeviceProperties.isSidewinder(context);
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return isUninstalledAppPossiblyUpdating(context, "com.google.android.gms");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static boolean isPlayStorePossiblyUpdating(Context context, int i) {
        if (i == 9) {
            return isUninstalledAppPossiblyUpdating(context, "com.android.vending");
        }
        return false;
    }

    @TargetApi(21)
    static boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(appPackageName.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !isRestrictedUserProfile(context);
        } catch (NameNotFoundException e2) {
            return false;
        }
    }

    @TargetApi(18)
    @KeepForSdk
    public static boolean isRestrictedUserProfile(Context context) {
        if (PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }
}
