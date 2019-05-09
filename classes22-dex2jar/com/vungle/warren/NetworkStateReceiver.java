// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.BroadcastReceiver;

public class NetworkStateReceiver extends BroadcastReceiver
{
    private static final String TAG = "NetworkStateReceiver";
    
    public static void enableBroadcastReceiver(final Context context, final boolean b) {
        final PackageManager packageManager = context.getPackageManager();
        final ComponentName componentName = new ComponentName(context, (Class)NetworkStateReceiver.class);
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 2;
        }
        packageManager.setComponentEnabledSetting(componentName, n, 1);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (context != null) {
            if (Vungle.isInitialized()) {
                Vungle.handleApkDirectDownloads(context);
                return;
            }
            enableBroadcastReceiver(context, false);
        }
    }
}
