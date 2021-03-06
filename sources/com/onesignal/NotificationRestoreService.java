package com.onesignal;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class NotificationRestoreService extends IntentService {
    public NotificationRestoreService() {
        super("NotificationRestoreService");
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Thread.currentThread().setPriority(10);
            OneSignal.setAppContext(this);
            NotificationRestorer.restore(this);
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }
}
