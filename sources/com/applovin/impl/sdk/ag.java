package com.applovin.impl.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.sdk.precache.DownloadManager;
import com.tapjoy.TJAdUnitConstants;
import com.tapjoy.TapjoyConstants;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ag {
    /* renamed from: a */
    private static final int[] f2016a = new int[]{7, 4, 2, 1, 11};
    /* renamed from: b */
    private static final int[] f2017b = new int[]{5, 6, 10, 3, 9, 8, 14};
    /* renamed from: c */
    private static final int[] f2018c = new int[]{15, 12, 13};
    /* renamed from: d */
    private static final String f2019d = ag.class.getSimpleName();

    /* renamed from: a */
    private static NetworkInfo m2233a(Context context) {
        if (ah.m2251a("android.permission.ACCESS_NETWORK_STATE", context)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
        }
        return null;
    }

    /* renamed from: a */
    static String m2234a(AppLovinSdkImpl appLovinSdkImpl) {
        NetworkInfo a = m2233a(appLovinSdkImpl.getApplicationContext());
        if (a == null) {
            return "unknown";
        }
        int type = a.getType();
        int subtype = a.getSubtype();
        String str = type == 1 ? "wifi" : type == 0 ? m2241a(subtype, f2016a) ? "2g" : m2241a(subtype, f2017b) ? ConnectivityService.NETWORK_TYPE_3G : m2241a(subtype, f2018c) ? "4g" : TapjoyConstants.TJC_CONNECTION_TYPE_MOBILE : "unknown";
        appLovinSdkImpl.getLogger().mo4172d(f2019d, "Network " + type + "/" + subtype + " resolved to " + str);
        return str;
    }

    /* renamed from: a */
    static String m2235a(InputStream inputStream, AppLovinSdkImpl appLovinSdkImpl) {
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[((Integer) appLovinSdkImpl.get(ea.df)).intValue()];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return byteArrayOutputStream.toString("UTF-8");
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            appLovinSdkImpl.getLogger().mo4174e(f2019d, "Encountered error while reading stream", th);
            return null;
        }
    }

    /* renamed from: a */
    static String m2236a(String str, String str2, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        if (str == null || str.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            StringBuilder stringBuilder = new StringBuilder(str + str2);
            if (map != null && map.size() > 0) {
                stringBuilder.append("?" + gd.m2941a((Map) map));
            }
            return stringBuilder.toString();
        }
    }

    /* renamed from: a */
    static String m2237a(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        Map b;
        String str2 = (String) appLovinSdkImpl.get(ea.f2416m);
        String str3 = (String) appLovinSdkImpl.get(ea.f2411h);
        if (map == null) {
            b = m2244b(appLovinSdkImpl);
        } else {
            map.putAll(m2244b(appLovinSdkImpl));
        }
        return m2236a(str2, str, b, appLovinSdkImpl);
    }

    /* renamed from: a */
    static JSONObject m2238a(JSONObject jSONObject) throws JSONException {
        return (JSONObject) jSONObject.getJSONArray("results").get(0);
    }

    /* renamed from: a */
    static void m2239a(int i, AppLovinSdkImpl appLovinSdkImpl) {
        ed settingsManager = appLovinSdkImpl.getSettingsManager();
        if (i == 401) {
            settingsManager.m2668a(ea.f2409f, (Object) "");
            settingsManager.m2668a(ea.f2411h, (Object) "");
            settingsManager.m2667a();
        } else if (i == 418) {
            settingsManager.m2668a(ea.f2403a, Boolean.valueOf(true));
            settingsManager.m2667a();
        } else if (i >= 400 && i < TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL) {
            appLovinSdkImpl.m2147h();
        } else if (i == -1) {
            appLovinSdkImpl.m2147h();
        }
    }

    /* renamed from: a */
    static void m2240a(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        if (jSONObject == null) {
            throw new IllegalArgumentException("No response specified");
        } else if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else {
            try {
                if (jSONObject.has(DownloadManager.SETTINGS)) {
                    ed settingsManager = appLovinSdkImpl.getSettingsManager();
                    if (!jSONObject.isNull(DownloadManager.SETTINGS)) {
                        settingsManager.m2670a(jSONObject.getJSONObject(DownloadManager.SETTINGS));
                        settingsManager.m2667a();
                        appLovinSdkImpl.getLogger().mo4172d(f2019d, "New settings processed");
                    }
                }
            } catch (Throwable e) {
                appLovinSdkImpl.getLogger().mo4174e(f2019d, "Unable to parse settings out of API response", e);
            }
        }
    }

    /* renamed from: a */
    private static boolean m2241a(int i, int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m2242a(Context context, AppLovinSdkImpl appLovinSdkImpl) {
        boolean z = ah.m2251a("android.permission.ACCESS_NETWORK_STATE", context) && context.getSystemService("connectivity") != null;
        if (!z) {
            return true;
        }
        NetworkInfo a = m2233a(context);
        return a != null ? a.isConnected() : ((Boolean) appLovinSdkImpl.get(ea.cH)).booleanValue();
    }

    /* renamed from: b */
    static String m2243b(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        return m2236a((String) appLovinSdkImpl.get(ea.f2417n), str, map, appLovinSdkImpl);
    }

    /* renamed from: b */
    private static Map<String, String> m2244b(AppLovinSdkImpl appLovinSdkImpl) {
        Map<String, String> hashMap = new HashMap();
        String str = (String) appLovinSdkImpl.get(ea.f2411h);
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("device_token", str);
        } else {
            hashMap.put(TapjoyConstants.TJC_API_KEY, appLovinSdkImpl.getSdkKey());
        }
        return hashMap;
    }

    /* renamed from: b */
    static void m2245b(int i, AppLovinSdkImpl appLovinSdkImpl) {
        if (i == 418) {
            ed settingsManager = appLovinSdkImpl.getSettingsManager();
            settingsManager.m2668a(ea.f2403a, Boolean.valueOf(true));
            settingsManager.m2667a();
        }
    }

    /* renamed from: b */
    static void m2246b(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl) {
        JSONArray a = bu.m2392a(jSONObject, "zones", null, (AppLovinSdk) appLovinSdkImpl);
        if (a != null) {
            Iterator it = appLovinSdkImpl.getZoneManager().m3069a(a).iterator();
            while (it.hasNext()) {
                C1287n c1287n = (C1287n) it.next();
                if (c1287n.m3056e()) {
                    appLovinSdkImpl.getNativeAdService().m2589a(c1287n);
                } else {
                    appLovinSdkImpl.getAdService().preloadAds(c1287n);
                }
            }
            appLovinSdkImpl.m2142c().mo4136a(appLovinSdkImpl.getZoneManager().m3072b());
            appLovinSdkImpl.m2143d().mo4136a(appLovinSdkImpl.getZoneManager().m3072b());
        }
    }

    /* renamed from: c */
    static String m2247c(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        Map b;
        String str2 = (String) appLovinSdkImpl.get(ea.f2420q);
        if (map == null) {
            b = m2244b(appLovinSdkImpl);
        } else {
            map.putAll(m2244b(appLovinSdkImpl));
        }
        return m2236a(str2, str, b, appLovinSdkImpl);
    }

    /* renamed from: d */
    static String m2248d(String str, Map<String, String> map, AppLovinSdkImpl appLovinSdkImpl) {
        return m2236a((String) appLovinSdkImpl.get(ea.f2421r), str, map, appLovinSdkImpl);
    }
}
