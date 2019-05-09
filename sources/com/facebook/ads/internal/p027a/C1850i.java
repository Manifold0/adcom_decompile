package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.net.Uri;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a.i */
public class C1850i extends C1847h {
    /* renamed from: e */
    private final Uri f3855e;
    /* renamed from: f */
    private Map<String, String> f3856f;

    C1850i(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map) {
        super(context, c2085c, str, null);
        this.f3855e = uri;
        this.f3856f = map;
    }

    /* renamed from: a */
    public void m4166a(Map<String, String> map) {
        this.f3856f.putAll(map);
    }

    /* renamed from: c */
    public Uri m4167c() {
        return Uri.parse(this.f3855e.getQueryParameter("link"));
    }

    /* renamed from: e */
    void mo5378e() {
        m4152a(this.f3856f, null);
    }
}
