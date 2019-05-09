package com.kongregate.android.internal.browser;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p006c.C0626d;
import com.kongregate.p000o.p007d.C0635b;

/* renamed from: com.kongregate.android.internal.browser.a */
public class C0451a extends WebView {
    /* renamed from: a */
    public static final String f311a = "path";
    /* renamed from: b */
    public static final String f312b = "showSystemUi";
    /* renamed from: c */
    public static final String f313c = "allowImmersiveMode";
    /* renamed from: d */
    public static final String f314d = "external";
    /* renamed from: e */
    public static final String f315e = "redirect";
    /* renamed from: f */
    public static final String f316f = "file:///android_res/raw/blank";
    /* renamed from: g */
    public static final String f317g = "orientationOverride";
    /* renamed from: h */
    public static final String f318h = "overrideTransition";
    /* renamed from: i */
    protected C0457c f319i;
    /* renamed from: j */
    protected boolean f320j = false;
    /* renamed from: k */
    protected boolean f321k;
    /* renamed from: l */
    protected final Context f322l;
    /* renamed from: m */
    protected final Bundle f323m;
    /* renamed from: n */
    protected String f324n;

    /* renamed from: com.kongregate.android.internal.browser.a$b */
    public class C0449b extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ C0451a f309a;

        public C0449b(C0451a c0451a) {
            this.f309a = c0451a;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            C0562j.m756b("WebView Page started: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            C0562j.m761d("WebView Error: " + i + ", " + str + ", " + str2);
        }

        public void onPageFinished(WebView webView, String str) {
            C0562j.m756b("WebView Page finished: " + str);
            super.onPageFinished(webView, str);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            C0562j.m761d("WebView SSL Error: " + sslError.toString());
            if (C0635b.m1001a().m1023k()) {
                sslErrorHandler.cancel();
                this.f309a.m220f();
                return;
            }
            sslErrorHandler.proceed();
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            C0562j.m756b("WebView Received HTTP auth request: " + str + ", realm=" + str2);
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }

        /* renamed from: a */
        protected boolean m211a(String str) {
            try {
                this.f309a.f322l.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } catch (ActivityNotFoundException e) {
                C0562j.m761d("WebView Couldn't start ACTION_VIEW activity for URL: " + str);
                return false;
            }
        }

        /* renamed from: a */
        protected boolean m210a(WebView webView, String str) {
            if (this.f309a.f321k) {
                m211a(str);
                return true;
            } else if (str.indexOf("market://") == 0) {
                m211a(str);
                return true;
            } else if (!str.contains("/pages/")) {
                return false;
            } else {
                m211a(str);
                return true;
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.a$1 */
    class C04521 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0451a f346a;

        C04521(C0451a c0451a) {
            this.f346a = c0451a;
        }

        public void run() {
            this.f346a.loadUrl(this.f346a.f324n);
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.a$3 */
    class C04543 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0451a f349a;

        C04543(C0451a c0451a) {
            this.f349a = c0451a;
        }

        public void run() {
            this.f349a.loadDataWithBaseURL(C0451a.f316f, "<!DOCTYPE html><html><head></head><body></body></html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.a$4 */
    static /* synthetic */ class C04554 {
        /* renamed from: a */
        static final /* synthetic */ int[] f350a = new int[MessageLevel.values().length];

        static {
            try {
                f350a[MessageLevel.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f350a[MessageLevel.TIP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f350a[MessageLevel.LOG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f350a[MessageLevel.WARNING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f350a[MessageLevel.ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.a$a */
    public static class C0456a extends WebChromeClient {
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            switch (C04554.f350a[consoleMessage.messageLevel().ordinal()]) {
                case 1:
                case 2:
                    C0562j.m753a("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                case 3:
                    C0562j.m756b("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                case 4:
                    C0562j.m756b("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                case 5:
                    C0562j.m761d("[WebView]: " + consoleMessage.message() + ", " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
                    break;
                default:
                    return super.onConsoleMessage(consoleMessage);
            }
            return true;
        }
    }

    /* renamed from: com.kongregate.android.internal.browser.a$c */
    protected class C0457c {
        /* renamed from: a */
        String f351a;
        /* renamed from: b */
        String f352b;
        /* renamed from: c */
        HttpAuthHandler f353c;
        /* renamed from: d */
        final /* synthetic */ C0451a f354d;

        public C0457c(C0451a c0451a, HttpAuthHandler httpAuthHandler, String str, String str2) {
            this.f354d = c0451a;
            this.f353c = httpAuthHandler;
            this.f351a = str;
            this.f352b = str2;
        }
    }

    public C0451a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f322l = context;
        Activity activity = this.f322l instanceof Activity ? (Activity) this.f322l : null;
        if (activity != null) {
            Bundle extras = activity.getIntent().getExtras();
            if (extras == null) {
                extras = new Bundle();
            }
            this.f323m = extras;
        } else {
            this.f323m = new Bundle();
        }
        if (isInEditMode() || C0635b.m1001a() == null) {
            this.f324n = null;
        } else {
            String str = "http://" + C0635b.m1001a().m1014c();
            String string = this.f323m.getString("path");
            if (string != null) {
                if (!(string.startsWith("http://") || string.startsWith("https://"))) {
                    string = str + string;
                }
                this.f324n = string;
            } else {
                this.f324n = null;
            }
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
        requestFocus();
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(true);
        setVerticalScrollbarOverlay(true);
        if (!isInEditMode()) {
            getSettings().setJavaScriptEnabled(true);
            getSettings().setSupportZoom(false);
            getSettings().setBuiltInZoomControls(false);
            getSettings().setNeedInitialFocus(false);
            getSettings().setSaveFormData(false);
            getSettings().setDomStorageEnabled(true);
            getSettings().setAppCacheEnabled(true);
            getSettings().setDatabaseEnabled(true);
            getSettings().setSavePassword(false);
            getSettings().setDefaultZoom(ZoomDensity.MEDIUM);
            mo1129g();
        }
        setWebChromeClient(new C0456a());
    }

    @TargetApi(17)
    /* renamed from: g */
    private void mo1129g() {
        if (C0542a.m606a(17)) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }

    /* renamed from: a */
    public void m213a() {
        if (StringUtils.m584b(this.f324n)) {
            C0626d.m968b(new C04521(this));
        }
    }

    /* renamed from: a */
    public void m214a(String str) {
        C0562j.m759c("Loading offline html");
        final String replace = C0558g.m699e(getResources().getIdentifier("error", "raw", getContext().getPackageName())).replace("${RETRY_URL}", str);
        C0626d.m968b(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0451a f348b;

            public void run() {
                this.f348b.loadDataWithBaseURL("file:///android_res/raw/error", replace, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
            }
        });
    }

    /* renamed from: b */
    public void m216b() {
        C0562j.m753a("Loading blank html");
        String str = "<!DOCTYPE html><html><head></head><body></body></html>";
        C0626d.m968b(new C04543(this));
    }

    /* renamed from: c */
    protected boolean m217c() {
        return this.f323m.getBoolean(f315e, false);
    }

    public void setShowKeyboard(boolean z) {
        this.f320j = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable e) {
            C0562j.m760c("NPE in onTouchEvent", e);
            return false;
        }
    }

    public void setAllLinksExternal(boolean z) {
        this.f321k = z;
    }

    /* renamed from: d */
    public C0449b m218d() {
        return new C0449b(this);
    }

    public void onWindowFocusChanged(boolean z) {
        try {
            super.onWindowFocusChanged(z);
        } catch (Throwable e) {
            C0562j.m762d("Caught NPE when rendering WebView", e);
        }
    }

    /* renamed from: a */
    public void m215a(String str, String str2) {
        if (this.f319i == null) {
            C0562j.m759c("expecting pending auth handler");
            return;
        }
        setHttpAuthUsernamePassword(this.f319i.f351a, this.f319i.f352b, str, str2);
        this.f319i.f353c.proceed(str, str2);
        this.f319i = null;
    }

    /* renamed from: e */
    public void m219e() {
        if (this.f319i == null) {
            C0562j.m759c("expecting pending auth handler");
        } else {
            this.f319i.f353c.cancel();
        }
    }

    public boolean onCheckIsTextEditor() {
        return this.f320j || super.onCheckIsTextEditor();
    }

    /* renamed from: f */
    protected void m220f() {
        if (this.f322l instanceof Activity) {
            ((Activity) this.f322l).finish();
        }
    }
}
