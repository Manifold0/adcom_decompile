// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.v4.content.WakefulBroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.IntentService;

public class NotificationRestoreService extends IntentService
{
    public NotificationRestoreService() {
        super("NotificationRestoreService");
    }
    
    protected void onHandleIntent(final Intent intent) {
        if (intent == null) {
            return;
        }
        Thread.currentThread().setPriority(10);
        OneSignal.setAppContext((Context)this);
        NotificationRestorer.restore((Context)this);
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
