package com.tapjoy.internal;

import com.kongregate.android.internal.sdk.C0525o;

final class gz extends gr implements fs {
    /* renamed from: a */
    public static final bn f8022a = new C29671();
    /* renamed from: b */
    private final String f8023b;
    /* renamed from: c */
    private final String f8024c;

    /* renamed from: com.tapjoy.internal.gz$1 */
    static class C29671 implements bn {
        C29671() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            String str = "";
            String str2 = "";
            bsVar.mo6193h();
            while (bsVar.mo6195j()) {
                String l = bsVar.mo6197l();
                if ("campaign_id".equals(l)) {
                    str = bsVar.m7255c("");
                } else if (C0525o.f622h.equals(l)) {
                    str2 = bsVar.m7255c("");
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return new gz(str, str2);
        }
    }

    gz(String str, String str2) {
        this.f8023b = str;
        this.f8024c = str2;
    }

    /* renamed from: a */
    public final String mo6324a() {
        return this.f8023b;
    }

    /* renamed from: b */
    public final String mo6325b() {
        return this.f8024c;
    }
}
