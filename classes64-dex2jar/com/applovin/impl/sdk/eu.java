// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;
import android.os.Build$VERSION;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.Map;

public class eu extends dx
{
    eu(final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskFetchBasicSettings", appLovinSdkImpl);
        this.g = true;
    }
    
    private String a(final Map<String, String> map) {
        return ag.b("i", map, this.d);
    }
    
    private String b(final Map<String, String> map) {
        return ag.d("i", map, this.d);
    }
    
    private Map<String, String> b() {
        final HashMap<String, String> hashMap = new HashMap<String, String>(11);
        hashMap.put("sdk_key", this.d.getSdkKey());
        hashMap.put("sdk_version", "8.0.1");
        hashMap.put("build", String.valueOf(89));
        final Boolean a = ac.a(this.f);
        if (a != null) {
            hashMap.put("huc", a.toString());
        }
        final Boolean b = ac.b(this.f);
        if (b != null) {
            hashMap.put("aru", b.toString());
        }
        final String s = this.d.get(ea.I);
        if (AppLovinSdkUtils.isValidString(s)) {
            hashMap.put("plugin_version", gd.c(s));
        }
        final String mediationProvider = this.d.getMediationProvider();
        if (AppLovinSdkUtils.isValidString(mediationProvider)) {
            hashMap.put("mediation_provider", gd.c(mediationProvider));
        }
        final ak d = this.d.getDataCollector().d();
        hashMap.put("package_name", gd.c(d.c));
        hashMap.put("app_version", gd.c(d.b));
        hashMap.put("platform", gd.c(this.d.getDataCollector().b()));
        hashMap.put("os", gd.c(Build$VERSION.RELEASE));
        return hashMap;
    }
    
    @Override
    public void run() {
        final Map<String, String> b = this.b();
        final String a = this.a(b);
        final String b2 = this.b(b);
        final ev ev = new ev(this, "GET", new JSONObject(), "TaskRepeatFetchBasicSettings", this.d);
        ev.g = this.g;
        ev.a(a);
        ev.b(b2);
        ev.c(this.d.get(ea.dj));
        ev.a((long)this.d.get(ea.dk));
        ev.b(this.d.get(ea.di));
        ev.a(ea.n);
        ev.b(ea.r);
        ev.run();
    }
}
