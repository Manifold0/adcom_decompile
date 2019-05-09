package com.hippogames.simpleandroidnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RebootManager extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        for (Integer intValue : Storage.GetNotificationIds(context)) {
            NotificationParams params = Storage.GetNotification(context, intValue.intValue());
            if (params != null) {
                Controller.SetNotification(context, params);
            }
        }
    }
}
