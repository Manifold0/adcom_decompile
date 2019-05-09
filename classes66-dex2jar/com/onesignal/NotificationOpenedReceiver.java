// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class NotificationOpenedReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        NotificationOpenedProcessor.processFromContext(context, intent);
    }
}
