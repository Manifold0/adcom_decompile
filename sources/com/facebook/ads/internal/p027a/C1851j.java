package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a.j */
public class C1851j extends C1847h {
    /* renamed from: e */
    private static final String f3857e = C1851j.class.getSimpleName();
    /* renamed from: f */
    private final Uri f3858f;
    /* renamed from: g */
    private final Map<String, String> f3859g;

    C1851j(Context context, C2085c c2085c, String str, Uri uri, Map<String, String> map, C1855m c1855m) {
        super(context, c2085c, str, c1855m);
        this.f3858f = uri;
        this.f3859g = map;
    }

    @Nullable
    /* renamed from: b */
    public C1841a mo5377b() {
        try {
            C2615g.m6721a(new C2615g(), this.a, Uri.parse(this.f3858f.getQueryParameter("link")), this.c);
            return null;
        } catch (Throwable e) {
            Log.d(f3857e, "Failed to open link url: " + this.f3858f.toString(), e);
            return C1841a.CANNOT_OPEN;
        }
    }

    /* renamed from: e */
    void mo5378e() {
        m4152a(this.f3859g, mo5377b());
    }
}
