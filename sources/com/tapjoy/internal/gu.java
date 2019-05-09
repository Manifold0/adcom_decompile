package com.tapjoy.internal;

import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public final class gu extends gt {
    /* renamed from: d */
    public static final bn f7980d = new C29601();
    /* renamed from: a */
    public ArrayList f7981a = new ArrayList();
    /* renamed from: b */
    public Map f7982b;
    /* renamed from: c */
    public float f7983c;

    /* renamed from: com.tapjoy.internal.gu$1 */
    static class C29601 implements bn {
        C29601() {
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6185a(bs bsVar) {
            return new gu(bsVar);
        }
    }

    public gu(bs bsVar) {
        bsVar.mo6193h();
        String str = null;
        String str2 = null;
        while (bsVar.mo6195j()) {
            String l = bsVar.mo6197l();
            if ("layouts".equals(l)) {
                bsVar.m7251a(this.f7981a, hd.f8057d);
            } else if ("meta".equals(l)) {
                this.f7982b = bsVar.m7257d();
            } else if ("max_show_time".equals(l)) {
                this.f7983c = (float) bsVar.mo6201p();
            } else if ("ad_content".equals(l)) {
                str2 = bsVar.m7254b();
            } else if (TapjoyConstants.TJC_REDIRECT_URL.equals(l)) {
                str = bsVar.m7254b();
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
        if (this.f7981a != null) {
            Iterator it = this.f7981a.iterator();
            while (it.hasNext()) {
                hd hdVar = (hd) it.next();
                if (hdVar.f8060c != null) {
                    Iterator it2 = hdVar.f8060c.iterator();
                    while (it2.hasNext()) {
                        hc hcVar = (hc) it2.next();
                        if (hcVar.f8052i == null) {
                            hcVar.f8052i = str2;
                        }
                        if (hcVar.f8051h == null) {
                            hcVar.f8051h = str;
                        }
                    }
                }
            }
        }
    }
}
