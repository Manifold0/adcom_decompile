// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.content.Context;

public class c
{
    public static void a() {
        try {
            Class.forName("android.os.AsyncTask");
        }
        catch (Throwable t) {}
    }
    
    public static void a(final Context context, final String s) {
        if (AdInternalSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", s + " (displayed for test ads only)");
        }
    }
}
