// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.content.IntentSender$SendIntentException;
import android.app.PendingIntent;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;

public interface IActivityLauncher
{
    void startActivity(final Context p0, final Intent p1);
    
    void startIntentSenderForResult(final Activity p0, final PendingIntent p1, final int p2, final Intent p3, final String p4) throws IntentSender$SendIntentException;
}
