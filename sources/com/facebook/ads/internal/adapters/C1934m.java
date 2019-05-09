package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p025w.p057e.C2238a;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.adapters.m */
public class C1934m extends C1888b {
    /* renamed from: c */
    private static final String f4206c = C1934m.class.getSimpleName();
    /* renamed from: d */
    private final C2238a f4207d;
    /* renamed from: e */
    private final C2085c f4208e;
    /* renamed from: f */
    private C1932l f4209f;
    /* renamed from: g */
    private boolean f4210g;

    /* renamed from: com.facebook.ads.internal.adapters.m$1 */
    class C19331 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1934m f4205a;

        C19331(C1934m c1934m) {
            this.f4205a = c1934m;
        }

        public void run() {
            if (this.f4205a.f4207d.m5725c()) {
                Log.w(C1934m.f4206c, "Webview already destroyed, cannot activate");
            } else {
                this.f4205a.f4207d.loadUrl("javascript:" + this.f4205a.f4209f.m4555d());
            }
        }
    }

    public C1934m(Context context, C2085c c2085c, C2238a c2238a, C2630a c2630a, C1895c c1895c) {
        super(context, c1895c, c2630a);
        this.f4208e = c2085c;
        this.f4207d = c2238a;
    }

    /* renamed from: a */
    public void m4563a(C1932l c1932l) {
        this.f4209f = c1932l;
    }

    /* renamed from: a */
    protected void mo5414a(Map<String, String> map) {
        if (this.f4209f != null && !TextUtils.isEmpty(this.f4209f.getClientToken())) {
            this.f4208e.mo5470a(this.f4209f.getClientToken(), map);
        }
    }

    /* renamed from: b */
    public synchronized void m4565b() {
        if (!(this.f4210g || this.f4209f == null)) {
            this.f4210g = true;
            if (!(this.f4207d == null || TextUtils.isEmpty(this.f4209f.m4555d()))) {
                this.f4207d.post(new C19331(this));
            }
        }
    }
}
