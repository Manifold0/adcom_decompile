// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import org.json.JSONArray;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdLoadListener;
import org.json.JSONObject;

class fj extends dx
{
    private final JSONObject a;
    private final n b;
    private final AppLovinAdLoadListener h;
    
    fj(final JSONObject a, final n b, final AppLovinAdLoadListener h, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskProcessAdWaterfall", appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No zone specified");
        }
        this.a = a;
        this.b = b;
        this.h = h;
    }
    
    private void a(final int n) {
        gd.a(this.h, this.b, n, this.d);
    }
    
    private void a(final AppLovinAd appLovinAd) {
        try {
            if (this.h != null) {
                this.h.adReceived(appLovinAd);
            }
        }
        catch (Throwable t) {
            this.e.e(this.c, "Unable process a ad received notification", t);
        }
    }
    
    private void b() {
        this.a(-6);
    }
    
    @Override
    public void run() {
        try {
            this.e.d(this.c, "Processing ad response...");
            final JSONArray jsonArray = this.a.getJSONArray("ads");
            final int length = jsonArray.length();
            if (length > 0) {
                this.e.d(this.c, "Loading the first out of " + length + " ads...");
                this.d.getTaskManager().a(new fk(this, 0, jsonArray));
                return;
            }
            this.e.w(this.c, "No ads were returned from the server");
            this.a(204);
        }
        catch (Throwable t) {
            this.e.e(this.c, "Encountered error while processing ad response", t);
            this.b();
        }
    }
}
