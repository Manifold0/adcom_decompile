package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2089f;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a.k */
class C1852k extends C1842b {
    /* renamed from: d */
    private static final String f3860d = C1852k.class.getSimpleName();
    /* renamed from: e */
    private final Uri f3861e;
    /* renamed from: f */
    private final Map<String, String> f3862f;

    C1852k(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map) {
        super(context, c2085c, str);
        this.f3861e = uri;
        this.f3862f = map;
    }

    /* renamed from: a */
    public void mo5376a() {
        C2089f c2089f = C2089f.IMMEDIATE;
        Object queryParameter = this.f3861e.getQueryParameter("priority");
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                c2089f = C2089f.values()[Integer.valueOf(queryParameter).intValue()];
            } catch (Exception e) {
            }
        }
        this.b.mo5471a(this.c, this.f3862f, this.f3861e.getQueryParameter("type"), c2089f);
    }
}
