// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.n;

import android.util.Log;
import android.app.Application;
import com.facebook.ads.internal.w.h.b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.f.a;
import org.json.JSONObject;
import android.content.Context;
import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

public class c
{
    @Nullable
    private static AtomicBoolean a;
    
    public static String a(final Context context) {
        final JSONObject jsonObject = new JSONObject();
        try {
            String a;
            if ((a = com.facebook.ads.internal.w.f.a.a(context)) == null) {
                a = "Unknown";
            }
            jsonObject.put("process_name", (Object)a);
            jsonObject.put("is_ads_process", AdInternalSettings.d);
            jsonObject.put("client_supports", b(context.getApplicationContext()));
            return jsonObject.toString();
        }
        catch (Exception ex) {
            com.facebook.ads.internal.w.h.a.b(context, "generic", b.D, ex);
            return jsonObject.toString();
        }
    }
    
    public static boolean b(final Context context) {
        if (AdInternalSettings.c) {
            return false;
        }
        if (c.a != null) {
            return c.a.get();
        }
        final Context applicationContext = context.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            Log.w("FBAudienceNetwork", "Multi-process support won't work because application Context is not Application instance.");
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ad, new Exception("ApplicationContext is not Application."));
            c.a = new AtomicBoolean(false);
            return false;
        }
        if (!com.facebook.ads.internal.w.f.a.a && !((Application)applicationContext).getClass().equals(Application.class)) {
            Log.e("FBAudienceNetwork", "You are using custom Application class and don't call AudienceNetworkAds.isInAdsProcess(). Multi-process support will be disabled. Please call AudienceNetworkAds.isInAdsProcess() if you want to support multi-process mode.");
            com.facebook.ads.internal.w.h.a.a(context, "ipc", b.ae, new Exception("No AudienceNetworkAds.isInAdsProcess() call."));
            c.a = new AtomicBoolean(false);
            return false;
        }
        c.a = new AtomicBoolean(true);
        return true;
    }
}
