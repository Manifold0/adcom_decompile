// 
// Decompiled by Procyon v0.5.34
// 

package com.hippogames.simpleandroidnotifications;

import android.app.Notification$InboxStyle;
import android.service.notification.StatusBarNotification;
import java.util.ArrayList;
import android.app.NotificationChannel;
import android.app.Activity;
import android.app.AlarmManager;
import java.util.Iterator;
import com.unity3d.player.UnityPlayer;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.media.RingtoneManager;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.os.Build$VERSION;
import android.app.PendingIntent;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class Controller extends BroadcastReceiver
{
    private Notification BuildNotification(final Context context, final NotificationParams notificationParams, final Intent intent, final NotificationManager notificationManager) {
        final PendingIntent activity = PendingIntent.getActivity(context, notificationParams.Id, intent, 134217728);
        final Resources resources = context.getResources();
        Notification$Builder notification$Builder2;
        if (Build$VERSION.SDK_INT >= 26) {
            final Notification$Builder notification$Builder = notification$Builder2 = new Notification$Builder(context, notificationParams.ChannelId);
            if (notificationManager.getNotificationChannel(notificationParams.ChannelId) == null) {
                this.CreateChannel(notificationParams, notificationManager);
                notification$Builder2 = notification$Builder;
            }
        }
        else {
            notification$Builder2 = new Notification$Builder(context);
        }
        notification$Builder2.setContentIntent(activity).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentTitle((CharSequence)notificationParams.Title).setContentText((CharSequence)notificationParams.Message);
        if (notificationParams.Multiline && Build$VERSION.SDK_INT >= 16) {
            notification$Builder2.setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)notificationParams.Message));
        }
        if (!IsEmpty(notificationParams.GroupName) && Build$VERSION.SDK_INT >= 20) {
            notification$Builder2.setGroup(notificationParams.GroupName);
        }
        if (notificationParams.Sound) {
            if (IsEmpty(notificationParams.CustomSound)) {
                notification$Builder2.setSound(RingtoneManager.getDefaultUri(2));
            }
            else {
                notification$Builder2.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + notificationParams.CustomSound));
            }
        }
        if (Build$VERSION.SDK_INT >= 21) {
            notification$Builder2.setColor(notificationParams.SmallIconColor);
        }
        if (!IsEmpty(notificationParams.Ticker)) {
            notification$Builder2.setTicker((CharSequence)notificationParams.Ticker);
        }
        if (!IsEmpty(notificationParams.SmallIcon)) {
            notification$Builder2.setSmallIcon(resources.getIdentifier(notificationParams.SmallIcon, "drawable", context.getPackageName()));
        }
        if (!IsEmpty(notificationParams.LargeIcon)) {
            notification$Builder2.setLargeIcon(BitmapFactory.decodeResource(resources, resources.getIdentifier(notificationParams.LargeIcon, "drawable", context.getPackageName())));
        }
        if (notificationParams.Vibrate) {
            notification$Builder2.setVibrate(notificationParams.Vibration);
        }
        if (notificationParams.Light) {
            notification$Builder2.setLights(notificationParams.LightColor, notificationParams.LightOnMs, notificationParams.LightOffMs);
        }
        if (Build$VERSION.SDK_INT < 16) {
            return notification$Builder2.getNotification();
        }
        return notification$Builder2.build();
    }
    
    public static void CancelAllNotifications() {
        final Iterator<Integer> iterator = Storage.GetNotificationIds((Context)UnityPlayer.currentActivity).iterator();
        while (iterator.hasNext()) {
            CancelNotification(iterator.next());
        }
    }
    
    public static void CancelAllNotificationsEx() {
        CancelAllNotifications();
        ((NotificationManager)UnityPlayer.currentActivity.getSystemService("notification")).cancelAll();
    }
    
    public static void CancelNotification(final int n) {
        final Activity currentActivity = UnityPlayer.currentActivity;
        ((AlarmManager)currentActivity.getSystemService("alarm")).cancel(PendingIntent.getBroadcast((Context)currentActivity, n, new Intent((Context)currentActivity, (Class)Controller.class), 0));
        Storage.RemoveNotification((Context)currentActivity, n);
    }
    
    public static void CancelNotificationEx(final int n) {
        CancelNotification(n);
        ((NotificationManager)UnityPlayer.currentActivity.getSystemService("notification")).cancel(n);
    }
    
    private void CreateChannel(final NotificationParams notificationParams, final NotificationManager notificationManager) {
        final NotificationChannel notificationChannel = new NotificationChannel(notificationParams.ChannelId, (CharSequence)notificationParams.ChannelName, 3);
        notificationChannel.enableVibration(notificationParams.Vibrate);
        notificationChannel.enableLights(notificationParams.Light);
        notificationChannel.setLightColor(notificationParams.LightColor);
        notificationChannel.setLockscreenVisibility(0);
        notificationManager.createNotificationChannel(notificationChannel);
    }
    
    private void CreateStackedNotification(final Context context, final Intent intent, final NotificationParams notificationParams) {
        if (Build$VERSION.SDK_INT >= 20) {
            final int n = -Math.abs(notificationParams.GroupName.hashCode());
            final ArrayList<StatusBarNotification> list = new ArrayList<StatusBarNotification>();
            final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            final StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
            for (int length = activeNotifications.length, i = 0; i < length; ++i) {
                final StatusBarNotification statusBarNotification = activeNotifications[i];
                if (statusBarNotification.getId() != n && statusBarNotification.getGroupKey().endsWith("g:" + notificationParams.GroupName)) {
                    list.add(statusBarNotification);
                }
            }
            if (list.size() >= 2) {
                new Notification$Builder(context, notificationParams.ChannelId);
                Notification$Builder notification$Builder;
                if (Build$VERSION.SDK_INT >= 26) {
                    notification$Builder = new Notification$Builder(context, notificationParams.ChannelId);
                }
                else {
                    notification$Builder = new Notification$Builder(context);
                }
                final Resources resources = context.getResources();
                final String replace = notificationParams.GroupSummary.replace("{0}", new StringBuilder(String.valueOf(list.size())).toString());
                final Notification$InboxStyle style = new Notification$InboxStyle();
                final Iterator<StatusBarNotification> iterator = list.iterator();
                while (iterator.hasNext()) {
                    final String s = (String)iterator.next().getNotification().extras.get("android.title");
                    if (!IsEmpty(s)) {
                        style.addLine((CharSequence)s);
                    }
                }
                style.setSummaryText((CharSequence)replace);
                notification$Builder.setContentTitle((CharSequence)notificationParams.GroupName);
                notification$Builder.setContentText((CharSequence)replace);
                notification$Builder.setStyle((Notification$Style)style);
                notification$Builder.setGroup(notificationParams.GroupName).setGroupSummary(true);
                notification$Builder.setAutoCancel(true).setPriority(1);
                notification$Builder.setSmallIcon(resources.getIdentifier(notificationParams.SmallIcon, "drawable", context.getPackageName()));
                notification$Builder.setLargeIcon(BitmapFactory.decodeResource(resources, resources.getIdentifier(notificationParams.LargeIcon, "drawable", context.getPackageName())));
                notification$Builder.setContentIntent(PendingIntent.getActivity(context, (int)System.currentTimeMillis() / 1000, intent, 134217728));
                final Notification build = notification$Builder.build();
                build.defaults = -1;
                notificationManager.notify(notificationParams.GroupName, n, build);
            }
        }
    }
    
    private static boolean IsEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static void SetNotification(final int n, final long n2, final String s, final String s2, final String s3, final int n3, final int n4, final int n5, final String s4, final String s5, final int n6, final String s6) {
        SetNotification(n, "", "", "", "", n2, false, 0L, s, s2, s3, false, n3 == 1, null, n4 == 1, new long[] { 1000L, 1000L }, n5 == 1, 3000, 3000, -16711936, s4, s5, n6, 0, null, s6);
    }
    
    public static void SetNotification(final int n, final String s, final String s2, final String s3, final String s4, final long n2, final int n3, final long n4, final String s5, final String s6, final String s7, final int n5, final int n6, final String s8, final int n7, final String s9, final int n8, final int n9, final int n10, final int n11, final String s10, final String s11, final int n12, final int n13, final String s12, final String s13) {
        final String[] split = s9.split(",");
        final long[] array = new long[split.length];
        for (int i = 0; i < split.length; ++i) {
            array[i] = Long.parseLong(split[i]);
        }
        SetNotification(n, s, s2, s3, s4, n2, n3 == 1, n4, s5, s6, s7, n5 == 1, n6 == 1, s8, n7 == 1, array, n8 == 1, n9, n10, n11, s10, s11, n12, n13, s12, s13);
    }
    
    public static void SetNotification(final int id, final String groupName, final String groupSummary, final String channelId, final String channelName, final long n, final boolean repeat, final long repeatIntervalMs, final String title, final String message, final String ticker, final boolean multiline, final boolean sound, final String customSound, final boolean vibrate, final long[] vibration, final boolean light, final int lightOnMs, final int lightOffMs, final int lightColor, final String largeIcon, final String smallIcon, final int smallIconColor, final int executeMode, final String callbackData, final String unityClass) {
        final NotificationParams notificationParams = new NotificationParams();
        notificationParams.Id = id;
        notificationParams.GroupName = groupName;
        notificationParams.GroupSummary = groupSummary;
        notificationParams.ChannelId = channelId;
        notificationParams.ChannelName = channelName;
        notificationParams.TriggerAtMillis = System.currentTimeMillis() + n;
        notificationParams.Repeat = repeat;
        notificationParams.RepeatIntervalMs = repeatIntervalMs;
        notificationParams.Title = title;
        notificationParams.Message = message;
        notificationParams.Ticker = ticker;
        notificationParams.Multiline = multiline;
        notificationParams.Sound = sound;
        notificationParams.CustomSound = customSound;
        notificationParams.Vibrate = vibrate;
        notificationParams.Vibration = vibration;
        notificationParams.Light = light;
        notificationParams.LightOnMs = lightOnMs;
        notificationParams.LightOffMs = lightOffMs;
        notificationParams.LightColor = lightColor;
        notificationParams.LargeIcon = largeIcon;
        notificationParams.SmallIcon = smallIcon;
        notificationParams.SmallIconColor = smallIconColor;
        notificationParams.ExecuteMode = executeMode;
        notificationParams.CallbackData = callbackData;
        notificationParams.UnityClass = unityClass;
        SetNotification((Context)UnityPlayer.currentActivity, notificationParams);
    }
    
    public static void SetNotification(final Context context, final NotificationParams notificationParams) {
        if (Build$VERSION.SDK_INT < 11) {
            return;
        }
        final AlarmManager alarmManager = (AlarmManager)context.getSystemService("alarm");
        final Intent intent = new Intent(context, (Class)Controller.class);
        intent.putExtra("Id", notificationParams.Id);
        final PendingIntent broadcast = PendingIntent.getBroadcast(context, notificationParams.Id, intent, 134217728);
        if (notificationParams.Repeat) {
            if (notificationParams.ExecuteMode == 0) {
                alarmManager.setInexactRepeating(0, notificationParams.TriggerAtMillis, notificationParams.RepeatIntervalMs, broadcast);
            }
            else {
                alarmManager.setRepeating(0, notificationParams.TriggerAtMillis, notificationParams.RepeatIntervalMs, broadcast);
            }
        }
        else if (notificationParams.ExecuteMode == 2 && Build$VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, notificationParams.TriggerAtMillis, broadcast);
        }
        else if (notificationParams.ExecuteMode == 1 && Build$VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, notificationParams.TriggerAtMillis, broadcast);
        }
        else {
            alarmManager.set(0, notificationParams.TriggerAtMillis, broadcast);
        }
        Storage.AddNotification(context, notificationParams);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final int intExtra = intent.getIntExtra("Id", 0);
        final NotificationParams getNotification = Storage.GetNotification(context, intExtra);
        if (getNotification != null) {
            if (IsEmpty(getNotification.ChannelId)) {
                getNotification.ChannelId = "Default";
            }
            if (IsEmpty(getNotification.ChannelName)) {
                getNotification.ChannelName = "Default";
            }
            while (true) {
                while (true) {
                    try {
                        final Intent intent2 = new Intent(context, (Class)Class.forName(getNotification.UnityClass));
                        intent2.putExtra("Notification.Id", getNotification.Id);
                        intent2.putExtra("Notification.CallbackData", getNotification.CallbackData);
                        final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
                        final Notification buildNotification = this.BuildNotification(context, getNotification, intent2, notificationManager);
                        notificationManager.cancel(intExtra);
                        if (getNotification.Repeat) {
                            notificationManager.cancel(intExtra);
                            notificationManager.notify(intExtra, buildNotification);
                            if (!IsEmpty(getNotification.GroupName) && Build$VERSION.SDK_INT >= 20) {
                                this.CreateStackedNotification(context, intent2, getNotification);
                                return;
                            }
                            break;
                        }
                    }
                    catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                        return;
                    }
                    Storage.RemoveNotification(context, intExtra);
                    continue;
                }
            }
        }
    }
}
