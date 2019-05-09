package com.kongregate.p000o.p009h;

import org.json.JSONObject;

/* renamed from: com.kongregate.o.h.c */
public class C0656c {
    /* renamed from: a */
    public final long f1060a;
    /* renamed from: b */
    public final String f1061b;
    /* renamed from: c */
    public final String f1062c;
    /* renamed from: d */
    public final int f1063d;

    public C0656c(JSONObject jSONObject) {
        this.f1060a = (long) jSONObject.optInt("id");
        this.f1061b = jSONObject.optString("identifier");
        this.f1062c = jSONObject.optString("data");
        this.f1063d = jSONObject.optInt("remaining_uses", -1);
    }
}
