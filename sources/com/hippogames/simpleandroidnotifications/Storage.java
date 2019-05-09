package com.hippogames.simpleandroidnotifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    public static List<Integer> GetNotificationIds(Context context) {
        String notifications = PreferenceManager.getDefaultSharedPreferences(context).getString("Notifications", null);
        if (notifications == null) {
            return new ArrayList();
        }
        List<Integer> ids = new ArrayList();
        for (String id : notifications.split(",")) {
            try {
                ids.add(Integer.valueOf(Integer.parseInt(id)));
            } catch (NumberFormatException e) {
            }
        }
        return ids;
    }

    public static void AddNotification(Context context, NotificationParams params) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        Editor editor = prefs.edit();
        String notifications = prefs.getString("Notifications", null);
        int id = params.Id;
        try {
            editor.putString("Notifications", notifications == null ? Integer.toString(id) : new StringBuilder(String.valueOf(notifications)).append(",").append(id).toString());
            editor.putString("Notification." + id, Serializer.ToString(params));
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NotificationParams GetNotification(Context context, int id) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (!prefs.contains("Notification." + id)) {
            return null;
        }
        try {
            return (NotificationParams) Serializer.FromString(prefs.getString("Notification." + id, null));
        } catch (Exception e) {
            return null;
        }
    }

    public static void RemoveNotification(Context context, int id) {
        List<Integer> ids = GetNotificationIds(context);
        if (ids.size() != 0) {
            ids.removeAll(Arrays.asList(new Integer[]{Integer.valueOf(id)}));
            Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
            editor.putString("Notifications", TextUtils.join(",", ids));
            editor.remove("Notification." + id);
            editor.apply();
        }
    }
}
