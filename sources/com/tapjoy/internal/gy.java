package com.tapjoy.internal;

import android.graphics.Point;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.net.URL;

public final class gy {
    /* renamed from: d */
    public static final bn f8018d = new C29661();
    /* renamed from: a */
    public final ha f8019a;
    /* renamed from: b */
    public final Point f8020b;
    /* renamed from: c */
    public final Point f8021c;

    /* renamed from: com.tapjoy.internal.gy$1 */
    static class C29661 implements bn {
        C29661() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            ha haVar = null;
            bsVar.mo6193h();
            Point point = null;
            Point point2 = null;
            while (bsVar.mo6195j()) {
                String l = bsVar.mo6197l();
                if (MessengerShareContentUtility.MEDIA_IMAGE.equals(l)) {
                    l = bsVar.mo6198m();
                    if (!ct.m7339c(l)) {
                        haVar = new ha(new URL(l));
                    }
                } else if ("landscape".equals(l)) {
                    point2 = C29661.m8012b(bsVar);
                } else if ("portrait".equals(l)) {
                    point = C29661.m8012b(bsVar);
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return new gy(haVar, point2, point);
        }

        /* renamed from: b */
        private static Point m8012b(bs bsVar) {
            Point point = null;
            bsVar.mo6193h();
            while (bsVar.mo6195j()) {
                if ("offset".equals(bsVar.mo6197l())) {
                    bsVar.mo6193h();
                    int i = 0;
                    int i2 = 0;
                    while (bsVar.mo6195j()) {
                        String l = bsVar.mo6197l();
                        if ("x".equals(l)) {
                            i2 = bsVar.mo6203r();
                        } else if ("y".equals(l)) {
                            i = bsVar.mo6203r();
                        } else {
                            bsVar.mo6204s();
                        }
                    }
                    bsVar.mo6194i();
                    point = new Point(i2, i);
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return point;
        }
    }

    public gy(ha haVar, Point point, Point point2) {
        this.f8019a = haVar;
        this.f8020b = point;
        this.f8021c = point2;
    }
}
