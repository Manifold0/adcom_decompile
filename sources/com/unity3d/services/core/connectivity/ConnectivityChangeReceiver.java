package com.unity3d.services.core.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.unity3d.services.core.properties.ClientProperties;

public class ConnectivityChangeReceiver extends BroadcastReceiver {
    private static ConnectivityChangeReceiver _receiver = null;

    public static void register() {
        if (_receiver == null) {
            _receiver = new ConnectivityChangeReceiver();
            ClientProperties.getApplicationContext().registerReceiver(_receiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public static void unregister() {
        if (_receiver != null) {
            ClientProperties.getApplicationContext().unregisterReceiver(_receiver);
            _receiver = null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getBooleanExtra("noConnectivity", false)) {
            ConnectivityMonitor.disconnected();
            return;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni != null && ni.isConnected()) {
                ConnectivityMonitor.connected();
            }
        }
    }
}
