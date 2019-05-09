// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.unity;

import android.net.Uri;
import net.hockeyapp.android.UpdateManagerListener;
import net.hockeyapp.android.metrics.MetricsManager;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.FeedbackManagerListener;
import net.hockeyapp.android.FeedbackManager;
import android.content.Context;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.Constants;
import android.annotation.TargetApi;
import net.hockeyapp.android.UpdateManager;
import android.app.Activity;

public class HockeyUnityPlugin
{
    @TargetApi(9)
    public static void checkForUpdate(final Activity activity, final String s, final String s2) {
        UpdateManager.unregister();
        registerUpdateManager(activity, s, s2);
    }
    
    public static String getCrashReporterKey() {
        return Constants.CRASH_IDENTIFIER;
    }
    
    public static String getManufacturer() {
        return Constants.PHONE_MANUFACTURER;
    }
    
    public static String getModel() {
        return Constants.PHONE_MODEL;
    }
    
    public static String getSdkName() {
        return "HockeySDK";
    }
    
    public static String getVersionCode() {
        return Constants.APP_VERSION;
    }
    
    public static String getVersionName() {
        return Constants.APP_VERSION_NAME;
    }
    
    @TargetApi(9)
    public static void registerAll(final Activity activity, final String s, final String s2) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                HockeyUnityPlugin.registerUpdateManager(activity, s, s2);
                HockeyUnityPlugin.registerCrashManager(activity, s, s2, true);
                HockeyUnityPlugin.registerFeedbackManager(activity, s, s2);
                HockeyUnityPlugin.registerMetricsManager(activity, s2);
            }
        });
    }
    
    @TargetApi(9)
    public static void registerCrashManager(final Activity activity, final String s, final String s2, final boolean b) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                CrashManager.register((Context)activity, s, s2, (CrashManagerListener)new CrashManagerListener() {
                    public boolean shouldAutoUploadCrashes() {
                        return b;
                    }
                });
            }
        });
    }
    
    @TargetApi(9)
    public static void registerFeedbackManager(final Activity activity, final String s, final String s2) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                FeedbackManager.register((Context)activity, s, s2, (FeedbackManagerListener)null);
            }
        });
    }
    
    @TargetApi(9)
    public static void registerLoginManager(final Activity activity, final String s, final String s2, final String s3, final int n) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                LoginManager.register((Context)activity, s2, s3, s, n, (Class)activity.getClass());
                LoginManager.verifyLogin(activity, activity.getIntent());
            }
        });
    }
    
    @TargetApi(9)
    public static void registerMetricsManager(final Activity activity, final String s) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                MetricsManager.register((Context)activity, activity.getApplication(), s);
            }
        });
    }
    
    @TargetApi(9)
    public static void registerUpdateManager(final Activity activity, final String s, final String s2) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                UpdateManager.register(activity, s, s2, (UpdateManagerListener)null, true);
            }
        });
    }
    
    @TargetApi(9)
    public static void startFeedbackForm(final Activity activity) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                FeedbackManager.showFeedbackActivity((Context)activity, new Uri[0]);
            }
        });
    }
    
    @Deprecated
    @TargetApi(9)
    public static void startHockeyAppManager(final Activity activity, final String s, final String s2, final String s3, final int n, final boolean b, final boolean b2, final boolean b3) {
        activity.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                if (b) {
                    HockeyUnityPlugin.registerUpdateManager(activity, s, s2);
                }
                if (b2) {
                    HockeyUnityPlugin.registerMetricsManager(activity, s2);
                }
                HockeyUnityPlugin.registerCrashManager(activity, s, s2, b3);
                HockeyUnityPlugin.registerFeedbackManager(activity, s, s2);
                HockeyUnityPlugin.registerLoginManager(activity, s, s2, s3, n);
            }
        });
    }
}
