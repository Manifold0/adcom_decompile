package com.deltadna.android.sdk.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.onesignal.OneSignalDbContract.NotificationTable;
import java.util.Locale;
import java.util.Map;

public class NotificationListenerService extends FirebaseMessagingService {
    protected static final String NOTIFICATION_TAG = "com.deltadna.android.sdk.notifications";
    protected static final IntentFilter RECEIVER_FILTER = new IntentFilter();
    private static final String TAG = ("deltaDNA " + NotificationListenerService.class.getSimpleName());
    protected NotificationFactory factory;
    protected NotificationManager manager;
    protected Bundle metaData;

    static {
        RECEIVER_FILTER.addAction("com.deltadna.android.sdk.notifications.NOTIFICATION_OPENED");
        RECEIVER_FILTER.addAction("com.deltadna.android.sdk.notifications.NOTIFICATION_DISMISSED");
    }

    public void onCreate() {
        super.onCreate();
        this.metaData = MetaData.get(this);
        this.manager = (NotificationManager) getSystemService(NotificationTable.TABLE_NAME);
        this.factory = createFactory(this);
    }

    public void onMessageReceived(RemoteMessage message) {
        String from = message.getFrom();
        Map<String, String> data = message.getData();
        Log.d(TAG, String.format(Locale.US, "Received message %s from %s", new Object[]{data, from}));
        if (from == null) {
            Log.w(TAG, "Message sender is unknown");
        } else if (!from.equals(getString(this.metaData.getInt("ddna_sender_id")))) {
            Log.d(TAG, "Not handling message due to sender ID mismatch");
        } else if (data == null || data.isEmpty()) {
            Log.w(TAG, "Message data is null or empty");
        } else {
            PushMessage pushMessage = new PushMessage(this, message.getFrom(), message.getData());
            sendBroadcast(Utils.wrapWithReceiver(this, new Intent("com.deltadna.android.sdk.notifications.MESSAGE_RECEIVED").setPackage(getPackageName()).putExtra("push_message", pushMessage)));
            int id = (int) pushMessage.id;
            NotificationInfo info = new NotificationInfo(id, pushMessage);
            Notification notification = this.factory.create(this.factory.configure(new Builder(this), pushMessage), info);
            if (notification != null) {
                notify((long) id, notification);
                sendBroadcast(Utils.wrapWithReceiver(this, new Intent("com.deltadna.android.sdk.notifications.NOTIFICATION_POSTED").setPackage(getPackageName()).putExtra("notification_info", info)));
            }
        }
    }

    protected NotificationFactory createFactory(Context context) {
        return new NotificationFactory(context);
    }

    protected void notify(long id, Notification notification) {
        this.manager.notify("com.deltadna.android.sdk.notifications", (int) id, notification);
    }
}
