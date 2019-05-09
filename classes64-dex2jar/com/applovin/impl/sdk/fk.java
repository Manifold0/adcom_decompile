// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONException;
import org.json.JSONArray;
import com.applovin.sdk.AppLovinAdLoadListener;

class fk extends dx implements AppLovinAdLoadListener
{
    final /* synthetic */ fj a;
    private final JSONArray b;
    private final int h;
    
    fk(final fj a, final int h, final JSONArray b) {
        this.a = a;
        super("TaskProcessNextWaterfallAd", a.d);
        if (b == null) {
            throw new IllegalArgumentException("No ad objects array specified");
        }
        if (h < 0 || h >= b.length()) {
            throw new IllegalArgumentException("Invalid ad index specified: " + h);
        }
        this.b = b;
        this.h = h;
    }
    
    private void a(final int n) throws JSONException {
        if ("adapter".equals(this.b(n))) {
            this.d.getTaskManager().a(new fi(this.b.getJSONObject(n), this.a.a, this.d), fe.b);
        }
    }
    
    private String b(final int n) {
        if (n < 0 || n >= this.b.length()) {
            return "undefined";
        }
        try {
            return bu.a(this.b.getJSONObject(n), "type", "undefined", this.d);
        }
        catch (JSONException ex) {
            this.e.e(this.c, "Unable to parse next ad from the ad response");
            return "undefined";
        }
    }
    
    private void b() throws JSONException {
        final JSONObject jsonObject = this.b.getJSONObject(this.h);
        final String b = this.b(this.h);
        if ("applovin".equalsIgnoreCase(b)) {
            this.e.d(this.c, "Starting task for AppLovin ad...");
            this.d.getTaskManager().a(new fp(jsonObject, this.a.a, this, this.d));
            return;
        }
        if ("vast".equalsIgnoreCase(b)) {
            this.e.d(this.c, "Starting task for VAST ad...");
            this.d.getTaskManager().a(fl.a(jsonObject, this.a.a, this, this.d));
            return;
        }
        if ("adapter".equalsIgnoreCase(b)) {
            this.e.d(this.c, "Starting task for adapter ad...");
            this.d.getTaskManager().a(new fc(jsonObject, this.a.a, this.d, this));
            return;
        }
        this.e.w(this.c, "Unable to process ad of unknown type: " + b);
        this.failedToReceiveAd(-800);
    }
    
    @Override
    public void adReceived(final AppLovinAd appLovinAd) {
        this.a.a(appLovinAd);
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        if (this.h < this.b.length() - 1) {
            this.e.i(this.c, "Attempting to load next ad (" + this.h + ") after failure...");
            this.d.getTaskManager().a(new fk(this.a, this.h + 1, this.b), fe.b);
            return;
        }
        this.a.b();
    }
    
    @Override
    public void run() {
        try {
            if (this.h == 0) {
                for (int intValue = this.d.get(ea.dB), n = 1; n <= intValue && n < this.b.length(); ++n) {
                    this.a(n);
                }
            }
            else {
                final int n2 = this.d.get(ea.dB) + this.h;
                if (n2 < this.b.length()) {
                    this.a(n2);
                }
            }
            this.b();
        }
        catch (Throwable t) {
            this.e.e(this.c, "Encountered error while processing ad number " + this.h, t);
            this.a.b();
        }
    }
}
