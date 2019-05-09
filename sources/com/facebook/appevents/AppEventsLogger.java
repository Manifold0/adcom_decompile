package com.facebook.appevents;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import bolts.AppLinks;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.Callback;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.kongregate.android.internal.sdk.C0498e;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppEventsLogger {
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final String APP_EVENT_NAME_PUSH_OPENED = "fb_mobile_push_opened";
    public static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String APP_EVENT_PUSH_PARAMETER_ACTION = "fb_push_action";
    private static final String APP_EVENT_PUSH_PARAMETER_CAMPAIGN = "fb_push_campaign";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final String PUSH_PAYLOAD_CAMPAIGN_KEY = "campaign";
    private static final String PUSH_PAYLOAD_KEY = "fb_push_payload";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private static final String TAG = AppEventsLogger.class.getCanonicalName();
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static FlushBehavior flushBehavior = FlushBehavior.AUTO;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByAppLink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private static Object staticLock = new Object();
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;

    /* renamed from: com.facebook.appevents.AppEventsLogger$5 */
    static class C11855 implements Runnable {
        C11855() {
        }

        public void run() {
            Set<String> applicationIds = new HashSet();
            for (AccessTokenAppIdPair accessTokenAppId : AppEventQueue.getKeySet()) {
                applicationIds.add(accessTokenAppId.getApplicationId());
            }
            for (String applicationId : applicationIds) {
                FetchedAppSettingsManager.queryAppSettings(applicationId, true);
            }
        }
    }

    public enum FlushBehavior {
        AUTO,
        EXPLICIT_ONLY
    }

    static class PersistedAppSessionInfo {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable = new C11861();
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
        private static boolean hasChanges = false;
        private static boolean isLoaded = false;
        private static final Object staticLock = new Object();

        /* renamed from: com.facebook.appevents.AppEventsLogger$PersistedAppSessionInfo$1 */
        static class C11861 implements Runnable {
            C11861() {
            }

            public void run() {
                PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
            }
        }

        PersistedAppSessionInfo() {
        }

        private static void restoreAppSessionInformation(Context context) {
            Throwable th;
            Exception e;
            ObjectInputStream ois = null;
            synchronized (staticLock) {
                if (!isLoaded) {
                    try {
                        ObjectInputStream ois2 = new ObjectInputStream(context.openFileInput(PERSISTED_SESSION_INFO_FILENAME));
                        try {
                            appSessionInfoMap = (HashMap) ois2.readObject();
                            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info loaded");
                            try {
                                Utility.closeQuietly(ois2);
                                context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                                if (appSessionInfoMap == null) {
                                    appSessionInfoMap = new HashMap();
                                }
                                isLoaded = true;
                                hasChanges = false;
                                ois = ois2;
                            } catch (Throwable th2) {
                                th = th2;
                                ois = ois2;
                                throw th;
                            }
                        } catch (FileNotFoundException e2) {
                            ois = ois2;
                            Utility.closeQuietly(ois);
                            context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                            if (appSessionInfoMap == null) {
                                appSessionInfoMap = new HashMap();
                            }
                            isLoaded = true;
                            hasChanges = false;
                        } catch (Exception e3) {
                            e = e3;
                            ois = ois2;
                            try {
                                Log.w(AppEventsLogger.TAG, "Got unexpected exception restoring app session info: " + e.toString());
                                Utility.closeQuietly(ois);
                                context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                                if (appSessionInfoMap == null) {
                                    appSessionInfoMap = new HashMap();
                                }
                                isLoaded = true;
                                hasChanges = false;
                            } catch (Throwable th3) {
                                th = th3;
                                Utility.closeQuietly(ois);
                                context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                                if (appSessionInfoMap == null) {
                                    appSessionInfoMap = new HashMap();
                                }
                                isLoaded = true;
                                hasChanges = false;
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            ois = ois2;
                            Utility.closeQuietly(ois);
                            context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                            if (appSessionInfoMap == null) {
                                appSessionInfoMap = new HashMap();
                            }
                            isLoaded = true;
                            hasChanges = false;
                            throw th;
                        }
                    } catch (FileNotFoundException e4) {
                        Utility.closeQuietly(ois);
                        context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                        if (appSessionInfoMap == null) {
                            appSessionInfoMap = new HashMap();
                        }
                        isLoaded = true;
                        hasChanges = false;
                    } catch (Exception e5) {
                        e = e5;
                        Log.w(AppEventsLogger.TAG, "Got unexpected exception restoring app session info: " + e.toString());
                        Utility.closeQuietly(ois);
                        context.deleteFile(PERSISTED_SESSION_INFO_FILENAME);
                        if (appSessionInfoMap == null) {
                            appSessionInfoMap = new HashMap();
                        }
                        isLoaded = true;
                        hasChanges = false;
                    } catch (Throwable th5) {
                        th = th5;
                        throw th;
                    }
                }
            }
        }

        static void saveAppSessionInformation(Context context) {
            Throwable th;
            Exception e;
            ObjectOutputStream oos = null;
            synchronized (staticLock) {
                try {
                    if (hasChanges) {
                        try {
                            ObjectOutputStream oos2 = new ObjectOutputStream(new BufferedOutputStream(context.openFileOutput(PERSISTED_SESSION_INFO_FILENAME, 0)));
                            try {
                                oos2.writeObject(appSessionInfoMap);
                                hasChanges = false;
                                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "App session info saved");
                                try {
                                    Utility.closeQuietly(oos2);
                                    oos = oos2;
                                } catch (Throwable th2) {
                                    th = th2;
                                    oos = oos2;
                                    throw th;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                oos = oos2;
                                try {
                                    Log.w(AppEventsLogger.TAG, "Got unexpected exception while writing app session info: " + e.toString());
                                    Utility.closeQuietly(oos);
                                } catch (Throwable th3) {
                                    th = th3;
                                    Utility.closeQuietly(oos);
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                oos = oos2;
                                Utility.closeQuietly(oos);
                                throw th;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            Log.w(AppEventsLogger.TAG, "Got unexpected exception while writing app session info: " + e.toString());
                            Utility.closeQuietly(oos);
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    throw th;
                }
            }
        }

        static void onResume(Context context, AccessTokenAppIdPair accessTokenAppId, AppEventsLogger logger, long eventTime, String sourceApplicationInfo) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppId).onResume(logger, eventTime, sourceApplicationInfo);
                onTimeSpentDataUpdate();
            }
        }

        static void onSuspend(Context context, AccessTokenAppIdPair accessTokenAppId, AppEventsLogger logger, long eventTime) {
            synchronized (staticLock) {
                getTimeSpentData(context, accessTokenAppId).onSuspend(logger, eventTime);
                onTimeSpentDataUpdate();
            }
        }

        private static FacebookTimeSpentData getTimeSpentData(Context context, AccessTokenAppIdPair accessTokenAppId) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData result = (FacebookTimeSpentData) appSessionInfoMap.get(accessTokenAppId);
            if (result != null) {
                return result;
            }
            result = new FacebookTimeSpentData();
            appSessionInfoMap.put(accessTokenAppId, result);
            return result;
        }

        private static void onTimeSpentDataUpdate() {
            if (!hasChanges) {
                hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(appSessionInfoFlushRunnable, 30, TimeUnit.SECONDS);
            }
        }
    }

    public static void activateApp(Application application) {
        activateApp(application, null);
    }

    public static void activateApp(Application application, String applicationId) {
        if (FacebookSdk.isInitialized()) {
            AnalyticsUserIDStore.initStore();
            if (applicationId == null) {
                applicationId = FacebookSdk.getApplicationId();
            }
            FacebookSdk.publishInstallAsync(application, applicationId);
            ActivityLifecycleTracker.startTracking(application, applicationId);
            return;
        }
        throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
    }

    @Deprecated
    public static void activateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }

    @Deprecated
    public static void activateApp(Context context, String applicationId) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
        } else if (context == null || applicationId == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            AnalyticsUserIDStore.initStore();
            if (context instanceof Activity) {
                setSourceApplication((Activity) context);
            } else {
                resetSourceApplication();
                Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
            }
            FacebookSdk.publishInstallAsync(context, applicationId);
            final AppEventsLogger logger = new AppEventsLogger(context, applicationId, null);
            final long eventTime = System.currentTimeMillis();
            final String sourceApplicationInfo = getSourceApplication();
            backgroundExecutor.execute(new Runnable() {
                public void run() {
                    logger.logAppSessionResumeEvent(eventTime, sourceApplicationInfo);
                }
            });
        }
    }

    @Deprecated
    public static void deactivateApp(Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else {
            deactivateApp(context, Utility.getMetadataApplicationId(context));
        }
    }

    @Deprecated
    public static void deactivateApp(Context context, String applicationId) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
        } else if (context == null || applicationId == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        } else {
            resetSourceApplication();
            final AppEventsLogger logger = new AppEventsLogger(context, applicationId, null);
            final long eventTime = System.currentTimeMillis();
            backgroundExecutor.execute(new Runnable() {
                public void run() {
                    logger.logAppSessionSuspendEvent(eventTime);
                }
            });
        }
    }

    private void logAppSessionResumeEvent(long eventTime, String sourceApplicationInfo) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, eventTime, sourceApplicationInfo);
    }

    private void logAppSessionSuspendEvent(long eventTime) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, eventTime);
    }

    public static void initializeLib(Context context, String applicationId) {
        final AppEventsLogger logger = new AppEventsLogger(context, applicationId, null);
        backgroundExecutor.execute(new Runnable() {
            public void run() {
                Bundle params = new Bundle();
                try {
                    Class.forName("com.facebook.core.Core");
                    params.putInt("core_lib_included", 1);
                } catch (ClassNotFoundException e) {
                }
                try {
                    Class.forName("com.facebook.login.Login");
                    params.putInt("login_lib_included", 1);
                } catch (ClassNotFoundException e2) {
                }
                try {
                    Class.forName("com.facebook.share.Share");
                    params.putInt("share_lib_included", 1);
                } catch (ClassNotFoundException e3) {
                }
                try {
                    Class.forName("com.facebook.places.Places");
                    params.putInt("places_lib_included", 1);
                } catch (ClassNotFoundException e4) {
                }
                try {
                    Class.forName("com.facebook.messenger.Messenger");
                    params.putInt("messenger_lib_included", 1);
                } catch (ClassNotFoundException e5) {
                }
                try {
                    Class.forName("com.facebook.applinks.AppLinks");
                    params.putInt("applinks_lib_included", 1);
                } catch (ClassNotFoundException e6) {
                }
                try {
                    Class.forName("com.facebook.all.All");
                    params.putInt("all_lib_included", 1);
                } catch (ClassNotFoundException e7) {
                }
                logger.logSdkEvent(AnalyticsEvents.EVENT_SDK_INITIALIZE, null, params);
            }
        });
    }

    public static AppEventsLogger newLogger(Context context) {
        return new AppEventsLogger(context, null, null);
    }

    public static AppEventsLogger newLogger(Context context, AccessToken accessToken) {
        return new AppEventsLogger(context, null, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId, AccessToken accessToken) {
        return new AppEventsLogger(context, applicationId, accessToken);
    }

    public static AppEventsLogger newLogger(Context context, String applicationId) {
        return new AppEventsLogger(context, applicationId, null);
    }

    public static FlushBehavior getFlushBehavior() {
        FlushBehavior flushBehavior;
        synchronized (staticLock) {
            flushBehavior = flushBehavior;
        }
        return flushBehavior;
    }

    public static void setFlushBehavior(FlushBehavior flushBehavior) {
        synchronized (staticLock) {
            flushBehavior = flushBehavior;
        }
    }

    public void logEvent(String eventName) {
        logEvent(eventName, null);
    }

    public void logEvent(String eventName, double valueToSum) {
        logEvent(eventName, valueToSum, null);
    }

    public void logEvent(String eventName, Bundle parameters) {
        logEvent(eventName, null, parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logEvent(String eventName, double valueToSum, Bundle parameters) {
        logEvent(eventName, Double.valueOf(valueToSum), parameters, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        logPurchase(purchaseAmount, currency, null, false);
    }

    public void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        logPurchase(purchaseAmount, currency, parameters, false);
    }

    public void logPurchaseImplicitly(BigDecimal purchaseAmount, Currency currency, Bundle parameters) {
        logPurchase(purchaseAmount, currency, parameters, true);
    }

    private void logPurchase(BigDecimal purchaseAmount, Currency currency, Bundle parameters, boolean isImplicitlyLogged) {
        if (purchaseAmount == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
        } else if (currency == null) {
            notifyDeveloperError("currency cannot be null");
        } else {
            if (parameters == null) {
                parameters = new Bundle();
            }
            parameters.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency.getCurrencyCode());
            logEvent(AppEventsConstants.EVENT_NAME_PURCHASED, Double.valueOf(purchaseAmount.doubleValue()), parameters, isImplicitlyLogged, ActivityLifecycleTracker.getCurrentSessionGuid());
            eagerFlush();
        }
    }

    public void logPushNotificationOpen(Bundle payload) {
        logPushNotificationOpen(payload, null);
    }

    public void logPushNotificationOpen(Bundle payload, String action) {
        String campaignId = null;
        try {
            String payloadString = payload.getString(PUSH_PAYLOAD_KEY);
            if (!Utility.isNullOrEmpty(payloadString)) {
                campaignId = new JSONObject(payloadString).getString(PUSH_PAYLOAD_CAMPAIGN_KEY);
                if (campaignId == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                Bundle parameters = new Bundle();
                parameters.putString(APP_EVENT_PUSH_PARAMETER_CAMPAIGN, campaignId);
                if (action != null) {
                    parameters.putString(APP_EVENT_PUSH_PARAMETER_ACTION, action);
                }
                logEvent(APP_EVENT_NAME_PUSH_OPENED, parameters);
            }
        } catch (JSONException e) {
        }
    }

    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }

    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }

    public boolean isValidForAccessToken(AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }

    public static void setPushNotificationsRegistrationId(String registrationId) {
        synchronized (staticLock) {
            if (!Utility.stringsEqualOrEmpty(pushNotificationsRegistrationId, registrationId)) {
                pushNotificationsRegistrationId = registrationId;
                AppEventsLogger logger = newLogger(FacebookSdk.getApplicationContext());
                logger.logEvent(AppEventsConstants.EVENT_NAME_PUSH_TOKEN_OBTAINED);
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    logger.flush();
                }
            }
        }
    }

    static String getPushNotificationsRegistrationId() {
        String str;
        synchronized (staticLock) {
            str = pushNotificationsRegistrationId;
        }
        return str;
    }

    public static void setUserID(String userID) {
        AnalyticsUserIDStore.setUserID(userID);
    }

    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }

    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }

    public static void updateUserProperties(Bundle parameters, Callback callback) {
        updateUserProperties(parameters, FacebookSdk.getApplicationId(), callback);
    }

    public static void updateUserProperties(final Bundle parameters, final String applicationID, final Callback callback) {
        getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                String userID = AppEventsLogger.getUserID();
                if (userID == null || userID.isEmpty()) {
                    Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "AppEventsLogger userID cannot be null or empty");
                    return;
                }
                Bundle userPropertiesParams = new Bundle();
                userPropertiesParams.putString("user_unique_id", userID);
                userPropertiesParams.putBundle("custom_data", parameters);
                AttributionIdentifiers identifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                if (!(identifiers == null || identifiers.getAndroidAdvertiserId() == null)) {
                    userPropertiesParams.putString("advertiser_id", identifiers.getAndroidAdvertiserId());
                }
                Bundle data = new Bundle();
                try {
                    JSONObject userData = BundleJSONConverter.convertToJSON(userPropertiesParams);
                    JSONArray dataArray = new JSONArray();
                    dataArray.put(userData);
                    data.putString("data", dataArray.toString());
                    GraphRequest request = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", new Object[]{applicationID}), data, HttpMethod.POST, callback);
                    request.setSkipClientToken(true);
                    request.executeAsync();
                } catch (Throwable ex) {
                    throw new FacebookException("Failed to construct request", ex);
                }
            }
        });
    }

    public void logSdkEvent(String eventName, Double valueToSum, Bundle parameters) {
        logEvent(eventName, valueToSum, parameters, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }

    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }

    private AppEventsLogger(Context context, String applicationId, AccessToken accessToken) {
        this(Utility.getActivityName(context), applicationId, accessToken);
    }

    protected AppEventsLogger(String activityName, String applicationId, AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = activityName;
        if (accessToken == null) {
            accessToken = AccessToken.getCurrentAccessToken();
        }
        if (AccessToken.isCurrentAccessTokenActive() && (applicationId == null || applicationId.equals(accessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair(accessToken);
        } else {
            if (applicationId == null) {
                applicationId = Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext());
            }
            this.accessTokenAppId = new AccessTokenAppIdPair(null, applicationId);
        }
        initializeTimersIfNeeded();
    }

    private static void initializeTimersIfNeeded() {
        synchronized (staticLock) {
            if (backgroundExecutor != null) {
                return;
            }
            backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            backgroundExecutor.scheduleAtFixedRate(new C11855(), 0, 86400, TimeUnit.SECONDS);
        }
    }

    private void logEvent(String eventName, Double valueToSum, Bundle parameters, boolean isImplicitlyLogged, @Nullable UUID currentSessionId) {
        try {
            logEvent(FacebookSdk.getApplicationContext(), new AppEvent(this.contextName, eventName, valueToSum, parameters, isImplicitlyLogged, currentSessionId), this.accessTokenAppId);
        } catch (JSONException jsonException) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", jsonException.toString());
        } catch (FacebookException e) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", e.toString());
        }
    }

    private static void logEvent(Context context, AppEvent event, AccessTokenAppIdPair accessTokenAppId) {
        AppEventQueue.add(accessTokenAppId, event);
        if (!event.getIsImplicit() && !isActivateAppEventRequested) {
            if (event.getName() == AppEventsConstants.EVENT_NAME_ACTIVATED_APP) {
                isActivateAppEventRequested = true;
            } else {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
            }
        }
    }

    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }

    private static void notifyDeveloperError(String message) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", message);
    }

    private static void setSourceApplication(Activity activity) {
        ComponentName callingApplication = activity.getCallingActivity();
        if (callingApplication != null) {
            String callingApplicationPackage = callingApplication.getPackageName();
            if (callingApplicationPackage.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            sourceApplication = callingApplicationPackage;
        }
        Intent openIntent = activity.getIntent();
        if (openIntent == null || openIntent.getBooleanExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false)) {
            resetSourceApplication();
            return;
        }
        Bundle appLinkData = AppLinks.getAppLinkData(openIntent);
        if (appLinkData == null) {
            resetSourceApplication();
            return;
        }
        isOpenedByAppLink = true;
        Bundle appLinkReferrerData = appLinkData.getBundle("referer_app_link");
        if (appLinkReferrerData == null) {
            sourceApplication = null;
            return;
        }
        sourceApplication = appLinkReferrerData.getString(C0498e.f503t);
        openIntent.putExtra(SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
    }

    static void setSourceApplication(String applicationPackage, boolean openByAppLink) {
        sourceApplication = applicationPackage;
        isOpenedByAppLink = openByAppLink;
    }

    static String getSourceApplication() {
        String openType = "Unclassified";
        if (isOpenedByAppLink) {
            openType = "Applink";
        }
        if (sourceApplication != null) {
            return openType + "(" + sourceApplication + ")";
        }
        return openType;
    }

    static void resetSourceApplication() {
        sourceApplication = null;
        isOpenedByAppLink = false;
    }

    static Executor getAnalyticsExecutor() {
        if (backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return backgroundExecutor;
    }

    public static String getAnonymousAppDeviceGUID(Context context) {
        if (anonymousAppDeviceGUID == null) {
            synchronized (staticLock) {
                if (anonymousAppDeviceGUID == null) {
                    anonymousAppDeviceGUID = context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).getString("anonymousAppDeviceGUID", null);
                    if (anonymousAppDeviceGUID == null) {
                        anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences(APP_EVENT_PREFERENCES, 0).edit().putString("anonymousAppDeviceGUID", anonymousAppDeviceGUID).apply();
                    }
                }
            }
        }
        return anonymousAppDeviceGUID;
    }
}
