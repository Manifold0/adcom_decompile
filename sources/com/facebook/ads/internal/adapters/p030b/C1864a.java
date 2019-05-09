package com.facebook.ads.internal.adapters.p030b;

import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.a */
public abstract class C1864a implements Serializable {
    private static final long serialVersionUID = -5352540727250859603L;
    /* renamed from: a */
    private int f3896a = 200;
    /* renamed from: b */
    private String f3897b;
    /* renamed from: c */
    private String f3898c;

    /* renamed from: a */
    public static C1864a m4204a(boolean z, JSONObject jSONObject) {
        return z ? C1874f.m4272a(jSONObject) : C1887q.m4355a(jSONObject);
    }

    /* renamed from: a */
    public abstract String mo5384a();

    /* renamed from: a */
    public void m4206a(int i) {
        this.f3896a = i;
    }

    /* renamed from: a */
    public void mo5386a(String str) {
        this.f3897b = str;
    }

    /* renamed from: b */
    public int m4208b() {
        return this.f3896a;
    }

    /* renamed from: b */
    public void mo5385b(String str) {
        this.f3898c = str;
    }

    /* renamed from: c */
    public String m4210c() {
        return this.f3897b;
    }

    /* renamed from: d */
    public String m4211d() {
        return this.f3898c;
    }
}
