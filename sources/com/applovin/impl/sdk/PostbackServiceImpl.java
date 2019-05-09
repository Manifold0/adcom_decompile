package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class PostbackServiceImpl implements AppLovinPostbackService {
    /* renamed from: a */
    private final AppLovinSdkImpl f2008a;

    PostbackServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2008a = appLovinSdkImpl;
    }

    public void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackAsync(str, null, null, appLovinPostbackListener);
    }

    public void dispatchPostbackAsync(String str, Map<String, String> map, String str2, int i, long j, int i2, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.isValidString(str)) {
            this.f2008a.m2145f();
            dx esVar = new es(this.f2008a, str, map, new dr(this, appLovinPostbackListener));
            esVar.m2791a(i);
            esVar.m2792a(j);
            esVar.m2794b(i2);
            esVar.m2793a(str2);
            this.f2008a.getTaskManager().m2855a(esVar, fe.POSTBACKS);
            return;
        }
        this.f2008a.getLogger().mo4173e("PostbackService", "Requested a postback dispatch for an empty URL; nothing to do...");
        if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(str, -900);
        }
    }

    public void dispatchPostbackAsync(String str, Map<String, String> map, String str2, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackAsync(str, map, str2, ((Integer) this.f2008a.get(ea.bn)).intValue(), ((Long) this.f2008a.get(ea.f2423t)).longValue(), ((Integer) this.f2008a.get(ea.bm)).intValue(), appLovinPostbackListener);
    }
}
