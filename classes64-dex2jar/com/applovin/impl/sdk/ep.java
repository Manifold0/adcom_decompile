// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.impl.a.r;
import java.util.List;
import android.net.Uri;
import com.applovin.impl.a.i;
import com.applovin.impl.a.f;
import com.applovin.impl.a.j;
import com.applovin.sdk.AppLovinSdkUtils;
import android.webkit.URLUtil;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.a.a;

class ep extends ej
{
    private final a a;
    
    public ep(final a a, final AppLovinAdLoadListener appLovinAdLoadListener, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheVastAd", a, appLovinAdLoadListener, appLovinSdkImpl);
        this.a = a;
    }
    
    private void d() {
        if (this.a.a(this.d)) {
            final f e = this.a.e();
            if (e == null) {
                this.e.d(this.c, "No companion ad provided. Skipping...");
                return;
            }
            final i b = e.b();
            if (b == null) {
                this.e.e(this.c, "Failed to retrieve non-video resources from companion ad. Skipping...");
                return;
            }
        Label_0143_Outer:
            while (true) {
                Label_0408: {
                    String string = null;
                    String c = null;
                Label_0196:
                    while (true) {
                    Label_0478:
                        while (true) {
                            Label_0472: {
                                try {
                                    final Uri b2 = b.b();
                                    if (b2 == null) {
                                        break Label_0472;
                                    }
                                    string = b2.toString();
                                    c = b.c();
                                    if (!URLUtil.isValidUrl(string) && !AppLovinSdkUtils.isValidString(c)) {
                                        break Label_0408;
                                    }
                                    if (b.a() == com.applovin.impl.a.j.b) {
                                        this.e.d(this.c, "Caching static companion ad at " + string + "...");
                                        final List<String> h = this.a.h();
                                        if (h == null || h.isEmpty()) {
                                            break Label_0478;
                                        }
                                        final boolean b3 = true;
                                        final Uri b4 = this.b(string, h, b3);
                                        if (b4 != null) {
                                            b.a(b4);
                                            return;
                                        }
                                        this.e.e(this.c, "Failed to cache static companion ad");
                                        return;
                                    }
                                }
                                catch (Throwable t) {
                                    this.e.e(this.c, "Failed to cache companion ad", t);
                                    return;
                                }
                                break Label_0196;
                            }
                            string = "";
                            continue Label_0143_Outer;
                        }
                        final boolean b3 = false;
                        continue;
                    }
                    if (b.a() == com.applovin.impl.a.j.d) {
                        if (!AppLovinSdkUtils.isValidString(string)) {
                            this.e.d(this.c, "Caching provided HTML for companion ad. No fetch required. HTML: " + c);
                            b.a(this.b(c, this.a.h()));
                            return;
                        }
                        this.e.d(this.c, "Begin caching HTML companion ad. Fetching from " + string + "...");
                        final String c2 = this.c(string);
                        if (AppLovinSdkUtils.isValidString(c2)) {
                            this.e.d(this.c, "HTML fetched. Caching HTML now...");
                            b.a(this.b(c2, this.a.h()));
                            return;
                        }
                        this.e.e(this.c, "Unable to load companion ad resources from " + string);
                        return;
                    }
                    else {
                        if (b.a() == com.applovin.impl.a.j.c) {
                            this.e.d(this.c, "Skip caching of iFrame resource...");
                            return;
                        }
                        break;
                    }
                }
                this.e.w(this.c, "Companion ad does not have any resources attached. Skipping...");
            }
        }
        else {
            this.e.d(this.c, "Companion ad caching disabled. Skipping...");
        }
    }
    
    private void e() {
        if (this.a.b(this.d)) {
            if (this.a.a() != null) {
                final r c = this.a.c();
                if (c != null) {
                    final Uri b = c.b();
                    if (b != null) {
                        final List<String> h = this.a.h();
                        final Uri a = this.a(b.toString(), h, h != null && !h.isEmpty());
                        if (a == null) {
                            this.e.e(this.c, "Failed to cache video file: " + c);
                            return;
                        }
                        this.e.d(this.c, "Video file successfully cached into: " + a);
                        c.a(a);
                    }
                }
            }
            return;
        }
        this.e.d(this.c, "Video caching disabled. Skipping...");
    }
    
    private void f() {
        String s;
        if (this.a.k() != null) {
            this.e.d(this.c, "Begin caching HTML template. Fetching from " + this.a.k() + "...");
            s = this.a(this.a.k().toString(), this.a.O());
        }
        else {
            s = this.a.j();
        }
        if (AppLovinSdkUtils.isValidString(s)) {
            this.a.c(this.b(s, this.a.O()));
            this.e.d(this.c, "Finish caching HTML template " + this.a.j() + " for ad #" + this.a.getAdIdNumber());
            return;
        }
        this.e.d(this.c, "Unable to load HTML template");
    }
    
    @Override
    public void run() {
        this.e.d(this.c, "Begin caching for VAST ad #" + this.a.getAdIdNumber() + "...");
        this.b();
        this.d();
        this.e();
        this.f();
        this.c();
        this.e.d(this.c, "Finished caching VAST ad #" + this.a.getAdIdNumber());
        final long currentTimeMillis = System.currentTimeMillis();
        final long l = this.a.l();
        com.applovin.impl.sdk.g.a(this.a, this.d);
        com.applovin.impl.sdk.g.a(currentTimeMillis - l, this.a, this.d);
        this.a(this.a);
    }
}
