package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.ui.VungleActivity;
import java.util.List;
import java.util.Map;

public final class hm extends hl {
    /* renamed from: c */
    public final String f8132c;
    /* renamed from: d */
    public boolean f8133d = false;
    /* renamed from: e */
    private final gc f8134e;
    /* renamed from: f */
    private final ed f8135f;
    /* renamed from: g */
    private final dx f8136g;
    /* renamed from: h */
    private final ek f8137h;
    /* renamed from: i */
    private Context f8138i;

    /* renamed from: com.tapjoy.internal.hm$a */
    public static class C2978a {
        /* renamed from: a */
        public gj f8130a;
        /* renamed from: b */
        public final List f8131b;

        public C2978a(gj gjVar, List list) {
            this.f8130a = gjVar;
            this.f8131b = list;
        }
    }

    /* renamed from: a */
    protected final /* synthetic */ Object mo6333a(bs bsVar) {
        bsVar.mo6193h();
        List list = null;
        gu guVar = null;
        gx gxVar = null;
        while (bsVar.mo6195j()) {
            String l = bsVar.mo6197l();
            if ("interstitial".equals(l)) {
                gxVar = (gx) bsVar.m7248a(gx.f8004n);
            } else if ("contextual_button".equals(l)) {
                guVar = (gu) bsVar.m7248a(gu.f7980d);
            } else if ("enabled_placements".equals(l)) {
                list = bsVar.m7256c();
            } else {
                bsVar.mo6204s();
            }
        }
        bsVar.mo6194i();
        if (gxVar != null && (gxVar.m8010a() || gxVar.m8011b())) {
            return new C2978a(new gh(this.f8134e, this.f8132c, gxVar, this.f8138i), list);
        }
        if (guVar != null) {
            return new C2978a(new fy(this.f8134e, this.f8132c, guVar, this.f8138i), list);
        }
        return new C2978a(new gi(), list);
    }

    public hm(gc gcVar, ed edVar, dx dxVar, ek ekVar, String str, Context context) {
        this.f8134e = gcVar;
        this.f8135f = edVar;
        this.f8136g = dxVar;
        this.f8137h = ekVar;
        this.f8132c = str;
        this.f8138i = context;
    }

    /* renamed from: c */
    public final String mo6338c() {
        return VungleActivity.PLACEMENT_EXTRA;
    }

    /* renamed from: e */
    public final Map mo6336e() {
        Map e = super.mo6336e();
        e.put(String.VIDEO_INFO, new br(gs.m7994a(this.f8135f)));
        e.put(TapjoyConstants.TJC_APP_PLACEMENT, new br(gs.m7990a(this.f8136g)));
        e.put("user", new br(gs.m7995a(this.f8137h)));
        e.put(VungleActivity.PLACEMENT_EXTRA, this.f8132c);
        return e;
    }

    /* renamed from: f */
    protected final /* synthetic */ Object mo6337f() {
        C2978a c2978a = (C2978a) super.mo6337f();
        if (!(c2978a.f8130a instanceof gi)) {
            c2978a.f8130a.mo6291b();
            if (!c2978a.f8130a.mo6292c()) {
                new Object[1][0] = this.f8132c;
                c2978a.f8130a = new gi();
            }
        }
        return c2978a;
    }
}
