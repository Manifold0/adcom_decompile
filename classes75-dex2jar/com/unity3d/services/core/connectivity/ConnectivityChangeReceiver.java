// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.connectivity;

import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import com.unity3d.services.core.properties.ClientProperties;
import android.content.BroadcastReceiver;

public class ConnectivityChangeReceiver extends BroadcastReceiver
{
    private static ConnectivityChangeReceiver _receiver;
    
    static {
        ConnectivityChangeReceiver._receiver = null;
    }
    
    public static void register() {
        if (ConnectivityChangeReceiver._receiver == null) {
            ConnectivityChangeReceiver._receiver = new ConnectivityChangeReceiver();
            ClientProperties.getApplicationContext().registerReceiver((BroadcastReceiver)ConnectivityChangeReceiver._receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }
    
    public static void unregister() {
        if (ConnectivityChangeReceiver._receiver != null) {
            ClientProperties.getApplicationContext().unregisterReceiver((BroadcastReceiver)ConnectivityChangeReceiver._receiver);
            ConnectivityChangeReceiver._receiver = null;
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getBooleanExtra("noConnectivity", false)) {
            ConnectivityMonitor.disconnected();
        }
        else {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager != null) {
                final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    ConnectivityMonitor.connected();
                }
            }
        }
    }
}
