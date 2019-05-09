package com.facebook.ads.internal.view.p019c;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p057e.C2238a;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p042l.C2042a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.internal.NativeProtocol;
import com.ironsource.sdk.precache.DownloadManager;
import com.unity.purchasing.googleplay.Consts;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.view.c.a */
public class C2248a extends C2238a {
    /* renamed from: a */
    private static final String f5243a = C2248a.class.getSimpleName();
    /* renamed from: b */
    private final WeakReference<C1898b> f5244b;
    /* renamed from: c */
    private final AtomicBoolean f5245c = new AtomicBoolean();
    /* renamed from: d */
    private final AtomicBoolean f5246d = new AtomicBoolean(true);
    /* renamed from: e */
    private final Path f5247e = new Path();
    /* renamed from: f */
    private final RectF f5248f = new RectF();
    /* renamed from: g */
    private final AtomicInteger f5249g = new AtomicInteger(DownloadManager.OPERATION_TIMEOUT);
    /* renamed from: h */
    private final AtomicReference<String> f5250h = new AtomicReference();
    @Nullable
    /* renamed from: i */
    private WeakReference<C2243d> f5251i;
    @Nullable
    /* renamed from: j */
    private C2630a f5252j;
    /* renamed from: k */
    private C2598w f5253k = new C2598w();
    /* renamed from: l */
    private C1858a f5254l;
    /* renamed from: m */
    private boolean f5255m = true;
    /* renamed from: n */
    private boolean f5256n;
    /* renamed from: o */
    private float f5257o;

    /* renamed from: com.facebook.ads.internal.view.c.a$b */
    public interface C1898b {
        /* renamed from: a */
        void mo5387a();

        /* renamed from: a */
        void mo5388a(int i);

        /* renamed from: a */
        void mo5389a(int i, @Nullable String str);

        /* renamed from: a */
        void mo5390a(String str, Map<String, String> map);

        /* renamed from: b */
        void mo5391b();
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$c */
    public static class C1899c implements C1898b {
        /* renamed from: a */
        public void mo5387a() {
        }

        /* renamed from: a */
        public void mo5388a(int i) {
        }

        /* renamed from: a */
        public void mo5389a(int i, @Nullable String str) {
        }

        /* renamed from: a */
        public void mo5390a(String str, Map<String, String> map) {
        }

        /* renamed from: b */
        public void mo5391b() {
        }
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$1 */
    class C22411 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2248a f5223a;

        C22411(C2248a c2248a) {
            this.f5223a = c2248a;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (this.f5223a.f5255m || !this.f5223a.f5253k.m6673b()) {
                this.f5223a.f5253k.m6670a();
            }
            if (this.f5223a.f5244b.get() != null) {
                ((C1898b) this.f5223a.f5244b.get()).mo5391b();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$a */
    static class C2242a {
        /* renamed from: a */
        private final String f5224a = C2242a.class.getSimpleName();
        /* renamed from: b */
        private final WeakReference<C2248a> f5225b;
        /* renamed from: c */
        private final WeakReference<C1898b> f5226c;
        /* renamed from: d */
        private final WeakReference<C2630a> f5227d;
        /* renamed from: e */
        private final WeakReference<AtomicBoolean> f5228e;
        /* renamed from: f */
        private final WeakReference<AtomicBoolean> f5229f;
        /* renamed from: g */
        private final boolean f5230g;

        C2242a(C2248a c2248a, C1898b c1898b, C2630a c2630a, AtomicBoolean atomicBoolean, AtomicBoolean atomicBoolean2, boolean z) {
            this.f5225b = new WeakReference(c2248a);
            this.f5226c = new WeakReference(c1898b);
            this.f5227d = new WeakReference(c2630a);
            this.f5228e = new WeakReference(atomicBoolean);
            this.f5229f = new WeakReference(atomicBoolean2);
            this.f5230g = z;
        }

        @JavascriptInterface
        public void alert(String str) {
            Log.e(this.f5224a, str);
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return C2581k.m6645a(C2042a.m4944a());
        }

        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (this.f5225b.get() != null && this.f5228e.get() != null && this.f5229f.get() != null && this.f5230g && ((AtomicBoolean) this.f5229f.get()).get()) {
                ((AtomicBoolean) this.f5228e.get()).set(true);
                if (((C2248a) this.f5225b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new C2244e(this.f5227d));
                }
            }
        }

        @JavascriptInterface
        public void onPageInitialized() {
            C2248a c2248a = (C2248a) this.f5225b.get();
            if (c2248a != null && !c2248a.m5725c()) {
                C1898b c1898b = (C1898b) this.f5226c.get();
                if (c1898b != null) {
                    c1898b.mo5387a();
                }
                if (!this.f5230g && ((C2248a) this.f5225b.get()).isShown()) {
                    new Handler(Looper.getMainLooper()).post(new C2244e(this.f5227d));
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$d */
    public interface C2243d {
        /* renamed from: b */
        void mo5588b();
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$e */
    static class C2244e implements Runnable {
        /* renamed from: a */
        private final WeakReference<C2630a> f5231a;

        C2244e(C2630a c2630a) {
            this.f5231a = new WeakReference(c2630a);
        }

        C2244e(WeakReference<C2630a> weakReference) {
            this.f5231a = weakReference;
        }

        public void run() {
            C2630a c2630a = (C2630a) this.f5231a.get();
            if (c2630a != null) {
                c2630a.m6769a();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$f */
    static class C2245f extends WebChromeClient {
        C2245f() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
        }
    }

    /* renamed from: com.facebook.ads.internal.view.c.a$g */
    static class C2247g extends WebViewClient {
        /* renamed from: a */
        private final Context f5233a;
        /* renamed from: b */
        private final WeakReference<C1898b> f5234b;
        /* renamed from: c */
        private final WeakReference<C2630a> f5235c;
        /* renamed from: d */
        private final WeakReference<C2598w> f5236d;
        /* renamed from: e */
        private final WeakReference<AtomicBoolean> f5237e;
        /* renamed from: f */
        private final WeakReference<C2248a> f5238f;
        /* renamed from: g */
        private final AtomicInteger f5239g;
        /* renamed from: h */
        private final AtomicReference<String> f5240h;
        /* renamed from: i */
        private boolean f5241i = false;
        /* renamed from: j */
        private Date f5242j;

        /* renamed from: com.facebook.ads.internal.view.c.a$g$1 */
        class C22461 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C2247g f5232a;

            C22461(C2247g c2247g) {
                this.f5232a = c2247g;
            }

            public void run() {
                if (!this.f5232a.f5241i) {
                    this.f5232a.m5746a(-1, null);
                }
            }
        }

        C2247g(Context context, WeakReference<C1898b> weakReference, WeakReference<C2630a> weakReference2, WeakReference<C2598w> weakReference3, WeakReference<AtomicBoolean> weakReference4, WeakReference<C2248a> weakReference5, AtomicInteger atomicInteger, AtomicReference<String> atomicReference) {
            this.f5233a = context.getApplicationContext();
            this.f5234b = weakReference;
            this.f5235c = weakReference2;
            this.f5236d = weakReference3;
            this.f5237e = weakReference4;
            this.f5238f = weakReference5;
            this.f5239g = atomicInteger;
            this.f5240h = atomicReference;
        }

        /* renamed from: a */
        private void m5746a(int i, @Nullable String str) {
            if (!"net::ERR_EMPTY_RESPONSE".equals(str)) {
                long time = new Date().getTime() - this.f5242j.getTime();
                JSONObject jSONObject = new JSONObject();
                boolean z = str != null;
                try {
                    jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_CODE, i);
                    jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION, str);
                    jSONObject.put("is_web_resource_error", z);
                    jSONObject.put("loading_time_in_millis", time);
                    jSONObject.put(Consts.INAPP_REQUEST_ID, this.f5240h.get());
                } catch (JSONException e) {
                }
                C2625a.m6741b(this.f5233a, "web_view", C2626b.f6514E, new C2066b(AdErrorType.WEB_VIEW_FAILED_TO_LOAD, jSONObject.toString()));
                if (this.f5234b.get() != null) {
                    ((C1898b) this.f5234b.get()).mo5389a(i, str);
                }
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!(this.f5238f.get() == null || this.f5237e.get() == null || ((AtomicBoolean) this.f5237e.get()).get())) {
                C2248a.m5752d((C2248a) this.f5238f.get());
            }
            this.f5241i = true;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            this.f5242j = new Date();
            new Handler().postDelayed(new C22461(this), (long) this.f5239g.get());
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.f5241i = true;
            m5746a(i, str);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            this.f5241i = true;
            if (VERSION.SDK_INT >= 23) {
                m5746a(webResourceError.getErrorCode(), webResourceError.getDescription().toString());
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Map hashMap = new HashMap();
            if (this.f5235c.get() != null) {
                ((C2630a) this.f5235c.get()).m6771a(hashMap);
            }
            if (this.f5236d.get() != null) {
                hashMap.put("touch", C2581k.m6645a(((C2598w) this.f5236d.get()).m6676e()));
            }
            if (this.f5234b.get() != null) {
                ((C1898b) this.f5234b.get()).mo5390a(str, hashMap);
            }
            return true;
        }
    }

    public C2248a(Context context, WeakReference<C1898b> weakReference, int i) {
        super(context);
        this.f5256n = C2078a.m5075N(context);
        this.f5244b = weakReference;
        this.f5254l = new C22411(this);
        this.f5252j = new C2630a(this, i, this.f5254l);
        setWebChromeClient(mo5552a());
        setWebViewClient(mo5553b());
        getSettings().setSupportZoom(false);
        getSettings().setCacheMode(1);
        addJavascriptInterface(new C2242a(this, (C1898b) weakReference.get(), this.f5252j, this.f5245c, this.f5246d, this.f5256n), "AdControl");
    }

    /* renamed from: d */
    static /* synthetic */ void m5752d(C2248a c2248a) {
        c2248a.f5245c.set(true);
        new Handler(Looper.getMainLooper()).post(new C2244e(c2248a.f5252j));
        if (c2248a.f5251i != null && c2248a.f5251i.get() != null) {
            ((C2243d) c2248a.f5251i.get()).mo5588b();
        }
    }

    /* renamed from: a */
    protected WebChromeClient mo5552a() {
        return new C2245f();
    }

    /* renamed from: a */
    public void m5754a(int i, int i2) {
        if (this.f5252j != null) {
            this.f5252j.m6770a(i);
            this.f5252j.m6772b(i2);
        }
    }

    /* renamed from: b */
    protected WebViewClient mo5553b() {
        return new C2247g(getContext(), this.f5244b, new WeakReference(this.f5252j), new WeakReference(this.f5253k), new WeakReference(this.f5246d), new WeakReference(this), this.f5249g, this.f5250h);
    }

    public void destroy() {
        if (this.f5252j != null) {
            this.f5252j.m6774c();
            this.f5252j = null;
        }
        C2600x.m6689b(this);
        this.f5254l = null;
        this.f5253k = null;
        C2610b.m6708a(this);
        super.destroy();
    }

    public Map<String, String> getTouchData() {
        return this.f5253k.m6676e();
    }

    public C2598w getTouchDataRecorder() {
        return this.f5253k;
    }

    public C2630a getViewabilityChecker() {
        return this.f5252j;
    }

    protected void onDraw(Canvas canvas) {
        if (this.f5257o > 0.0f) {
            this.f5248f.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
            this.f5247e.reset();
            this.f5247e.addRoundRect(this.f5248f, this.f5257o, this.f5257o, Direction.CW);
            canvas.clipPath(this.f5247e);
        }
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f5253k.m6671a(motionEvent, this, this);
        return super.onTouchEvent(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.f5244b.get() != null) {
            ((C1898b) this.f5244b.get()).mo5388a(i);
        }
        if (this.f5252j != null) {
            if (i == 0) {
                Object obj = (!this.f5256n || this.f5245c.get()) ? 1 : null;
                if (obj != null) {
                    this.f5252j.m6769a();
                    return;
                }
            }
            if (i == 8) {
                this.f5252j.m6774c();
            }
        }
    }

    public void setCheckAssetsByJavascriptBridge(boolean z) {
        this.f5246d.set(z);
    }

    public void setCornerRadius(float f) {
        this.f5257o = f;
        invalidate();
    }

    public void setLogMultipleImpressions(boolean z) {
        this.f5255m = z;
    }

    public void setOnAssetsLoadedListener(C2243d c2243d) {
        this.f5251i = new WeakReference(c2243d);
    }

    public void setRequestId(String str) {
        this.f5250h.set(str);
    }

    public void setWaitForAssetsToLoad(boolean z) {
        this.f5256n = z;
    }

    public void setWebViewTimeoutInMillis(int i) {
        if (i >= 0) {
            this.f5249g.set(i);
        }
    }
}
