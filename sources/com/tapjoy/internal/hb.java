package com.tapjoy.internal;

import com.kongregate.android.api.AnalyticsServices;

final class hb extends gr implements ft {
    /* renamed from: a */
    public static final bn f8038a = new C29701();
    /* renamed from: b */
    private final String f8039b;
    /* renamed from: c */
    private final String f8040c;
    /* renamed from: d */
    private final int f8041d;
    /* renamed from: e */
    private final String f8042e;

    /* renamed from: com.tapjoy.internal.hb$1 */
    static class C29701 implements bn {
        C29701() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            String str = null;
            int i = 1;
            bsVar.mo6193h();
            String str2 = null;
            String str3 = null;
            while (bsVar.mo6195j()) {
                String l = bsVar.mo6197l();
                if ("id".equals(l)) {
                    str3 = bsVar.mo6198m();
                } else if ("name".equals(l)) {
                    str2 = bsVar.mo6198m();
                } else if (AnalyticsServices.SWRVE_VIRTUAL_ECONOMY_PARAM_QUANTITY.equals(l)) {
                    i = bsVar.mo6203r();
                } else if ("token".equals(l)) {
                    str = bsVar.mo6198m();
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return new hb(str3, str2, i, str);
        }
    }

    hb(String str, String str2, int i, String str3) {
        this.f8039b = str;
        this.f8040c = str2;
        this.f8041d = i;
        this.f8042e = str3;
    }

    /* renamed from: a */
    public final String mo6326a() {
        return this.f8039b;
    }

    /* renamed from: b */
    public final String mo6327b() {
        return this.f8040c;
    }

    /* renamed from: c */
    public final int mo6328c() {
        return this.f8041d;
    }

    /* renamed from: d */
    public final String mo6329d() {
        return this.f8042e;
    }
}
