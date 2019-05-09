package com.facebook.ads.internal.p017t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.t.g */
public class C2115g {
    /* renamed from: a */
    private final String f4873a;
    /* renamed from: b */
    private final int f4874b;
    /* renamed from: c */
    private final int f4875c;

    public C2115g(String str, int i, int i2) {
        this.f4873a = str;
        this.f4874b = i;
        this.f4875c = i2;
    }

    @Nullable
    /* renamed from: a */
    public static C2115g m5351a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("url");
        return optString != null ? new C2115g(optString, jSONObject.optInt("width", 0), jSONObject.optInt("height", 0)) : null;
    }

    /* renamed from: a */
    public String m5352a() {
        return this.f4873a;
    }

    /* renamed from: b */
    public int m5353b() {
        return this.f4874b;
    }

    /* renamed from: c */
    public int m5354c() {
        return this.f4875c;
    }
}
