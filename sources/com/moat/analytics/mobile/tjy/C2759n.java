package com.moat.analytics.mobile.tjy;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.tjy.n */
class C2759n implements C2758l {
    /* renamed from: a */
    private final ScheduledExecutorService f6721a;
    /* renamed from: b */
    private ScheduledFuture f6722b;
    /* renamed from: c */
    private ScheduledFuture f6723c;
    /* renamed from: d */
    private final ap f6724d;
    /* renamed from: e */
    private int f6725e = 0;
    /* renamed from: f */
    private boolean f6726f = false;
    /* renamed from: g */
    private boolean f6727g = false;
    /* renamed from: h */
    private WebView f6728h;
    /* renamed from: i */
    private C2750m f6729i;

    C2759n(Context context, ap apVar) {
        this.f6724d = apVar;
        this.f6721a = Executors.newScheduledThreadPool(1);
    }

    /* renamed from: b */
    private void m6937b() {
        try {
            if (this.f6724d.mo6103a() != ar.OFF) {
                if (this.f6724d.mo6105b() && !this.f6727g) {
                    Log.d("MoatJavaScriptBridge", "Ready for communication (setting environment variables).");
                    this.f6727g = true;
                }
                this.f6728h.loadUrl(String.format("javascript:(function(b,f){function g(){function b(a,e){for(k in a)if(a.hasOwnProperty(k)){var c=a[k].fn;if('function'===typeof c)try{e?c(e):c()}catch(d){}}}function d(a,b,c){'function'===typeof a&&(c[b]={ts:+new Date,fn:a})}bjmk={};uqaj={};yhgt={};ryup=dptk=!1;this.a=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash};this.bpsy=function(a){dptk||ryup||d(a,+new Date,bjmk)};this.qmrv=function(a){ryup||d(a,+new Date,uqaj)};this.lgpr=function(a,b){d(a,b,yhgt)};this.xrnk=function(a){yhgt.hasOwnProperty(a)&&delete yhgt[a]};this.vgft=function(){return dptk};this.lkpu=function(){return ryup};this.mqjh=function(){dptk||ryup||(dptk=!0,b(bjmk))};this.egpw=function(){ryup||(ryup=!0,b(uqaj))};this.sglu=function(a){b(yhgt,a);return 0<Object.keys(yhgt).length}}'undefined'===typeof b.MoatMAK&&(b.MoatMAK=new g,b.MoatMAK.a(f),b.__zMoatInit__=!0)})(window,%s);", new Object[]{this.f6729i.mo6120b()}));
            }
        } catch (Throwable e) {
            if (this.f6724d.mo6105b()) {
                Log.e("MoatJavaScriptBridge", "Failed to initialize communication (did not set environment variables).", e);
            }
        }
    }

    @TargetApi(19)
    /* renamed from: c */
    private void m6939c() {
        if (this.f6724d.mo6103a() != ar.OFF) {
            if (this.f6728h == null || (this.f6726f && this.f6728h.getUrl() == null)) {
                if (this.f6724d.mo6105b()) {
                    Log.d("MoatJavaScriptBridge", "WebView became null" + (this.f6728h == null ? "" : "based on null url") + ", stopping tracking loop");
                }
                m6946g();
                return;
            }
            if (this.f6728h.getUrl() != null) {
                this.f6726f = true;
            }
            String format = String.format("MoatMAK.sglu(%s)", new Object[]{this.f6729i.mo6118a()});
            if (VERSION.SDK_INT >= 19) {
                this.f6728h.evaluateJavascript(format, new C2761p(this));
            } else {
                this.f6728h.loadUrl("javascript:" + format);
            }
        }
    }

    /* renamed from: d */
    private void m6940d() {
        if (this.f6724d.mo6105b()) {
            Log.d("MoatJavaScriptBridge", "Starting metadata reporting loop");
        }
        this.f6723c = this.f6721a.scheduleWithFixedDelay(new C2762q(this), 0, 50, TimeUnit.MILLISECONDS);
    }

    /* renamed from: e */
    private void m6943e() {
        if (this.f6723c != null) {
            if (!this.f6723c.isCancelled() && this.f6724d.mo6105b()) {
                Log.d("MoatJavaScriptBridge", "Stopping metadata reporting loop");
            }
            this.f6723c.cancel(true);
        }
    }

    /* renamed from: f */
    private void m6944f() {
        if (this.f6724d.mo6105b()) {
            Log.d("MoatJavaScriptBridge", "Starting view update loop");
        }
        this.f6722b = this.f6721a.scheduleWithFixedDelay(new C2764s(this), 0, (long) this.f6724d.mo6106c(), TimeUnit.MILLISECONDS);
    }

    /* renamed from: g */
    private void m6946g() {
        if (this.f6722b != null) {
            if (this.f6722b.isCancelled() && this.f6724d.mo6105b()) {
                Log.d("MoatJavaScriptBridge", "Stopping view update loop");
            }
            this.f6722b.cancel(true);
        }
    }

    /* renamed from: a */
    public void mo6128a() {
        if (this.f6724d.mo6103a() != ar.OFF) {
            m6943e();
            m6946g();
        }
    }

    /* renamed from: a */
    public boolean mo6129a(WebView webView, C2750m c2750m) {
        boolean b = this.f6724d.mo6105b();
        if (webView.getSettings().getJavaScriptEnabled()) {
            this.f6728h = webView;
            this.f6729i = c2750m;
            m6940d();
            m6944f();
            this.f6721a.schedule(new C2760o(this), 10, TimeUnit.SECONDS);
            return true;
        }
        if (b) {
            Log.e("MoatJavaScriptBridge", "JavaScript is not enabled in the given WebView. Can't track.");
        }
        return false;
    }
}
