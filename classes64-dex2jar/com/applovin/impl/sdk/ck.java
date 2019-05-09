// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import java.util.Collections;
import org.json.JSONException;
import java.util.Map;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;
import com.applovin.mediation.AppLovinMediatedAdInfo;

public class ck extends q
{
    private final boolean e;
    private final AppLovinMediatedAdInfo f;
    
    public ck(final ck ck, final boolean e, final AppLovinMediatedAdInfo f) {
        super(ck.a, ck.b, ck.c);
        this.e = e;
        this.f = f;
    }
    
    ck(final JSONObject jsonObject, final JSONObject jsonObject2, final AppLovinSdkImpl appLovinSdkImpl) {
        super(jsonObject, jsonObject2, appLovinSdkImpl);
        this.e = false;
        this.f = null;
    }
    
    public boolean a() {
        return this.e;
    }
    
    String b() {
        return bu.a(this.a, "class", (String)null, this.c);
    }
    
    String c() {
        return bu.a(this.a, "name", (String)null, this.c);
    }
    
    public AppLovinMediatedAdInfo d() {
        return this.f;
    }
    
    public Map<String, String> e() {
        if (this.a.has("config")) {
            try {
                return bu.a(this.a.getJSONObject("config"));
            }
            catch (JSONException ex) {
                this.c.getLogger().e("MediatedAd", "Failed to retrieve mediation configuration", (Throwable)ex);
            }
        }
        return Collections.emptyMap();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!super.equals(o)) {
                return false;
            }
            final ck ck = (ck)o;
            if (this.e != ck.e) {
                return false;
            }
            if (this.f != null) {
                return this.f.equals(ck.f);
            }
            if (ck.f != null) {
                return false;
            }
        }
        return true;
    }
    
    public int g() {
        return bu.a(this.a, "timeout_sec", 5, this.c);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = super.hashCode();
        int n;
        if (this.e) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (this.f != null) {
            hashCode = this.f.hashCode();
        }
        return (n + hashCode2 * 31) * 31 + hashCode;
    }
}
