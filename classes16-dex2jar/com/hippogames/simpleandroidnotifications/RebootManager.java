// 
// Decompiled by Procyon v0.5.34
// 

package com.hippogames.simpleandroidnotifications;

import java.util.Iterator;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class RebootManager extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        final Iterator<Integer> iterator = Storage.GetNotificationIds(context).iterator();
        while (iterator.hasNext()) {
            final NotificationParams getNotification = Storage.GetNotification(context, iterator.next());
            if (getNotification != null) {
                Controller.SetNotification(context, getNotification);
            }
        }
    }
}
