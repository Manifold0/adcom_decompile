// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import java.io.Serializable;
import android.content.Intent;
import android.app.Notification;
import android.os.Build$VERSION;
import android.support.v4.app.NotificationCompat$Builder;
import android.content.Context;

public class NotificationFactory
{
    public static final String DEFAULT_CHANNEL = "com.deltadna.default";
    protected final Context context;
    
    public NotificationFactory(final Context context) {
        this.context = context;
    }
    
    public NotificationCompat$Builder configure(final NotificationCompat$Builder notificationCompat$Builder, final PushMessage pushMessage) {
        if (Build$VERSION.SDK_INT >= 26) {
            notificationCompat$Builder.setChannelId(this.getChannel().getId());
        }
        return notificationCompat$Builder.setSmallIcon(pushMessage.icon).setContentTitle((CharSequence)pushMessage.title).setContentText((CharSequence)pushMessage.message).setAutoCancel(true);
    }
    
    @Nullable
    public Notification create(final NotificationCompat$Builder notificationCompat$Builder, final NotificationInfo notificationInfo) {
        notificationCompat$Builder.setContentIntent(PendingIntent.getBroadcast(this.context, 0, new Intent("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_OPENED").setPackage(this.context.getPackageName()).setClass(this.context, (Class)NotificationInteractionReceiver.class).putExtra("notification_info", (Serializable)notificationInfo), 134217728));
        notificationCompat$Builder.setDeleteIntent(PendingIntent.getBroadcast(this.context, 0, new Intent("com.deltadna.android.sdk.notifications.internal.NOTIFICATION_DISMISSED").setPackage(this.context.getPackageName()).setClass(this.context, (Class)NotificationInteractionReceiver.class).putExtra("notification_info", (Serializable)notificationInfo), 134217728));
        return notificationCompat$Builder.build();
    }
    
    @TargetApi(26)
    protected NotificationChannel getChannel() {
        final NotificationChannel notificationChannel = new NotificationChannel("com.deltadna.default", (CharSequence)this.context.getString(R$string.ddna_notification_channel_name), 3);
        ((NotificationManager)this.context.getSystemService("notification")).createNotificationChannel(notificationChannel);
        return notificationChannel;
    }
}
