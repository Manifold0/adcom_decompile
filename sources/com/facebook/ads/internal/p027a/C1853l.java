package com.facebook.ads.internal.p027a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p051s.C2085c;

/* renamed from: com.facebook.ads.internal.a.l */
public class C1853l extends C1842b {
    /* renamed from: d */
    private static final String f3863d = C1853l.class.getSimpleName();
    /* renamed from: e */
    private final Uri f3864e;

    public C1853l(Context context, C2085c c2085c, String str, Uri uri) {
        super(context, c2085c, str);
        this.f3864e = uri;
    }

    /* renamed from: a */
    public void mo5376a() {
        try {
            Log.w("REDIRECTACTION: ", this.f3864e.toString());
            C2615g.m6721a(new C2615g(), this.a, this.f3864e, this.c);
        } catch (Throwable e) {
            Log.d(f3863d, "Failed to open link url: " + this.f3864e.toString(), e);
        }
    }
}
