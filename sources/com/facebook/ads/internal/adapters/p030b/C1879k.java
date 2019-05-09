package com.facebook.ads.internal.adapters.p030b;

import android.content.Context;
import com.facebook.ads.internal.adapters.p030b.C1883m.C1882a;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.unity.purchasing.googleplay.Consts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.k */
public class C1879k implements Serializable {
    private static final long serialVersionUID = 8751287062553772011L;
    /* renamed from: a */
    private final C1883m f3969a;
    /* renamed from: b */
    private final C1865b f3970b;
    /* renamed from: c */
    private final List<C1880l> f3971c;
    /* renamed from: d */
    private final int f3972d;
    /* renamed from: e */
    private final int f3973e;
    /* renamed from: f */
    private final String f3974f;
    /* renamed from: g */
    private final String f3975g;
    /* renamed from: h */
    private int f3976h = 200;
    /* renamed from: i */
    private String f3977i;

    private C1879k(C1883m c1883m, C1865b c1865b, List<C1880l> list, String str, String str2, int i, int i2) {
        this.f3969a = c1883m;
        this.f3970b = c1865b;
        this.f3971c = list;
        this.f3974f = str;
        this.f3975g = str2;
        this.f3972d = i;
        this.f3973e = i2;
    }

    /* renamed from: a */
    public static C1879k m4302a(JSONObject jSONObject, Context context) {
        JSONObject jSONObject2 = null;
        C1882a c = new C1882a().m4323a(jSONObject.optString("title")).m4325b(jSONObject.optJSONObject("icon") != null ? jSONObject.optJSONObject("icon").optString("url") : "").m4326c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        C1883m a = c.m4327d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).m4324a();
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        C1876h a2 = C1876h.m4285a(optJSONObject2 != null ? optJSONObject2.optJSONObject("portrait") : null);
        if (optJSONObject2 != null) {
            jSONObject2 = optJSONObject2.optJSONObject("landscape");
        }
        C1865b c1865b = new C1865b(a2, C1876h.m4285a(jSONObject2));
        int optInt = jSONObject.optInt("viewability_check_initial_delay", 0);
        int optInt2 = jSONObject.optInt("viewability_check_interval", 1000);
        String optString = jSONObject.optString("ct");
        String optString2 = jSONObject.optString(Consts.INAPP_REQUEST_ID, "");
        JSONArray optJSONArray = jSONObject.optJSONArray("carousel");
        List arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            arrayList.add(C1880l.m4314a(jSONObject));
        } else {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(C1880l.m4314a(optJSONArray.getJSONObject(i)));
                } catch (Exception e) {
                    C2625a.m6741b(context, "parsing", C2626b.f6520K, e);
                    e.printStackTrace();
                }
            }
        }
        return new C1879k(a, c1865b, arrayList, optString, optString2, optInt, optInt2);
    }

    /* renamed from: a */
    public C1883m m4303a() {
        return this.f3969a;
    }

    /* renamed from: a */
    public void m4304a(int i) {
        this.f3976h = i;
    }

    /* renamed from: a */
    public void m4305a(String str) {
        this.f3977i = str;
    }

    /* renamed from: b */
    public C1865b m4306b() {
        return this.f3970b;
    }

    /* renamed from: c */
    public String m4307c() {
        return this.f3974f;
    }

    /* renamed from: d */
    public List<C1880l> m4308d() {
        return Collections.unmodifiableList(this.f3971c);
    }

    /* renamed from: e */
    public String m4309e() {
        return this.f3975g;
    }

    /* renamed from: f */
    public int m4310f() {
        return this.f3972d;
    }

    /* renamed from: g */
    public int m4311g() {
        return this.f3973e;
    }

    /* renamed from: h */
    public int m4312h() {
        return this.f3976h;
    }

    /* renamed from: i */
    public String m4313i() {
        return this.f3977i;
    }
}
