package com.facebook.ads.internal.p017t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.t.i */
public class C2116i {
    /* renamed from: a */
    private final double f4876a;
    /* renamed from: b */
    private final double f4877b;

    public C2116i(double d, double d2) {
        this.f4876a = d;
        this.f4877b = d2;
    }

    @Nullable
    /* renamed from: a */
    public static C2116i m5355a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        double optDouble = jSONObject.optDouble("value", 0.0d);
        double optDouble2 = jSONObject.optDouble("scale", 0.0d);
        return (optDouble == 0.0d || optDouble2 == 0.0d) ? null : new C2116i(optDouble, optDouble2);
    }

    /* renamed from: a */
    public double m5356a() {
        return this.f4876a;
    }

    /* renamed from: b */
    public double m5357b() {
        return this.f4877b;
    }
}
