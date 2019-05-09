// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.Libraries.CBUtility;
import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.i;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Tracking.a;
import org.json.JSONObject;

public final class am extends aj
{
    private final JSONObject n;
    private final JSONObject o;
    private final JSONObject p;
    private final JSONObject q;
    
    public am(final String s, final ap ap, final com.chartboost.sdk.Tracking.a a, final int n, final a a2) {
        super(s, ap, a, n, a2);
        this.n = new JSONObject();
        this.o = new JSONObject();
        this.p = new JSONObject();
        this.q = new JSONObject();
    }
    
    public void a(final String s, final Object o, final int n) {
        if (n == 0) {
            com.chartboost.sdk.Libraries.e.a(this.q, s, o);
            this.a("ad", this.q);
        }
    }
    
    @Override
    protected void c() {
        boolean b = true;
        com.chartboost.sdk.Libraries.e.a(this.o, "app", this.m.s);
        com.chartboost.sdk.Libraries.e.a(this.o, "bundle", this.m.j);
        com.chartboost.sdk.Libraries.e.a(this.o, "bundle_id", this.m.k);
        com.chartboost.sdk.Libraries.e.a(this.o, "custom_id", com.chartboost.sdk.i.a);
        com.chartboost.sdk.Libraries.e.a(this.o, "session_id", "");
        com.chartboost.sdk.Libraries.e.a(this.o, "ui", -1);
        com.chartboost.sdk.Libraries.e.a(this.o, "test_mode", false);
        com.chartboost.sdk.Libraries.e.a(this.o, "certification_providers", com.chartboost.sdk.impl.o.f());
        this.a("app", this.o);
        com.chartboost.sdk.Libraries.e.a(this.p, "carrier", com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("carrier_name", this.m.v.optString("carrier-name")), com.chartboost.sdk.Libraries.e.a("mobile_country_code", this.m.v.optString("mobile-country-code")), com.chartboost.sdk.Libraries.e.a("mobile_network_code", this.m.v.optString("mobile-network-code")), com.chartboost.sdk.Libraries.e.a("iso_country_code", this.m.v.optString("iso-country-code")), com.chartboost.sdk.Libraries.e.a("phone_type", this.m.v.optInt("phone-type"))));
        com.chartboost.sdk.Libraries.e.a(this.p, "model", this.m.f);
        com.chartboost.sdk.Libraries.e.a(this.p, "device_type", this.m.t);
        com.chartboost.sdk.Libraries.e.a(this.p, "actual_device_type", this.m.u);
        com.chartboost.sdk.Libraries.e.a(this.p, "os", this.m.g);
        com.chartboost.sdk.Libraries.e.a(this.p, "country", this.m.h);
        com.chartboost.sdk.Libraries.e.a(this.p, "language", this.m.i);
        com.chartboost.sdk.Libraries.e.a(this.p, "timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.m.e.a())));
        com.chartboost.sdk.Libraries.e.a(this.p, "reachability", this.m.b.a());
        com.chartboost.sdk.Libraries.e.a(this.p, "scale", this.m.r);
        com.chartboost.sdk.Libraries.e.a(this.p, "is_portrait", CBUtility.a(CBUtility.a()));
        com.chartboost.sdk.Libraries.e.a(this.p, "rooted_device", this.m.w);
        com.chartboost.sdk.Libraries.e.a(this.p, "timezone", this.m.x);
        com.chartboost.sdk.Libraries.e.a(this.p, "mobile_network", this.m.y);
        com.chartboost.sdk.Libraries.e.a(this.p, "dw", this.m.o);
        com.chartboost.sdk.Libraries.e.a(this.p, "dh", this.m.p);
        com.chartboost.sdk.Libraries.e.a(this.p, "dpi", this.m.q);
        com.chartboost.sdk.Libraries.e.a(this.p, "w", this.m.m);
        com.chartboost.sdk.Libraries.e.a(this.p, "h", this.m.n);
        com.chartboost.sdk.Libraries.e.a(this.p, "user_agent", com.chartboost.sdk.i.w);
        com.chartboost.sdk.Libraries.e.a(this.p, "device_family", "");
        com.chartboost.sdk.Libraries.e.a(this.p, "retina", false);
        final d.a a = this.m.a.a();
        com.chartboost.sdk.Libraries.e.a(this.p, "identity", a.b);
        if (a.a != -1) {
            if (a.a != 1) {
                b = false;
            }
            com.chartboost.sdk.Libraries.e.a(this.p, "limit_ad_tracking", b);
        }
        com.chartboost.sdk.Libraries.e.a(this.p, "pidatauseconsent", com.chartboost.sdk.i.x.getValue());
        this.a("device", this.p);
        com.chartboost.sdk.Libraries.e.a(this.n, "framework", "");
        com.chartboost.sdk.Libraries.e.a(this.n, "sdk", this.m.l);
        if (com.chartboost.sdk.i.d != null) {
            com.chartboost.sdk.Libraries.e.a(this.n, "framework_version", com.chartboost.sdk.i.f);
            com.chartboost.sdk.Libraries.e.a(this.n, "wrapper_version", com.chartboost.sdk.i.b);
        }
        com.chartboost.sdk.Libraries.e.a(this.n, "mediation", com.chartboost.sdk.i.h);
        com.chartboost.sdk.Libraries.e.a(this.n, "commit_hash", "d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
        final String a2 = this.m.c.get().a;
        if (!s.a().a(a2)) {
            com.chartboost.sdk.Libraries.e.a(this.n, "config_variant", a2);
        }
        this.a("sdk", this.n);
        com.chartboost.sdk.Libraries.e.a(this.q, "session", this.m.d.getInt("cbPrefSessionCount", 0));
        if (this.q.isNull("cache")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "cache", false);
        }
        if (this.q.isNull("amount")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "amount", 0);
        }
        if (this.q.isNull("retry_count")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "retry_count", 0);
        }
        if (this.q.isNull("location")) {
            com.chartboost.sdk.Libraries.e.a(this.q, "location", "");
        }
        this.a("ad", this.q);
    }
}
