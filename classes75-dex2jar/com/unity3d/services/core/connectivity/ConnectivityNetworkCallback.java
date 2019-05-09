// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.connectivity;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.Network;
import android.net.NetworkRequest$Builder;
import com.unity3d.services.core.properties.ClientProperties;
import android.net.ConnectivityManager;
import android.annotation.TargetApi;
import android.net.ConnectivityManager$NetworkCallback;

@TargetApi(21)
public class ConnectivityNetworkCallback extends ConnectivityManager$NetworkCallback
{
    private static ConnectivityNetworkCallback _impl;
    
    static {
        ConnectivityNetworkCallback._impl = null;
    }
    
    public static void register() {
        if (ConnectivityNetworkCallback._impl == null) {
            ConnectivityNetworkCallback._impl = new ConnectivityNetworkCallback();
            ((ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity")).registerNetworkCallback(new NetworkRequest$Builder().build(), (ConnectivityManager$NetworkCallback)ConnectivityNetworkCallback._impl);
        }
    }
    
    public static void unregister() {
        if (ConnectivityNetworkCallback._impl != null) {
            ((ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity")).unregisterNetworkCallback((ConnectivityManager$NetworkCallback)ConnectivityNetworkCallback._impl);
            ConnectivityNetworkCallback._impl = null;
        }
    }
    
    public void onAvailable(final Network network) {
        ConnectivityMonitor.connected();
    }
    
    public void onCapabilitiesChanged(final Network network, final NetworkCapabilities networkCapabilities) {
        ConnectivityMonitor.connectionStatusChanged();
    }
    
    public void onLinkPropertiesChanged(final Network network, final LinkProperties linkProperties) {
        ConnectivityMonitor.connectionStatusChanged();
    }
    
    public void onLost(final Network network) {
        ConnectivityMonitor.disconnected();
    }
}
