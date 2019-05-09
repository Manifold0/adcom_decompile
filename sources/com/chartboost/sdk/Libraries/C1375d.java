package com.chartboost.sdk.Libraries;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.util.Base64;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.impl.ar;
import com.chartboost.sdk.impl.as;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.network.VungleApiClient;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Libraries.d */
public class C1375d {
    /* renamed from: a */
    private int f2685a = -1;
    /* renamed from: b */
    private String f2686b = null;
    /* renamed from: c */
    private final String f2687c;

    /* renamed from: com.chartboost.sdk.Libraries.d$a */
    public static class C1374a {
        /* renamed from: a */
        public final int f2681a;
        /* renamed from: b */
        public final String f2682b;
        /* renamed from: c */
        public final String f2683c;
        /* renamed from: d */
        public final String f2684d;

        public C1374a(int i, String str, String str2, String str3) {
            this.f2681a = i;
            this.f2682b = str;
            this.f2683c = str2;
            this.f2684d = str3;
        }
    }

    public C1375d(Context context) {
        this.f2687c = ar.m3413b(context);
    }

    /* renamed from: a */
    public synchronized C1374a m3127a() {
        C1374a c1374a;
        if (Looper.myLooper() != Looper.getMainLooper() || "robolectric".equals(Build.FINGERPRINT)) {
            String str;
            if (C1375d.m3125b()) {
                m3126c();
            } else {
                m3124a(C1410i.f2936m);
            }
            String str2 = this.f2686b;
            JSONObject jSONObject = new JSONObject();
            if (this.f2687c != null && str2 == null) {
                C1377e.m3131a(jSONObject, "uuid", this.f2687c);
            }
            if (str2 != null) {
                C1377e.m3131a(jSONObject, "gaid", str2);
            }
            int i = this.f2685a;
            String encodeToString = Base64.encodeToString(jSONObject.toString().getBytes(), 0);
            if (str2 != null) {
                str = "000000000";
            } else {
                str = this.f2687c;
            }
            c1374a = new C1374a(i, encodeToString, str, str2);
        } else {
            CBLogging.m3099b("CBIdentity", "I must be called from a background thread");
            c1374a = null;
        }
        return c1374a;
    }

    /* renamed from: b */
    private static boolean m3125b() {
        return !VungleApiClient.MANUFACTURER_AMAZON.equalsIgnoreCase(Build.MANUFACTURER);
    }

    /* renamed from: c */
    private void m3126c() {
        if (as.m3415a(C1410i.f2936m)) {
            C1371a c1371a = new C1371a(C1410i.f2936m);
            this.f2685a = c1371a.f2676a;
            this.f2686b = c1371a.f2677b;
        }
    }

    /* renamed from: a */
    private void m3124a(Context context) {
        Object obj = 1;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (Secure.getInt(contentResolver, "limit_ad_tracking") == 0) {
                obj = null;
            }
            if (obj == null) {
                String string = Secure.getString(contentResolver, TapjoyConstants.TJC_ADVERTISING_ID);
                if ("00000000-0000-0000-0000-000000000000".equals(string)) {
                    this.f2685a = 1;
                    this.f2686b = null;
                    return;
                }
                this.f2685a = 0;
                this.f2686b = string;
                return;
            }
            this.f2685a = 1;
            this.f2686b = null;
        } catch (SettingNotFoundException e) {
            this.f2685a = -1;
            this.f2686b = null;
        }
    }
}
