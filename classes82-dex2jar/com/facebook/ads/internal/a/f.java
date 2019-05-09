// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.a;

import java.util.Locale;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.ComponentName;
import android.text.TextUtils;
import android.content.Intent;
import com.facebook.ads.internal.s.c;
import android.content.Context;
import java.util.Map;
import android.net.Uri;

public class f extends h
{
    private static final String e;
    private final Uri f;
    private final Map<String, String> g;
    private final boolean h;
    
    static {
        e = f.class.getSimpleName();
    }
    
    public f(final Context context, final c c, final String s, final Uri f, final Map<String, String> g, final m m, final boolean h) {
        super(context, c, s, m);
        this.f = f;
        this.g = g;
        this.h = h;
    }
    
    private Intent a(final g g) {
        if (TextUtils.isEmpty((CharSequence)g.a())) {
            return null;
        }
        if (!com.facebook.ads.internal.a.e.a(this.a, g.a())) {
            return null;
        }
        final String c = g.c();
        if (!TextUtils.isEmpty((CharSequence)c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        final PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty((CharSequence)g.b()) && TextUtils.isEmpty((CharSequence)c)) {
            return packageManager.getLaunchIntentForPackage(g.a());
        }
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        if (!TextUtils.isEmpty((CharSequence)g.a()) && !TextUtils.isEmpty((CharSequence)g.b())) {
            intent.setComponent(new ComponentName(g.a(), g.b()));
        }
        if (!TextUtils.isEmpty((CharSequence)g.c())) {
            intent.setData(Uri.parse(g.c()));
        }
        final List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (intent.getComponent() == null) {
            for (final ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(g.a())) {
                    intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        if (queryIntentActivities.isEmpty() || intent.getComponent() == null) {
            return null;
        }
        return intent;
    }
    
    private List<g> f() {
        final String queryParameter = this.f.getQueryParameter("appsite_data");
        List<g> list;
        if (TextUtils.isEmpty((CharSequence)queryParameter) || "[]".equals(queryParameter)) {
            list = null;
        }
        else {
            final ArrayList<g> list2 = new ArrayList<g>();
            try {
                final JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
                list = list2;
                if (optJSONArray != null) {
                    int n = 0;
                    while (true) {
                        list = list2;
                        if (n >= optJSONArray.length()) {
                            break;
                        }
                        final g a = com.facebook.ads.internal.a.g.a(optJSONArray.optJSONObject(n));
                        if (a != null) {
                            list2.add(a);
                        }
                        ++n;
                    }
                }
            }
            catch (JSONException ex) {
                Log.w(com.facebook.ads.internal.a.f.e, "Error parsing appsite_data", (Throwable)ex);
                return list2;
            }
        }
        return list;
    }
    
    private boolean g() {
        final List<Intent> d = this.d();
        if (d == null) {
            return false;
        }
        for (final Intent intent : d) {
            try {
                this.a.startActivity(intent);
                return true;
            }
            catch (Exception ex) {
                Log.d(com.facebook.ads.internal.a.f.e, "Failed to open app intent, falling back", (Throwable)ex);
                continue;
            }
            break;
        }
        return false;
    }
    
    private boolean h() {
        final com.facebook.ads.internal.w.e.g g = new com.facebook.ads.internal.w.e.g();
        try {
            com.facebook.ads.internal.w.e.g.a(g, this.a, this.c(), this.c);
            return true;
        }
        catch (Exception ex) {
            Log.d(com.facebook.ads.internal.a.f.e, "Failed to open market url: " + this.f.toString(), (Throwable)ex);
            final String queryParameter = this.f.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                com.facebook.ads.internal.w.e.g.a(g, this.a, Uri.parse(queryParameter), this.c);
            }
            return false;
        }
    }
    
    @Nullable
    @Override
    public a b() {
        final a a = null;
        String s = "opened_deeplink";
        a a2 = a;
    Label_0032:
        while (true) {
            if (this.g()) {
                break Label_0032;
            }
            while (true) {
                try {
                    if (this.h()) {
                        s = "opened_store_url";
                        a2 = a;
                    }
                    else {
                        s = "opened_store_fallback_url";
                        a2 = a;
                    }
                    this.g.put(s, String.valueOf(true));
                    return a2;
                }
                catch (Exception ex) {
                    Log.d(com.facebook.ads.internal.a.f.e, "Failed to open all options including fallback url, can't open anything");
                    a2 = com.facebook.ads.internal.a.a.a;
                    s = s;
                    continue Label_0032;
                }
                continue Label_0032;
            }
            break;
        }
    }
    
    protected Uri c() {
        final String queryParameter = this.f.getQueryParameter("store_url");
        if (!TextUtils.isEmpty((CharSequence)queryParameter)) {
            return Uri.parse(queryParameter);
        }
        return Uri.parse(String.format(Locale.US, "market://details?id=%s", this.f.getQueryParameter("store_id")));
    }
    
    protected List<Intent> d() {
        final List<g> f = this.f();
        final ArrayList<Intent> list = new ArrayList<Intent>();
        if (f != null) {
            final Iterator<g> iterator = f.iterator();
            while (iterator.hasNext()) {
                final Intent a = this.a(iterator.next());
                if (a != null) {
                    list.add(a);
                }
            }
        }
        return list;
    }
    
    @Override
    void e() {
        a b = null;
        if (!this.h) {
            b = this.b();
        }
        else {
            this.g.put("opened_store_url", String.valueOf(true));
        }
        this.a(this.g, b);
    }
}
