package com.deltadna.android.sdk.notifications;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public final class NotificationInteractionReceiver extends BroadcastReceiver {
    private static final short DELAY = (short) 5000;
    private static final String TAG = ("deltaDNA " + NotificationInteractionReceiver.class.getSimpleName());
    private final Handler notifier = new Handler(Looper.myLooper());

    private class Checker implements ActivityLifecycleCallbacks {
        final Intent intent;
        boolean launched;

        Checker(Intent intent) {
            this.intent = intent;
        }

        private boolean intentMatches(Activity activity) {
            Intent other = activity.getIntent();
            return (other == null || other.getComponent() == null || !other.getComponent().equals(this.intent.getComponent()) || other.getAction() == null || !other.getAction().equals(this.intent.getAction())) ? false : true;
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (intentMatches(activity)) {
                this.launched = true;
            }
        }

        public void onActivityStarted(Activity activity) {
            if (intentMatches(activity)) {
                this.launched = true;
            }
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received " + intent);
        String action = intent.getAction();
        if (action != null) {
            Intent intentCopy = new Intent();
            boolean z = true;
            switch (action.hashCode()) {
                case -1535079477:
                    if (action.equals("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_DISMISSED")) {
                        z = true;
                        break;
                    }
                    break;
                case 1549966439:
                    if (action.equals("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_OPENED")) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    intentCopy.setAction("com.deltadna.android.sdk.notifications.NOTIFICATION_OPENED");
                    if (MetaData.get(context).containsKey("ddna_start_launch_intent")) {
                        Log.w(TAG, "Use of ddna_start_launch_intent in the manifest has been deprecated");
                    }
                    if (MetaData.get(context).getBoolean("ddna_start_launch_intent", true)) {
                        final NotificationInfo info = (NotificationInfo) intent.getSerializableExtra("notification_info");
                        if (info == null) {
                            Log.w(TAG, "Failed to find/deserialise notification info");
                        } else {
                            intentCopy.putExtra("notification_info", info);
                        }
                        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                        if (info != null) {
                            final Checker checker = new Checker(launchIntent);
                            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(checker);
                            this.notifier.postDelayed(new Runnable() {
                                public void run() {
                                    Log.d(NotificationInteractionReceiver.TAG, "Notifying SDK of notification opening");
                                    DDNANotifications.recordNotificationOpened(Utils.convert(info.message.data), checker.launched);
                                }
                            }, 5000);
                        }
                        Log.d(TAG, "Starting activity with launch intent");
                        context.startActivity(launchIntent);
                        break;
                    }
                    break;
                case true:
                    intentCopy.setAction("com.deltadna.android.sdk.notifications.NOTIFICATION_DISMISSED");
                    Log.d(TAG, "Notification has been dismissed");
                    break;
                default:
                    Log.d(TAG, "Ignoring " + action);
                    return;
            }
            context.sendBroadcast(Utils.wrapWithReceiver(context, intentCopy));
            return;
        }
        Log.w(TAG, "Null action");
    }
}
