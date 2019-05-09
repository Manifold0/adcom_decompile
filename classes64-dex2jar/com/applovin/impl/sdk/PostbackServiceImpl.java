// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;

public class PostbackServiceImpl implements AppLovinPostbackService
{
    private final AppLovinSdkImpl a;
    
    PostbackServiceImpl(final AppLovinSdkImpl a) {
        this.a = a;
    }
    
    @Override
    public void dispatchPostbackAsync(final String s, final AppLovinPostbackListener appLovinPostbackListener) {
        this.dispatchPostbackAsync(s, null, null, appLovinPostbackListener);
    }
    
    public void dispatchPostbackAsync(final String s, final Map<String, String> map, final String s2, final int n, final long n2, final int n3, final AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.isValidString(s)) {
            this.a.f();
            final es es = new es(this.a, s, map, new dr(this, appLovinPostbackListener));
            es.a(n);
            es.a(n2);
            es.b(n3);
            es.a(s2);
            this.a.getTaskManager().a(es, fe.c);
        }
        else {
            this.a.getLogger().e("PostbackService", "Requested a postback dispatch for an empty URL; nothing to do...");
            if (appLovinPostbackListener != null) {
                appLovinPostbackListener.onPostbackFailure(s, -900);
            }
        }
    }
    
    public void dispatchPostbackAsync(final String s, final Map<String, String> map, final String s2, final AppLovinPostbackListener appLovinPostbackListener) {
        this.dispatchPostbackAsync(s, map, s2, this.a.get(ea.bn), this.a.get(ea.t), this.a.get(ea.bm), appLovinPostbackListener);
    }
}
