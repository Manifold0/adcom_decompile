// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.app.Application;
import java.io.Serializable;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.os.Looper;
import android.os.Handler;
import android.content.BroadcastReceiver;

public final class NotificationInteractionReceiver extends BroadcastReceiver
{
    private static final short DELAY = 5000;
    private static final String TAG;
    private final Handler notifier;
    
    static {
        TAG = "deltaDNA " + NotificationInteractionReceiver.class.getSimpleName();
    }
    
    public NotificationInteractionReceiver() {
        this.notifier = new Handler(Looper.myLooper());
    }
    
    public void onReceive(final Context context, final Intent intent) {
        Log.d(NotificationInteractionReceiver.TAG, "Received " + intent);
        final String action = intent.getAction();
        if (action != null) {
            final Intent intent2 = new Intent();
            switch (action) {
                default: {
                    Log.d(NotificationInteractionReceiver.TAG, "Ignoring " + action);
                    return;
                }
                case "com.deltadna.android.sdk.notifications.internal.NOTIFICATION_OPENED": {
                    intent2.setAction("com.deltadna.android.sdk.notifications.NOTIFICATION_OPENED");
                    if (MetaData.get(context).containsKey("ddna_start_launch_intent")) {
                        Log.w(NotificationInteractionReceiver.TAG, "Use of ddna_start_launch_intent in the manifest has been deprecated");
                    }
                    if (MetaData.get(context).getBoolean("ddna_start_launch_intent", true)) {
                        final NotificationInfo notificationInfo = (NotificationInfo)intent.getSerializableExtra("notification_info");
                        if (notificationInfo == null) {
                            Log.w(NotificationInteractionReceiver.TAG, "Failed to find/deserialise notification info");
                        }
                        else {
                            intent2.putExtra("notification_info", (Serializable)notificationInfo);
                        }
                        final Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                        if (notificationInfo != null) {
                            final Checker checker = new Checker(launchIntentForPackage);
                            ((Application)context.getApplicationContext()).registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)checker);
                            this.notifier.postDelayed((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    Log.d(NotificationInteractionReceiver.TAG, "Notifying SDK of notification opening");
                                    DDNANotifications.recordNotificationOpened(Utils.convert(notificationInfo.message.data), checker.launched);
                                }
                            }, 5000L);
                        }
                        Log.d(NotificationInteractionReceiver.TAG, "Starting activity with launch intent");
                        context.startActivity(launchIntentForPackage);
                        break;
                    }
                    break;
                }
                case "com.deltadna.android.sdk.notifications.internal.NOTIFICATION_DISMISSED": {
                    intent2.setAction("com.deltadna.android.sdk.notifications.NOTIFICATION_DISMISSED");
                    Log.d(NotificationInteractionReceiver.TAG, "Notification has been dismissed");
                    break;
                }
            }
            context.sendBroadcast(Utils.wrapWithReceiver(context, intent2));
            return;
        }
        Log.w(NotificationInteractionReceiver.TAG, "Null action");
    }
    
    private class Checker implements Application$ActivityLifecycleCallbacks
    {
        final Intent intent;
        boolean launched;
        
        Checker(final Intent intent) {
            this.intent = intent;
        }
        
        private boolean intentMatches(final Activity activity) {
            final Intent intent = activity.getIntent();
            return intent != null && intent.getComponent() != null && intent.getComponent().equals((Object)this.intent.getComponent()) && intent.getAction() != null && intent.getAction().equals(this.intent.getAction());
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            if (this.intentMatches(activity)) {
                this.launched = true;
            }
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
        }
        
        public void onActivityResumed(final Activity activity) {
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            if (this.intentMatches(activity)) {
                this.launched = true;
            }
        }
        
        public void onActivityStopped(final Activity activity) {
        }
    }
}
