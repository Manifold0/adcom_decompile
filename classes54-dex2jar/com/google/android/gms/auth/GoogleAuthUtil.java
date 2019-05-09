// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.annotation.TargetApi;
import android.support.annotation.RequiresPermission;
import android.content.ContentResolver;
import com.google.android.gms.common.internal.Preconditions;
import java.net.URISyntaxException;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Bundle;
import android.accounts.Account;
import java.util.List;
import java.io.IOException;
import android.content.Context;

public final class GoogleAuthUtil extends zzd
{
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    private static final String KEY_ANDROID_PACKAGE_NAME;
    private static final String KEY_CALLER_UID;
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    
    static {
        KEY_CALLER_UID = zzd.KEY_CALLER_UID;
        KEY_ANDROID_PACKAGE_NAME = zzd.KEY_ANDROID_PACKAGE_NAME;
    }
    
    private GoogleAuthUtil() {
    }
    
    public static void clearToken(final Context context, final String s) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        zzd.clearToken(context, s);
    }
    
    public static List<AccountChangeEvent> getAccountChangeEvents(final Context context, final int n, final String s) throws GoogleAuthException, IOException {
        return zzd.getAccountChangeEvents(context, n, s);
    }
    
    public static String getAccountId(final Context context, final String s) throws GoogleAuthException, IOException {
        return zzd.getAccountId(context, s);
    }
    
    public static String getToken(final Context context, final Account account, final String s) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, account, s);
    }
    
    public static String getToken(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, account, s, bundle);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, s, s2);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzd.getToken(context, s, s2, bundle);
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putBoolean("handle_notification", true);
        return zza(context, account, s, bundle2).zzb();
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, final Bundle bundle, final Intent intent) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        final String uri = intent.toUri(1);
        try {
            Intent.parseUri(uri, 1);
            Bundle bundle2 = bundle;
            if (bundle == null) {
                bundle2 = new Bundle();
            }
            bundle2.putParcelable("callback_intent", (Parcelable)intent);
            bundle2.putBoolean("handle_notification", true);
            return zza(context, account, s, bundle2).zzb();
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }
    
    public static String getTokenWithNotification(final Context context, final Account account, final String s, Bundle o, final String s2, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        Preconditions.checkNotEmpty(s2, (Object)"Authority cannot be empty or null.");
        Object o2 = o;
        if (o == null) {
            o2 = new Bundle();
        }
        if ((o = bundle) == null) {
            o = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle((Bundle)o);
        ((Bundle)o2).putString("authority", s2);
        ((Bundle)o2).putBundle("sync_extras", (Bundle)o);
        ((Bundle)o2).putBoolean("handle_notification", true);
        return zza(context, account, s, (Bundle)o2).zzb();
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle);
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final Intent intent) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle, intent);
    }
    
    @Deprecated
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final String s3, final Bundle bundle2) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        return getTokenWithNotification(context, new Account(s, "com.google"), s2, bundle, s3, bundle2);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    public static void invalidateToken(final Context context, final String s) {
        zzd.invalidateToken(context, s);
    }
    
    @TargetApi(23)
    public static Bundle removeAccount(final Context context, final Account account) throws GoogleAuthException, IOException {
        return zzd.removeAccount(context, account);
    }
    
    @TargetApi(26)
    public static Boolean requestGoogleAccountsAccess(final Context context) throws GoogleAuthException, IOException {
        return zzd.requestGoogleAccountsAccess(context);
    }
    
    private static TokenData zza(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, GoogleAuthException {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        try {
            final TokenData zzb = zzd.zzb(context, account, s, bundle2);
            GooglePlayServicesUtil.cancelAvailabilityErrorNotifications(context);
            return zzb;
        }
        catch (GooglePlayServicesAvailabilityException ex) {
            GooglePlayServicesUtil.showErrorNotification(ex.getConnectionStatusCode(), context);
            Log.w("GoogleAuthUtil", "Error when getting token", (Throwable)ex);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        catch (UserRecoverableAuthException ex2) {
            GooglePlayServicesUtil.cancelAvailabilityErrorNotifications(context);
            Log.w("GoogleAuthUtil", "Error when getting token", (Throwable)ex2);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }
}
