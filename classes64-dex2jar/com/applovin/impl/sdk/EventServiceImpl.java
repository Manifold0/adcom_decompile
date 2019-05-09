// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.Intent;
import java.util.UUID;
import java.util.Iterator;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import android.net.Uri;
import java.util.Map;
import com.applovin.sdk.AppLovinSdk;
import java.util.List;
import com.applovin.sdk.AppLovinEventService;

public class EventServiceImpl implements AppLovinEventService
{
    private final AppLovinSdkImpl a;
    private final List<String> b;
    
    public EventServiceImpl(final AppLovinSdk appLovinSdk) {
        this.a = (AppLovinSdkImpl)appLovinSdk;
        this.b = aa.a(((AppLovinSdkImpl)appLovinSdk).get(ea.bQ));
    }
    
    private Uri a(final String s, final Map<String, String> map) {
        try {
            return Uri.parse(s).buildUpon().encodedQuery(gd.a(map)).build();
        }
        catch (Throwable t) {
            this.a.getLogger().e("EventServiceImpl", "Unable to create postback uri due to invalid endpoint", t);
            return null;
        }
    }
    
    private String a() {
        return this.a.get(ea.p);
    }
    
    private HashMap<String, String> a(final du du, final aj aj) {
        final ah dataCollector = this.a.getDataCollector();
        final am a = dataCollector.a();
        final ak d = dataCollector.d();
        final boolean contains = this.b.contains(du.a());
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String c;
        if (contains) {
            c = gd.c(du.a());
        }
        else {
            c = "postinstall";
        }
        hashMap.put("event", c);
        hashMap.put("ts", Long.toString(du.c()));
        hashMap.put("platform", gd.c(a.c));
        hashMap.put("model", gd.c(a.a));
        hashMap.put("package_name", gd.c(d.c));
        hashMap.put("installer_name", gd.c(d.d));
        hashMap.put("sdk_key", this.a.getSdkKey());
        hashMap.put("ia", Long.toString(d.e));
        hashMap.put("api_did", this.a.get(ea.f));
        hashMap.put("brand", gd.c(a.d));
        hashMap.put("brand_name", gd.c(a.e));
        hashMap.put("hardware", gd.c(a.f));
        hashMap.put("revision", gd.c(a.g));
        hashMap.put("sdk_version", "8.0.1");
        hashMap.put("os", gd.c(a.b));
        hashMap.put("orientation_lock", a.l);
        hashMap.put("app_version", gd.c(d.b));
        hashMap.put("country_code", gd.c(a.i));
        hashMap.put("carrier", gd.c(a.j));
        hashMap.put("tz_offset", String.valueOf(a.o));
        String s;
        if (a.q) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("adr", s);
        hashMap.put("volume", String.valueOf(a.s));
        String s2;
        if (a.u) {
            s2 = "1";
        }
        else {
            s2 = "0";
        }
        hashMap.put("sim", s2);
        hashMap.put("gy", String.valueOf(a.v));
        this.a(aj, hashMap);
        final Boolean w = a.w;
        if (w != null) {
            hashMap.put("huc", w.toString());
        }
        final Boolean x = a.x;
        if (x != null) {
            hashMap.put("aru", x.toString());
        }
        final al r = a.r;
        if (r != null) {
            hashMap.put("act", String.valueOf(r.a));
            hashMap.put("acm", String.valueOf(r.b));
        }
        final String t = a.t;
        if (AppLovinSdkUtils.isValidString(t)) {
            hashMap.put("ua", gd.c(t));
        }
        if (!contains) {
            hashMap.put("sub_event", gd.c(du.a()));
        }
        return hashMap;
    }
    
    private Map<String, String> a(final Map<String, String> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                if (key instanceof String && value instanceof String) {
                    hashMap.put(key, value);
                }
                else {
                    this.a.getLogger().w("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    hashMap.put(key.toString(), value.toString());
                }
            }
        }
        return hashMap;
    }
    
    private void a(final aj aj, final Map<String, String> map) {
        final String b = aj.b;
        if (AppLovinSdkUtils.isValidString(b)) {
            map.put("idfa", b);
        }
        map.put("dnt", Boolean.toString(aj.a));
    }
    
    private void a(final du du, final boolean b) {
        if (!this.a.get(ea.bR)) {
            return;
        }
        this.a.getLogger().d("EventServiceImpl", "Tracking event: " + du);
        this.a(new as(this, du, b));
    }
    
    private void a(final er er) {
        this.a.getTaskManager().a(new eq(this.a, er), fe.b);
    }
    
    private void a(final String s, final Map<String, String> map, final boolean b) {
        this.a(new du(s, this.a(map), System.currentTimeMillis(), gd.b(UUID.randomUUID().toString())), b);
    }
    
    private String b() {
        return this.a.get(ea.s);
    }
    
    void a(final String s, final boolean b) {
        this.a(s, new HashMap<String, String>(), b);
    }
    
    @Override
    public void trackCheckout(final String s, final Map<String, String> map) {
        HashMap<String, String> hashMap;
        if (map != null) {
            hashMap = new HashMap<String, String>(map);
        }
        else {
            hashMap = new HashMap<String, String>(1);
        }
        hashMap.put("transaction_id", s);
        this.trackEvent("checkout", hashMap);
    }
    
    @Override
    public void trackEvent(final String s) {
        this.trackEvent(s, new HashMap<String, String>());
    }
    
    @Override
    public void trackEvent(final String s, final Map<String, String> map) {
        this.a(s, map, true);
    }
    
    @Override
    public void trackInAppPurchase(final Intent intent, Map<String, String> hashMap) {
        Label_0056: {
            if (hashMap == null) {
                break Label_0056;
            }
            hashMap = new HashMap<String, String>(hashMap);
            while (true) {
                try {
                    while (true) {
                        hashMap.put("receipt_data", intent.getStringExtra("INAPP_PURCHASE_DATA"));
                        hashMap.put("receipt_data_signature", intent.getStringExtra("INAPP_DATA_SIGNATURE"));
                        this.trackEvent("iap", hashMap);
                        return;
                        hashMap = new HashMap<String, String>();
                        continue;
                    }
                }
                catch (Exception ex) {
                    this.a.getLogger().userError("EventServiceImpl", "Unable to track in app purchase; invalid purchanse intent", ex);
                    continue;
                }
                break;
            }
        }
    }
}
