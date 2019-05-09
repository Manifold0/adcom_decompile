package com.applovin.impl.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.NativeProtocol;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class fu extends dv {
    /* renamed from: a */
    private final an f2537a;

    public fu(an anVar, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskReportReward", appLovinSdkImpl);
        this.f2537a = anVar;
    }

    public void run() {
        String b = dn.m2591a().m2595b(this.f2537a);
        if (b != null) {
            Map hashMap = new HashMap(6);
            hashMap.put("result", b);
            hashMap.put("zone_id", this.f2537a.mo3997t().m3051a());
            hashMap.put("fire_percent", Integer.valueOf(this.f2537a.ac()));
            Object n = this.f2537a.mo3996n();
            String str = "clcode";
            if (!AppLovinSdkUtils.isValidString(n)) {
                n = "NO_CLCODE";
            }
            hashMap.put(str, n);
            CharSequence userIdentifier = this.d.getUserIdentifier();
            if (!TextUtils.isEmpty(userIdentifier)) {
                hashMap.put("user_id", userIdentifier);
            }
            Map a = dn.m2591a().m2592a(this.f2537a);
            if (a != null) {
                hashMap.put(NativeProtocol.WEB_DIALOG_PARAMS, a);
            }
            m2630a("cr", new JSONObject(hashMap), new fv(this));
            return;
        }
        this.e.mo4173e("TaskReportReward", "No reward result was found for ad: " + this.f2537a);
    }
}
