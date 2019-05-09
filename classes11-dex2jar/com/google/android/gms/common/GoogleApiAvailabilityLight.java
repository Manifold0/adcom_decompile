// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.HideFirstParty;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.DeviceProperties;
import android.content.Intent;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.content.Context;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@ShowFirstParty
public class GoogleApiAvailabilityLight
{
    @KeepForSdk
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    @KeepForSdk
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    @KeepForSdk
    static final String TRACKING_SOURCE_DIALOG = "d";
    @KeepForSdk
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    private static final GoogleApiAvailabilityLight zzm;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zzm = new GoogleApiAvailabilityLight();
    }
    
    @KeepForSdk
    GoogleApiAvailabilityLight() {
    }
    
    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return GoogleApiAvailabilityLight.zzm;
    }
    
    @VisibleForTesting
    private static String zza(@Nullable final Context context, @Nullable final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        Label_0094: {
            if (context == null) {
                break Label_0094;
            }
            try {
                sb.append(Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionCode);
                return sb.toString();
            }
            catch (PackageManager$NameNotFoundException ex) {
                return sb.toString();
            }
        }
    }
    
    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(final Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }
    
    @KeepForSdk
    @ShowFirstParty
    public int getApkVersion(final Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }
    
    @KeepForSdk
    @ShowFirstParty
    public int getClientVersion(final Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }
    
    @Deprecated
    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public Intent getErrorResolutionIntent(final int n) {
        return this.getErrorResolutionIntent(null, n, null);
    }
    
    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public Intent getErrorResolutionIntent(final Context context, final int n, @Nullable final String s) {
        switch (n) {
            default: {
                return null;
            }
            case 1:
            case 2: {
                if (context != null && DeviceProperties.isWearableWithoutPlayStore(context)) {
                    return zzg.zzs();
                }
                return zzg.zza("com.google.android.gms", zza(context, s));
            }
            case 3: {
                return zzg.zzg("com.google.android.gms");
            }
        }
    }
    
    @Nullable
    @KeepForSdk
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return this.getErrorResolutionPendingIntent(context, n, n2, null);
    }
    
    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2, @Nullable final String s) {
        final Intent errorResolutionIntent = this.getErrorResolutionIntent(context, n, s);
        if (errorResolutionIntent == null) {
            return null;
        }
        return PendingIntent.getActivity(context, n2, errorResolutionIntent, 134217728);
    }
    
    @KeepForSdk
    public String getErrorString(final int n) {
        return GooglePlayServicesUtilLight.getErrorString(n);
    }
    
    @KeepForSdk
    @HideFirstParty
    public int isGooglePlayServicesAvailable(final Context context) {
        return this.isGooglePlayServicesAvailable(context, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
    
    @KeepForSdk
    public int isGooglePlayServicesAvailable(final Context context, int googlePlayServicesAvailable) {
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, googlePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, googlePlayServicesAvailable))) {
            googlePlayServicesAvailable = 18;
        }
        return googlePlayServicesAvailable;
    }
    
    @KeepForSdk
    @ShowFirstParty
    public boolean isPlayServicesPossiblyUpdating(final Context context, final int n) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, n);
    }
    
    @KeepForSdk
    @ShowFirstParty
    public boolean isPlayStorePossiblyUpdating(final Context context, final int n) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, n);
    }
    
    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(final Context context, final String s) {
        return GooglePlayServicesUtilLight.isUninstalledAppPossiblyUpdating(context, s);
    }
    
    @KeepForSdk
    public boolean isUserResolvableError(final int n) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(n);
    }
    
    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(final Context context, final int n) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, n);
    }
}
