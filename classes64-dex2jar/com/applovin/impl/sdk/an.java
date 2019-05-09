// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Locale;
import com.applovin.sdk.AppLovinAdSize;
import java.util.concurrent.TimeUnit;
import android.net.Uri;
import java.util.List;
import com.applovin.sdk.AppLovinSdkUtils;
import android.graphics.Color;
import com.applovin.impl.adview.cm;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.adview.al;
import com.applovin.sdk.AppLovinAdType;
import org.json.JSONObject;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class an extends q
{
    private final AtomicBoolean e;
    
    public an(final JSONObject jsonObject, final JSONObject jsonObject2, final AppLovinSdkImpl appLovinSdkImpl) {
        super(jsonObject, jsonObject2, appLovinSdkImpl);
        this.e = new AtomicBoolean();
    }
    
    private float a(final AppLovinAdType appLovinAdType, final float n, final boolean b) {
        if (!appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) && (!appLovinAdType.equals(AppLovinAdType.REGULAR) || !b || n != -1.0f)) {
            return 0.0f;
        }
        return 0.5f;
    }
    
    private al a(final boolean b) {
        if (b) {
            return al.b;
        }
        return al.a;
    }
    
    public String A() {
        return bu.a(this.a, "video_end_url", "", this.c);
    }
    
    public boolean B() {
        return bu.a(this.a, "dismiss_on_skip", false, this.c);
    }
    
    public String C() {
        final JSONObject a = bu.a(this.a, "video_button_properties", (JSONObject)null, this.c);
        if (a != null) {
            return bu.a(a, "video_button_html", "", this.c);
        }
        return "";
    }
    
    public cm D() {
        return new cm(bu.a(this.a, "video_button_properties", (JSONObject)null, this.c), this.c);
    }
    
    public boolean E() {
        return bu.a(this.a, "video_clickable", false, this.c);
    }
    
    public boolean F() {
        return bu.a(this.a, "accelerate_hardware", false, this.c);
    }
    
    public boolean G() {
        return bu.a(this.a, "hide_close_on_exit_graphic", false, this.c);
    }
    
    public boolean H() {
        return bu.a(this.a, "hide_close_on_exit", false, this.c);
    }
    
    public boolean I() {
        return bu.a(this.a, "lock_current_orientation", false, this.c);
    }
    
    public int J() {
        return bu.a(this.a, "countdown_length", 0, this.c);
    }
    
    public int K() {
        final int color = Color.parseColor("#C8FFFFFF");
        final String a = bu.a(this.a, "countdown_color", (String)null, this.c);
        int color2 = color;
        if (!AppLovinSdkUtils.isValidString(a)) {
            return color2;
        }
        try {
            color2 = Color.parseColor(a);
            return color2;
        }
        catch (Throwable t) {
            this.c.getLogger().e("DirectAd", "Unable to parse countdown color", t);
            return color;
        }
    }
    
    public int L() {
        int color = -16777216;
        final String a = bu.a(this.a, "video_background_color", (String)null, this.c);
        if (!AppLovinSdkUtils.isValidString(a)) {
            return color;
        }
        try {
            color = Color.parseColor(a);
            return color;
        }
        catch (Throwable t) {
            return -16777216;
        }
    }
    
    public int M() {
        Label_0041: {
            if (!this.f()) {
                break Label_0041;
            }
            int n = -16777216;
            while (true) {
                final String a = bu.a(this.a, "graphic_background_color", (String)null, this.c);
                int color = n;
                if (!AppLovinSdkUtils.isValidString(a)) {
                    return color;
                }
                try {
                    color = Color.parseColor(a);
                    return color;
                    n = -1157627904;
                    continue;
                }
                catch (Throwable t) {
                    return n;
                }
                break;
            }
        }
    }
    
    public ao N() {
        final String a = bu.a(this.a, "poststitial_dismiss_type", (String)null, this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            if ("dismiss".equalsIgnoreCase(a)) {
                return ao.b;
            }
            if ("no_dismiss".equalsIgnoreCase(a)) {
                return ao.c;
            }
        }
        return ao.a;
    }
    
    public List<String> O() {
        final String a = bu.a(this.a, "resource_cache_prefix", (String)null, this.c);
        if (a != null) {
            return aa.a(a);
        }
        return this.c.getAsList(ea.M);
    }
    
    public String P() {
        return bu.a(this.a, "cache_prefix", (String)null, this.c);
    }
    
    public boolean Q() {
        return bu.a(this.a, "progress_bar_enabled", false, this.c);
    }
    
    public int R() {
        final String a = bu.a(this.a, "progress_bar_color", "#C8FFFFFF", this.c);
        int color = 0;
        if (!AppLovinSdkUtils.isValidString(a)) {
            return color;
        }
        try {
            color = Color.parseColor(a);
            return color;
        }
        catch (Throwable t) {
            return 0;
        }
    }
    
    public int S() {
        return gd.a(this.a);
    }
    
    public int T() {
        return bu.a(this.a, "poststitial_shown_forward_delay_millis", -1, this.c);
    }
    
    public int U() {
        return bu.a(this.a, "close_button_size", this.c.get(ea.aR), this.c);
    }
    
    public int V() {
        return bu.a(this.a, "close_button_top_margin", this.c.get(ea.aS), this.c);
    }
    
    public int W() {
        return bu.a(this.a, "close_button_horizontal_margin", this.c.get(ea.aT), this.c);
    }
    
    public boolean X() {
        return bu.a(this.a, "lhs_close_button", this.c.get(ea.bB), this.c);
    }
    
    public boolean Y() {
        return bu.a(this.a, "lhs_skip_button", this.c.get(ea.bC), this.c);
    }
    
    public boolean Z() {
        return bu.a(this.a, "stop_video_player_after_poststitial_render", false, this.c);
    }
    
    protected al a(final int n) {
        if (n == 1) {
            return al.b;
        }
        if (n == 2) {
            return al.c;
        }
        return al.a;
    }
    
    public String a(final int n, final String s, final boolean b) {
        final String a = this.A();
        if (AppLovinSdkUtils.isValidString(a)) {
            return gd.a(s, Uri.parse(a.replace("{CLCODE}", this.n())).buildUpon().appendQueryParameter("pv", Integer.toString(n)).appendQueryParameter("vid_ts", Long.toString(System.currentTimeMillis())).appendQueryParameter("uvs", Boolean.toString(b)).build().toString());
        }
        return "";
    }
    
    public boolean aa() {
        return bu.a(this.a, "unhide_adview_on_render", false, this.c);
    }
    
    public long ab() {
        long millis = -1L;
        final long a = bu.a(this.a, "report_reward_duration", -1L, this.c);
        if (a >= 0L) {
            millis = TimeUnit.SECONDS.toMillis(a);
        }
        return millis;
    }
    
    public int ac() {
        return bu.a(this.a, "report_reward_percent", -1, this.c);
    }
    
    public boolean ad() {
        return bu.a(this.a, "report_reward_percent_include_close_delay", true, this.c);
    }
    
    public AtomicBoolean ae() {
        return this.e;
    }
    
    public boolean af() {
        return bu.a(this.a, "render_poststitial_on_attach", false, this.c);
    }
    
    public boolean ag() {
        return bu.a(this.a, "playback_requires_user_action", true, this.c);
    }
    
    public boolean ah() {
        return bu.a(this.a, "sanitize_webview", false, this.c);
    }
    
    public String ai() {
        String a;
        if ("null".equalsIgnoreCase(a = bu.a(this.a, "base_url", "/", this.c))) {
            a = null;
        }
        return a;
    }
    
    public boolean aj() {
        return bu.a(this.a, "web_contents_debugging_enabled", false, this.c);
    }
    
    public ge ak() {
        ge ge = null;
        final JSONObject a = bu.a(this.a, "web_view_settings", (JSONObject)null, this.c);
        if (a != null) {
            ge = new ge(a, this.c);
        }
        return ge;
    }
    
    public Uri al() {
        Uri parse = null;
        final String a = bu.a(this.a, "mute_image", (String)null, this.c);
        if (!AppLovinSdkUtils.isValidString(a)) {
            return parse;
        }
        try {
            parse = Uri.parse(a);
            return parse;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public Uri am() {
        Uri parse = null;
        final String a = bu.a(this.a, "unmute_image", "", this.c);
        if (!AppLovinSdkUtils.isValidString(a)) {
            return parse;
        }
        try {
            parse = Uri.parse(a);
            return parse;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public String b(final String s) {
        final String a = bu.a(this.a, "click_tracking_url", "", this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            return gd.a(s, a.replace("{CLCODE}", this.n()));
        }
        return "";
    }
    
    public void b(final Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("mute_image", (Object)uri);
            }
        }
        catch (Throwable t) {}
    }
    
    public boolean b() {
        this.c.getLogger().e("DirectAd", "Attempting to invoke isVideoStream() from base ad class");
        return false;
    }
    
    public void c(final Uri uri) {
        try {
            synchronized (this.d) {
                this.a.put("unmute_image", (Object)uri);
            }
        }
        catch (Throwable t) {}
    }
    
    public Uri d() {
        this.c.getLogger().e("DirectAd", "Attempting to invoke getVideoUri() from base ad class");
        return null;
    }
    
    public Uri g() {
        this.c.getLogger().e("DirectAd", "Attempting to invoke getClickDestinationUri() from base ad class");
        return null;
    }
    
    public ap v() {
        final String upperCase = bu.a(this.a, "ad_target", ap.a.toString(), this.c).toUpperCase(Locale.ENGLISH);
        if ("ACTIVITY_PORTRAIT".equalsIgnoreCase(upperCase)) {
            return ap.b;
        }
        if ("ACTIVITY_LANDSCAPE".equalsIgnoreCase(upperCase)) {
            return ap.c;
        }
        return ap.a;
    }
    
    public float w() {
        return bu.a(this.a, "close_delay", 0.0f, this.c);
    }
    
    public float x() {
        return bu.a(this.a, "close_delay_graphic", this.a(this.getType(), this.w(), this.f()), this.c);
    }
    
    public al y() {
        final int a = bu.a(this.a, "close_style", -1, this.c);
        if (a == -1) {
            return this.a(this.f());
        }
        return this.a(a);
    }
    
    public al z() {
        final int a = bu.a(this.a, "skip_style", -1, this.c);
        if (a == -1) {
            return this.y();
        }
        return this.a(a);
    }
}
