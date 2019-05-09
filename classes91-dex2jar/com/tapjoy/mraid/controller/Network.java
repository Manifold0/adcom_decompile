// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.controller;

import android.net.NetworkInfo;
import android.content.BroadcastReceiver;
import com.tapjoy.TapjoyLog;
import android.content.Context;
import com.tapjoy.mraid.view.MraidView;
import android.content.IntentFilter;
import com.tapjoy.mraid.util.NetworkBroadcastReceiver;
import android.net.ConnectivityManager;

public class Network extends Abstract
{
    private ConnectivityManager c;
    private int d;
    private NetworkBroadcastReceiver e;
    private IntentFilter f;
    
    public Network(final MraidView mraidView, final Context context) {
        super(mraidView, context);
        this.c = (ConnectivityManager)context.getSystemService("connectivity");
    }
    
    public String getNetwork() {
    Label_0019_Outer:
        while (true) {
            Object activeNetworkInfo = null;
            while (true) {
                while (true) {
                    try {
                        activeNetworkInfo = this.c.getActiveNetworkInfo();
                        if (activeNetworkInfo == null) {
                            activeNetworkInfo = "offline";
                            TapjoyLog.d("MRAID Network", "getNetwork: " + (String)activeNetworkInfo);
                            return (String)activeNetworkInfo;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        continue Label_0019_Outer;
                    }
                    break;
                }
                switch (Network$1.a[((NetworkInfo)activeNetworkInfo).getState().ordinal()]) {
                    default: {
                        final int type = ((NetworkInfo)activeNetworkInfo).getType();
                        if (type == 0) {
                            activeNetworkInfo = "cell";
                            continue;
                        }
                        if (type == 1) {
                            activeNetworkInfo = "wifi";
                            continue;
                        }
                        activeNetworkInfo = "unknown";
                        continue;
                    }
                    case 1: {
                        activeNetworkInfo = "unknown";
                        continue;
                    }
                    case 2: {
                        activeNetworkInfo = "offline";
                        continue;
                    }
                }
                break;
            }
        }
    }
    
    public void onConnectionChanged() {
        final String string = "window.mraidview.fireChangeEvent({ network: '" + this.getNetwork() + "'});";
        TapjoyLog.d("MRAID Network", string);
        this.a.injectMraidJavaScript(string);
    }
    
    public void startNetworkListener() {
        if (this.d == 0) {
            this.e = new NetworkBroadcastReceiver(this);
            (this.f = new IntentFilter()).addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        ++this.d;
        this.b.registerReceiver((BroadcastReceiver)this.e, this.f);
    }
    
    @Override
    public void stopAllListeners() {
        this.d = 0;
        try {
            this.b.unregisterReceiver((BroadcastReceiver)this.e);
        }
        catch (Exception ex) {}
    }
    
    public void stopNetworkListener() {
        --this.d;
        if (this.d == 0) {
            this.b.unregisterReceiver((BroadcastReceiver)this.e);
            this.e = null;
            this.f = null;
        }
    }
}
