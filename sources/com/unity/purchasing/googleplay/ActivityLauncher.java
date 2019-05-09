package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;

public class ActivityLauncher implements IActivityLauncher {
    public void startIntentSenderForResult(Activity act, PendingIntent pendingIntent, int requestCode, Intent intent, String sku) throws SendIntentException {
        act.startIntentSenderForResult(pendingIntent.getIntentSender(), requestCode, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
    }

    public void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }
}
