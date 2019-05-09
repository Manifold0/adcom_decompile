// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.webkit.WebChromeClient;
import android.view.ViewGroup;
import android.view.View;
import com.chartboost.sdk.h;
import com.chartboost.sdk.g;
import android.widget.RelativeLayout;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.chartboost.sdk.Libraries.CBUtility;
import android.graphics.Rect;
import android.app.Activity;
import android.util.DisplayMetrics;
import java.util.Iterator;
import java.util.List;
import android.text.TextUtils;
import android.content.Context;
import java.io.File;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import org.json.JSONObject;
import android.view.Window;
import android.os.Handler;
import com.chartboost.sdk.Model.c;
import android.content.SharedPreferences;
import com.chartboost.sdk.d;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.e;

public class bf extends e
{
    int A;
    int B;
    int C;
    int D;
    int E;
    int F;
    boolean G;
    int H;
    private final f I;
    private final ah J;
    private String K;
    private float L;
    private float M;
    private boolean N;
    private int O;
    final com.chartboost.sdk.Tracking.a j;
    final d k;
    final SharedPreferences l;
    public String m;
    String n;
    protected int o;
    long p;
    long q;
    boolean r;
    int s;
    int t;
    int u;
    int v;
    int w;
    int x;
    int y;
    int z;
    
    public bf(final c c, final f i, final ah j, final SharedPreferences l, final com.chartboost.sdk.Tracking.a k, final Handler handler, final com.chartboost.sdk.c c2, final d m) {
        super(c, handler, c2);
        this.m = "UNKNOWN";
        this.K = null;
        this.n = null;
        this.o = 1;
        this.L = 0.0f;
        this.M = 0.0f;
        this.N = false;
        this.p = 0L;
        this.q = 0L;
        this.r = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = -1;
        this.G = true;
        this.H = -1;
        this.O = 0;
        this.I = i;
        this.J = j;
        this.j = k;
        this.k = m;
        this.l = l;
    }
    
    int a(final Window window) {
        return window.findViewById(16908290).getTop();
    }
    
    public String a(final int n) {
        switch (n) {
            default: {
                return "error";
            }
            case 1: {
                return "portrait";
            }
            case 0: {
                return "landscape";
            }
            case -1: {
                return "none";
            }
        }
    }
    
    public void a(final float m) {
        this.M = m;
    }
    
    @Override
    public boolean a(final JSONObject jsonObject) {
        final File a = this.I.d().a;
        if (a == null) {
            CBLogging.b("CBWebViewProtocol", "External Storage path is unavailable or media not mounted");
            this.a(CBError.CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.n = "file://" + a.getAbsolutePath() + "/";
        if (com.chartboost.sdk.impl.s.a().a(this.e.p.e)) {
            CBLogging.b("CBWebViewProtocol", "Invalid adId being passed in the response");
            this.a(CBError.CBImpressionError.ERROR_DISPLAYING_VIEW);
            return false;
        }
        final String o = this.e.o;
        if (o == null) {
            CBLogging.b("CBWebViewProtocol", "No html data found in memory");
            this.a(CBError.CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.K = o;
        return true;
    }
    
    @Override
    protected e.a b(final Context context) {
        return new b(context, this.K);
    }
    
    public void b(final float l) {
        this.L = l;
    }
    
    public void b(final int o) {
        this.O = o;
    }
    
    public void b(final String s) {
        if (this.e.p.n != null && !TextUtils.isEmpty((CharSequence)s)) {
            final List<String> list = this.e.p.n.get(s);
            if (list != null) {
                for (final String s2 : list) {
                    if (!s2.isEmpty()) {
                        this.J.a(new ad<Object>("GET", s2, 2, null));
                        CBLogging.a("CBWebViewProtocol", "###### Sending VAST Tracking Event: " + s2);
                    }
                }
            }
        }
    }
    
    void c(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.s = displayMetrics.widthPixels;
        this.t = displayMetrics.heightPixels;
    }
    
    public void c(final String s) {
        this.j.a(this.e.a.a(this.e.p.b), this.e.m, this.e.o(), s);
    }
    
    public void c(final JSONObject jsonObject) {
        this.G = jsonObject.optBoolean("allowOrientationChange", this.G);
        this.H = this.f(jsonObject.optString("forceOrientation", this.a(this.H)));
        this.q();
    }
    
    @Override
    public void d() {
        com.chartboost.sdk.impl.o.d();
        final b y = this.y();
        if (y != null) {
            if (y.c != null) {
                CBLogging.a("CBWebViewProtocol", "Destroying the webview object and cleaning up the references");
                y.c.destroy();
                y.c = null;
            }
            if (y.d != null) {
                y.d = null;
            }
            if (y.e != null) {
                y.e = null;
            }
            if (y.f != null) {
                y.f = null;
            }
        }
        super.d();
    }
    
    void d(final Context context) {
        if (context instanceof Activity) {
            final Window window = ((Activity)context).getWindow();
            final Rect rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            this.w = this.a(window);
            if (this.s == 0 || this.t == 0) {
                this.c(context);
            }
            final int width = rect.width();
            final int v = this.t - this.w;
            if (width != this.u || v != this.v) {
                this.u = width;
                this.v = v;
            }
        }
    }
    
    public void d(String s) {
        if (s.a().a(s)) {
            s = "Unknown Webview error";
        }
        this.j.a(this.e.a.a(this.e.p.b), this.e.m, this.e.o(), s, true);
        CBLogging.b("CBWebViewProtocol", "Webview error occurred closing the webview" + s);
        this.a(CBError.CBImpressionError.ERROR_LOADING_WEB_VIEW);
        this.h();
    }
    
    public void e(final String s) {
        String s2 = s;
        if (s.a().a(s)) {
            s2 = "Unknown Webview warning message";
        }
        this.j.b(this.e.a.a(this.e.p.b), this.e.m, this.e.o(), s2);
        CBLogging.d("CBWebViewProtocol", "Webview warning occurred closing the webview" + s2);
    }
    
    public int f(final String s) {
        if (s.equals("portrait")) {
            return 1;
        }
        if (s.equals("landscape")) {
            return 0;
        }
        return -1;
    }
    
    @Override
    public void h() {
        super.h();
        this.r();
    }
    
    @Override
    public float j() {
        return this.L;
    }
    
    @Override
    public float k() {
        return this.M;
    }
    
    @Override
    public boolean l() {
        if (this.O == 2 && this.e.a.a == 1) {
            return true;
        }
        this.d();
        this.h();
        return true;
    }
    
    @Override
    public void m() {
        super.m();
        final b y = this.y();
        if (y != null && y.c != null) {
            this.a.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (y.c != null) {
                        y.c.onResume();
                    }
                }
            });
            this.j.d(this.m, this.e.o());
        }
    }
    
    @Override
    public void n() {
        super.n();
        final b y = this.y();
        if (y != null && y.c != null) {
            this.a.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (y.c != null) {
                        y.c.onPause();
                    }
                }
            });
            this.j.e(this.m, this.e.o());
        }
    }
    
    void o() {
        final b y = this.y();
        if (y == null || !this.r) {
            this.B = this.x;
            this.C = this.y;
            this.D = this.z;
            this.E = this.A;
            return;
        }
        final int[] array = new int[2];
        y.getLocationInWindow(array);
        final int x = array[0];
        final int y2 = array[1] - this.w;
        final int width = y.getWidth();
        final int height = y.getHeight();
        this.x = x;
        this.y = y2;
        this.z = x + width;
        this.A = height + y2;
        this.B = this.x;
        this.C = this.y;
        this.D = this.z;
        this.E = this.A;
    }
    
    public String p() {
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("allowOrientationChange", this.G), com.chartboost.sdk.Libraries.e.a("forceOrientation", this.a(this.H))).toString();
    }
    
    void q() {
        int requestedOrientation = 1;
        final Activity b = this.b.b();
        if (b == null || CBUtility.a(b)) {
            return;
        }
        if (this.H != 1) {
            if (this.H == 0) {
                requestedOrientation = 0;
            }
            else if (this.G) {
                requestedOrientation = -1;
            }
            else {
                int n;
                if (b.getResources().getConfiguration().orientation == 1) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    requestedOrientation = 0;
                }
            }
        }
        b.setRequestedOrientation(requestedOrientation);
    }
    
    void r() {
        final Activity b = this.b.b();
        if (b == null || CBUtility.a(b)) {
            return;
        }
        if (b.getRequestedOrientation() != this.F) {
            b.setRequestedOrientation(this.F);
        }
        this.G = true;
        this.H = -1;
    }
    
    public String s() {
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("width", this.u), com.chartboost.sdk.Libraries.e.a("height", this.v)).toString();
    }
    
    public String t() {
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("width", this.s), com.chartboost.sdk.Libraries.e.a("height", this.t)).toString();
    }
    
    public String u() {
        this.o();
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", this.x), com.chartboost.sdk.Libraries.e.a("y", this.y), com.chartboost.sdk.Libraries.e.a("width", this.z), com.chartboost.sdk.Libraries.e.a("height", this.A)).toString();
    }
    
    public String v() {
        this.o();
        return com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("x", this.B), com.chartboost.sdk.Libraries.e.a("y", this.C), com.chartboost.sdk.Libraries.e.a("width", this.D), com.chartboost.sdk.Libraries.e.a("height", this.E)).toString();
    }
    
    public void w() {
        if (this.o <= 1) {
            this.e.e();
            ++this.o;
        }
    }
    
    public void x() {
        this.j.c(this.m, this.e.o());
    }
    
    public b y() {
        return (b)super.e();
    }
    
    public void z() {
        if (this.e.l == 2 && !this.N) {
            this.j.d("", this.e.o());
            this.e.p();
            this.N = true;
            com.chartboost.sdk.impl.o.c();
        }
    }
    
    private class a extends WebViewClient
    {
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            bf.this.r = true;
            bf.this.q = System.currentTimeMillis();
            CBLogging.a("CBWebViewProtocol", "Total web view load response time " + (bf.this.q - bf.this.p) / 1000L);
            final Context context = webView.getContext();
            if (context != null) {
                bf.this.c(context);
                bf.this.d(context);
                bf.this.o();
            }
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            bf.this.a(CBError.CBImpressionError.WEB_VIEW_CLIENT_RECEIVED_ERROR);
            bf.this.r = true;
            bf.this.k.d(bf.this.e);
            CBLogging.a("CBWebViewProtocol", "Webview seems to have some issues loading html, onRecievedError callback triggered");
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            return false;
        }
    }
    
    public class b extends e.a
    {
        public be c;
        public bd d;
        public RelativeLayout e;
        public RelativeLayout f;
        
        public b(final Context context, final String s) {
            super(context);
            this.setFocusable(false);
            final g a = com.chartboost.sdk.g.a();
            this.e = a.a(new RelativeLayout(context));
            this.f = a.a(new RelativeLayout(context));
            com.chartboost.sdk.h.a(context, this.c = a.a(new be(context)), bf.this.l);
            this.c.setWebViewClient((WebViewClient)a.a(new bf.a()));
            this.d = a.a(new bd((View)this.e, (ViewGroup)this.f, null, this.c, bf.this, bf.this.a));
            this.c.setWebChromeClient(this.d);
            if (com.chartboost.sdk.impl.s.a().a(19)) {
                final be c = this.c;
                be.setWebContentsDebuggingEnabled(true);
            }
            this.c.loadDataWithBaseURL(bf.this.n, s, "text/html", "utf-8", null);
            this.e.addView((View)this.c);
            this.c.getSettings().setSupportZoom(false);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
            this.e.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.c.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.c.setBackgroundColor(0);
            this.f.setVisibility(8);
            this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.addView((View)this.e);
            this.addView((View)this.f);
            bf.this.p = System.currentTimeMillis();
            if (context instanceof Activity) {
                bf.this.F = ((Activity)context).getRequestedOrientation();
            }
            else {
                bf.this.F = -1;
            }
            com.chartboost.sdk.impl.o.a(this.c, bf.this.e.p.s);
            bf.this.a.postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (!bf.this.r) {
                        CBLogging.a("CBWebViewProtocol", "Webview seems to be taking more time loading the html content, so closing the view.");
                        bf.this.a(CBError.CBImpressionError.WEB_VIEW_PAGE_LOAD_TIMEOUT);
                    }
                }
            }, 3000L);
        }
        
        @Override
        protected void a(final int n, final int n2) {
        }
    }
}
