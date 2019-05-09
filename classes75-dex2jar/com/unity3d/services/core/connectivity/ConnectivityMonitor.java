// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.connectivity;

import android.os.Build$VERSION;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.unity3d.services.core.properties.ClientProperties;
import android.net.ConnectivityManager;
import java.util.Iterator;
import com.unity3d.services.core.log.DeviceLog;
import java.util.HashSet;

public class ConnectivityMonitor
{
    private static int _connected;
    private static HashSet<IConnectivityListener> _listeners;
    private static boolean _listening;
    private static int _networkType;
    private static boolean _webappMonitoring;
    private static boolean _wifi;
    
    static {
        ConnectivityMonitor._connected = -1;
        ConnectivityMonitor._listening = false;
        ConnectivityMonitor._webappMonitoring = false;
        ConnectivityMonitor._wifi = false;
        ConnectivityMonitor._networkType = -1;
        ConnectivityMonitor._listeners = null;
    }
    
    public static void addListener(final IConnectivityListener connectivityListener) {
        if (ConnectivityMonitor._listeners == null) {
            ConnectivityMonitor._listeners = new HashSet<IConnectivityListener>();
        }
        ConnectivityMonitor._listeners.add(connectivityListener);
        updateListeningStatus();
    }
    
    public static void connected() {
        if (ConnectivityMonitor._connected == 1) {
            return;
        }
        DeviceLog.debug("Unity Ads connectivity change: connected");
        initConnectionStatus();
        if (ConnectivityMonitor._listeners != null) {
            final Iterator<IConnectivityListener> iterator = ConnectivityMonitor._listeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onConnected();
            }
        }
        sendToWebview(ConnectivityEvent.CONNECTED, ConnectivityMonitor._wifi, ConnectivityMonitor._networkType);
    }
    
    public static void connectionStatusChanged() {
        boolean wifi = true;
        if (ConnectivityMonitor._connected == 1) {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (activeNetworkInfo.getType() != 1) {
                    wifi = false;
                }
                final int networkType = ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkType();
                if (wifi != ConnectivityMonitor._wifi || (networkType != ConnectivityMonitor._networkType && !ConnectivityMonitor._wifi)) {
                    ConnectivityMonitor._wifi = wifi;
                    ConnectivityMonitor._networkType = networkType;
                    DeviceLog.debug("Unity Ads connectivity change: network change");
                    sendToWebview(ConnectivityEvent.NETWORK_CHANGE, wifi, networkType);
                }
            }
        }
    }
    
    public static void disconnected() {
        if (ConnectivityMonitor._connected == 0) {
            return;
        }
        ConnectivityMonitor._connected = 0;
        DeviceLog.debug("Unity Ads connectivity change: disconnected");
        if (ConnectivityMonitor._listeners != null) {
            final Iterator<IConnectivityListener> iterator = ConnectivityMonitor._listeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onDisconnected();
            }
        }
        sendToWebview(ConnectivityEvent.DISCONNECTED, false, 0);
    }
    
    private static void initConnectionStatus() {
        boolean wifi = true;
        final ConnectivityManager connectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                ConnectivityMonitor._connected = 0;
                return;
            }
            ConnectivityMonitor._connected = 1;
            if (activeNetworkInfo.getType() != 1) {
                wifi = false;
            }
            if (!(ConnectivityMonitor._wifi = wifi)) {
                ConnectivityMonitor._networkType = ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkType();
            }
        }
    }
    
    public static void removeListener(final IConnectivityListener connectivityListener) {
        if (ConnectivityMonitor._listeners == null) {
            return;
        }
        ConnectivityMonitor._listeners.remove(connectivityListener);
        updateListeningStatus();
    }
    
    private static void sendToWebview(final ConnectivityEvent connectivityEvent, final boolean b, final int n) {
        if (ConnectivityMonitor._webappMonitoring) {
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null && currentApp.isWebAppLoaded()) {
                switch (connectivityEvent) {
                    default: {}
                    case CONNECTED: {
                        if (b) {
                            currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, b, 0);
                            return;
                        }
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, b, n);
                    }
                    case DISCONNECTED: {
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.DISCONNECTED, new Object[0]);
                    }
                    case NETWORK_CHANGE: {
                        if (b) {
                            currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, b, 0);
                            return;
                        }
                        currentApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, b, n);
                    }
                }
            }
        }
    }
    
    public static void setConnectionMonitoring(final boolean webappMonitoring) {
        ConnectivityMonitor._webappMonitoring = webappMonitoring;
        updateListeningStatus();
    }
    
    private static void startListening() {
        if (ConnectivityMonitor._listening) {
            return;
        }
        ConnectivityMonitor._listening = true;
        initConnectionStatus();
        if (Build$VERSION.SDK_INT < 21) {
            ConnectivityChangeReceiver.register();
            return;
        }
        ConnectivityNetworkCallback.register();
    }
    
    public static void stopAll() {
        ConnectivityMonitor._listeners = null;
        ConnectivityMonitor._webappMonitoring = false;
        updateListeningStatus();
    }
    
    private static void stopListening() {
        if (!ConnectivityMonitor._listening) {
            return;
        }
        ConnectivityMonitor._listening = false;
        if (Build$VERSION.SDK_INT < 21) {
            ConnectivityChangeReceiver.unregister();
            return;
        }
        ConnectivityNetworkCallback.unregister();
    }
    
    private static void updateListeningStatus() {
        if (ConnectivityMonitor._webappMonitoring || (ConnectivityMonitor._listeners != null && !ConnectivityMonitor._listeners.isEmpty())) {
            startListening();
            return;
        }
        stopListening();
    }
}
