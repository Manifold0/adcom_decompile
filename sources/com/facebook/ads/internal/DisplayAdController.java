package com.facebook.ads.internal;

import android.util.Log;

public class DisplayAdController {
    /* renamed from: a */
    private static final String f3827a = DisplayAdController.class.getSimpleName();
    /* renamed from: b */
    private static boolean f3828b = false;

    @Deprecated
    protected static synchronized void setMainThreadForced(boolean z) {
        synchronized (DisplayAdController.class) {
            Log.d(f3827a, "BaseAdController changed main thread forced from " + f3828b + " to " + z);
            f3828b = z;
        }
    }
}
