package com.applovin.impl.sdk;

import android.content.Intent;
import android.net.Uri;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.appevents.AppEventsConstants;
import com.tapjoy.TapjoyConstants;
import com.unity.purchasing.googleplay.IabHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class EventServiceImpl implements AppLovinEventService {
    /* renamed from: a */
    private final AppLovinSdkImpl f1977a;
    /* renamed from: b */
    private final List<String> f1978b;

    public EventServiceImpl(AppLovinSdk appLovinSdk) {
        this.f1977a = (AppLovinSdkImpl) appLovinSdk;
        this.f1978b = aa.m2193a((String) ((AppLovinSdkImpl) appLovinSdk).get(ea.bQ));
    }

    /* renamed from: a */
    private Uri m2151a(String str, Map<String, String> map) {
        try {
            return Uri.parse(str).buildUpon().encodedQuery(gd.m2941a((Map) map)).build();
        } catch (Throwable th) {
            this.f1977a.getLogger().mo4174e("EventServiceImpl", "Unable to create postback uri due to invalid endpoint", th);
            return null;
        }
    }

    /* renamed from: a */
    private String m2152a() {
        return (String) this.f1977a.get(ea.f2419p);
    }

    /* renamed from: a */
    private HashMap<String, String> m2155a(du duVar, aj ajVar) {
        ah dataCollector = this.f1977a.getDataCollector();
        am a = dataCollector.m2265a();
        ak d = dataCollector.m2269d();
        boolean contains = this.f1978b.contains(duVar.m2627a());
        Map hashMap = new HashMap();
        hashMap.put("event", contains ? gd.m2953c(duVar.m2627a()) : "postinstall");
        hashMap.put("ts", Long.toString(duVar.m2629c()));
        hashMap.put(TapjoyConstants.TJC_PLATFORM, gd.m2953c(a.f2041c));
        hashMap.put("model", gd.m2953c(a.f2039a));
        hashMap.put("package_name", gd.m2953c(d.f2034c));
        hashMap.put("installer_name", gd.m2953c(d.f2035d));
        hashMap.put("sdk_key", this.f1977a.getSdkKey());
        hashMap.put("ia", Long.toString(d.f2036e));
        hashMap.put("api_did", this.f1977a.get(ea.f2409f));
        hashMap.put("brand", gd.m2953c(a.f2042d));
        hashMap.put("brand_name", gd.m2953c(a.f2043e));
        hashMap.put("hardware", gd.m2953c(a.f2044f));
        hashMap.put("revision", gd.m2953c(a.f2045g));
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put("os", gd.m2953c(a.f2040b));
        hashMap.put("orientation_lock", a.f2050l);
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, gd.m2953c(d.f2033b));
        hashMap.put(TapjoyConstants.TJC_DEVICE_COUNTRY_CODE, gd.m2953c(a.f2047i));
        hashMap.put("carrier", gd.m2953c(a.f2048j));
        hashMap.put("tz_offset", String.valueOf(a.f2053o));
        hashMap.put("adr", a.f2055q ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put("volume", String.valueOf(a.f2057s));
        hashMap.put("sim", a.f2059u ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put("gy", String.valueOf(a.f2060v));
        m2157a(ajVar, hashMap);
        Boolean bool = a.f2061w;
        if (bool != null) {
            hashMap.put("huc", bool.toString());
        }
        bool = a.f2062x;
        if (bool != null) {
            hashMap.put("aru", bool.toString());
        }
        al alVar = a.f2056r;
        if (alVar != null) {
            hashMap.put("act", String.valueOf(alVar.f2037a));
            hashMap.put("acm", String.valueOf(alVar.f2038b));
        }
        String str = a.f2058t;
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("ua", gd.m2953c(str));
        }
        if (!contains) {
            hashMap.put("sub_event", gd.m2953c(duVar.m2627a()));
        }
        return hashMap;
    }

    /* renamed from: a */
    private Map<String, String> m2156a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if ((key instanceof String) && (value instanceof String)) {
                    hashMap.put((String) key, (String) value);
                } else {
                    this.f1977a.getLogger().mo4178w("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    hashMap.put(key.toString(), value.toString());
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m2157a(aj ajVar, Map<String, String> map) {
        String str = ajVar.f2031b;
        if (AppLovinSdkUtils.isValidString(str)) {
            map.put("idfa", str);
        }
        map.put("dnt", Boolean.toString(ajVar.f2030a));
    }

    /* renamed from: a */
    private void m2158a(du duVar, boolean z) {
        if (((Boolean) this.f1977a.get(ea.bR)).booleanValue()) {
            this.f1977a.getLogger().mo4172d("EventServiceImpl", "Tracking event: " + duVar);
            m2159a(new as(this, duVar, z));
        }
    }

    /* renamed from: a */
    private void m2159a(er erVar) {
        this.f1977a.getTaskManager().m2855a(new eq(this.f1977a, erVar), fe.BACKGROUND);
    }

    /* renamed from: a */
    private void m2160a(String str, Map<String, String> map, boolean z) {
        m2158a(new du(str, m2156a((Map) map), System.currentTimeMillis(), gd.m2950b(UUID.randomUUID().toString())), z);
    }

    /* renamed from: b */
    private String m2161b() {
        return (String) this.f1977a.get(ea.f2422s);
    }

    /* renamed from: a */
    void m2164a(String str, boolean z) {
        m2160a(str, new HashMap(), z);
    }

    public void trackCheckout(String str, Map<String, String> map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap(1);
        hashMap.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, str);
        trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, hashMap);
    }

    public void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    public void trackEvent(String str, Map<String, String> map) {
        m2160a(str, (Map) map, true);
    }

    public void trackInAppPurchase(Intent intent, Map<String, String> map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap();
        try {
            hashMap.put("receipt_data", intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA));
            hashMap.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE));
        } catch (Throwable e) {
            this.f1977a.getLogger().userError("EventServiceImpl", "Unable to track in app purchase; invalid purchanse intent", e);
        }
        trackEvent("iap", hashMap);
    }
}
