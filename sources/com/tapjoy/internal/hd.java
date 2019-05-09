package com.tapjoy.internal;

import android.graphics.PointF;
import java.util.ArrayList;

public final class hd {
    /* renamed from: d */
    public static final bn f8057d = new C29721();
    /* renamed from: a */
    public af f8058a = af.UNSPECIFIED;
    /* renamed from: b */
    public PointF f8059b;
    /* renamed from: c */
    public ArrayList f8060c = new ArrayList();

    /* renamed from: com.tapjoy.internal.hd$1 */
    static class C29721 implements bn {
        C29721() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            return new hd(bsVar);
        }
    }

    public hd(bs bsVar) {
        bsVar.mo6193h();
        while (bsVar.mo6195j()) {
            String l = bsVar.mo6197l();
            if ("buttons".equals(l)) {
                Object obj;
                if (bsVar.mo6196k() == bx.BEGIN_ARRAY) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj != null) {
                    bsVar.m7251a(this.f8060c, hc.f8043n);
                } else {
                    bsVar.mo6204s();
                }
            } else if ("window_aspect_ratio".equals(l)) {
                if (bsVar.m7253a()) {
                    PointF pointF = new PointF();
                    bsVar.mo6193h();
                    while (bsVar.mo6195j()) {
                        String l2 = bsVar.mo6197l();
                        if ("width".equals(l2)) {
                            pointF.x = (float) bsVar.mo6201p();
                        } else if ("height".equals(l2)) {
                            pointF.y = (float) bsVar.mo6201p();
                        } else {
                            bsVar.mo6204s();
                        }
                    }
                    bsVar.mo6194i();
                    if (!(pointF.x == 0.0f || pointF.y == 0.0f)) {
                        this.f8059b = pointF;
                    }
                } else {
                    bsVar.mo6204s();
                }
            } else if ("orientation".equals(l)) {
                l = bsVar.mo6198m();
                if ("landscape".equals(l)) {
                    this.f8058a = af.LANDSCAPE;
                } else if ("portrait".equals(l)) {
                    this.f8058a = af.PORTRAIT;
                }
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
    }
}
