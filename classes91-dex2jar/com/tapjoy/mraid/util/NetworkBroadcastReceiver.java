// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

import android.content.Intent;
import android.content.Context;
import com.tapjoy.mraid.controller.Network;
import android.content.BroadcastReceiver;

public class NetworkBroadcastReceiver extends BroadcastReceiver
{
    private Network a;
    
    public NetworkBroadcastReceiver(final Network a) {
        this.a = a;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.a.onConnectionChanged();
        }
    }
}
