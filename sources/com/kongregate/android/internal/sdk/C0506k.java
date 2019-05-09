package com.kongregate.android.internal.sdk;

import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.internal.util.C0562j;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.kongregate.android.internal.sdk.k */
public class C0506k {
    /* renamed from: a */
    public static final String f529a = "KONG_OPTION_DOMAIN";
    /* renamed from: b */
    public static final String f530b = "KONG_OPTION_DEBUG";
    /* renamed from: c */
    public static final String f531c = "KONG_OPTION_DEBUG_WEBVIEW";
    /* renamed from: d */
    public static final String f532d = "KONG_OPTION_MANAGE_LIFECYCLE";
    /* renamed from: e */
    public static final String f533e = "KONG_OPTION_SYSTEM_UI";
    /* renamed from: f */
    public static final String f534f = "KONG_OPTION_ALLOW_IMMERSIVE_MODE";
    /* renamed from: g */
    public static final String f535g = "KONG_OPTION_PROJECT_ID";
    /* renamed from: h */
    public static final String f536h = "KONG_OPTION_WRITE_KEY";
    /* renamed from: i */
    public static final String f537i = "KONG_OPTION_ANALYTICS_FLAG";
    /* renamed from: j */
    public static final String f538j = "KONGREGATE_OPTION_DEFER_ANALYTICS";
    /* renamed from: k */
    public static final String f539k = "KONGREGATE_OPTION_PERSISTENT_WEBVIEW";
    /* renamed from: l */
    public static final String f540l = "KONGREGATE_OPTION_GUILD_CHAT";
    /* renamed from: m */
    public static final String f541m = "KONG_AUTO_ANALYTICS_FILTER";
    /* renamed from: n */
    public static final String f542n = "KONG_ADX_ENABLED";
    /* renamed from: o */
    public static final String f543o = "KONG_ADX_UPGRADE";
    /* renamed from: p */
    public static final String f544p = "KONG_APP_ID";
    /* renamed from: q */
    public static final String f545q = "KONG_API_KEY";
    /* renamed from: r */
    public static final String f546r = "KONG_OPTION_PANEL_ORIENTATION_OVERRIDE";
    /* renamed from: s */
    public static final String f547s = "KONG_OPTION_CRASHLYTICS_LOGGING";
    /* renamed from: t */
    public static final String f548t = "KONG_OPTION_CRASHLYTICS_USER_KEYS";
    /* renamed from: u */
    public static final String f549u = "KONG_OPTIONS_STRICT_LIFECYLE";
    /* renamed from: v */
    public static Map<String, String> f550v = new HashMap();

    static {
        f550v.put(f529a, "domain");
        f550v.put(f530b, "debug");
        f550v.put(f531c, KongregateAPI.KONGREGATE_OPTION_DEBUG_WEBVIEW);
        f550v.put(f532d, KongregateAPI.KONGREGATE_OPTION_MANAGE_LIFECYCLE);
        f550v.put(f533e, KongregateAPI.KONGREGATE_OPTION_SHOW_SYSTEM_UI);
        f550v.put(f534f, KongregateAPI.KONGREGATE_OPTION_ALLOW_IMMERSIVE_MODE);
        f550v.put(f535g, KongregateAPI.KONGREGATE_OPTION_KEEN_PROJECT_ID);
        f550v.put(f536h, KongregateAPI.KONGREGATE_OPTION_KEEN_WRITE_KEY);
        f550v.put(f537i, KongregateAPI.KONGREGATE_OPTION_ANALYTICS_MODE);
        f550v.put(f538j, KongregateAPI.KONGREGATE_OPTION_DEFER_ANALYTICS);
        f550v.put(f539k, KongregateAPI.KONGREGATE_OPTION_PERSISTENT_WEBVIEW);
        f550v.put(f540l, "guild_chat");
        f550v.put(f541m, KongregateAPI.KONGREGATE_OPTION_AUTO_ANALYTICS_FILTER);
        f550v.put(f542n, KongregateAPI.KONGREGATE_OPTION_ADX_ENABLED);
        f550v.put(f543o, KongregateAPI.KONGREGATE_OPTION_ADX_UPGRADE);
        f550v.put(f544p, KongregateAPI.KONGREGATE_OPTION_SWRVE_APP_ID);
        f550v.put(f545q, KongregateAPI.KONGREGATE_OPTION_SWRVE_API_KEY);
        f550v.put(f546r, KongregateAPI.KONGREGATE_OPTION_PANEL_ORIENTATION_OVERRIDE);
        f550v.put(f547s, KongregateAPI.KONGREGATE_OPTION_CRASHLYTICS_LOGGING);
        f550v.put(f548t, KongregateAPI.KONGREGATE_OPTION_CRASHLYTICS_USER_KEYS);
        f550v.put(f549u, KongregateAPI.KONGREGATE_OPTION_STRICT_LIFECYCLE_MODE);
    }

    /* renamed from: a */
    public static Map<String, Object> m455a(Map<String, Object> map) {
        Map hashMap = new HashMap(map);
        for (String str : map.keySet()) {
            if (f550v.containsKey(str)) {
                String str2 = (String) f550v.get(str);
                Object obj = map.get(str);
                C0562j.m753a("Remapping legacy setting: " + str + " to " + str2);
                hashMap.put(str2, obj);
            }
        }
        return hashMap;
    }
}
