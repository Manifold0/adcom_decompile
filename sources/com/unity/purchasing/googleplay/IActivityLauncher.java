package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;

public interface IActivityLauncher {
    void startActivity(Context context, Intent intent);

    void startIntentSenderForResult(Activity activity, PendingIntent pendingIntent, int i, Intent intent, String str) throws SendIntentException;
}
