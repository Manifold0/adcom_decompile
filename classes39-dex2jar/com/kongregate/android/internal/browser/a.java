// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.browser;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.HttpAuthHandler;
import android.graphics.Bitmap;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebViewClient;
import android.webkit.ConsoleMessage;
import android.view.MotionEvent;
import com.kongregate.android.internal.util.g;
import com.kongregate.android.internal.util.j;
import com.kongregate.o.c.d;
import com.kongregate.android.internal.util.StringUtils;
import android.annotation.TargetApi;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings$ZoomDensity;
import com.kongregate.o.d.b;
import android.app.Activity;
import android.util.AttributeSet;
import android.os.Bundle;
import android.content.Context;
import android.webkit.WebView;

public class a extends WebView
{
    public static final String a = "path";
    public static final String b = "showSystemUi";
    public static final String c = "allowImmersiveMode";
    public static final String d = "external";
    public static final String e = "redirect";
    public static final String f = "file:///android_res/raw/blank";
    public static final String g = "orientationOverride";
    public static final String h = "overrideTransition";
    protected c i;
    protected boolean j;
    protected boolean k;
    protected final Context l;
    protected final Bundle m;
    protected String n;
    
    public a(final Context l, final AttributeSet set) {
        super(l, set);
        this.j = false;
        this.l = l;
        Activity activity;
        if (this.l instanceof Activity) {
            activity = (Activity)this.l;
        }
        else {
            activity = null;
        }
        if (activity != null) {
            Bundle extras = activity.getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            this.m = extras;
        }
        else {
            this.m = new Bundle();
        }
        if (this.isInEditMode() || com.kongregate.o.d.b.a() == null) {
            this.n = null;
        }
        else {
            final String string = "http://" + com.kongregate.o.d.b.a().c();
            final String string2 = this.m.getString("path");
            if (string2 != null) {
                String string3 = string2;
                if (!string2.startsWith("http://")) {
                    if (string2.startsWith("https://")) {
                        string3 = string2;
                    }
                    else {
                        string3 = string + string2;
                    }
                }
                this.n = string3;
            }
            else {
                this.n = null;
            }
        }
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setClickable(true);
        this.requestFocus();
        this.setHorizontalScrollBarEnabled(false);
        this.setHorizontalScrollbarOverlay(false);
        this.setVerticalScrollBarEnabled(true);
        this.setVerticalScrollbarOverlay(true);
        if (!this.isInEditMode()) {
            this.getSettings().setJavaScriptEnabled(true);
            this.getSettings().setSupportZoom(false);
            this.getSettings().setBuiltInZoomControls(false);
            this.getSettings().setNeedInitialFocus(false);
            this.getSettings().setSaveFormData(false);
            this.getSettings().setDomStorageEnabled(true);
            this.getSettings().setAppCacheEnabled(true);
            this.getSettings().setDatabaseEnabled(true);
            this.getSettings().setSavePassword(false);
            this.getSettings().setDefaultZoom(WebSettings$ZoomDensity.MEDIUM);
            this.g();
        }
        this.setWebChromeClient((WebChromeClient)new a());
    }
    
    @TargetApi(17)
    private void g() {
        if (com.kongregate.android.internal.util.a.a(17)) {
            this.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }
    
    public void a() {
        if (StringUtils.b((CharSequence)this.n)) {
            com.kongregate.o.c.d.b(new Runnable() {
                @Override
                public void run() {
                    com.kongregate.android.internal.browser.a.this.loadUrl(com.kongregate.android.internal.browser.a.this.n);
                }
            });
        }
    }
    
    public void a(final String s) {
        com.kongregate.android.internal.util.j.c("Loading offline html");
        com.kongregate.o.c.d.b(new Runnable() {
            final /* synthetic */ String a = com.kongregate.android.internal.util.g.e(com.kongregate.android.internal.browser.a.this.getResources().getIdentifier("error", "raw", com.kongregate.android.internal.browser.a.this.getContext().getPackageName())).replace("${RETRY_URL}", s);
            
            @Override
            public void run() {
                com.kongregate.android.internal.browser.a.this.loadDataWithBaseURL("file:///android_res/raw/error", this.a, "text/html", "UTF-8", (String)null);
            }
        });
    }
    
    public void a(final String s, final String s2) {
        if (this.i == null) {
            com.kongregate.android.internal.util.j.c("expecting pending auth handler");
            return;
        }
        this.setHttpAuthUsernamePassword(this.i.a, this.i.b, s, s2);
        this.i.c.proceed(s, s2);
        this.i = null;
    }
    
    public void b() {
        com.kongregate.android.internal.util.j.a("Loading blank html");
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.browser.a.this.loadDataWithBaseURL("file:///android_res/raw/blank", "<!DOCTYPE html><html><head></head><body></body></html>", "text/html", "UTF-8", (String)null);
            }
        });
    }
    
    protected boolean c() {
        return this.m.getBoolean("redirect", false);
    }
    
    public b d() {
        return new b();
    }
    
    public void e() {
        if (this.i == null) {
            com.kongregate.android.internal.util.j.c("expecting pending auth handler");
            return;
        }
        this.i.c.cancel();
    }
    
    protected void f() {
        if (this.l instanceof Activity) {
            ((Activity)this.l).finish();
        }
    }
    
    public boolean onCheckIsTextEditor() {
        return this.j || super.onCheckIsTextEditor();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        }
        catch (NullPointerException ex) {
            com.kongregate.android.internal.util.j.c("NPE in onTouchEvent", ex);
            return false;
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        try {
            super.onWindowFocusChanged(b);
        }
        catch (NullPointerException ex) {
            com.kongregate.android.internal.util.j.d("Caught NPE when rendering WebView", ex);
        }
    }
    
    public void setAllLinksExternal(final boolean k) {
        this.k = k;
    }
    
    public void setShowKeyboard(final boolean j) {
        this.j = j;
    }
    
    public static class a extends WebChromeClient
    {
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            switch (a$4.a[consoleMessage.messageLevel().ordinal()]) {
                default: {
                    return super.onConsoleMessage(consoleMessage);
                }
                case 1:
                case 2: {
                    com.kongregate.android.internal.util.j.a("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                }
                case 3: {
                    com.kongregate.android.internal.util.j.b("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                }
                case 4: {
                    com.kongregate.android.internal.util.j.b("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                }
                case 5: {
                    com.kongregate.android.internal.util.j.d("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                }
            }
            return true;
        }
    }
    
    public class b extends WebViewClient
    {
        protected boolean a(final WebView webView, final String s) {
            if (com.kongregate.android.internal.browser.a.this.k) {
                this.a(s);
                return true;
            }
            if (s.indexOf("market://") == 0) {
                this.a(s);
                return true;
            }
            if (s.contains("/pages/")) {
                this.a(s);
                return true;
            }
            return false;
        }
        
        protected boolean a(final String s) {
            try {
                com.kongregate.android.internal.browser.a.this.l.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                return true;
            }
            catch (ActivityNotFoundException ex) {
                com.kongregate.android.internal.util.j.d("WebView Couldn't start ACTION_VIEW activity for URL: " + s);
                return false;
            }
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            com.kongregate.android.internal.util.j.b("WebView Page finished: " + s);
            super.onPageFinished(webView, s);
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            com.kongregate.android.internal.util.j.b("WebView Page started: " + s);
            super.onPageStarted(webView, s, bitmap);
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            com.kongregate.android.internal.util.j.d("WebView Error: " + n + ", " + s + ", " + s2);
        }
        
        public void onReceivedHttpAuthRequest(final WebView webView, final HttpAuthHandler httpAuthHandler, final String s, final String s2) {
            com.kongregate.android.internal.util.j.b("WebView Received HTTP auth request: " + s + ", realm=" + s2);
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, s, s2);
        }
        
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            com.kongregate.android.internal.util.j.d("WebView SSL Error: " + sslError.toString());
            if (com.kongregate.o.d.b.a().k()) {
                sslErrorHandler.cancel();
                com.kongregate.android.internal.browser.a.this.f();
                return;
            }
            sslErrorHandler.proceed();
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            return super.shouldOverrideUrlLoading(webView, s);
        }
    }
    
    protected class c
    {
        String a;
        String b;
        HttpAuthHandler c;
        
        public c(final HttpAuthHandler c, final String a, final String b) {
            this.c = c;
            this.a = a;
            this.b = b;
        }
    }
}
