// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.f;

import android.webkit.JavascriptInterface;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import java.util.HashMap;
import android.view.MotionEvent;
import android.text.TextUtils;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebSettings;
import android.view.View$OnTouchListener;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import com.facebook.ads.internal.view.component.j;
import android.view.View$OnClickListener;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.support.annotation.Nullable;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.ads.internal.view.i;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.o;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.w.b.e;
import com.facebook.ads.internal.view.c.a;
import android.widget.RelativeLayout;

public class b extends RelativeLayout implements com.facebook.ads.internal.view.c.a.d, com.facebook.ads.internal.w.b.e.a
{
    private static final int a;
    private static final RelativeLayout$LayoutParams b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final float f;
    private final o g;
    private final n h;
    private final b i;
    private final com.facebook.ads.internal.s.c j;
    private final i k;
    private final AtomicBoolean l;
    private final e m;
    private final e n;
    private final boolean o;
    private WeakReference<com.facebook.ads.internal.view.c.a> p;
    private com.facebook.ads.internal.view.c.a.b q;
    private com.facebook.ads.internal.view.component.c r;
    private com.facebook.ads.internal.view.f.a s;
    private RelativeLayout t;
    private boolean u;
    private Toast v;
    @Nullable
    private c w;
    
    static {
        a = (int)(64.0f * x.b);
        b = new RelativeLayout$LayoutParams(-1, -1);
        c = (int)(16.0f * x.b);
        d = (int)(12.0f * x.b);
        e = (int)(10.0f * x.b);
        f = (float)(int)(4.0f * x.b);
    }
    
    public b(final Context context, final o g, final com.facebook.ads.internal.s.c j, final com.facebook.ads.internal.view.a.a a, final c w, final boolean b, final boolean o) {
        super(context);
        this.l = new AtomicBoolean();
        this.u = false;
        this.g = g;
        this.h = g.f().j();
        this.i = g.e();
        this.j = j;
        this.w = w;
        this.k = new i(context, a, com.facebook.ads.internal.view.i.a.a);
        this.o = o;
        int c;
        if (b) {
            c = this.h.c();
        }
        else {
            c = 0;
        }
        this.m = new e(c, (com.facebook.ads.internal.w.b.e.a)this);
        int n;
        if (this.h.h()) {
            n = 2;
        }
        else {
            n = 0;
        }
        this.n = new e(n, (com.facebook.ads.internal.w.b.e.a)new com.facebook.ads.internal.w.b.e.a() {
            @Override
            public void a() {
                b.this.g();
            }
            
            @Override
            public void a(final int n) {
            }
        });
        this.k.a(this.i.a(), true);
        this.k.setShowPageDetails(false);
        this.k.a(this.g.b(), this.g.g(), this.h.c());
        this.k.setToolbarListener((i.b)new i.b() {
            @Override
            public void a() {
                if (b.this.w != null) {
                    b.this.w.c();
                }
            }
        });
        x.a((View)this.k);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.k.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.s = new com.facebook.ads.internal.view.f.a(this.getContext(), this.g);
        this.setLayoutParams((ViewGroup$LayoutParams)com.facebook.ads.internal.view.f.b.b);
        x.a((View)this, this.i.a().d(true));
        this.addView((View)this.s, (ViewGroup$LayoutParams)com.facebook.ads.internal.view.f.b.b);
        x.a((View)this, -14473425);
        this.setLayoutParams((ViewGroup$LayoutParams)com.facebook.ads.internal.view.f.b.b);
    }
    
    private static TextView a(final ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); ++i) {
            final View child = viewGroup.getChildAt(i);
            if (child instanceof TextView) {
                return (TextView)child;
            }
            if (child instanceof ViewGroup) {
                a((ViewGroup)child);
            }
        }
        return null;
    }
    
    private void b(final int n) {
        if (this.v != null) {
            this.v.setGravity(49, 0, com.facebook.ads.internal.view.f.b.a);
            final TextView a = a((ViewGroup)this.v.getView());
            if (a != null) {
                a.setText((CharSequence)this.h.e().replace("[secs]", String.valueOf(n)));
                a.setGravity(17);
            }
        }
    }
    
    static /* synthetic */ void f(final b b) {
        if (b.v != null && b.v.getView().getWindowVisibility() == 0) {
            return;
        }
        b.v = Toast.makeText(b.getContext(), (CharSequence)b.h.e(), 1);
        b.b(b.m.e());
        b.v.show();
    }
    
    private void g() {
        if (this.w != null) {
            this.w.a();
        }
        x.a((View)(this.t = new RelativeLayout(this.getContext())));
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams.setMargins(com.facebook.ads.internal.view.f.b.c, com.facebook.ads.internal.view.f.b.d, com.facebook.ads.internal.view.f.b.c, com.facebook.ads.internal.view.f.b.d);
        layoutParams.addRule(12);
        this.t.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        final com.facebook.ads.internal.view.component.c r = new com.facebook.ads.internal.view.component.c(this.getContext(), true, false, this.i.a());
        r.setButtonColor(452984831);
        r.setText(this.g.d().b());
        r.getBackground().setAlpha(0);
        x.a((View)r);
        r.setOnClickListener((View$OnClickListener)new a(this));
        r.setTextSize(14.0f);
        r.setIncludeFontPadding(false);
        r.setPadding(com.facebook.ads.internal.view.f.b.e, com.facebook.ads.internal.view.f.b.e, com.facebook.ads.internal.view.f.b.e, com.facebook.ads.internal.view.f.b.e);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        r.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        if (!this.o) {
            r.setVisibility(8);
        }
        this.r = r;
        final com.facebook.ads.internal.view.component.c r2 = this.r;
        final j j = new j(this.getContext(), this.g.e().a(), true, 16, 14, 0);
        x.a((View)j);
        j.a(this.g.c().a(), this.g.c().b(), null, false, true);
        final TextView descriptionTextView = j.getDescriptionTextView();
        descriptionTextView.setAlpha(0.8f);
        descriptionTextView.setMaxLines(1);
        descriptionTextView.setEllipsize(TextUtils$TruncateAt.END);
        final TextView titleTextView = j.getTitleTextView();
        titleTextView.setMaxLines(1);
        titleTextView.setEllipsize(TextUtils$TruncateAt.END);
        final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-1, -2);
        layoutParams3.addRule(0, r2.getId());
        layoutParams3.setMargins(0, 0, com.facebook.ads.internal.view.f.b.c, 0);
        j.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.r.getLayoutParams();
        relativeLayout$LayoutParams.addRule(6, j.getId());
        relativeLayout$LayoutParams.addRule(8, j.getId());
        this.q = new com.facebook.ads.internal.view.c.a.c() {
            @Override
            public void a(final int n, @Nullable final String s) {
                b.this.u = true;
                if (b.this.p.get() != null) {
                    ((a)b.this.p.get()).setVisibility(4);
                }
                if (b.this.w != null) {
                    b.this.w.d();
                }
            }
            
            @Override
            public void b() {
                if (b.this.l.compareAndSet(false, true) && b.this.p.get() != null && b.this.w != null) {
                    final a a = (a)b.this.p.get();
                    b.this.w.a(a.getViewabilityChecker(), a.getTouchDataRecorder());
                    b.this.m.a();
                }
            }
        };
        final com.facebook.ads.internal.view.c.a a = new com.facebook.ads.internal.view.c.a(this.getContext(), new WeakReference<com.facebook.ads.internal.view.c.a.b>(this.q), 10);
        a.setLogMultipleImpressions(false);
        a.setWaitForAssetsToLoad(true);
        a.setCheckAssetsByJavascriptBridge(false);
        a.setWebViewTimeoutInMillis(this.h.g());
        a.setRequestId(this.g.a());
        final WebSettings settings = a.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (Build$VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        this.p = new WeakReference<com.facebook.ads.internal.view.c.a>(a);
        a.loadUrl(this.getMarkupUrl());
        a.setOnTouchListener((View$OnTouchListener)new b(a, this.j, this.g));
        a.addJavascriptInterface((Object)new d(), "FbPlayableAd");
        a.setCornerRadius(com.facebook.ads.internal.view.f.b.f);
        x.a((View)this, -14473425);
        final RelativeLayout$LayoutParams layoutParams4 = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams4.setMargins(com.facebook.ads.internal.view.f.b.c, 0, com.facebook.ads.internal.view.f.b.c, 0);
        layoutParams4.addRule(3, this.k.getId());
        layoutParams4.addRule(2, this.t.getId());
        a.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
        a.setVisibility(4);
        a.setOnAssetsLoadedListener((com.facebook.ads.internal.view.c.a.d)this);
        this.t.addView((View)j);
        this.t.addView((View)this.r);
        this.addView((View)this.k);
        this.addView((View)a);
        this.addView((View)this.t);
        this.k.setVisibility(4);
        a.setVisibility(4);
        a.setTranslationY(50.0f);
        this.t.setVisibility(4);
        this.t.setTranslationY(200.0f);
    }
    
    static /* synthetic */ void g(final b b) {
        final boolean b2 = !b.o && !b.m.d();
        if (b.w != null) {
            b.w.a(b2);
        }
        if (b2) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    com.facebook.ads.internal.view.f.b.f(com.facebook.ads.internal.view.f.b.this);
                }
            });
        }
    }
    
    private String getMarkupUrl() {
        if (!TextUtils.isEmpty((CharSequence)this.h.j())) {
            return this.h.j();
        }
        return this.h.a();
    }
    
    public void a() {
        if (this.w != null) {
            this.w.b();
        }
        this.k.a(true);
        if (!this.o) {
            x.a((ViewGroup)this, 500);
            this.r.setVisibility(0);
        }
    }
    
    public void a(final int n) {
        this.k.setProgress((1.0f - n / (float)this.h.c()) * 100.0f);
        this.b(n);
    }
    
    public void b() {
        if (!this.u && this.p.get() != null) {
            final com.facebook.ads.internal.view.c.a adWebView = this.getAdWebView();
            if (adWebView != null) {
                x.a((ViewGroup)this);
                adWebView.setVisibility(0);
                x.b((View)this.s);
                this.k.setVisibility(0);
                this.t.setVisibility(0);
                adWebView.animate().setStartDelay(100L).setDuration(300L).translationYBy(-50.0f);
                this.t.animate().setStartDelay(100L).setDuration(300L).translationYBy(-200.0f);
            }
        }
    }
    
    public void c() {
        if (this.h.h()) {
            this.n.a();
            return;
        }
        this.removeAllViews();
        this.g();
    }
    
    public void d() {
        if (!this.n.d()) {
            this.n.a();
        }
        else if (!this.m.c()) {
            this.m.a();
        }
    }
    
    public void e() {
        this.n.b();
        this.m.b();
    }
    
    public void f() {
        this.n.b();
        this.m.b();
        this.k.setToolbarListener(null);
        com.facebook.ads.internal.view.c.a a;
        if (this.p != null) {
            a = this.p.get();
        }
        else {
            a = null;
        }
        if (a != null) {
            a.removeJavascriptInterface("FbPlayableAd");
        }
        this.w = null;
        this.v = null;
    }
    
    public com.facebook.ads.internal.view.c.a getAdWebView() {
        if (this.p != null) {
            return this.p.get();
        }
        return null;
    }
    
    private static class a implements View$OnClickListener
    {
        final WeakReference<b> a;
        
        a(final b b) {
            this.a = new WeakReference<b>(b);
        }
        
        public void onClick(final View view) {
            if (this.a.get() != null) {
                com.facebook.ads.internal.view.f.b.g(this.a.get());
            }
        }
    }
    
    private static class b implements View$OnTouchListener
    {
        final WeakReference<com.facebook.ads.internal.view.c.a> a;
        final com.facebook.ads.internal.s.c b;
        final o c;
        
        private b(final com.facebook.ads.internal.view.c.a a, final com.facebook.ads.internal.s.c b, final o c) {
            this.a = new WeakReference<com.facebook.ads.internal.view.c.a>(a);
            this.b = b;
            this.c = c;
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (this.a.get() != null && motionEvent.getAction() == 1) {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                this.a.get().getViewabilityChecker().a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.a.get().getTouchDataRecorder().e()));
                this.b.d(this.c.g(), hashMap);
            }
            return false;
        }
    }
    
    public interface c
    {
        void a();
        
        void a(final com.facebook.ads.internal.x.a p0, final w p1);
        
        void a(final boolean p0);
        
        void b();
        
        void c();
        
        void d();
    }
    
    private class d
    {
        @JavascriptInterface
        public void onCTAClick() {
            com.facebook.ads.internal.view.f.b.g(com.facebook.ads.internal.view.f.b.this);
        }
    }
}
