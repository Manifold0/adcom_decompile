// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.concurrent.TimeUnit;
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.util.Log;
import java.util.concurrent.Executors;
import android.content.Context;
import android.webkit.WebView;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;

class n implements l
{
    private final ScheduledExecutorService a;
    private ScheduledFuture b;
    private ScheduledFuture c;
    private final ap d;
    private int e;
    private boolean f;
    private boolean g;
    private WebView h;
    private m i;
    
    n(final Context context, final ap d) {
        this.e = 0;
        this.f = false;
        this.g = false;
        this.d = d;
        this.a = Executors.newScheduledThreadPool(1);
    }
    
    private void b() {
        try {
            if (this.d.a() == ar.a) {
                return;
            }
            if (this.d.b() && !this.g) {
                Log.d("MoatJavaScriptBridge", "Ready for communication (setting environment variables).");
                this.g = true;
            }
            this.h.loadUrl(String.format("javascript:(function(b,f){function g(){function b(a,e){for(k in a)if(a.hasOwnProperty(k)){var c=a[k].fn;if('function'===typeof c)try{e?c(e):c()}catch(d){}}}function d(a,b,c){'function'===typeof a&&(c[b]={ts:+new Date,fn:a})}bjmk={};uqaj={};yhgt={};ryup=dptk=!1;this.a=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash};this.bpsy=function(a){dptk||ryup||d(a,+new Date,bjmk)};this.qmrv=function(a){ryup||d(a,+new Date,uqaj)};this.lgpr=function(a,b){d(a,b,yhgt)};this.xrnk=function(a){yhgt.hasOwnProperty(a)&&delete yhgt[a]};this.vgft=function(){return dptk};this.lkpu=function(){return ryup};this.mqjh=function(){dptk||ryup||(dptk=!0,b(bjmk))};this.egpw=function(){ryup||(ryup=!0,b(uqaj))};this.sglu=function(a){b(yhgt,a);return 0<Object.keys(yhgt).length}}'undefined'===typeof b.MoatMAK&&(b.MoatMAK=new g,b.MoatMAK.a(f),b.__zMoatInit__=!0)})(window,%s);", this.i.b()));
        }
        catch (Exception ex) {
            if (this.d.b()) {
                Log.e("MoatJavaScriptBridge", "Failed to initialize communication (did not set environment variables).", (Throwable)ex);
            }
        }
    }
    
    @TargetApi(19)
    private void c() {
        if (this.d.a() == ar.a) {
            return;
        }
        if (this.h == null || (this.f && this.h.getUrl() == null)) {
            if (this.d.b()) {
                final StringBuilder sb = new StringBuilder("WebView became null");
                String s;
                if (this.h == null) {
                    s = "";
                }
                else {
                    s = "based on null url";
                }
                Log.d("MoatJavaScriptBridge", sb.append(s).append(", stopping tracking loop").toString());
            }
            this.g();
            return;
        }
        if (this.h.getUrl() != null) {
            this.f = true;
        }
        final String format = String.format("MoatMAK.sglu(%s)", this.i.a());
        if (Build$VERSION.SDK_INT >= 19) {
            this.h.evaluateJavascript(format, (ValueCallback)new p(this));
            return;
        }
        this.h.loadUrl("javascript:" + format);
    }
    
    private void d() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting metadata reporting loop");
        }
        this.c = this.a.scheduleWithFixedDelay(new q(this), 0L, 50L, TimeUnit.MILLISECONDS);
    }
    
    private void e() {
        if (this.c != null) {
            if (!this.c.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping metadata reporting loop");
            }
            this.c.cancel(true);
        }
    }
    
    private void f() {
        if (this.d.b()) {
            Log.d("MoatJavaScriptBridge", "Starting view update loop");
        }
        this.b = this.a.scheduleWithFixedDelay(new s(this), 0L, this.d.c(), TimeUnit.MILLISECONDS);
    }
    
    private void g() {
        if (this.b != null) {
            if (this.b.isCancelled() && this.d.b()) {
                Log.d("MoatJavaScriptBridge", "Stopping view update loop");
            }
            this.b.cancel(true);
        }
    }
    
    @Override
    public void a() {
        if (this.d.a() == ar.a) {
            return;
        }
        this.e();
        this.g();
    }
    
    @Override
    public boolean a(final WebView h, final m i) {
        final boolean b = this.d.b();
        if (!h.getSettings().getJavaScriptEnabled()) {
            if (b) {
                Log.e("MoatJavaScriptBridge", "JavaScript is not enabled in the given WebView. Can't track.");
            }
            return false;
        }
        this.h = h;
        this.i = i;
        this.d();
        this.f();
        this.a.schedule(new o(this), 10L, TimeUnit.SECONDS);
        return true;
    }
}
