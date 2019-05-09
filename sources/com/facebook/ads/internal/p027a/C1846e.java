package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;

/* renamed from: com.facebook.ads.internal.a.e */
public class C1846e {

    /* renamed from: com.facebook.ads.internal.a.e$a */
    public interface C1845a {
        /* renamed from: a */
        C1844d mo5409a();

        /* renamed from: b */
        Collection<String> mo5410b();

        String getClientToken();
    }

    /* renamed from: a */
    public static Collection<String> m4148a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        Set hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(jSONArray.optString(i));
        }
        return hashSet;
    }

    /* renamed from: a */
    public static boolean m4149a(Context context, C1845a c1845a, C2085c c2085c) {
        C1844d a = c1845a.mo5409a();
        if (a == null || a == C1844d.NONE) {
            return false;
        }
        Collection<String> b = c1845a.mo5410b();
        if (b == null || b.isEmpty()) {
            return false;
        }
        boolean z;
        for (String a2 : b) {
            if (C1846e.m4150a(context, a2)) {
                z = true;
                break;
            }
        }
        z = false;
        if (z != (a == C1844d.INSTALLED)) {
            return false;
        }
        Object clientToken = c1845a.getClientToken();
        if (TextUtils.isEmpty(clientToken)) {
            C2625a.m6741b(context, "api", C2626b.f6545j, new Exception("Ad is invalidated without token."));
            return true;
        }
        c2085c.mo5473b(clientToken, null);
        return true;
    }

    /* renamed from: a */
    public static boolean m4150a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        } catch (RuntimeException e2) {
            return false;
        }
    }
}
