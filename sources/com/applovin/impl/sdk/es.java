package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;
import org.json.JSONObject;

public class es extends dx {
    /* renamed from: a */
    private final String f2474a;
    /* renamed from: b */
    private final Map<String, String> f2475b;
    /* renamed from: h */
    private final AppLovinPostbackListener f2476h;
    /* renamed from: i */
    private String f2477i;
    /* renamed from: j */
    private int f2478j;
    /* renamed from: k */
    private long f2479k;
    /* renamed from: l */
    private int f2480l = -1;

    public es(AppLovinSdkImpl appLovinSdkImpl, String str, Map<String, String> map, AppLovinPostbackListener appLovinPostbackListener) {
        super("TaskDispatchPostback", appLovinSdkImpl);
        this.f2474a = str;
        this.f2476h = appLovinPostbackListener;
        this.f2475b = map;
    }

    /* renamed from: a */
    public void m2791a(int i) {
        this.f2478j = i;
    }

    /* renamed from: a */
    public void m2792a(long j) {
        this.f2479k = j;
    }

    /* renamed from: a */
    public void m2793a(String str) {
        this.f2477i = str;
    }

    /* renamed from: b */
    public void m2794b(int i) {
        this.f2480l = i;
    }

    public void run() {
        if (AppLovinSdkUtils.isValidString(this.f2474a)) {
            fs etVar = new et(this, this.f2475b == null ? "GET" : "POST", new JSONObject(), "RepeatTaskDispatchPostback", this.d);
            etVar.m2497a(this.f2474a);
            etVar.m2502b(this.f2477i);
            etVar.m2498a(this.f2475b == null ? null : new JSONObject(this.f2475b));
            etVar.m2494a(this.f2479k);
            etVar.m2503c(this.f2478j < 0 ? ((Integer) this.d.get(ea.bn)).intValue() : this.f2478j);
            etVar.m2500b(this.f2480l < 0 ? ((Integer) this.d.get(ea.bm)).intValue() : this.f2480l);
            etVar.m2499a(false);
            etVar.run();
            return;
        }
        this.d.getLogger().mo4175i("TaskDispatchPostback", "Requested URL is not valid; nothing to do...");
        this.f2476h.onPostbackFailure(this.f2474a, -900);
    }
}
