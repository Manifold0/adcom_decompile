package com.facebook.ads.internal.adapters.p030b;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.p019c.C2253f;
import com.facebook.share.internal.ShareConstants;
import com.ironsource.sdk.precache.DownloadManager;
import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.n */
public class C1884n implements Serializable {
    /* renamed from: a */
    private static final C2253f f3990a = C2253f.PORTRAIT;
    /* renamed from: b */
    private static final C1886p f3991b = C1886p.FILE_PRECACHE;
    private static final long serialVersionUID = -5352540123250859603L;
    /* renamed from: c */
    private final String f3992c;
    /* renamed from: d */
    private final String f3993d;
    /* renamed from: e */
    private final int f3994e;
    /* renamed from: f */
    private final String f3995f;
    /* renamed from: g */
    private final String f3996g;
    /* renamed from: h */
    private final int f3997h;
    /* renamed from: i */
    private final boolean f3998i;
    /* renamed from: j */
    private final boolean f3999j;
    /* renamed from: k */
    private final C2253f f4000k;
    /* renamed from: l */
    private final C1886p f4001l;
    /* renamed from: m */
    private String f4002m;

    private C1884n(String str, String str2, int i, String str3, String str4, C2253f c2253f, int i2, boolean z, boolean z2, C1886p c1886p) {
        this.f3992c = str;
        this.f3993d = str2;
        this.f3994e = i;
        this.f3995f = str3;
        this.f3996g = str4;
        this.f4000k = c2253f;
        this.f3997h = i2;
        this.f3998i = z;
        this.f3999j = z2;
        this.f4001l = c1886p;
    }

    @Nullable
    /* renamed from: a */
    public static C1884n m4332a(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("playable_data");
        if (optJSONObject == null) {
            return null;
        }
        C1886p a = C1886p.m4354a(optJSONObject.optString("precaching_method", f3991b.name()));
        String optString = optJSONObject.optString(ShareConstants.MEDIA_URI);
        String optString2 = optJSONObject.optString("intro_card_icon_url");
        int optInt = jSONObject.has("skippable_seconds") ? jSONObject.optInt("skippable_seconds") : jSONObject.optInt("unskippable_seconds", 0);
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("generic_text");
        String optString3 = optJSONObject2 == null ? "Rewarded Play" : optJSONObject2.optString("rewarded_play_text", "Rewarded Play");
        JSONObject optJSONObject3 = optJSONObject.optJSONObject("generic_text");
        return new C1884n(optString, optString2, optInt, optString3, optJSONObject3 == null ? "Play Store will automatically open in [secs]s" : optJSONObject3.optString("delay_click_text", "Play Store will automatically open in [secs]s"), C2253f.m5778a(optJSONObject.optInt("orientation", f3990a.m5779a())), optJSONObject.optInt("web_view_timeout_in_milliseconds", DownloadManager.OPERATION_TIMEOUT), optJSONObject.optBoolean("enable_intro_card", true), optJSONObject.optBoolean("enable_end_card"), a);
    }

    /* renamed from: a */
    public String m4333a() {
        return this.f3992c;
    }

    /* renamed from: a */
    public void m4334a(String str) {
        this.f4002m = str;
    }

    /* renamed from: b */
    public String m4335b() {
        return this.f3993d;
    }

    /* renamed from: c */
    public int m4336c() {
        return this.f3994e;
    }

    /* renamed from: d */
    public String m4337d() {
        return this.f3995f;
    }

    /* renamed from: e */
    public String m4338e() {
        return this.f3996g;
    }

    /* renamed from: f */
    public C2253f m4339f() {
        return this.f4000k;
    }

    /* renamed from: g */
    public int m4340g() {
        return this.f3997h;
    }

    /* renamed from: h */
    public boolean m4341h() {
        return this.f3998i;
    }

    /* renamed from: i */
    public boolean m4342i() {
        return this.f3999j;
    }

    /* renamed from: j */
    public String m4343j() {
        return this.f4002m;
    }

    /* renamed from: k */
    public C1886p m4344k() {
        return this.f4001l;
    }
}
