// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.d;
import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Map;
import com.chartboost.sdk.Libraries.CBUtility;
import java.util.HashMap;
import com.chartboost.sdk.Libraries.c;
import java.util.Locale;
import com.chartboost.sdk.i;
import java.io.Serializable;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Model.CBError;
import java.io.File;
import com.chartboost.sdk.Tracking.a;
import org.json.JSONObject;

public class aj extends ad<JSONObject>
{
    public final JSONObject a;
    public final a k;
    public boolean l;
    protected final ap m;
    private final String n;
    private String o;
    private final com.chartboost.sdk.Tracking.a p;
    
    public aj(final String n, final ap m, final com.chartboost.sdk.Tracking.a p5, final int n2, final a k) {
        super("POST", a(n), n2, null);
        this.l = false;
        this.a = new JSONObject();
        this.n = n;
        this.m = m;
        this.p = p5;
        this.k = k;
    }
    
    public static String a(String s) {
        String s2;
        if (s != null && s.startsWith("/")) {
            s2 = "";
        }
        else {
            s2 = "/";
        }
        if (s == null) {
            s = "";
        }
        return String.format("%s%s%s", "https://live.chartboost.com", s2, s);
    }
    
    private void a(final ag ag, final CBError cbError) {
        final e.a a = com.chartboost.sdk.Libraries.e.a("endpoint", this.e());
        Serializable value;
        if (ag == null) {
            value = "None";
        }
        else {
            value = ag.a;
        }
        final e.a a2 = com.chartboost.sdk.Libraries.e.a("statuscode", value);
        String string;
        if (cbError == null) {
            string = "None";
        }
        else {
            string = cbError.a().toString();
        }
        final e.a a3 = com.chartboost.sdk.Libraries.e.a("error", string);
        String b;
        if (cbError == null) {
            b = "None";
        }
        else {
            b = cbError.b();
        }
        final JSONObject a4 = com.chartboost.sdk.Libraries.e.a(a, a2, a3, com.chartboost.sdk.Libraries.e.a("errorDescription", b), com.chartboost.sdk.Libraries.e.a("retryCount", 0));
        final com.chartboost.sdk.Tracking.a p2 = this.p;
        String s;
        if (cbError == null) {
            s = "success";
        }
        else {
            s = "failure";
        }
        p2.a("request_manager", "request", s, null, null, null, a4);
    }
    
    @Override
    public ae a() {
        this.c();
        final String string = this.a.toString();
        final String k = com.chartboost.sdk.i.k;
        final String b = com.chartboost.sdk.Libraries.c.b(com.chartboost.sdk.Libraries.c.a(String.format(Locale.US, "%s %s\n%s\n%s", this.b, this.d(), com.chartboost.sdk.i.l, string).getBytes()));
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Accept", "application/json");
        hashMap.put("X-Chartboost-Client", CBUtility.b());
        hashMap.put("X-Chartboost-API", "7.3.0");
        hashMap.put("X-Chartboost-App", k);
        hashMap.put("X-Chartboost-Signature", b);
        return new ae(hashMap, string.getBytes(), "application/json");
    }
    
    @Override
    public af<JSONObject> a(final ag ag) {
    Label_0133_Outer:
        while (true) {
            while (true) {
                while (true) {
                    int optInt = 0;
                    Label_0218: {
                        try {
                            if (ag.b == null) {
                                return af.a(new CBError(CBError.a.c, "Response is not a valid json object"));
                            }
                            final JSONObject jsonObject = new JSONObject(new String(ag.b));
                            CBLogging.c("CBRequest", "Request " + this.e() + " succeeded. Response code: " + ag.a + ", body: " + jsonObject.toString(4));
                            if (!this.l) {
                                return af.a(jsonObject);
                            }
                            optInt = jsonObject.optInt("status");
                            if (optInt == 404) {
                                return af.a(new CBError(CBError.a.g, "404 error from server"));
                            }
                            break Label_0218;
                            final String string = "Request failed due to status code " + optInt + " in message";
                            CBLogging.b("CBRequest", string);
                            return (af<JSONObject>)af.a(new CBError(CBError.a.d, string));
                        }
                        catch (Exception ex) {
                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "parseServerResponse", ex);
                            return af.a(new CBError(CBError.a.a, ex.getLocalizedMessage()));
                        }
                    }
                    if (optInt >= 200 && optInt <= 299) {
                        continue Label_0133_Outer;
                    }
                    break;
                }
                continue;
            }
        }
    }
    
    @Override
    public void a(final CBError cbError, final ag ag) {
        if (cbError != null) {
            if (this.k != null) {
                this.k.a(this, cbError);
            }
            if (this.p != null) {
                this.a(ag, cbError);
            }
        }
    }
    
    public void a(final String s, final Object o) {
        com.chartboost.sdk.Libraries.e.a(this.a, s, o);
    }
    
    @Override
    public void a(final JSONObject jsonObject, final ag ag) {
        if (this.k != null && jsonObject != null) {
            this.k.a(this, jsonObject);
        }
        if (this.p != null) {
            this.a(ag, null);
        }
    }
    
    public void b(final String o) {
        this.o = o;
    }
    
    protected void c() {
        boolean b = true;
        this.a("app", this.m.s);
        this.a("model", this.m.f);
        this.a("device_type", this.m.t);
        this.a("actual_device_type", this.m.u);
        this.a("os", this.m.g);
        this.a("country", this.m.h);
        this.a("language", this.m.i);
        this.a("sdk", this.m.l);
        this.a("user_agent", com.chartboost.sdk.i.w);
        this.a("timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.m.e.a())));
        this.a("session", this.m.d.getInt("cbPrefSessionCount", 0));
        this.a("reachability", this.m.b.a());
        this.a("scale", this.m.r);
        this.a("is_portrait", CBUtility.a(CBUtility.a()));
        this.a("bundle", this.m.j);
        this.a("bundle_id", this.m.k);
        this.a("carrier", this.m.v);
        this.a("custom_id", com.chartboost.sdk.i.a);
        this.a("mediation", com.chartboost.sdk.i.h);
        if (com.chartboost.sdk.i.d != null) {
            this.a("framework_version", com.chartboost.sdk.i.f);
            this.a("wrapper_version", com.chartboost.sdk.i.b);
        }
        this.a("rooted_device", this.m.w);
        this.a("timezone", this.m.x);
        this.a("mobile_network", this.m.y);
        this.a("dw", this.m.o);
        this.a("dh", this.m.p);
        this.a("dpi", this.m.q);
        this.a("w", this.m.m);
        this.a("h", this.m.n);
        this.a("commit_hash", "d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
        final d.a a = this.m.a.a();
        this.a("identity", a.b);
        if (a.a != -1) {
            if (a.a != 1) {
                b = false;
            }
            this.a("limit_ad_tracking", b);
        }
        this.a("pidatauseconsent", com.chartboost.sdk.i.x.getValue());
        final String a2 = this.m.c.get().a;
        if (!s.a().a(a2)) {
            this.a("config_variant", a2);
        }
        this.a("certification_providers", com.chartboost.sdk.impl.o.e());
    }
    
    public String d() {
        return this.e();
    }
    
    public String e() {
        if (this.n == null) {
            return "/";
        }
        final StringBuilder sb = new StringBuilder();
        String s;
        if (this.n.startsWith("/")) {
            s = "";
        }
        else {
            s = "/";
        }
        return sb.append(s).append(this.n).toString();
    }
    
    public interface a
    {
        void a(final aj p0, final CBError p1);
        
        void a(final aj p0, final JSONObject p1);
    }
}
