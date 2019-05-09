// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.i;

import android.os.Build$VERSION;
import android.os.PowerManager;
import android.util.Log;
import android.content.Context;

public class a
{
    private static final String a;
    
    static {
        a = a.class.getSimpleName();
    }
    
    public static boolean a(final Context context) {
        return b(context) && b.b(context);
    }
    
    public static boolean b(final Context context) {
        if (context == null) {
            Log.v(com.facebook.ads.internal.w.i.a.a, "Invalid context in screen interactive check, assuming interactive.");
            return true;
        }
        try {
            final PowerManager powerManager = (PowerManager)context.getSystemService("power");
            if (Build$VERSION.SDK_INT >= 20) {
                return powerManager.isInteractive();
            }
            return powerManager.isScreenOn();
        }
        catch (Exception ex) {
            Log.e(com.facebook.ads.internal.w.i.a.a, "Exception in screen interactive check, assuming interactive.", (Throwable)ex);
            com.facebook.ads.internal.w.h.a.b(context, "risky", com.facebook.ads.internal.w.h.b.G, ex);
            return true;
        }
    }
}
