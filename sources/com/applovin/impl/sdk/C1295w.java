package com.applovin.impl.sdk;

/* renamed from: com.applovin.impl.sdk.w */
class C1295w extends dx {
    /* renamed from: a */
    final /* synthetic */ AppLovinAdServiceImpl f2632a;
    /* renamed from: b */
    private final C1287n f2633b;

    private C1295w(AppLovinAdServiceImpl appLovinAdServiceImpl, C1287n c1287n) {
        this.f2632a = appLovinAdServiceImpl;
        super("UpdateAdTask", appLovinAdServiceImpl.f1935a);
        this.f2633b = c1287n;
    }

    public void run() {
        boolean z = true;
        this.e.mo4172d("AppLovinAdService", "Attempt update for spec: " + this.f2633b);
        C1294v b = this.f2632a.m2120a(this.f2633b);
        synchronized (b.f2626a) {
            boolean i = this.f2633b.m3060i();
            boolean d = this.f2632a.m2129a();
            boolean z2 = !b.f2630e.isEmpty();
            if (System.currentTimeMillis() <= b.f2628c) {
                z = false;
            }
            this.e.mo4172d("AppLovinAdService", "Update ad states - isRefreshEnabled=" + i + " hasUpdateListeners=" + z2 + " isCurrentAdExpired=" + z + " isDeviceOn=" + d + " isWaitingForAd=" + b.f2629d);
            if (i && z2 && z && d && !b.f2629d) {
                this.e.mo4172d("AppLovinAdService", "Performing ad update...");
                b.f2629d = true;
                this.f2632a.m2127a(this.f2633b, new C1293u(this.f2632a, b));
            } else {
                this.e.mo4172d("AppLovinAdService", "Ad update skipped");
            }
        }
    }
}
