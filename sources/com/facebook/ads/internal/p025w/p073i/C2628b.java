package com.facebook.ads.internal.p025w.p073i;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import com.facebook.ads.internal.p025w.p026b.C2601z;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.w.i.b */
public class C2628b {
    /* renamed from: a */
    private static final String f6563a = C2628b.class.getSimpleName();

    /* renamed from: a */
    public static Map<String, String> m6745a(Context context) {
        Map<String, String> hashMap = new HashMap();
        if (context == null) {
            Log.v(f6563a, "Null context in window interactive check.");
            return hashMap;
        }
        try {
            String str = "kgr";
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            boolean z = keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
            hashMap.put(str, String.valueOf(z));
            if (context instanceof Activity) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    int i = window.getAttributes().flags;
                    hashMap.put("wt", Integer.toString(window.getAttributes().type));
                    hashMap.put("wfdkg", (4194304 & i) > 0 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    hashMap.put("wfswl", (524288 & i) > 0 ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                } else {
                    Log.v(f6563a, "Invalid window in window interactive check, assuming interactive.");
                }
            } else {
                Log.v(f6563a, "Invalid Activity context in window interactive check, assuming interactive.");
            }
        } catch (Throwable e) {
            Log.e(f6563a, "Exception in window info check", e);
            C2625a.m6741b(context, "risky", C2626b.f6517H, e);
        }
        return hashMap;
    }

    /* renamed from: b */
    public static boolean m6746b(Context context) {
        return !C2601z.m6693b(C2628b.m6745a(context));
    }
}
