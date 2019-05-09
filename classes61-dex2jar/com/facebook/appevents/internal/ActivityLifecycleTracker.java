// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Logger;
import com.facebook.LoggingBehavior;
import android.os.Bundle;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import android.content.Context;
import com.facebook.internal.Utility;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.FacebookSdk;
import java.util.UUID;
import android.app.Activity;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledFuture;

public class ActivityLifecycleTracker
{
    private static final String INCORRECT_IMPL_WARNING = "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method";
    private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000L;
    private static final String TAG;
    private static String appId;
    private static long currentActivityAppearTime;
    private static volatile ScheduledFuture currentFuture;
    private static final Object currentFutureLock;
    private static volatile SessionInfo currentSession;
    private static AtomicInteger foregroundActivityCount;
    private static final ScheduledExecutorService singleThreadExecutor;
    private static AtomicBoolean tracking;
    
    static {
        TAG = ActivityLifecycleTracker.class.getCanonicalName();
        singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
        currentFutureLock = new Object();
        ActivityLifecycleTracker.foregroundActivityCount = new AtomicInteger(0);
        ActivityLifecycleTracker.tracking = new AtomicBoolean(false);
    }
    
    private static void cancelCurrentTask() {
        synchronized (ActivityLifecycleTracker.currentFutureLock) {
            if (ActivityLifecycleTracker.currentFuture != null) {
                ActivityLifecycleTracker.currentFuture.cancel(false);
            }
            ActivityLifecycleTracker.currentFuture = null;
        }
    }
    
    public static UUID getCurrentSessionGuid() {
        if (ActivityLifecycleTracker.currentSession != null) {
            return ActivityLifecycleTracker.currentSession.getSessionId();
        }
        return null;
    }
    
    private static int getSessionTimeoutInSeconds() {
        final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (appSettingsWithoutQuery == null) {
            return Constants.getDefaultAppEventsSessionTimeoutInSeconds();
        }
        return appSettingsWithoutQuery.getSessionTimeoutInSeconds();
    }
    
    public static boolean isTracking() {
        return ActivityLifecycleTracker.tracking.get();
    }
    
    public static void onActivityCreated(final Activity activity) {
        ActivityLifecycleTracker.singleThreadExecutor.execute(new Runnable() {
            final /* synthetic */ String val$activityName = Utility.getActivityName((Context)activity);
            final /* synthetic */ Context val$applicationContext = activity.getApplicationContext();
            final /* synthetic */ long val$currentTime = System.currentTimeMillis();
            final /* synthetic */ SourceApplicationInfo val$sourceApplicationInfo = SourceApplicationInfo.Factory.create(activity);
            
            @Override
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    final SessionInfo storedSessionInfo = SessionInfo.getStoredSessionInfo();
                    if (storedSessionInfo != null) {
                        SessionLogger.logDeactivateApp(this.val$applicationContext, this.val$activityName, storedSessionInfo, ActivityLifecycleTracker.appId);
                    }
                    ActivityLifecycleTracker.currentSession = new SessionInfo(this.val$currentTime, null);
                    ActivityLifecycleTracker.currentSession.setSourceApplicationInfo(this.val$sourceApplicationInfo);
                    SessionLogger.logActivateApp(this.val$applicationContext, this.val$activityName, this.val$sourceApplicationInfo, ActivityLifecycleTracker.appId);
                }
            }
        });
    }
    
    private static void onActivityPaused(final Activity activity) {
        if (ActivityLifecycleTracker.foregroundActivityCount.decrementAndGet() < 0) {
            ActivityLifecycleTracker.foregroundActivityCount.set(0);
            Log.w(ActivityLifecycleTracker.TAG, "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method");
        }
        cancelCurrentTask();
        ActivityLifecycleTracker.singleThreadExecutor.execute(new Runnable() {
            final /* synthetic */ String val$activityName = Utility.getActivityName((Context)activity);
            final /* synthetic */ Context val$applicationContext = activity.getApplicationContext();
            final /* synthetic */ long val$currentTime = System.currentTimeMillis();
            
            @Override
            public void run() {
                long n = 0L;
                if (ActivityLifecycleTracker.currentSession == null) {
                    ActivityLifecycleTracker.currentSession = new SessionInfo(this.val$currentTime, null);
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(this.val$currentTime);
                Label_0091: {
                    if (ActivityLifecycleTracker.foregroundActivityCount.get() > 0) {
                        break Label_0091;
                    }
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                                SessionLogger.logDeactivateApp(Runnable.this.val$applicationContext, Runnable.this.val$activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                                SessionInfo.clearSavedSessionFromDisk();
                                ActivityLifecycleTracker.currentSession = null;
                            }
                            synchronized (ActivityLifecycleTracker.currentFutureLock) {
                                ActivityLifecycleTracker.currentFuture = null;
                            }
                        }
                    };
                    synchronized (ActivityLifecycleTracker.currentFutureLock) {
                        ActivityLifecycleTracker.currentFuture = ActivityLifecycleTracker.singleThreadExecutor.schedule(runnable, getSessionTimeoutInSeconds(), TimeUnit.SECONDS);
                        // monitorexit(ActivityLifecycleTracker.access$600())
                        final long access$900 = ActivityLifecycleTracker.currentActivityAppearTime;
                        if (access$900 > 0L) {
                            n = (this.val$currentTime - access$900) / 1000L;
                        }
                        AutomaticAnalyticsLogger.logActivityTimeSpentEvent(this.val$activityName, n);
                        ActivityLifecycleTracker.currentSession.writeSessionToDisk();
                    }
                }
            }
        });
    }
    
    public static void onActivityResumed(final Activity activity) {
        ActivityLifecycleTracker.foregroundActivityCount.incrementAndGet();
        cancelCurrentTask();
        ActivityLifecycleTracker.singleThreadExecutor.execute(new Runnable() {
            final /* synthetic */ String val$activityName = Utility.getActivityName((Context)activity);
            final /* synthetic */ Context val$applicationContext = activity.getApplicationContext();
            final /* synthetic */ long val$currentTime = ActivityLifecycleTracker.currentActivityAppearTime = System.currentTimeMillis();
            
            @Override
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    ActivityLifecycleTracker.currentSession = new SessionInfo(this.val$currentTime, null);
                    SessionLogger.logActivateApp(this.val$applicationContext, this.val$activityName, null, ActivityLifecycleTracker.appId);
                }
                else if (ActivityLifecycleTracker.currentSession.getSessionLastEventTime() != null) {
                    final long n = this.val$currentTime - ActivityLifecycleTracker.currentSession.getSessionLastEventTime();
                    if (n > getSessionTimeoutInSeconds() * 1000) {
                        SessionLogger.logDeactivateApp(this.val$applicationContext, this.val$activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                        SessionLogger.logActivateApp(this.val$applicationContext, this.val$activityName, null, ActivityLifecycleTracker.appId);
                        ActivityLifecycleTracker.currentSession = new SessionInfo(this.val$currentTime, null);
                    }
                    else if (n > 1000L) {
                        ActivityLifecycleTracker.currentSession.incrementInterruptionCount();
                    }
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(this.val$currentTime);
                ActivityLifecycleTracker.currentSession.writeSessionToDisk();
            }
        });
    }
    
    public static void startTracking(final Application application, final String appId) {
        if (!ActivityLifecycleTracker.tracking.compareAndSet(false, true)) {
            return;
        }
        ActivityLifecycleTracker.appId = appId;
        application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new Application$ActivityLifecycleCallbacks() {
            public void onActivityCreated(final Activity activity, final Bundle bundle) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityCreated");
                AppEventUtility.assertIsMainThread();
                ActivityLifecycleTracker.onActivityCreated(activity);
            }
            
            public void onActivityDestroyed(final Activity activity) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityDestroyed");
            }
            
            public void onActivityPaused(final Activity activity) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityPaused");
                AppEventUtility.assertIsMainThread();
                onActivityPaused(activity);
            }
            
            public void onActivityResumed(final Activity activity) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityResumed");
                AppEventUtility.assertIsMainThread();
                ActivityLifecycleTracker.onActivityResumed(activity);
            }
            
            public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivitySaveInstanceState");
            }
            
            public void onActivityStarted(final Activity activity) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStarted");
            }
            
            public void onActivityStopped(final Activity activity) {
                Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStopped");
                AppEventsLogger.onContextStop();
            }
        });
    }
}
