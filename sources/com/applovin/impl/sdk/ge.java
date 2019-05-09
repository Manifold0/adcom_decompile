package com.applovin.impl.sdk;

import android.webkit.WebSettings.PluginState;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;

public final class ge {
    /* renamed from: a */
    private AppLovinSdk f2568a;
    /* renamed from: b */
    private JSONObject f2569b;

    ge(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.f2568a = appLovinSdk;
        this.f2569b = jSONObject;
    }

    /* renamed from: a */
    public Integer m2958a() {
        String a = bu.m2389a(this.f2569b, "mixed_content_mode", null, this.f2568a);
        return AppLovinSdkUtils.isValidString(a) ? "always_allow".equalsIgnoreCase(a) ? Integer.valueOf(0) : "never_allow".equalsIgnoreCase(a) ? Integer.valueOf(1) : "compatibility_mode".equalsIgnoreCase(a) ? Integer.valueOf(2) : null : null;
    }

    /* renamed from: b */
    public PluginState m2959b() {
        String a = bu.m2389a(this.f2569b, "plugin_state", null, this.f2568a);
        return AppLovinSdkUtils.isValidString(a) ? "on".equalsIgnoreCase(a) ? PluginState.ON : "on_demand".equalsIgnoreCase(a) ? PluginState.ON_DEMAND : "off".equalsIgnoreCase(a) ? PluginState.OFF : null : null;
    }

    /* renamed from: c */
    public Boolean m2960c() {
        return bu.m2387a(this.f2569b, "allow_file_access", null, this.f2568a);
    }

    /* renamed from: d */
    public Boolean m2961d() {
        return bu.m2387a(this.f2569b, "load_with_overview_mode", null, this.f2568a);
    }

    /* renamed from: e */
    public Boolean m2962e() {
        return bu.m2387a(this.f2569b, "use_wide_view_port", null, this.f2568a);
    }

    /* renamed from: f */
    public Boolean m2963f() {
        return bu.m2387a(this.f2569b, "allow_content_access", null, this.f2568a);
    }

    /* renamed from: g */
    public Boolean m2964g() {
        return bu.m2387a(this.f2569b, "use_built_in_zoom_controls", null, this.f2568a);
    }

    /* renamed from: h */
    public Boolean m2965h() {
        return bu.m2387a(this.f2569b, "display_zoom_controls", null, this.f2568a);
    }

    /* renamed from: i */
    public Boolean m2966i() {
        return bu.m2387a(this.f2569b, "save_form_data", null, this.f2568a);
    }

    /* renamed from: j */
    public Boolean m2967j() {
        return bu.m2387a(this.f2569b, "geolocation_enabled", null, this.f2568a);
    }

    /* renamed from: k */
    public Boolean m2968k() {
        return bu.m2387a(this.f2569b, "need_initial_focus", null, this.f2568a);
    }

    /* renamed from: l */
    public Boolean m2969l() {
        return bu.m2387a(this.f2569b, "allow_file_access_from_file_urls", null, this.f2568a);
    }

    /* renamed from: m */
    public Boolean m2970m() {
        return bu.m2387a(this.f2569b, "allow_universal_access_from_file_urls", null, this.f2568a);
    }

    /* renamed from: n */
    public Boolean m2971n() {
        return bu.m2387a(this.f2569b, "offscreen_pre_raster", null, this.f2568a);
    }
}
