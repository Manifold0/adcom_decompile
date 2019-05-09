// 
// Decompiled by Procyon v0.5.34
// 

package com.hippogames.simpleandroidnotifications;

import android.text.TextUtils;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import java.io.Serializable;
import android.preference.PreferenceManager;
import android.content.Context;

public class Storage
{
    public static void AddNotification(final Context context, final NotificationParams notificationParams) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences$Editor edit = defaultSharedPreferences.edit();
        final String string = defaultSharedPreferences.getString("Notifications", (String)null);
        final int id = notificationParams.Id;
        Label_0080: {
            if (string != null) {
                break Label_0080;
            }
            try {
                String s = Integer.toString(id);
                while (true) {
                    edit.putString("Notifications", s);
                    edit.putString("Notification." + id, Serializer.ToString(notificationParams));
                    edit.apply();
                    return;
                    s = String.valueOf(string) + "," + id;
                    continue;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static NotificationParams GetNotification(final Context context, final int n) {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!defaultSharedPreferences.contains("Notification." + n)) {
            return null;
        }
        final String string = defaultSharedPreferences.getString("Notification." + n, (String)null);
        try {
            return (NotificationParams)Serializer.FromString(string);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static List<Integer> GetNotificationIds(final Context context) {
        final String string = PreferenceManager.getDefaultSharedPreferences(context).getString("Notifications", (String)null);
        ArrayList<Integer> list;
        if (string == null) {
            list = new ArrayList<Integer>();
        }
        else {
            final ArrayList<Integer> list2 = new ArrayList<Integer>();
            final String[] split = string.split(",");
            final int length = split.length;
            int n = 0;
            while (true) {
                list = list2;
                if (n >= length) {
                    break;
                }
                final String s = split[n];
                while (true) {
                    try {
                        list2.add(Integer.parseInt(s));
                        ++n;
                    }
                    catch (NumberFormatException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
        return list;
    }
    
    public static void RemoveNotification(final Context context, final int n) {
        final List<Integer> getNotificationIds = GetNotificationIds(context);
        if (getNotificationIds.size() == 0) {
            return;
        }
        getNotificationIds.removeAll(Arrays.asList(n));
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        edit.putString("Notifications", TextUtils.join((CharSequence)",", (Iterable)getNotificationIds));
        edit.remove("Notification." + n);
        edit.apply();
    }
}
