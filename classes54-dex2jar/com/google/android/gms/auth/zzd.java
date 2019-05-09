// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import android.os.SystemClock;
import android.text.TextUtils;
import android.os.RemoteException;
import android.content.ServiceConnection;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.BlockingServiceConnection;
import android.annotation.TargetApi;
import android.support.annotation.RequiresPermission;
import android.accounts.AccountManager;
import android.accounts.Account;
import java.util.List;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.io.IOException;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import com.google.android.gms.common.logging.Logger;
import android.content.ComponentName;
import android.annotation.SuppressLint;

public class zzd
{
    private static final String[] ACCEPTABLE_ACCOUNT_TYPES;
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    @SuppressLint({ "InlinedApi" })
    public static final String KEY_ANDROID_PACKAGE_NAME;
    @SuppressLint({ "InlinedApi" })
    public static final String KEY_CALLER_UID;
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    private static final ComponentName zzm;
    private static final Logger zzn;
    
    static {
        ACCEPTABLE_ACCOUNT_TYPES = new String[] { "com.google", "com.google.work", "cn.google" };
        KEY_CALLER_UID = "callerUid";
        KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
        zzm = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        zzn = new Logger("Auth", new String[] { "GoogleAuthUtil" });
    }
    
    zzd() {
    }
    
    public static void clearToken(final Context context, final String s) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        ensurePlayServicesAvailable(context, 8400000);
        final Bundle bundle = new Bundle();
        final String packageName = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", packageName);
        if (!bundle.containsKey(zzd.KEY_ANDROID_PACKAGE_NAME)) {
            bundle.putString(zzd.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        zza(context, zzd.zzm, (zzj<Object>)new zzf(s, bundle));
    }
    
    private static void ensurePlayServicesAvailable(final Context context, final int n) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context.getApplicationContext(), n);
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new GooglePlayServicesAvailabilityException(ex.getConnectionStatusCode(), ex.getMessage(), ex.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            throw new GoogleAuthException(ex2.getMessage());
        }
    }
    
    public static List<AccountChangeEvent> getAccountChangeEvents(final Context context, final int n, final String s) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(s, (Object)"accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        ensurePlayServicesAvailable(context, 8400000);
        return zza(context, zzd.zzm, (zzj<List<AccountChangeEvent>>)new zzg(s, n));
    }
    
    public static String getAccountId(final Context context, final String s) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(s, (Object)"accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        ensurePlayServicesAvailable(context, 8400000);
        return getToken(context, s, "^^_account_id_^^", new Bundle());
    }
    
    public static String getToken(final Context context, final Account account, final String s) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, account, s, new Bundle());
    }
    
    public static String getToken(final Context context, final Account account, final String s, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzb(account);
        return zzb(context, account, s, bundle).zzb();
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(s, "com.google"), s2);
    }
    
    @Deprecated
    public static String getToken(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, new Account(s, "com.google"), s2, bundle);
    }
    
    @Deprecated
    @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
    public static void invalidateToken(final Context context, final String s) {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }
    
    @TargetApi(23)
    public static Bundle removeAccount(final Context context, final Account account) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull((Object)context);
        zzb(account);
        ensurePlayServicesAvailable(context, 8400000);
        return zza(context, zzd.zzm, (zzj<Bundle>)new zzh(account));
    }
    
    @TargetApi(26)
    public static Boolean requestGoogleAccountsAccess(final Context context) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull((Object)context);
        ensurePlayServicesAvailable(context, 11400000);
        return zza(context, zzd.zzm, (zzj<Boolean>)new zzi(context.getApplicationInfo().packageName));
    }
    
    private static <T> T zza(Context zzb, final ComponentName componentName, final zzj<T> zzj) throws IOException, GoogleAuthException {
        final BlockingServiceConnection blockingServiceConnection = new BlockingServiceConnection();
        if (!GmsClientSupervisor.getInstance((Context)zzb).bindService(componentName, (ServiceConnection)blockingServiceConnection, "GoogleAuthUtil")) {
            goto Label_0095;
        }
        try {
            zzb = (RemoteException)zzj.zzb(blockingServiceConnection.getService());
            return (T)zzb;
        }
        catch (InterruptedException ex) {}
        catch (RemoteException zzb) {}
    }
    
    private static <T> T zza(final T t) throws IOException {
        if (t == null) {
            zzd.zzn.w("GoogleAuthUtil", new Object[] { "Binder call returned null." });
            throw new IOException("Service unavailable.");
        }
        return t;
    }
    
    public static TokenData zzb(final Context context, final Account account, final String s, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        Preconditions.checkNotEmpty(s, (Object)"Scope cannot be empty or null.");
        zzb(account);
        ensurePlayServicesAvailable(context, 8400000);
        if (bundle == null) {
            bundle = new Bundle();
        }
        else {
            bundle = new Bundle(bundle);
        }
        final String packageName = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", packageName);
        if (TextUtils.isEmpty((CharSequence)bundle.getString(zzd.KEY_ANDROID_PACKAGE_NAME))) {
            bundle.putString(zzd.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        bundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
        return zza(context, zzd.zzm, (zzj<TokenData>)new zze(account, s, bundle));
    }
    
    private static void zzb(final Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if (TextUtils.isEmpty((CharSequence)account.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        final String[] acceptable_ACCOUNT_TYPES = zzd.ACCEPTABLE_ACCOUNT_TYPES;
        for (int length = acceptable_ACCOUNT_TYPES.length, i = 0; i < length; ++i) {
            if (acceptable_ACCOUNT_TYPES[i].equals(account.type)) {
                return;
            }
        }
        throw new IllegalArgumentException("Account type not supported");
    }
}
