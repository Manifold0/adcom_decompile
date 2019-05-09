// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

import android.net.Uri;
import java.util.List;
import java.util.Map;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

public interface KongregateAPI
{
    public static final String CLOUD_GAME = "CLOUD";
    public static final String DISABLE_ALL = "DISABLE_ALL";
    public static final String ENABLE_ALL = "ENABLE_ALL";
    public static final String GUEST_GAME_AUTH_TOKEN = "Guest_Game_Auth_Token";
    public static final String GUEST_USERNAME = "Guest";
    public static final long GUEST_USER_ID = 0L;
    public static final String KONGREGATE_ADJUST_INSTALL_EVENT_TOKEN = "adjust.install";
    public static final String KONGREGATE_ADJUST_PREFIX = "adjust.";
    public static final String KONGREGATE_ADJUST_SALE_EVENT_TOKEN = "adjust.sale";
    public static final String KONGREGATE_ADJUST_SESSION_EVENT_TOKEN = "adjust.session";
    public static final String KONGREGATE_API_VERSION = "3.0.5";
    public static final String KONGREGATE_OPTION_ADJUST_APP_TOKEN = "adjust.app_token";
    public static final String KONGREGATE_OPTION_ADJUST_ENVIRONMENT = "adjust.environment";
    @Deprecated
    public static final String KONGREGATE_OPTION_ADX_ENABLED = "adx_enabled";
    @Deprecated
    public static final String KONGREGATE_OPTION_ADX_UPGRADE = "adx_upgrade";
    public static final String KONGREGATE_OPTION_ALLOW_IMMERSIVE_MODE = "allow_immersive_mode";
    public static final String KONGREGATE_OPTION_ANALYTICS_DOMAIN = "kong_analytics_domain";
    public static final String KONGREGATE_OPTION_ANALYTICS_MODE = "auto_analytics_mode";
    public static final String KONGREGATE_OPTION_AUTO_ANALYTICS_FILTER = "auto_analytics_filter";
    public static final String KONGREGATE_OPTION_AUTO_PROCESS_DEEP_LINKS = "KONG_OPTIONS_AUTO_PROCESS_DEEPLINKS";
    public static final String KONGREGATE_OPTION_AUTO_REPOSITION = "auto_reposition";
    public static final String KONGREGATE_OPTION_CRASHLYTICS_LOGGING = "crashlytics_logging";
    public static final String KONGREGATE_OPTION_CRASHLYTICS_USER_KEYS = "crashlytics_user_keys";
    public static final String KONGREGATE_OPTION_CUSTOM_PLAYER_ID = "custom_player_id";
    public static final String KONGREGATE_OPTION_DEBUG = "debug";
    public static final String KONGREGATE_OPTION_DEBUG_WEBVIEW = "debug_webview";
    public static final String KONGREGATE_OPTION_DEFAULT_PANEL_TRANSITION = "default_panel_transition";
    public static final String KONGREGATE_OPTION_DEFER_ANALYTICS = "defer_analytics";
    public static final String KONGREGATE_OPTION_DEFER_GDPR_ALERT = "defer_gdpr_check";
    public static final String KONGREGATE_OPTION_DELTA_COLLECT_URL = "delta_collect_url";
    public static final String KONGREGATE_OPTION_DELTA_ENGAGE_URL = "delta_engage_url";
    public static final String KONGREGATE_OPTION_DELTA_ENVIRONMENT_KEY = "delta_env_key";
    public static final String KONGREGATE_OPTION_DOMAIN = "domain";
    public static final String KONGREGATE_OPTION_GUILD_CHAT = "guild_chat";
    public static final String KONGREGATE_OPTION_KEEN_PROJECT_ID = "keen_product_id";
    public static final String KONGREGATE_OPTION_KEEN_WRITE_KEY = "keen_write_key";
    public static final String KONGREGATE_OPTION_KONG_ANALYTICS_ID = "kong_analytics_id";
    public static final String KONGREGATE_OPTION_KONG_ANALYTICS_KEY = "kong_analytics_key";
    public static final String KONGREGATE_OPTION_MANAGE_LIFECYCLE = "manage_lifecycle";
    public static final String KONGREGATE_OPTION_PANEL_ORIENTATION_OVERRIDE = "panel_orientation_override";
    public static final String KONGREGATE_OPTION_PERSISTENT_WEBVIEW = "persistent_webview";
    public static final String KONGREGATE_OPTION_SHOW_SYSTEM_UI = "show_system_ui";
    public static final String KONGREGATE_OPTION_STRICT_LIFECYCLE_MODE = "strict_lifecycle";
    public static final String KONGREGATE_OPTION_SUPPORTED_PANEL_EVENTS = "supported_panel_events";
    public static final String KONGREGATE_OPTION_SWRVE_API_KEY = "swrve_api_key";
    public static final String KONGREGATE_OPTION_SWRVE_APP_ID = "swrve_app_id";
    public static final String KONGREGATE_OPTION_TEST_GDPR_ALERT = "test.gdpr_alert";
    public static final String KONGREGATE_SWRVE_APP_STORE = "swrve.appStore";
    public static final String KONGREGATE_SWRVE_APP_VERSION = "swrve.appVersion";
    public static final String KONGREGATE_SWRVE_AUTO_DOWNLOAD = "swrve.autoDownload";
    public static final String KONGREGATE_SWRVE_CACHE_DIR = "swrve.cacheDir";
    public static final String KONGREGATE_SWRVE_DB_NAME = "swrve.dbName";
    public static final String KONGREGATE_SWRVE_LANGUAGE = "swrve.language";
    public static final String KONGREGATE_SWRVE_LINK_TOKEN = "swrve.linkToken";
    public static final String KONGREGATE_SWRVE_MAX_DB_SIZE = "swrve.maxDbSize";
    public static final String KONGREGATE_SWRVE_MAX_EVENTS_PER_FLUSH = "swrve.maxEventsPerFlush";
    public static final String KONGREGATE_SWRVE_PREFIX = "swrve.";
    public static final String KONGREGATE_SWRVE_SENDER_ID = "swrve.senderId";
    public static final String KONGREGATE_SWRVE_TALK_ENABLED = "swrve.talkEnabled";
    public static final String ORIENTATION_LANDSCAPE = "landscape";
    public static final String ORIENTATION_LANDSCAPE_SENSOR = "landscapeSensor";
    public static final String ORIENTATION_PORTRAIT = "portrait";
    public static final String ORIENTATION_PORTRAIT_SENSOR = "portraitSensor";
    
    void addEventBundleListener(final KongregateEventBundleListener p0);
    
    void addEventListener(final KongregateEventListener p0);
    
    AnalyticsServices analytics();
    
    String getApiKey();
    
    Context getApplicationContext();
    
    long getApplicationId();
    
    boolean isReady();
    
    MobileServices mobile();
    
    MicrotransactionServices mtx();
    
    void onCreate(final Activity p0, final Bundle p1);
    
    void onDestroy(final Activity p0);
    
    void onLowMemory();
    
    void onPause(final Activity p0);
    
    void onPause(final Activity p0, final String p1);
    
    void onPause(final Activity p0, final Map<String, Object> p1);
    
    void onResume(final Activity p0);
    
    void onResume(final Activity p0, final String p1);
    
    void onResume(final Activity p0, final Map<String, Object> p1);
    
    List<KongregateEventBundle> pollEventBundles();
    
    List<String> pollEvents();
    
    void removeEventBundleListener(final KongregateEventBundleListener p0);
    
    void removeEventListener(final KongregateEventListener p0);
    
    KongregateServices services();
    
    StatServices stats();
    
    void willOpenUrl(final Uri p0);
}
