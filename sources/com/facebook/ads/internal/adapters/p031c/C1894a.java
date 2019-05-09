package com.facebook.ads.internal.adapters.p031c;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.adapters.p030b.C1886p;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p050r.C2078a;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.adapters.c.a */
public final class C1894a {

    /* renamed from: com.facebook.ads.internal.adapters.c.a$a */
    private static class C1890a implements C1832a {
        /* renamed from: a */
        final Context f4028a;
        /* renamed from: b */
        final WeakReference<C1891b> f4029b;
        /* renamed from: c */
        final C2011b f4030c;
        /* renamed from: d */
        final C1884n f4031d;
        /* renamed from: e */
        final boolean f4032e;

        private C1890a(Context context, C1891b c1891b, C2011b c2011b, C1884n c1884n, boolean z) {
            this.f4028a = context;
            this.f4029b = new WeakReference(c1891b);
            this.f4030c = c2011b;
            this.f4031d = c1884n;
            this.f4032e = z;
        }

        /* renamed from: a */
        private void m4371a(boolean z) {
            if (this.f4029b.get() != null) {
                if (this.f4031d.m4344k() == C1886p.WEBVIEW_PRECACHE) {
                    WebView webView = new WebView(this.f4028a);
                    webView.getSettings().setCacheMode(1);
                    webView.setWebViewClient(new C1893c(this.f4031d, this.f4029b, this.f4032e));
                    webView.loadUrl(this.f4031d.m4333a());
                    return;
                }
                String a = this.f4031d.m4333a();
                if (z) {
                    a = this.f4031d.m4344k() == C1886p.FILE_PRECACHE ? this.f4030c.m4847d(this.f4031d.m4333a()) : this.f4030c.m4846c(this.f4031d.m4333a());
                }
                this.f4031d.m4334a(a);
                ((C1891b) this.f4029b.get()).mo5399a();
            }
        }

        /* renamed from: a */
        public void mo5368a() {
            m4371a(true);
        }

        /* renamed from: b */
        public void mo5369b() {
            if (this.f4029b.get() != null) {
                if (this.f4032e) {
                    ((C1891b) this.f4029b.get()).mo5400a(AdError.CACHE_ERROR);
                } else {
                    m4371a(false);
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.c.a$b */
    public interface C1891b {
        /* renamed from: a */
        void mo5399a();

        /* renamed from: a */
        void mo5400a(AdError adError);
    }

    /* renamed from: com.facebook.ads.internal.adapters.c.a$c */
    private static class C1893c extends WebViewClient {
        /* renamed from: a */
        boolean f4034a = false;
        /* renamed from: b */
        final C1884n f4035b;
        /* renamed from: c */
        final WeakReference<C1891b> f4036c;
        /* renamed from: d */
        final boolean f4037d;

        /* renamed from: com.facebook.ads.internal.adapters.c.a$c$1 */
        class C18921 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C1893c f4033a;

            C18921(C1893c c1893c) {
                this.f4033a = c1893c;
            }

            public void run() {
                if (!this.f4033a.f4034a) {
                    this.f4033a.m4377a(null);
                }
            }
        }

        C1893c(C1884n c1884n, WeakReference<C1891b> weakReference, boolean z) {
            this.f4035b = c1884n;
            this.f4036c = weakReference;
            this.f4037d = z;
        }

        /* renamed from: a */
        private void m4376a() {
            if (this.f4036c.get() != null) {
                ((C1891b) this.f4036c.get()).mo5399a();
            }
        }

        /* renamed from: a */
        private void m4377a(WebResourceError webResourceError) {
            if (this.f4036c.get() != null) {
                if (this.f4037d) {
                    ((C1891b) this.f4036c.get()).mo5400a(AdError.CACHE_ERROR);
                } else {
                    m4376a();
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            this.f4034a = true;
            m4376a();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            new Handler().postDelayed(new C18921(this), (long) this.f4035b.m4340g());
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.f4034a = true;
            m4377a(webResourceError);
        }
    }

    /* renamed from: a */
    public static void m4379a(Context context, C1885o c1885o, boolean z, C1891b c1891b) {
        if (C2078a.m5104n(context)) {
            c1891b.mo5399a();
            return;
        }
        C1884n j = c1885o.m4352f().m4248j();
        C2011b c2011b = new C2011b(context);
        if (j == null) {
            c1891b.mo5400a(AdError.CACHE_ERROR);
            return;
        }
        switch (j.m4344k()) {
            case PROXY_PRECACHE:
                c2011b.m4843a(j.m4333a());
                break;
            case FILE_PRECACHE:
                c2011b.m4845b(j.m4333a());
                break;
        }
        c2011b.m4844a(c1885o.m4348b().m4329b(), -1, -1);
        c2011b.m4844a(j.m4335b(), -1, -1);
        c2011b.m4842a(new C1890a(context, c1891b, c2011b, j, z));
    }
}
