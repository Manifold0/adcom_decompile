// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents;

import java.util.Map;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import org.json.JSONObject;
import com.facebook.HttpMethod;
import java.util.Locale;
import org.json.JSONArray;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.GraphRequest;
import android.content.Intent;
import android.content.ComponentName;
import bolts.AppLinks;
import java.util.Currency;
import java.math.BigDecimal;
import org.json.JSONException;
import android.support.annotation.Nullable;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.facebook.internal.FetchedAppSettingsManager;
import java.util.HashSet;
import android.os.Bundle;
import java.util.UUID;
import java.util.concurrent.Executor;
import android.app.Activity;
import android.util.Log;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.FacebookException;
import android.app.Application;
import com.facebook.FacebookSdk;
import com.facebook.internal.Validate;
import com.facebook.internal.Utility;
import com.facebook.AccessToken;
import android.content.Context;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class AppEventsLogger
{
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
    private static final String TAG;
    private static String anonymousAppDeviceGUID;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static FlushBehavior flushBehavior;
    private static boolean isActivateAppEventRequested;
    private static boolean isOpenedByAppLink;
    private static String pushNotificationsRegistrationId;
    private static String sourceApplication;
    private static Object staticLock;
    private final AccessTokenAppIdPair accessTokenAppId;
    private final String contextName;
    
    static {
        TAG = AppEventsLogger.class.getCanonicalName();
        AppEventsLogger.flushBehavior = FlushBehavior.AUTO;
        AppEventsLogger.staticLock = new Object();
    }
    
    private AppEventsLogger(final Context context, final String s, final AccessToken accessToken) {
        this(Utility.getActivityName(context), s, accessToken);
    }
    
    protected AppEventsLogger(String metadataApplicationId, final String s, final AccessToken accessToken) {
        Validate.sdkInitialized();
        this.contextName = metadataApplicationId;
        AccessToken currentAccessToken = accessToken;
        if (accessToken == null) {
            currentAccessToken = AccessToken.getCurrentAccessToken();
        }
        if (AccessToken.isCurrentAccessTokenActive() && (s == null || s.equals(currentAccessToken.getApplicationId()))) {
            this.accessTokenAppId = new AccessTokenAppIdPair(currentAccessToken);
        }
        else {
            if ((metadataApplicationId = s) == null) {
                metadataApplicationId = Utility.getMetadataApplicationId(FacebookSdk.getApplicationContext());
            }
            this.accessTokenAppId = new AccessTokenAppIdPair(null, metadataApplicationId);
        }
        initializeTimersIfNeeded();
    }
    
    public static void activateApp(final Application application) {
        activateApp(application, null);
    }
    
    public static void activateApp(final Application application, final String s) {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookException("The Facebook sdk must be initialized before calling activateApp");
        }
        AnalyticsUserIDStore.initStore();
        String applicationId;
        if ((applicationId = s) == null) {
            applicationId = FacebookSdk.getApplicationId();
        }
        FacebookSdk.publishInstallAsync((Context)application, applicationId);
        ActivityLifecycleTracker.startTracking(application, applicationId);
    }
    
    @Deprecated
    public static void activateApp(final Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(AppEventsLogger.TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        FacebookSdk.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    @Deprecated
    public static void activateApp(final Context context, String sourceApplication) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(AppEventsLogger.TAG, "activateApp events are being logged automatically. There's no need to call activateApp explicitly, this is safe to remove.");
            return;
        }
        if (context == null || sourceApplication == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        AnalyticsUserIDStore.initStore();
        if (context instanceof Activity) {
            setSourceApplication((Activity)context);
        }
        else {
            resetSourceApplication();
            Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
        }
        FacebookSdk.publishInstallAsync(context, sourceApplication);
        final AppEventsLogger appEventsLogger = new AppEventsLogger(context, sourceApplication, null);
        final long currentTimeMillis = System.currentTimeMillis();
        sourceApplication = getSourceApplication();
        AppEventsLogger.backgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AppEventsLogger.this.logAppSessionResumeEvent(currentTimeMillis, sourceApplication);
            }
        });
    }
    
    public static void clearUserID() {
        AnalyticsUserIDStore.setUserID(null);
    }
    
    @Deprecated
    public static void deactivateApp(final Context context) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(AppEventsLogger.TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
            return;
        }
        deactivateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    @Deprecated
    public static void deactivateApp(final Context context, final String s) {
        if (ActivityLifecycleTracker.isTracking()) {
            Log.w(AppEventsLogger.TAG, "deactivateApp events are being logged automatically. There's no need to call deactivateApp, this is safe to remove.");
            return;
        }
        if (context == null || s == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        resetSourceApplication();
        AppEventsLogger.backgroundExecutor.execute(new Runnable() {
            final /* synthetic */ long val$eventTime = System.currentTimeMillis();
            final /* synthetic */ AppEventsLogger val$logger = new AppEventsLogger(context, s, null);
            
            @Override
            public void run() {
                AppEventsLogger.this.logAppSessionSuspendEvent(this.val$eventTime);
            }
        });
    }
    
    static void eagerFlush() {
        if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventQueue.flush(FlushReason.EAGER_FLUSHING_EVENT);
        }
    }
    
    static Executor getAnalyticsExecutor() {
        if (AppEventsLogger.backgroundExecutor == null) {
            initializeTimersIfNeeded();
        }
        return AppEventsLogger.backgroundExecutor;
    }
    
    public static String getAnonymousAppDeviceGUID(final Context context) {
        Label_0101: {
            if (AppEventsLogger.anonymousAppDeviceGUID != null) {
                break Label_0101;
            }
            synchronized (AppEventsLogger.staticLock) {
                if (AppEventsLogger.anonymousAppDeviceGUID == null) {
                    AppEventsLogger.anonymousAppDeviceGUID = context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getString("anonymousAppDeviceGUID", (String)null);
                    if (AppEventsLogger.anonymousAppDeviceGUID == null) {
                        AppEventsLogger.anonymousAppDeviceGUID = "XZ" + UUID.randomUUID().toString();
                        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putString("anonymousAppDeviceGUID", AppEventsLogger.anonymousAppDeviceGUID).apply();
                    }
                }
                return AppEventsLogger.anonymousAppDeviceGUID;
            }
        }
    }
    
    public static FlushBehavior getFlushBehavior() {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.flushBehavior;
        }
    }
    
    static String getPushNotificationsRegistrationId() {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.pushNotificationsRegistrationId;
        }
    }
    
    static String getSourceApplication() {
        String s = "Unclassified";
        if (AppEventsLogger.isOpenedByAppLink) {
            s = "Applink";
        }
        String string = s;
        if (AppEventsLogger.sourceApplication != null) {
            string = s + "(" + AppEventsLogger.sourceApplication + ")";
        }
        return string;
    }
    
    public static String getUserID() {
        return AnalyticsUserIDStore.getUserID();
    }
    
    public static void initializeLib(final Context context, final String s) {
        AppEventsLogger.backgroundExecutor.execute(new Runnable() {
            final /* synthetic */ AppEventsLogger val$logger = new AppEventsLogger(context, s, null);
            
            @Override
            public void run() {
                final Bundle bundle = new Bundle();
                while (true) {
                    try {
                        Class.forName("com.facebook.core.Core");
                        bundle.putInt("core_lib_included", 1);
                        try {
                            Class.forName("com.facebook.login.Login");
                            bundle.putInt("login_lib_included", 1);
                            try {
                                Class.forName("com.facebook.share.Share");
                                bundle.putInt("share_lib_included", 1);
                                try {
                                    Class.forName("com.facebook.places.Places");
                                    bundle.putInt("places_lib_included", 1);
                                    try {
                                        Class.forName("com.facebook.messenger.Messenger");
                                        bundle.putInt("messenger_lib_included", 1);
                                        try {
                                            Class.forName("com.facebook.applinks.AppLinks");
                                            bundle.putInt("applinks_lib_included", 1);
                                            try {
                                                Class.forName("com.facebook.all.All");
                                                bundle.putInt("all_lib_included", 1);
                                                this.val$logger.logSdkEvent("fb_sdk_initialize", null, bundle);
                                            }
                                            catch (ClassNotFoundException ex) {}
                                        }
                                        catch (ClassNotFoundException ex2) {}
                                    }
                                    catch (ClassNotFoundException ex3) {}
                                }
                                catch (ClassNotFoundException ex4) {}
                            }
                            catch (ClassNotFoundException ex5) {}
                        }
                        catch (ClassNotFoundException ex6) {}
                    }
                    catch (ClassNotFoundException ex7) {
                        continue;
                    }
                    break;
                }
            }
        });
    }
    
    private static void initializeTimersIfNeeded() {
        Object staticLock = AppEventsLogger.staticLock;
        synchronized (staticLock) {
            if (AppEventsLogger.backgroundExecutor != null) {
                return;
            }
            AppEventsLogger.backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            // monitorexit(staticLock)
            staticLock = new Runnable() {
                @Override
                public void run() {
                    final HashSet<String> set = new HashSet<String>();
                    final Iterator<AccessTokenAppIdPair> iterator = AppEventQueue.getKeySet().iterator();
                    while (iterator.hasNext()) {
                        set.add(iterator.next().getApplicationId());
                    }
                    final Iterator<Object> iterator2 = set.iterator();
                    while (iterator2.hasNext()) {
                        FetchedAppSettingsManager.queryAppSettings(iterator2.next(), true);
                    }
                }
            };
            AppEventsLogger.backgroundExecutor.scheduleAtFixedRate((Runnable)staticLock, 0L, 86400L, TimeUnit.SECONDS);
        }
    }
    
    private void logAppSessionResumeEvent(final long n, final String s) {
        PersistedAppSessionInfo.onResume(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, n, s);
    }
    
    private void logAppSessionSuspendEvent(final long n) {
        PersistedAppSessionInfo.onSuspend(FacebookSdk.getApplicationContext(), this.accessTokenAppId, this, n);
    }
    
    private static void logEvent(final Context context, final AppEvent appEvent, final AccessTokenAppIdPair accessTokenAppIdPair) {
        AppEventQueue.add(accessTokenAppIdPair, appEvent);
        if (!appEvent.getIsImplicit() && !AppEventsLogger.isActivateAppEventRequested) {
            if (appEvent.getName() != "fb_mobile_activate_app") {
                Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Warning: Please call AppEventsLogger.activateApp(...)from the long-lived activity's onResume() methodbefore logging other app events.");
                return;
            }
            AppEventsLogger.isActivateAppEventRequested = true;
        }
    }
    
    private void logEvent(final String s, final Double n, final Bundle bundle, final boolean b, @Nullable final UUID uuid) {
        try {
            logEvent(FacebookSdk.getApplicationContext(), new AppEvent(this.contextName, s, n, bundle, b, uuid), this.accessTokenAppId);
        }
        catch (JSONException ex) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "JSON encoding for app event failed: '%s'", ex.toString());
        }
        catch (FacebookException ex2) {
            Logger.log(LoggingBehavior.APP_EVENTS, "AppEvents", "Invalid app event: %s", ex2.toString());
        }
    }
    
    private void logPurchase(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle, final boolean b) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
            return;
        }
        if (currency == null) {
            notifyDeveloperError("currency cannot be null");
            return;
        }
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("fb_currency", currency.getCurrencyCode());
        this.logEvent("fb_mobile_purchase", bigDecimal.doubleValue(), bundle2, b, ActivityLifecycleTracker.getCurrentSessionGuid());
        eagerFlush();
    }
    
    public static AppEventsLogger newLogger(final Context context) {
        return new AppEventsLogger(context, null, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final AccessToken accessToken) {
        return new AppEventsLogger(context, null, accessToken);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s) {
        return new AppEventsLogger(context, s, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s, final AccessToken accessToken) {
        return new AppEventsLogger(context, s, accessToken);
    }
    
    private static void notifyDeveloperError(final String s) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", s);
    }
    
    public static void onContextStop() {
        AppEventQueue.persistToDisk();
    }
    
    static void resetSourceApplication() {
        AppEventsLogger.sourceApplication = null;
        AppEventsLogger.isOpenedByAppLink = false;
    }
    
    public static void setFlushBehavior(final FlushBehavior flushBehavior) {
        synchronized (AppEventsLogger.staticLock) {
            AppEventsLogger.flushBehavior = flushBehavior;
        }
    }
    
    public static void setPushNotificationsRegistrationId(final String pushNotificationsRegistrationId) {
        synchronized (AppEventsLogger.staticLock) {
            if (!Utility.stringsEqualOrEmpty(AppEventsLogger.pushNotificationsRegistrationId, pushNotificationsRegistrationId)) {
                AppEventsLogger.pushNotificationsRegistrationId = pushNotificationsRegistrationId;
                final AppEventsLogger logger = newLogger(FacebookSdk.getApplicationContext());
                logger.logEvent("fb_mobile_obtain_push_token");
                if (getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
                    logger.flush();
                }
            }
        }
    }
    
    private static void setSourceApplication(final Activity activity) {
        final ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            final String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            AppEventsLogger.sourceApplication = packageName;
        }
        final Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
            resetSourceApplication();
            return;
        }
        final Bundle appLinkData = AppLinks.getAppLinkData(intent);
        if (appLinkData == null) {
            resetSourceApplication();
            return;
        }
        AppEventsLogger.isOpenedByAppLink = true;
        final Bundle bundle = appLinkData.getBundle("referer_app_link");
        if (bundle == null) {
            AppEventsLogger.sourceApplication = null;
            return;
        }
        AppEventsLogger.sourceApplication = bundle.getString("package");
        intent.putExtra("_fbSourceApplicationHasBeenSet", true);
    }
    
    static void setSourceApplication(final String sourceApplication, final boolean isOpenedByAppLink) {
        AppEventsLogger.sourceApplication = sourceApplication;
        AppEventsLogger.isOpenedByAppLink = isOpenedByAppLink;
    }
    
    public static void setUserID(final String userID) {
        AnalyticsUserIDStore.setUserID(userID);
    }
    
    public static void updateUserProperties(final Bundle bundle, final GraphRequest.Callback callback) {
        updateUserProperties(bundle, FacebookSdk.getApplicationId(), callback);
    }
    
    public static void updateUserProperties(final Bundle bundle, final String s, final GraphRequest.Callback callback) {
        getAnalyticsExecutor().execute(new Runnable() {
            @Override
            public void run() {
                final String userID = AppEventsLogger.getUserID();
                if (userID == null || userID.isEmpty()) {
                    Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "AppEventsLogger userID cannot be null or empty");
                    return;
                }
                final Bundle bundle = new Bundle();
                bundle.putString("user_unique_id", userID);
                bundle.putBundle("custom_data", bundle);
                final AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
                    bundle.putString("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
                }
                final Bundle bundle2 = new Bundle();
                try {
                    final JSONObject convertToJSON = BundleJSONConverter.convertToJSON(bundle);
                    final JSONArray jsonArray = new JSONArray();
                    jsonArray.put((Object)convertToJSON);
                    bundle2.putString("data", jsonArray.toString());
                    final GraphRequest graphRequest = new GraphRequest(AccessToken.getCurrentAccessToken(), String.format(Locale.US, "%s/user_properties", s), bundle2, HttpMethod.POST, callback);
                    graphRequest.setSkipClientToken(true);
                    graphRequest.executeAsync();
                }
                catch (JSONException ex) {
                    throw new FacebookException("Failed to construct request", (Throwable)ex);
                }
            }
        });
    }
    
    public void flush() {
        AppEventQueue.flush(FlushReason.EXPLICIT);
    }
    
    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }
    
    public boolean isValidForAccessToken(final AccessToken accessToken) {
        return this.accessTokenAppId.equals(new AccessTokenAppIdPair(accessToken));
    }
    
    public void logEvent(final String s) {
        this.logEvent(s, null);
    }
    
    public void logEvent(final String s, final double n) {
        this.logEvent(s, n, null);
    }
    
    public void logEvent(final String s, final double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }
    
    public void logEvent(final String s, final Bundle bundle) {
        this.logEvent(s, null, bundle, false, ActivityLifecycleTracker.getCurrentSessionGuid());
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(AppEventsLogger.TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        this.logPurchase(bigDecimal, currency, null, false);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle) {
        if (AutomaticAnalyticsLogger.isImplicitPurchaseLoggingEnabled()) {
            Log.w(AppEventsLogger.TAG, "You are logging purchase events while auto-logging of in-app purchase is enabled in the SDK. Make sure you don't log duplicate events");
        }
        this.logPurchase(bigDecimal, currency, bundle, false);
    }
    
    public void logPurchaseImplicitly(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle) {
        this.logPurchase(bigDecimal, currency, bundle, true);
    }
    
    public void logPushNotificationOpen(final Bundle bundle) {
        this.logPushNotificationOpen(bundle, null);
    }
    
    public void logPushNotificationOpen(final Bundle bundle, final String s) {
        Bundle bundle2 = null;
        while (true) {
            try {
                final String string = bundle.getString("fb_push_payload");
                if (Utility.isNullOrEmpty(string)) {
                    return;
                }
                final String string2 = new JSONObject(string).getString("campaign");
                if (string2 == null) {
                    Logger.log(LoggingBehavior.DEVELOPER_ERRORS, AppEventsLogger.TAG, "Malformed payload specified for logging a push notification open.");
                    return;
                }
                bundle2 = new Bundle();
                bundle2.putString("fb_push_campaign", string2);
                if (s != null) {
                    bundle2.putString("fb_push_action", s);
                }
                this.logEvent("fb_mobile_push_opened", bundle2);
            }
            catch (JSONException ex) {
                final String string2 = (String)bundle2;
                continue;
            }
            break;
        }
    }
    
    public void logSdkEvent(final String s, final Double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, true, ActivityLifecycleTracker.getCurrentSessionGuid());
    }
    
    public enum FlushBehavior
    {
        AUTO, 
        EXPLICIT_ONLY;
    }
    
    static class PersistedAppSessionInfo
    {
        private static final String PERSISTED_SESSION_INFO_FILENAME = "AppEventsLogger.persistedsessioninfo";
        private static final Runnable appSessionInfoFlushRunnable;
        private static Map<AccessTokenAppIdPair, FacebookTimeSpentData> appSessionInfoMap;
        private static boolean hasChanges;
        private static boolean isLoaded;
        private static final Object staticLock;
        
        static {
            staticLock = new Object();
            PersistedAppSessionInfo.hasChanges = false;
            PersistedAppSessionInfo.isLoaded = false;
            appSessionInfoFlushRunnable = new Runnable() {
                @Override
                public void run() {
                    PersistedAppSessionInfo.saveAppSessionInformation(FacebookSdk.getApplicationContext());
                }
            };
        }
        
        private static FacebookTimeSpentData getTimeSpentData(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair) {
            restoreAppSessionInformation(context);
            FacebookTimeSpentData facebookTimeSpentData;
            if ((facebookTimeSpentData = PersistedAppSessionInfo.appSessionInfoMap.get(accessTokenAppIdPair)) == null) {
                facebookTimeSpentData = new FacebookTimeSpentData();
                PersistedAppSessionInfo.appSessionInfoMap.put(accessTokenAppIdPair, facebookTimeSpentData);
            }
            return facebookTimeSpentData;
        }
        
        static void onResume(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n, final String s) {
            synchronized (PersistedAppSessionInfo.staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onResume(appEventsLogger, n, s);
                onTimeSpentDataUpdate();
            }
        }
        
        static void onSuspend(final Context context, final AccessTokenAppIdPair accessTokenAppIdPair, final AppEventsLogger appEventsLogger, final long n) {
            synchronized (PersistedAppSessionInfo.staticLock) {
                getTimeSpentData(context, accessTokenAppIdPair).onSuspend(appEventsLogger, n);
                onTimeSpentDataUpdate();
            }
        }
        
        private static void onTimeSpentDataUpdate() {
            if (!PersistedAppSessionInfo.hasChanges) {
                PersistedAppSessionInfo.hasChanges = true;
                AppEventsLogger.backgroundExecutor.schedule(PersistedAppSessionInfo.appSessionInfoFlushRunnable, 30L, TimeUnit.SECONDS);
            }
        }
        
        private static void restoreAppSessionInformation(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: astore          5
            //     3: aconst_null    
            //     4: astore_3       
            //     5: aconst_null    
            //     6: astore          4
            //     8: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
            //    11: astore          6
            //    13: aload           6
            //    15: monitorenter   
            //    16: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //    19: istore_1       
            //    20: iload_1        
            //    21: ifne            93
            //    24: new             Ljava/io/ObjectInputStream;
            //    27: dup            
            //    28: aload_0        
            //    29: ldc             "AppEventsLogger.persistedsessioninfo"
            //    31: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
            //    34: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
            //    37: astore_2       
            //    38: aload_2        
            //    39: invokevirtual   java/io/ObjectInputStream.readObject:()Ljava/lang/Object;
            //    42: checkcast       Ljava/util/HashMap;
            //    45: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    48: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
            //    51: ldc             "AppEvents"
            //    53: ldc             "App session info loaded"
            //    55: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
            //    58: aload_2        
            //    59: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    62: aload_0        
            //    63: ldc             "AppEventsLogger.persistedsessioninfo"
            //    65: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //    68: pop            
            //    69: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    72: ifnonnull       85
            //    75: new             Ljava/util/HashMap;
            //    78: dup            
            //    79: invokespecial   java/util/HashMap.<init>:()V
            //    82: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    85: iconst_1       
            //    86: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //    89: iconst_0       
            //    90: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    93: aload           6
            //    95: monitorexit    
            //    96: return         
            //    97: aload_2        
            //    98: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   101: aload_0        
            //   102: ldc             "AppEventsLogger.persistedsessioninfo"
            //   104: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   107: pop            
            //   108: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   111: ifnonnull       124
            //   114: new             Ljava/util/HashMap;
            //   117: dup            
            //   118: invokespecial   java/util/HashMap.<init>:()V
            //   121: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   124: iconst_1       
            //   125: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   128: iconst_0       
            //   129: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   132: goto            93
            //   135: aload           6
            //   137: monitorexit    
            //   138: aload_0        
            //   139: athrow         
            //   140: astore          4
            //   142: aload           5
            //   144: astore_2       
            //   145: aload_2        
            //   146: astore_3       
            //   147: invokestatic    com/facebook/appevents/AppEventsLogger.access$200:()Ljava/lang/String;
            //   150: new             Ljava/lang/StringBuilder;
            //   153: dup            
            //   154: invokespecial   java/lang/StringBuilder.<init>:()V
            //   157: ldc             "Got unexpected exception restoring app session info: "
            //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   162: aload           4
            //   164: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   170: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   173: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //   176: pop            
            //   177: aload_2        
            //   178: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   181: aload_0        
            //   182: ldc             "AppEventsLogger.persistedsessioninfo"
            //   184: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   187: pop            
            //   188: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   191: ifnonnull       204
            //   194: new             Ljava/util/HashMap;
            //   197: dup            
            //   198: invokespecial   java/util/HashMap.<init>:()V
            //   201: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   204: iconst_1       
            //   205: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   208: iconst_0       
            //   209: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   212: goto            93
            //   215: aload_3        
            //   216: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   219: aload_0        
            //   220: ldc             "AppEventsLogger.persistedsessioninfo"
            //   222: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
            //   225: pop            
            //   226: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   229: ifnonnull       242
            //   232: new             Ljava/util/HashMap;
            //   235: dup            
            //   236: invokespecial   java/util/HashMap.<init>:()V
            //   239: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //   242: iconst_1       
            //   243: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.isLoaded:Z
            //   246: iconst_0       
            //   247: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //   250: aload_2        
            //   251: athrow         
            //   252: astore_0       
            //   253: goto            135
            //   256: astore          4
            //   258: aload_2        
            //   259: astore_3       
            //   260: aload           4
            //   262: astore_2       
            //   263: goto            215
            //   266: astore          4
            //   268: goto            145
            //   271: astore_3       
            //   272: goto            97
            //   275: astore_2       
            //   276: aload           4
            //   278: astore_2       
            //   279: goto            97
            //   282: astore_0       
            //   283: goto            135
            //   286: astore_2       
            //   287: goto            215
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                           
            //  -----  -----  -----  -----  -------------------------------
            //  16     20     282    286    Any
            //  24     38     275    282    Ljava/io/FileNotFoundException;
            //  24     38     140    145    Ljava/lang/Exception;
            //  24     38     286    290    Any
            //  38     58     271    275    Ljava/io/FileNotFoundException;
            //  38     58     266    271    Ljava/lang/Exception;
            //  38     58     256    266    Any
            //  58     85     252    256    Any
            //  85     93     252    256    Any
            //  93     96     282    286    Any
            //  97     124    282    286    Any
            //  124    132    282    286    Any
            //  135    138    282    286    Any
            //  147    177    286    290    Any
            //  177    204    282    286    Any
            //  204    212    282    286    Any
            //  215    242    282    286    Any
            //  242    252    282    286    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        static void saveAppSessionInformation(final Context p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: astore_2       
            //     2: aconst_null    
            //     3: astore_3       
            //     4: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.staticLock:Ljava/lang/Object;
            //     7: astore          4
            //     9: aload           4
            //    11: monitorenter   
            //    12: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    15: istore_1       
            //    16: iload_1        
            //    17: ifeq            67
            //    20: new             Ljava/io/ObjectOutputStream;
            //    23: dup            
            //    24: new             Ljava/io/BufferedOutputStream;
            //    27: dup            
            //    28: aload_0        
            //    29: ldc             "AppEventsLogger.persistedsessioninfo"
            //    31: iconst_0       
            //    32: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
            //    35: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    38: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
            //    41: astore_0       
            //    42: aload_0        
            //    43: getstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.appSessionInfoMap:Ljava/util/Map;
            //    46: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
            //    49: iconst_0       
            //    50: putstatic       com/facebook/appevents/AppEventsLogger$PersistedAppSessionInfo.hasChanges:Z
            //    53: getstatic       com/facebook/LoggingBehavior.APP_EVENTS:Lcom/facebook/LoggingBehavior;
            //    56: ldc             "AppEvents"
            //    58: ldc             "App session info saved"
            //    60: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
            //    63: aload_0        
            //    64: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //    67: aload           4
            //    69: monitorexit    
            //    70: return         
            //    71: astore_2       
            //    72: aload_3        
            //    73: astore_0       
            //    74: aload_2        
            //    75: astore_3       
            //    76: aload_0        
            //    77: astore_2       
            //    78: invokestatic    com/facebook/appevents/AppEventsLogger.access$200:()Ljava/lang/String;
            //    81: new             Ljava/lang/StringBuilder;
            //    84: dup            
            //    85: invokespecial   java/lang/StringBuilder.<init>:()V
            //    88: ldc             "Got unexpected exception while writing app session info: "
            //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    93: aload_3        
            //    94: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   100: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   103: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //   106: pop            
            //   107: aload_0        
            //   108: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   111: goto            67
            //   114: aload           4
            //   116: monitorexit    
            //   117: aload_0        
            //   118: athrow         
            //   119: astore_0       
            //   120: aload_2        
            //   121: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
            //   124: aload_0        
            //   125: athrow         
            //   126: astore_0       
            //   127: goto            114
            //   130: astore_3       
            //   131: aload_0        
            //   132: astore_2       
            //   133: aload_3        
            //   134: astore_0       
            //   135: goto            120
            //   138: astore_3       
            //   139: goto            76
            //   142: astore_0       
            //   143: goto            114
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  12     16     142    146    Any
            //  20     42     71     76     Ljava/lang/Exception;
            //  20     42     119    120    Any
            //  42     63     138    142    Ljava/lang/Exception;
            //  42     63     130    138    Any
            //  63     67     126    130    Any
            //  67     70     142    146    Any
            //  78     107    119    120    Any
            //  107    111    142    146    Any
            //  114    117    142    146    Any
            //  120    126    142    146    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
}
