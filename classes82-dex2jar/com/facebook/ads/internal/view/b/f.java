// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import android.content.ActivityNotFoundException;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Bitmap;
import android.webkit.ConsoleMessage$MessageLevel;
import android.text.TextUtils;
import android.webkit.ConsoleMessage;
import android.graphics.Canvas;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import com.facebook.ads.internal.w.e.b;
import android.webkit.WebViewClient;
import android.webkit.ValueCallback;
import java.lang.ref.WeakReference;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.content.Context;
import java.util.HashSet;
import java.util.Set;
import android.annotation.TargetApi;
import com.facebook.ads.internal.w.e.a;

@TargetApi(19)
public class f extends com.facebook.ads.internal.w.e.a
{
    private static final String a;
    private static final Set<String> b;
    private a c;
    private d d;
    private long e;
    private long f;
    private long g;
    private long h;
    
    static {
        a = f.class.getSimpleName();
        (b = new HashSet<String>(2)).add("http");
        f.b.add("https");
    }
    
    public f(final Context context) {
        super(context);
        this.e = -1L;
        this.f = -1L;
        this.g = -1L;
        this.h = -1L;
        this.f();
    }
    
    public f(final Context context, final a c) {
        super(context);
        this.e = -1L;
        this.f = -1L;
        this.g = -1L;
        this.h = -1L;
        this.c = c;
        this.setWebChromeClient(this.a());
        this.setWebViewClient(this.b());
        this.f();
    }
    
    private void f() {
        final WebSettings settings = this.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setAllowFileAccess(false);
        this.d = new d(this);
    }
    
    private void g() {
        if (this.f > -1L && this.g > -1L && this.h > -1L) {
            this.d.a(false);
        }
    }
    
    @Override
    protected WebChromeClient a() {
        return new b(new WeakReference<a>(this.c), new WeakReference<d>(this.d));
    }
    
    public void a(final long e) {
        if (this.e < 0L) {
            this.e = e;
        }
    }
    
    public void a(final String s) {
        try {
            this.evaluateJavascript(s, (ValueCallback)null);
        }
        catch (IllegalStateException ex) {
            this.loadUrl("javascript:" + s);
        }
    }
    
    @Override
    protected WebViewClient b() {
        return new c(new WeakReference<a>(this.c), new WeakReference<Context>(this.getContext()));
    }
    
    public void b(final long f) {
        if (this.f < 0L) {
            this.f = f;
        }
        this.g();
    }
    
    public void c(final long h) {
        if (this.h < 0L) {
            this.h = h;
        }
        this.g();
    }
    
    @Override
    public void destroy() {
        this.c = null;
        com.facebook.ads.internal.w.e.b.a(this);
        super.destroy();
    }
    
    public long getDomContentLoadedMs() {
        return this.f;
    }
    
    public String getFirstUrl() {
        final WebBackForwardList copyBackForwardList = this.copyBackForwardList();
        if (copyBackForwardList.getSize() > 0) {
            return copyBackForwardList.getItemAtIndex(0).getUrl();
        }
        return this.getUrl();
    }
    
    public long getLoadFinishMs() {
        return this.h;
    }
    
    public long getResponseEndMs() {
        return this.e;
    }
    
    public long getScrollReadyMs() {
        return this.g;
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.g < 0L && this.computeVerticalScrollRange() > this.getHeight()) {
            this.g = System.currentTimeMillis();
            this.g();
        }
    }
    
    public void setListener(final a c) {
        this.c = c;
    }
    
    public interface a
    {
        void a(final int p0);
        
        void a(final String p0);
        
        void b(final String p0);
        
        void c(final String p0);
    }
    
    static class b extends WebChromeClient
    {
        private final WeakReference<a> a;
        private final WeakReference<d> b;
        
        b(final WeakReference<a> a, final WeakReference<d> b) {
            this.a = a;
            this.b = b;
        }
        
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            final String message = consoleMessage.message();
            if (!TextUtils.isEmpty((CharSequence)message) && consoleMessage.messageLevel() == ConsoleMessage$MessageLevel.LOG && this.b.get() != null) {
                this.b.get().a(message);
            }
            return true;
        }
        
        public void onProgressChanged(final WebView webView, final int n) {
            super.onProgressChanged(webView, n);
            if (this.b.get() != null) {
                this.b.get().a();
            }
            if (this.a.get() != null) {
                ((a)this.a.get()).a(n);
            }
        }
        
        public void onReceivedTitle(final WebView webView, final String s) {
            super.onReceivedTitle(webView, s);
            if (this.a.get() != null) {
                ((a)this.a.get()).b(s);
            }
        }
    }
    
    static class c extends WebViewClient
    {
        private final WeakReference<a> a;
        private final WeakReference<Context> b;
        
        c(final WeakReference<a> a, final WeakReference<Context> b) {
            this.a = a;
            this.b = b;
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            if (this.a.get() != null) {
                ((a)this.a.get()).c(s);
            }
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
            if (this.a.get() != null) {
                ((a)this.a.get()).a(s);
            }
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            final Uri parse = Uri.parse(s);
            if (com.facebook.ads.internal.view.b.f.b.contains(parse.getScheme()) || this.b.get() == null) {
                goto Label_0068;
            }
            try {
                this.b.get().startActivity(new Intent("android.intent.action.VIEW", parse));
                return true;
            }
            catch (ActivityNotFoundException ex) {
                Log.w(com.facebook.ads.internal.view.b.f.a, "Activity not found to handle URI.", (Throwable)ex);
            }
            catch (Exception ex2) {
                Log.e(com.facebook.ads.internal.view.b.f.a, "Unknown exception occurred when trying to handle URI.", (Throwable)ex2);
                goto Label_0068;
            }
        }
    }
}
