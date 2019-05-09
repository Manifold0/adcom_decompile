// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import java.io.Serializable;
import android.text.TextUtils;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public abstract class EventReceiver extends BroadcastReceiver
{
    private static final String TAG;
    
    static {
        TAG = "deltaDNA " + EventReceiver.class.getSimpleName();
    }
    
    protected void onMessageReceived(final Context context, final PushMessage pushMessage) {
    }
    
    protected void onNotificationDismissed(final Context context, final NotificationInfo notificationInfo) {
    }
    
    protected void onNotificationOpened(final Context context, final NotificationInfo notificationInfo) {
    }
    
    protected void onNotificationPosted(final Context context, final NotificationInfo notificationInfo) {
    }
    
    public final void onReceive(final Context context, final Intent intent) {
        if (intent != null && intent.getAction() != null) {
            Serializable serializableExtra;
            if (intent.hasExtra("notification_info")) {
                serializableExtra = intent.getSerializableExtra("notification_info");
            }
            else {
                serializableExtra = null;
            }
            final NotificationInfo notificationInfo = (NotificationInfo)serializableExtra;
            final String action = intent.getAction();
            switch (action) {
                default: {
                    Log.w(EventReceiver.TAG, "Unknown action: " + intent.getAction());
                    break;
                }
                case "com.deltadna.android.sdk.notifications.REGISTERED": {
                    final String stringExtra = intent.getStringExtra("registration_token");
                    if (TextUtils.isEmpty((CharSequence)stringExtra)) {
                        Log.w(EventReceiver.TAG, "Registration token is null or empty");
                        return;
                    }
                    this.onRegistered(context, stringExtra);
                }
                case "com.deltadna.android.sdk.notifications.REGISTRATION_FAILED": {
                    final Throwable t = (Throwable)intent.getSerializableExtra("registration_failure_reason");
                    if (t == null) {
                        Log.w(EventReceiver.TAG, "Failed to deserialise registration failure reason");
                        return;
                    }
                    this.onRegistrationFailed(context, t);
                }
                case "com.deltadna.android.sdk.notifications.MESSAGE_RECEIVED": {
                    final PushMessage pushMessage = (PushMessage)intent.getSerializableExtra("push_message");
                    if (pushMessage == null) {
                        Log.w(EventReceiver.TAG, "Failed to find or deserialise push message");
                        return;
                    }
                    this.onMessageReceived(context, pushMessage);
                }
                case "com.deltadna.android.sdk.notifications.NOTIFICATION_POSTED": {
                    if (notificationInfo == null) {
                        Log.w(EventReceiver.TAG, "Failed to find or deserialise notification info");
                        return;
                    }
                    this.onNotificationPosted(context, notificationInfo);
                }
                case "com.deltadna.android.sdk.notifications.NOTIFICATION_OPENED": {
                    if (notificationInfo == null) {
                        Log.w(EventReceiver.TAG, "Failed to find or deserialise notification info");
                        return;
                    }
                    this.onNotificationOpened(context, notificationInfo);
                }
                case "com.deltadna.android.sdk.notifications.NOTIFICATION_DISMISSED": {
                    if (notificationInfo == null) {
                        Log.w(EventReceiver.TAG, "Failed to find or deserialise notification info");
                        return;
                    }
                    this.onNotificationDismissed(context, notificationInfo);
                }
            }
        }
    }
    
    protected void onRegistered(final Context context, final String s) {
    }
    
    protected void onRegistrationFailed(final Context context, final Throwable t) {
    }
}
