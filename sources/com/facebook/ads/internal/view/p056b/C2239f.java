package com.facebook.ads.internal.view.p056b;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.adjust.sdk.Constants;
import com.facebook.ads.internal.p025w.p057e.C2238a;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

@TargetApi(19)
/* renamed from: com.facebook.ads.internal.view.b.f */
public class C2239f extends C2238a {
    /* renamed from: a */
    private static final String f5202a = C2239f.class.getSimpleName();
    /* renamed from: b */
    private static final Set<String> f5203b = new HashSet(2);
    /* renamed from: c */
    private C2224a f5204c;
    /* renamed from: d */
    private C2234d f5205d;
    /* renamed from: e */
    private long f5206e = -1;
    /* renamed from: f */
    private long f5207f = -1;
    /* renamed from: g */
    private long f5208g = -1;
    /* renamed from: h */
    private long f5209h = -1;

    /* renamed from: com.facebook.ads.internal.view.b.f$a */
    public interface C2224a {
        /* renamed from: a */
        void mo5548a(int i);

        /* renamed from: a */
        void mo5549a(String str);

        /* renamed from: b */
        void mo5550b(String str);

        /* renamed from: c */
        void mo5551c(String str);
    }

    /* renamed from: com.facebook.ads.internal.view.b.f$b */
    static class C2236b extends WebChromeClient {
        /* renamed from: a */
        private final WeakReference<C2224a> f5196a;
        /* renamed from: b */
        private final WeakReference<C2234d> f5197b;

        C2236b(WeakReference<C2224a> weakReference, WeakReference<C2234d> weakReference2) {
            this.f5196a = weakReference;
            this.f5197b = weakReference2;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            if (!(TextUtils.isEmpty(message) || consoleMessage.messageLevel() != MessageLevel.LOG || this.f5197b.get() == null)) {
                ((C2234d) this.f5197b.get()).m5721a(message);
            }
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            if (this.f5197b.get() != null) {
                ((C2234d) this.f5197b.get()).m5720a();
            }
            if (this.f5196a.get() != null) {
                ((C2224a) this.f5196a.get()).mo5548a(i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (this.f5196a.get() != null) {
                ((C2224a) this.f5196a.get()).mo5550b(str);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.b.f$c */
    static class C2237c extends WebViewClient {
        /* renamed from: a */
        private final WeakReference<C2224a> f5198a;
        /* renamed from: b */
        private final WeakReference<Context> f5199b;

        C2237c(WeakReference<C2224a> weakReference, WeakReference<Context> weakReference2) {
            this.f5198a = weakReference;
            this.f5199b = weakReference2;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.f5198a.get() != null) {
                ((C2224a) this.f5198a.get()).mo5551c(str);
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.f5198a.get() != null) {
                ((C2224a) this.f5198a.get()).mo5549a(str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (!(C2239f.f5203b.contains(parse.getScheme()) || this.f5199b.get() == null)) {
                try {
                    ((Context) this.f5199b.get()).startActivity(new Intent("android.intent.action.VIEW", parse));
                    return true;
                } catch (Throwable e) {
                    Log.w(C2239f.f5202a, "Activity not found to handle URI.", e);
                } catch (Throwable e2) {
                    Log.e(C2239f.f5202a, "Unknown exception occurred when trying to handle URI.", e2);
                }
            }
            return false;
        }
    }

    static {
        f5203b.add("http");
        f5203b.add(Constants.SCHEME);
    }

    public C2239f(Context context) {
        super(context);
        m5728f();
    }

    public C2239f(Context context, C2224a c2224a) {
        super(context);
        this.f5204c = c2224a;
        setWebChromeClient(mo5552a());
        setWebViewClient(mo5553b());
        m5728f();
    }

    /* renamed from: f */
    private void m5728f() {
        WebSettings settings = getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.f5205d = new C2234d(this);
    }

    /* renamed from: g */
    private void m5729g() {
        if (this.f5207f > -1 && this.f5208g > -1 && this.f5209h > -1) {
            this.f5205d.m5722a(false);
        }
    }

    /* renamed from: a */
    protected WebChromeClient mo5552a() {
        return new C2236b(new WeakReference(this.f5204c), new WeakReference(this.f5205d));
    }

    /* renamed from: a */
    public void m5731a(long j) {
        if (this.f5206e < 0) {
            this.f5206e = j;
        }
    }

    /* renamed from: a */
    public void m5732a(String str) {
        try {
            evaluateJavascript(str, null);
        } catch (IllegalStateException e) {
            loadUrl("javascript:" + str);
        }
    }

    /* renamed from: b */
    protected WebViewClient mo5553b() {
        return new C2237c(new WeakReference(this.f5204c), new WeakReference(getContext()));
    }

    /* renamed from: b */
    public void m5734b(long j) {
        if (this.f5207f < 0) {
            this.f5207f = j;
        }
        m5729g();
    }

    /* renamed from: c */
    public void m5735c(long j) {
        if (this.f5209h < 0) {
            this.f5209h = j;
        }
        m5729g();
    }

    public void destroy() {
        this.f5204c = null;
        C2610b.m6708a(this);
        super.destroy();
    }

    public long getDomContentLoadedMs() {
        return this.f5207f;
    }

    public String getFirstUrl() {
        WebBackForwardList copyBackForwardList = copyBackForwardList();
        return copyBackForwardList.getSize() > 0 ? copyBackForwardList.getItemAtIndex(0).getUrl() : getUrl();
    }

    public long getLoadFinishMs() {
        return this.f5209h;
    }

    public long getResponseEndMs() {
        return this.f5206e;
    }

    public long getScrollReadyMs() {
        return this.f5208g;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f5208g < 0 && computeVerticalScrollRange() > getHeight()) {
            this.f5208g = System.currentTimeMillis();
            m5729g();
        }
    }

    public void setListener(C2224a c2224a) {
        this.f5204c = c2224a;
    }
}
