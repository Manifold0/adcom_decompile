// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.support.v4.app.NotificationCompat$Builder;
import java.io.Serializable;
import android.content.Intent;
import java.util.Map;
import android.util.Log;
import java.util.Locale;
import com.google.firebase.messaging.RemoteMessage;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.app.NotificationManager;
import android.content.IntentFilter;
import com.google.firebase.messaging.FirebaseMessagingService;

public class NotificationListenerService extends FirebaseMessagingService
{
    protected static final String NOTIFICATION_TAG = "com.deltadna.android.sdk.notifications";
    protected static final IntentFilter RECEIVER_FILTER;
    private static final String TAG;
    protected NotificationFactory factory;
    protected NotificationManager manager;
    protected Bundle metaData;
    
    static {
        (RECEIVER_FILTER = new IntentFilter()).addAction("com.deltadna.android.sdk.notifications.NOTIFICATION_OPENED");
        NotificationListenerService.RECEIVER_FILTER.addAction("com.deltadna.android.sdk.notifications.NOTIFICATION_DISMISSED");
        TAG = "deltaDNA " + NotificationListenerService.class.getSimpleName();
    }
    
    protected NotificationFactory createFactory(final Context context) {
        return new NotificationFactory(context);
    }
    
    protected void notify(final long n, final Notification notification) {
        this.manager.notify("com.deltadna.android.sdk.notifications", (int)n, notification);
    }
    
    public void onCreate() {
        super.onCreate();
        this.metaData = MetaData.get((Context)this);
        this.manager = (NotificationManager)this.getSystemService("notification");
        this.factory = this.createFactory((Context)this);
    }
    
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        final String from = remoteMessage.getFrom();
        final Map data = remoteMessage.getData();
        Log.d(NotificationListenerService.TAG, String.format(Locale.US, "Received message %s from %s", data, from));
        if (from == null) {
            Log.w(NotificationListenerService.TAG, "Message sender is unknown");
        }
        else {
            if (!from.equals(this.getString(this.metaData.getInt("ddna_sender_id")))) {
                Log.d(NotificationListenerService.TAG, "Not handling message due to sender ID mismatch");
                return;
            }
            if (data == null || data.isEmpty()) {
                Log.w(NotificationListenerService.TAG, "Message data is null or empty");
                return;
            }
            final PushMessage pushMessage = new PushMessage((Context)this, remoteMessage.getFrom(), remoteMessage.getData());
            this.sendBroadcast(Utils.wrapWithReceiver((Context)this, new Intent("com.deltadna.android.sdk.notifications.MESSAGE_RECEIVED").setPackage(this.getPackageName()).putExtra("push_message", (Serializable)pushMessage)));
            final int n = (int)pushMessage.id;
            final NotificationInfo notificationInfo = new NotificationInfo(n, pushMessage);
            final Notification create = this.factory.create(this.factory.configure(new NotificationCompat$Builder((Context)this), pushMessage), notificationInfo);
            if (create != null) {
                this.notify(n, create);
                this.sendBroadcast(Utils.wrapWithReceiver((Context)this, new Intent("com.deltadna.android.sdk.notifications.NOTIFICATION_POSTED").setPackage(this.getPackageName()).putExtra("notification_info", (Serializable)notificationInfo)));
            }
        }
    }
}
