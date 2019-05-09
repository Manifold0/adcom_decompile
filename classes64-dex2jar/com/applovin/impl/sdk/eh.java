// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Map;
import org.json.JSONException;
import android.content.SharedPreferences;
import java.util.Locale;
import android.preference.PreferenceManager;
import java.util.List;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;

class eh extends dx
{
    eh(final AppLovinSdkImpl appLovinSdkImpl) {
        super("SubmitData", appLovinSdkImpl);
    }
    
    private void a(JSONObject a) {
        try {
            a = ag.a(a);
            final ed settingsManager = this.d.getSettingsManager();
            settingsManager.a(ea.f, a.getString("device_id"));
            settingsManager.a(ea.h, a.getString("device_token"));
            settingsManager.a(ea.g, a.getString("publisher_id"));
            settingsManager.a();
            ag.a(a, this.d);
            this.d.i();
            ag.b(a, this.d);
            if (a.has("adserver_parameters")) {
                settingsManager.a(ea.B, a.getJSONObject("adserver_parameters").toString());
            }
            final String a2 = bu.a(a, "latest_version", "", this.d);
            if (this.a(a2)) {
                this.d.getLogger().userError("ALSdk", "Please integrate the latest version of the AppLovin SDK (" + a2 + "). Not doing so can negatively impact your eCPMs!");
            }
        }
        catch (Throwable t) {
            this.e.e(this.c, "Unable to parse API response", t);
        }
    }
    
    private boolean a(final String s) {
        try {
            if (AppLovinSdkUtils.isValidString(s) && !"8.0.1".equals(s)) {
                final List<String> a = aa.a(s, "\\.");
                final List<String> a2 = aa.a("8.0.1", "\\.");
                if (a.size() == 3 && a2.size() == 3) {
                    for (int i = 0; i < 3; ++i) {
                        final int int1 = Integer.parseInt(a2.get(i));
                        final int int2 = Integer.parseInt(a.get(i));
                        if (int1 < int2) {
                            return true;
                        }
                        if (int1 > int2) {
                            return false;
                        }
                    }
                    return false;
                }
            }
        }
        catch (Throwable t) {
            this.d.getLogger().e(this.c, "Encountered exception while checking if current version is outdated", t);
        }
        return false;
    }
    
    private void b(final JSONObject jsonObject) throws JSONException {
        final ah dataCollector = this.d.getDataCollector();
        final ak d = dataCollector.d();
        final am a = dataCollector.a();
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("model", (Object)a.a);
        jsonObject2.put("os", (Object)a.b);
        jsonObject2.put("brand", (Object)a.d);
        jsonObject2.put("brand_name", (Object)a.e);
        jsonObject2.put("hardware", (Object)a.f);
        jsonObject2.put("sdk_version", a.h);
        jsonObject2.put("revision", (Object)a.g);
        jsonObject2.put("adns", (double)a.m);
        jsonObject2.put("adnsd", a.n);
        jsonObject2.put("gy", a.v);
        jsonObject2.put("country_code", (Object)a.i);
        jsonObject2.put("carrier", (Object)a.j);
        jsonObject2.put("orientation_lock", (Object)a.l);
        jsonObject2.put("tz_offset", a.o);
        String s;
        if (a.q) {
            s = "1";
        }
        else {
            s = "0";
        }
        jsonObject2.put("adr", (Object)s);
        jsonObject2.put("wvvc", a.p);
        jsonObject2.put("volume", a.s);
        jsonObject2.put("type", (Object)"android");
        String s2;
        if (a.u) {
            s2 = "1";
        }
        else {
            s2 = "0";
        }
        jsonObject2.put("sim", (Object)s2);
        this.e(jsonObject2);
        final Boolean w = a.w;
        if (w != null) {
            jsonObject2.put("huc", (Object)w.toString());
        }
        final Boolean x = a.x;
        if (x != null) {
            jsonObject2.put("aru", (Object)x.toString());
        }
        final al r = a.r;
        if (r != null) {
            jsonObject2.put("act", r.a);
            jsonObject2.put("acm", r.b);
        }
        final String t = a.t;
        if (AppLovinSdkUtils.isValidString(t)) {
            jsonObject2.put("ua", (Object)gd.c(t));
        }
        final Locale k = a.k;
        if (k != null) {
            jsonObject2.put("locale", (Object)gd.c(k.toString()));
        }
        jsonObject.put("device_info", (Object)jsonObject2);
        final JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("package_name", (Object)d.c);
        jsonObject3.put("installer_name", (Object)d.d);
        jsonObject3.put("app_name", (Object)d.a);
        jsonObject3.put("app_version", (Object)d.b);
        jsonObject3.put("installed_at", d.e);
        jsonObject3.put("applovin_sdk_version", (Object)"8.0.1");
        jsonObject3.put("ic", this.d.isInitializedInMainActivity());
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f);
        final String s3 = this.d.get(ef.a, null, defaultSharedPreferences);
        if (AppLovinSdkUtils.isValidString(s3)) {
            jsonObject3.put("first_install", (Object)s3);
            if (s3.equalsIgnoreCase(Boolean.toString(true))) {
                this.d.put(ef.a, Boolean.toString(false), defaultSharedPreferences);
            }
        }
        final String s4 = this.d.get(ea.I);
        if (s4 != null && s4.length() > 0) {
            jsonObject3.put("plugin_version", (Object)s4);
        }
        jsonObject.put("app_info", (Object)jsonObject3);
    }
    
    private void c(final JSONObject jsonObject) throws JSONException {
        if (this.d.get(ea.R)) {
            jsonObject.put("stats", (Object)this.d.a().b());
        }
    }
    
    private void d(final JSONObject jsonObject) {
        final ei ei = new ei(this, "POST", new JSONObject(), "Repeat" + this.c, this.d);
        ei.a(ag.a("device", null, this.d));
        ei.b(ag.c("device", null, this.d));
        ei.a(jsonObject);
        ei.c(this.d.get(ea.i));
        ei.a(ea.m);
        ei.b(ea.q);
        ei.run();
    }
    
    private void e(final JSONObject jsonObject) {
        try {
            final aj e = this.d.getDataCollector().e();
            final String b = e.b;
            if (AppLovinSdkUtils.isValidString(b)) {
                jsonObject.put("idfa", (Object)b);
            }
            jsonObject.put("dnt", (Object)Boolean.toString(e.a));
        }
        catch (Throwable t) {
            this.e.e(this.a(), "Failed to populate advertising info", t);
        }
    }
    
    @Override
    public void run() {
        try {
            this.e.i(this.c, "Submitting user data...");
            final JSONObject jsonObject = new JSONObject();
            this.b(jsonObject);
            this.c(jsonObject);
            jsonObject.put("sc", (Object)this.d.get(ea.A));
            this.d(jsonObject);
        }
        catch (JSONException ex) {
            this.e.e(this.c, "Unable to build JSON message with collected data", (Throwable)ex);
        }
    }
}
