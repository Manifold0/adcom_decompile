package com.applovin.impl.sdk;

import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1233f;
import com.applovin.impl.p016a.C1234g;
import com.applovin.impl.p016a.C1235h;
import com.applovin.impl.p016a.C1238k;
import com.applovin.impl.p016a.C1241n;
import com.applovin.impl.p016a.C1242o;
import com.applovin.sdk.AppLovinAdLoadListener;
import java.util.HashSet;
import java.util.Set;

class fr extends dx {
    /* renamed from: a */
    private C1234g f2532a;
    /* renamed from: b */
    private final AppLovinAdLoadListener f2533b;

    fr(C1234g c1234g, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderVastAd", appLovinSdkImpl);
        if (c1234g == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.f2533b = appLovinAdLoadListener;
        this.f2532a = c1234g;
    }

    /* renamed from: a */
    private void m2881a(C1235h c1235h, Throwable th) {
        this.e.mo4174e(this.c, "Failed to render valid VAST ad", th);
        C1241n.m1910a(this.f2532a, this.f2533b, c1235h, -6, this.d);
    }

    public void run() {
        C1233f c1233f = null;
        this.e.mo4172d(this.c, "Rendering VAST ad...");
        String str = "";
        String str2 = "";
        int size = this.f2532a.m1881b().size();
        Set hashSet = new HashSet(size);
        Set hashSet2 = new HashSet(size);
        C1242o c1242o = null;
        C1238k c1238k = null;
        for (gf gfVar : this.f2532a.m1881b()) {
            gf gfVar2;
            C1238k c1238k2;
            String str3;
            gf c = gfVar2.m2976c(C1241n.m1916a(gfVar2) ? "Wrapper" : "InLine");
            if (c != null) {
                gfVar2 = c.m2976c("AdSystem");
                if (gfVar2 != null) {
                    c1238k = C1238k.m1896a(gfVar2, c1238k, this.d);
                }
                str = C1241n.m1907a(c, "AdTitle", str);
                str2 = C1241n.m1907a(c, "Description", str2);
                C1241n.m1912a(c.m2973a("Impression"), hashSet, this.f2532a, this.d);
                C1241n.m1912a(c.m2973a("Error"), hashSet2, this.f2532a, this.d);
                gfVar2 = c.m2974b("Creatives");
                if (gfVar2 != null) {
                    for (gf gfVar22 : gfVar22.m2978d()) {
                        gf b = gfVar22.m2974b("Linear");
                        if (b != null) {
                            c1242o = C1242o.m1922a(b, c1242o, this.f2532a, this.d);
                        } else {
                            b = gfVar22.m2976c("CompanionAds");
                            if (b != null) {
                                gfVar22 = b.m2976c("Companion");
                                if (gfVar22 != null) {
                                    c1233f = C1233f.m1875a(gfVar22, c1233f, this.f2532a, this.d);
                                }
                            } else {
                                this.e.mo4173e(this.c, "Received and will skip rendering for an unidentified creative: " + gfVar22);
                            }
                        }
                    }
                }
                c1238k2 = c1238k;
                str3 = str2;
                str2 = str;
            } else {
                this.e.mo4173e(this.c, "Did not find wrapper or inline response for node: " + gfVar22);
                c1238k2 = c1238k;
                str3 = str2;
                str2 = str;
            }
            str = str2;
            str2 = str3;
            c1238k = c1238k2;
        }
        try {
            C1228a a = C1228a.an().m1868a(this.d).m1871a(this.f2532a.m1882c()).m1874b(this.f2532a.m1883d()).m1864a(this.f2532a.m1884e()).m1869a(str).m1872b(str2).m1866a(c1238k).m1867a(c1242o).m1865a(c1233f).m1870a(hashSet).m1873b(hashSet2).m1863a();
            C1235h a2 = C1241n.m1903a(a);
            if (a2 == null) {
                this.d.getTaskManager().m2854a(new ep(a, this.f2533b, this.d));
            } else {
                m2881a(a2, null);
            }
        } catch (Throwable th) {
            m2881a(C1235h.GENERAL_WRAPPER_ERROR, th);
        }
    }
}
