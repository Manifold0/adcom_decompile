package com.unity3d.services.core.connectivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.HashSet;
import java.util.Iterator;

public class ConnectivityMonitor {
    private static int _connected = -1;
    private static HashSet<IConnectivityListener> _listeners = null;
    private static boolean _listening = false;
    private static int _networkType = -1;
    private static boolean _webappMonitoring = false;
    private static boolean _wifi = false;

    public static void setConnectionMonitoring(boolean monitoring) {
        _webappMonitoring = monitoring;
        updateListeningStatus();
    }

    public static void addListener(IConnectivityListener listener) {
        if (_listeners == null) {
            _listeners = new HashSet();
        }
        _listeners.add(listener);
        updateListeningStatus();
    }

    public static void removeListener(IConnectivityListener listener) {
        if (_listeners != null) {
            _listeners.remove(listener);
            updateListeningStatus();
        }
    }

    public static void stopAll() {
        _listeners = null;
        _webappMonitoring = false;
        updateListeningStatus();
    }

    private static void updateListeningStatus() {
        if (_webappMonitoring || !(_listeners == null || _listeners.isEmpty())) {
            startListening();
        } else {
            stopListening();
        }
    }

    private static void startListening() {
        if (!_listening) {
            _listening = true;
            initConnectionStatus();
            if (VERSION.SDK_INT < 21) {
                ConnectivityChangeReceiver.register();
            } else {
                ConnectivityNetworkCallback.register();
            }
        }
    }

    private static void stopListening() {
        if (_listening) {
            _listening = false;
            if (VERSION.SDK_INT < 21) {
                ConnectivityChangeReceiver.unregister();
            } else {
                ConnectivityNetworkCallback.unregister();
            }
        }
    }

    private static void initConnectionStatus() {
        boolean z = true;
        ConnectivityManager cm = (ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity");
        if (cm != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null || !ni.isConnected()) {
                _connected = 0;
                return;
            }
            _connected = 1;
            if (ni.getType() != 1) {
                z = false;
            }
            _wifi = z;
            if (!_wifi) {
                _networkType = ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
            }
        }
    }

    public static void connected() {
        if (_connected != 1) {
            DeviceLog.debug("Unity Ads connectivity change: connected");
            initConnectionStatus();
            if (_listeners != null) {
                Iterator it = _listeners.iterator();
                while (it.hasNext()) {
                    ((IConnectivityListener) it.next()).onConnected();
                }
            }
            sendToWebview(ConnectivityEvent.CONNECTED, _wifi, _networkType);
        }
    }

    public static void disconnected() {
        if (_connected != 0) {
            _connected = 0;
            DeviceLog.debug("Unity Ads connectivity change: disconnected");
            if (_listeners != null) {
                Iterator it = _listeners.iterator();
                while (it.hasNext()) {
                    ((IConnectivityListener) it.next()).onDisconnected();
                }
            }
            sendToWebview(ConnectivityEvent.DISCONNECTED, false, 0);
        }
    }

    public static void connectionStatusChanged() {
        boolean wifiStatus = true;
        if (_connected == 1) {
            NetworkInfo ni = ((ConnectivityManager) ClientProperties.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (ni != null && ni.isConnected()) {
                if (ni.getType() != 1) {
                    wifiStatus = false;
                }
                int mobileNetworkType = ((TelephonyManager) ClientProperties.getApplicationContext().getSystemService(PlaceFields.PHONE)).getNetworkType();
                if (wifiStatus != _wifi || (mobileNetworkType != _networkType && !_wifi)) {
                    _wifi = wifiStatus;
                    _networkType = mobileNetworkType;
                    DeviceLog.debug("Unity Ads connectivity change: network change");
                    sendToWebview(ConnectivityEvent.NETWORK_CHANGE, wifiStatus, mobileNetworkType);
                }
            }
        }
    }

    private static void sendToWebview(ConnectivityEvent eventType, boolean wifi, int networkType) {
        if (_webappMonitoring) {
            WebViewApp webViewApp = WebViewApp.getCurrentApp();
            if (webViewApp != null && webViewApp.isWebAppLoaded()) {
                switch (eventType) {
                    case CONNECTED:
                        if (wifi) {
                            webViewApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, Boolean.valueOf(wifi), Integer.valueOf(0));
                            return;
                        }
                        webViewApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.CONNECTED, Boolean.valueOf(wifi), Integer.valueOf(networkType));
                        return;
                    case DISCONNECTED:
                        webViewApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.DISCONNECTED, new Object[0]);
                        return;
                    case NETWORK_CHANGE:
                        if (wifi) {
                            webViewApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, Boolean.valueOf(wifi), Integer.valueOf(0));
                            return;
                        }
                        webViewApp.sendEvent(WebViewEventCategory.CONNECTIVITY, ConnectivityEvent.NETWORK_CHANGE, Boolean.valueOf(wifi), Integer.valueOf(networkType));
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
