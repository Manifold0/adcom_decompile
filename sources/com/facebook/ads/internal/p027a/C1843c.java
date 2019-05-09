package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p051s.C2085c;
import com.tapjoy.TapjoyConstants;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.a.c */
public class C1843c {
    /* renamed from: a */
    private static final String f3839a = C1843c.class.getSimpleName();

    @Nullable
    /* renamed from: a */
    public static C1842b m4142a(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map) {
        return C1843c.m4143a(context, c2085c, str, uri, map, false, false);
    }

    @Nullable
    /* renamed from: a */
    public static C1842b m4143a(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map, boolean z, boolean z2) {
        if (uri == null || uri.getAuthority() == null) {
            return null;
        }
        String authority = uri.getAuthority();
        String queryParameter = uri.getQueryParameter(TapjoyConstants.TJC_VIDEO_URL);
        if (!TextUtils.isEmpty(uri.getQueryParameter("data"))) {
            try {
                JSONObject jSONObject = new JSONObject(uri.getQueryParameter("data"));
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    map.put(str2, jSONObject.getString(str2));
                }
            } catch (Throwable e) {
                Log.w(f3839a, "Unable to parse json data in AdActionFactory.", e);
            }
        }
        C1855m a = C1855m.m4173a(c2085c, C2564b.m6613a());
        Object obj = -1;
        switch (authority.hashCode()) {
            case -1458789996:
                if (authority.equals("passthrough")) {
                    obj = 2;
                    break;
                }
                break;
            case 109770977:
                if (authority.equals("store")) {
                    obj = null;
                    break;
                }
                break;
            case 1546100943:
                if (authority.equals("open_link")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return queryParameter != null ? null : new C1848f(context, c2085c, str, uri, map, a, z);
            case 1:
                return z2 ? new C1850i(context, c2085c, str, uri, map) : new C1851j(context, c2085c, str, uri, map, a);
            case 2:
                return new C1852k(context, c2085c, str, uri, map);
            default:
                return new C1853l(context, c2085c, str, uri);
        }
    }

    /* renamed from: a */
    public static boolean m4144a(String str) {
        return "store".equalsIgnoreCase(str) || "open_link".equalsIgnoreCase(str);
    }
}
