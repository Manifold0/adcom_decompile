package com.tapjoy.internal;

import android.graphics.Point;
import android.os.SystemClock;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class gx extends gt {
    /* renamed from: n */
    public static final bn f8004n = new C29651();
    /* renamed from: a */
    public ha f8005a;
    /* renamed from: b */
    public ha f8006b;
    /* renamed from: c */
    public ha f8007c;
    /* renamed from: d */
    public Point f8008d;
    /* renamed from: e */
    public ha f8009e;
    /* renamed from: f */
    public ha f8010f;
    /* renamed from: g */
    public String f8011g;
    /* renamed from: h */
    public fo f8012h;
    /* renamed from: i */
    public ArrayList f8013i = new ArrayList();
    /* renamed from: j */
    public ArrayList f8014j = new ArrayList();
    /* renamed from: k */
    public Map f8015k;
    /* renamed from: l */
    public long f8016l;
    /* renamed from: m */
    public gy f8017m;

    /* renamed from: com.tapjoy.internal.gx$1 */
    static class C29651 implements bn {
        C29651() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            return new gx(bsVar);
        }
    }

    gx(bs bsVar) {
        Iterator it;
        gv gvVar;
        bsVar.mo6193h();
        String str = null;
        String str2 = null;
        while (bsVar.mo6195j()) {
            String l = bsVar.mo6197l();
            if ("frame".equals(l)) {
                bsVar.mo6193h();
                while (bsVar.mo6195j()) {
                    l = bsVar.mo6197l();
                    if ("portrait".equals(l)) {
                        this.f8005a = (ha) ha.f8032e.mo6185a(bsVar);
                    } else if ("landscape".equals(l)) {
                        this.f8006b = (ha) ha.f8032e.mo6185a(bsVar);
                    } else if ("close_button".equals(l)) {
                        this.f8007c = (ha) ha.f8032e.mo6185a(bsVar);
                    } else if ("close_button_offset".equals(l)) {
                        this.f8008d = (Point) bo.f7229a.mo6185a(bsVar);
                    } else {
                        bsVar.mo6204s();
                    }
                }
                bsVar.mo6194i();
            } else if ("creative".equals(l)) {
                bsVar.mo6193h();
                while (bsVar.mo6195j()) {
                    l = bsVar.mo6197l();
                    if ("portrait".equals(l)) {
                        this.f8009e = (ha) ha.f8032e.mo6185a(bsVar);
                    } else if ("landscape".equals(l)) {
                        this.f8010f = (ha) ha.f8032e.mo6185a(bsVar);
                    } else {
                        bsVar.mo6204s();
                    }
                }
                bsVar.mo6194i();
            } else if ("url".equals(l)) {
                this.f8011g = bsVar.m7254b();
            } else if (gr.m7988a(l)) {
                this.f8012h = gr.m7987a(l, bsVar);
            } else if ("mappings".equals(l)) {
                bsVar.mo6193h();
                while (bsVar.mo6195j()) {
                    l = bsVar.mo6197l();
                    if ("portrait".equals(l)) {
                        bsVar.m7251a(this.f8013i, gv.f7984h);
                    } else if ("landscape".equals(l)) {
                        bsVar.m7251a(this.f8014j, gv.f7984h);
                    } else {
                        bsVar.mo6204s();
                    }
                }
                bsVar.mo6194i();
            } else if ("meta".equals(l)) {
                this.f8015k = bsVar.m7257d();
            } else if ("ttl".equals(l)) {
                this.f8016l = ((long) (bsVar.mo6201p() * 1000.0d)) + SystemClock.elapsedRealtime();
            } else if ("no_more_today".equals(l)) {
                this.f8017m = (gy) gy.f8018d.mo6185a(bsVar);
            } else if ("ad_content".equals(l)) {
                str = bsVar.m7254b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str2 = bsVar.m7254b();
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
        if (this.f8011g == null) {
            this.f8011g = "";
        }
        if (this.f8013i != null) {
            it = this.f8013i.iterator();
            while (it.hasNext()) {
                gvVar = (gv) it.next();
                if (gvVar.f7990f == null) {
                    gvVar.f7990f = str;
                }
                if (gvVar.f7989e == null) {
                    gvVar.f7989e = str2;
                }
            }
        }
        if (this.f8014j != null) {
            it = this.f8014j.iterator();
            while (it.hasNext()) {
                gvVar = (gv) it.next();
                if (gvVar.f7990f == null) {
                    gvVar.f7990f = str;
                }
                if (gvVar.f7989e == null) {
                    gvVar.f7989e = str2;
                }
            }
        }
    }

    /* renamed from: a */
    public final boolean m8010a() {
        return (this.f8007c == null || this.f8005a == null || this.f8009e == null) ? false : true;
    }

    /* renamed from: b */
    public final boolean m8011b() {
        return (this.f8007c == null || this.f8006b == null || this.f8010f == null) ? false : true;
    }
}
