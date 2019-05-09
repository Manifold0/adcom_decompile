// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.net.wifi.WifiManager;
import android.content.Context;

public final class ab
{
    public static String a(final Context context) {
        final WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
        if (wifiManager == null) {
            goto Label_0028;
        }
        try {
            return ct.b(wifiManager.getConnectionInfo().getMacAddress());
        }
        catch (RuntimeException ex) {}
        catch (SecurityException ex2) {
            goto Label_0028;
        }
    }
}
