package com.tapjoy.internal;

import android.graphics.Rect;
import com.tapjoy.TapjoyConstants;

public final class gv {
    /* renamed from: h */
    public static final bn f7984h = new C29611();
    /* renamed from: a */
    public final Rect f7985a;
    /* renamed from: b */
    public final String f7986b;
    /* renamed from: c */
    public final boolean f7987c;
    /* renamed from: d */
    public final String f7988d;
    /* renamed from: e */
    public String f7989e;
    /* renamed from: f */
    public String f7990f;
    /* renamed from: g */
    public final fo f7991g;

    /* renamed from: com.tapjoy.internal.gv$1 */
    static class C29611 implements bn {
        C29611() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            fo foVar = null;
            boolean z = false;
            String str = "";
            bsVar.mo6193h();
            String str2 = null;
            String str3 = null;
            String str4 = null;
            Rect rect = null;
            while (bsVar.mo6195j()) {
                String l = bsVar.mo6197l();
                if ("region".equals(l)) {
                    rect = (Rect) bo.f7230b.mo6185a(bsVar);
                } else if ("value".equals(l)) {
                    str4 = bsVar.mo6198m();
                } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                    z = bsVar.mo6199n();
                } else if ("url".equals(l)) {
                    str = bsVar.mo6198m();
                } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                    str3 = bsVar.m7254b();
                } else if ("ad_content".equals(l)) {
                    str2 = bsVar.m7254b();
                } else if (gr.m7988a(l)) {
                    foVar = gr.m7987a(l, bsVar);
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return new gv(rect, str4, z, str, str3, str2, foVar);
        }
    }

    gv(Rect rect, String str, boolean z, String str2, String str3, String str4, fo foVar) {
        this.f7985a = rect;
        this.f7986b = str;
        this.f7987c = z;
        this.f7988d = str2;
        this.f7989e = str3;
        this.f7990f = str4;
        this.f7991g = foVar;
    }
}
