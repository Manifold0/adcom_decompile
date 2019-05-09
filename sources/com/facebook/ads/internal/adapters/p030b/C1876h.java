package com.facebook.ads.internal.adapters.p030b;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.h */
public class C1876h implements Serializable {
    /* renamed from: a */
    public static final int f3950a = Color.parseColor("#90949c");
    /* renamed from: b */
    public static final int f3951b = Color.parseColor("#4b4f56");
    /* renamed from: c */
    public static final int f3952c = Color.parseColor("#f6f7f9");
    /* renamed from: d */
    public static final int f3953d = Color.parseColor("#ff4080ff");
    /* renamed from: e */
    public static final int f3954e = Color.parseColor("#23272F");
    /* renamed from: f */
    public static final int f3955f = Color.parseColor("#ff4080ff");
    private static final long serialVersionUID = 8946536326456653736L;
    /* renamed from: g */
    private int f3956g = f3950a;
    /* renamed from: h */
    private int f3957h = f3951b;
    /* renamed from: i */
    private int f3958i = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: j */
    private int f3959j = f3952c;
    /* renamed from: k */
    private int f3960k = f3953d;
    /* renamed from: l */
    private int f3961l = -1;
    /* renamed from: m */
    private int f3962m = ViewCompat.MEASURED_STATE_MASK;

    /* renamed from: a */
    public static C1876h m4285a(JSONObject jSONObject) {
        C1876h c1876h = new C1876h();
        if (jSONObject != null) {
            Object optString = jSONObject.optString("accent_color");
            Object optString2 = jSONObject.optString("body_color");
            Object optString3 = jSONObject.optString("subtitle_color");
            Object optString4 = jSONObject.optString("bg_color");
            Object optString5 = jSONObject.optString("cta_color");
            Object optString6 = jSONObject.optString("cta_text_color");
            Object optString7 = jSONObject.optString("title_color");
            if (!TextUtils.isEmpty(optString)) {
                c1876h.f3956g = Color.parseColor(optString);
            }
            if (!TextUtils.isEmpty(optString2)) {
                c1876h.f3957h = Color.parseColor(optString2);
            }
            if (!TextUtils.isEmpty(optString3)) {
                c1876h.f3958i = Color.parseColor(optString3);
            }
            if (!TextUtils.isEmpty(optString4)) {
                c1876h.f3959j = Color.parseColor(optString4);
            }
            if (!TextUtils.isEmpty(optString5)) {
                c1876h.f3960k = Color.parseColor(optString5);
            }
            if (!TextUtils.isEmpty(optString6)) {
                c1876h.f3961l = Color.parseColor(optString6);
            }
            if (!TextUtils.isEmpty(optString7)) {
                c1876h.f3962m = Color.parseColor(optString7);
            }
        }
        return c1876h;
    }

    /* renamed from: a */
    public int m4286a(boolean z) {
        return z ? -1 : this.f3956g;
    }

    /* renamed from: a */
    public void m4287a(int i) {
        this.f3960k = i;
    }

    /* renamed from: b */
    public int m4288b(boolean z) {
        return z ? -1 : this.f3957h;
    }

    /* renamed from: c */
    public int m4289c(boolean z) {
        return z ? -1 : this.f3958i;
    }

    /* renamed from: d */
    public int m4290d(boolean z) {
        return z ? f3954e : this.f3959j;
    }

    /* renamed from: e */
    public int m4291e(boolean z) {
        return z ? -1 : this.f3960k;
    }

    /* renamed from: f */
    public int m4292f(boolean z) {
        return z ? f3955f : this.f3961l;
    }

    /* renamed from: g */
    public int m4293g(boolean z) {
        return z ? -1 : this.f3962m;
    }
}
