package com.facebook.ads.internal.p045n;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.n.c */
public class C2053c {
    @Nullable
    /* renamed from: a */
    private static AtomicBoolean f4602a;

    /* renamed from: a */
    public static String m5002a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            Object a = C2616a.m6729a(context);
            if (a == null) {
                a = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            }
            jSONObject.put("process_name", a);
            jSONObject.put("is_ads_process", AdInternalSettings.f4779d);
            jSONObject.put("client_supports", C2053c.m5003b(context.getApplicationContext()));
        } catch (Exception e) {
            C2625a.m6741b(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6513D, e);
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    public static boolean m5003b(Context context) {
        if (AdInternalSettings.f4778c) {
            return false;
        }
        if (f4602a != null) {
            return f4602a.get();
        }
        Context applicationContext = context.getApplicationContext();
        if (!(applicationContext instanceof Application)) {
            Log.w(AudienceNetworkAds.TAG, "Multi-process support won't work because application Context is not Application instance.");
            C2625a.m6738a(context, "ipc", C2626b.ad, new Exception("ApplicationContext is not Application."));
            f4602a = new AtomicBoolean(false);
            return false;
        } else if (C2616a.f6479a || ((Application) applicationContext).getClass().equals(Application.class)) {
            f4602a = new AtomicBoolean(true);
            return true;
        } else {
            Log.e(AudienceNetworkAds.TAG, "You are using custom Application class and don't call AudienceNetworkAds.isInAdsProcess(). Multi-process support will be disabled. Please call AudienceNetworkAds.isInAdsProcess() if you want to support multi-process mode.");
            C2625a.m6738a(context, "ipc", C2626b.ae, new Exception("No AudienceNetworkAds.isInAdsProcess() call."));
            f4602a = new AtomicBoolean(false);
            return false;
        }
    }
}
