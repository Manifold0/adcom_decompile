// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import java.util.Iterator;
import com.kongregate.android.internal.util.j;
import java.util.HashMap;
import java.util.Map;

public class k
{
    public static final String a = "KONG_OPTION_DOMAIN";
    public static final String b = "KONG_OPTION_DEBUG";
    public static final String c = "KONG_OPTION_DEBUG_WEBVIEW";
    public static final String d = "KONG_OPTION_MANAGE_LIFECYCLE";
    public static final String e = "KONG_OPTION_SYSTEM_UI";
    public static final String f = "KONG_OPTION_ALLOW_IMMERSIVE_MODE";
    public static final String g = "KONG_OPTION_PROJECT_ID";
    public static final String h = "KONG_OPTION_WRITE_KEY";
    public static final String i = "KONG_OPTION_ANALYTICS_FLAG";
    public static final String j = "KONGREGATE_OPTION_DEFER_ANALYTICS";
    public static final String k = "KONGREGATE_OPTION_PERSISTENT_WEBVIEW";
    public static final String l = "KONGREGATE_OPTION_GUILD_CHAT";
    public static final String m = "KONG_AUTO_ANALYTICS_FILTER";
    public static final String n = "KONG_ADX_ENABLED";
    public static final String o = "KONG_ADX_UPGRADE";
    public static final String p = "KONG_APP_ID";
    public static final String q = "KONG_API_KEY";
    public static final String r = "KONG_OPTION_PANEL_ORIENTATION_OVERRIDE";
    public static final String s = "KONG_OPTION_CRASHLYTICS_LOGGING";
    public static final String t = "KONG_OPTION_CRASHLYTICS_USER_KEYS";
    public static final String u = "KONG_OPTIONS_STRICT_LIFECYLE";
    public static Map<String, String> v;
    
    static {
        (com.kongregate.android.internal.sdk.k.v = new HashMap<String, String>()).put("KONG_OPTION_DOMAIN", "domain");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_DEBUG", "debug");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_DEBUG_WEBVIEW", "debug_webview");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_MANAGE_LIFECYCLE", "manage_lifecycle");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_SYSTEM_UI", "show_system_ui");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_ALLOW_IMMERSIVE_MODE", "allow_immersive_mode");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_PROJECT_ID", "keen_product_id");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_WRITE_KEY", "keen_write_key");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_ANALYTICS_FLAG", "auto_analytics_mode");
        com.kongregate.android.internal.sdk.k.v.put("KONGREGATE_OPTION_DEFER_ANALYTICS", "defer_analytics");
        com.kongregate.android.internal.sdk.k.v.put("KONGREGATE_OPTION_PERSISTENT_WEBVIEW", "persistent_webview");
        com.kongregate.android.internal.sdk.k.v.put("KONGREGATE_OPTION_GUILD_CHAT", "guild_chat");
        com.kongregate.android.internal.sdk.k.v.put("KONG_AUTO_ANALYTICS_FILTER", "auto_analytics_filter");
        com.kongregate.android.internal.sdk.k.v.put("KONG_ADX_ENABLED", "adx_enabled");
        com.kongregate.android.internal.sdk.k.v.put("KONG_ADX_UPGRADE", "adx_upgrade");
        com.kongregate.android.internal.sdk.k.v.put("KONG_APP_ID", "swrve_app_id");
        com.kongregate.android.internal.sdk.k.v.put("KONG_API_KEY", "swrve_api_key");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_PANEL_ORIENTATION_OVERRIDE", "panel_orientation_override");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_CRASHLYTICS_LOGGING", "crashlytics_logging");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTION_CRASHLYTICS_USER_KEYS", "crashlytics_user_keys");
        com.kongregate.android.internal.sdk.k.v.put("KONG_OPTIONS_STRICT_LIFECYLE", "strict_lifecycle");
    }
    
    public static Map<String, Object> a(final Map<String, Object> map) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>(map);
        for (final String s : map.keySet()) {
            if (com.kongregate.android.internal.sdk.k.v.containsKey(s)) {
                final String s2 = com.kongregate.android.internal.sdk.k.v.get(s);
                final Object value = map.get(s);
                com.kongregate.android.internal.util.j.a("Remapping legacy setting: " + s + " to " + s2);
                hashMap.put(s2, value);
            }
        }
        return hashMap;
    }
}
