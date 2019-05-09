package com.facebook.ads.internal.p050r;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.google.android.gms.nearby.messages.Strategy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.r.a */
public final class C2078a {
    /* renamed from: a */
    private static C2078a f4695a;
    /* renamed from: b */
    private final SharedPreferences f4696b;
    /* renamed from: c */
    private final Context f4697c;

    private C2078a(Context context) {
        this.f4697c = context.getApplicationContext();
        this.f4696b = this.f4697c.getSharedPreferences(C2616a.m6730a("com.facebook.ads.FEATURE_CONFIG", context), 0);
    }

    @Nullable
    /* renamed from: A */
    public static String m5062A(Context context) {
        return C2078a.af(context).m5120a("adnw_wo_network_signal_url", "");
    }

    /* renamed from: B */
    public static double m5063B(Context context) {
        return C2078a.af(context).m5117a("adnw_wo_network_signal_sampling_rate", 0.0d);
    }

    /* renamed from: C */
    public static boolean m5064C(Context context) {
        return C2078a.af(context).m5122a("adnw_wo_network_signal_large_payload_enabled", false);
    }

    /* renamed from: D */
    public static int m5065D(Context context) {
        return C2078a.af(context).m5118a("adnw_wo_network_signal_large_payload_size", -1);
    }

    /* renamed from: E */
    public static double m5066E(Context context) {
        return C2078a.af(context).m5117a("adnw_wo_network_signal_large_payload_sampling_rate", -1.0d);
    }

    /* renamed from: F */
    public static int m5067F(Context context) {
        return C2078a.af(context).m5118a("minimum_elapsed_time_after_impression", -1);
    }

    /* renamed from: G */
    public static int m5068G(Context context) {
        return C2078a.af(context).m5118a("stack_trace_sample_rate", 0);
    }

    /* renamed from: H */
    public static boolean m5069H(Context context) {
        return C2078a.af(context).m5122a("adnw_top_activity_viewability", false);
    }

    /* renamed from: I */
    public static boolean m5070I(Context context) {
        return C2078a.af(context).m5122a("adnw_enhanced_viewability_area_check", false);
    }

    /* renamed from: J */
    public static boolean m5071J(Context context) {
        return C2078a.af(context).m5122a("adnw_purge_on_413_response", false);
    }

    /* renamed from: K */
    public static boolean m5072K(Context context) {
        return C2078a.af(context).m5122a("adnw_arrows_instead_of_x_skip_button", false);
    }

    /* renamed from: L */
    public static boolean m5073L(Context context) {
        return C2078a.af(context).m5122a("adnw_viewability_check_area_based", false);
    }

    @Nullable
    /* renamed from: M */
    public static String m5074M(Context context) {
        return C2078a.af(context).m5120a("adnw_logging_endpoint_prefix", "www");
    }

    /* renamed from: N */
    public static boolean m5075N(Context context) {
        return C2078a.af(context).m5122a("adnw_mapp_markup_impression_after_image_load", false);
    }

    /* renamed from: O */
    public static boolean m5076O(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_inline_x_out_on_sdk", false);
    }

    /* renamed from: P */
    public static boolean m5077P(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_inline_x_out_non_fullscreen_on_sdk", false);
    }

    /* renamed from: Q */
    public static boolean m5078Q(Context context) {
        return C2078a.af(context).m5122a("adnw_unique_db_name_per_process", false);
    }

    /* renamed from: R */
    public static boolean m5079R(Context context) {
        return C2078a.af(context).m5122a("adnw_log_interstitial_cache_result", false);
    }

    /* renamed from: S */
    public static boolean m5080S(Context context) {
        return C2078a.af(context).m5122a("adnw_images_in_display_size", true);
    }

    /* renamed from: T */
    public static boolean m5081T(Context context) {
        return C2078a.af(context).m5122a("adnw_fail_ad_load_on_cache_failure", false);
    }

    /* renamed from: U */
    public static boolean m5082U(Context context) {
        return C2078a.af(context).m5122a("adnw_should_fail_on_cleartext_http_blocked", false);
    }

    /* renamed from: V */
    public static boolean m5083V(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_multiprocess_support", false);
    }

    /* renamed from: W */
    public static boolean m5084W(Context context) {
        return C2078a.af(context).m5122a("adnw_request_first_ad_from_main_process", true);
    }

    /* renamed from: X */
    public static boolean m5085X(Context context) {
        return C2078a.af(context).m5122a("adnw_hide_error_dialog_for_ad_process", true);
    }

    /* renamed from: Y */
    public static boolean m5086Y(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_circular_process_binding", true);
    }

    /* renamed from: Z */
    public static boolean m5087Z(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_auto_destroy_leaks", true);
    }

    /* renamed from: a */
    private static int m5088a(Context context, String str, int i) {
        int a = C2078a.af(context).m5118a(str, i);
        return (a < 0 || a >= 101) ? i : a;
    }

    /* renamed from: a */
    public static boolean m5089a(Context context) {
        return VERSION.SDK_INT >= 14 && C2078a.m5093c("com.google.android.exoplayer2", "ExoPlayer") && C2078a.af(context).m5122a("adnw_enable_exoplayer", false);
    }

    public static boolean aa(Context context) {
        return C2078a.af(context).m5122a("adnw_disable_dependencies_check", true);
    }

    public static boolean ab(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_wrong_ad_states_check", true);
    }

    public static boolean ac(Context context) {
        return C2078a.af(context).m5122a("adnw_do_not_reload_interstitial_adapter", true);
    }

    public static int ad(Context context) {
        return C2078a.af(context).m5118a("adnw_time_to_wait_for_video_prepared_ms", 3000);
    }

    public static int ae(Context context) {
        return C2078a.af(context).m5118a("adnw_debug_log_file_size_limit_bytes", 10485760);
    }

    public static C2078a af(Context context) {
        if (f4695a == null) {
            synchronized (C2078a.class) {
                if (f4695a == null) {
                    f4695a = new C2078a(context);
                }
            }
        }
        return f4695a;
    }

    /* renamed from: b */
    private void m5090b(@Nullable String str, String str2) {
        if (str != null && !str.isEmpty() && !str.equals("[]")) {
            Editor edit = this.f4696b.edit();
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str3 = (String) keys.next();
                if (str3.equals("accidental_clicks_config")) {
                    m5090b(jSONObject.getString(str3), str3);
                } else {
                    edit.putString(str2 != null ? str2 + "." + str3 : str3, jSONObject.getString(str3));
                }
            }
            edit.apply();
        }
    }

    /* renamed from: b */
    public static boolean m5091b(Context context) {
        return VERSION.SDK_INT >= 18 && C2078a.af(context).m5122a("adnw_enable_debug_overlay", false);
    }

    /* renamed from: c */
    public static boolean m5092c(Context context) {
        return C2078a.af(context).m5122a("adnw_enable_rage_shake", false);
    }

    /* renamed from: c */
    private static boolean m5093c(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* renamed from: d */
    public static int m5094d(Context context) {
        return C2078a.af(context).m5118a("clickguard_time_ms", 0);
    }

    /* renamed from: e */
    public static boolean m5095e(Context context) {
        return C2078a.af(context).m5122a("accidental_clicks_config.two_step_confirmation", false);
    }

    @Nullable
    /* renamed from: f */
    public static String m5096f(Context context) {
        return C2078a.af(context).m5120a("accidental_clicks_config.two_step_confirmation_title", "Continue?");
    }

    @Nullable
    /* renamed from: g */
    public static String m5097g(Context context) {
        return C2078a.af(context).m5120a("accidental_clicks_config.two_step_confirmation_body", "You will be taken to another destination.");
    }

    @Nullable
    /* renamed from: h */
    public static String m5098h(Context context) {
        return C2078a.af(context).m5120a("accidental_clicks_config.two_step_confirm_button_text", "Continue");
    }

    @Nullable
    /* renamed from: i */
    public static String m5099i(Context context) {
        return C2078a.af(context).m5120a("accidental_clicks_config.two_step_cancel_button_text", "Cancel");
    }

    /* renamed from: j */
    public static boolean m5100j(Context context) {
        return C2078a.af(context).m5122a("adnw_block_lockscreen", false);
    }

    /* renamed from: k */
    public static boolean m5101k(Context context) {
        return C2078a.af(context).m5122a("adnw_block_cta_before_impression", false);
    }

    /* renamed from: l */
    public static boolean m5102l(Context context) {
        return C2078a.af(context).m5122a("adnw_android_memory_opt", false);
    }

    /* renamed from: m */
    public static boolean m5103m(Context context) {
        return C2078a.af(context).m5122a("adnw_android_disable_blur", false);
    }

    /* renamed from: n */
    public static boolean m5104n(Context context) {
        return C2078a.af(context).m5122a("adnw_android_disable_playable_precache", false);
    }

    /* renamed from: o */
    public static boolean m5105o(Context context) {
        return VERSION.SDK_INT >= 19 && C2078a.af(context).m5122a("adnw_enable_iab", false);
    }

    /* renamed from: p */
    public static boolean m5106p(Context context) {
        return C2078a.af(context).m5122a("adnw_debug_logging", false);
    }

    /* renamed from: q */
    public static boolean m5107q(Context context) {
        return C2078a.af(context).m5122a("adnw_text_in_native_carousel", false);
    }

    /* renamed from: r */
    public static int m5108r(Context context) {
        return C2078a.af(context).m5118a("adnw_native_carousel_compact_threshold", 225);
    }

    /* renamed from: s */
    public static Set<String> m5109s(Context context) {
        String a = C2078a.af(context).m5120a("additional_debug_logging_black_list", "");
        Set hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(a);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        } catch (JSONException e) {
        }
        return hashSet;
    }

    /* renamed from: t */
    public static int m5110t(Context context) {
        return C2078a.m5088a(context, "additional_debug_logging_black_list_percentage", 0);
    }

    /* renamed from: u */
    public static int m5111u(Context context) {
        return C2078a.m5088a(context, "additional_debug_logging_sampling_percentage", 0);
    }

    /* renamed from: v */
    public static long m5112v(Context context) {
        return C2078a.af(context).m5119a("unified_logging_immediate_delay_ms", 500);
    }

    /* renamed from: w */
    public static long m5113w(Context context) {
        return ((long) C2078a.af(context).m5118a("unified_logging_dispatch_interval_seconds", (int) Strategy.TTL_SECONDS_DEFAULT)) * 1000;
    }

    /* renamed from: x */
    public static int m5114x(Context context) {
        return C2078a.af(context).m5118a("unified_logging_event_limit", -1);
    }

    /* renamed from: y */
    public static boolean m5115y(Context context) {
        return C2078a.af(context).m5120a("video_and_endcard_autorotate", "autorotate_disabled").equals("autorotate_enabled");
    }

    /* renamed from: z */
    public static boolean m5116z(Context context) {
        return C2078a.af(context).m5122a("adnw_wo_network_signal_enabled", false);
    }

    /* renamed from: a */
    public double m5117a(String str, double d) {
        String string = this.f4696b.getString(str, String.valueOf(d));
        try {
            if (!string.equals("null")) {
                d = Double.valueOf(string).doubleValue();
            }
        } catch (NumberFormatException e) {
        }
        return d;
    }

    /* renamed from: a */
    public int m5118a(String str, int i) {
        String string = this.f4696b.getString(str, String.valueOf(i));
        try {
            if (!string.equals("null")) {
                i = Integer.valueOf(string).intValue();
            }
        } catch (NumberFormatException e) {
        }
        return i;
    }

    /* renamed from: a */
    public long m5119a(String str, long j) {
        String string = this.f4696b.getString(str, String.valueOf(j));
        try {
            if (!string.equals("null")) {
                j = Long.valueOf(string).longValue();
            }
        } catch (NumberFormatException e) {
        }
        return j;
    }

    @Nullable
    /* renamed from: a */
    public String m5120a(String str, String str2) {
        String string = this.f4696b.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    /* renamed from: a */
    public void m5121a(@Nullable String str) {
        m5090b(str, null);
    }

    /* renamed from: a */
    public boolean m5122a(String str, boolean z) {
        String string = this.f4696b.getString(str, String.valueOf(z));
        return string.equals("null") ? z : Boolean.valueOf(string).booleanValue();
    }
}
