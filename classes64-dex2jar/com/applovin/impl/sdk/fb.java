// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinLogger;
import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinSdkUtils;
import android.preference.PreferenceManager;
import java.util.LinkedHashSet;
import java.util.Iterator;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;

class fb extends dx
{
    fb(final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskInitializeSdk", appLovinSdkImpl);
        this.g = true;
    }
    
    private void a(final ec<String> ec, final o o) {
        final String s = this.d.get(ec);
        if (s.length() > 0) {
            final Iterator<String> iterator = aa.a(s).iterator();
            while (iterator.hasNext()) {
                final AppLovinAdSize fromString = AppLovinAdSize.fromString(iterator.next());
                if (fromString != null) {
                    this.d.c().g(n.a(fromString, AppLovinAdType.REGULAR, o, this.d));
                    if (!AppLovinAdSize.INTERSTITIAL.getLabel().equals(fromString.getLabel())) {
                        continue;
                    }
                    ec<Boolean> ec2;
                    if (o == o.b) {
                        ec2 = ea.P;
                    }
                    else {
                        ec2 = ea.Q;
                    }
                    this.b(ec2, o);
                }
            }
        }
    }
    
    private void b(final ec<Boolean> ec, final o o) {
        if (this.d.get(ec)) {
            this.d.c().g(n.a(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, o, this.d));
        }
    }
    
    private boolean b() {
        if (!ah.a("android.permission.INTERNET", this.f)) {
            this.e.userError(this.a(), "Unable to enable AppLovin SDK: no android.permission.INTERNET");
            return false;
        }
        return true;
    }
    
    private void c() {
        this.d.getTaskManager().a(new eh(this.d), fe.a, 500L);
    }
    
    private void d() {
        this.e();
        this.a(ea.N, o.b);
        this.a(ea.O, o.c);
        this.f();
    }
    
    private void e() {
        final LinkedHashSet<n> b = this.d.getZoneManager().b();
        if (!b.isEmpty()) {
            this.e.d(this.a(), "Scheduling preload(s) for " + b.size() + " zone(s)");
            for (final n n : b) {
                if (n.e()) {
                    this.d.getNativeAdService().a(n);
                }
                else {
                    this.d.getAdService().preloadAds(n);
                }
            }
        }
    }
    
    private void f() {
        if (this.d.get(ea.bt)) {
            this.d.d().g(n.j(this.d));
        }
    }
    
    @Override
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        this.e.d(this.a(), "Initializing AppLovin SDK 8.0.1...");
        try {
        Label_0368_Outer:
            while (true) {
                while (true) {
                Label_0414:
                    while (true) {
                        try {
                            if (this.b()) {
                                final aw a = this.d.a();
                                a.c();
                                a.c("ad_imp_session");
                                com.applovin.impl.sdk.a.b(this.d);
                                this.d.getFileManager().d(this.f);
                                this.d.getFileManager().c(this.f);
                                this.d.getMediationService().a();
                                this.d();
                                this.d.b().a();
                                this.d.i();
                                this.c();
                                final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f);
                                if (!AppLovinSdkUtils.isValidString(this.d.get(ef.a, null, defaultSharedPreferences))) {
                                    this.d.put(ef.a, Boolean.toString(true), defaultSharedPreferences);
                                }
                                this.d.a(true);
                                this.d.getPersistentPostbackManager().a();
                                this.d.getEventService().trackEvent("landing");
                            }
                            else {
                                this.d.a(false);
                                this.e.userError(this.a(), "Couldn't initialize the AppLovin SDK due to missing INTERNET permission");
                            }
                            final AppLovinLogger e = this.e;
                            final String a2 = this.a();
                            final StringBuilder append = new StringBuilder().append("AppLovin SDK 8.0.1 initialization ");
                            if (this.d.isEnabled()) {
                                final String s = "succeeded";
                                e.d(a2, append.append(s).append(" in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms").toString());
                                return;
                            }
                        }
                        catch (Throwable t) {
                            this.e.e(this.a(), "Unable to initialize SDK. Exception occurred: %@", t);
                            this.d.a(false);
                            final AppLovinLogger e2 = this.e;
                            final String a3 = this.a();
                            final StringBuilder append2 = new StringBuilder().append("AppLovin SDK 8.0.1 initialization ");
                            if (this.d.isEnabled()) {
                                final String s2 = "succeeded";
                                e2.d(a3, append2.append(s2).append(" in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms").toString());
                                return;
                            }
                            break Label_0414;
                        }
                        final String s = "failed";
                        continue Label_0368_Outer;
                    }
                    final String s2 = "failed";
                    continue;
                }
            }
        }
        finally {
            final AppLovinLogger e3 = this.e;
            final String a4 = this.a();
            final StringBuilder append3 = new StringBuilder().append("AppLovin SDK 8.0.1 initialization ");
            String s3;
            if (this.d.isEnabled()) {
                s3 = "succeeded";
            }
            else {
                s3 = "failed";
            }
            while (true) {
                e3.d(a4, append3.append(s3).append(" in ").append(System.currentTimeMillis() - currentTimeMillis).append("ms").toString());
                continue;
            }
        }
    }
}
