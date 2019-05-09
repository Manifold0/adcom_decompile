// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import java.util.LinkedHashSet;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONArray;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.content.Context;

public class ag
{
    private static final int[] a;
    private static final int[] b;
    private static final int[] c;
    private static final String d;
    
    static {
        a = new int[] { 7, 4, 2, 1, 11 };
        b = new int[] { 5, 6, 10, 3, 9, 8, 14 };
        c = new int[] { 15, 12, 13 };
        d = ag.class.getSimpleName();
    }
    
    private static NetworkInfo a(final Context context) {
        if (ah.a("android.permission.ACCESS_NETWORK_STATE", context)) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
            if (connectivityManager != null) {
                return connectivityManager.getActiveNetworkInfo();
            }
        }
        return null;
    }
    
    static String a(final AppLovinSdkImpl appLovinSdkImpl) {
        final NetworkInfo a = a(appLovinSdkImpl.getApplicationContext());
        if (a != null) {
            final int type = a.getType();
            final int subtype = a.getSubtype();
            String s;
            if (type == 1) {
                s = "wifi";
            }
            else if (type == 0) {
                if (a(subtype, ag.a)) {
                    s = "2g";
                }
                else if (a(subtype, ag.b)) {
                    s = "3g";
                }
                else if (a(subtype, ag.c)) {
                    s = "4g";
                }
                else {
                    s = "mobile";
                }
            }
            else {
                s = "unknown";
            }
            appLovinSdkImpl.getLogger().d(ag.d, "Network " + type + "/" + subtype + " resolved to " + s);
            return s;
        }
        return "unknown";
    }
    
    static String a(final InputStream inputStream, final AppLovinSdkImpl appLovinSdkImpl) {
        if (inputStream == null) {
            return null;
        }
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final byte[] array = new byte[(int)appLovinSdkImpl.get(ea.df)];
            while (true) {
                final int read = inputStream.read(array);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(array, 0, read);
            }
        }
        catch (Throwable t) {
            appLovinSdkImpl.getLogger().e(ag.d, "Encountered error while reading stream", t);
            return null;
        }
        return byteArrayOutputStream.toString("UTF-8");
    }
    
    static String a(final String s, final String s2, final Map<String, String> map, final AppLovinSdkImpl appLovinSdkImpl) {
        if (s == null || s.length() < 4) {
            throw new IllegalArgumentException("Invalid domain specified");
        }
        if (s2 == null) {
            throw new IllegalArgumentException("No endpoint specified");
        }
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        final StringBuilder sb = new StringBuilder(s + s2);
        if (map != null && map.size() > 0) {
            sb.append("?" + gd.a(map));
        }
        return sb.toString();
    }
    
    static String a(final String s, Map<String, String> b, final AppLovinSdkImpl appLovinSdkImpl) {
        final String s2 = appLovinSdkImpl.get(ea.m);
        final String s3 = appLovinSdkImpl.get(ea.h);
        if (b == null) {
            b = b(appLovinSdkImpl);
        }
        else {
            b.putAll(b(appLovinSdkImpl));
        }
        return a(s2, s, b, appLovinSdkImpl);
    }
    
    static JSONObject a(final JSONObject jsonObject) throws JSONException {
        return (JSONObject)jsonObject.getJSONArray("results").get(0);
    }
    
    static void a(final int n, final AppLovinSdkImpl appLovinSdkImpl) {
        final ed settingsManager = appLovinSdkImpl.getSettingsManager();
        if (n == 401) {
            settingsManager.a(ea.f, "");
            settingsManager.a(ea.h, "");
            settingsManager.a();
        }
        else {
            if (n == 418) {
                settingsManager.a(ea.a, true);
                settingsManager.a();
                return;
            }
            if (n >= 400 && n < 500) {
                appLovinSdkImpl.h();
                return;
            }
            if (n == -1) {
                appLovinSdkImpl.h();
            }
        }
    }
    
    static void a(final JSONObject jsonObject, final AppLovinSdkImpl appLovinSdkImpl) {
        if (jsonObject == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        try {
            if (jsonObject.has("settings")) {
                final ed settingsManager = appLovinSdkImpl.getSettingsManager();
                if (!jsonObject.isNull("settings")) {
                    settingsManager.a(jsonObject.getJSONObject("settings"));
                    settingsManager.a();
                    appLovinSdkImpl.getLogger().d(ag.d, "New settings processed");
                }
            }
        }
        catch (JSONException ex) {
            appLovinSdkImpl.getLogger().e(ag.d, "Unable to parse settings out of API response", (Throwable)ex);
        }
    }
    
    private static boolean a(final int n, final int[] array) {
        final boolean b = false;
        final int length = array.length;
        int n2 = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n2 >= length) {
                break;
            }
            if (array[n2] == n) {
                b2 = true;
                break;
            }
            ++n2;
        }
        return b2;
    }
    
    public static boolean a(final Context context, final AppLovinSdkImpl appLovinSdkImpl) {
        boolean connected = true;
        int n;
        if (ah.a("android.permission.ACCESS_NETWORK_STATE", context) && context.getSystemService("connectivity") != null) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            final NetworkInfo a = a(context);
            if (a == null) {
                return appLovinSdkImpl.get(ea.cH);
            }
            connected = a.isConnected();
        }
        return connected;
    }
    
    static String b(final String s, final Map<String, String> map, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(appLovinSdkImpl.get(ea.n), s, map, appLovinSdkImpl);
    }
    
    private static Map<String, String> b(final AppLovinSdkImpl appLovinSdkImpl) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final String s = appLovinSdkImpl.get(ea.h);
        if (AppLovinSdkUtils.isValidString(s)) {
            hashMap.put("device_token", s);
            return hashMap;
        }
        hashMap.put("api_key", appLovinSdkImpl.getSdkKey());
        return hashMap;
    }
    
    static void b(final int n, final AppLovinSdkImpl appLovinSdkImpl) {
        if (n == 418) {
            final ed settingsManager = appLovinSdkImpl.getSettingsManager();
            settingsManager.a(ea.a, true);
            settingsManager.a();
        }
    }
    
    static void b(final JSONObject jsonObject, final AppLovinSdkImpl appLovinSdkImpl) {
        final JSONArray a = bu.a(jsonObject, "zones", (JSONArray)null, appLovinSdkImpl);
        if (a != null) {
            for (final n n : appLovinSdkImpl.getZoneManager().a(a)) {
                if (n.e()) {
                    appLovinSdkImpl.getNativeAdService().a(n);
                }
                else {
                    appLovinSdkImpl.getAdService().preloadAds(n);
                }
            }
            appLovinSdkImpl.c().a(appLovinSdkImpl.getZoneManager().b());
            appLovinSdkImpl.d().a(appLovinSdkImpl.getZoneManager().b());
        }
    }
    
    static String c(final String s, Map<String, String> b, final AppLovinSdkImpl appLovinSdkImpl) {
        final String s2 = appLovinSdkImpl.get(ea.q);
        if (b == null) {
            b = b(appLovinSdkImpl);
        }
        else {
            b.putAll(b(appLovinSdkImpl));
        }
        return a(s2, s, b, appLovinSdkImpl);
    }
    
    static String d(final String s, final Map<String, String> map, final AppLovinSdkImpl appLovinSdkImpl) {
        return a(appLovinSdkImpl.get(ea.r), s, map, appLovinSdkImpl);
    }
}
