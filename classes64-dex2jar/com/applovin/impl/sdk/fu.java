// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import org.json.JSONObject;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;

public class fu extends dv
{
    private final an a;
    
    public fu(final an a, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskReportReward", appLovinSdkImpl);
        this.a = a;
    }
    
    @Override
    public void run() {
        final String b = dn.a().b(this.a);
        if (b != null) {
            final HashMap<String, String> hashMap = new HashMap<String, String>(6);
            hashMap.put("result", b);
            hashMap.put("zone_id", this.a.t().a());
            hashMap.put("fire_percent", (String)this.a.ac());
            String n = this.a.n();
            if (!AppLovinSdkUtils.isValidString(n)) {
                n = "NO_CLCODE";
            }
            hashMap.put("clcode", n);
            final String userIdentifier = this.d.getUserIdentifier();
            if (!TextUtils.isEmpty((CharSequence)userIdentifier)) {
                hashMap.put("user_id", userIdentifier);
            }
            final Map<String, String> a = dn.a().a(this.a);
            if (a != null) {
                hashMap.put("params", (String)a);
            }
            this.a("cr", new JSONObject((Map)hashMap), new fv(this));
            return;
        }
        this.e.e("TaskReportReward", "No reward result was found for ad: " + this.a);
    }
}
