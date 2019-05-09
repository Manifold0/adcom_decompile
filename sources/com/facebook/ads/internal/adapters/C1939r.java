package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.adapters.r */
public class C1939r extends C1888b {
    /* renamed from: c */
    private final C2085c f4238c;
    /* renamed from: d */
    private final C2598w f4239d;
    /* renamed from: e */
    private C1887q f4240e;

    public C1939r(Context context, C2085c c2085c, C2630a c2630a, C2598w c2598w, C1895c c1895c) {
        super(context, c1895c, c2630a);
        this.f4238c = c2085c;
        this.f4239d = c2598w;
    }

    /* renamed from: a */
    public void m4584a(C1887q c1887q) {
        this.f4240e = c1887q;
    }

    /* renamed from: a */
    protected void mo5414a(Map<String, String> map) {
        if (this.f4240e != null && !TextUtils.isEmpty(this.f4240e.mo5384a())) {
            map.put("touch", C2581k.m6645a(this.f4239d.m6676e()));
            this.f4238c.mo5470a(this.f4240e.mo5384a(), map);
        }
    }
}
