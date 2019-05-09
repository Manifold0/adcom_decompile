// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal;

import android.util.Log;

public class DisplayAdController
{
    private static final String a;
    private static boolean b;
    
    static {
        a = DisplayAdController.class.getSimpleName();
        DisplayAdController.b = false;
    }
    
    @Deprecated
    protected static void setMainThreadForced(final boolean b) {
        synchronized (DisplayAdController.class) {
            Log.d(DisplayAdController.a, "BaseAdController changed main thread forced from " + DisplayAdController.b + " to " + b);
            DisplayAdController.b = b;
        }
    }
}
