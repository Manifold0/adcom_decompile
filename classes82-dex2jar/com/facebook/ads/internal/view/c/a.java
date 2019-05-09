// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.c;

import java.util.HashMap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.os.Build$VERSION;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.graphics.Bitmap;
import org.json.JSONException;
import com.facebook.ads.internal.protocol.AdErrorType;
import org.json.JSONObject;
import java.util.Date;
import android.webkit.ConsoleMessage;
import com.facebook.ads.internal.w.b.k;
import android.webkit.JavascriptInterface;
import android.util.Log;
import android.view.MotionEvent;
import android.graphics.Path$Direction;
import android.graphics.Canvas;
import java.util.Map;
import android.webkit.WebView;
import com.facebook.ads.internal.w.e.b;
import com.facebook.ads.internal.w.b.x;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.w;
import android.support.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicInteger;
import android.graphics.RectF;
import android.graphics.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.ref.WeakReference;

public class a extends a
{
    private static final String a;
    private final WeakReference<b> b;
    private final AtomicBoolean c;
    private final AtomicBoolean d;
    private final Path e;
    private final RectF f;
    private final AtomicInteger g;
    private final AtomicReference<String> h;
    @Nullable
    private WeakReference<d> i;
    @Nullable
    private com.facebook.ads.internal.x.a j;
    private w k;
    private a.a l;
    private boolean m;
    private boolean n;
    private float o;
    
    static {
        a = a.class.getSimpleName();
    }
    
    public a(final Context context, final WeakReference<b> b, final int n) {
        super(context);
        this.c = new AtomicBoolean();
        this.d = new AtomicBoolean(true);
        this.e = new Path();
        this.f = new RectF();
        this.g = new AtomicInteger(5000);
        this.h = new AtomicReference<String>();
        this.k = new w();
        this.m = true;
        this.n = com.facebook.ads.internal.r.a.N(context);
        this.b = b;
        this.l = new a.a() {
            @Override
            public void a() {
                if (com.facebook.ads.internal.view.c.a.this.m || !com.facebook.ads.internal.view.c.a.this.k.b()) {
                    com.facebook.ads.internal.view.c.a.this.k.a();
                }
                if (com.facebook.ads.internal.view.c.a.this.b.get() != null) {
                    ((b)com.facebook.ads.internal.view.c.a.this.b.get()).b();
                }
            }
        };
        this.j = new com.facebook.ads.internal.x.a((View)this, n, this.l);
        this.setWebChromeClient(this.a());
        this.setWebViewClient(this.b());
        this.getSettings().setSupportZoom(false);
        this.getSettings().setCacheMode(1);
        this.addJavascriptInterface((Object)new a(this, (b)b.get(), this.j, this.c, this.d, this.n), "AdControl");
    }
    
    static /* synthetic */ void d(final a a) {
        a.c.set(true);
        new Handler(Looper.getMainLooper()).post((Runnable)new e(a.j));
        if (a.i != null && a.i.get() != null) {
            a.i.get().b();
        }
    }
    
    @Override
    protected WebChromeClient a() {
        return new f();
    }
    
    public void a(final int n, final int n2) {
        if (this.j != null) {
            this.j.a(n);
            this.j.b(n2);
        }
    }
    
    @Override
    protected WebViewClient b() {
        return new g(this.getContext(), this.b, new WeakReference<com.facebook.ads.internal.x.a>(this.j), new WeakReference<w>(this.k), new WeakReference<AtomicBoolean>(this.d), new WeakReference<a>(this), this.g, this.h);
    }
    
    @Override
    public void destroy() {
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
        x.b((View)this);
        this.l = null;
        this.k = null;
        com.facebook.ads.internal.w.e.b.a(this);
        super.destroy();
    }
    
    public Map<String, String> getTouchData() {
        return this.k.e();
    }
    
    public w getTouchDataRecorder() {
        return this.k;
    }
    
    public com.facebook.ads.internal.x.a getViewabilityChecker() {
        return this.j;
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.o > 0.0f) {
            this.f.set(0.0f, 0.0f, (float)this.getWidth(), (float)this.getHeight());
            this.e.reset();
            this.e.addRoundRect(this.f, this.o, this.o, Path$Direction.CW);
            canvas.clipPath(this.e);
        }
        super.onDraw(canvas);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.k.a(motionEvent, (View)this, (View)this);
        return super.onTouchEvent(motionEvent);
    }
    
    protected void onWindowVisibilityChanged(final int n) {
        super.onWindowVisibilityChanged(n);
        if (this.b.get() != null) {
            this.b.get().a(n);
        }
        if (this.j != null) {
            if (n == 0) {
                int n2;
                if (!this.n || this.c.get()) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                if (n2 != 0) {
                    this.j.a();
                    return;
                }
            }
            if (n == 8) {
                this.j.c();
            }
        }
    }
    
    public void setCheckAssetsByJavascriptBridge(final boolean b) {
        this.d.set(b);
    }
    
    public void setCornerRadius(final float o) {
        this.o = o;
        this.invalidate();
    }
    
    public void setLogMultipleImpressions(final boolean m) {
        this.m = m;
    }
    
    public void setOnAssetsLoadedListener(final d d) {
        this.i = new WeakReference<d>(d);
    }
    
    public void setRequestId(final String s) {
        this.h.set(s);
    }
    
    public void setWaitForAssetsToLoad(final boolean n) {
        this.n = n;
    }
    
    public void setWebViewTimeoutInMillis(final int n) {
        if (n >= 0) {
            this.g.set(n);
        }
    }
    
    static class a
    {
        private final String a;
        private final WeakReference<com.facebook.ads.internal.view.c.a> b;
        private final WeakReference<b> c;
        private final WeakReference<com.facebook.ads.internal.x.a> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<AtomicBoolean> f;
        private final boolean g;
        
        a(final com.facebook.ads.internal.view.c.a a, final b b, final com.facebook.ads.internal.x.a a2, final AtomicBoolean atomicBoolean, final AtomicBoolean atomicBoolean2, final boolean g) {
            this.a = a.class.getSimpleName();
            this.b = new WeakReference<com.facebook.ads.internal.view.c.a>(a);
            this.c = new WeakReference<b>(b);
            this.d = new WeakReference<com.facebook.ads.internal.x.a>(a2);
            this.e = new WeakReference<AtomicBoolean>(atomicBoolean);
            this.f = new WeakReference<AtomicBoolean>(atomicBoolean2);
            this.g = g;
        }
        
        @JavascriptInterface
        public void alert(final String s) {
            Log.e(this.a, s);
        }
        
        @JavascriptInterface
        public String getAnalogInfo() {
            return com.facebook.ads.internal.w.b.k.a(com.facebook.ads.internal.l.a.a());
        }
        
        @JavascriptInterface
        public void onMainAssetLoaded() {
            if (this.b.get() != null && this.e.get() != null && this.f.get() != null && this.g && this.f.get().get()) {
                this.e.get().set(true);
                if (this.b.get().isShown()) {
                    new Handler(Looper.getMainLooper()).post((Runnable)new e(this.d));
                }
            }
        }
        
        @JavascriptInterface
        public void onPageInitialized() {
            final com.facebook.ads.internal.view.c.a a = this.b.get();
            if (a != null && !a.c()) {
                final b b = this.c.get();
                if (b != null) {
                    b.a();
                }
                if (!this.g && this.b.get().isShown()) {
                    new Handler(Looper.getMainLooper()).post((Runnable)new e(this.d));
                }
            }
        }
    }
    
    public interface b
    {
        void a();
        
        void a(final int p0);
        
        void a(final int p0, @Nullable final String p1);
        
        void a(final String p0, final Map<String, String> p1);
        
        void b();
    }
    
    public static class c implements b
    {
        @Override
        public void a() {
        }
        
        @Override
        public void a(final int n) {
        }
        
        @Override
        public void a(final int n, @Nullable final String s) {
        }
        
        @Override
        public void a(final String s, final Map<String, String> map) {
        }
        
        @Override
        public void b() {
        }
    }
    
    public interface d
    {
        void b();
    }
    
    static class e implements Runnable
    {
        private final WeakReference<com.facebook.ads.internal.x.a> a;
        
        e(final com.facebook.ads.internal.x.a a) {
            this.a = new WeakReference<com.facebook.ads.internal.x.a>(a);
        }
        
        e(final WeakReference<com.facebook.ads.internal.x.a> a) {
            this.a = a;
        }
        
        @Override
        public void run() {
            final com.facebook.ads.internal.x.a a = this.a.get();
            if (a != null) {
                a.a();
            }
        }
    }
    
    static class f extends WebChromeClient
    {
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            return true;
        }
        
        public void onProgressChanged(final WebView webView, final int n) {
        }
    }
    
    static class g extends WebViewClient
    {
        private final Context a;
        private final WeakReference<b> b;
        private final WeakReference<com.facebook.ads.internal.x.a> c;
        private final WeakReference<w> d;
        private final WeakReference<AtomicBoolean> e;
        private final WeakReference<a> f;
        private final AtomicInteger g;
        private final AtomicReference<String> h;
        private boolean i;
        private Date j;
        
        g(final Context context, final WeakReference<b> b, final WeakReference<com.facebook.ads.internal.x.a> c, final WeakReference<w> d, final WeakReference<AtomicBoolean> e, final WeakReference<a> f, final AtomicInteger g, final AtomicReference<String> h) {
            this.i = false;
            this.a = context.getApplicationContext();
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            this.g = g;
            this.h = h;
        }
        
        private void a(final int n, @Nullable final String s) {
            if (!"net::ERR_EMPTY_RESPONSE".equals(s)) {
                final long time = new Date().getTime();
                final long time2 = this.j.getTime();
                final JSONObject jsonObject = new JSONObject();
                Label_0156: {
                    if (s == null) {
                        break Label_0156;
                    }
                    boolean b = true;
                    while (true) {
                        try {
                            while (true) {
                                jsonObject.put("error_code", n);
                                jsonObject.put("error_description", (Object)s);
                                jsonObject.put("is_web_resource_error", b);
                                jsonObject.put("loading_time_in_millis", time - time2);
                                jsonObject.put("request_id", (Object)this.h.get());
                                com.facebook.ads.internal.w.h.a.b(this.a, "web_view", com.facebook.ads.internal.w.h.b.E, new com.facebook.ads.internal.protocol.b(AdErrorType.WEB_VIEW_FAILED_TO_LOAD, jsonObject.toString()));
                                if (this.b.get() != null) {
                                    ((b)this.b.get()).a(n, s);
                                    return;
                                }
                                return;
                                b = false;
                                continue;
                            }
                        }
                        catch (JSONException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            if (this.f.get() != null && this.e.get() != null && !this.e.get().get()) {
                com.facebook.ads.internal.view.c.a.d(this.f.get());
            }
            this.i = true;
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            this.j = new Date();
            new Handler().postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (!com.facebook.ads.internal.view.c.a.g.this.i) {
                        com.facebook.ads.internal.view.c.a.g.this.a(-1, null);
                    }
                }
            }, (long)this.g.get());
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            this.i = true;
            this.a(n, s);
        }
        
        public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
            this.i = true;
            if (Build$VERSION.SDK_INT >= 23) {
                this.a(webResourceError.getErrorCode(), webResourceError.getDescription().toString());
            }
        }
        
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            sslErrorHandler.cancel();
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            if (this.c.get() != null) {
                this.c.get().a(hashMap);
            }
            if (this.d.get() != null) {
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.d.get().e()));
            }
            if (this.b.get() != null) {
                ((b)this.b.get()).a(s, hashMap);
            }
            return true;
        }
    }
}
