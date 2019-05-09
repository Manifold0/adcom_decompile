package com.tapjoy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.internal.ge;

public class GCMReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        boolean a = ge.m7914b(context).m7909a(intent);
        if (isOrderedBroadcast()) {
            setResult(-1, null, null);
            if (a) {
                abortBroadcast();
            }
        }
    }
}
