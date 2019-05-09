package com.facebook.ads.internal.p027a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p051s.C2085c;
import com.google.android.gms.drive.DriveFile;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.a.f */
public class C1848f extends C1847h {
    /* renamed from: e */
    private static final String f3845e = C1848f.class.getSimpleName();
    /* renamed from: f */
    private final Uri f3846f;
    /* renamed from: g */
    private final Map<String, String> f3847g;
    /* renamed from: h */
    private final boolean f3848h;

    public C1848f(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map, C1855m c1855m, boolean z) {
        super(context, c2085c, str, c1855m);
        this.f3846f = uri;
        this.f3847g = map;
        this.f3848h = z;
    }

    /* renamed from: a */
    private Intent m4154a(C1849g c1849g) {
        if (TextUtils.isEmpty(c1849g.m4163a())) {
            return null;
        }
        if (!C1846e.m4150a(this.a, c1849g.m4163a())) {
            return null;
        }
        CharSequence c = c1849g.m4165c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(c1849g.m4164b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(c1849g.m4163a());
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        if (!(TextUtils.isEmpty(c1849g.m4163a()) || TextUtils.isEmpty(c1849g.m4164b()))) {
            intent.setComponent(new ComponentName(c1849g.m4163a(), c1849g.m4164b()));
        }
        if (!TextUtils.isEmpty(c1849g.m4165c())) {
            intent.setData(Uri.parse(c1849g.m4165c()));
        }
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (intent.getComponent() == null) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(c1849g.m4163a())) {
                    intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        return (queryIntentActivities.isEmpty() || intent.getComponent() == null) ? null : intent;
    }

    /* renamed from: f */
    private List<C1849g> m4155f() {
        Object queryParameter = this.f3846f.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        List<C1849g> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray(TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
            if (optJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1849g a = C1849g.m4162a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            Log.w(f3845e, "Error parsing appsite_data", e);
            return arrayList;
        }
    }

    /* renamed from: g */
    private boolean m4156g() {
        List<Intent> d = m4160d();
        if (d == null) {
            return false;
        }
        for (Intent startActivity : d) {
            try {
                this.a.startActivity(startActivity);
                return true;
            } catch (Throwable e) {
                Log.d(f3845e, "Failed to open app intent, falling back", e);
            }
        }
        return false;
    }

    /* renamed from: h */
    private boolean m4157h() {
        C2615g c2615g = new C2615g();
        try {
            C2615g.m6721a(c2615g, this.a, m4159c(), this.c);
            return true;
        } catch (Throwable e) {
            Log.d(f3845e, "Failed to open market url: " + this.f3846f.toString(), e);
            String queryParameter = this.f3846f.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                C2615g.m6721a(c2615g, this.a, Uri.parse(queryParameter), this.c);
            }
            return false;
        }
    }

    @Nullable
    /* renamed from: b */
    public C1841a mo5377b() {
        C1841a c1841a = null;
        Object obj = "opened_deeplink";
        if (!m4156g()) {
            try {
                obj = m4157h() ? "opened_store_url" : "opened_store_fallback_url";
            } catch (Exception e) {
                Log.d(f3845e, "Failed to open all options including fallback url, can't open anything");
                c1841a = C1841a.CANNOT_OPEN;
            }
        }
        this.f3847g.put(obj, String.valueOf(true));
        return c1841a;
    }

    /* renamed from: c */
    protected Uri m4159c() {
        Object queryParameter = this.f3846f.getQueryParameter("store_url");
        if (!TextUtils.isEmpty(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        String queryParameter2 = this.f3846f.getQueryParameter(AppLovinEventParameters.IN_APP_PURCHASE_TRANSACTION_IDENTIFIER);
        return Uri.parse(String.format(Locale.US, "market://details?id=%s", new Object[]{queryParameter2}));
    }

    /* renamed from: d */
    protected List<Intent> m4160d() {
        List<C1849g> f = m4155f();
        List<Intent> arrayList = new ArrayList();
        if (f != null) {
            for (C1849g a : f) {
                Intent a2 = m4154a(a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    void mo5378e() {
        C1841a c1841a = null;
        if (this.f3848h) {
            this.f3847g.put("opened_store_url", String.valueOf(true));
        } else {
            c1841a = mo5377b();
        }
        m4152a(this.f3847g, c1841a);
    }
}
