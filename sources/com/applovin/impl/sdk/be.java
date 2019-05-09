package com.applovin.impl.sdk;

import android.content.Context;
import android.widget.Toast;
import com.applovin.sdk.AppLovinSdkUtils;

public class be {
    /* renamed from: a */
    private final AppLovinSdkImpl f2133a;
    /* renamed from: b */
    private final String f2134b;
    /* renamed from: c */
    private final Context f2135c;

    public be(AppLovinSdkImpl appLovinSdkImpl, Context context, String str) {
        this.f2133a = appLovinSdkImpl;
        this.f2134b = str;
        this.f2135c = context;
    }

    /* renamed from: a */
    void m2352a() {
        AppLovinSdkUtils.runOnUiThread(new bf(this));
    }

    /* renamed from: a */
    void m2353a(String str, Throwable th) {
        this.f2133a.getLogger().userError("IncentivizedConfirmationManager", "Unable to show incentivized ad reward dialog. Have you defined com.applovin.adview.AppLovinConfirmationActivity in your manifest?", th);
        Toast.makeText(this.f2135c, str, 1).show();
    }

    /* renamed from: b */
    String m2354b() {
        return this.f2134b.equals("accepted") ? (String) this.f2133a.get(ea.aa) : this.f2134b.equals("quota_exceeded") ? (String) this.f2133a.get(ea.ab) : this.f2134b.equals("rejected") ? (String) this.f2133a.get(ea.ac) : (String) this.f2133a.get(ea.ad);
    }
}
