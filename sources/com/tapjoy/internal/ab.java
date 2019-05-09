package com.tapjoy.internal;

import android.content.Context;
import android.net.wifi.WifiManager;

public final class ab {
    /* renamed from: a */
    public static String m7142a(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager != null) {
            try {
                return ct.m7338b(wifiManager.getConnectionInfo().getMacAddress());
            } catch (SecurityException e) {
            } catch (RuntimeException e2) {
            }
        }
        return null;
    }
}
