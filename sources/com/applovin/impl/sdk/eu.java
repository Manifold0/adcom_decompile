package com.applovin.impl.sdk;

import android.os.Build.VERSION;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.kongregate.android.internal.sdk.C0498e;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class eu extends dx {
    eu(AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskFetchBasicSettings", appLovinSdkImpl);
        this.g = true;
    }

    /* renamed from: a */
    private String m2798a(Map<String, String> map) {
        return ag.m2243b("i", map, this.d);
    }

    /* renamed from: b */
    private String m2799b(Map<String, String> map) {
        return ag.m2248d("i", map, this.d);
    }

    /* renamed from: b */
    private Map<String, String> m2800b() {
        Map<String, String> hashMap = new HashMap(11);
        hashMap.put("sdk_key", this.d.getSdkKey());
        hashMap.put("sdk_version", AppLovinSdk.VERSION);
        hashMap.put(C0498e.f496m, String.valueOf(89));
        Boolean a = ac.m2209a(this.f);
        if (a != null) {
            hashMap.put("huc", a.toString());
        }
        a = ac.m2213b(this.f);
        if (a != null) {
            hashMap.put("aru", a.toString());
        }
        String str = (String) this.d.get(ea.f2385I);
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("plugin_version", gd.m2953c(str));
        }
        str = this.d.getMediationProvider();
        if (AppLovinSdkUtils.isValidString(str)) {
            hashMap.put("mediation_provider", gd.m2953c(str));
        }
        ak d = this.d.getDataCollector().m2269d();
        hashMap.put("package_name", gd.m2953c(d.f2034c));
        hashMap.put(TapjoyConstants.TJC_APP_VERSION_NAME, gd.m2953c(d.f2033b));
        hashMap.put(TapjoyConstants.TJC_PLATFORM, gd.m2953c(this.d.getDataCollector().m2267b()));
        hashMap.put("os", gd.m2953c(VERSION.RELEASE));
        return hashMap;
    }

    public void run() {
        Map b = m2800b();
        String a = m2798a(b);
        String b2 = m2799b(b);
        fs evVar = new ev(this, "GET", new JSONObject(), "TaskRepeatFetchBasicSettings", this.d);
        evVar.g = this.g;
        evVar.m2497a(a);
        evVar.m2502b(b2);
        evVar.m2503c(((Integer) this.d.get(ea.dj)).intValue());
        evVar.m2494a((long) ((Integer) this.d.get(ea.dk)).intValue());
        evVar.m2500b(((Integer) this.d.get(ea.di)).intValue());
        evVar.m2495a(ea.f2417n);
        evVar.m2501b(ea.f2421r);
        evVar.run();
    }
}
