package com.tapjoy.mraid.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tapjoy.mraid.controller.Display;

public class ConfigBroadcastReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private Display f8297a;
    /* renamed from: b */
    private int f8298b = this.f8297a.getOrientation();

    public ConfigBroadcastReceiver(Display mraidDisplayController) {
        this.f8297a = mraidDisplayController;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
            int orientation = this.f8297a.getOrientation();
            if (orientation != this.f8298b) {
                this.f8298b = orientation;
                this.f8297a.onOrientationChanged(this.f8298b);
            }
        }
    }
}
