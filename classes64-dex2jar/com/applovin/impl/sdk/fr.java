// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import com.applovin.impl.a.a;
import com.applovin.impl.a.f;
import com.applovin.impl.a.o;
import java.util.Set;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.impl.a.k;
import com.applovin.impl.a.l;
import java.util.HashSet;
import com.applovin.impl.a.n;
import com.applovin.impl.a.h;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.impl.a.g;

class fr extends dx
{
    private g a;
    private final AppLovinAdLoadListener b;
    
    fr(final g a, final AppLovinAdLoadListener b, final AppLovinSdkImpl appLovinSdkImpl) {
        super("TaskRenderVastAd", appLovinSdkImpl);
        if (a == null) {
            throw new IllegalArgumentException("No context specified.");
        }
        this.b = b;
        this.a = a;
    }
    
    private void a(final h h, final Throwable t) {
        this.e.e(this.c, "Failed to render valid VAST ad", t);
        n.a(this.a, this.b, h, -6, this.d);
    }
    
    @Override
    public void run() {
        f a = null;
        this.e.d(this.c, "Rendering VAST ad...");
        String s = "";
        String s2 = "";
        final int size = this.a.b().size();
        final HashSet set = new HashSet<l>(size);
        final HashSet set2 = new HashSet<l>(size);
        final Iterator<gf> iterator = this.a.b().iterator();
        o a2 = null;
        k k = null;
        while (iterator.hasNext()) {
            final gf gf = iterator.next();
            String s3;
            if (n.a(gf)) {
                s3 = "Wrapper";
            }
            else {
                s3 = "InLine";
            }
            final gf c = gf.c(s3);
            o o;
            String s4;
            String s5;
            f f2;
            if (c != null) {
                final gf c2 = c.c("AdSystem");
                k a3 = k;
                if (c2 != null) {
                    a3 = com.applovin.impl.a.k.a(c2, k, this.d);
                }
                final String a4 = n.a(c, "AdTitle", s);
                final String a5 = n.a(c, "Description", s2);
                n.a(c.a("Impression"), (Set<l>)set, this.a, this.d);
                n.a(c.a("Error"), (Set<l>)set2, this.a, this.d);
                final gf b = c.b("Creatives");
                f f = a;
                o = a2;
                if (b != null) {
                    final Iterator<gf> iterator2 = b.d().iterator();
                    while (true) {
                        f = a;
                        o = a2;
                        if (!iterator2.hasNext()) {
                            break;
                        }
                        final gf gf2 = iterator2.next();
                        final gf b2 = gf2.b("Linear");
                        if (b2 != null) {
                            a2 = com.applovin.impl.a.o.a(b2, a2, this.a, this.d);
                        }
                        else {
                            final gf c3 = gf2.c("CompanionAds");
                            if (c3 != null) {
                                final gf c4 = c3.c("Companion");
                                if (c4 == null) {
                                    continue;
                                }
                                a = com.applovin.impl.a.f.a(c4, a, this.a, this.d);
                            }
                            else {
                                this.e.e(this.c, "Received and will skip rendering for an unidentified creative: " + gf2);
                            }
                        }
                    }
                }
                final k i = a3;
                s4 = a5;
                s5 = a4;
                f2 = f;
                k = i;
            }
            else {
                this.e.e(this.c, "Did not find wrapper or inline response for node: " + gf);
                final String s6 = s2;
                f2 = a;
                o = a2;
                s4 = s6;
                s5 = s;
            }
            s = s5;
            final String s7 = s4;
            a = f2;
            a2 = o;
            s2 = s7;
        }
        try {
            final a a6 = com.applovin.impl.a.a.an().a(this.d).a(this.a.c()).b(this.a.d()).a(this.a.e()).a(s).b(s2).a(k).a(a2).a(a).a((Set<l>)set).b((Set<l>)set2).a();
            final h a7 = n.a(a6);
            if (a7 == null) {
                this.d.getTaskManager().a(new ep(a6, this.b, this.d));
                return;
            }
            this.a(a7, null);
        }
        catch (Throwable t) {
            this.a(h.c, t);
        }
    }
}
