// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.stats;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.gms.common.stats.WakeLockTracker;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.support.v4.content.WakefulBroadcastReceiver;

@KeepForSdk
@ShowFirstParty
public abstract class GCoreWakefulBroadcastReceiver extends WakefulBroadcastReceiver
{
    private static String TAG;
    
    static {
        GCoreWakefulBroadcastReceiver.TAG = "GCoreWakefulBroadcastReceiver";
    }
    
    @SuppressLint({ "UnwrappedWakefulBroadcastReceiver" })
    @KeepForSdk
    public static boolean completeWakefulIntent(final Context context, final Intent intent) {
        if (intent == null) {
            return false;
        }
        if (context != null) {
            WakeLockTracker.getInstance().registerReleaseEvent(context, intent);
        }
        else {
            final String tag = GCoreWakefulBroadcastReceiver.TAG;
            final String value = String.valueOf(intent.toUri(0));
            String concat;
            if (value.length() != 0) {
                concat = "context shouldn't be null. intent: ".concat(value);
            }
            else {
                concat = new String("context shouldn't be null. intent: ");
            }
            Log.w(tag, concat);
        }
        return WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }
}
