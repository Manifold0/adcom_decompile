// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.tasks.CheckUpdateTaskWithUI;
import android.os.AsyncTask$Status;
import android.content.Intent;
import net.hockeyapp.android.utils.Util;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.content.Context;
import android.annotation.TargetApi;
import java.util.Date;
import android.app.Activity;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.tasks.CheckUpdateTask;

public class UpdateManager
{
    public static final String INSTALLER_ADB = "adb";
    public static final String INSTALLER_PACKAGE_INSTALLER_NOUGAT = "com.google.android.packageinstaller";
    private static UpdateManagerListener lastListener;
    private static CheckUpdateTask updateTask;
    
    static {
        UpdateManager.updateTask = null;
        UpdateManager.lastListener = null;
    }
    
    private static boolean checkExpiryDate(final WeakReference<Activity> weakReference, final UpdateManagerListener updateManagerListener) {
        boolean onBuildExpired = false;
        final boolean checkExpiryDateForBackground = checkExpiryDateForBackground(updateManagerListener);
        if (checkExpiryDateForBackground) {
            onBuildExpired = updateManagerListener.onBuildExpired();
        }
        if (checkExpiryDateForBackground && onBuildExpired) {
            startExpiryInfoIntent(weakReference);
        }
        return checkExpiryDateForBackground;
    }
    
    private static boolean checkExpiryDateForBackground(final UpdateManagerListener updateManagerListener) {
        boolean b = false;
        if (updateManagerListener != null) {
            final Date expiryDate = updateManagerListener.getExpiryDate();
            if (expiryDate == null || new Date().compareTo(expiryDate) <= 0) {
                return false;
            }
            b = true;
        }
        return b;
    }
    
    @TargetApi(11)
    private static boolean dialogShown(final WeakReference<Activity> weakReference) {
        boolean b2;
        final boolean b = b2 = false;
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            b2 = b;
            if (activity != null) {
                b2 = b;
                if (activity.getFragmentManager().findFragmentByTag("hockey_update_dialog") != null) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static UpdateManagerListener getLastListener() {
        return UpdateManager.lastListener;
    }
    
    protected static boolean installedFromMarket(final WeakReference<? extends Context> weakReference) {
        final boolean b = false;
        final boolean b2 = false;
        final Context context = weakReference.get();
        int n = b2 ? 1 : 0;
        if (context == null) {
            return n != 0;
        }
        boolean b3 = b;
        try {
            final String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            n = (b2 ? 1 : 0);
            b3 = b;
            if (!TextUtils.isEmpty((CharSequence)installerPackageName)) {
                final boolean b4 = true;
                final boolean b5 = (n = 1) != 0;
                b3 = b4;
                if (Build$VERSION.SDK_INT >= 24) {
                    n = (b5 ? 1 : 0);
                    b3 = b4;
                    if (TextUtils.equals((CharSequence)installerPackageName, (CharSequence)"com.google.android.packageinstaller")) {
                        n = (false ? 1 : 0);
                    }
                }
                b3 = (n != 0);
                if (TextUtils.equals((CharSequence)installerPackageName, (CharSequence)"adb")) {
                    n = (false ? 1 : 0);
                }
            }
            return n != 0;
        }
        catch (Throwable t) {
            return b3;
        }
    }
    
    public static void register(final Activity activity) {
        final String appIdentifier = Util.getAppIdentifier((Context)activity);
        if (TextUtils.isEmpty((CharSequence)appIdentifier)) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(activity, appIdentifier);
    }
    
    public static void register(final Activity activity, final String s) {
        register(activity, s, true);
    }
    
    public static void register(final Activity activity, final String s, final String s2, final UpdateManagerListener updateManagerListener) {
        register(activity, s, s2, updateManagerListener, true);
    }
    
    public static void register(final Activity activity, final String s, String sanitizeAppIdentifier, final UpdateManagerListener lastListener, final boolean b) {
        sanitizeAppIdentifier = Util.sanitizeAppIdentifier(sanitizeAppIdentifier);
        UpdateManager.lastListener = lastListener;
        final WeakReference<Context> weakReference = new WeakReference<Context>((Context)activity);
        if ((!Util.fragmentsSupported() || !dialogShown((WeakReference<Activity>)weakReference)) && !checkExpiryDate((WeakReference<Activity>)weakReference, lastListener) && ((lastListener != null && lastListener.canUpdateInMarket()) || !installedFromMarket(weakReference))) {
            startUpdateTask((WeakReference<Activity>)weakReference, s, sanitizeAppIdentifier, lastListener, b);
        }
    }
    
    public static void register(final Activity activity, final String s, final UpdateManagerListener updateManagerListener) {
        register(activity, "https://sdk.hockeyapp.net/", s, updateManagerListener, true);
    }
    
    public static void register(final Activity activity, final String s, final UpdateManagerListener updateManagerListener, final boolean b) {
        register(activity, "https://sdk.hockeyapp.net/", s, updateManagerListener, b);
    }
    
    public static void register(final Activity activity, final String s, final boolean b) {
        register(activity, s, null, b);
    }
    
    public static void registerForBackground(final Context context, final String s, String sanitizeAppIdentifier, final UpdateManagerListener lastListener) {
        sanitizeAppIdentifier = Util.sanitizeAppIdentifier(sanitizeAppIdentifier);
        UpdateManager.lastListener = lastListener;
        final WeakReference<Context> weakReference = new WeakReference<Context>(context);
        if (!checkExpiryDateForBackground(lastListener) && ((lastListener != null && lastListener.canUpdateInMarket()) || !installedFromMarket(weakReference))) {
            startUpdateTaskForBackground(weakReference, s, sanitizeAppIdentifier, lastListener);
        }
    }
    
    public static void registerForBackground(final Context context, final String s, final UpdateManagerListener updateManagerListener) {
        registerForBackground(context, "https://sdk.hockeyapp.net/", s, updateManagerListener);
    }
    
    private static void startExpiryInfoIntent(final WeakReference<Activity> weakReference) {
        if (weakReference != null) {
            final Activity activity = weakReference.get();
            if (activity != null) {
                activity.finish();
                final Intent intent = new Intent((Context)activity, (Class)ExpiryInfoActivity.class);
                intent.addFlags(335544320);
                activity.startActivity(intent);
            }
        }
    }
    
    private static void startUpdateTask(final WeakReference<Activity> weakReference, final String s, final String s2, final UpdateManagerListener updateManagerListener, final boolean b) {
        if (UpdateManager.updateTask == null || UpdateManager.updateTask.getStatus() == AsyncTask$Status.FINISHED) {
            AsyncTaskUtils.execute(UpdateManager.updateTask = new CheckUpdateTaskWithUI(weakReference, s, s2, updateManagerListener, b));
            return;
        }
        UpdateManager.updateTask.attach((WeakReference<? extends Context>)weakReference);
    }
    
    private static void startUpdateTaskForBackground(final WeakReference<Context> weakReference, final String s, final String s2, final UpdateManagerListener updateManagerListener) {
        if (UpdateManager.updateTask == null || UpdateManager.updateTask.getStatus() == AsyncTask$Status.FINISHED) {
            AsyncTaskUtils.execute(UpdateManager.updateTask = new CheckUpdateTask(weakReference, s, s2, updateManagerListener));
            return;
        }
        UpdateManager.updateTask.attach(weakReference);
    }
    
    public static void unregister() {
        if (UpdateManager.updateTask != null) {
            UpdateManager.updateTask.cancel(true);
            UpdateManager.updateTask.detach();
            UpdateManager.updateTask = null;
        }
        UpdateManager.lastListener = null;
    }
}
