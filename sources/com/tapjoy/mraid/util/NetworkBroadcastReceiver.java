package com.tapjoy.mraid.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.mraid.controller.Network;

public class NetworkBroadcastReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private Network f8311a;

    public NetworkBroadcastReceiver(Network mraidNetworkController) {
        this.f8311a = mraidNetworkController;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            this.f8311a.onConnectionChanged();
        }
    }
}
