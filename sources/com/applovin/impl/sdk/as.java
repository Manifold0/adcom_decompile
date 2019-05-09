package com.applovin.impl.sdk;

import java.util.Map;

class as implements er {
    /* renamed from: a */
    final /* synthetic */ du f2073a;
    /* renamed from: b */
    final /* synthetic */ boolean f2074b;
    /* renamed from: c */
    final /* synthetic */ EventServiceImpl f2075c;

    as(EventServiceImpl eventServiceImpl, du duVar, boolean z) {
        this.f2075c = eventServiceImpl;
        this.f2073a = duVar;
        this.f2074b = z;
    }

    /* renamed from: a */
    public void mo4106a(aj ajVar) {
        try {
            Map a = this.f2075c.m2155a(this.f2073a, ajVar);
            String uri = this.f2075c.m2151a(this.f2075c.m2152a(), a).toString();
            String uri2 = this.f2075c.m2151a(this.f2075c.m2161b(), a).toString();
            if (this.f2074b) {
                this.f2075c.f1977a.getPersistentPostbackManager().m2612a(uri, this.f2073a.m2628b(), true, uri2);
            } else {
                this.f2075c.f1977a.getPostbackService().dispatchPostbackAsync(uri, this.f2073a.m2628b(), uri2, null);
            }
        } catch (Throwable e) {
            this.f2075c.f1977a.getLogger().mo4174e("EventServiceImpl", "Unable to track event due to failure to convert event parameters into JSONObject for event: " + this.f2073a, e);
        }
    }
}
