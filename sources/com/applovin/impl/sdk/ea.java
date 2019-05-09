package com.applovin.impl.sdk;

import com.adjust.sdk.Constants;
import com.applovin.impl.p016a.C1244q;
import com.ironsource.sdk.precache.DownloadManager;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class ea {
    /* renamed from: A */
    public static final ec<String> f2377A = m2656a("sc", "");
    /* renamed from: B */
    public static final ec<String> f2378B = m2656a("ad_request_parameters", "");
    /* renamed from: C */
    public static final ec<Boolean> f2379C = m2656a("ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: D */
    public static final ec<Long> f2380D = m2656a("ad_refresh_seconds", Long.valueOf(120));
    /* renamed from: E */
    public static final ec<Boolean> f2381E = m2656a("mrec_ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: F */
    public static final ec<Long> f2382F = m2656a("mrec_ad_refresh_seconds", Long.valueOf(120));
    /* renamed from: G */
    public static final ec<Boolean> f2383G = m2656a("leader_ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: H */
    public static final ec<Long> f2384H = m2656a("leader_ad_refresh_seconds", Long.valueOf(120));
    /* renamed from: I */
    public static final ec<String> f2385I = m2656a("plugin_version", "");
    /* renamed from: J */
    public static final ec<Boolean> f2386J = m2656a("ad_preload_enabled", Boolean.valueOf(true));
    /* renamed from: K */
    public static final ec<Boolean> f2387K = m2656a("ad_resource_caching_enabled", Boolean.valueOf(true));
    /* renamed from: L */
    public static final ec<Boolean> f2388L = m2656a("fail_ad_load_on_failed_video_cache", Boolean.valueOf(true));
    /* renamed from: M */
    public static final ec<String> f2389M = m2656a("resource_cache_prefix", "https://vid.applovin.com/,https://pdn.applovin.com/,https://img.applovin.com/,https://d.applovin.com/,https://assets.applovin.com/,https://cdnjs.cloudflare.com/,http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    /* renamed from: N */
    public static final ec<String> f2390N = m2656a("ad_auto_preload_sizes", "");
    /* renamed from: O */
    public static final ec<String> f2391O = m2656a("ad_auto_preload_msizes", "");
    /* renamed from: P */
    public static final ec<Boolean> f2392P = m2656a("ad_auto_preload_incent", Boolean.valueOf(true));
    /* renamed from: Q */
    public static final ec<Boolean> f2393Q = m2656a("ad_auto_preload_mincent", Boolean.valueOf(true));
    /* renamed from: R */
    public static final ec<Boolean> f2394R = m2656a("is_track_ad_info", Boolean.valueOf(true));
    /* renamed from: S */
    public static final ec<Boolean> f2395S = m2656a("force_back_button_enabled_always", Boolean.valueOf(false));
    /* renamed from: T */
    public static final ec<Integer> f2396T = m2656a("close_fade_in_time", Integer.valueOf(400));
    /* renamed from: U */
    public static final ec<Boolean> f2397U = m2656a("show_close_on_exit", Boolean.valueOf(true));
    /* renamed from: V */
    public static final ec<String> f2398V = m2656a("text_incent_prompt_title", "Earn a Reward");
    /* renamed from: W */
    public static final ec<String> f2399W = m2656a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    /* renamed from: X */
    public static final ec<String> f2400X = m2656a("text_incent_prompt_yes_option", "Watch Now");
    /* renamed from: Y */
    public static final ec<String> f2401Y = m2656a("text_incent_prompt_no_option", "No Thanks");
    /* renamed from: Z */
    public static final ec<String> f2402Z = m2656a("text_incent_completion_title", "Video Reward");
    /* renamed from: a */
    public static final ec<Boolean> f2403a = m2656a("is_disabled", Boolean.valueOf(false));
    public static final ec<Integer> aA = m2656a("preload_capacity_inter_videoa_indirect", Integer.valueOf(0));
    public static final ec<Integer> aB = m2656a("extended_preload_capacity_banner_regular_direct", Integer.valueOf(15));
    public static final ec<Integer> aC = m2656a("extended_preload_capacity_banner_regular_indirect", Integer.valueOf(15));
    public static final ec<Integer> aD = m2656a("extended_preload_capacity_mrec_regular_direct", Integer.valueOf(15));
    public static final ec<Integer> aE = m2656a("extended_preload_capacity_mrec_regular_indirect", Integer.valueOf(15));
    public static final ec<Integer> aF = m2656a("extended_preload_capacity_leader_regular_direct", Integer.valueOf(15));
    public static final ec<Integer> aG = m2656a("extended_preload_capacity_leader_regular_indirect", Integer.valueOf(15));
    public static final ec<Integer> aH = m2656a("extended_preload_capacity_inter_regular_direct", Integer.valueOf(15));
    public static final ec<Integer> aI = m2656a("extended_preload_capacity_inter_regular_indirect", Integer.valueOf(15));
    public static final ec<Integer> aJ = m2656a("extended_preload_capacity_inter_videoa_direct", Integer.valueOf(15));
    public static final ec<Integer> aK = m2656a("extended_preload_capacity_inter_videoa_indirect", Integer.valueOf(15));
    public static final ec<Integer> aL = m2656a("preload_capacity_zone", Integer.valueOf(1));
    public static final ec<Integer> aM = m2656a("preload_capacity_zone_native", Integer.valueOf(1));
    public static final ec<Integer> aN = m2656a("extended_preload_capacity_zone", Integer.valueOf(15));
    public static final ec<Integer> aO = m2656a("preload_capacity_native_native_direct", Integer.valueOf(0));
    public static final ec<Boolean> aP = m2656a("dismiss_video_on_error", Boolean.valueOf(true));
    public static final ec<String> aQ = m2656a("precache_delimiters", ")]',");
    public static final ec<Integer> aR = m2656a("close_button_size_video", Integer.valueOf(30));
    public static final ec<Integer> aS = m2656a("close_button_top_margin_video", Integer.valueOf(8));
    public static final ec<Integer> aT = m2656a("close_button_right_margin_video", Integer.valueOf(4));
    public static final ec<Boolean> aU = m2656a("force_back_button_enabled_poststitial", Boolean.valueOf(false));
    public static final ec<Boolean> aV = m2656a("force_back_button_enabled_close_button", Boolean.valueOf(false));
    public static final ec<Integer> aW = m2656a("close_button_touch_area", Integer.valueOf(0));
    public static final ec<Boolean> aX = m2656a("is_video_skippable", Boolean.valueOf(false));
    public static final ec<Boolean> aY = m2656a("cache_cleanup_enabled", Boolean.valueOf(false));
    public static final ec<Long> aZ = m2656a("cache_file_ttl_seconds", Long.valueOf(86400));
    public static final ec<String> aa = m2656a("text_incent_completion_body_success", "You have earned a reward!");
    public static final ec<String> ab = m2656a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    public static final ec<String> ac = m2656a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    public static final ec<String> ad = m2656a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    public static final ec<String> ae = m2656a("text_incent_completion_close_option", "Okay");
    public static final ec<Boolean> af = m2656a("incent_warning_enabled", Boolean.valueOf(false));
    public static final ec<String> ag = m2656a("text_incent_warning_title", "Attention!");
    public static final ec<String> ah = m2656a("text_incent_warning_body", "You won’t get your reward if the video hasn’t finished.");
    public static final ec<String> ai = m2656a("text_incent_warning_close_option", "Close");
    public static final ec<String> aj = m2656a("text_incent_warning_continue_option", "Keep Watching");
    public static final ec<Boolean> ak = m2656a("incent_nonvideo_warning_enabled", Boolean.valueOf(false));
    public static final ec<String> al = m2656a("text_incent_nonvideo_warning_title", "Attention!");
    public static final ec<String> am = m2656a("text_incent_nonvideo_warning_body", "You won’t get your reward if the game hasn’t finished.");
    public static final ec<String> an = m2656a("text_incent_nonvideo_warning_close_option", "Close");
    public static final ec<String> ao = m2656a("text_incent_nonvideo_warning_continue_option", "Keep Playing");
    public static final ec<Boolean> ap = m2656a("show_incent_prepopup", Boolean.valueOf(true));
    public static final ec<Boolean> aq = m2656a("show_incent_postpopup", Boolean.valueOf(true));
    public static final ec<Integer> ar = m2656a("preload_capacity_banner_regular_direct", Integer.valueOf(0));
    public static final ec<Integer> as = m2656a("preload_capacity_banner_regular_indirect", Integer.valueOf(0));
    public static final ec<Integer> at = m2656a("preload_capacity_mrec_regular_direct", Integer.valueOf(0));
    public static final ec<Integer> au = m2656a("preload_capacity_mrec_regular_indirect", Integer.valueOf(0));
    public static final ec<Integer> av = m2656a("preload_capacity_leader_regular_direct", Integer.valueOf(0));
    public static final ec<Integer> aw = m2656a("preload_capacity_leader_regular_indirect", Integer.valueOf(0));
    public static final ec<Integer> ax = m2656a("preload_capacity_inter_regular_direct", Integer.valueOf(0));
    public static final ec<Integer> ay = m2656a("preload_capacity_inter_regular_indirect", Integer.valueOf(0));
    public static final ec<Integer> az = m2656a("preload_capacity_inter_videoa_direct", Integer.valueOf(0));
    /* renamed from: b */
    public static final ec<Boolean> f2404b = m2656a("honor_publisher_settings", Boolean.valueOf(true));
    public static final ec<Boolean> bA = m2656a("android_gc_on_widget_detach", Boolean.valueOf(true));
    public static final ec<Boolean> bB = m2656a("lhs_close_button_video", Boolean.valueOf(false));
    public static final ec<Boolean> bC = m2656a("lhs_skip_button", Boolean.valueOf(true));
    public static final ec<Boolean> bD = m2656a("countdown_toggleable", Boolean.valueOf(false));
    public static final ec<Integer> bE = m2656a("native_batch_precache_count", Integer.valueOf(1));
    public static final ec<Boolean> bF = m2656a("mute_controls_enabled", Boolean.valueOf(false));
    public static final ec<Boolean> bG = m2656a("allow_user_muting", Boolean.valueOf(true));
    public static final ec<Integer> bH = m2656a("mute_button_size", Integer.valueOf(32));
    public static final ec<Integer> bI = m2656a("mute_button_margin", Integer.valueOf(10));
    public static final ec<Integer> bJ = m2656a("mute_button_gravity", Integer.valueOf(85));
    public static final ec<Boolean> bK = m2656a("qq", Boolean.valueOf(false));
    public static final ec<Boolean> bL = m2656a("qq1", Boolean.valueOf(true));
    public static final ec<Boolean> bM = m2656a("hw_accelerate_webviews", Boolean.valueOf(false));
    public static final ec<Boolean> bN = m2656a("mute_videos", Boolean.valueOf(false));
    public static final ec<Boolean> bO = m2656a("show_mute_by_default", Boolean.valueOf(false));
    public static final ec<Boolean> bP = m2656a("mute_with_user_settings", Boolean.valueOf(true));
    public static final ec<String> bQ = m2656a("top_level_events", "landing,paused,resumed,checkout,iap");
    public static final ec<Boolean> bR = m2656a("events_enabled", Boolean.valueOf(true));
    public static final ec<Boolean> bS = m2656a("force_ssl", Boolean.valueOf(false));
    public static final ec<Integer> bT = m2656a("postback_service_max_queue_size", Integer.valueOf(100));
    public static final ec<Integer> bU = m2656a("max_postback_attempts", Integer.valueOf(3));
    public static final ec<Boolean> bV = m2656a("click_overlay_enabled", Boolean.valueOf(false));
    public static final ec<String> bW = m2656a("click_overlay_color", "#66000000");
    public static final ec<Integer> bX = m2656a("click_tracking_retry_count_v1", Integer.valueOf(3));
    public static final ec<Integer> bY = m2656a("click_tracking_retry_delay", Integer.valueOf(2000));
    public static final ec<Integer> bZ = m2656a("click_tracking_timeout", Integer.valueOf(10000));
    public static final ec<Integer> ba = m2656a("cache_max_size_mb", Integer.valueOf(-1));
    public static final ec<Boolean> bb = m2656a("preload_merge_init_tasks_inter_regular_direct", Boolean.valueOf(false));
    public static final ec<Boolean> bc = m2656a("preload_merge_init_tasks_inter_videoa_direct", Boolean.valueOf(false));
    public static final ec<Boolean> bd = m2656a("preload_merge_init_tasks_banner_regular_direct", Boolean.valueOf(false));
    public static final ec<Boolean> be = m2656a("preload_merge_init_tasks_mrec_regular_direct", Boolean.valueOf(false));
    public static final ec<Boolean> bf = m2656a("preload_merge_init_tasks_leader_regular_direct", Boolean.valueOf(false));
    public static final ec<Boolean> bg = m2656a("preload_merge_init_tasks_inter_regular_indirect", Boolean.valueOf(false));
    public static final ec<Boolean> bh = m2656a("preload_merge_init_tasks_inter_videoa_indirect", Boolean.valueOf(false));
    public static final ec<Boolean> bi = m2656a("preload_merge_init_tasks_banner_regular_indirect", Boolean.valueOf(false));
    public static final ec<Boolean> bj = m2656a("preload_merge_init_tasks_mrec_regular_indirect", Boolean.valueOf(false));
    public static final ec<Boolean> bk = m2656a("preload_merge_init_tasks_leader_regular_indirect", Boolean.valueOf(false));
    public static final ec<Boolean> bl = m2656a("preload_merge_init_tasks_zones", Boolean.valueOf(false));
    public static final ec<Integer> bm = m2656a("submit_postback_timeout", Integer.valueOf(10000));
    public static final ec<Integer> bn = m2656a("submit_postback_retries", Integer.valueOf(4));
    public static final ec<Integer> bo = m2656a("widget_imp_tracking_delay", Integer.valueOf(2000));
    public static final ec<Boolean> bp = m2656a("draw_countdown_clock", Boolean.valueOf(true));
    public static final ec<Integer> bq = m2656a("countdown_clock_size", Integer.valueOf(32));
    public static final ec<Integer> br = m2656a("countdown_clock_stroke_size", Integer.valueOf(4));
    public static final ec<Integer> bs = m2656a("countdown_clock_text_size", Integer.valueOf(28));
    public static final ec<Boolean> bt = m2656a("ad_auto_preload_native", Boolean.valueOf(false));
    public static final ec<Boolean> bu = m2656a("preload_native_ad_on_dequeue", Boolean.valueOf(false));
    public static final ec<Boolean> bv = m2656a("widget_fail_on_slot_count_diff", Boolean.valueOf(true));
    public static final ec<Boolean> bw = m2656a("video_zero_length_as_computed", Boolean.valueOf(false));
    public static final ec<Integer> bx = m2656a("video_countdown_clock_margin", Integer.valueOf(10));
    public static final ec<Integer> by = m2656a("video_countdown_clock_gravity", Integer.valueOf(83));
    public static final ec<Integer> bz = m2656a("widget_latch_timeout_ms", Integer.valueOf(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL));
    /* renamed from: c */
    public static final ec<Boolean> f2405c = m2656a("honor_publisher_settings_verbose_logging", Boolean.valueOf(true));
    public static final ec<Boolean> cA = m2656a("handle_window_actions", Boolean.valueOf(false));
    public static final ec<String> cB = m2656a("soft_buttons_resource_id", "config_showNavigationBar");
    public static final ec<Boolean> cC = m2656a("immediate_render", Boolean.valueOf(false));
    public static final ec<Long> cD = m2656a("video_resume_delay", Long.valueOf(250));
    public static final ec<Long> cE = m2656a("force_hide_status_bar_delay_ms", Long.valueOf(0));
    public static final ec<Boolean> cF = m2656a("load_ads_if_no_internet", Boolean.valueOf(true));
    public static final ec<Boolean> cG = m2656a("display_ads_if_no_internet", Boolean.valueOf(true));
    public static final ec<Boolean> cH = m2656a("network_available_if_none_detected", Boolean.valueOf(true));
    public static final ec<Long> cI = m2656a("expandable_close_button_animation_duration_ms", Long.valueOf(300));
    public static final ec<Integer> cJ = m2656a("expandable_close_button_size", Integer.valueOf(27));
    public static final ec<Integer> cK = m2656a("expandable_h_close_button_margin", Integer.valueOf(10));
    public static final ec<Integer> cL = m2656a("expandable_t_close_button_margin", Integer.valueOf(10));
    public static final ec<Boolean> cM = m2656a("expandable_lhs_close_button", Boolean.valueOf(false));
    public static final ec<Integer> cN = m2656a("expandable_close_button_touch_area", Integer.valueOf(0));
    public static final ec<Long> cO = m2656a("progress_bar_step", Long.valueOf(25));
    public static final ec<Integer> cP = m2656a("progress_bar_scale", Integer.valueOf(10000));
    public static final ec<Integer> cQ = m2656a("progress_bar_vertical_padding", Integer.valueOf(-8));
    public static final ec<Boolean> cR = m2656a("click_failed_expand", Boolean.valueOf(false));
    public static final ec<Boolean> cS = m2656a("ignore_is_showing", Boolean.valueOf(false));
    public static final ec<Boolean> cT = m2656a("wrapped_zones", Boolean.valueOf(false));
    public static final ec<String> cU = m2656a("wrapped_sizes", "");
    public static final ec<Boolean> cV = m2656a("return_wrapped_ad_on_empty_queue", Boolean.valueOf(false));
    public static final ec<Boolean> cW = m2656a("consider_wrapped_ad_preloaded", Boolean.valueOf(false));
    public static final ec<Boolean> cX = m2656a("preload_persisted_zones", Boolean.valueOf(true));
    public static final ec<Boolean> cY = m2656a("persist_zones", Boolean.valueOf(true));
    public static final ec<Boolean> cZ = m2656a("validate_zone_input", Boolean.valueOf(false));
    public static final ec<Integer> ca = m2656a("android_click_spinner_size", Integer.valueOf(50));
    public static final ec<Integer> cb = m2656a("android_click_spinner_style", Integer.valueOf(16973853));
    public static final ec<Boolean> cc = m2656a("android_require_external_storage_permission", Boolean.valueOf(true));
    public static final ec<Boolean> cd = m2656a("android_drop_nomedia", Boolean.valueOf(true));
    public static final ec<Boolean> ce = m2656a("native_auto_cache_preload_resources", Boolean.valueOf(true));
    public static final ec<Boolean> cf = m2656a("video_immersive_mode_enabled", Boolean.valueOf(false));
    public static final ec<String> cg = m2656a("fireos_manufacturer_list", "amazon");
    public static final ec<Boolean> ch = m2656a("fireos_collect_google_idfa_on_fail", Boolean.valueOf(false));
    public static final ec<Boolean> ci = m2656a("fireos_hide_adview_on_init", Boolean.valueOf(true));
    public static final ec<Boolean> cj = m2656a("fireos_load_empty_adview_on_init", Boolean.valueOf(false));
    public static final ec<Boolean> ck = m2656a("sanitize_webview", Boolean.valueOf(false));
    public static final ec<Boolean> cl = m2656a("force_rerender", Boolean.valueOf(false));
    public static final ec<String> cm = m2656a("webview_package_name", "com.google.android.webview");
    public static final ec<Boolean> cn = m2656a("check_webview_has_gesture", Boolean.valueOf(false));
    public static final ec<Boolean> co = m2656a("adr", Boolean.valueOf(false));
    public static final ec<Boolean> cp = m2656a("hgn", Boolean.valueOf(false));
    public static final ec<String> cq = m2656a("emulator_hardware_list", "ranchu,goldfish,vbox");
    public static final ec<String> cr = m2656a("emulator_device_list", "generic,vbox");
    public static final ec<String> cs = m2656a("emulator_manufacturer_list", "Genymotion");
    public static final ec<String> ct = m2656a("emulator_model_list", "Android SDK built for x86");
    public static final ec<Long> cu = m2656a("inter_display_delay", Long.valueOf(200));
    public static final ec<Float> cv = m2656a("volume_normalization_factor", Float.valueOf(6.6666665f));
    public static final ec<Boolean> cw = m2656a("lock_specific_orientation", Boolean.valueOf(false));
    public static final ec<Boolean> cx = m2656a("video_callbacks_for_incent_nonvideo_ads_enabled", Boolean.valueOf(true));
    public static final ec<Boolean> cy = m2656a("user_agent_collection_enabled", Boolean.valueOf(false));
    public static final ec<Long> cz = m2656a("user_agent_collection_timeout_ms", Long.valueOf(600));
    /* renamed from: d */
    public static final ec<Boolean> f2406d = m2656a("honor_publisher_settings_auto_preload_ad_sizes", Boolean.valueOf(true));
    public static final ec<Boolean> dA = m2656a("vast_validate_with_extension_if_no_video_type", Boolean.valueOf(true));
    public static final ec<Integer> dB = m2656a("num_ads_preload_ahead", Integer.valueOf(2));
    public static final ec<Boolean> dC = m2656a("mediation_load_last_session_adapters_on_next_init", Boolean.valueOf(true));
    public static final ec<Boolean> dD = m2656a("mediation_load_predefined_adapters_on_next_init", Boolean.valueOf(true));
    public static final ec<Boolean> dE = m2656a("mediation_track_imp", Boolean.valueOf(true));
    public static final ec<Boolean> dF = m2656a("mediation_track_clk", Boolean.valueOf(true));
    public static final ec<Boolean> dG = m2656a("mediation_track_err", Boolean.valueOf(true));
    public static final ec<Boolean> dH = m2656a("submit_ad_stats_enabled", Boolean.valueOf(false));
    public static final ec<Integer> dI = m2656a("submit_ad_stats_connection_timeout", Integer.valueOf(30000));
    public static final ec<Integer> dJ = m2656a("submit_ad_stats_retry_count", Integer.valueOf(1));
    public static final ec<Integer> dK = m2656a("submit_ad_stats_max_count", Integer.valueOf(TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL));
    private static final List<?> dL = Arrays.asList(new Class[]{Boolean.class, Float.class, Integer.class, Long.class, String.class});
    private static final List<ec<?>> dM = new ArrayList();
    public static final ec<Boolean> da = m2656a("cleanup_webview", Boolean.valueOf(false));
    public static final ec<Boolean> db = m2656a("dismiss_expanded_adview_on_refresh", Boolean.valueOf(false));
    public static final ec<Boolean> dc = m2656a("dismiss_expanded_adview_on_detach", Boolean.valueOf(false));
    public static final ec<Boolean> dd = m2656a("detach_update_listener_on_pause", Boolean.valueOf(false));
    public static final ec<Boolean> de = m2656a("contract_on_close_ad_command", Boolean.valueOf(false));
    public static final ec<Integer> df = m2656a("response_buffer_size", Integer.valueOf(16000));
    public static final ec<Boolean> dg = m2656a("sp_apply", Boolean.valueOf(true));
    public static final ec<Boolean> dh = m2656a("sr_0", Boolean.valueOf(true));
    public static final ec<Integer> di = m2656a("fetch_basic_settings_connection_timeout_ms", Integer.valueOf(DownloadManager.OPERATION_TIMEOUT));
    public static final ec<Integer> dj = m2656a("fetch_basic_settings_retry_count", Integer.valueOf(3));
    public static final ec<Integer> dk = m2656a("fetch_basic_settings_retry_delay_ms", Integer.valueOf(2000));
    public static final ec<Boolean> dl = m2656a("session_tracking_enabled", Boolean.valueOf(false));
    public static final ec<Boolean> dm = m2656a("session_tracking_cooldown_on_event_fire", Boolean.valueOf(true));
    public static final ec<Long> dn = m2656a("session_tracking_resumed_cooldown_minutes", Long.valueOf(90));
    /* renamed from: do */
    public static final ec<Long> f2407do = m2656a("session_tracking_paused_cooldown_minutes", Long.valueOf(90));
    public static final ec<Boolean> dp = m2656a("track_app_paused", Boolean.valueOf(false));
    public static final ec<Integer> dq = m2656a("vast_max_wrapper_depth", Integer.valueOf(5));
    public static final ec<Integer> dr = m2656a("vast_wrapper_resolution_retry_count_v1", Integer.valueOf(1));
    public static final ec<Integer> ds = m2656a("vast_wrapper_resolution_connection_timeout", Integer.valueOf(30000));
    public static final ec<Integer> dt = m2656a("vast_max_response_length", Integer.valueOf(640000));
    public static final ec<Integer> du = m2656a("vast_video_selection_policy", Integer.valueOf(C1244q.MEDIUM.ordinal()));
    public static final ec<String> dv = m2656a("vast_image_html", "<html><head><style>html,body{height:100%;width:100%}body{background-image:url({SOURCE});background-repeat:no-repeat;background-size:contain;background-position:center;}a{position:absolute;top:0;bottom:0;left:0;right:0}</style></head><body><a href=\"applovin://com.applovin.sdk/adservice/track_click_now\"></a></body></html>");
    public static final ec<String> dw = m2656a("vast_link_html", "<html><head><style>html,body,iframe{height:100%;width:100%;}body{margin:0}iframe{border:0;overflow:hidden;position:absolute}</style></head><body><iframe src={SOURCE} frameborder=0></iframe></body></html>");
    public static final ec<Long> dx = m2656a("vast_progress_tracking_countdown_step", Long.valueOf(1000));
    public static final ec<String> dy = m2656a("vast_unsupported_video_types", "video/ogg,video/x-flv");
    public static final ec<String> dz = m2656a("vast_unsupported_video_extensions", "ogv,flv");
    /* renamed from: e */
    public static final ec<Boolean> f2408e = m2656a("honor_publisher_settings_auto_preload_ad_types", Boolean.valueOf(true));
    /* renamed from: f */
    public static final ec<String> f2409f = m2656a("device_id", "");
    /* renamed from: g */
    public static final ec<String> f2410g = m2656a("publisher_id", "");
    /* renamed from: h */
    public static final ec<String> f2411h = m2656a("device_token", "");
    /* renamed from: i */
    public static final ec<Integer> f2412i = m2656a("submit_data_retry_count_v1", Integer.valueOf(1));
    /* renamed from: j */
    public static final ec<Integer> f2413j = m2656a("vr_retry_count_v1", Integer.valueOf(1));
    /* renamed from: k */
    public static final ec<Integer> f2414k = m2656a("fetch_ad_retry_count_v1", Integer.valueOf(1));
    /* renamed from: l */
    public static final ec<Boolean> f2415l = m2656a("is_verbose_logging", Boolean.valueOf(false));
    /* renamed from: m */
    public static final ec<String> f2416m = m2656a("api_endpoint", "https://d.applovin.com/");
    /* renamed from: n */
    public static final ec<String> f2417n = m2656a("adserver_endpoint", "https://a.applovin.com/");
    /* renamed from: o */
    public static final ec<String> f2418o = m2656a("mediation_tracking_endpoint", "https://rt.applovin.com/med");
    /* renamed from: p */
    public static final ec<String> f2419p = m2656a("event_tracking_endpoint", "https://rt.applovin.com/pix");
    /* renamed from: q */
    public static final ec<String> f2420q = m2656a("api_backup_endpoint", "https://d.applvn.com/");
    /* renamed from: r */
    public static final ec<String> f2421r = m2656a("adserver_backup_endpoint", "https://a.applvn.com/");
    /* renamed from: s */
    public static final ec<String> f2422s = m2656a("event_tracking_backup_endpoint", "https://rt.applvn.com/pix");
    /* renamed from: t */
    public static final ec<Long> f2423t = m2656a("get_retry_delay_v1", Long.valueOf(TapjoyConstants.TIMER_INCREMENT));
    /* renamed from: u */
    public static final ec<String> f2424u = m2656a("hash_algorithm", Constants.SHA1);
    /* renamed from: v */
    public static final ec<Integer> f2425v = m2656a("short_hash_size", Integer.valueOf(16));
    /* renamed from: w */
    public static final ec<Integer> f2426w = m2656a("http_connection_timeout", Integer.valueOf(30000));
    /* renamed from: x */
    public static final ec<Integer> f2427x = m2656a("fetch_ad_connection_timeout", Integer.valueOf(30000));
    /* renamed from: y */
    public static final ec<Integer> f2428y = m2656a("http_socket_timeout", Integer.valueOf(20000));
    /* renamed from: z */
    public static final ec<Integer> f2429z = m2656a("ad_session_minutes", Integer.valueOf(60));

    /* renamed from: a */
    private static <T> ec<T> m2656a(String str, T t) {
        if (t == null) {
            throw new IllegalArgumentException("No default value specified");
        } else if (dL.contains(t.getClass())) {
            ec<T> ecVar = new ec(str, t);
            dM.add(ecVar);
            return ecVar;
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + t.getClass());
        }
    }

    /* renamed from: a */
    public static Collection<ec<?>> m2657a() {
        return Collections.unmodifiableList(dM);
    }

    /* renamed from: b */
    public static int m2658b() {
        return dM.size();
    }
}
