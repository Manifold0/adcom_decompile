package com.tapjoy.internal;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TapjoyConstants;

public final class hc {
    /* renamed from: n */
    public static final bn f8043n = new C29711();
    /* renamed from: a */
    public he f8044a;
    /* renamed from: b */
    public he f8045b;
    /* renamed from: c */
    public he f8046c;
    /* renamed from: d */
    public he f8047d;
    /* renamed from: e */
    public int f8048e = 9;
    /* renamed from: f */
    public int f8049f = 10;
    /* renamed from: g */
    public String f8050g;
    /* renamed from: h */
    public String f8051h;
    /* renamed from: i */
    public String f8052i;
    /* renamed from: j */
    public boolean f8053j = false;
    /* renamed from: k */
    public String f8054k;
    /* renamed from: l */
    public ha f8055l;
    /* renamed from: m */
    public ha f8056m;

    /* renamed from: com.tapjoy.internal.hc$1 */
    static class C29711 implements bn {
        C29711() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            return new hc(bsVar);
        }
    }

    public hc(bs bsVar) {
        bsVar.mo6193h();
        while (bsVar.mo6195j()) {
            String l = bsVar.mo6197l();
            if ("x".equals(l)) {
                this.f8044a = he.m8028a(bsVar.mo6198m());
            } else if ("y".equals(l)) {
                this.f8045b = he.m8028a(bsVar.mo6198m());
            } else if ("width".equals(l)) {
                this.f8046c = he.m8028a(bsVar.mo6198m());
            } else if ("height".equals(l)) {
                this.f8047d = he.m8028a(bsVar.mo6198m());
            } else if ("url".equals(l)) {
                this.f8050g = bsVar.mo6198m();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                this.f8051h = bsVar.mo6198m();
            } else if ("ad_content".equals(l)) {
                this.f8052i = bsVar.mo6198m();
            } else if (TapjoyConstants.TJC_FULLSCREEN_AD_DISMISS_URL.equals(l)) {
                this.f8053j = bsVar.mo6199n();
            } else if ("value".equals(l)) {
                this.f8054k = bsVar.mo6198m();
            } else if (MessengerShareContentUtility.MEDIA_IMAGE.equals(l)) {
                this.f8055l = (ha) ha.f8032e.mo6185a(bsVar);
            } else if ("image_clicked".equals(l)) {
                this.f8056m = (ha) ha.f8032e.mo6185a(bsVar);
            } else if ("align".equals(l)) {
                l = bsVar.mo6198m();
                if ("left".equals(l)) {
                    this.f8048e = 9;
                } else if ("right".equals(l)) {
                    this.f8048e = 11;
                } else if ("center".equals(l)) {
                    this.f8048e = 14;
                } else {
                    bsVar.mo6204s();
                }
            } else if ("valign".equals(l)) {
                l = bsVar.mo6198m();
                if ("top".equals(l)) {
                    this.f8049f = 10;
                } else if ("middle".equals(l)) {
                    this.f8049f = 15;
                } else if ("bottom".equals(l)) {
                    this.f8049f = 12;
                } else {
                    bsVar.mo6204s();
                }
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
    }
}
