package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.chartboost.sdk.impl.k */
class C1445k extends ad<Void> {
    /* renamed from: a */
    final C1444j f3292a;
    /* renamed from: k */
    private final C1447l f3293k;
    /* renamed from: l */
    private final ai f3294l;

    C1445k(C1447l c1447l, ai aiVar, C1444j c1444j, File file) {
        super("GET", c1444j.f3285c, 2, file);
        this.j = 1;
        this.f3293k = c1447l;
        this.f3294l = aiVar;
        this.f3292a = c1444j;
    }

    /* renamed from: a */
    public ae mo4280a() {
        Map hashMap = new HashMap();
        hashMap.put("X-Chartboost-App", C1410i.f2934k);
        hashMap.put("X-Chartboost-Client", CBUtility.m3114b());
        hashMap.put("X-Chartboost-Reachability", Integer.toString(this.f3294l.m3373a()));
        return new ae(hashMap, null, null);
    }

    /* renamed from: a */
    public void m3594a(Void voidR, ag agVar) {
        this.f3293k.m3599a(this, null, null);
    }

    /* renamed from: a */
    public void mo4282a(CBError cBError, ag agVar) {
        this.f3293k.m3599a(this, cBError, agVar);
    }
}
