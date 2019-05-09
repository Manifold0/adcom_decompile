// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdk;
import java.util.Arrays;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinAd;

abstract class q implements cj, AppLovinAd
{
    protected final JSONObject a;
    protected final JSONObject b;
    protected final AppLovinSdkImpl c;
    protected final Object d;
    private n e;
    private final long f;
    private aq g;
    
    q(final JSONObject a, final JSONObject b, final AppLovinSdkImpl c) {
        if (a == null) {
            throw new IllegalArgumentException("No ad object specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No response specified");
        }
        if (c == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = new Object();
        this.f = System.currentTimeMillis();
    }
    
    private String a() {
        Object o = this.d;
        synchronized (o) {
            final String string = this.a.toString();
            // monitorexit(o)
            o = string.toCharArray();
            Arrays.sort((char[])o);
            return new String((char[])o) + this.getType() + this.getSize() + this.n();
        }
    }
    
    void a(final aq g) {
        this.g = g;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof aq) {
            final AppLovinAd b = ((aq)o).b();
            if (b != null) {
                o = b;
            }
        }
        while (true) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final q q = (q)o;
            if (this.e != null) {
                if (this.e.equals(q.e)) {
                    return this.a().equals(q.a());
                }
            }
            else if (q.e == null) {
                return this.a().equals(q.a());
            }
            return false;
            continue;
        }
    }
    
    public boolean f() {
        this.c.getLogger().e("AppLovinAdBase", "Attempting to invoke hasVideoUrl() from base ad class");
        return false;
    }
    
    @Override
    public long getAdIdNumber() {
        return bu.a(this.a, "ad_id", -1L, this.c);
    }
    
    @Override
    public String getAdValue(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final JSONObject a = bu.a(this.a, "ad_values", (JSONObject)null, this.c);
            if (a != null && a.length() > 0) {
                return bu.a(a, s, (String)null, this.c);
            }
        }
        return null;
    }
    
    @Override
    public AppLovinAdSize getSize() {
        return AppLovinAdSize.fromString(bu.a(this.b, "ad_size", (String)null, this.c));
    }
    
    @Override
    public AppLovinAdType getType() {
        return AppLovinAdType.fromString(bu.a(this.b, "ad_type", (String)null, this.c));
    }
    
    @Override
    public String getZoneId() {
        if (this.t().m()) {
            return null;
        }
        return bu.a(this.b, "zone_id", (String)null, this.c);
    }
    
    @Override
    public int hashCode() {
        return this.e.hashCode() + this.a().hashCode();
    }
    
    @Override
    public boolean isVideoAd() {
        if (this.a.has("is_video_ad")) {
            return bu.a(this.a, "is_video_ad", false, this.c);
        }
        return this.f();
    }
    
    public long l() {
        return this.f;
    }
    
    public o m() {
        return o.a(bu.a(this.b, "type", o.b.toString(), this.c));
    }
    
    public String n() {
        final String a = bu.a(this.a, "clcode", "", this.c);
        if (AppLovinSdkUtils.isValidString(a)) {
            return a;
        }
        return bu.a(this.b, "clcode", "", this.c);
    }
    
    String o() {
        return bu.a(this.a, "pk", "NA", this.c);
    }
    
    String p() {
        return bu.a(this.a, "sk1", (String)null, this.c);
    }
    
    String q() {
        return bu.a(this.a, "sk2", (String)null, this.c);
    }
    
    long r() {
        return bu.a(this.b, "fetch_ad_latency_millis", -1L, this.c);
    }
    
    long s() {
        return bu.a(this.b, "fetch_ad_response_size", -1L, this.c);
    }
    
    public n t() {
        if (this.e != null) {
            return this.e;
        }
        return this.e = n.a(this.getSize(), this.getType(), this.m(), bu.a(this.b, "zone_id", (String)null, this.c), this.c);
    }
    
    @Override
    public String toString() {
        synchronized (this.d) {
            final String string = this.a.toString();
            // monitorexit(this.d)
            return "[" + this.getClass().getSimpleName() + " #" + this.getAdIdNumber() + " adType=" + this.getType() + ", adSize=" + this.getSize() + ", adObject=" + string + "]";
        }
    }
    
    aq u() {
        return this.g;
    }
}
