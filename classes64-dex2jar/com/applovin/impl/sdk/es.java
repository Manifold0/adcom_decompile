// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONObject;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinPostbackListener;
import java.util.Map;

public class es extends dx
{
    private final String a;
    private final Map<String, String> b;
    private final AppLovinPostbackListener h;
    private String i;
    private int j;
    private long k;
    private int l;
    
    public es(final AppLovinSdkImpl appLovinSdkImpl, final String a, final Map<String, String> b, final AppLovinPostbackListener h) {
        super("TaskDispatchPostback", appLovinSdkImpl);
        this.l = -1;
        this.a = a;
        this.h = h;
        this.b = b;
    }
    
    public void a(final int j) {
        this.j = j;
    }
    
    public void a(final long k) {
        this.k = k;
    }
    
    public void a(final String i) {
        this.i = i;
    }
    
    public void b(final int l) {
        this.l = l;
    }
    
    @Override
    public void run() {
        if (!AppLovinSdkUtils.isValidString(this.a)) {
            this.d.getLogger().i("TaskDispatchPostback", "Requested URL is not valid; nothing to do...");
            this.h.onPostbackFailure(this.a, -900);
            return;
        }
        String s;
        if (this.b == null) {
            s = "GET";
        }
        else {
            s = "POST";
        }
        final et et = new et(this, s, new JSONObject(), "RepeatTaskDispatchPostback", this.d);
        et.a(this.a);
        et.b(this.i);
        JSONObject jsonObject;
        if (this.b == null) {
            jsonObject = null;
        }
        else {
            jsonObject = new JSONObject((Map)this.b);
        }
        et.a(jsonObject);
        et.a(this.k);
        int n;
        if (this.j < 0) {
            n = this.d.get(ea.bn);
        }
        else {
            n = this.j;
        }
        et.c(n);
        int n2;
        if (this.l < 0) {
            n2 = this.d.get(ea.bm);
        }
        else {
            n2 = this.l;
        }
        et.b(n2);
        et.a(false);
        et.run();
    }
}
