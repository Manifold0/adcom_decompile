// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.os.Message;
import android.os.Looper;
import android.annotation.SuppressLint;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabr;
import android.support.annotation.NonNull;
import android.support.annotation.MainThread;
import com.google.android.gms.common.api.internal.zabu;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.common.internal.HideFirstParty;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.GoogleApi;
import android.annotation.TargetApi;
import android.app.Notification;
import android.content.res.Resources;
import android.util.Log;
import android.app.NotificationChannel;
import com.google.android.gms.base.R$string;
import com.google.android.gms.base.R$drawable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.DeviceProperties;
import android.support.v4.app.NotificationCompat$Style;
import android.support.v4.app.NotificationCompat$BigTextStyle;
import android.support.v4.app.NotificationCompat$Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import com.google.android.gms.common.internal.DialogRedirect;
import android.app.AlertDialog;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import android.view.View;
import android.app.AlertDialog$Builder;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ProgressBar;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.app.Activity;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.GuardedBy;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final Object mLock;
    private static final GoogleApiAvailability zaao;
    @GuardedBy("mLock")
    private String zaap;
    
    static {
        mLock = new Object();
        zaao = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }
    
    @VisibleForTesting
    public GoogleApiAvailability() {
    }
    
    public static GoogleApiAvailability getInstance() {
        return GoogleApiAvailability.zaao;
    }
    
    public static Dialog zaa(final Activity activity, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final ProgressBar view = new ProgressBar((Context)activity, (AttributeSet)null, 16842874);
        view.setIndeterminate(true);
        view.setVisibility(0);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity);
        alertDialog$Builder.setView((View)view);
        alertDialog$Builder.setMessage((CharSequence)ConnectionErrorMessages.getErrorMessage((Context)activity, 18));
        alertDialog$Builder.setPositiveButton((CharSequence)"", (DialogInterface$OnClickListener)null);
        final AlertDialog create = alertDialog$Builder.create();
        zaa(activity, (Dialog)create, "GooglePlayServicesUpdatingDialog", dialogInterface$OnCancelListener);
        return (Dialog)create;
    }
    
    static Dialog zaa(final Context context, final int n, final DialogRedirect dialogRedirect, final DialogInterface$OnCancelListener onCancelListener) {
        AlertDialog$Builder alertDialog$Builder = null;
        if (n == 0) {
            return null;
        }
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843529, typedValue, true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
            alertDialog$Builder = new AlertDialog$Builder(context, 5);
        }
        AlertDialog$Builder alertDialog$Builder2;
        if ((alertDialog$Builder2 = alertDialog$Builder) == null) {
            alertDialog$Builder2 = new AlertDialog$Builder(context);
        }
        alertDialog$Builder2.setMessage((CharSequence)ConnectionErrorMessages.getErrorMessage(context, n));
        if (onCancelListener != null) {
            alertDialog$Builder2.setOnCancelListener(onCancelListener);
        }
        final String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, n);
        if (errorDialogButtonMessage != null) {
            alertDialog$Builder2.setPositiveButton((CharSequence)errorDialogButtonMessage, (DialogInterface$OnClickListener)dialogRedirect);
        }
        final String errorTitle = ConnectionErrorMessages.getErrorTitle(context, n);
        if (errorTitle != null) {
            alertDialog$Builder2.setTitle((CharSequence)errorTitle);
        }
        return (Dialog)alertDialog$Builder2.create();
    }
    
    static void zaa(final Activity activity, final Dialog dialog, final String s, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        if (activity instanceof FragmentActivity) {
            SupportErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(((FragmentActivity)activity).getSupportFragmentManager(), s);
            return;
        }
        ErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(activity.getFragmentManager(), s);
    }
    
    @TargetApi(20)
    private final void zaa(final Context context, int n, String s, final PendingIntent pendingIntent) {
        if (n == 18) {
            this.zaa(context);
        }
        else {
            if (pendingIntent != null) {
                final String errorNotificationTitle = ConnectionErrorMessages.getErrorNotificationTitle(context, n);
                s = ConnectionErrorMessages.getErrorNotificationMessage(context, n);
                final Resources resources = context.getResources();
                final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
                final NotificationCompat$Builder setStyle = new NotificationCompat$Builder(context).setLocalOnly(true).setAutoCancel(true).setContentTitle((CharSequence)errorNotificationTitle).setStyle((NotificationCompat$Style)new NotificationCompat$BigTextStyle().bigText((CharSequence)s));
                if (DeviceProperties.isWearable(context)) {
                    Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
                    setStyle.setSmallIcon(context.getApplicationInfo().icon).setPriority(2);
                    if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                        setStyle.addAction(R$drawable.common_full_open_on_phone, (CharSequence)resources.getString(R$string.common_open_on_phone), pendingIntent);
                    }
                    else {
                        setStyle.setContentIntent(pendingIntent);
                    }
                }
                else {
                    setStyle.setSmallIcon(17301642).setTicker((CharSequence)resources.getString(R$string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).setContentText((CharSequence)s);
                }
                if (PlatformVersion.isAtLeastO()) {
                    Preconditions.checkState(PlatformVersion.isAtLeastO());
                    if ((s = this.zag()) == null) {
                        final String s2 = "com.google.android.gms.availability";
                        final NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                        final String defaultNotificationChannelName = ConnectionErrorMessages.getDefaultNotificationChannelName(context);
                        if (notificationChannel == null) {
                            notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", (CharSequence)defaultNotificationChannelName, 4));
                            s = s2;
                        }
                        else {
                            s = s2;
                            if (!defaultNotificationChannelName.contentEquals(notificationChannel.getName())) {
                                notificationChannel.setName((CharSequence)defaultNotificationChannelName);
                                notificationManager.createNotificationChannel(notificationChannel);
                                s = s2;
                            }
                        }
                    }
                    setStyle.setChannelId(s);
                }
                final Notification build = setStyle.build();
                switch (n) {
                    default: {
                        n = 39789;
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        n = 10436;
                        GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
                        break;
                    }
                }
                notificationManager.notify(n, build);
                return;
            }
            if (n == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
            }
        }
    }
    
    @VisibleForTesting(otherwise = 2)
    private final String zag() {
        synchronized (GoogleApiAvailability.mLock) {
            return this.zaap;
        }
    }
    
    public Task<Void> checkApiAvailability(final GoogleApi<?> googleApi, final GoogleApi<?>... array) {
        Preconditions.checkNotNull((Object)googleApi, (Object)"Requested API must not be null.");
        for (int length = array.length, i = 0; i < length; ++i) {
            Preconditions.checkNotNull((Object)array[i], (Object)"Requested API must not be null.");
        }
        final ArrayList<GoogleApi<?>> list = new ArrayList<GoogleApi<?>>(array.length + 1);
        list.add(googleApi);
        list.addAll((Collection<?>)Arrays.asList(array));
        return (Task<Void>)GoogleApiManager.zabc().zaa(list).continueWith((Continuation)new com.google.android.gms.common.zaa(this));
    }
    
    @KeepForSdk
    @ShowFirstParty
    public int getClientVersion(final Context context) {
        return super.getClientVersion(context);
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2) {
        return this.getErrorDialog(activity, n, n2, null);
    }
    
    public Dialog getErrorDialog(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return zaa((Context)activity, n, DialogRedirect.getInstance(activity, this.getErrorResolutionIntent((Context)activity, n, "d"), n2), dialogInterface$OnCancelListener);
    }
    
    @Nullable
    @KeepForSdk
    @ShowFirstParty
    public Intent getErrorResolutionIntent(final Context context, final int n, @Nullable final String s) {
        return super.getErrorResolutionIntent(context, n, s);
    }
    
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return super.getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            return connectionResult.getResolution();
        }
        return this.getErrorResolutionPendingIntent(context, connectionResult.getErrorCode(), 0);
    }
    
    public final String getErrorString(final int n) {
        return super.getErrorString(n);
    }
    
    @HideFirstParty
    public int isGooglePlayServicesAvailable(final Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }
    
    @KeepForSdk
    @ShowFirstParty
    public int isGooglePlayServicesAvailable(final Context context, final int n) {
        return super.isGooglePlayServicesAvailable(context, n);
    }
    
    public final boolean isUserResolvableError(final int n) {
        return super.isUserResolvableError(n);
    }
    
    @MainThread
    public Task<Void> makeGooglePlayServicesAvailable(final Activity activity) {
        final int google_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
        final int googlePlayServicesAvailable = this.isGooglePlayServicesAvailable((Context)activity, google_PLAY_SERVICES_VERSION_CODE);
        if (googlePlayServicesAvailable == 0) {
            return (Task<Void>)Tasks.forResult((Object)null);
        }
        final zabu zac = zabu.zac(activity);
        zac.zab(new ConnectionResult(googlePlayServicesAvailable, (PendingIntent)null), 0);
        return zac.getTask();
    }
    
    @TargetApi(26)
    public void setDefaultNotificationChannelId(@NonNull final Context context, @NonNull final String zaap) {
        if (PlatformVersion.isAtLeastO()) {
            Preconditions.checkNotNull((Object)((NotificationManager)context.getSystemService("notification")).getNotificationChannel(zaap));
        }
        synchronized (GoogleApiAvailability.mLock) {
            this.zaap = zaap;
        }
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2) {
        return this.showErrorDialogFragment(activity, n, n2, null);
    }
    
    public boolean showErrorDialogFragment(final Activity activity, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog errorDialog = this.getErrorDialog(activity, n, n2, dialogInterface$OnCancelListener);
        if (errorDialog == null) {
            return false;
        }
        zaa(activity, errorDialog, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
        return true;
    }
    
    public void showErrorNotification(final Context context, final int n) {
        this.zaa(context, n, null, this.getErrorResolutionPendingIntent(context, n, 0, "n"));
    }
    
    public void showErrorNotification(final Context context, final ConnectionResult connectionResult) {
        this.zaa(context, connectionResult.getErrorCode(), null, this.getErrorResolutionPendingIntent(context, connectionResult));
    }
    
    @Nullable
    public final zabq zaa(final Context context, final zabr zabr) {
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        final zabq zabq = new zabq(zabr);
        context.registerReceiver((BroadcastReceiver)zabq, intentFilter);
        zabq.zac(context);
        zabq zabq2 = zabq;
        if (!this.isUninstalledAppPossiblyUpdating(context, "com.google.android.gms")) {
            zabr.zas();
            zabq.unregister();
            zabq2 = null;
        }
        return zabq2;
    }
    
    final void zaa(final Context context) {
        new zaa(context).sendEmptyMessageDelayed(1, 120000L);
    }
    
    public final boolean zaa(final Activity activity, @NonNull final LifecycleFragment lifecycleFragment, final int n, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog zaa = zaa((Context)activity, n, DialogRedirect.getInstance(lifecycleFragment, this.getErrorResolutionIntent((Context)activity, n, "d"), 2), dialogInterface$OnCancelListener);
        if (zaa == null) {
            return false;
        }
        zaa(activity, zaa, "GooglePlayServicesErrorDialog", dialogInterface$OnCancelListener);
        return true;
    }
    
    public final boolean zaa(final Context context, final ConnectionResult connectionResult, final int n) {
        final PendingIntent errorResolutionPendingIntent = this.getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            this.zaa(context, connectionResult.getErrorCode(), null, GoogleApiActivity.zaa(context, errorResolutionPendingIntent, n));
            return true;
        }
        return false;
    }
    
    @SuppressLint({ "HandlerLeak" })
    private final class zaa extends zap
    {
        private final Context zaaq;
        
        public zaa(final Context context) {
            Looper looper;
            if (Looper.myLooper() == null) {
                looper = Looper.getMainLooper();
            }
            else {
                looper = Looper.myLooper();
            }
            super(looper);
            this.zaaq = context.getApplicationContext();
        }
        
        public final void handleMessage(final Message message) {
            switch (message.what) {
                default: {
                    Log.w("GoogleApiAvailability", new StringBuilder(50).append("Don't know how to handle this message: ").append(message.what).toString());
                    break;
                }
                case 1: {
                    final int googlePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zaaq);
                    if (GoogleApiAvailability.this.isUserResolvableError(googlePlayServicesAvailable)) {
                        GoogleApiAvailability.this.showErrorNotification(this.zaaq, googlePlayServicesAvailable);
                        return;
                    }
                    break;
                }
            }
        }
    }
}
