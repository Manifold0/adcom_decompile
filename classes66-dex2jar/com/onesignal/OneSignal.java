// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.database.Cursor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Base64;
import android.content.pm.PackageInfo;
import java.security.MessageDigest;
import android.os.Build;
import android.os.Build$VERSION;
import android.net.Uri;
import android.support.annotation.WorkerThread;
import android.support.annotation.Nullable;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import android.os.SystemClock;
import android.app.Activity;
import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;
import android.os.Bundle;
import android.content.Intent;
import java.util.Iterator;
import android.app.NotificationManager;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.support.annotation.NonNull;
import org.json.JSONException;
import android.app.AlertDialog$Builder;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.util.Log;
import java.util.ArrayList;
import org.json.JSONArray;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;
import android.content.Context;

public class OneSignal
{
    static final long MIN_ON_FOCUS_TIME = 60L;
    private static final long MIN_ON_SESSION_TIME = 30L;
    public static final String VERSION = "031005";
    private static int androidParamsReties;
    static Context appContext;
    static String appId;
    private static JSONObject awl;
    private static boolean awlFired;
    private static OSEmailSubscriptionState currentEmailSubscriptionState;
    private static OSPermissionState currentPermissionState;
    private static OSSubscriptionState currentSubscriptionState;
    static DelayedConsentInitializationParameters delayedInitParams;
    private static int deviceType;
    private static String emailId;
    private static EmailUpdateHandler emailLogoutHandler;
    private static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> emailSubscriptionStateChangesObserver;
    private static EmailUpdateHandler emailUpdateHandler;
    private static boolean foreground;
    private static boolean getTagsCall;
    private static IAPUpdateJob iapUpdateJob;
    private static IdsAvailableHandler idsAvailableHandler;
    static boolean initDone;
    static OSEmailSubscriptionState lastEmailSubscriptionState;
    private static LocationGMS.LocationPoint lastLocationPoint;
    static OSPermissionState lastPermissionState;
    private static String lastRegistrationId;
    static OSSubscriptionState lastSubscriptionState;
    static AtomicLong lastTaskId;
    private static long lastTrackedFocusTime;
    private static boolean locationFired;
    private static LOG_LEVEL logCatLevel;
    static boolean mEnterp;
    private static String mGoogleProjectNumber;
    private static boolean mGoogleProjectNumberIsRemote;
    static Builder mInitBuilder;
    private static PushRegistrator mPushRegistrator;
    private static AdvertisingIdentifierProvider mainAdIdProvider;
    private static OSUtils osUtils;
    private static GetTagsHandler pendingGetTagsHandler;
    static ExecutorService pendingTaskExecutor;
    private static OSObservable<OSPermissionObserver, OSPermissionStateChanges> permissionStateChangesObserver;
    private static HashSet<String> postedOpenedNotifIds;
    private static boolean promptedLocation;
    private static boolean registerForPushFired;
    static boolean requiresUserPrivacyConsent;
    public static String sdkType;
    private static boolean sendAsSession;
    static boolean shareLocation;
    private static int subscribableStatus;
    private static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> subscriptionStateChangesObserver;
    public static ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit;
    private static TrackAmazonPurchase trackAmazonPurchase;
    private static TrackFirebaseAnalytics trackFirebaseAnalytics;
    private static TrackGooglePurchase trackGooglePurchase;
    private static long unSentActiveTime;
    private static Collection<JSONArray> unprocessedOpenedNotifis;
    private static boolean useEmailAuth;
    private static String userId;
    private static LOG_LEVEL visualLogLevel;
    private static boolean waitingToPostStateSync;
    
    static {
        OneSignal.visualLogLevel = LOG_LEVEL.NONE;
        OneSignal.logCatLevel = LOG_LEVEL.WARN;
        OneSignal.userId = null;
        OneSignal.emailId = null;
        OneSignal.taskQueueWaitingForInit = new ConcurrentLinkedQueue<Runnable>();
        OneSignal.lastTaskId = new AtomicLong();
        OneSignal.lastTrackedFocusTime = 1L;
        OneSignal.unSentActiveTime = -1L;
        OneSignal.mainAdIdProvider = new AdvertisingIdProviderGPS();
        OneSignal.sdkType = "native";
        OneSignal.shareLocation = true;
        OneSignal.unprocessedOpenedNotifis = new ArrayList<JSONArray>();
        OneSignal.postedOpenedNotifIds = new HashSet<String>();
        OneSignal.requiresUserPrivacyConsent = false;
        OneSignal.androidParamsReties = 0;
    }
    
    static long GetUnsentActiveTime() {
        if (OneSignal.unSentActiveTime == -1L && OneSignal.appContext != null) {
            OneSignal.unSentActiveTime = OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", 0L);
        }
        Log(LOG_LEVEL.INFO, "GetUnsentActiveTime: " + OneSignal.unSentActiveTime);
        return OneSignal.unSentActiveTime;
    }
    
    static void Log(final LOG_LEVEL log_LEVEL, final String s) {
        Log(log_LEVEL, s, null);
    }
    
    static void Log(final LOG_LEVEL log_LEVEL, String s, final Throwable t) {
        Label_0148: {
            if (log_LEVEL.compareTo(OneSignal.logCatLevel) < 1) {
                if (log_LEVEL != LOG_LEVEL.VERBOSE) {
                    break Label_0148;
                }
                Log.v("OneSignal", s, t);
            }
        Block_7_Outer:
            while (true) {
                if (log_LEVEL.compareTo(OneSignal.visualLogLevel) >= 1 || ActivityLifecycleHandler.curActivity == null) {
                    return;
                }
                try {
                    final String s2 = s += "\n";
                    if (t != null) {
                        s = s2 + t.getMessage();
                        final StringWriter stringWriter = new StringWriter();
                        t.printStackTrace(new PrintWriter(stringWriter));
                        s += stringWriter.toString();
                    }
                    OSUtils.runOnMainUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (ActivityLifecycleHandler.curActivity != null) {
                                new AlertDialog$Builder((Context)ActivityLifecycleHandler.curActivity).setTitle((CharSequence)log_LEVEL.toString()).setMessage((CharSequence)s).show();
                            }
                        }
                    });
                    return;
                    Label_0186: {
                        Log.w("OneSignal", s, t);
                    }
                    // iftrue(Label_0205:, log_LEVEL != LOG_LEVEL.WARN)
                    continue;
                    // iftrue(Label_0186:, log_LEVEL != LOG_LEVEL.INFO)
                    // iftrue(Label_0027:, log_LEVEL != LOG_LEVEL.ERROR && log_LEVEL != LOG_LEVEL.FATAL)
                    // iftrue(Label_0167:, log_LEVEL != LOG_LEVEL.DEBUG)
                    Block_8: {
                        break Block_8;
                    Label_0205:
                        while (true) {
                            Label_0219: {
                                break Label_0219;
                                Log.d("OneSignal", s, t);
                                continue Block_7_Outer;
                            }
                            Log.e("OneSignal", s, t);
                            continue Block_7_Outer;
                            continue;
                        }
                    }
                    Log.i("OneSignal", s, t);
                    continue;
                }
                catch (Throwable t2) {
                    Log.e("OneSignal", "Error showing logging message.", t2);
                }
                break;
            }
        }
    }
    
    private static void SaveAppId(final String s) {
        if (OneSignal.appContext == null) {
            return;
        }
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", s);
    }
    
    private static void SaveUnsentActiveTime(final long unSentActiveTime) {
        OneSignal.unSentActiveTime = unSentActiveTime;
        if (OneSignal.appContext == null) {
            return;
        }
        Log(LOG_LEVEL.INFO, "SaveUnsentActiveTime: " + OneSignal.unSentActiveTime);
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", unSentActiveTime);
    }
    
    public static void addEmailSubscriptionObserver(@NonNull final OSEmailSubscriptionObserver osEmailSubscriptionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
        }
        else {
            getEmailSubscriptionStateChangesObserver().addObserver(osEmailSubscriptionObserver);
            if (getCurrentEmailSubscriptionState(OneSignal.appContext).compare(getLastEmailSubscriptionState(OneSignal.appContext))) {
                OSEmailSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentEmailSubscriptionState(OneSignal.appContext));
            }
        }
    }
    
    private static void addNetType(final JSONObject jsonObject) {
        try {
            jsonObject.put("net_type", (Object)OneSignal.osUtils.getNetType());
        }
        catch (Throwable t) {}
    }
    
    public static void addPermissionObserver(final OSPermissionObserver osPermissionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
        }
        else {
            getPermissionStateChangesObserver().addObserver(osPermissionObserver);
            if (getCurrentPermissionState(OneSignal.appContext).compare(getLastPermissionState(OneSignal.appContext))) {
                OSPermissionChangedInternalObserver.fireChangesToPublicObserver(getCurrentPermissionState(OneSignal.appContext));
            }
        }
    }
    
    public static void addSubscriptionObserver(final OSSubscriptionObserver osSubscriptionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
        }
        else {
            getSubscriptionStateChangesObserver().addObserver(osSubscriptionObserver);
            if (getCurrentSubscriptionState(OneSignal.appContext).compare(getLastSubscriptionState(OneSignal.appContext))) {
                OSSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentSubscriptionState(OneSignal.appContext));
            }
        }
    }
    
    private static void addTaskToQueue(final PendingTaskRunnable pendingTaskRunnable) {
        pendingTaskRunnable.taskId = OneSignal.lastTaskId.incrementAndGet();
        if (OneSignal.pendingTaskExecutor == null) {
            Log(LOG_LEVEL.INFO, "Adding a task to the pending queue with ID: " + pendingTaskRunnable.taskId);
            OneSignal.taskQueueWaitingForInit.add(pendingTaskRunnable);
        }
        else if (!OneSignal.pendingTaskExecutor.isShutdown()) {
            Log(LOG_LEVEL.INFO, "Executor is still running, add to the executor with ID: " + pendingTaskRunnable.taskId);
            OneSignal.pendingTaskExecutor.submit(pendingTaskRunnable);
        }
    }
    
    static boolean areNotificationsEnabledForSubscribedState() {
        return !OneSignal.mInitBuilder.mUnsubscribeWhenNotificationsAreDisabled || OSUtils.areNotificationsEnabled(OneSignal.appContext);
    }
    
    private static boolean atLogLevel(final LOG_LEVEL log_LEVEL) {
        return log_LEVEL.compareTo(OneSignal.visualLogLevel) < 1 || log_LEVEL.compareTo(OneSignal.logCatLevel) < 1;
    }
    
    public static void cancelGroupedNotifications(final String s) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("cancelGroupedNotifications()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: ldc             "notification"
                //     5: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
                //     8: checkcast       Landroid/app/NotificationManager;
                //    11: astore          7
                //    13: getstatic       com/onesignal/OneSignal.appContext:Landroid/content/Context;
                //    16: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
                //    19: astore          6
                //    21: aconst_null    
                //    22: astore          5
                //    24: aconst_null    
                //    25: astore          4
                //    27: aload           4
                //    29: astore_3       
                //    30: aload           5
                //    32: astore_2       
                //    33: aload           6
                //    35: invokevirtual   com/onesignal/OneSignalDbHelper.getReadableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
                //    38: astore          8
                //    40: aload           4
                //    42: astore_3       
                //    43: aload           5
                //    45: astore_2       
                //    46: aload_0        
                //    47: getfield        com/onesignal/OneSignal$24.val$group:Ljava/lang/String;
                //    50: astore          9
                //    52: aload           4
                //    54: astore_3       
                //    55: aload           5
                //    57: astore_2       
                //    58: aload           8
                //    60: ldc             "notification"
                //    62: iconst_1       
                //    63: anewarray       Ljava/lang/String;
                //    66: dup            
                //    67: iconst_0       
                //    68: ldc             "android_notification_id"
                //    70: aastore        
                //    71: ldc             "group_id = ? AND dismissed = 0 AND opened = 0"
                //    73: iconst_1       
                //    74: anewarray       Ljava/lang/String;
                //    77: dup            
                //    78: iconst_0       
                //    79: aload           9
                //    81: aastore        
                //    82: aconst_null    
                //    83: aconst_null    
                //    84: aconst_null    
                //    85: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
                //    88: astore          4
                //    90: aload           4
                //    92: astore_3       
                //    93: aload           4
                //    95: astore_2       
                //    96: aload           4
                //    98: invokeinterface android/database/Cursor.moveToNext:()Z
                //   103: ifeq            331
                //   106: aload           4
                //   108: astore_3       
                //   109: aload           4
                //   111: astore_2       
                //   112: aload           4
                //   114: aload           4
                //   116: ldc             "android_notification_id"
                //   118: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
                //   123: invokeinterface android/database/Cursor.getInt:(I)I
                //   128: istore_1       
                //   129: iload_1        
                //   130: iconst_m1      
                //   131: if_icmpeq       90
                //   134: aload           4
                //   136: astore_3       
                //   137: aload           4
                //   139: astore_2       
                //   140: aload           7
                //   142: iload_1        
                //   143: invokevirtual   android/app/NotificationManager.cancel:(I)V
                //   146: goto            90
                //   149: astore          4
                //   151: aload_3        
                //   152: astore_2       
                //   153: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   156: new             Ljava/lang/StringBuilder;
                //   159: dup            
                //   160: invokespecial   java/lang/StringBuilder.<init>:()V
                //   163: ldc             "Error getting android notifications part of group: "
                //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   168: aload_0        
                //   169: getfield        com/onesignal/OneSignal$24.val$group:Ljava/lang/String;
                //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   175: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   178: aload           4
                //   180: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   183: aload_3        
                //   184: ifnull          202
                //   187: aload_3        
                //   188: invokeinterface android/database/Cursor.isClosed:()Z
                //   193: ifne            202
                //   196: aload_3        
                //   197: invokeinterface android/database/Cursor.close:()V
                //   202: aconst_null    
                //   203: astore_3       
                //   204: aconst_null    
                //   205: astore_2       
                //   206: aload           6
                //   208: invokevirtual   com/onesignal/OneSignalDbHelper.getWritableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
                //   211: astore          4
                //   213: aload           4
                //   215: astore_2       
                //   216: aload           4
                //   218: astore_3       
                //   219: aload           4
                //   221: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
                //   224: aload           4
                //   226: astore_2       
                //   227: aload           4
                //   229: astore_3       
                //   230: aload_0        
                //   231: getfield        com/onesignal/OneSignal$24.val$group:Ljava/lang/String;
                //   234: astore          5
                //   236: aload           4
                //   238: astore_2       
                //   239: aload           4
                //   241: astore_3       
                //   242: new             Landroid/content/ContentValues;
                //   245: dup            
                //   246: invokespecial   android/content/ContentValues.<init>:()V
                //   249: astore          6
                //   251: aload           4
                //   253: astore_2       
                //   254: aload           4
                //   256: astore_3       
                //   257: aload           6
                //   259: ldc             "dismissed"
                //   261: iconst_1       
                //   262: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //   265: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
                //   268: aload           4
                //   270: astore_2       
                //   271: aload           4
                //   273: astore_3       
                //   274: aload           4
                //   276: ldc             "notification"
                //   278: aload           6
                //   280: ldc             "group_id = ? AND opened = 0 AND dismissed = 0"
                //   282: iconst_1       
                //   283: anewarray       Ljava/lang/String;
                //   286: dup            
                //   287: iconst_0       
                //   288: aload           5
                //   290: aastore        
                //   291: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
                //   294: pop            
                //   295: aload           4
                //   297: astore_2       
                //   298: aload           4
                //   300: astore_3       
                //   301: aload           4
                //   303: getstatic       com/onesignal/OneSignal.appContext:Landroid/content/Context;
                //   306: invokestatic    com/onesignal/BadgeCountUpdater.update:(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
                //   309: aload           4
                //   311: astore_2       
                //   312: aload           4
                //   314: astore_3       
                //   315: aload           4
                //   317: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
                //   320: aload           4
                //   322: ifnull          330
                //   325: aload           4
                //   327: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   330: return         
                //   331: aload           4
                //   333: ifnull          202
                //   336: aload           4
                //   338: invokeinterface android/database/Cursor.isClosed:()Z
                //   343: ifne            202
                //   346: aload           4
                //   348: invokeinterface android/database/Cursor.close:()V
                //   353: goto            202
                //   356: astore_3       
                //   357: aload_2        
                //   358: ifnull          376
                //   361: aload_2        
                //   362: invokeinterface android/database/Cursor.isClosed:()Z
                //   367: ifne            376
                //   370: aload_2        
                //   371: invokeinterface android/database/Cursor.close:()V
                //   376: aload_3        
                //   377: athrow         
                //   378: astore_2       
                //   379: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   382: ldc             "Error closing transaction! "
                //   384: aload_2        
                //   385: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   388: return         
                //   389: astore          4
                //   391: aload_2        
                //   392: astore_3       
                //   393: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   396: new             Ljava/lang/StringBuilder;
                //   399: dup            
                //   400: invokespecial   java/lang/StringBuilder.<init>:()V
                //   403: ldc             "Error marking a notifications with group "
                //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   408: aload_0        
                //   409: getfield        com/onesignal/OneSignal$24.val$group:Ljava/lang/String;
                //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   415: ldc             " as dismissed! "
                //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   423: aload           4
                //   425: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   428: aload_2        
                //   429: ifnull          330
                //   432: aload_2        
                //   433: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   436: return         
                //   437: astore_2       
                //   438: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   441: ldc             "Error closing transaction! "
                //   443: aload_2        
                //   444: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   447: return         
                //   448: astore_2       
                //   449: aload_3        
                //   450: ifnull          457
                //   453: aload_3        
                //   454: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   457: aload_2        
                //   458: athrow         
                //   459: astore_3       
                //   460: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   463: ldc             "Error closing transaction! "
                //   465: aload_3        
                //   466: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   469: goto            457
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  33     40     149    202    Ljava/lang/Throwable;
                //  33     40     356    378    Any
                //  46     52     149    202    Ljava/lang/Throwable;
                //  46     52     356    378    Any
                //  58     90     149    202    Ljava/lang/Throwable;
                //  58     90     356    378    Any
                //  96     106    149    202    Ljava/lang/Throwable;
                //  96     106    356    378    Any
                //  112    129    149    202    Ljava/lang/Throwable;
                //  112    129    356    378    Any
                //  140    146    149    202    Ljava/lang/Throwable;
                //  140    146    356    378    Any
                //  153    183    356    378    Any
                //  206    213    389    448    Ljava/lang/Throwable;
                //  206    213    448    472    Any
                //  219    224    389    448    Ljava/lang/Throwable;
                //  219    224    448    472    Any
                //  230    236    389    448    Ljava/lang/Throwable;
                //  230    236    448    472    Any
                //  242    251    389    448    Ljava/lang/Throwable;
                //  242    251    448    472    Any
                //  257    268    389    448    Ljava/lang/Throwable;
                //  257    268    448    472    Any
                //  274    295    389    448    Ljava/lang/Throwable;
                //  274    295    448    472    Any
                //  301    309    389    448    Ljava/lang/Throwable;
                //  301    309    448    472    Any
                //  315    320    389    448    Ljava/lang/Throwable;
                //  315    320    448    472    Any
                //  325    330    378    389    Ljava/lang/Throwable;
                //  393    428    448    472    Any
                //  432    436    437    448    Ljava/lang/Throwable;
                //  453    457    459    472    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0330:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
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
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications part of group " + s + " - movingthis operation to a waiting task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static void cancelNotification(final int n) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("cancelNotification()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final OneSignalDbHelper instance = OneSignalDbHelper.getInstance(OneSignal.appContext);
                SQLiteDatabase sqLiteDatabase = null;
                Throwable writableDbWithRetries = null;
                try {
                    final SQLiteDatabase sqLiteDatabase2 = sqLiteDatabase = (SQLiteDatabase)(writableDbWithRetries = (Throwable)instance.getWritableDbWithRetries());
                    sqLiteDatabase2.beginTransaction();
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    final String string = "android_notification_id = " + n + " AND " + "opened" + " = 0 AND " + "dismissed" + " = 0";
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    final ContentValues contentValues = new ContentValues();
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    contentValues.put("dismissed", Integer.valueOf(1));
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    if (sqLiteDatabase2.update("notification", contentValues, string, (String[])null) > 0) {
                        writableDbWithRetries = (Throwable)sqLiteDatabase2;
                        sqLiteDatabase = sqLiteDatabase2;
                        NotificationSummaryManager.updatePossibleDependentSummaryOnDismiss(OneSignal.appContext, sqLiteDatabase2, n);
                    }
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    BadgeCountUpdater.update(sqLiteDatabase2, OneSignal.appContext);
                    writableDbWithRetries = (Throwable)sqLiteDatabase2;
                    sqLiteDatabase = sqLiteDatabase2;
                    sqLiteDatabase2.setTransactionSuccessful();
                    if (sqLiteDatabase2 == null) {
                        return;
                    }
                    try {
                        sqLiteDatabase2.endTransaction();
                    }
                    catch (Throwable writableDbWithRetries) {
                        OneSignal.Log(LOG_LEVEL.ERROR, "Error closing transaction! ", writableDbWithRetries);
                    }
                }
                catch (Throwable t) {
                    sqLiteDatabase = (SQLiteDatabase)writableDbWithRetries;
                    OneSignal.Log(LOG_LEVEL.ERROR, "Error marking a notification id " + n + " as dismissed! ", t);
                    if (writableDbWithRetries == null) {
                        return;
                    }
                    try {
                        ((SQLiteDatabase)writableDbWithRetries).endTransaction();
                    }
                    catch (Throwable t2) {
                        OneSignal.Log(LOG_LEVEL.ERROR, "Error closing transaction! ", t2);
                    }
                }
                finally {
                    Label_0243: {
                        if (sqLiteDatabase == null) {
                            break Label_0243;
                        }
                        try {
                            sqLiteDatabase.endTransaction();
                        }
                        catch (Throwable t3) {
                            OneSignal.Log(LOG_LEVEL.ERROR, "Error closing transaction! ", t3);
                        }
                    }
                }
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notification id: " + n + " at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
            OneSignal.taskQueueWaitingForInit.add(runnable);
            return;
        }
        runnable.run();
        ((NotificationManager)OneSignal.appContext.getSystemService("notification")).cancel(n);
    }
    
    public static void clearOneSignalNotifications() {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("clearOneSignalNotifications()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: ldc             "notification"
                //     5: invokevirtual   android/content/Context.getSystemService:(Ljava/lang/String;)Ljava/lang/Object;
                //     8: checkcast       Landroid/app/NotificationManager;
                //    11: astore          4
                //    13: getstatic       com/onesignal/OneSignal.appContext:Landroid/content/Context;
                //    16: invokestatic    com/onesignal/OneSignalDbHelper.getInstance:(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
                //    19: astore          6
                //    21: aconst_null    
                //    22: astore_2       
                //    23: aconst_null    
                //    24: astore_3       
                //    25: aload           6
                //    27: invokevirtual   com/onesignal/OneSignalDbHelper.getReadableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
                //    30: ldc             "notification"
                //    32: iconst_1       
                //    33: anewarray       Ljava/lang/String;
                //    36: dup            
                //    37: iconst_0       
                //    38: ldc             "android_notification_id"
                //    40: aastore        
                //    41: ldc             "dismissed = 0 AND opened = 0"
                //    43: aconst_null    
                //    44: aconst_null    
                //    45: aconst_null    
                //    46: aconst_null    
                //    47: invokevirtual   android/database/sqlite/SQLiteDatabase.query:(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
                //    50: astore          5
                //    52: aload           5
                //    54: astore_3       
                //    55: aload           5
                //    57: astore_2       
                //    58: aload           5
                //    60: invokeinterface android/database/Cursor.moveToFirst:()Z
                //    65: ifeq            113
                //    68: aload           5
                //    70: astore_3       
                //    71: aload           5
                //    73: astore_2       
                //    74: aload           4
                //    76: aload           5
                //    78: aload           5
                //    80: ldc             "android_notification_id"
                //    82: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
                //    87: invokeinterface android/database/Cursor.getInt:(I)I
                //    92: invokevirtual   android/app/NotificationManager.cancel:(I)V
                //    95: aload           5
                //    97: astore_3       
                //    98: aload           5
                //   100: astore_2       
                //   101: aload           5
                //   103: invokeinterface android/database/Cursor.moveToNext:()Z
                //   108: istore_1       
                //   109: iload_1        
                //   110: ifne            68
                //   113: aconst_null    
                //   114: astore          4
                //   116: aconst_null    
                //   117: astore_3       
                //   118: aload           6
                //   120: invokevirtual   com/onesignal/OneSignalDbHelper.getWritableDbWithRetries:()Landroid/database/sqlite/SQLiteDatabase;
                //   123: astore          6
                //   125: aload           6
                //   127: astore_3       
                //   128: aload           6
                //   130: astore          4
                //   132: aload           6
                //   134: invokevirtual   android/database/sqlite/SQLiteDatabase.beginTransaction:()V
                //   137: aload           6
                //   139: astore_3       
                //   140: aload           6
                //   142: astore          4
                //   144: new             Landroid/content/ContentValues;
                //   147: dup            
                //   148: invokespecial   android/content/ContentValues.<init>:()V
                //   151: astore_2       
                //   152: aload           6
                //   154: astore_3       
                //   155: aload           6
                //   157: astore          4
                //   159: aload_2        
                //   160: ldc             "dismissed"
                //   162: iconst_1       
                //   163: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //   166: invokevirtual   android/content/ContentValues.put:(Ljava/lang/String;Ljava/lang/Integer;)V
                //   169: aload           6
                //   171: astore_3       
                //   172: aload           6
                //   174: astore          4
                //   176: aload           6
                //   178: ldc             "notification"
                //   180: aload_2        
                //   181: ldc             "opened = 0"
                //   183: aconst_null    
                //   184: invokevirtual   android/database/sqlite/SQLiteDatabase.update:(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
                //   187: pop            
                //   188: aload           6
                //   190: astore_3       
                //   191: aload           6
                //   193: astore          4
                //   195: aload           6
                //   197: invokevirtual   android/database/sqlite/SQLiteDatabase.setTransactionSuccessful:()V
                //   200: aload           6
                //   202: ifnull          213
                //   205: aload           5
                //   207: astore_2       
                //   208: aload           6
                //   210: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   213: aload           5
                //   215: astore_3       
                //   216: aload           5
                //   218: astore_2       
                //   219: iconst_0       
                //   220: getstatic       com/onesignal/OneSignal.appContext:Landroid/content/Context;
                //   223: invokestatic    com/onesignal/BadgeCountUpdater.updateCount:(ILandroid/content/Context;)V
                //   226: aload           5
                //   228: ifnull          238
                //   231: aload           5
                //   233: invokeinterface android/database/Cursor.close:()V
                //   238: return         
                //   239: astore          4
                //   241: aload           5
                //   243: astore_3       
                //   244: aload           5
                //   246: astore_2       
                //   247: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   250: ldc             "Error closing transaction! "
                //   252: aload           4
                //   254: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   257: goto            213
                //   260: astore          4
                //   262: aload_3        
                //   263: astore_2       
                //   264: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   267: ldc             "Error canceling all notifications! "
                //   269: aload           4
                //   271: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   274: aload_3        
                //   275: ifnull          238
                //   278: aload_3        
                //   279: invokeinterface android/database/Cursor.close:()V
                //   284: return         
                //   285: astore_2       
                //   286: aload_3        
                //   287: astore          4
                //   289: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   292: ldc             "Error marking all notifications as dismissed! "
                //   294: aload_2        
                //   295: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   298: aload_3        
                //   299: ifnull          213
                //   302: aload           5
                //   304: astore_2       
                //   305: aload_3        
                //   306: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   309: goto            213
                //   312: astore          4
                //   314: aload           5
                //   316: astore_3       
                //   317: aload           5
                //   319: astore_2       
                //   320: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   323: ldc             "Error closing transaction! "
                //   325: aload           4
                //   327: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   330: goto            213
                //   333: astore_3       
                //   334: aload_2        
                //   335: ifnull          344
                //   338: aload_2        
                //   339: invokeinterface android/database/Cursor.close:()V
                //   344: aload_3        
                //   345: athrow         
                //   346: astore          6
                //   348: aload           4
                //   350: ifnull          361
                //   353: aload           5
                //   355: astore_2       
                //   356: aload           4
                //   358: invokevirtual   android/database/sqlite/SQLiteDatabase.endTransaction:()V
                //   361: aload           5
                //   363: astore_3       
                //   364: aload           5
                //   366: astore_2       
                //   367: aload           6
                //   369: athrow         
                //   370: astore          4
                //   372: aload           5
                //   374: astore_3       
                //   375: aload           5
                //   377: astore_2       
                //   378: getstatic       com/onesignal/OneSignal$LOG_LEVEL.ERROR:Lcom/onesignal/OneSignal$LOG_LEVEL;
                //   381: ldc             "Error closing transaction! "
                //   383: aload           4
                //   385: invokestatic    com/onesignal/OneSignal.Log:(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
                //   388: goto            361
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  25     52     260    285    Ljava/lang/Throwable;
                //  25     52     333    346    Any
                //  58     68     260    285    Ljava/lang/Throwable;
                //  58     68     333    346    Any
                //  74     95     260    285    Ljava/lang/Throwable;
                //  74     95     333    346    Any
                //  101    109    260    285    Ljava/lang/Throwable;
                //  101    109    333    346    Any
                //  118    125    285    333    Ljava/lang/Throwable;
                //  118    125    346    391    Any
                //  132    137    285    333    Ljava/lang/Throwable;
                //  132    137    346    391    Any
                //  144    152    285    333    Ljava/lang/Throwable;
                //  144    152    346    391    Any
                //  159    169    285    333    Ljava/lang/Throwable;
                //  159    169    346    391    Any
                //  176    188    285    333    Ljava/lang/Throwable;
                //  176    188    346    391    Any
                //  195    200    285    333    Ljava/lang/Throwable;
                //  195    200    346    391    Any
                //  208    213    239    260    Ljava/lang/Throwable;
                //  208    213    333    346    Any
                //  219    226    260    285    Ljava/lang/Throwable;
                //  219    226    333    346    Any
                //  247    257    260    285    Ljava/lang/Throwable;
                //  247    257    333    346    Any
                //  264    274    333    346    Any
                //  289    298    346    391    Any
                //  305    309    312    333    Ljava/lang/Throwable;
                //  305    309    333    346    Any
                //  320    330    260    285    Ljava/lang/Throwable;
                //  320    330    333    346    Any
                //  356    361    370    391    Ljava/lang/Throwable;
                //  356    361    333    346    Any
                //  367    370    260    285    Ljava/lang/Throwable;
                //  367    370    333    346    Any
                //  378    388    260    285    Ljava/lang/Throwable;
                //  378    388    333    346    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0213:
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
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
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static OSInFocusDisplayOption currentInFocusDisplayOption() {
        return getCurrentOrNewInitBuilder().mDisplayOption;
    }
    
    public static void deleteTag(final String s) {
        deleteTag(s, null);
    }
    
    public static void deleteTag(final String s, final ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTag()")) {
            return;
        }
        final ArrayList<String> list = new ArrayList<String>(1);
        list.add(s);
        deleteTags(list, changeTagsUpdateHandler);
    }
    
    public static void deleteTags(final String s) {
        deleteTags(s, null);
    }
    
    public static void deleteTags(final String s, final ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTags()")) {
            return;
        }
        try {
            final JSONObject jsonObject = new JSONObject();
            final JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); ++i) {
                jsonObject.put(jsonArray.getString(i), (Object)"");
            }
            sendTags(jsonObject, changeTagsUpdateHandler);
        }
        catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", t);
        }
    }
    
    public static void deleteTags(final Collection<String> collection) {
        deleteTags(collection, null);
    }
    
    public static void deleteTags(final Collection<String> collection, final ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTags()")) {
            return;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            final Iterator<String> iterator = collection.iterator();
            while (iterator.hasNext()) {
                jsonObject.put((String)iterator.next(), (Object)"");
            }
        }
        catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", t);
            return;
        }
        sendTags(jsonObject, changeTagsUpdateHandler);
    }
    
    public static void enableSound(final boolean b) {
        if (OneSignal.appContext == null) {
            return;
        }
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", b);
    }
    
    public static void enableVibrate(final boolean b) {
        if (OneSignal.appContext == null) {
            return;
        }
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", b);
    }
    
    private static void fireCallbackForOpenedNotifications() {
        final Iterator<JSONArray> iterator = OneSignal.unprocessedOpenedNotifis.iterator();
        while (iterator.hasNext()) {
            runNotificationOpenedCallback(iterator.next(), true, false);
        }
        OneSignal.unprocessedOpenedNotifis.clear();
    }
    
    static void fireEmailUpdateFailure() {
        if (OneSignal.emailUpdateHandler != null) {
            OneSignal.emailUpdateHandler.onFailure(new EmailUpdateError(EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
            OneSignal.emailUpdateHandler = null;
        }
    }
    
    static void fireEmailUpdateSuccess() {
        if (OneSignal.emailUpdateHandler != null) {
            OneSignal.emailUpdateHandler.onSuccess();
            OneSignal.emailUpdateHandler = null;
        }
    }
    
    static void fireIdsAvailableCallback() {
        if (OneSignal.idsAvailableHandler != null) {
            OSUtils.runOnMainUIThread(new Runnable() {
                @Override
                public void run() {
                    internalFireIdsAvailableCallback();
                }
            });
        }
    }
    
    private static void fireIntentFromNotificationOpen(final Context context) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(268566528);
                context.startActivity(launchIntentForPackage);
            }
        }
    }
    
    private static void fireNotificationOpenedHandler(final OSNotificationOpenResult osNotificationOpenResult) {
        OSUtils.runOnMainUIThread(new Runnable() {
            @Override
            public void run() {
                OneSignal.mInitBuilder.mNotificationOpenedHandler.notificationOpened(osNotificationOpenResult);
            }
        });
    }
    
    @NonNull
    private static OSNotificationOpenResult generateOsNotificationOpenResult(final JSONArray jsonArray, final boolean shown, final boolean b) {
        final int length = jsonArray.length();
        int i = 1;
        final OSNotificationOpenResult osNotificationOpenResult = new OSNotificationOpenResult();
        final OSNotification notification = new OSNotification();
        notification.isAppInFocus = isAppActive();
        notification.shown = shown;
        notification.androidNotificationId = jsonArray.optJSONObject(0).optInt("notificationId");
        String actionID = null;
        int j = 0;
    Label_0350_Outer:
        while (j < length) {
        Label_0350:
            while (true) {
                String optString = null;
                Block_1: {
                    break Block_1;
                Label_0338:
                    while (true) {
                        do {
                            Label_0139: {
                                break Label_0139;
                                String s = actionID;
                                try {
                                    final JSONObject jsonObject = jsonArray.getJSONObject(j);
                                    s = actionID;
                                    notification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(jsonObject);
                                    optString = actionID;
                                    if (actionID != null) {
                                        continue Label_0338;
                                    }
                                    s = actionID;
                                    optString = actionID;
                                    if (jsonObject.has("actionSelected")) {
                                        s = actionID;
                                        optString = jsonObject.optString("actionSelected", (String)null);
                                        continue Label_0338;
                                    }
                                    continue Label_0338;
                                    s = optString;
                                    // iftrue(Label_0167:, notification.groupedNotifications != null)
                                    while (true) {
                                        Block_9: {
                                            break Block_9;
                                            s = optString;
                                            notification.groupedNotifications.add(notification.payload);
                                            actionID = optString;
                                            break Label_0350;
                                        }
                                        s = optString;
                                        notification.groupedNotifications = new ArrayList<OSNotificationPayload>();
                                        continue Label_0350_Outer;
                                    }
                                }
                                catch (Throwable t) {
                                    Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + j + "/" + length + " for callback.", t);
                                    actionID = s;
                                }
                            }
                            ++j;
                            continue Label_0350_Outer;
                        } while (i == 0);
                        break;
                    }
                }
                i = 0;
                actionID = optString;
                continue Label_0350;
            }
        }
        osNotificationOpenResult.notification = notification;
        osNotificationOpenResult.action = new OSNotificationAction();
        osNotificationOpenResult.action.actionID = actionID;
        final OSNotificationAction action = osNotificationOpenResult.action;
        OSNotificationAction.ActionType type;
        if (actionID != null) {
            type = OSNotificationAction.ActionType.ActionTaken;
        }
        else {
            type = OSNotificationAction.ActionType.Opened;
        }
        action.type = type;
        if (b) {
            osNotificationOpenResult.notification.displayType = OSNotification.DisplayType.InAppAlert;
            return osNotificationOpenResult;
        }
        osNotificationOpenResult.notification.displayType = OSNotification.DisplayType.Notification;
        return osNotificationOpenResult;
    }
    
    private static OSEmailSubscriptionState getCurrentEmailSubscriptionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.currentEmailSubscriptionState == null) {
            OneSignal.currentEmailSubscriptionState = new OSEmailSubscriptionState(false);
            OneSignal.currentEmailSubscriptionState.observable.addObserverStrong(new OSEmailSubscriptionChangedInternalObserver());
        }
        return OneSignal.currentEmailSubscriptionState;
    }
    
    public static Builder getCurrentOrNewInitBuilder() {
        if (OneSignal.mInitBuilder == null) {
            OneSignal.mInitBuilder = new Builder();
        }
        return OneSignal.mInitBuilder;
    }
    
    private static OSPermissionState getCurrentPermissionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.currentPermissionState == null) {
            OneSignal.currentPermissionState = new OSPermissionState(false);
            OneSignal.currentPermissionState.observable.addObserverStrong(new OSPermissionChangedInternalObserver());
        }
        return OneSignal.currentPermissionState;
    }
    
    private static OSSubscriptionState getCurrentSubscriptionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.currentSubscriptionState == null) {
            OneSignal.currentSubscriptionState = new OSSubscriptionState(false, getCurrentPermissionState(context).getEnabled());
            getCurrentPermissionState(context).observable.addObserver(OneSignal.currentSubscriptionState);
            OneSignal.currentSubscriptionState.observable.addObserverStrong(new OSSubscriptionChangedInternalObserver());
        }
        return OneSignal.currentSubscriptionState;
    }
    
    static String getEmailId() {
        if ("".equals(OneSignal.emailId)) {
            return null;
        }
        if (OneSignal.emailId == null && OneSignal.appContext != null) {
            OneSignal.emailId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "OS_EMAIL_ID", null);
        }
        return OneSignal.emailId;
    }
    
    static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> getEmailSubscriptionStateChangesObserver() {
        if (OneSignal.emailSubscriptionStateChangesObserver == null) {
            OneSignal.emailSubscriptionStateChangesObserver = new OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges>("onOSEmailSubscriptionChanged", true);
        }
        return OneSignal.emailSubscriptionStateChangesObserver;
    }
    
    static boolean getFilterOtherGCMReceivers(final Context context) {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
    }
    
    static boolean getFirebaseAnalyticsEnabled(final Context context) {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", false);
    }
    
    static boolean getInAppAlertNotificationEnabled() {
        return OneSignal.mInitBuilder != null && OneSignal.mInitBuilder.mDisplayOption == OSInFocusDisplayOption.InAppAlert;
    }
    
    private static OSInFocusDisplayOption getInFocusDisplaying(final int n) {
        switch (n) {
            default: {
                if (n < 0) {
                    return OSInFocusDisplayOption.None;
                }
                return OSInFocusDisplayOption.Notification;
            }
            case 0: {
                return OSInFocusDisplayOption.None;
            }
            case 1: {
                return OSInFocusDisplayOption.InAppAlert;
            }
            case 2: {
                return OSInFocusDisplayOption.Notification;
            }
        }
    }
    
    private static OSEmailSubscriptionState getLastEmailSubscriptionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.lastEmailSubscriptionState == null) {
            OneSignal.lastEmailSubscriptionState = new OSEmailSubscriptionState(true);
        }
        return OneSignal.lastEmailSubscriptionState;
    }
    
    private static OSPermissionState getLastPermissionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.lastPermissionState == null) {
            OneSignal.lastPermissionState = new OSPermissionState(true);
        }
        return OneSignal.lastPermissionState;
    }
    
    private static long getLastSessionTime(final Context context) {
        return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", -31000L);
    }
    
    private static OSSubscriptionState getLastSubscriptionState(final Context context) {
        if (context == null) {
            return null;
        }
        if (OneSignal.lastSubscriptionState == null) {
            OneSignal.lastSubscriptionState = new OSSubscriptionState(true, false);
        }
        return OneSignal.lastSubscriptionState;
    }
    
    private static LOG_LEVEL getLogLevel(final int n) {
        switch (n) {
            default: {
                if (n < 0) {
                    return LOG_LEVEL.NONE;
                }
                return LOG_LEVEL.VERBOSE;
            }
            case 0: {
                return LOG_LEVEL.NONE;
            }
            case 1: {
                return LOG_LEVEL.FATAL;
            }
            case 2: {
                return LOG_LEVEL.ERROR;
            }
            case 3: {
                return LOG_LEVEL.WARN;
            }
            case 4: {
                return LOG_LEVEL.INFO;
            }
            case 5: {
                return LOG_LEVEL.DEBUG;
            }
            case 6: {
                return LOG_LEVEL.VERBOSE;
            }
        }
    }
    
    static String getNotificationIdFromGCMBundle(final Bundle bundle) {
        if (bundle.isEmpty()) {
            return null;
        }
        try {
            if (bundle.containsKey("custom")) {
                final JSONObject jsonObject = new JSONObject(bundle.getString("custom"));
                if (jsonObject.has("i")) {
                    return jsonObject.optString("i", (String)null);
                }
                Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
                return null;
            }
        }
        catch (Throwable t) {
            Log(LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", t);
            return null;
        }
        Log(LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
        return null;
    }
    
    private static String getNotificationIdFromGCMJsonPayload(final JSONObject jsonObject) {
        try {
            return new JSONObject(jsonObject.optString("custom")).optString("i", (String)null);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    static boolean getNotificationsWhenActiveEnabled() {
        return OneSignal.mInitBuilder == null || OneSignal.mInitBuilder.mDisplayOption == OSInFocusDisplayOption.Notification;
    }
    
    static OSObservable<OSPermissionObserver, OSPermissionStateChanges> getPermissionStateChangesObserver() {
        if (OneSignal.permissionStateChangesObserver == null) {
            OneSignal.permissionStateChangesObserver = new OSObservable<OSPermissionObserver, OSPermissionStateChanges>("onOSPermissionChanged", true);
        }
        return OneSignal.permissionStateChangesObserver;
    }
    
    public static OSPermissionSubscriptionState getPermissionSubscriptionState() {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("getPermissionSubscriptionState()")) {
            return null;
        }
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
            return null;
        }
        final OSPermissionSubscriptionState osPermissionSubscriptionState = new OSPermissionSubscriptionState();
        osPermissionSubscriptionState.subscriptionStatus = getCurrentSubscriptionState(OneSignal.appContext);
        osPermissionSubscriptionState.permissionStatus = getCurrentPermissionState(OneSignal.appContext);
        osPermissionSubscriptionState.emailSubscriptionStatus = getCurrentEmailSubscriptionState(OneSignal.appContext);
        return osPermissionSubscriptionState;
    }
    
    private static PushRegistrator getPushRegistrator() {
        if (OneSignal.mPushRegistrator != null) {
            return OneSignal.mPushRegistrator;
        }
        if (OneSignal.deviceType == 2) {
            OneSignal.mPushRegistrator = new PushRegistratorADM();
        }
        else if (OSUtils.hasFCMLibrary()) {
            OneSignal.mPushRegistrator = new PushRegistratorFCM();
        }
        else {
            OneSignal.mPushRegistrator = new PushRegistratorGCM();
        }
        return OneSignal.mPushRegistrator;
    }
    
    static String getSavedAppId() {
        return getSavedAppId(OneSignal.appContext);
    }
    
    private static String getSavedAppId(final Context context) {
        if (context == null) {
            return "";
        }
        return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", null);
    }
    
    static boolean getSavedUserConsentStatus() {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
    }
    
    private static String getSavedUserId(final Context context) {
        if (context == null) {
            return "";
        }
        return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
    }
    
    static boolean getSoundEnabled(final Context context) {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", true);
    }
    
    static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> getSubscriptionStateChangesObserver() {
        if (OneSignal.subscriptionStateChangesObserver == null) {
            OneSignal.subscriptionStateChangesObserver = new OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges>("onOSSubscriptionChanged", true);
        }
        return OneSignal.subscriptionStateChangesObserver;
    }
    
    public static void getTags(final GetTagsHandler pendingGetTagsHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("getTags()")) {
            return;
        }
        OneSignal.pendingGetTagsHandler = pendingGetTagsHandler;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (pendingGetTagsHandler == null) {
                    OneSignal.Log(LOG_LEVEL.ERROR, "getTagsHandler is null!");
                }
                else if (OneSignal.getUserId() != null) {
                    internalFireGetTagsCallback(OneSignal.pendingGetTagsHandler);
                }
            }
        };
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
            OneSignal.taskQueueWaitingForInit.add(runnable);
            return;
        }
        runnable.run();
    }
    
    private static int getTimeZoneOffset() {
        final TimeZone timeZone = Calendar.getInstance().getTimeZone();
        int rawOffset = timeZone.getRawOffset();
        if (timeZone.inDaylightTime(new Date())) {
            rawOffset += timeZone.getDSTSavings();
        }
        return rawOffset / 1000;
    }
    
    static String getUserId() {
        if (OneSignal.userId == null && OneSignal.appContext != null) {
            OneSignal.userId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
        }
        return OneSignal.userId;
    }
    
    static boolean getVibrate(final Context context) {
        return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", true);
    }
    
    static void handleFailedEmailLogout() {
        if (OneSignal.emailLogoutHandler != null) {
            OneSignal.emailLogoutHandler.onFailure(new EmailUpdateError(EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
            OneSignal.emailLogoutHandler = null;
        }
    }
    
    public static void handleNotificationOpen(final Context context, final JSONArray jsonArray, final boolean b) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            notificationOpenedRESTCall(context, jsonArray);
            if (OneSignal.trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled(OneSignal.appContext)) {
                OneSignal.trackFirebaseAnalytics.trackOpenedEvent(generateOsNotificationOpenResult(jsonArray, true, b));
            }
            boolean openURLFromNotification = false;
            final boolean equals = "DISABLE".equals(OSUtils.getManifestMeta(context, "com.onesignal.NotificationOpened.DEFAULT"));
            if (!equals) {
                openURLFromNotification = openURLFromNotification(context, jsonArray);
            }
            runNotificationOpenedCallback(jsonArray, true, b);
            if (!b && !openURLFromNotification && !equals) {
                fireIntentFromNotificationOpen(context);
            }
        }
    }
    
    static void handleNotificationReceived(final JSONArray jsonArray, final boolean b, final boolean b2) {
        final OSNotificationOpenResult generateOsNotificationOpenResult = generateOsNotificationOpenResult(jsonArray, b, b2);
        if (OneSignal.trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled(OneSignal.appContext)) {
            OneSignal.trackFirebaseAnalytics.trackReceivedEvent(generateOsNotificationOpenResult);
        }
        if (OneSignal.mInitBuilder == null || OneSignal.mInitBuilder.mNotificationReceivedHandler == null) {
            return;
        }
        OneSignal.mInitBuilder.mNotificationReceivedHandler.notificationReceived(generateOsNotificationOpenResult.notification);
    }
    
    static void handleSuccessfulEmailLogout() {
        if (OneSignal.emailLogoutHandler != null) {
            OneSignal.emailLogoutHandler.onSuccess();
            OneSignal.emailLogoutHandler = null;
        }
    }
    
    public static void idsAvailable(final IdsAvailableHandler idsAvailableHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("idsAvailable()")) {
            return;
        }
        OneSignal.idsAvailableHandler = idsAvailableHandler;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (OneSignal.getUserId() != null) {
                    OSUtils.runOnMainUIThread(new Runnable() {
                        @Override
                        public void run() {
                            internalFireIdsAvailableCallback();
                        }
                    });
                }
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static void init(final Context context, final String s, final String s2) {
        init(context, s, s2, null, null);
    }
    
    public static void init(final Context context, final String s, final String s2, final NotificationOpenedHandler notificationOpenedHandler) {
        init(context, s, s2, notificationOpenedHandler, null);
    }
    
    public static void init(final Context appContext, final String mGoogleProjectNumber, final String appId, final NotificationOpenedHandler mNotificationOpenedHandler, final NotificationReceivedHandler mNotificationReceivedHandler) {
        setAppContext(appContext);
        if (OneSignal.requiresUserPrivacyConsent && !userProvidedPrivacyConsent()) {
            Log(LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
            OneSignal.delayedInitParams = new DelayedConsentInitializationParameters(appContext, mGoogleProjectNumber, appId, mNotificationOpenedHandler, mNotificationReceivedHandler);
        }
        else {
            OneSignal.mInitBuilder = getCurrentOrNewInitBuilder();
            OneSignal.mInitBuilder.mDisplayOptionCarryOver = false;
            OneSignal.mInitBuilder.mNotificationOpenedHandler = mNotificationOpenedHandler;
            OneSignal.mInitBuilder.mNotificationReceivedHandler = mNotificationReceivedHandler;
            if (!OneSignal.mGoogleProjectNumberIsRemote) {
                OneSignal.mGoogleProjectNumber = mGoogleProjectNumber;
            }
            OneSignal.osUtils = new OSUtils();
            OneSignal.deviceType = OneSignal.osUtils.getDeviceType();
            OneSignal.subscribableStatus = OneSignal.osUtils.initializationChecker(appContext, OneSignal.deviceType, appId);
            if (OneSignal.subscribableStatus != -999) {
                if (OneSignal.initDone) {
                    if (OneSignal.mInitBuilder.mNotificationOpenedHandler != null) {
                        fireCallbackForOpenedNotifications();
                    }
                }
                else {
                    final boolean b = OneSignal.foreground = (appContext instanceof Activity);
                    OneSignal.appId = appId;
                    saveFilterOtherGCMReceivers(OneSignal.mInitBuilder.mFilterOtherGCMReceivers);
                    Label_0367: {
                        if (!b) {
                            break Label_0367;
                        }
                        ActivityLifecycleHandler.curActivity = (Activity)appContext;
                        NotificationRestorer.asyncRestore(OneSignal.appContext);
                        while (true) {
                            OneSignal.lastTrackedFocusTime = SystemClock.elapsedRealtime();
                            OneSignalStateSynchronizer.initUserState();
                            ((Application)OneSignal.appContext).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new ActivityLifecycleListener());
                            while (true) {
                                try {
                                    Class.forName("com.amazon.device.iap.PurchasingListener");
                                    OneSignal.trackAmazonPurchase = new TrackAmazonPurchase(OneSignal.appContext);
                                    final String savedAppId = getSavedAppId();
                                    if (savedAppId != null) {
                                        if (!savedAppId.equals(OneSignal.appId)) {
                                            Log(LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
                                            SaveAppId(OneSignal.appId);
                                            OneSignalStateSynchronizer.resetCurrentState();
                                        }
                                    }
                                    else {
                                        BadgeCountUpdater.updateCount(0, OneSignal.appContext);
                                        SaveAppId(OneSignal.appId);
                                    }
                                    OSPermissionChangedInternalObserver.handleInternalChanges(getCurrentPermissionState(OneSignal.appContext));
                                    if (OneSignal.foreground || getUserId() == null) {
                                        OneSignal.sendAsSession = isPastOnSessionTime();
                                        setLastSessionTime(System.currentTimeMillis());
                                        startRegistrationOrOnSession();
                                    }
                                    if (OneSignal.mInitBuilder.mNotificationOpenedHandler != null) {
                                        fireCallbackForOpenedNotifications();
                                    }
                                    if (TrackGooglePurchase.CanTrack(OneSignal.appContext)) {
                                        OneSignal.trackGooglePurchase = new TrackGooglePurchase(OneSignal.appContext);
                                    }
                                    if (TrackFirebaseAnalytics.CanTrack()) {
                                        OneSignal.trackFirebaseAnalytics = new TrackFirebaseAnalytics(OneSignal.appContext);
                                    }
                                    PushRegistratorFCM.disableFirebaseInstanceIdService(OneSignal.appContext);
                                    OneSignal.initDone = true;
                                    startPendingTasks();
                                    return;
                                    ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
                                }
                                catch (ClassNotFoundException ex) {
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void init(final Builder mInitBuilder) {
        if (getCurrentOrNewInitBuilder().mDisplayOptionCarryOver) {
            mInitBuilder.mDisplayOption = getCurrentOrNewInitBuilder().mDisplayOption;
        }
        OneSignal.mInitBuilder = mInitBuilder;
        final Context mContext = OneSignal.mInitBuilder.mContext;
        OneSignal.mInitBuilder.mContext = null;
        try {
            final Bundle metaData = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), 128).metaData;
            String s2;
            final String s = s2 = metaData.getString("onesignal_google_project_number");
            if (s != null) {
                s2 = s;
                if (s.length() > 4) {
                    s2 = s.substring(4);
                }
            }
            setRequiresUserPrivacyConsent("ENABLE".equalsIgnoreCase(metaData.getString("com.onesignal.PrivacyConsent")));
            init(mContext, s2, metaData.getString("onesignal_app_id"), OneSignal.mInitBuilder.mNotificationOpenedHandler, OneSignal.mInitBuilder.mNotificationReceivedHandler);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static void internalFireGetTagsCallback(final GetTagsHandler getTagsHandler) {
        if (getTagsHandler == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                final UserStateSynchronizer.GetTagsResult tags = OneSignalStateSynchronizer.getTags(!OneSignal.getTagsCall);
                if (tags.serverSuccess) {
                    OneSignal.getTagsCall = true;
                }
                if (tags.result == null || tags.toString().equals("{}")) {
                    getTagsHandler.tagsAvailable(null);
                    return;
                }
                getTagsHandler.tagsAvailable(tags.result);
            }
        }, "OS_GETTAGS_CALLBACK").start();
    }
    
    private static void internalFireIdsAvailableCallback() {
        synchronized (OneSignal.class) {
            if (OneSignal.idsAvailableHandler != null) {
                String registrationId = OneSignalStateSynchronizer.getRegistrationId();
                if (!OneSignalStateSynchronizer.getSubscribed()) {
                    registrationId = null;
                }
                final String userId = getUserId();
                if (userId != null) {
                    OneSignal.idsAvailableHandler.idsAvailable(userId, registrationId);
                    if (registrationId != null) {
                        OneSignal.idsAvailableHandler = null;
                    }
                }
            }
        }
    }
    
    static boolean isAppActive() {
        return OneSignal.initDone && isForeground();
    }
    
    private static boolean isDuplicateNotification(final String s, Context query) {
        if (s == null || "".equals(s)) {
            return false;
        }
        boolean b = false;
        final OneSignalDbHelper instance = OneSignalDbHelper.getInstance(query);
        Object o = null;
        query = null;
        while (true) {
            try {
                final Context context = (Context)(o = (query = (Context)instance.getReadableDbWithRetries().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { s }, (String)null, (String)null, (String)null)));
                boolean moveToFirst;
                b = (moveToFirst = ((Cursor)context).moveToFirst());
                if (context != null) {
                    ((Cursor)context).close();
                    moveToFirst = b;
                }
                if (moveToFirst) {
                    Log(LOG_LEVEL.DEBUG, "Duplicate GCM message received, skip processing of " + s);
                    return true;
                }
            }
            catch (Throwable t) {
                o = query;
                Log(LOG_LEVEL.ERROR, "Could not check for duplicate, assuming unique.", t);
                boolean moveToFirst = b;
                if (query != null) {
                    ((Cursor)query).close();
                    moveToFirst = b;
                }
                continue;
            }
            finally {
                if (o != null) {
                    ((Cursor)o).close();
                }
            }
            break;
        }
        return false;
    }
    
    static boolean isForeground() {
        return OneSignal.foreground;
    }
    
    private static boolean isPastOnSessionTime() {
        return OneSignal.sendAsSession || (System.currentTimeMillis() - getLastSessionTime(OneSignal.appContext)) / 1000L >= 30L;
    }
    
    private static void logHttpError(final String s, final int n, final Throwable t, final String s2) {
        String string = "";
        if (s2 != null) {
            string = string;
            if (atLogLevel(LOG_LEVEL.INFO)) {
                string = "\n" + s2 + "\n";
            }
        }
        Log(LOG_LEVEL.WARN, "HTTP code: " + n + " " + s + string, t);
    }
    
    public static void logoutEmail() {
        logoutEmail(null);
    }
    
    public static void logoutEmail(@Nullable final EmailUpdateHandler emailLogoutHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("logoutEmail()")) {
            return;
        }
        if (getEmailId() == null) {
            if (emailLogoutHandler != null) {
                emailLogoutHandler.onFailure(new EmailUpdateError(EmailErrorType.INVALID_OPERATION, "logoutEmail not valid as email was not set or already logged out!"));
            }
            Log(LOG_LEVEL.ERROR, "logoutEmail not valid as email was not set or already logged out!");
            return;
        }
        OneSignal.emailLogoutHandler = emailLogoutHandler;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                OneSignalStateSynchronizer.logoutEmail();
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    private static void makeAndroidParamsRequest() {
        if (OneSignal.awlFired) {
            registerForPushToken();
            return;
        }
        final OneSignalRestClient.ResponseHandler responseHandler = new OneSignalRestClient.ResponseHandler() {
            @Override
            void onFailure(final int n, final String s, final Throwable t) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                int n;
                                if ((n = OneSignal.androidParamsReties * 10000 + 30000) > 90000) {
                                    n = 90000;
                                }
                                OneSignal.Log(LOG_LEVEL.INFO, "Failed to get Android parameters, trying again in " + n / 1000 + " seconds.");
                                Thread.sleep(n);
                                OneSignal.androidParamsReties = OneSignal.androidParamsReties;
                                makeAndroidParamsRequest();
                            }
                            catch (Throwable t) {
                                continue;
                            }
                            break;
                        }
                    }
                }, "OS_PARAMS_REQUEST").start();
            }
            
            @Override
            void onSuccess(final String s) {
                while (true) {
                    try {
                        final JSONObject jsonObject = new JSONObject(s);
                        if (jsonObject.has("android_sender_id")) {
                            OneSignal.mGoogleProjectNumberIsRemote = true;
                            OneSignal.mGoogleProjectNumber = jsonObject.getString("android_sender_id");
                        }
                        OneSignal.mEnterp = jsonObject.optBoolean("enterp", false);
                        OneSignal.useEmailAuth = jsonObject.optBoolean("use_email_auth", false);
                        OneSignal.awl = jsonObject.getJSONObject("awl_list");
                        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", jsonObject.optBoolean("fba", false));
                        NotificationChannelManager.processChannelList(OneSignal.appContext, jsonObject);
                        OneSignal.awlFired = true;
                        registerForPushToken();
                    }
                    catch (Throwable t) {
                        t.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        };
        final String string = "apps/" + OneSignal.appId + "/android_params.js";
        final String userId = getUserId();
        String string2 = string;
        if (userId != null) {
            string2 = string + "?player_id=" + userId;
        }
        Log(LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
        OneSignalRestClient.get(string2, (OneSignalRestClient.ResponseHandler)responseHandler);
    }
    
    static boolean notValidOrDuplicated(final Context context, final JSONObject jsonObject) {
        final String notificationIdFromGCMJsonPayload = getNotificationIdFromGCMJsonPayload(jsonObject);
        return notificationIdFromGCMJsonPayload == null || isDuplicateNotification(notificationIdFromGCMJsonPayload, context);
    }
    
    private static void notificationOpenedRESTCall(final Context context, final JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); ++i) {
            try {
                final String optString = new JSONObject(jsonArray.getJSONObject(i).optString("custom", (String)null)).optString("i", (String)null);
                if (!OneSignal.postedOpenedNotifIds.contains(optString)) {
                    OneSignal.postedOpenedNotifIds.add(optString);
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("app_id", (Object)getSavedAppId(context));
                    jsonObject.put("player_id", (Object)getSavedUserId(context));
                    jsonObject.put("opened", true);
                    OneSignalRestClient.put("notifications/" + optString, jsonObject, (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
                        @Override
                        void onFailure(final int n, final String s, final Throwable t) {
                            logHttpError("sending Notification Opened Failed", n, t, s);
                        }
                    });
                }
            }
            catch (Throwable t) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", t);
            }
        }
    }
    
    static void onAppFocus() {
        OneSignal.foreground = true;
        LocationGMS.onFocusChange();
        OneSignal.lastTrackedFocusTime = SystemClock.elapsedRealtime();
        OneSignal.sendAsSession = isPastOnSessionTime();
        setLastSessionTime(System.currentTimeMillis());
        startRegistrationOrOnSession();
        if (OneSignal.trackGooglePurchase != null) {
            OneSignal.trackGooglePurchase.trackIAP();
        }
        NotificationRestorer.asyncRestore(OneSignal.appContext);
        getCurrentPermissionState(OneSignal.appContext).refreshAsTo();
        if (OneSignal.trackFirebaseAnalytics != null && getFirebaseAnalyticsEnabled(OneSignal.appContext)) {
            OneSignal.trackFirebaseAnalytics.trackInfluenceOpenEvent();
        }
        OneSignalSyncServiceUtils.cancelSyncTask(OneSignal.appContext);
    }
    
    @WorkerThread
    static boolean onAppLostFocus() {
        OneSignal.foreground = false;
        LocationGMS.onFocusChange();
        if (OneSignal.initDone) {
            if (OneSignal.trackAmazonPurchase != null) {
                OneSignal.trackAmazonPurchase.checkListener();
            }
            if (OneSignal.lastTrackedFocusTime != -1L) {
                final long n = (long)((SystemClock.elapsedRealtime() - OneSignal.lastTrackedFocusTime) / 1000.0 + 0.5);
                OneSignal.lastTrackedFocusTime = SystemClock.elapsedRealtime();
                if (n >= 0L && n <= 86400L) {
                    if (OneSignal.appContext == null) {
                        Log(LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
                        return false;
                    }
                    final boolean scheduleSyncService = scheduleSyncService();
                    setLastSessionTime(System.currentTimeMillis());
                    final long n2 = GetUnsentActiveTime() + n;
                    SaveUnsentActiveTime(n2);
                    if (n2 >= 60L && getUserId() != null) {
                        if (!scheduleSyncService) {
                            OneSignalSyncServiceUtils.scheduleSyncTask(OneSignal.appContext);
                        }
                        OneSignalSyncServiceUtils.syncOnFocusTime();
                        return false;
                    }
                    if (n2 >= 60L) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private static void onTaskRan(final long n) {
        if (OneSignal.lastTaskId.get() == n) {
            Log(LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
            OneSignal.pendingTaskExecutor.shutdown();
        }
    }
    
    public static void onesignalLog(final LOG_LEVEL log_LEVEL, final String s) {
        Log(log_LEVEL, s);
    }
    
    private static boolean openURLFromNotification(final Context context, final JSONArray jsonArray) {
        boolean b;
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            b = false;
        }
        else {
            final int length = jsonArray.length();
            boolean b2 = false;
            int n = 0;
            while (true) {
                b = b2;
                if (n >= length) {
                    break;
                }
                boolean b3;
                try {
                    final JSONObject jsonObject = jsonArray.getJSONObject(n);
                    if (!jsonObject.has("custom")) {
                        b3 = b2;
                    }
                    else {
                        final JSONObject jsonObject2 = new JSONObject(jsonObject.optString("custom"));
                        b3 = b2;
                        if (jsonObject2.has("u")) {
                            String s2;
                            final String s = s2 = jsonObject2.optString("u", (String)null);
                            if (!s.contains("://")) {
                                s2 = "http://" + s;
                            }
                            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(s2.trim()));
                            intent.addFlags(1476919296);
                            context.startActivity(intent);
                            b3 = true;
                        }
                    }
                }
                catch (Throwable t) {
                    Log(LOG_LEVEL.ERROR, "Error parsing JSON item " + n + "/" + length + " for launching a web URL.", t);
                    b3 = b2;
                }
                ++n;
                b2 = b3;
            }
        }
        return b;
    }
    
    public static void postNotification(final String s, final PostNotificationResponseHandler postNotificationResponseHandler) {
        try {
            postNotification(new JSONObject(s), postNotificationResponseHandler);
        }
        catch (JSONException ex) {
            Log(LOG_LEVEL.ERROR, "Invalid postNotification JSON format: " + s);
        }
    }
    
    public static void postNotification(final JSONObject jsonObject, final PostNotificationResponseHandler postNotificationResponseHandler) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("postNotification()")) {
            try {
                if (!jsonObject.has("app_id")) {
                    jsonObject.put("app_id", (Object)getSavedAppId());
                }
                OneSignalRestClient.post("notifications/", jsonObject, (OneSignalRestClient.ResponseHandler)new OneSignalRestClient.ResponseHandler() {
                    @Override
                    void onFailure(final int n, String s, final Throwable t) {
                        logHttpError("create notification failed", n, t, s);
                        if (postNotificationResponseHandler == null) {
                            return;
                        }
                        if (n == 0) {
                            s = "{\"error\": \"HTTP no response error\"}";
                        }
                        try {
                            postNotificationResponseHandler.onFailure(new JSONObject(s));
                        }
                        catch (Throwable t2) {
                            try {
                                postNotificationResponseHandler.onFailure(new JSONObject("{\"error\": \"Unknown response!\"}"));
                            }
                            catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    
                    public void onSuccess(final String s) {
                        final LOG_LEVEL debug = LOG_LEVEL.DEBUG;
                        final StringBuilder append = new StringBuilder().append("HTTP create notification success: ");
                        Label_0073: {
                            if (s == null) {
                                break Label_0073;
                            }
                            String s2 = s;
                            while (true) {
                                OneSignal.Log(debug, append.append(s2).toString());
                                if (postNotificationResponseHandler == null) {
                                    return;
                                }
                                try {
                                    final JSONObject jsonObject = new JSONObject(s);
                                    if (jsonObject.has("errors")) {
                                        postNotificationResponseHandler.onFailure(jsonObject);
                                        return;
                                    }
                                    postNotificationResponseHandler.onSuccess(new JSONObject(s));
                                    return;
                                    s2 = "null";
                                    continue;
                                }
                                catch (Throwable t) {
                                    t.printStackTrace();
                                }
                                break;
                            }
                        }
                    }
                });
            }
            catch (JSONException ex) {
                Log(LOG_LEVEL.ERROR, "HTTP create notification json exception!", (Throwable)ex);
                if (postNotificationResponseHandler != null) {
                    try {
                        postNotificationResponseHandler.onFailure(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
                    }
                    catch (JSONException ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static void promptLocation() {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("promptLocation()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LocationGMS.getLocation(OneSignal.appContext, true, (LocationGMS.LocationHandler)new LocationGMS.LocationHandler() {
                    @Override
                    public void complete(final LocationPoint locationPoint) {
                        if (!OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName("promptLocation()") && locationPoint != null) {
                            OneSignalStateSynchronizer.updateLocation(locationPoint);
                        }
                    }
                    
                    @Override
                    public CALLBACK_TYPE getType() {
                        return CALLBACK_TYPE.PROMPT_LOCATION;
                    }
                });
                OneSignal.promptedLocation = true;
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static void provideUserConsent(final boolean b) {
        final boolean userProvidedPrivacyConsent = userProvidedPrivacyConsent();
        saveUserConsentStatus(b);
        if (!userProvidedPrivacyConsent && b && OneSignal.delayedInitParams != null) {
            init(OneSignal.delayedInitParams.context, OneSignal.delayedInitParams.googleProjectNumber, OneSignal.delayedInitParams.appId, OneSignal.delayedInitParams.openedHandler, OneSignal.delayedInitParams.receivedHandler);
            OneSignal.delayedInitParams = null;
        }
    }
    
    private static boolean pushStatusRuntimeError(final int n) {
        return n < -6;
    }
    
    private static void registerForPushToken() {
        getPushRegistrator().registerForPush(OneSignal.appContext, OneSignal.mGoogleProjectNumber, (PushRegistrator.RegisteredHandler)new PushRegistrator.RegisteredHandler() {
            @Override
            public void complete(final String pushToken, final int n) {
                if (n < 1) {
                    if (OneSignalStateSynchronizer.getRegistrationId() == null && (OneSignal.subscribableStatus == 1 || pushStatusRuntimeError(OneSignal.subscribableStatus))) {
                        OneSignal.subscribableStatus = n;
                    }
                }
                else if (pushStatusRuntimeError(OneSignal.subscribableStatus)) {
                    OneSignal.subscribableStatus = n;
                }
                OneSignal.lastRegistrationId = pushToken;
                OneSignal.registerForPushFired = true;
                getCurrentSubscriptionState(OneSignal.appContext).setPushToken(pushToken);
                registerUser();
            }
        });
    }
    
    private static void registerUser() {
        Log(LOG_LEVEL.DEBUG, "registerUser: registerForPushFired:" + OneSignal.registerForPushFired + ", locationFired: " + OneSignal.locationFired + ", awlFired: " + OneSignal.awlFired);
        if (!OneSignal.registerForPushFired || !OneSignal.locationFired || !OneSignal.awlFired) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    registerUserTask();
                    OneSignalChromeTab.setup(OneSignal.appContext, OneSignal.appId, OneSignal.userId, AdvertisingIdProviderGPS.getLastValue());
                }
                catch (JSONException ex) {
                    OneSignal.Log(LOG_LEVEL.FATAL, "FATAL Error registering device!", (Throwable)ex);
                }
            }
        }, "OS_REG_USER").start();
    }
    
    private static void registerUserTask() throws JSONException {
        final String packageName = OneSignal.appContext.getPackageName();
        final PackageManager packageManager = OneSignal.appContext.getPackageManager();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("app_id", (Object)OneSignal.appId);
        final String identifier = OneSignal.mainAdIdProvider.getIdentifier(OneSignal.appContext);
        if (identifier != null) {
            jsonObject.put("ad_id", (Object)identifier);
        }
    Label_0176_Outer:
        while (true) {
            jsonObject.put("device_os", (Object)Build$VERSION.RELEASE);
            jsonObject.put("timezone", getTimeZoneOffset());
            jsonObject.put("language", (Object)OSUtils.getCorrectedLanguage());
            jsonObject.put("sdk", (Object)"031005");
            jsonObject.put("sdk_type", (Object)OneSignal.sdkType);
            jsonObject.put("android_package", (Object)packageName);
            jsonObject.put("device_model", (Object)Build.MODEL);
            while (true) {
                int n = 0;
                while (true) {
                    try {
                        jsonObject.put("game_version", packageManager.getPackageInfo(packageName, 0).versionCode);
                        try {
                            final List installedPackages = packageManager.getInstalledPackages(0);
                            final JSONArray jsonArray = new JSONArray();
                            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
                            n = 0;
                            if (n >= installedPackages.size()) {
                                jsonObject.put("pkgs", (Object)jsonArray);
                                jsonObject.put("net_type", (Object)OneSignal.osUtils.getNetType());
                                jsonObject.put("carrier", (Object)OneSignal.osUtils.getCarrierName());
                                jsonObject.put("rooted", RootToolsInternalMethods.isRooted());
                                OneSignalStateSynchronizer.updateDeviceInfo(jsonObject);
                                jsonObject = new JSONObject();
                                jsonObject.put("identifier", (Object)OneSignal.lastRegistrationId);
                                jsonObject.put("subscribableStatus", OneSignal.subscribableStatus);
                                jsonObject.put("androidPermission", areNotificationsEnabledForSubscribedState());
                                jsonObject.put("device_type", OneSignal.deviceType);
                                OneSignalStateSynchronizer.updatePushState(jsonObject);
                                if (OneSignal.shareLocation && OneSignal.lastLocationPoint != null) {
                                    OneSignalStateSynchronizer.updateLocation(OneSignal.lastLocationPoint);
                                }
                                if (OneSignal.sendAsSession) {
                                    OneSignalStateSynchronizer.setSyncAsNewSession();
                                }
                                OneSignal.waitingToPostStateSync = false;
                                return;
                            }
                            instance.update(installedPackages.get(n).packageName.getBytes());
                            final String encodeToString = Base64.encodeToString(instance.digest(), 2);
                            if (OneSignal.awl.has(encodeToString)) {
                                jsonArray.put((Object)encodeToString);
                            }
                        }
                        catch (Throwable packageManager) {}
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        continue Label_0176_Outer;
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
    }
    
    public static void removeEmailSubscriptionObserver(@NonNull final OSEmailSubscriptionObserver osEmailSubscriptionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify email subscription observer");
            return;
        }
        getEmailSubscriptionStateChangesObserver().removeObserver(osEmailSubscriptionObserver);
    }
    
    public static void removeExternalUserId() {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("removeExternalUserId()")) {
            return;
        }
        setExternalUserId("");
    }
    
    public static void removeNotificationOpenedHandler() {
        getCurrentOrNewInitBuilder().mNotificationOpenedHandler = null;
    }
    
    public static void removeNotificationReceivedHandler() {
        getCurrentOrNewInitBuilder().mNotificationReceivedHandler = null;
    }
    
    public static void removePermissionObserver(final OSPermissionObserver osPermissionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify permission observer");
            return;
        }
        getPermissionStateChangesObserver().removeObserver(osPermissionObserver);
    }
    
    public static void removeSubscriptionObserver(final OSSubscriptionObserver osSubscriptionObserver) {
        if (OneSignal.appContext == null) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify subscription observer");
            return;
        }
        getSubscriptionStateChangesObserver().removeObserver(osSubscriptionObserver);
    }
    
    public static boolean requiresUserPrivacyConsent() {
        return OneSignal.requiresUserPrivacyConsent && !userProvidedPrivacyConsent();
    }
    
    private static void runNotificationOpenedCallback(final JSONArray jsonArray, final boolean b, final boolean b2) {
        if (OneSignal.mInitBuilder == null || OneSignal.mInitBuilder.mNotificationOpenedHandler == null) {
            OneSignal.unprocessedOpenedNotifis.add(jsonArray);
            return;
        }
        fireNotificationOpenedHandler(generateOsNotificationOpenResult(jsonArray, b, b2));
    }
    
    static void saveEmailId(String emailId) {
        OneSignal.emailId = emailId;
        if (OneSignal.appContext == null) {
            return;
        }
        final String prefs_ONESIGNAL = OneSignalPrefs.PREFS_ONESIGNAL;
        if ("".equals(OneSignal.emailId)) {
            emailId = null;
        }
        else {
            emailId = OneSignal.emailId;
        }
        OneSignalPrefs.saveString(prefs_ONESIGNAL, "OS_EMAIL_ID", emailId);
    }
    
    static void saveFilterOtherGCMReceivers(final boolean b) {
        if (OneSignal.appContext == null) {
            return;
        }
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", b);
    }
    
    static void saveUserConsentStatus(final boolean b) {
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", b);
    }
    
    static void saveUserId(final String userId) {
        OneSignal.userId = userId;
        if (OneSignal.appContext == null) {
            return;
        }
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", OneSignal.userId);
    }
    
    static boolean scheduleSyncService() {
        final boolean persist = OneSignalStateSynchronizer.persist();
        if (persist) {
            OneSignalSyncServiceUtils.scheduleSyncTask(OneSignal.appContext);
        }
        return LocationGMS.scheduleUpdate(OneSignal.appContext) || persist;
    }
    
    static void sendOnFocus(final long n, final boolean b) {
        try {
            final JSONObject put = new JSONObject().put("app_id", (Object)OneSignal.appId).put("type", 1).put("state", (Object)"ping").put("active_time", n);
            addNetType(put);
            sendOnFocusToPlayer(getUserId(), put, b);
            final String emailId = getEmailId();
            if (emailId != null) {
                sendOnFocusToPlayer(emailId, put, b);
            }
        }
        catch (Throwable t) {
            Log(LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", t);
        }
    }
    
    private static void sendOnFocusToPlayer(String string, final JSONObject jsonObject, final boolean b) {
        string = "players/" + string + "/on_focus";
        final OneSignalRestClient.ResponseHandler responseHandler = new OneSignalRestClient.ResponseHandler() {
            @Override
            void onFailure(final int n, final String s, final Throwable t) {
                logHttpError("sending on_focus Failed", n, t, s);
            }
            
            @Override
            void onSuccess(final String s) {
                SaveUnsentActiveTime(0L);
            }
        };
        if (b) {
            OneSignalRestClient.postSync(string, jsonObject, (OneSignalRestClient.ResponseHandler)responseHandler);
            return;
        }
        OneSignalRestClient.post(string, jsonObject, (OneSignalRestClient.ResponseHandler)responseHandler);
    }
    
    static void sendPurchases(final JSONArray jsonArray, final boolean newAsExisting, final OneSignalRestClient.ResponseHandler restResponseHandler) {
        if (!shouldLogUserPrivacyConsentErrorMessageForMethodName("sendPurchases()")) {
            if (getUserId() == null) {
                OneSignal.iapUpdateJob = new IAPUpdateJob(jsonArray);
                OneSignal.iapUpdateJob.newAsExisting = newAsExisting;
                OneSignal.iapUpdateJob.restResponseHandler = restResponseHandler;
                return;
            }
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("app_id", (Object)OneSignal.appId);
                if (newAsExisting) {
                    jsonObject.put("existing", true);
                }
                jsonObject.put("purchases", (Object)jsonArray);
                OneSignalRestClient.post("players/" + getUserId() + "/on_purchase", jsonObject, restResponseHandler);
                if (getEmailId() != null) {
                    OneSignalRestClient.post("players/" + getEmailId() + "/on_purchase", jsonObject, null);
                }
            }
            catch (Throwable t) {
                Log(LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", t);
            }
        }
    }
    
    public static void sendTag(final String s, final String s2) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("sendTag()")) {
            return;
        }
        try {
            sendTags(new JSONObject().put(s, (Object)s2));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void sendTags(final String s) {
        try {
            sendTags(new JSONObject(s));
        }
        catch (JSONException ex) {
            Log(LOG_LEVEL.ERROR, "Generating JSONObject for sendTags failed!", (Throwable)ex);
        }
    }
    
    public static void sendTags(final JSONObject jsonObject) {
        sendTags(jsonObject, null);
    }
    
    public static void sendTags(final JSONObject jsonObject, final ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("sendTags()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (jsonObject == null) {
                    if (changeTagsUpdateHandler != null) {
                        changeTagsUpdateHandler.onFailure(new SendTagsError(-1, "Attempted to send null tags"));
                    }
                }
                else {
                    final JSONObject result = OneSignalStateSynchronizer.getTags(false).result;
                    final JSONObject jsonObject = new JSONObject();
                    final Iterator keys = jsonObject.keys();
                Block_10_Outer:
                    while (true) {
                        Label_0201: {
                            if (!keys.hasNext()) {
                                break Label_0201;
                            }
                            final String s = keys.next();
                            try {
                                final Object opt = jsonObject.opt(s);
                                if (opt instanceof JSONArray || opt instanceof JSONObject) {
                                    OneSignal.Log(LOG_LEVEL.ERROR, "Omitting key '" + s + "'! sendTags DO NOT supported nested values!");
                                    continue Block_10_Outer;
                                }
                                if (!jsonObject.isNull(s) && !"".equals(opt)) {
                                    jsonObject.put(s, (Object)opt.toString());
                                    continue Block_10_Outer;
                                }
                                if (result != null && result.has(s)) {
                                    jsonObject.put(s, (Object)"");
                                    continue Block_10_Outer;
                                }
                                continue Block_10_Outer;
                                // iftrue(Label_0033:, this.val$tagsHandler == null)
                                // iftrue(Label_0222:, jsonObject.toString().equals((Object)"{}"))
                            Block_11:
                                while (true) {
                                    OneSignalStateSynchronizer.sendTags(jsonObject, changeTagsUpdateHandler);
                                    return;
                                    Label_0222: {
                                        break Block_11;
                                    }
                                    continue;
                                }
                                changeTagsUpdateHandler.onSuccess(result);
                                return;
                            }
                            catch (Throwable t) {}
                        }
                    }
                }
                Label_0033:;
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
            if (changeTagsUpdateHandler != null) {
                changeTagsUpdateHandler.onFailure(new SendTagsError(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
            }
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    static void setAppContext(final Context context) {
        int n;
        if (OneSignal.appContext == null) {
            n = 1;
        }
        else {
            n = 0;
        }
        OneSignal.appContext = context.getApplicationContext();
        if (n != 0) {
            OneSignalPrefs.startDelayedWrite();
        }
    }
    
    public static void setEmail(@NonNull final String s) {
        setEmail(s, null, null);
    }
    
    public static void setEmail(@NonNull final String s, final EmailUpdateHandler emailUpdateHandler) {
        setEmail(s, null, emailUpdateHandler);
    }
    
    public static void setEmail(@NonNull final String s, @Nullable final String s2) {
        setEmail(s, s2, null);
    }
    
    public static void setEmail(@NonNull final String s, @Nullable final String s2, @Nullable final EmailUpdateHandler emailUpdateHandler) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setEmail()")) {
            return;
        }
        if (!OSUtils.isValidEmail(s)) {
            if (emailUpdateHandler != null) {
                emailUpdateHandler.onFailure(new EmailUpdateError(EmailErrorType.VALIDATION, "Email is invalid"));
            }
            Log(LOG_LEVEL.ERROR, "Email is invalid");
            return;
        }
        if (OneSignal.useEmailAuth && s2 == null) {
            if (emailUpdateHandler != null) {
                emailUpdateHandler.onFailure(new EmailUpdateError(EmailErrorType.REQUIRES_EMAIL_AUTH, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
            }
            Log(LOG_LEVEL.ERROR, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
            return;
        }
        OneSignal.emailUpdateHandler = emailUpdateHandler;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final String trim = s.trim();
                final String val$emailAuthHash = s2;
                if (val$emailAuthHash != null) {
                    val$emailAuthHash.toLowerCase();
                }
                getCurrentEmailSubscriptionState(OneSignal.appContext).setEmailAddress(trim);
                OneSignalStateSynchronizer.setEmail(trim.toLowerCase(), val$emailAuthHash);
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static void setExternalUserId(final String s) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setExternalId()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    OneSignalStateSynchronizer.setExternalUserId(s);
                }
                catch (JSONException ex) {
                    String s;
                    if (s == "") {
                        s = "remove";
                    }
                    else {
                        s = "set";
                    }
                    OneSignal.onesignalLog(LOG_LEVEL.ERROR, "Attempted to " + s + " external ID but encountered a JSON exception");
                    ex.printStackTrace();
                }
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    public static void setInFocusDisplaying(final int n) {
        setInFocusDisplaying(getInFocusDisplaying(n));
    }
    
    public static void setInFocusDisplaying(final OSInFocusDisplayOption mDisplayOption) {
        getCurrentOrNewInitBuilder().mDisplayOptionCarryOver = true;
        getCurrentOrNewInitBuilder().mDisplayOption = mDisplayOption;
    }
    
    static void setLastSessionTime(final long n) {
        OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", n);
    }
    
    public static void setLocationShared(final boolean shareLocation) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setLocationShared()")) {
            return;
        }
        if (!(OneSignal.shareLocation = shareLocation)) {
            OneSignalStateSynchronizer.clearLocation();
        }
        Log(LOG_LEVEL.DEBUG, "shareLocation:" + OneSignal.shareLocation);
    }
    
    public static void setLogLevel(final int n, final int n2) {
        setLogLevel(getLogLevel(n), getLogLevel(n2));
    }
    
    public static void setLogLevel(final LOG_LEVEL logCatLevel, final LOG_LEVEL visualLogLevel) {
        OneSignal.logCatLevel = logCatLevel;
        OneSignal.visualLogLevel = visualLogLevel;
    }
    
    public static void setRequiresUserPrivacyConsent(final boolean requiresUserPrivacyConsent) {
        if (OneSignal.requiresUserPrivacyConsent && !requiresUserPrivacyConsent) {
            Log(LOG_LEVEL.ERROR, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
            return;
        }
        OneSignal.requiresUserPrivacyConsent = requiresUserPrivacyConsent;
    }
    
    public static void setSubscription(final boolean b) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setSubscription()")) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getCurrentSubscriptionState(OneSignal.appContext).setUserSubscriptionSetting(b);
                OneSignalStateSynchronizer.setSubscription(b);
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    static boolean shouldLogUserPrivacyConsentErrorMessageForMethodName(final String s) {
        if (OneSignal.requiresUserPrivacyConsent && !userProvidedPrivacyConsent()) {
            if (s != null) {
                Log(LOG_LEVEL.WARN, "Method " + s + " was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
            }
            return true;
        }
        return false;
    }
    
    private static boolean shouldRunTaskThroughQueue() {
        if (!OneSignal.initDone || OneSignal.pendingTaskExecutor != null) {
            if (!OneSignal.initDone && OneSignal.pendingTaskExecutor == null) {
                return true;
            }
            if (OneSignal.pendingTaskExecutor != null && !OneSignal.pendingTaskExecutor.isShutdown()) {
                return true;
            }
        }
        return false;
    }
    
    public static Builder startInit(final Context context) {
        return new Builder(context);
    }
    
    private static void startLocationUpdate() {
        LocationGMS.getLocation(OneSignal.appContext, OneSignal.mInitBuilder.mPromptLocation && !OneSignal.promptedLocation, (LocationGMS.LocationHandler)new LocationGMS.LocationHandler() {
            @Override
            public void complete(final LocationPoint locationPoint) {
                OneSignal.lastLocationPoint = locationPoint;
                OneSignal.locationFired = true;
                registerUser();
            }
            
            @Override
            public CALLBACK_TYPE getType() {
                return CALLBACK_TYPE.STARTUP;
            }
        });
    }
    
    private static void startPendingTasks() {
        if (!OneSignal.taskQueueWaitingForInit.isEmpty()) {
            OneSignal.pendingTaskExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull final Runnable runnable) {
                    final Thread thread = new Thread(runnable);
                    thread.setName("OS_PENDING_EXECUTOR_" + thread.getId());
                    return thread;
                }
            });
            while (!OneSignal.taskQueueWaitingForInit.isEmpty()) {
                OneSignal.pendingTaskExecutor.submit(OneSignal.taskQueueWaitingForInit.poll());
            }
        }
    }
    
    private static void startRegistrationOrOnSession() {
        boolean promptedLocation = false;
        if (OneSignal.waitingToPostStateSync) {
            return;
        }
        OneSignal.waitingToPostStateSync = true;
        OneSignal.registerForPushFired = false;
        if (OneSignal.sendAsSession) {
            OneSignal.locationFired = false;
        }
        startLocationUpdate();
        makeAndroidParamsRequest();
        if (OneSignal.promptedLocation || OneSignal.mInitBuilder.mPromptLocation) {
            promptedLocation = true;
        }
        OneSignal.promptedLocation = promptedLocation;
    }
    
    @Deprecated
    public static void syncHashedEmail(final String s) {
        if (shouldLogUserPrivacyConsentErrorMessageForMethodName("SyncHashedEmail()") || !OSUtils.isValidEmail(s)) {
            return;
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                OneSignalStateSynchronizer.syncHashedEmail(s.trim().toLowerCase());
            }
        };
        if (OneSignal.appContext == null || shouldRunTaskThroughQueue()) {
            Log(LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
            addTaskToQueue(new PendingTaskRunnable(runnable));
            return;
        }
        runnable.run();
    }
    
    static void updateEmailIdDependents(final String emailUserId) {
        saveEmailId(emailUserId);
        getCurrentEmailSubscriptionState(OneSignal.appContext).setEmailUserId(emailUserId);
        try {
            OneSignalStateSynchronizer.updatePushState(new JSONObject().put("parent_player_id", (Object)emailUserId));
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    static void updateOnSessionDependents() {
        OneSignal.sendAsSession = false;
        setLastSessionTime(System.currentTimeMillis());
    }
    
    static void updateUserIdDependents(final String userId) {
        saveUserId(userId);
        fireIdsAvailableCallback();
        internalFireGetTagsCallback(OneSignal.pendingGetTagsHandler);
        getCurrentSubscriptionState(OneSignal.appContext).setUserId(userId);
        if (OneSignal.iapUpdateJob != null) {
            sendPurchases(OneSignal.iapUpdateJob.toReport, OneSignal.iapUpdateJob.newAsExisting, OneSignal.iapUpdateJob.restResponseHandler);
            OneSignal.iapUpdateJob = null;
        }
        OneSignalStateSynchronizer.refreshEmailState();
        OneSignalChromeTab.setup(OneSignal.appContext, OneSignal.appId, userId, AdvertisingIdProviderGPS.getLastValue());
    }
    
    public static boolean userProvidedPrivacyConsent() {
        return getSavedUserConsentStatus();
    }
    
    public static class Builder
    {
        Context mContext;
        boolean mDisableGmsMissingPrompt;
        OSInFocusDisplayOption mDisplayOption;
        boolean mDisplayOptionCarryOver;
        boolean mFilterOtherGCMReceivers;
        NotificationOpenedHandler mNotificationOpenedHandler;
        NotificationReceivedHandler mNotificationReceivedHandler;
        boolean mPromptLocation;
        boolean mUnsubscribeWhenNotificationsAreDisabled;
        
        private Builder() {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
        }
        
        private Builder(final Context mContext) {
            this.mDisplayOption = OSInFocusDisplayOption.InAppAlert;
            this.mContext = mContext;
        }
        
        private void setDisplayOptionCarryOver(final boolean mDisplayOptionCarryOver) {
            this.mDisplayOptionCarryOver = mDisplayOptionCarryOver;
        }
        
        public Builder autoPromptLocation(final boolean mPromptLocation) {
            this.mPromptLocation = mPromptLocation;
            return this;
        }
        
        public Builder disableGmsMissingPrompt(final boolean mDisableGmsMissingPrompt) {
            this.mDisableGmsMissingPrompt = mDisableGmsMissingPrompt;
            return this;
        }
        
        public Builder filterOtherGCMReceivers(final boolean mFilterOtherGCMReceivers) {
            this.mFilterOtherGCMReceivers = mFilterOtherGCMReceivers;
            return this;
        }
        
        public Builder inFocusDisplaying(final OSInFocusDisplayOption mDisplayOption) {
            OneSignal.getCurrentOrNewInitBuilder().mDisplayOptionCarryOver = false;
            this.mDisplayOption = mDisplayOption;
            return this;
        }
        
        public void init() {
            init(this);
        }
        
        public Builder setNotificationOpenedHandler(final NotificationOpenedHandler mNotificationOpenedHandler) {
            this.mNotificationOpenedHandler = mNotificationOpenedHandler;
            return this;
        }
        
        public Builder setNotificationReceivedHandler(final NotificationReceivedHandler mNotificationReceivedHandler) {
            this.mNotificationReceivedHandler = mNotificationReceivedHandler;
            return this;
        }
        
        public Builder unsubscribeWhenNotificationsAreDisabled(final boolean mUnsubscribeWhenNotificationsAreDisabled) {
            this.mUnsubscribeWhenNotificationsAreDisabled = mUnsubscribeWhenNotificationsAreDisabled;
            return this;
        }
    }
    
    public interface ChangeTagsUpdateHandler
    {
        void onFailure(final SendTagsError p0);
        
        void onSuccess(final JSONObject p0);
    }
    
    public enum EmailErrorType
    {
        INVALID_OPERATION, 
        NETWORK, 
        REQUIRES_EMAIL_AUTH, 
        VALIDATION;
    }
    
    public static class EmailUpdateError
    {
        private String message;
        private EmailErrorType type;
        
        EmailUpdateError(final EmailErrorType type, final String message) {
            this.type = type;
            this.message = message;
        }
        
        public String getMessage() {
            return this.message;
        }
        
        public EmailErrorType getType() {
            return this.type;
        }
    }
    
    public interface EmailUpdateHandler
    {
        void onFailure(final EmailUpdateError p0);
        
        void onSuccess();
    }
    
    public interface GetTagsHandler
    {
        void tagsAvailable(final JSONObject p0);
    }
    
    private static class IAPUpdateJob
    {
        boolean newAsExisting;
        OneSignalRestClient.ResponseHandler restResponseHandler;
        JSONArray toReport;
        
        IAPUpdateJob(final JSONArray toReport) {
            this.toReport = toReport;
        }
    }
    
    public interface IdsAvailableHandler
    {
        void idsAvailable(final String p0, final String p1);
    }
    
    public enum LOG_LEVEL
    {
        DEBUG, 
        ERROR, 
        FATAL, 
        INFO, 
        NONE, 
        VERBOSE, 
        WARN;
    }
    
    public interface NotificationOpenedHandler
    {
        void notificationOpened(final OSNotificationOpenResult p0);
    }
    
    public interface NotificationReceivedHandler
    {
        void notificationReceived(final OSNotification p0);
    }
    
    public enum OSInFocusDisplayOption
    {
        InAppAlert, 
        None, 
        Notification;
    }
    
    private static class PendingTaskRunnable implements Runnable
    {
        private Runnable innerTask;
        private long taskId;
        
        PendingTaskRunnable(final Runnable innerTask) {
            this.innerTask = innerTask;
        }
        
        @Override
        public void run() {
            this.innerTask.run();
            onTaskRan(this.taskId);
        }
    }
    
    public interface PostNotificationResponseHandler
    {
        void onFailure(final JSONObject p0);
        
        void onSuccess(final JSONObject p0);
    }
    
    public static class SendTagsError
    {
        private int code;
        private String message;
        
        SendTagsError(final int code, final String message) {
            this.message = message;
            this.code = code;
        }
        
        public int getCode() {
            return this.code;
        }
        
        public String getMessage() {
            return this.message;
        }
    }
}
