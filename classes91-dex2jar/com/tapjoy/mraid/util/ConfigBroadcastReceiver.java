// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

import android.content.Intent;
import android.content.Context;
import com.tapjoy.mraid.controller.Display;
import android.content.BroadcastReceiver;

public class ConfigBroadcastReceiver extends BroadcastReceiver
{
    private Display a;
    private int b;
    
    public ConfigBroadcastReceiver(final Display a) {
        this.a = a;
        this.b = this.a.getOrientation();
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
            final int orientation = this.a.getOrientation();
            if (orientation != this.b) {
                this.b = orientation;
                this.a.onOrientationChanged(this.b);
            }
        }
    }
}
