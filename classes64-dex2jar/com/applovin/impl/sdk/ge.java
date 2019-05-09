// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.webkit.WebSettings$PluginState;
import com.applovin.sdk.AppLovinSdkUtils;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinSdk;

public final class ge
{
    private AppLovinSdk a;
    private JSONObject b;
    
    ge(final JSONObject b, final AppLovinSdk a) {
        this.a = a;
        this.b = b;
    }
    
    public Integer a() {
        final Integer n = null;
        final String a = bu.a(this.b, "mixed_content_mode", (String)null, this.a);
        Integer value = n;
        if (AppLovinSdkUtils.isValidString(a)) {
            if ("always_allow".equalsIgnoreCase(a)) {
                value = 0;
            }
            else {
                if ("never_allow".equalsIgnoreCase(a)) {
                    return 1;
                }
                value = n;
                if ("compatibility_mode".equalsIgnoreCase(a)) {
                    return 2;
                }
            }
        }
        return value;
    }
    
    public WebSettings$PluginState b() {
        final WebSettings$PluginState webSettings$PluginState = null;
        final String a = bu.a(this.b, "plugin_state", (String)null, this.a);
        WebSettings$PluginState on = webSettings$PluginState;
        if (AppLovinSdkUtils.isValidString(a)) {
            if ("on".equalsIgnoreCase(a)) {
                on = WebSettings$PluginState.ON;
            }
            else {
                if ("on_demand".equalsIgnoreCase(a)) {
                    return WebSettings$PluginState.ON_DEMAND;
                }
                on = webSettings$PluginState;
                if ("off".equalsIgnoreCase(a)) {
                    return WebSettings$PluginState.OFF;
                }
            }
        }
        return on;
    }
    
    public Boolean c() {
        return bu.a(this.b, "allow_file_access", (Boolean)null, this.a);
    }
    
    public Boolean d() {
        return bu.a(this.b, "load_with_overview_mode", (Boolean)null, this.a);
    }
    
    public Boolean e() {
        return bu.a(this.b, "use_wide_view_port", (Boolean)null, this.a);
    }
    
    public Boolean f() {
        return bu.a(this.b, "allow_content_access", (Boolean)null, this.a);
    }
    
    public Boolean g() {
        return bu.a(this.b, "use_built_in_zoom_controls", (Boolean)null, this.a);
    }
    
    public Boolean h() {
        return bu.a(this.b, "display_zoom_controls", (Boolean)null, this.a);
    }
    
    public Boolean i() {
        return bu.a(this.b, "save_form_data", (Boolean)null, this.a);
    }
    
    public Boolean j() {
        return bu.a(this.b, "geolocation_enabled", (Boolean)null, this.a);
    }
    
    public Boolean k() {
        return bu.a(this.b, "need_initial_focus", (Boolean)null, this.a);
    }
    
    public Boolean l() {
        return bu.a(this.b, "allow_file_access_from_file_urls", (Boolean)null, this.a);
    }
    
    public Boolean m() {
        return bu.a(this.b, "allow_universal_access_from_file_urls", (Boolean)null, this.a);
    }
    
    public Boolean n() {
        return bu.a(this.b, "offscreen_pre_raster", (Boolean)null, this.a);
    }
}
