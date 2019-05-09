// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Looper;
import android.os.Handler;
import android.os.HandlerThread;
import android.app.Activity;

class ActivityLifecycleHandler
{
    static Activity curActivity;
    static FocusHandlerThread focusHandlerThread;
    private static ActivityAvailableListener mActivityAvailableListener;
    static boolean nextResumeIsFirstActivity;
    
    static {
        ActivityLifecycleHandler.focusHandlerThread = new FocusHandlerThread();
    }
    
    private static void handleFocus() {
        if (ActivityLifecycleHandler.focusHandlerThread.hasBackgrounded() || ActivityLifecycleHandler.nextResumeIsFirstActivity) {
            ActivityLifecycleHandler.nextResumeIsFirstActivity = false;
            ActivityLifecycleHandler.focusHandlerThread.resetBackgroundState();
            OneSignal.onAppFocus();
            return;
        }
        ActivityLifecycleHandler.focusHandlerThread.stopScheduledRunnable();
    }
    
    private static void handleLostFocus() {
        ActivityLifecycleHandler.focusHandlerThread.runRunnable(new AppFocusRunnable());
    }
    
    private static void logCurActivity() {
        final OneSignal.LOG_LEVEL debug = OneSignal.LOG_LEVEL.DEBUG;
        final StringBuilder append = new StringBuilder().append("curActivity is NOW: ");
        String string;
        if (ActivityLifecycleHandler.curActivity != null) {
            string = "" + ActivityLifecycleHandler.curActivity.getClass().getName() + ":" + ActivityLifecycleHandler.curActivity;
        }
        else {
            string = "null";
        }
        OneSignal.Log(debug, append.append(string).toString());
    }
    
    static void onActivityCreated(final Activity activity) {
    }
    
    static void onActivityDestroyed(final Activity activity) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "onActivityDestroyed: " + activity.getClass().getName());
        if (activity == ActivityLifecycleHandler.curActivity) {
            ActivityLifecycleHandler.curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }
    
    static void onActivityPaused(final Activity activity) {
        if (activity == ActivityLifecycleHandler.curActivity) {
            ActivityLifecycleHandler.curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }
    
    static void onActivityResumed(final Activity curActivity) {
        setCurActivity(curActivity);
        logCurActivity();
        handleFocus();
    }
    
    static void onActivityStarted(final Activity activity) {
    }
    
    static void onActivityStopped(final Activity activity) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "onActivityStopped: " + activity.getClass().getName());
        if (activity == ActivityLifecycleHandler.curActivity) {
            ActivityLifecycleHandler.curActivity = null;
            handleLostFocus();
        }
        logCurActivity();
    }
    
    public static void removeActivityAvailableListener(final ActivityAvailableListener activityAvailableListener) {
        ActivityLifecycleHandler.mActivityAvailableListener = null;
    }
    
    static void setActivityAvailableListener(final ActivityAvailableListener activityAvailableListener) {
        if (ActivityLifecycleHandler.curActivity != null) {
            activityAvailableListener.available(ActivityLifecycleHandler.curActivity);
            ActivityLifecycleHandler.mActivityAvailableListener = activityAvailableListener;
            return;
        }
        ActivityLifecycleHandler.mActivityAvailableListener = activityAvailableListener;
    }
    
    private static void setCurActivity(final Activity curActivity) {
        ActivityLifecycleHandler.curActivity = curActivity;
        if (ActivityLifecycleHandler.mActivityAvailableListener != null) {
            ActivityLifecycleHandler.mActivityAvailableListener.available(ActivityLifecycleHandler.curActivity);
        }
    }
    
    interface ActivityAvailableListener
    {
        void available(final Activity p0);
    }
    
    private static class AppFocusRunnable implements Runnable
    {
        private boolean backgrounded;
        private boolean completed;
        
        @Override
        public void run() {
            if (ActivityLifecycleHandler.curActivity != null) {
                return;
            }
            this.backgrounded = true;
            OneSignal.onAppLostFocus();
            this.completed = true;
        }
    }
    
    static class FocusHandlerThread extends HandlerThread
    {
        private AppFocusRunnable appFocusRunnable;
        Handler mHandler;
        
        FocusHandlerThread() {
            super("FocusHandlerThread");
            this.mHandler = null;
            this.start();
            this.mHandler = new Handler(this.getLooper());
        }
        
        Looper getHandlerLooper() {
            return this.mHandler.getLooper();
        }
        
        boolean hasBackgrounded() {
            return this.appFocusRunnable != null && this.appFocusRunnable.backgrounded;
        }
        
        void resetBackgroundState() {
            if (this.appFocusRunnable != null) {
                this.appFocusRunnable.backgrounded = false;
            }
        }
        
        void runRunnable(final AppFocusRunnable appFocusRunnable) {
            if (this.appFocusRunnable != null && this.appFocusRunnable.backgrounded && !this.appFocusRunnable.completed) {
                return;
            }
            this.appFocusRunnable = appFocusRunnable;
            this.mHandler.removeCallbacksAndMessages((Object)null);
            this.mHandler.postDelayed((Runnable)appFocusRunnable, 2000L);
        }
        
        void stopScheduledRunnable() {
            this.mHandler.removeCallbacksAndMessages((Object)null);
        }
    }
}
