// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.r;

import org.json.JSONException;
import org.json.JSONArray;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import android.content.SharedPreferences$Editor;
import org.json.JSONObject;
import android.os.Build$VERSION;
import android.support.annotation.Nullable;
import android.content.Context;
import android.content.SharedPreferences;

public final class a
{
    private static a a;
    private final SharedPreferences b;
    private final Context c;
    
    private a(final Context context) {
        this.c = context.getApplicationContext();
        this.b = this.c.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("com.facebook.ads.FEATURE_CONFIG", context), 0);
    }
    
    @Nullable
    public static String A(final Context context) {
        return af(context).a("adnw_wo_network_signal_url", "");
    }
    
    public static double B(final Context context) {
        return af(context).a("adnw_wo_network_signal_sampling_rate", 0.0);
    }
    
    public static boolean C(final Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_enabled", false);
    }
    
    public static int D(final Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_size", -1);
    }
    
    public static double E(final Context context) {
        return af(context).a("adnw_wo_network_signal_large_payload_sampling_rate", -1.0);
    }
    
    public static int F(final Context context) {
        return af(context).a("minimum_elapsed_time_after_impression", -1);
    }
    
    public static int G(final Context context) {
        return af(context).a("stack_trace_sample_rate", 0);
    }
    
    public static boolean H(final Context context) {
        return af(context).a("adnw_top_activity_viewability", false);
    }
    
    public static boolean I(final Context context) {
        return af(context).a("adnw_enhanced_viewability_area_check", false);
    }
    
    public static boolean J(final Context context) {
        return af(context).a("adnw_purge_on_413_response", false);
    }
    
    public static boolean K(final Context context) {
        return af(context).a("adnw_arrows_instead_of_x_skip_button", false);
    }
    
    public static boolean L(final Context context) {
        return af(context).a("adnw_viewability_check_area_based", false);
    }
    
    @Nullable
    public static String M(final Context context) {
        return af(context).a("adnw_logging_endpoint_prefix", "www");
    }
    
    public static boolean N(final Context context) {
        return af(context).a("adnw_mapp_markup_impression_after_image_load", false);
    }
    
    public static boolean O(final Context context) {
        return af(context).a("adnw_enable_inline_x_out_on_sdk", false);
    }
    
    public static boolean P(final Context context) {
        return af(context).a("adnw_enable_inline_x_out_non_fullscreen_on_sdk", false);
    }
    
    public static boolean Q(final Context context) {
        return af(context).a("adnw_unique_db_name_per_process", false);
    }
    
    public static boolean R(final Context context) {
        return af(context).a("adnw_log_interstitial_cache_result", false);
    }
    
    public static boolean S(final Context context) {
        return af(context).a("adnw_images_in_display_size", true);
    }
    
    public static boolean T(final Context context) {
        return af(context).a("adnw_fail_ad_load_on_cache_failure", false);
    }
    
    public static boolean U(final Context context) {
        return af(context).a("adnw_should_fail_on_cleartext_http_blocked", false);
    }
    
    public static boolean V(final Context context) {
        return af(context).a("adnw_enable_multiprocess_support", false);
    }
    
    public static boolean W(final Context context) {
        return af(context).a("adnw_request_first_ad_from_main_process", true);
    }
    
    public static boolean X(final Context context) {
        return af(context).a("adnw_hide_error_dialog_for_ad_process", true);
    }
    
    public static boolean Y(final Context context) {
        return af(context).a("adnw_enable_circular_process_binding", true);
    }
    
    public static boolean Z(final Context context) {
        return af(context).a("adnw_enable_auto_destroy_leaks", true);
    }
    
    private static int a(final Context context, final String s, final int n) {
        final int a = af(context).a(s, n);
        int n2 = n;
        if (a >= 0) {
            n2 = n;
            if (a < 101) {
                n2 = a;
            }
        }
        return n2;
    }
    
    public static boolean a(final Context context) {
        boolean b2;
        final boolean b = b2 = false;
        if (Build$VERSION.SDK_INT >= 14) {
            b2 = b;
            if (c("com.google.android.exoplayer2", "ExoPlayer")) {
                b2 = b;
                if (af(context).a("adnw_enable_exoplayer", false)) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    public static boolean aa(final Context context) {
        return af(context).a("adnw_disable_dependencies_check", true);
    }
    
    public static boolean ab(final Context context) {
        return af(context).a("adnw_enable_wrong_ad_states_check", true);
    }
    
    public static boolean ac(final Context context) {
        return af(context).a("adnw_do_not_reload_interstitial_adapter", true);
    }
    
    public static int ad(final Context context) {
        return af(context).a("adnw_time_to_wait_for_video_prepared_ms", 3000);
    }
    
    public static int ae(final Context context) {
        return af(context).a("adnw_debug_log_file_size_limit_bytes", 10485760);
    }
    
    public static a af(final Context context) {
        Label_0029: {
            if (com.facebook.ads.internal.r.a.a != null) {
                break Label_0029;
            }
            synchronized (a.class) {
                if (com.facebook.ads.internal.r.a.a == null) {
                    com.facebook.ads.internal.r.a.a = new a(context);
                }
                return com.facebook.ads.internal.r.a.a;
            }
        }
    }
    
    private void b(@Nullable String string, final String s) {
        if (string == null || string.isEmpty() || string.equals("[]")) {
            return;
        }
        final SharedPreferences$Editor edit = this.b.edit();
        final JSONObject jsonObject = new JSONObject(string);
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s2 = keys.next();
            if (s2.equals("accidental_clicks_config")) {
                this.b(jsonObject.getString(s2), s2);
            }
            else {
                if (s != null) {
                    string = s + "." + s2;
                }
                else {
                    string = s2;
                }
                edit.putString(string, jsonObject.getString(s2));
            }
        }
        edit.apply();
    }
    
    public static boolean b(final Context context) {
        boolean b = false;
        if (Build$VERSION.SDK_INT >= 18) {
            b = b;
            if (af(context).a("adnw_enable_debug_overlay", false)) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean c(final Context context) {
        return af(context).a("adnw_enable_rage_shake", false);
    }
    
    private static boolean c(final String s, final String s2) {
        try {
            Class.forName(s + "." + s2);
            return true;
        }
        catch (ClassNotFoundException ex) {
            return false;
        }
    }
    
    public static int d(final Context context) {
        return af(context).a("clickguard_time_ms", 0);
    }
    
    public static boolean e(final Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation", false);
    }
    
    @Nullable
    public static String f(final Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation_title", "Continue?");
    }
    
    @Nullable
    public static String g(final Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirmation_body", "You will be taken to another destination.");
    }
    
    @Nullable
    public static String h(final Context context) {
        return af(context).a("accidental_clicks_config.two_step_confirm_button_text", "Continue");
    }
    
    @Nullable
    public static String i(final Context context) {
        return af(context).a("accidental_clicks_config.two_step_cancel_button_text", "Cancel");
    }
    
    public static boolean j(final Context context) {
        return af(context).a("adnw_block_lockscreen", false);
    }
    
    public static boolean k(final Context context) {
        return af(context).a("adnw_block_cta_before_impression", false);
    }
    
    public static boolean l(final Context context) {
        return af(context).a("adnw_android_memory_opt", false);
    }
    
    public static boolean m(final Context context) {
        return af(context).a("adnw_android_disable_blur", false);
    }
    
    public static boolean n(final Context context) {
        return af(context).a("adnw_android_disable_playable_precache", false);
    }
    
    public static boolean o(final Context context) {
        boolean b = false;
        if (Build$VERSION.SDK_INT >= 19) {
            b = b;
            if (af(context).a("adnw_enable_iab", false)) {
                b = true;
            }
        }
        return b;
    }
    
    public static boolean p(final Context context) {
        return af(context).a("adnw_debug_logging", false);
    }
    
    public static boolean q(final Context context) {
        return af(context).a("adnw_text_in_native_carousel", false);
    }
    
    public static int r(final Context context) {
        return af(context).a("adnw_native_carousel_compact_threshold", 225);
    }
    
    public static Set<String> s(Context context) {
        final String a = af(context).a("additional_debug_logging_black_list", "");
        context = (Context)new HashSet();
        try {
            final JSONArray jsonArray = new JSONArray(a);
            for (int i = 0; i < jsonArray.length(); ++i) {
                ((HashSet<String>)context).add(jsonArray.getString(i));
            }
        }
        catch (JSONException ex) {}
        return (Set<String>)context;
    }
    
    public static int t(final Context context) {
        return a(context, "additional_debug_logging_black_list_percentage", 0);
    }
    
    public static int u(final Context context) {
        return a(context, "additional_debug_logging_sampling_percentage", 0);
    }
    
    public static long v(final Context context) {
        return af(context).a("unified_logging_immediate_delay_ms", 500L);
    }
    
    public static long w(final Context context) {
        return af(context).a("unified_logging_dispatch_interval_seconds", 300) * 1000L;
    }
    
    public static int x(final Context context) {
        return af(context).a("unified_logging_event_limit", -1);
    }
    
    public static boolean y(final Context context) {
        return af(context).a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }
    
    public static boolean z(final Context context) {
        return af(context).a("adnw_wo_network_signal_enabled", false);
    }
    
    public double a(String string, final double n) {
        string = this.b.getString(string, String.valueOf(n));
        try {
            if (string.equals("null")) {
                return n;
            }
            return Double.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public int a(String string, final int n) {
        string = this.b.getString(string, String.valueOf(n));
        try {
            if (string.equals("null")) {
                return n;
            }
            return Integer.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public long a(String string, final long n) {
        string = this.b.getString(string, String.valueOf(n));
        try {
            if (string.equals("null")) {
                return n;
            }
            return Long.valueOf(string);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    @Nullable
    public String a(String string, final String s) {
        string = this.b.getString(string, s);
        if (string == null || string.equals("null")) {
            return s;
        }
        return string;
    }
    
    public void a(@Nullable final String s) {
        this.b(s, null);
    }
    
    public boolean a(String string, final boolean b) {
        string = this.b.getString(string, String.valueOf(b));
        if (string.equals("null")) {
            return b;
        }
        return Boolean.valueOf(string);
    }
}
