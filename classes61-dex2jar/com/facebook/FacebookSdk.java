// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import com.facebook.internal.BoltsMeasurementEventListener;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import android.app.Application;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import com.facebook.internal.Utility;
import org.json.JSONException;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.AppEventsLoggerUtility;
import com.facebook.internal.AttributionIdentifiers;
import android.content.pm.ApplicationInfo;
import java.util.Locale;
import java.util.Collections;
import java.util.Set;
import android.util.Log;
import android.os.AsyncTask;
import android.content.pm.Signature;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Base64;
import java.security.MessageDigest;
import com.facebook.internal.Validate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.LinkedBlockingQueue;
import com.facebook.internal.ServerProtocol;
import java.util.Collection;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.io.File;
import com.facebook.internal.LockOnGetVariable;
import android.content.Context;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;

public final class FacebookSdk
{
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    public static final String APPLICATION_NAME_PROPERTY = "com.facebook.sdk.ApplicationName";
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    public static final String AUTO_LOG_APP_EVENTS_ENABLED_PROPERTY = "com.facebook.sdk.AutoLogAppEventsEnabled";
    static final String CALLBACK_OFFSET_CHANGED_AFTER_INIT = "The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method";
    static final String CALLBACK_OFFSET_NEGATIVE = "The callback request code offset can't be negative.";
    public static final String CALLBACK_OFFSET_PROPERTY = "com.facebook.sdk.CallbackOffset";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CALLBACK_REQUEST_CODE_OFFSET = 64206;
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY;
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK;
    private static final int MAX_REQUEST_CODE_RANGE = 100;
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG;
    public static final String WEB_DIALOG_THEME = "com.facebook.sdk.WebDialogTheme";
    private static volatile String appClientToken;
    private static Context applicationContext;
    private static volatile String applicationId;
    private static volatile String applicationName;
    private static volatile Boolean autoLogAppEventsEnabled;
    private static LockOnGetVariable<File> cacheDir;
    private static int callbackRequestCodeOffset;
    private static Executor executor;
    private static volatile String facebookDomain;
    private static String graphApiVersion;
    private static volatile boolean isDebugEnabled;
    private static boolean isLegacyTokenUpgradeSupported;
    private static final HashSet<LoggingBehavior> loggingBehaviors;
    private static AtomicLong onProgressThreshold;
    private static Boolean sdkInitialized;
    
    static {
        TAG = FacebookSdk.class.getCanonicalName();
        loggingBehaviors = new HashSet<LoggingBehavior>(Arrays.asList(LoggingBehavior.DEVELOPER_ERRORS));
        FacebookSdk.facebookDomain = "facebook.com";
        FacebookSdk.onProgressThreshold = new AtomicLong(65536L);
        FacebookSdk.isDebugEnabled = false;
        FacebookSdk.isLegacyTokenUpgradeSupported = false;
        FacebookSdk.callbackRequestCodeOffset = 64206;
        LOCK = new Object();
        FacebookSdk.graphApiVersion = ServerProtocol.getDefaultAPIVersion();
        DEFAULT_WORK_QUEUE = new LinkedBlockingQueue<Runnable>(10);
        DEFAULT_THREAD_FACTORY = new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(0);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
            }
        };
        FacebookSdk.sdkInitialized = false;
    }
    
    public static void addLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.add(loggingBehavior);
            updateGraphDebugBehavior();
        }
    }
    
    public static void clearLoggingBehaviors() {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.clear();
        }
    }
    
    public static Context getApplicationContext() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationContext;
    }
    
    public static String getApplicationId() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationId;
    }
    
    public static String getApplicationName() {
        Validate.sdkInitialized();
        return FacebookSdk.applicationName;
    }
    
    public static String getApplicationSignature(final Context context) {
        Validate.sdkInitialized();
        if (context != null) {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                final String packageName = context.getPackageName();
                PackageInfo packageInfo;
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, 64);
                    final Signature[] signatures = packageInfo.signatures;
                    if (signatures != null && signatures.length != 0) {
                        final String s = "SHA-1";
                        final MessageDigest messageDigest = MessageDigest.getInstance(s);
                        final MessageDigest messageDigest3;
                        final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                        final PackageInfo packageInfo2 = packageInfo;
                        final Signature[] array = packageInfo2.signatures;
                        final int n = 0;
                        final Signature signature = array[n];
                        final byte[] array2 = signature.toByteArray();
                        messageDigest3.update(array2);
                        final MessageDigest messageDigest4 = messageDigest2;
                        final byte[] array3 = messageDigest4.digest();
                        final int n2 = 9;
                        return Base64.encodeToString(array3, n2);
                    }
                    return null;
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return null;
                }
                try {
                    final String s = "SHA-1";
                    final MessageDigest messageDigest = MessageDigest.getInstance(s);
                    final MessageDigest messageDigest3;
                    final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                    final PackageInfo packageInfo2 = packageInfo;
                    final Signature[] array = packageInfo2.signatures;
                    final int n = 0;
                    final Signature signature = array[n];
                    final byte[] array2 = signature.toByteArray();
                    messageDigest3.update(array2);
                    final MessageDigest messageDigest4 = messageDigest2;
                    final byte[] array3 = messageDigest4.digest();
                    final int n2 = 9;
                    return Base64.encodeToString(array3, n2);
                }
                catch (NoSuchAlgorithmException ex2) {
                    return null;
                }
            }
        }
        return null;
    }
    
    public static boolean getAutoLogAppEventsEnabled() {
        Validate.sdkInitialized();
        return FacebookSdk.autoLogAppEventsEnabled;
    }
    
    public static File getCacheDir() {
        Validate.sdkInitialized();
        return FacebookSdk.cacheDir.getValue();
    }
    
    public static int getCallbackRequestCodeOffset() {
        Validate.sdkInitialized();
        return FacebookSdk.callbackRequestCodeOffset;
    }
    
    public static String getClientToken() {
        Validate.sdkInitialized();
        return FacebookSdk.appClientToken;
    }
    
    public static Executor getExecutor() {
        synchronized (FacebookSdk.LOCK) {
            if (FacebookSdk.executor == null) {
                FacebookSdk.executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
            return FacebookSdk.executor;
        }
    }
    
    public static String getFacebookDomain() {
        return FacebookSdk.facebookDomain;
    }
    
    public static String getGraphApiVersion() {
        Log.d(FacebookSdk.TAG, String.format("getGraphApiVersion: %s", FacebookSdk.graphApiVersion));
        return FacebookSdk.graphApiVersion;
    }
    
    public static boolean getLimitEventAndDataUsage(final Context context) {
        Validate.sdkInitialized();
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }
    
    public static Set<LoggingBehavior> getLoggingBehaviors() {
        synchronized (FacebookSdk.loggingBehaviors) {
            return Collections.unmodifiableSet((Set<? extends LoggingBehavior>)new HashSet<LoggingBehavior>(FacebookSdk.loggingBehaviors));
        }
    }
    
    public static long getOnProgressThreshold() {
        Validate.sdkInitialized();
        return FacebookSdk.onProgressThreshold.get();
    }
    
    public static String getSdkVersion() {
        return "4.33.0";
    }
    
    public static boolean isDebugEnabled() {
        return FacebookSdk.isDebugEnabled;
    }
    
    public static boolean isFacebookRequestCode(final int n) {
        return n >= FacebookSdk.callbackRequestCodeOffset && n < FacebookSdk.callbackRequestCodeOffset + 100;
    }
    
    public static boolean isInitialized() {
        synchronized (FacebookSdk.class) {
            return FacebookSdk.sdkInitialized;
        }
    }
    
    public static boolean isLegacyTokenUpgradeSupported() {
        return FacebookSdk.isLegacyTokenUpgradeSupported;
    }
    
    public static boolean isLoggingBehaviorEnabled(final LoggingBehavior loggingBehavior) {
        while (true) {
            synchronized (FacebookSdk.loggingBehaviors) {
                if (isDebugEnabled() && FacebookSdk.loggingBehaviors.contains(loggingBehavior)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    static void loadDefaultsFromMetadata(final Context context) {
        if (context != null) {
            while (true) {
                while (true) {
                    Object value = null;
                    Label_0173: {
                        String applicationId = null;
                        Label_0166: {
                            try {
                                final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                                if (applicationInfo == null || applicationInfo.metaData == null) {
                                    return;
                                }
                                if (FacebookSdk.applicationId == null) {
                                    value = applicationInfo.metaData.get("com.facebook.sdk.ApplicationId");
                                    if (!(value instanceof String)) {
                                        break Label_0173;
                                    }
                                    applicationId = (String)value;
                                    if (!applicationId.toLowerCase(Locale.ROOT).startsWith("fb")) {
                                        break Label_0166;
                                    }
                                    FacebookSdk.applicationId = applicationId.substring(2);
                                }
                                if (FacebookSdk.applicationName == null) {
                                    FacebookSdk.applicationName = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationName");
                                }
                                if (FacebookSdk.appClientToken == null) {
                                    FacebookSdk.appClientToken = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                                }
                                if (FacebookSdk.callbackRequestCodeOffset == 64206) {
                                    FacebookSdk.callbackRequestCodeOffset = applicationInfo.metaData.getInt("com.facebook.sdk.CallbackOffset", 64206);
                                }
                                if (FacebookSdk.autoLogAppEventsEnabled == null) {
                                    FacebookSdk.autoLogAppEventsEnabled = applicationInfo.metaData.getBoolean("com.facebook.sdk.AutoLogAppEventsEnabled", true);
                                }
                                return;
                            }
                            catch (PackageManager$NameNotFoundException ex) {
                                return;
                            }
                        }
                        FacebookSdk.applicationId = applicationId;
                        continue;
                    }
                    if (value instanceof Integer) {
                        break;
                    }
                    continue;
                }
            }
            throw new FacebookException("App Ids cannot be directly placed in the manifest.They must be prefixed by 'fb' or be placed in the string resource file.");
        }
    }
    
    static void publishInstallAndWaitForResponse(final Context context, final String s) {
        if (context != null) {
            if (s != null) {
                final AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
                final SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
                final String string = s + "ping";
                final long long1 = sharedPreferences.getLong(string, 0L);
                try {
                    final GraphRequest postRequest = GraphRequest.newPostRequest(null, String.format("%s/activities", s), AppEventsLoggerUtility.getJSONObjectForGraphAPICall(AppEventsLoggerUtility.GraphAPIActivityType.MOBILE_INSTALL_EVENT, attributionIdentifiers, AppEventsLogger.getAnonymousAppDeviceGUID(context), getLimitEventAndDataUsage(context), context), null);
                    if (long1 == 0L) {
                        postRequest.executeAndWait();
                        final SharedPreferences$Editor edit = sharedPreferences.edit();
                        edit.putLong(string, System.currentTimeMillis());
                        edit.apply();
                    }
                    return;
                }
                catch (JSONException ex) {
                    throw new FacebookException("An error occurred while publishing install.", (Throwable)ex);
                }
            }
        }
        try {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        catch (Exception ex2) {
            Utility.logd("Facebook-publish", ex2);
        }
    }
    
    public static void publishInstallAsync(Context applicationContext, final String s) {
        applicationContext = applicationContext.getApplicationContext();
        getExecutor().execute(new Runnable() {
            @Override
            public void run() {
                FacebookSdk.publishInstallAndWaitForResponse(applicationContext, s);
            }
        });
    }
    
    public static void removeLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (FacebookSdk.loggingBehaviors) {
            FacebookSdk.loggingBehaviors.remove(loggingBehavior);
        }
    }
    
    @Deprecated
    public static void sdkInitialize(final Context context) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, null);
        }
    }
    
    @Deprecated
    public static void sdkInitialize(final Context context, final int n) {
        synchronized (FacebookSdk.class) {
            sdkInitialize(context, n, null);
        }
    }
    
    @Deprecated
    public static void sdkInitialize(final Context context, final int callbackRequestCodeOffset, final InitializeCallback initializeCallback) {
        synchronized (FacebookSdk.class) {
            if (FacebookSdk.sdkInitialized && callbackRequestCodeOffset != FacebookSdk.callbackRequestCodeOffset) {
                throw new FacebookException("The callback request code offset can't be updated once the SDK is initialized. Call FacebookSdk.setCallbackRequestCodeOffset inside your Application.onCreate method");
            }
        }
        if (callbackRequestCodeOffset < 0) {
            throw new FacebookException("The callback request code offset can't be negative.");
        }
        FacebookSdk.callbackRequestCodeOffset = callbackRequestCodeOffset;
        final Context context2;
        sdkInitialize(context2, initializeCallback);
    }
    // monitorexit(FacebookSdk.class)
    
    @Deprecated
    public static void sdkInitialize(final Context context, final InitializeCallback initializeCallback) {
        while (true) {
            synchronized (FacebookSdk.class) {
                if (FacebookSdk.sdkInitialized) {
                    if (initializeCallback != null) {
                        initializeCallback.onInitialized();
                    }
                    return;
                }
                Validate.notNull(context, "applicationContext");
                Validate.hasFacebookActivity(context, false);
                Validate.hasInternetPermissions(context, false);
                loadDefaultsFromMetadata(FacebookSdk.applicationContext = context.getApplicationContext());
                if (Utility.isNullOrEmpty(FacebookSdk.applicationId)) {
                    throw new FacebookException("A valid Facebook app id must be set in the AndroidManifest.xml or set by calling FacebookSdk.setApplicationId before initializing the sdk.");
                }
            }
            if (FacebookSdk.applicationContext instanceof Application && FacebookSdk.autoLogAppEventsEnabled) {
                ActivityLifecycleTracker.startTracking((Application)FacebookSdk.applicationContext, FacebookSdk.applicationId);
            }
            FacebookSdk.sdkInitialized = true;
            FetchedAppSettingsManager.loadAppSettingsAsync();
            NativeProtocol.updateAllAvailableProtocolVersionsAsync();
            BoltsMeasurementEventListener.getInstance(FacebookSdk.applicationContext);
            FacebookSdk.cacheDir = new LockOnGetVariable<File>(new Callable<File>() {
                @Override
                public File call() throws Exception {
                    return FacebookSdk.applicationContext.getCacheDir();
                }
            });
            final Context context2;
            getExecutor().execute(new FutureTask<Object>(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    AccessTokenManager.getInstance().loadCurrentAccessToken();
                    ProfileManager.getInstance().loadCurrentProfile();
                    if (AccessToken.isCurrentAccessTokenActive() && Profile.getCurrentProfile() == null) {
                        Profile.fetchProfileForCurrentAccessToken();
                    }
                    if (initializeCallback != null) {
                        initializeCallback.onInitialized();
                    }
                    AppEventsLogger.initializeLib(FacebookSdk.applicationContext, FacebookSdk.applicationId);
                    AppEventsLogger.newLogger(context2.getApplicationContext()).flush();
                    return null;
                }
            }));
        }
    }
    
    public static void setApplicationId(final String applicationId) {
        FacebookSdk.applicationId = applicationId;
    }
    
    public static void setApplicationName(final String applicationName) {
        FacebookSdk.applicationName = applicationName;
    }
    
    public static void setAutoLogAppEventsEnabled(final boolean b) {
        FacebookSdk.autoLogAppEventsEnabled = b;
    }
    
    public static void setCacheDir(final File file) {
        FacebookSdk.cacheDir = new LockOnGetVariable<File>(file);
    }
    
    public static void setClientToken(final String appClientToken) {
        FacebookSdk.appClientToken = appClientToken;
    }
    
    public static void setExecutor(final Executor executor) {
        Validate.notNull(executor, "executor");
        synchronized (FacebookSdk.LOCK) {
            FacebookSdk.executor = executor;
        }
    }
    
    public static void setFacebookDomain(final String facebookDomain) {
        Log.w(FacebookSdk.TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        FacebookSdk.facebookDomain = facebookDomain;
    }
    
    public static void setGraphApiVersion(final String graphApiVersion) {
        Log.w(FacebookSdk.TAG, "WARNING: Calling setGraphApiVersion from non-DEBUG code.");
        if (!Utility.isNullOrEmpty(graphApiVersion) && !FacebookSdk.graphApiVersion.equals(graphApiVersion)) {
            FacebookSdk.graphApiVersion = graphApiVersion;
        }
    }
    
    public static void setIsDebugEnabled(final boolean isDebugEnabled) {
        FacebookSdk.isDebugEnabled = isDebugEnabled;
    }
    
    public static void setLegacyTokenUpgradeSupported(final boolean isLegacyTokenUpgradeSupported) {
        FacebookSdk.isLegacyTokenUpgradeSupported = isLegacyTokenUpgradeSupported;
    }
    
    public static void setLimitEventAndDataUsage(final Context context, final boolean b) {
        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", b).apply();
    }
    
    public static void setOnProgressThreshold(final long n) {
        FacebookSdk.onProgressThreshold.set(n);
    }
    
    private static void updateGraphDebugBehavior() {
        if (FacebookSdk.loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_INFO) && !FacebookSdk.loggingBehaviors.contains(LoggingBehavior.GRAPH_API_DEBUG_WARNING)) {
            FacebookSdk.loggingBehaviors.add(LoggingBehavior.GRAPH_API_DEBUG_WARNING);
        }
    }
    
    public interface InitializeCallback
    {
        void onInitialized();
    }
}
