package com.facebook.appevents.internal;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.SourceApplicationInfo.Factory;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityLifecycleTracker {
    private static final String INCORRECT_IMPL_WARNING = "Unexpected activity pause without a matching activity resume. Logging data may be incorrect. Make sure you call activateApp from your Application's onCreate method";
    private static final long INTERRUPTION_THRESHOLD_MILLISECONDS = 1000;
    private static final String TAG = ActivityLifecycleTracker.class.getCanonicalName();
    private static String appId;
    private static long currentActivityAppearTime;
    private static volatile ScheduledFuture currentFuture;
    private static final Object currentFutureLock = new Object();
    private static volatile SessionInfo currentSession;
    private static AtomicInteger foregroundActivityCount = new AtomicInteger(0);
    private static final ScheduledExecutorService singleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
    private static AtomicBoolean tracking = new AtomicBoolean(false);

    /* renamed from: com.facebook.appevents.internal.ActivityLifecycleTracker$1 */
    static class C11891 implements ActivityLifecycleCallbacks {
        C11891() {
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityCreated");
            AppEventUtility.assertIsMainThread();
            ActivityLifecycleTracker.onActivityCreated(activity);
        }

        public void onActivityStarted(Activity activity) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStarted");
        }

        public void onActivityResumed(Activity activity) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityResumed");
            AppEventUtility.assertIsMainThread();
            ActivityLifecycleTracker.onActivityResumed(activity);
        }

        public void onActivityPaused(Activity activity) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityPaused");
            AppEventUtility.assertIsMainThread();
            ActivityLifecycleTracker.onActivityPaused(activity);
        }

        public void onActivityStopped(Activity activity) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityStopped");
            AppEventsLogger.onContextStop();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivitySaveInstanceState");
        }

        public void onActivityDestroyed(Activity activity) {
            Logger.log(LoggingBehavior.APP_EVENTS, ActivityLifecycleTracker.TAG, "onActivityDestroyed");
        }
    }

    public static void startTracking(Application application, String appId) {
        if (tracking.compareAndSet(false, true)) {
            appId = appId;
            application.registerActivityLifecycleCallbacks(new C11891());
        }
    }

    public static boolean isTracking() {
        return tracking.get();
    }

    public static UUID getCurrentSessionGuid() {
        return currentSession != null ? currentSession.getSessionId() : null;
    }

    public static void onActivityCreated(Activity activity) {
        final long currentTime = System.currentTimeMillis();
        final Context applicationContext = activity.getApplicationContext();
        final String activityName = Utility.getActivityName(activity);
        final SourceApplicationInfo sourceApplicationInfo = Factory.create(activity);
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    SessionInfo lastSession = SessionInfo.getStoredSessionInfo();
                    if (lastSession != null) {
                        SessionLogger.logDeactivateApp(applicationContext, activityName, lastSession, ActivityLifecycleTracker.appId);
                    }
                    ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTime), null);
                    ActivityLifecycleTracker.currentSession.setSourceApplicationInfo(sourceApplicationInfo);
                    SessionLogger.logActivateApp(applicationContext, activityName, sourceApplicationInfo, ActivityLifecycleTracker.appId);
                }
            }
        });
    }

    public static void onActivityResumed(Activity activity) {
        foregroundActivityCount.incrementAndGet();
        cancelCurrentTask();
        final long currentTime = System.currentTimeMillis();
        currentActivityAppearTime = currentTime;
        final Context applicationContext = activity.getApplicationContext();
        final String activityName = Utility.getActivityName(activity);
        singleThreadExecutor.execute(new Runnable() {
            public void run() {
                if (ActivityLifecycleTracker.currentSession == null) {
                    ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTime), null);
                    SessionLogger.logActivateApp(applicationContext, activityName, null, ActivityLifecycleTracker.appId);
                } else if (ActivityLifecycleTracker.currentSession.getSessionLastEventTime() != null) {
                    long suspendTime = currentTime - ActivityLifecycleTracker.currentSession.getSessionLastEventTime().longValue();
                    if (suspendTime > ((long) (ActivityLifecycleTracker.getSessionTimeoutInSeconds() * 1000))) {
                        SessionLogger.logDeactivateApp(applicationContext, activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                        SessionLogger.logActivateApp(applicationContext, activityName, null, ActivityLifecycleTracker.appId);
                        ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTime), null);
                    } else if (suspendTime > ActivityLifecycleTracker.INTERRUPTION_THRESHOLD_MILLISECONDS) {
                        ActivityLifecycleTracker.currentSession.incrementInterruptionCount();
                    }
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(currentTime));
                ActivityLifecycleTracker.currentSession.writeSessionToDisk();
            }
        });
    }

    private static void onActivityPaused(Activity activity) {
        if (foregroundActivityCount.decrementAndGet() < 0) {
            foregroundActivityCount.set(0);
            Log.w(TAG, INCORRECT_IMPL_WARNING);
        }
        cancelCurrentTask();
        final long currentTime = System.currentTimeMillis();
        final Context applicationContext = activity.getApplicationContext();
        final String activityName = Utility.getActivityName(activity);
        singleThreadExecutor.execute(new Runnable() {

            /* renamed from: com.facebook.appevents.internal.ActivityLifecycleTracker$4$1 */
            class C11921 implements Runnable {
                C11921() {
                }

                public void run() {
                    if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                        SessionLogger.logDeactivateApp(applicationContext, activityName, ActivityLifecycleTracker.currentSession, ActivityLifecycleTracker.appId);
                        SessionInfo.clearSavedSessionFromDisk();
                        ActivityLifecycleTracker.currentSession = null;
                    }
                    synchronized (ActivityLifecycleTracker.currentFutureLock) {
                        ActivityLifecycleTracker.currentFuture = null;
                    }
                }
            }

            public void run() {
                long timeSpentOnActivityInSeconds = 0;
                if (ActivityLifecycleTracker.currentSession == null) {
                    ActivityLifecycleTracker.currentSession = new SessionInfo(Long.valueOf(currentTime), null);
                }
                ActivityLifecycleTracker.currentSession.setSessionLastEventTime(Long.valueOf(currentTime));
                if (ActivityLifecycleTracker.foregroundActivityCount.get() <= 0) {
                    Runnable task = new C11921();
                    synchronized (ActivityLifecycleTracker.currentFutureLock) {
                        ActivityLifecycleTracker.currentFuture = ActivityLifecycleTracker.singleThreadExecutor.schedule(task, (long) ActivityLifecycleTracker.getSessionTimeoutInSeconds(), TimeUnit.SECONDS);
                    }
                }
                long appearTime = ActivityLifecycleTracker.currentActivityAppearTime;
                if (appearTime > 0) {
                    timeSpentOnActivityInSeconds = (currentTime - appearTime) / ActivityLifecycleTracker.INTERRUPTION_THRESHOLD_MILLISECONDS;
                }
                AutomaticAnalyticsLogger.logActivityTimeSpentEvent(activityName, timeSpentOnActivityInSeconds);
                ActivityLifecycleTracker.currentSession.writeSessionToDisk();
            }
        });
    }

    private static int getSessionTimeoutInSeconds() {
        FetchedAppSettings settings = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        if (settings == null) {
            return Constants.getDefaultAppEventsSessionTimeoutInSeconds();
        }
        return settings.getSessionTimeoutInSeconds();
    }

    private static void cancelCurrentTask() {
        synchronized (currentFutureLock) {
            if (currentFuture != null) {
                currentFuture.cancel(false);
            }
            currentFuture = null;
        }
    }
}
