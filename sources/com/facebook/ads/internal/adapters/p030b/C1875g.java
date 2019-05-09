package com.facebook.ads.internal.adapters.p030b;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.g */
public class C1875g implements Serializable {
    private static final long serialVersionUID = 4559450202335985006L;
    /* renamed from: a */
    private final String f3948a;
    /* renamed from: b */
    private final String f3949b;

    private C1875g() {
        this.f3948a = "";
        this.f3949b = "";
    }

    private C1875g(String str, String str2) {
        this.f3948a = str;
        this.f3949b = str2;
    }

    /* renamed from: a */
    static C1875g m4282a(@Nullable JSONObject jSONObject) {
        return jSONObject == null ? new C1875g() : new C1875g(jSONObject.optString("timer_text"), jSONObject.optString("title_text"));
    }

    /* renamed from: a */
    public String m4283a() {
        return this.f3949b;
    }

    /* renamed from: a */
    public String m4284a(String str) {
        return TextUtils.isEmpty(str) ? this.f3948a : this.f3948a.replace("[fb_sec]", str);
    }
}
