package com.hippogames.simpleandroidnotifications;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import com.chartboost.sdk.CBLocation;
import com.onesignal.OneSignalDbContract.NotificationTable;
import com.unity3d.player.UnityPlayer;
import java.util.ArrayList;
import java.util.Iterator;

public class Controller extends BroadcastReceiver {
    public static void SetNotification(int id, long delayMs, String title, String message, String ticker, int sound, int vibrate, int light, String largeIcon, String smallIcon, int smallIconColor, String unityClass) {
        long[] jArr = new long[2];
        SetNotification(id, "", "", "", "", delayMs, false, 0, title, message, ticker, false, sound == 1, null, vibrate == 1, new long[]{1000, 1000}, light == 1, 3000, 3000, -16711936, largeIcon, smallIcon, smallIconColor, 0, null, unityClass);
    }

    public static void SetNotification(int id, String groupName, String groupSummary, String channelId, String channelName, long delayMs, int repeat, long repeatIntervalMs, String title, String message, String ticker, int multiline, int sound, String customSound, int vibrate, String vibrationString, int light, int lightOnMs, int lightOffMs, int lightColor, String largeIcon, String smallIcon, int smallIconColor, int executeMode, String callbackData, String unityClass) {
        String[] array = vibrationString.split(",");
        long[] vibration = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            vibration[i] = Long.parseLong(array[i]);
        }
        SetNotification(id, groupName, groupSummary, channelId, channelName, delayMs, repeat == 1, repeatIntervalMs, title, message, ticker, multiline == 1, sound == 1, customSound, vibrate == 1, vibration, light == 1, lightOnMs, lightOffMs, lightColor, largeIcon, smallIcon, smallIconColor, executeMode, callbackData, unityClass);
    }

    public static void SetNotification(int id, String groupName, String groupSummary, String channelId, String channelName, long delayMs, boolean repeat, long repeatIntervalMs, String title, String message, String ticker, boolean multiline, boolean sound, String customSound, boolean vibrate, long[] vibration, boolean light, int lightOnMs, int lightOffMs, int lightColor, String largeIcon, String smallIcon, int smallIconColor, int executeMode, String callbackData, String unityClass) {
        NotificationParams params = new NotificationParams();
        params.Id = id;
        params.GroupName = groupName;
        params.GroupSummary = groupSummary;
        params.ChannelId = channelId;
        params.ChannelName = channelName;
        params.TriggerAtMillis = System.currentTimeMillis() + delayMs;
        params.Repeat = repeat;
        params.RepeatIntervalMs = repeatIntervalMs;
        params.Title = title;
        params.Message = message;
        params.Ticker = ticker;
        params.Multiline = multiline;
        params.Sound = sound;
        params.CustomSound = customSound;
        params.Vibrate = vibrate;
        params.Vibration = vibration;
        params.Light = light;
        params.LightOnMs = lightOnMs;
        params.LightOffMs = lightOffMs;
        params.LightColor = lightColor;
        params.LargeIcon = largeIcon;
        params.SmallIcon = smallIcon;
        params.SmallIconColor = smallIconColor;
        params.ExecuteMode = executeMode;
        params.CallbackData = callbackData;
        params.UnityClass = unityClass;
        SetNotification(UnityPlayer.currentActivity, params);
    }

    public static void SetNotification(Context context, NotificationParams params) {
        if (VERSION.SDK_INT >= 11) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            Intent intent = new Intent(context, Controller.class);
            intent.putExtra("Id", params.Id);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, params.Id, intent, 134217728);
            if (params.Repeat) {
                if (params.ExecuteMode == 0) {
                    alarmManager.setInexactRepeating(0, params.TriggerAtMillis, params.RepeatIntervalMs, pendingIntent);
                } else {
                    alarmManager.setRepeating(0, params.TriggerAtMillis, params.RepeatIntervalMs, pendingIntent);
                }
            } else if (params.ExecuteMode == 2 && VERSION.SDK_INT >= 23) {
                alarmManager.setExactAndAllowWhileIdle(0, params.TriggerAtMillis, pendingIntent);
            } else if (params.ExecuteMode != 1 || VERSION.SDK_INT < 19) {
                alarmManager.set(0, params.TriggerAtMillis, pendingIntent);
            } else {
                alarmManager.setExact(0, params.TriggerAtMillis, pendingIntent);
            }
            Storage.AddNotification(context, params);
        }
    }

    public void onReceive(Context context, Intent intent) {
        int id = intent.getIntExtra("Id", 0);
        NotificationParams params = Storage.GetNotification(context, id);
        if (params != null) {
            if (IsEmpty(params.ChannelId)) {
                params.ChannelId = CBLocation.LOCATION_DEFAULT;
            }
            if (IsEmpty(params.ChannelName)) {
                params.ChannelName = CBLocation.LOCATION_DEFAULT;
            }
            try {
                Intent notificationIntent = new Intent(context, Class.forName(params.UnityClass));
                notificationIntent.putExtra("Notification.Id", params.Id);
                notificationIntent.putExtra("Notification.CallbackData", params.CallbackData);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationTable.TABLE_NAME);
                Notification notification = BuildNotification(context, params, notificationIntent, notificationManager);
                notificationManager.cancel(id);
                if (params.Repeat) {
                    notificationManager.cancel(id);
                } else {
                    Storage.RemoveNotification(context, id);
                }
                notificationManager.notify(id, notification);
                if (!IsEmpty(params.GroupName) && VERSION.SDK_INT >= 20) {
                    CreateStackedNotification(context, notificationIntent, params);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void CancelNotification(int id) {
        Activity currentActivity = UnityPlayer.currentActivity;
        ((AlarmManager) currentActivity.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(currentActivity, id, new Intent(currentActivity, Controller.class), 0));
        Storage.RemoveNotification(currentActivity, id);
    }

    public static void CancelNotificationEx(int id) {
        CancelNotification(id);
        ((NotificationManager) UnityPlayer.currentActivity.getSystemService(NotificationTable.TABLE_NAME)).cancel(id);
    }

    public static void CancelAllNotifications() {
        for (Integer intValue : Storage.GetNotificationIds(UnityPlayer.currentActivity)) {
            CancelNotification(intValue.intValue());
        }
    }

    public static void CancelAllNotificationsEx() {
        CancelAllNotifications();
        ((NotificationManager) UnityPlayer.currentActivity.getSystemService(NotificationTable.TABLE_NAME)).cancelAll();
    }

    private static boolean IsEmpty(String value) {
        return value == null || value.length() == 0;
    }

    private Notification BuildNotification(Context context, NotificationParams params, Intent intent, NotificationManager notificationManager) {
        Builder builder;
        PendingIntent contentIntent = PendingIntent.getActivity(context, params.Id, intent, 134217728);
        Resources resources = context.getResources();
        if (VERSION.SDK_INT >= 26) {
            builder = new Builder(context, params.ChannelId);
            if (notificationManager.getNotificationChannel(params.ChannelId) == null) {
                CreateChannel(params, notificationManager);
            }
        } else {
            builder = new Builder(context);
        }
        builder.setContentIntent(contentIntent).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentTitle(params.Title).setContentText(params.Message);
        if (params.Multiline && VERSION.SDK_INT >= 16) {
            builder.setStyle(new BigTextStyle().bigText(params.Message));
        }
        if (!IsEmpty(params.GroupName) && VERSION.SDK_INT >= 20) {
            builder.setGroup(params.GroupName);
        }
        if (params.Sound) {
            if (IsEmpty(params.CustomSound)) {
                builder.setSound(RingtoneManager.getDefaultUri(2));
            } else {
                builder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + params.CustomSound));
            }
        }
        if (VERSION.SDK_INT >= 21) {
            builder.setColor(params.SmallIconColor);
        }
        if (!IsEmpty(params.Ticker)) {
            builder.setTicker(params.Ticker);
        }
        if (!IsEmpty(params.SmallIcon)) {
            builder.setSmallIcon(resources.getIdentifier(params.SmallIcon, "drawable", context.getPackageName()));
        }
        if (!IsEmpty(params.LargeIcon)) {
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, resources.getIdentifier(params.LargeIcon, "drawable", context.getPackageName())));
        }
        if (params.Vibrate) {
            builder.setVibrate(params.Vibration);
        }
        if (params.Light) {
            builder.setLights(params.LightColor, params.LightOnMs, params.LightOffMs);
        }
        if (VERSION.SDK_INT < 16) {
            return builder.getNotification();
        }
        return builder.build();
    }

    private void CreateChannel(NotificationParams params, NotificationManager notificationManager) {
        NotificationChannel channel = new NotificationChannel(params.ChannelId, params.ChannelName, 3);
        channel.enableVibration(params.Vibrate);
        channel.enableLights(params.Light);
        channel.setLightColor(params.LightColor);
        channel.setLockscreenVisibility(0);
        notificationManager.createNotificationChannel(channel);
    }

    private void CreateStackedNotification(Context context, Intent notificationIntent, NotificationParams params) {
        if (VERSION.SDK_INT >= 20) {
            int groupId = -Math.abs(params.GroupName.hashCode());
            ArrayList<StatusBarNotification> group = new ArrayList();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationTable.TABLE_NAME);
            for (StatusBarNotification notification : notificationManager.getActiveNotifications()) {
                if (notification.getId() != groupId && notification.getGroupKey().endsWith("g:" + params.GroupName)) {
                    group.add(notification);
                }
            }
            if (group.size() >= 2) {
                Builder builder = new Builder(context, params.ChannelId);
                if (VERSION.SDK_INT >= 26) {
                    builder = new Builder(context, params.ChannelId);
                } else {
                    builder = new Builder(context);
                }
                Resources res = context.getResources();
                String summary = params.GroupSummary.replace("{0}", new StringBuilder(String.valueOf(group.size())).toString());
                InboxStyle inbox = new InboxStyle();
                Iterator it = group.iterator();
                while (it.hasNext()) {
                    String title = (String) ((StatusBarNotification) it.next()).getNotification().extras.get(NotificationCompat.EXTRA_TITLE);
                    if (!IsEmpty(title)) {
                        inbox.addLine(title);
                    }
                }
                inbox.setSummaryText(summary);
                builder.setContentTitle(params.GroupName);
                builder.setContentText(summary);
                builder.setStyle(inbox);
                builder.setGroup(params.GroupName).setGroupSummary(true);
                builder.setAutoCancel(true).setPriority(1);
                builder.setSmallIcon(res.getIdentifier(params.SmallIcon, "drawable", context.getPackageName()));
                builder.setLargeIcon(BitmapFactory.decodeResource(res, res.getIdentifier(params.LargeIcon, "drawable", context.getPackageName())));
                builder.setContentIntent(PendingIntent.getActivity(context, ((int) System.currentTimeMillis()) / 1000, notificationIntent, 134217728));
                Notification stack = builder.build();
                stack.defaults = -1;
                notificationManager.notify(params.GroupName, groupId, stack);
            }
        }
    }
}
