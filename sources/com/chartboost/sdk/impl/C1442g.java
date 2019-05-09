package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.aj.C1406a;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.impl.g */
public class C1442g implements C1406a {
    /* renamed from: a */
    private final C1440e f3277a;
    /* renamed from: b */
    private final String f3278b;

    public C1442g(C1440e c1440e, String str) {
        this.f3277a = c1440e;
        this.f3278b = str;
    }

    /* renamed from: a */
    public void mo4278a(aj ajVar, JSONObject jSONObject) {
        if (this.f3277a.f3238f.f3216h || C1410i.f2943t) {
            synchronized (this.f3277a) {
                this.f3277a.m3583b(this.f3278b);
            }
        }
    }

    /* renamed from: a */
    public void mo4277a(aj ajVar, CBError cBError) {
        if (this.f3277a.f3238f.f3216h) {
            synchronized (this.f3277a) {
                this.f3277a.m3583b(this.f3278b);
            }
        }
    }
}
