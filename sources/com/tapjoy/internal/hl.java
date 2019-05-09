package com.tapjoy.internal;

import com.kongregate.p000o.p002g.C0640a;
import com.tapjoy.TapjoyConstants;
import java.util.Map;

public abstract class hl extends ce {
    /* renamed from: b */
    public final String mo6334b() {
        return "POST";
    }

    /* renamed from: d */
    public final String mo6335d() {
        return C0640a.f1003a;
    }

    /* renamed from: e */
    public Map mo6336e() {
        Map e = super.mo6336e();
        gc a = gc.m7831a();
        e.put("sdk_ver", a.f7861m + "/Android");
        e.put(TapjoyConstants.TJC_API_KEY, a.f7860l);
        if (fz.f7823a) {
            e.put("debug", Boolean.valueOf(true));
        }
        return e;
    }

    /* renamed from: f */
    protected Object mo6337f() {
        try {
            return super.mo6337f();
        } catch (Exception e) {
            new Object[1][0] = this;
            throw e;
        }
    }

    /* renamed from: a */
    protected Object mo6333a(bs bsVar) {
        bsVar.mo6204s();
        return null;
    }
}
