// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.content.IntentSender$SendIntentException;
import android.app.PendingIntent;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;

public class ActivityLauncher implements IActivityLauncher
{
    @Override
    public void startActivity(final Context context, final Intent intent) {
        context.startActivity(intent);
    }
    
    @Override
    public void startIntentSenderForResult(final Activity activity, final PendingIntent pendingIntent, final int n, final Intent intent, final String s) throws IntentSender$SendIntentException {
        activity.startIntentSenderForResult(pendingIntent.getIntentSender(), n, new Intent(), (int)0, (int)0, (int)0);
    }
}
