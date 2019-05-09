package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinLogger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

abstract class ds implements at, AppLovinNativeAdLoadListener {
    /* renamed from: a */
    protected final AppLovinSdkImpl f2309a;
    /* renamed from: b */
    protected final AppLovinLogger f2310b;
    /* renamed from: c */
    protected final Object f2311c = new Object();
    /* renamed from: d */
    protected final Map<C1287n, dt> f2312d = new HashMap();
    /* renamed from: e */
    protected final Map<C1287n, dt> f2313e = new HashMap();
    /* renamed from: f */
    protected final Map<C1287n, Object> f2314f = new HashMap();
    /* renamed from: g */
    protected final Set<C1287n> f2315g = new HashSet();

    ds(AppLovinSdkImpl appLovinSdkImpl) {
        this.f2309a = appLovinSdkImpl;
        this.f2310b = appLovinSdkImpl.getLogger();
        mo4132a();
    }

    /* renamed from: l */
    private dt m2530l(C1287n c1287n) {
        return (dt) this.f2312d.get(c1287n);
    }

    /* renamed from: m */
    private dt m2531m(C1287n c1287n) {
        return (dt) this.f2313e.get(c1287n);
    }

    /* renamed from: n */
    private dt m2532n(C1287n c1287n) {
        dt m;
        synchronized (this.f2311c) {
            m = m2531m(c1287n);
            if (m == null || m.m2619a() <= 0) {
                m = m2530l(c1287n);
            }
        }
        return m;
    }

    /* renamed from: a */
    abstract dx mo4130a(C1287n c1287n);

    /* renamed from: a */
    abstract C1287n mo4131a(cj cjVar);

    /* renamed from: a */
    abstract void mo4132a();

    /* renamed from: a */
    abstract void mo4134a(Object obj, cj cjVar);

    /* renamed from: a */
    abstract void mo4135a(Object obj, C1287n c1287n, int i);

    /* renamed from: a */
    public void mo4136a(LinkedHashSet<C1287n> linkedHashSet) {
        if (this.f2314f != null && !this.f2314f.isEmpty()) {
            synchronized (this.f2311c) {
                Iterator it = this.f2314f.keySet().iterator();
                while (it.hasNext()) {
                    C1287n c1287n = (C1287n) it.next();
                    if (!(c1287n.m3064m() || linkedHashSet.contains(c1287n))) {
                        Object obj = this.f2314f.get(c1287n);
                        it.remove();
                        this.f2310b.userError("AppLovinAdService", "Failed to load ad for zone (" + c1287n.m3051a() + "). Please check that the zone has been added to your AppLovin account.");
                        mo4135a(obj, c1287n, -7);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public boolean mo4137a(C1287n c1287n, Object obj) {
        boolean z;
        synchronized (this.f2311c) {
            if (m2554k(c1287n)) {
                z = false;
            } else {
                mo4139b(c1287n, obj);
                z = true;
            }
        }
        return z;
    }

    /* renamed from: b */
    void m2540b(cj cjVar) {
        mo4148j(mo4131a(cjVar));
    }

    /* renamed from: b */
    public void mo4138b(C1287n c1287n, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            mo4148j(c1287n);
        }
    }

    /* renamed from: b */
    public void mo4139b(C1287n c1287n, Object obj) {
        synchronized (this.f2311c) {
            if (this.f2314f.containsKey(c1287n)) {
                this.f2310b.mo4178w("PreloadManager", "Possibly missing prior registered preload callback.");
            }
            this.f2314f.put(c1287n, obj);
        }
    }

    /* renamed from: b */
    public boolean mo4140b(C1287n c1287n) {
        return this.f2314f.containsKey(c1287n);
    }

    /* renamed from: c */
    public cj mo4141c(C1287n c1287n) {
        cj f;
        synchronized (this.f2311c) {
            dt n = m2532n(c1287n);
            f = n != null ? n.m2626f() : null;
        }
        return f;
    }

    /* renamed from: c */
    protected void m2545c(cj cjVar) {
        C1287n a = mo4131a(cjVar);
        boolean l = a.m3063l();
        synchronized (this.f2311c) {
            Object obj = this.f2314f.get(a);
            this.f2314f.remove(a);
            this.f2315g.add(a);
            if (obj == null || l) {
                m2530l(a).m2621a(cjVar);
                this.f2310b.mo4172d("PreloadManager", "Ad enqueued: " + cjVar);
            } else {
                this.f2310b.mo4172d("PreloadManager", "Additional callback found or dummy ads are enabled; skipping enqueue...");
            }
        }
        if (obj != null) {
            this.f2310b.mo4172d("PreloadManager", "Called additional callback regarding " + cjVar);
            if (l) {
                try {
                    mo4134a(obj, new aq(a, this.f2309a));
                } catch (Throwable th) {
                    this.f2309a.getLogger().userError("PreloadManager", "Encountered throwable while notifying user callback", th);
                }
            } else {
                mo4134a(obj, cjVar);
                m2540b(cjVar);
            }
        }
        this.f2310b.mo4172d("PreloadManager", "Pulled ad from network and saved to preload cache: " + cjVar);
    }

    /* renamed from: c */
    protected void m2546c(C1287n c1287n, int i) {
        this.f2310b.mo4172d("PreloadManager", "Failed to pre-load an ad of zone " + c1287n + ", error code " + i);
        synchronized (this.f2311c) {
            Object remove = this.f2314f.remove(c1287n);
            this.f2315g.add(c1287n);
        }
        if (remove != null) {
            try {
                mo4135a(remove, c1287n, i);
            } catch (Throwable th) {
                this.f2309a.getLogger().userError("PreloadManager", "Encountered exception while invoking user callback", th);
            }
        }
    }

    /* renamed from: d */
    public cj mo4142d(C1287n c1287n) {
        cj e;
        synchronized (this.f2311c) {
            dt n = m2532n(c1287n);
            e = n != null ? n.m2625e() : null;
        }
        return e;
    }

    /* renamed from: e */
    public cj mo4143e(C1287n c1287n) {
        cj cjVar;
        synchronized (this.f2311c) {
            dt l = m2530l(c1287n);
            if (l == null) {
                cjVar = null;
            } else if (c1287n.m3063l()) {
                dt m = m2531m(c1287n);
                if (m.m2623c()) {
                    cjVar = new aq(c1287n, this.f2309a);
                } else if (l.m2619a() > 0) {
                    m.m2621a(l.m2625e());
                    cjVar = new aq(c1287n, this.f2309a);
                } else {
                    cjVar = (m.m2619a() <= 0 || !((Boolean) this.f2309a.get(ea.cV)).booleanValue()) ? null : new aq(c1287n, this.f2309a);
                }
            } else {
                cjVar = l.m2625e();
            }
        }
        if (cjVar != null) {
            this.f2310b.mo4172d("PreloadManager", "Retrieved ad of zone " + c1287n + "...");
        } else {
            this.f2310b.mo4172d("PreloadManager", "Unable to retrieve ad of zone " + c1287n + "...");
        }
        return cjVar;
    }

    /* renamed from: f */
    public boolean mo4144f(C1287n c1287n) {
        boolean c;
        synchronized (this.f2311c) {
            dt l = m2530l(c1287n);
            c = l != null ? l.m2623c() : false;
        }
        return c;
    }

    /* renamed from: g */
    public void mo4145g(C1287n c1287n) {
        if (c1287n != null) {
            int i = 0;
            synchronized (this.f2311c) {
                dt l = m2530l(c1287n);
                if (l != null) {
                    i = l.m2622b() - l.m2619a();
                }
            }
            mo4138b(c1287n, i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: h */
    public boolean mo4146h(com.applovin.impl.sdk.C1287n r7) {
        /*
        r6 = this;
        r1 = 1;
        r2 = 0;
        r3 = r6.f2311c;
        monitor-enter(r3);
        r4 = r6.m2531m(r7);	 Catch:{ all -> 0x0033 }
        r0 = r6.f2309a;	 Catch:{ all -> 0x0033 }
        r5 = com.applovin.impl.sdk.ea.cW;	 Catch:{ all -> 0x0033 }
        r0 = r0.get(r5);	 Catch:{ all -> 0x0033 }
        r0 = (java.lang.Boolean) r0;	 Catch:{ all -> 0x0033 }
        r0 = r0.booleanValue();	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0024;
    L_0x0019:
        if (r4 == 0) goto L_0x0024;
    L_0x001b:
        r0 = r4.m2619a();	 Catch:{ all -> 0x0033 }
        if (r0 <= 0) goto L_0x0024;
    L_0x0021:
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        r0 = r1;
    L_0x0023:
        return r0;
    L_0x0024:
        r0 = r6.m2530l(r7);	 Catch:{ all -> 0x0033 }
        if (r0 == 0) goto L_0x0038;
    L_0x002a:
        r0 = r0.m2624d();	 Catch:{ all -> 0x0033 }
        if (r0 != 0) goto L_0x0036;
    L_0x0030:
        r0 = r1;
    L_0x0031:
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        goto L_0x0023;
    L_0x0033:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0033 }
        throw r0;
    L_0x0036:
        r0 = r2;
        goto L_0x0031;
    L_0x0038:
        r0 = r2;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.ds.h(com.applovin.impl.sdk.n):boolean");
    }

    /* renamed from: i */
    public void mo4147i(C1287n c1287n) {
        synchronized (this.f2311c) {
            dt l = m2530l(c1287n);
            if (l != null) {
                l.m2620a(c1287n.m3057f());
            } else {
                this.f2312d.put(c1287n, new dt(c1287n.m3057f()));
            }
            l = m2531m(c1287n);
            if (l != null) {
                l.m2620a(c1287n.m3058g());
            } else {
                this.f2313e.put(c1287n, new dt(c1287n.m3058g()));
            }
        }
    }

    /* renamed from: j */
    public void mo4148j(C1287n c1287n) {
        if (((Boolean) this.f2309a.get(ea.f2386J)).booleanValue() && !mo4144f(c1287n)) {
            this.f2310b.mo4172d("PreloadManager", "Preloading ad for zone " + c1287n + "...");
            this.f2309a.getTaskManager().m2856a(mo4130a(c1287n), fe.MAIN, 500);
        }
    }

    /* renamed from: k */
    boolean m2554k(C1287n c1287n) {
        boolean contains;
        synchronized (this.f2311c) {
            contains = this.f2315g.contains(c1287n);
        }
        return contains;
    }
}
