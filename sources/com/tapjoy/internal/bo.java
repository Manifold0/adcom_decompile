package com.tapjoy.internal;

import android.graphics.Point;
import android.graphics.Rect;

public final class bo {
    /* renamed from: a */
    public static final bn f7229a = new C28421();
    /* renamed from: b */
    public static final bn f7230b = new C28432();

    /* renamed from: com.tapjoy.internal.bo$1 */
    static class C28421 implements bn {
        C28421() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            Point point = new Point();
            bsVar.mo6193h();
            while (bsVar.mo6195j()) {
                String l = bsVar.mo6197l();
                if ("x".equals(l)) {
                    point.x = bsVar.mo6203r();
                } else if ("y".equals(l)) {
                    point.y = bsVar.mo6203r();
                } else {
                    bsVar.mo6204s();
                }
            }
            bsVar.mo6194i();
            return point;
        }
    }

    /* renamed from: com.tapjoy.internal.bo$2 */
    static class C28432 implements bn {
        C28432() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            Rect rect = new Rect();
            switch (bsVar.mo6196k()) {
                case BEGIN_ARRAY:
                    bsVar.mo6191f();
                    rect.left = bsVar.mo6203r();
                    rect.top = bsVar.mo6203r();
                    rect.right = bsVar.mo6203r();
                    rect.bottom = bsVar.mo6203r();
                    while (bsVar.mo6195j()) {
                        bsVar.mo6204s();
                    }
                    bsVar.mo6192g();
                    break;
                case BEGIN_OBJECT:
                    bsVar.mo6193h();
                    while (bsVar.mo6195j()) {
                        String l = bsVar.mo6197l();
                        if ("left".equals(l)) {
                            rect.left = bsVar.mo6203r();
                        } else if ("top".equals(l)) {
                            rect.top = bsVar.mo6203r();
                        } else if ("right".equals(l)) {
                            rect.right = bsVar.mo6203r();
                        } else if ("bottom".equals(l)) {
                            rect.bottom = bsVar.mo6203r();
                        } else {
                            bsVar.mo6204s();
                        }
                    }
                    bsVar.mo6194i();
                    break;
                default:
                    throw new IllegalStateException("Unexpected token: " + bsVar.mo6196k());
            }
            return rect;
        }
    }
}
