package com.deltadna.android.sdk.notifications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Builder;
import com.onesignal.OneSignalDbContract.NotificationTable;

public class NotificationFactory {
    public static final String DEFAULT_CHANNEL = "com.deltadna.default";
    protected final Context context;

    public NotificationFactory(Context context) {
        this.context = context;
    }

    public Builder configure(Builder builder, PushMessage message) {
        if (VERSION.SDK_INT >= 26) {
            builder.setChannelId(getChannel().getId());
        }
        return builder.setSmallIcon(message.icon).setContentTitle(message.title).setContentText(message.message).setAutoCancel(true);
    }

    @Nullable
    public Notification create(Builder builder, NotificationInfo info) {
        builder.setContentIntent(PendingIntent.getBroadcast(this.context, 0, new Intent("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_OPENED").setPackage(this.context.getPackageName()).setClass(this.context, NotificationInteractionReceiver.class).putExtra("notification_info", info), 134217728));
        builder.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, new Intent("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_DISMISSED").setPackage(this.context.getPackageName()).setClass(this.context, NotificationInteractionReceiver.class).putExtra("notification_info", info), 134217728));
        return builder.build();
    }

    @TargetApi(26)
    protected NotificationChannel getChannel() {
        NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL, this.context.getString(C0936R.string.ddna_notification_channel_name), 3);
        ((NotificationManager) this.context.getSystemService(NotificationTable.TABLE_NAME)).createNotificationChannel(channel);
        return channel;
    }
}
