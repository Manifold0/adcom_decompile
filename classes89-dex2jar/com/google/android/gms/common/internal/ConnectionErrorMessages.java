// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.util.DeviceProperties;
import android.support.annotation.NonNull;
import android.content.res.Resources;
import com.google.android.gms.base.R$string;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import android.support.v4.util.SimpleArrayMap;

public final class ConnectionErrorMessages
{
    @GuardedBy("sCache")
    private static final SimpleArrayMap<String, String> zaog;
    
    static {
        zaog = new SimpleArrayMap();
    }
    
    private ConnectionErrorMessages() {
    }
    
    public static String getAppName(Context string) {
        final String packageName = ((Context)string).getPackageName();
        try {
            string = Wrappers.packageManager((Context)string).getApplicationLabel(packageName).toString();
            return (String)string;
        }
        catch (NullPointerException ex) {}
        catch (PackageManager$NameNotFoundException ex2) {
            goto Label_0024;
        }
    }
    
    public static String getDefaultNotificationChannelName(final Context context) {
        return context.getResources().getString(R$string.common_google_play_services_notification_channel_name);
    }
    
    @NonNull
    public static String getErrorDialogButtonMessage(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(17039370);
            }
            case 1: {
                return resources.getString(R$string.common_google_play_services_install_button);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_button);
            }
            case 2: {
                return resources.getString(R$string.common_google_play_services_update_button);
            }
        }
    }
    
    @NonNull
    public static String getErrorMessage(final Context context, final int n) {
        final Resources resources = context.getResources();
        final String appName = getAppName(context);
        switch (n) {
            default: {
                return resources.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue, new Object[] { appName });
            }
            case 1: {
                return resources.getString(R$string.common_google_play_services_install_text, new Object[] { appName });
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_text, new Object[] { appName });
            }
            case 18: {
                return resources.getString(R$string.common_google_play_services_updating_text, new Object[] { appName });
            }
            case 2: {
                if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                    return resources.getString(R$string.common_google_play_services_wear_update_text);
                }
                return resources.getString(R$string.common_google_play_services_update_text, new Object[] { appName });
            }
            case 9: {
                return resources.getString(R$string.common_google_play_services_unsupported_text, new Object[] { appName });
            }
            case 7: {
                return zaa(context, "common_google_play_services_network_error_text", appName);
            }
            case 5: {
                return zaa(context, "common_google_play_services_invalid_account_text", appName);
            }
            case 16: {
                return zaa(context, "common_google_play_services_api_unavailable_text", appName);
            }
            case 17: {
                return zaa(context, "common_google_play_services_sign_in_failed_text", appName);
            }
            case 20: {
                return zaa(context, "common_google_play_services_restricted_profile_text", appName);
            }
        }
    }
    
    @NonNull
    public static String getErrorNotificationMessage(final Context context, final int n) {
        if (n == 6) {
            return zaa(context, "common_google_play_services_resolution_required_text", getAppName(context));
        }
        return getErrorMessage(context, n);
    }
    
    @NonNull
    public static String getErrorNotificationTitle(final Context context, final int n) {
        String s;
        if (n == 6) {
            s = zaa(context, "common_google_play_services_resolution_required_title");
        }
        else {
            s = getErrorTitle(context, n);
        }
        String string = s;
        if (s == null) {
            string = context.getResources().getString(R$string.common_google_play_services_notification_ticker);
        }
        return string;
    }
    
    @Nullable
    public static String getErrorTitle(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                Log.e("GoogleApiAvailability", new StringBuilder(33).append("Unexpected error code ").append(n).toString());
                return null;
            }
            case 4:
            case 6:
            case 18: {
                return null;
            }
            case 1: {
                return resources.getString(R$string.common_google_play_services_install_title);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_title);
            }
            case 2: {
                return resources.getString(R$string.common_google_play_services_update_title);
            }
            case 9: {
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            }
            case 7: {
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zaa(context, "common_google_play_services_network_error_title");
            }
            case 8: {
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            }
            case 10: {
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            }
            case 5: {
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zaa(context, "common_google_play_services_invalid_account_title");
            }
            case 11: {
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            }
            case 16: {
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            }
            case 17: {
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zaa(context, "common_google_play_services_sign_in_failed_title");
            }
            case 20: {
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zaa(context, "common_google_play_services_restricted_profile_title");
            }
        }
    }
    
    @Nullable
    private static String zaa(final Context context, final String s) {
        final int identifier;
        synchronized (ConnectionErrorMessages.zaog) {
            final String s2 = (String)ConnectionErrorMessages.zaog.get((Object)s);
            if (s2 != null) {
                return s2;
            }
            final Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            identifier = remoteResource.getIdentifier(s, "string", "com.google.android.gms");
            if (identifier == 0) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Missing resource: ".concat(value);
                }
                else {
                    concat = new String("Missing resource: ");
                }
                Log.w("GoogleApiAvailability", concat);
                return null;
            }
        }
        final Resources resources;
        final String string = resources.getString(identifier);
        if (TextUtils.isEmpty((CharSequence)string)) {
            final String value2 = String.valueOf(s);
            String concat2;
            if (value2.length() != 0) {
                concat2 = "Got empty resource: ".concat(value2);
            }
            else {
                concat2 = new String("Got empty resource: ");
            }
            Log.w("GoogleApiAvailability", concat2);
            // monitorexit(simpleArrayMap)
            return null;
        }
        ConnectionErrorMessages.zaog.put((Object)s, (Object)string);
        // monitorexit(simpleArrayMap)
        return string;
    }
    
    private static String zaa(final Context context, String s, final String s2) {
        final Resources resources = context.getResources();
        String s3;
        s = (s3 = zaa(context, s));
        if (s == null) {
            s3 = resources.getString(com.google.android.gms.common.R$string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, s3, s2);
    }
}
