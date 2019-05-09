package net.hockeyapp.unity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.LoginManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.metrics.MetricsManager;

public class HockeyUnityPlugin {
    @TargetApi(9)
    @Deprecated
    public static void startHockeyAppManager(Activity currentActivity, String serverURL, String appID, String secret, int loginMode, boolean updateManagerEnabled, boolean userMetricsEnabled, boolean autoSendEnabled) {
        final boolean z = updateManagerEnabled;
        final Activity activity = currentActivity;
        final String str = serverURL;
        final String str2 = appID;
        final boolean z2 = userMetricsEnabled;
        final boolean z3 = autoSendEnabled;
        final String str3 = secret;
        final int i = loginMode;
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    HockeyUnityPlugin.registerUpdateManager(activity, str, str2);
                }
                if (z2) {
                    HockeyUnityPlugin.registerMetricsManager(activity, str2);
                }
                HockeyUnityPlugin.registerCrashManager(activity, str, str2, z3);
                HockeyUnityPlugin.registerFeedbackManager(activity, str, str2);
                HockeyUnityPlugin.registerLoginManager(activity, str, str2, str3, i);
            }
        });
    }

    @TargetApi(9)
    public static void registerUpdateManager(final Activity currentActivity, final String serverURL, final String appID) {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                UpdateManager.register(currentActivity, serverURL, appID, null, true);
            }
        });
    }

    @TargetApi(9)
    public static void registerCrashManager(final Activity currentActivity, final String serverURL, final String appID, final boolean autoSendEnabled) {
        currentActivity.runOnUiThread(new Runnable() {

            /* renamed from: net.hockeyapp.unity.HockeyUnityPlugin$3$1 */
            class C04281 extends CrashManagerListener {
                C04281() {
                }

                public boolean shouldAutoUploadCrashes() {
                    return autoSendEnabled;
                }
            }

            public void run() {
                CrashManager.register(currentActivity, serverURL, appID, new C04281());
            }
        });
    }

    @TargetApi(9)
    public static void registerLoginManager(Activity currentActivity, String serverURL, String appID, String secret, int loginMode) {
        final Activity activity = currentActivity;
        final String str = appID;
        final String str2 = secret;
        final String str3 = serverURL;
        final int i = loginMode;
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                LoginManager.register(activity, str, str2, str3, i, activity.getClass());
                LoginManager.verifyLogin(activity, activity.getIntent());
            }
        });
    }

    @TargetApi(9)
    public static void registerFeedbackManager(final Activity currentActivity, final String serverURL, final String appID) {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                FeedbackManager.register(currentActivity, serverURL, appID, null);
            }
        });
    }

    @TargetApi(9)
    public static void registerMetricsManager(final Activity currentActivity, final String appID) {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                MetricsManager.register(currentActivity, currentActivity.getApplication(), appID);
            }
        });
    }

    @TargetApi(9)
    public static void registerAll(final Activity currentActivity, final String serverURL, final String appID) {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                HockeyUnityPlugin.registerUpdateManager(currentActivity, serverURL, appID);
                HockeyUnityPlugin.registerCrashManager(currentActivity, serverURL, appID, true);
                HockeyUnityPlugin.registerFeedbackManager(currentActivity, serverURL, appID);
                HockeyUnityPlugin.registerMetricsManager(currentActivity, appID);
            }
        });
    }

    public static String getVersionCode() {
        return Constants.APP_VERSION;
    }

    public static String getVersionName() {
        return Constants.APP_VERSION_NAME;
    }

    public static String getSdkName() {
        return Constants.SDK_NAME;
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

    @TargetApi(9)
    public static void startFeedbackForm(final Activity currentActivity) {
        currentActivity.runOnUiThread(new Runnable() {
            public void run() {
                FeedbackManager.showFeedbackActivity(currentActivity, new Uri[0]);
            }
        });
    }

    @TargetApi(9)
    public static void checkForUpdate(Activity currentActivity, String serverURL, String appID) {
        UpdateManager.unregister();
        registerUpdateManager(currentActivity, serverURL, appID);
    }
}
