// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import com.facebook.ads.internal.w.e.f;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.o.d;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.w.e.e;
import android.text.TextUtils;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.w.b.w;
import android.view.View;
import com.facebook.ads.internal.adapters.b.o;
import android.os.Bundle;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.p;
import java.util.concurrent.Executor;
import com.facebook.ads.AudienceNetworkActivity;
import android.support.annotation.Nullable;
import android.content.Context;
import com.facebook.ads.internal.adapters.b.n;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.s.c;
import android.annotation.TargetApi;
import com.facebook.ads.internal.view.f.b;
import android.widget.RelativeLayout;

@TargetApi(16)
public class r extends RelativeLayout implements com.facebook.ads.internal.view.a, c
{
    private final com.facebook.ads.internal.s.c a;
    private final q b;
    private final n c;
    private final com.facebook.ads.internal.adapters.b.b d;
    private int e;
    @Nullable
    private Context f;
    @Nullable
    private AudienceNetworkActivity g;
    @Nullable
    private com.facebook.ads.internal.view.a.a h;
    private Executor i;
    private final AudienceNetworkActivity.BackButtonInterceptor j;
    private boolean k;
    private b l;
    private boolean m;
    private com.facebook.ads.internal.adapters.r n;
    
    public r(final Context f, final com.facebook.ads.internal.s.c a, final com.facebook.ads.internal.view.a.a h, final q b) {
        super(f);
        this.i = com.facebook.ads.internal.w.b.p.a;
        this.j = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                return !r.this.m;
            }
        };
        this.f = f;
        this.h = h;
        this.a = a;
        this.b = b;
        this.c = b.j().j();
        this.d = b.i();
    }
    
    @NonNull
    private com.facebook.ads.internal.view.component.a a(final com.facebook.ads.internal.view.c.a a) {
        return new com.facebook.ads.internal.view.component.a(this.f, true, false, aa.e.a(), this.d.a(), this.a, this.h, a.getViewabilityChecker(), a.getTouchDataRecorder());
    }
    
    static /* synthetic */ void b(final r r) {
        if (r.h != null) {
            r.h.a(aa.f.a());
        }
    }
    
    public void a() {
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity g) {
        if (this.h == null || this.f == null) {
            return;
        }
        (this.g = g).addBackButtonInterceptor(this.j);
        this.e = g.getRequestedOrientation();
        switch (r$3.a[this.c.f().ordinal()]) {
            case 1: {
                g.setRequestedOrientation(1);
                break;
            }
            case 2: {
                g.setRequestedOrientation(0);
                break;
            }
            case 3: {
                g.setRequestedOrientation(-1);
                break;
            }
        }
        final b l = new b(this.f, com.facebook.ads.internal.adapters.b.o.a(this.b), this.a, this.h, (c)this, true, false);
        this.addView((View)(this.l = l));
        this.h.a((View)this);
        l.c();
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a(final com.facebook.ads.internal.x.a a, final w w) {
        com.facebook.ads.internal.adapters.r r;
        if (this.n != null) {
            r = this.n;
        }
        else {
            (this.n = new com.facebook.ads.internal.adapters.r(this.getContext(), this.a, a, w, new com.facebook.ads.internal.adapters.c() {
                @Override
                public void a() {
                    r.b(r.this);
                }
            })).a(this.b);
            r = this.n;
        }
        r.a();
    }
    
    public void a(final boolean b) {
        this.k = true;
        final com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (adWebView == null) {
            return;
        }
        final com.facebook.ads.internal.view.component.a a = this.a(adWebView);
        a.a(this.b.h(), this.b.a(), new HashMap<String, String>(), b);
        a.performClick();
    }
    
    public void a_(final boolean b) {
        this.l.e();
    }
    
    public void b() {
        this.m = true;
        final String a = this.b.k().a();
        if (this.f != null || !TextUtils.isEmpty((CharSequence)a)) {
            final e e = new e(this.f, new HashMap<String, String>());
            e.a((com.facebook.ads.internal.w.e.e.a)new a(new WeakReference((T)this.h)));
            e.executeOnExecutor(this.i, (Object[])new String[] { a });
        }
        if (this.h != null) {
            this.h.a(aa.a.a(), new com.facebook.ads.internal.view.i.b.c(0, 0));
        }
        final com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (this.k && adWebView != null) {
            this.a(adWebView).b(this.b.h(), this.b.a(), new HashMap<String, String>());
        }
    }
    
    public void b(final boolean b) {
        this.l.d();
    }
    
    public void c() {
        if (this.h != null) {
            this.h.a(aa.c.a());
        }
    }
    
    public void d() {
        if (this.h != null) {
            this.h.a(aa.d.a());
        }
    }
    
    public void onDestroy() {
        if (this.g != null) {
            this.g.removeBackButtonInterceptor(this.j);
            this.g.setRequestedOrientation(this.e);
        }
        final com.facebook.ads.internal.view.c.a adWebView = this.l.getAdWebView();
        if (adWebView != null && !TextUtils.isEmpty((CharSequence)this.b.a())) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            adWebView.getViewabilityChecker().a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(adWebView.getTouchDataRecorder().e()));
            this.a.l(this.b.a(), hashMap);
        }
        this.l.f();
        this.h = null;
        this.g = null;
        this.f = null;
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.l.getAdWebView() == null) {
            return;
        }
        if (b) {
            this.b(false);
            return;
        }
        this.a_(false);
    }
    
    public void setListener(final com.facebook.ads.internal.view.a.a h) {
        this.h = h;
    }
    
    private static class a implements com.facebook.ads.internal.w.e.e.a
    {
        final WeakReference<com.facebook.ads.internal.view.a.a> a;
        
        private a(final WeakReference<com.facebook.ads.internal.view.a.a> a) {
            this.a = a;
        }
        
        @Override
        public void a() {
            if (this.a.get() != null) {
                ((com.facebook.ads.internal.view.a.a)this.a.get()).a(aa.i.a());
            }
        }
        
        @Override
        public void a(final com.facebook.ads.internal.w.e.f f) {
            if (this.a.get() == null) {
                return;
            }
            if (f != null && f.a()) {
                ((com.facebook.ads.internal.view.a.a)this.a.get()).a(aa.h.a());
                return;
            }
            ((com.facebook.ads.internal.view.a.a)this.a.get()).a(aa.i.a());
        }
    }
}
