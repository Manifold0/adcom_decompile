package com.applovin.impl.sdk;

import android.net.Uri;
import com.applovin.impl.adview.al;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import org.json.JSONObject;

/* renamed from: com.applovin.impl.sdk.m */
public final class C1286m extends an {
    public C1286m(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super(jSONObject, jSONObject2, appLovinSdkImpl);
    }

    private String an() {
        return bu.m2389a(this.a, "stream_url", "", this.c);
    }

    /* renamed from: a */
    public String mo4000a() {
        return bu.m2389a(this.a, String.HTML, null, this.c);
    }

    /* renamed from: a */
    public void m3022a(Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("video", uri.toString());
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public void m3023a(String str) {
        try {
            synchronized (this.d) {
                this.a.put(String.HTML, str);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public boolean mo4001b() {
        return this.a.has("stream_url");
    }

    /* renamed from: c */
    public void m3025c() {
        synchronized (this.d) {
            this.a.remove("stream_url");
        }
    }

    /* renamed from: d */
    public Uri mo4002d() {
        String an = an();
        if (AppLovinSdkUtils.isValidString(an)) {
            return Uri.parse(an);
        }
        an = m3027e();
        return AppLovinSdkUtils.isValidString(an) ? Uri.parse(an) : null;
    }

    /* renamed from: e */
    public String m3027e() {
        return bu.m2389a(this.a, "video", "", this.c);
    }

    /* renamed from: f */
    public boolean mo3992f() {
        return mo4002d() != null;
    }

    /* renamed from: g */
    public Uri mo4003g() {
        String a = bu.m2389a(this.a, TapjoyConstants.TJC_CLICK_URL, "", this.c);
        return AppLovinSdkUtils.isValidString(a) ? Uri.parse(a) : null;
    }

    /* renamed from: h */
    public float m3030h() {
        return bu.m2384a(this.a, "mraid_close_delay_graphic", 0.0f, this.c);
    }

    /* renamed from: i */
    public boolean m3031i() {
        return bu.m2387a(this.a, "close_button_graphic_hidden", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: j */
    public boolean m3032j() {
        return this.a.has("close_button_expandable_hidden") ? bu.m2387a(this.a, "close_button_expandable_hidden", Boolean.valueOf(false), this.c).booleanValue() : true;
    }

    /* renamed from: k */
    public al m3033k() {
        return m1814a(bu.m2385a(this.a, "expandable_style", al.Invisible.ordinal(), this.c));
    }
}
