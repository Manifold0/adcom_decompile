package com.applovin.impl.sdk;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1233f;
import com.applovin.impl.p016a.C1236i;
import com.applovin.impl.p016a.C1237j;
import com.applovin.impl.p016a.C1245r;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;

class ep extends ej {
    /* renamed from: a */
    private final C1228a f2472a;

    public ep(C1228a c1228a, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskCacheVastAd", c1228a, appLovinAdLoadListener, appLovinSdkImpl);
        this.f2472a = c1228a;
    }

    /* renamed from: d */
    private void m2786d() {
        if (this.f2472a.m1838a(this.d)) {
            C1233f e = this.f2472a.m1844e();
            if (e != null) {
                C1236i b = e.m1877b();
                if (b != null) {
                    try {
                        Uri b2 = b.m1894b();
                        String uri = b2 != null ? b2.toString() : "";
                        String c = b.m1895c();
                        if (!URLUtil.isValidUrl(uri) && !AppLovinSdkUtils.isValidString(c)) {
                            this.e.mo4178w(this.c, "Companion ad does not have any resources attached. Skipping...");
                            return;
                        } else if (b.m1891a() == C1237j.STATIC) {
                            this.e.mo4172d(this.c, "Caching static companion ad at " + uri + "...");
                            List h = this.f2472a.m1847h();
                            boolean z = (h == null || h.isEmpty()) ? false : true;
                            b2 = m2760b(uri, h, z);
                            if (b2 != null) {
                                b.m1892a(b2);
                                return;
                            } else {
                                this.e.mo4173e(this.c, "Failed to cache static companion ad");
                                return;
                            }
                        } else if (b.m1891a() == C1237j.HTML) {
                            if (AppLovinSdkUtils.isValidString(uri)) {
                                this.e.mo4172d(this.c, "Begin caching HTML companion ad. Fetching from " + uri + "...");
                                c = m2763c(uri);
                                if (AppLovinSdkUtils.isValidString(c)) {
                                    this.e.mo4172d(this.c, "HTML fetched. Caching HTML now...");
                                    b.m1893a(m2761b(c, this.f2472a.m1847h()));
                                    return;
                                }
                                this.e.mo4173e(this.c, "Unable to load companion ad resources from " + uri);
                                return;
                            }
                            this.e.mo4172d(this.c, "Caching provided HTML for companion ad. No fetch required. HTML: " + c);
                            b.m1893a(m2761b(c, this.f2472a.m1847h()));
                            return;
                        } else if (b.m1891a() == C1237j.IFRAME) {
                            this.e.mo4172d(this.c, "Skip caching of iFrame resource...");
                            return;
                        } else {
                            return;
                        }
                    } catch (Throwable th) {
                        this.e.mo4174e(this.c, "Failed to cache companion ad", th);
                        return;
                    }
                }
                this.e.mo4173e(this.c, "Failed to retrieve non-video resources from companion ad. Skipping...");
                return;
            }
            this.e.mo4172d(this.c, "No companion ad provided. Skipping...");
            return;
        }
        this.e.mo4172d(this.c, "Companion ad caching disabled. Skipping...");
    }

    /* renamed from: e */
    private void m2787e() {
        if (!this.f2472a.m1840b(this.d)) {
            this.e.mo4172d(this.c, "Video caching disabled. Skipping...");
        } else if (this.f2472a.mo4000a() != null) {
            C1245r c = this.f2472a.m1841c();
            if (c != null) {
                Uri b = c.m1936b();
                if (b != null) {
                    List h = this.f2472a.m1847h();
                    boolean z = (h == null || h.isEmpty()) ? false : true;
                    Uri a = m2756a(b.toString(), h, z);
                    if (a != null) {
                        this.e.mo4172d(this.c, "Video file successfully cached into: " + a);
                        c.m1935a(a);
                        return;
                    }
                    this.e.mo4173e(this.c, "Failed to cache video file: " + c);
                }
            }
        }
    }

    /* renamed from: f */
    private void m2788f() {
        String a;
        if (this.f2472a.m1850k() != null) {
            this.e.mo4172d(this.c, "Begin caching HTML template. Fetching from " + this.f2472a.m1850k() + "...");
            a = m2757a(this.f2472a.m1850k().toString(), this.f2472a.m1802O());
        } else {
            a = this.f2472a.m1849j();
        }
        if (AppLovinSdkUtils.isValidString(a)) {
            this.f2472a.m1842c(m2761b(a, this.f2472a.m1802O()));
            this.e.mo4172d(this.c, "Finish caching HTML template " + this.f2472a.m1849j() + " for ad #" + this.f2472a.getAdIdNumber());
            return;
        }
        this.e.mo4172d(this.c, "Unable to load HTML template");
    }

    public void run() {
        this.e.mo4172d(this.c, "Begin caching for VAST ad #" + this.f2472a.getAdIdNumber() + "...");
        m2762b();
        m2786d();
        m2787e();
        m2788f();
        m2765c();
        this.e.mo4172d(this.c, "Finished caching VAST ad #" + this.f2472a.getAdIdNumber());
        long currentTimeMillis = System.currentTimeMillis() - this.f2472a.mo3994l();
        C1280g.m2907a(this.f2472a, this.d);
        C1280g.m2905a(currentTimeMillis, this.f2472a, this.d);
        m2758a(this.f2472a);
    }
}
