package com.applovin.impl.sdk;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.ViewCompat;
import com.applovin.impl.adview.al;
import com.applovin.impl.adview.cm;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TapjoyConstants;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

public abstract class an extends C1227q {
    /* renamed from: e */
    private final AtomicBoolean f1578e = new AtomicBoolean();

    public an(JSONObject jSONObject, JSONObject jSONObject2, AppLovinSdkImpl appLovinSdkImpl) {
        super(jSONObject, jSONObject2, appLovinSdkImpl);
    }

    /* renamed from: a */
    private float m1786a(AppLovinAdType appLovinAdType, float f, boolean z) {
        return appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) ? 0.5f : (appLovinAdType.equals(AppLovinAdType.REGULAR) && z && f == -1.0f) ? 0.5f : 0.0f;
    }

    /* renamed from: a */
    private al m1787a(boolean z) {
        return z ? al.WhiteXOnTransparentGrey : al.WhiteXOnOpaqueBlack;
    }

    /* renamed from: A */
    public String m1788A() {
        return bu.m2389a(this.a, "video_end_url", "", this.c);
    }

    /* renamed from: B */
    public boolean m1789B() {
        return bu.m2387a(this.a, "dismiss_on_skip", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: C */
    public String m1790C() {
        JSONObject a = bu.m2395a(this.a, "video_button_properties", null, this.c);
        return a != null ? bu.m2389a(a, "video_button_html", "", this.c) : "";
    }

    /* renamed from: D */
    public cm m1791D() {
        return new cm(bu.m2395a(this.a, "video_button_properties", null, this.c), this.c);
    }

    /* renamed from: E */
    public boolean mo3999E() {
        return bu.m2387a(this.a, "video_clickable", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: F */
    public boolean m1793F() {
        return bu.m2387a(this.a, "accelerate_hardware", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: G */
    public boolean m1794G() {
        return bu.m2387a(this.a, "hide_close_on_exit_graphic", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: H */
    public boolean m1795H() {
        return bu.m2387a(this.a, "hide_close_on_exit", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: I */
    public boolean m1796I() {
        return bu.m2387a(this.a, "lock_current_orientation", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: J */
    public int m1797J() {
        return bu.m2385a(this.a, "countdown_length", 0, this.c);
    }

    /* renamed from: K */
    public int m1798K() {
        int parseColor = Color.parseColor("#C8FFFFFF");
        String a = bu.m2389a(this.a, "countdown_color", null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                parseColor = Color.parseColor(a);
            } catch (Throwable th) {
                this.c.getLogger().mo4174e("DirectAd", "Unable to parse countdown color", th);
            }
        }
        return parseColor;
    }

    /* renamed from: L */
    public int m1799L() {
        int i = ViewCompat.MEASURED_STATE_MASK;
        String a = bu.m2389a(this.a, "video_background_color", null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: M */
    public int m1800M() {
        int i = mo3992f() ? ViewCompat.MEASURED_STATE_MASK : -1157627904;
        String a = bu.m2389a(this.a, "graphic_background_color", null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: N */
    public ao m1801N() {
        String a = bu.m2389a(this.a, "poststitial_dismiss_type", null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equalsIgnoreCase(a)) {
                return ao.DISMISS;
            }
            if ("no_dismiss".equalsIgnoreCase(a)) {
                return ao.DO_NOT_DISMISS;
            }
        }
        return ao.UNSPECIFIED;
    }

    /* renamed from: O */
    public List<String> m1802O() {
        String a = bu.m2389a(this.a, "resource_cache_prefix", null, this.c);
        return a != null ? aa.m2193a(a) : this.c.getAsList(ea.f2389M);
    }

    /* renamed from: P */
    public String m1803P() {
        return bu.m2389a(this.a, "cache_prefix", null, this.c);
    }

    /* renamed from: Q */
    public boolean m1804Q() {
        return bu.m2387a(this.a, "progress_bar_enabled", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: R */
    public int m1805R() {
        String a = bu.m2389a(this.a, "progress_bar_color", "#C8FFFFFF", this.c);
        int i = 0;
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                i = Color.parseColor(a);
            } catch (Throwable th) {
            }
        }
        return i;
    }

    /* renamed from: S */
    public int m1806S() {
        return gd.m2932a(this.a);
    }

    /* renamed from: T */
    public int m1807T() {
        return bu.m2385a(this.a, "poststitial_shown_forward_delay_millis", -1, this.c);
    }

    /* renamed from: U */
    public int m1808U() {
        return bu.m2385a(this.a, "close_button_size", ((Integer) this.c.get(ea.aR)).intValue(), this.c);
    }

    /* renamed from: V */
    public int m1809V() {
        return bu.m2385a(this.a, "close_button_top_margin", ((Integer) this.c.get(ea.aS)).intValue(), this.c);
    }

    /* renamed from: W */
    public int m1810W() {
        return bu.m2385a(this.a, "close_button_horizontal_margin", ((Integer) this.c.get(ea.aT)).intValue(), this.c);
    }

    /* renamed from: X */
    public boolean m1811X() {
        return bu.m2387a(this.a, "lhs_close_button", (Boolean) this.c.get(ea.bB), this.c).booleanValue();
    }

    /* renamed from: Y */
    public boolean m1812Y() {
        return bu.m2387a(this.a, "lhs_skip_button", (Boolean) this.c.get(ea.bC), this.c).booleanValue();
    }

    /* renamed from: Z */
    public boolean m1813Z() {
        return bu.m2387a(this.a, "stop_video_player_after_poststitial_render", Boolean.valueOf(false), this.c).booleanValue();
    }

    /* renamed from: a */
    protected al m1814a(int i) {
        return i == 1 ? al.WhiteXOnTransparentGrey : i == 2 ? al.Invisible : al.WhiteXOnOpaqueBlack;
    }

    /* renamed from: a */
    public String m1815a(int i, String str, boolean z) {
        String A = m1788A();
        return AppLovinSdkUtils.isValidString(A) ? gd.m2940a(str, Uri.parse(A.replace("{CLCODE}", mo3996n())).buildUpon().appendQueryParameter(NativeAdImpl.QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter("vid_ts", Long.toString(System.currentTimeMillis())).appendQueryParameter("uvs", Boolean.toString(z)).build().toString()) : "";
    }

    public boolean aa() {
        return bu.m2387a(this.a, "unhide_adview_on_render", Boolean.valueOf(false), this.c).booleanValue();
    }

    public long ab() {
        long a = bu.m2386a(this.a, "report_reward_duration", -1, this.c);
        return a >= 0 ? TimeUnit.SECONDS.toMillis(a) : -1;
    }

    public int ac() {
        return bu.m2385a(this.a, "report_reward_percent", -1, this.c);
    }

    public boolean ad() {
        return bu.m2387a(this.a, "report_reward_percent_include_close_delay", Boolean.valueOf(true), this.c).booleanValue();
    }

    public AtomicBoolean ae() {
        return this.f1578e;
    }

    public boolean af() {
        return bu.m2387a(this.a, "render_poststitial_on_attach", Boolean.valueOf(false), this.c).booleanValue();
    }

    public boolean ag() {
        return bu.m2387a(this.a, "playback_requires_user_action", Boolean.valueOf(true), this.c).booleanValue();
    }

    public boolean ah() {
        return bu.m2387a(this.a, "sanitize_webview", Boolean.valueOf(false), this.c).booleanValue();
    }

    public String ai() {
        String a = bu.m2389a(this.a, "base_url", "/", this.c);
        return "null".equalsIgnoreCase(a) ? null : a;
    }

    public boolean aj() {
        return bu.m2387a(this.a, "web_contents_debugging_enabled", Boolean.valueOf(false), this.c).booleanValue();
    }

    public ge ak() {
        JSONObject a = bu.m2395a(this.a, "web_view_settings", null, this.c);
        return a != null ? new ge(a, this.c) : null;
    }

    public Uri al() {
        Uri uri = null;
        String a = bu.m2389a(this.a, "mute_image", (String) uri, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                uri = Uri.parse(a);
            } catch (Throwable th) {
            }
        }
        return uri;
    }

    public Uri am() {
        Uri uri = null;
        String a = bu.m2389a(this.a, "unmute_image", "", this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            try {
                uri = Uri.parse(a);
            } catch (Throwable th) {
            }
        }
        return uri;
    }

    /* renamed from: b */
    public String m1816b(String str) {
        String a = bu.m2389a(this.a, "click_tracking_url", "", this.c);
        return AppLovinSdkUtils.isValidString(a) ? gd.m2940a(str, a.replace("{CLCODE}", mo3996n())) : "";
    }

    /* renamed from: b */
    public void m1817b(Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("mute_image", uri);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public boolean mo4001b() {
        this.c.getLogger().mo4173e("DirectAd", "Attempting to invoke isVideoStream() from base ad class");
        return false;
    }

    /* renamed from: c */
    public void m1819c(Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("unmute_image", uri);
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    public Uri mo4002d() {
        this.c.getLogger().mo4173e("DirectAd", "Attempting to invoke getVideoUri() from base ad class");
        return null;
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ boolean mo3992f() {
        return super.mo3992f();
    }

    /* renamed from: g */
    public Uri mo4003g() {
        this.c.getLogger().mo4173e("DirectAd", "Attempting to invoke getClickDestinationUri() from base ad class");
        return null;
    }

    public /* bridge */ /* synthetic */ long getAdIdNumber() {
        return super.getAdIdNumber();
    }

    public /* bridge */ /* synthetic */ String getAdValue(String str) {
        return super.getAdValue(str);
    }

    public /* bridge */ /* synthetic */ AppLovinAdSize getSize() {
        return super.getSize();
    }

    public /* bridge */ /* synthetic */ AppLovinAdType getType() {
        return super.getType();
    }

    public /* bridge */ /* synthetic */ String getZoneId() {
        return super.getZoneId();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isVideoAd() {
        return super.isVideoAd();
    }

    /* renamed from: l */
    public /* bridge */ /* synthetic */ long mo3994l() {
        return super.mo3994l();
    }

    /* renamed from: m */
    public /* bridge */ /* synthetic */ C1288o mo3995m() {
        return super.mo3995m();
    }

    /* renamed from: n */
    public /* bridge */ /* synthetic */ String mo3996n() {
        return super.mo3996n();
    }

    /* renamed from: t */
    public /* bridge */ /* synthetic */ C1287n mo3997t() {
        return super.mo3997t();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* renamed from: v */
    public ap m1827v() {
        String toUpperCase = bu.m2389a(this.a, "ad_target", ap.f2067a.toString(), this.c).toUpperCase(Locale.ENGLISH);
        return "ACTIVITY_PORTRAIT".equalsIgnoreCase(toUpperCase) ? ap.ACTIVITY_PORTRAIT : "ACTIVITY_LANDSCAPE".equalsIgnoreCase(toUpperCase) ? ap.ACTIVITY_LANDSCAPE : ap.f2067a;
    }

    /* renamed from: w */
    public float m1828w() {
        return bu.m2384a(this.a, "close_delay", 0.0f, this.c);
    }

    /* renamed from: x */
    public float m1829x() {
        return bu.m2384a(this.a, "close_delay_graphic", m1786a(getType(), m1828w(), mo3992f()), this.c);
    }

    /* renamed from: y */
    public al m1830y() {
        int a = bu.m2385a(this.a, "close_style", -1, this.c);
        return a == -1 ? m1787a(mo3992f()) : m1814a(a);
    }

    /* renamed from: z */
    public al m1831z() {
        int a = bu.m2385a(this.a, "skip_style", -1, this.c);
        return a == -1 ? m1830y() : m1814a(a);
    }
}
