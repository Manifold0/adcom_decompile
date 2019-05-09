package com.applovin.impl.sdk;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.applovin.impl.sdk.a */
class C1273a {
    /* renamed from: a */
    private static final Object f2009a = new Object();
    /* renamed from: b */
    private static final Map<String, Map<String, String>> f2010b = new HashMap();

    /* renamed from: a */
    static Map<String, String> m2185a(AppLovinSdkImpl appLovinSdkImpl) {
        Map<String, String> c;
        synchronized (f2009a) {
            appLovinSdkImpl.getLogger().mo4172d("AdDataCache", "Reading cached device data...");
            c = C1273a.m2190c(appLovinSdkImpl);
        }
        return c;
    }

    /* renamed from: a */
    private static void m2186a(String str, Map<String, String> map) {
        List a = aa.m2194a(str, RequestParameters.EQUAL);
        if (a.size() == 2) {
            map.put(a.get(0), a.get(1));
        }
    }

    /* renamed from: a */
    static void m2187a(Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        C1273a.m2189b(map, appLovinSdkImpl);
    }

    /* renamed from: b */
    static void m2188b(AppLovinSdkImpl appLovinSdkImpl) {
        synchronized (f2009a) {
            appLovinSdkImpl.getLogger().mo4172d("AdDataCache", "Clearing old device data cache...");
            C1273a.m2187a(new HashMap(0), appLovinSdkImpl);
        }
    }

    /* renamed from: b */
    private static void m2189b(Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        if (map == null) {
            throw new IllegalArgumentException("No ad aata specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                synchronized (f2010b) {
                    Map map2 = (Map) f2010b.get("ad_data_cache");
                    if (map2 == null) {
                        map2 = new HashMap();
                    }
                    map2.clear();
                    map2.putAll(map);
                    f2010b.put("ad_data_cache", map2);
                }
                appLovinSdkImpl.put(ef.f2443d, gd.m2941a((Map) map));
                appLovinSdkImpl.getLogger().mo4172d("AdDataCache", map.size() + " " + "ad_data_cache" + " entries saved in cache");
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().mo4174e("AdDataCache", "Unable to save ad data entries", e);
            }
        }
    }

    /* renamed from: c */
    private static Map<String, String> m2190c(AppLovinSdkImpl appLovinSdkImpl) {
        Map map;
        Map hashMap;
        Throwable e;
        synchronized (f2010b) {
            map = (Map) f2010b.get("ad_data_cache");
        }
        if (map == null) {
            List<String> a = aa.m2194a((String) appLovinSdkImpl.get(ef.f2443d), RequestParameters.AMPERSAND);
            if (!a.isEmpty()) {
                try {
                    hashMap = new HashMap();
                    try {
                        for (String a2 : a) {
                            C1273a.m2186a(a2, hashMap);
                        }
                        synchronized (f2010b) {
                            f2010b.put("ad_data_cache", hashMap);
                        }
                        appLovinSdkImpl.getLogger().mo4172d("AdDataCache", hashMap.size() + " " + "ad_data_cache" + " entries loaded from cache");
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable e3) {
                    Throwable th = e3;
                    hashMap = map;
                    e = th;
                    appLovinSdkImpl.getLogger().mo4174e("AdDataCache", "Unable to load ad data", e);
                    appLovinSdkImpl.put(ef.f2443d, "");
                    return hashMap != null ? new HashMap(hashMap) : new HashMap();
                }
                if (hashMap != null) {
                }
            }
        }
        hashMap = map;
        if (hashMap != null) {
        }
    }
}
