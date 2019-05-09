package com.tapjoy.internal;

import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.Map;

public final class ho extends hl {
    /* renamed from: c */
    private final ed f8141c;
    /* renamed from: d */
    private final dx f8142d;
    /* renamed from: e */
    private final ek f8143e;
    /* renamed from: f */
    private final String f8144f;

    private ho(ed edVar, dx dxVar, ek ekVar, String str) {
        this.f8141c = edVar;
        this.f8142d = dxVar;
        this.f8143e = ekVar;
        this.f8144f = str;
    }

    public ho(ee eeVar, String str) {
        this(eeVar.f7536d, eeVar.f7537e, eeVar.f7538f, str);
    }

    /* renamed from: c */
    public final String mo6338c() {
        return "api/v1/tokens";
    }

    /* renamed from: e */
    public final Map mo6336e() {
        Map e = super.mo6336e();
        e.put(String.VIDEO_INFO, new br(gs.m7994a(this.f8141c)));
        e.put(TapjoyConstants.TJC_APP_PLACEMENT, new br(gs.m7990a(this.f8142d)));
        e.put("user", new br(gs.m7995a(this.f8143e)));
        if (!aq.m7169a(this.f8144f)) {
            e.put("push_token", this.f8144f);
        }
        return e;
    }
}
