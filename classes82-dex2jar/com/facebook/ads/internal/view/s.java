// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.support.annotation.VisibleForTesting;
import android.view.MotionEvent;
import android.content.res.Configuration;
import android.view.View$OnClickListener;
import android.os.Bundle;
import android.content.Intent;
import android.util.Pair;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import android.text.TextUtils;
import java.util.HashMap;
import android.view.ViewGroup;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.x;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.view.i.c.o;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.adapters.b.q;
import com.facebook.ads.internal.view.i.b.p;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.AudienceNetworkActivity;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.view.g.c;
import com.facebook.ads.internal.view.i.c.j;
import android.view.View;
import com.facebook.ads.internal.view.i.c.l;
import com.facebook.ads.internal.view.i.c.d;
import android.support.annotation.Nullable;
import android.content.Context;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;

public class s extends RelativeLayout implements a
{
    static final /* synthetic */ boolean a;
    private static final int b;
    private static final int c;
    private static final int d;
    private static final int e;
    private static final int f;
    private static final int g;
    private static final int h;
    private static final int i;
    private static final RelativeLayout$LayoutParams j;
    @Nullable
    private Context A;
    @Nullable
    private com.facebook.ads.internal.view.i.a B;
    @Nullable
    private a C;
    @Nullable
    private com.facebook.ads.internal.view.g.a D;
    @Nullable
    private d E;
    @Nullable
    private l F;
    @Nullable
    private View G;
    @Nullable
    private j H;
    @Nullable
    private i I;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a J;
    @Nullable
    private Integer K;
    private c L;
    private boolean M;
    private boolean N;
    private WeakReference<AudienceNetworkActivity> O;
    private final AudienceNetworkActivity.BackButtonInterceptor k;
    private final com.facebook.ads.internal.view.i.b.d l;
    private final f m;
    private final n n;
    private final p o;
    private final q p;
    private final com.facebook.ads.internal.s.c q;
    private final com.facebook.ads.internal.x.a r;
    private final a.a s;
    private final w t;
    private final o u;
    private final b v;
    private final RelativeLayout w;
    private final com.facebook.ads.internal.view.i.c.f x;
    private final h y;
    private final AtomicBoolean z;
    
    static {
        a = !s.class.desiredAssertionStatus();
        b = (int)(12.0f * x.b);
        c = (int)(18.0f * x.b);
        d = (int)(16.0f * x.b);
        e = (int)(72.0f * x.b);
        f = (int)(x.b * 56.0f);
        g = (int)(x.b * 56.0f);
        h = (int)(28.0f * x.b);
        i = (int)(20.0f * x.b);
        j = new RelativeLayout$LayoutParams(-1, -1);
    }
    
    public s(final Context a, final com.facebook.ads.internal.s.c q, final com.facebook.ads.internal.view.i.a b, final a c, final q p5) {
        super(a);
        this.k = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                return !com.facebook.ads.internal.view.s.this.M;
            }
        };
        this.l = new com.facebook.ads.internal.view.i.b.d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (com.facebook.ads.internal.view.s.this.C != null) {
                    com.facebook.ads.internal.view.s.this.L.d();
                    com.facebook.ads.internal.view.s.d(com.facebook.ads.internal.view.s.this);
                    com.facebook.ads.internal.view.s.this.C.a(aa.a.a(), c);
                }
            }
        };
        this.m = new f() {
            @Override
            public void a(final e e) {
                if (com.facebook.ads.internal.view.s.this.C != null) {
                    com.facebook.ads.internal.view.s.this.C.a(aa.d.a());
                }
                com.facebook.ads.internal.view.s.this.a();
            }
        };
        this.n = new n() {
            @Override
            public void a(final m m) {
                if (com.facebook.ads.internal.view.s.this.B != null) {
                    com.facebook.ads.internal.view.s.this.B.a(com.facebook.ads.internal.view.i.a.a.b);
                    com.facebook.ads.internal.view.s.this.r.a();
                    com.facebook.ads.internal.view.s.this.z.set(com.facebook.ads.internal.view.s.this.B.k());
                    com.facebook.ads.internal.view.s.this.b();
                }
            }
        };
        this.o = new p() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.o o) {
                if (com.facebook.ads.internal.view.s.this.B != null && com.facebook.ads.internal.view.s.this.E != null && com.facebook.ads.internal.view.s.this.B.getDuration() - com.facebook.ads.internal.view.s.this.B.getCurrentPositionInMillis() <= 3000 && com.facebook.ads.internal.view.s.this.E.a()) {
                    com.facebook.ads.internal.view.s.this.E.b();
                }
            }
        };
        this.t = new w();
        this.z = new AtomicBoolean(false);
        this.M = false;
        this.N = false;
        this.A = a;
        this.C = c;
        this.B = b;
        this.q = q;
        this.p = p5;
        this.y = this.p.i().a();
        this.w = new RelativeLayout(a);
        this.u = new o(this.A);
        this.x = new com.facebook.ads.internal.view.i.c.f(this.A);
        new com.facebook.ads.internal.view.c.d((ViewGroup)this.w, com.facebook.ads.internal.view.s.i).a().a(com.facebook.ads.internal.r.a.m(this.A)).a(this.p.j().g());
        this.s = new a.a() {
            @Override
            public void a() {
                if (!com.facebook.ads.internal.view.s.this.t.b()) {
                    com.facebook.ads.internal.view.s.this.t.a();
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.view.s.this.p.a())) {
                        com.facebook.ads.internal.view.s.this.r.a(hashMap);
                        hashMap.put("touch", k.a(com.facebook.ads.internal.view.s.this.t.e()));
                        if (com.facebook.ads.internal.view.s.this.p.d() != null) {
                            hashMap.put("extra_hints", com.facebook.ads.internal.view.s.this.p.d());
                        }
                        hashMap.put("is_cyoa", String.valueOf(com.facebook.ads.internal.view.s.this.p.l()));
                        com.facebook.ads.internal.view.s.this.q.a(com.facebook.ads.internal.view.s.this.p.a(), hashMap);
                    }
                    if (com.facebook.ads.internal.view.s.this.C != null) {
                        com.facebook.ads.internal.view.s.this.C.a(aa.f.a());
                    }
                }
            }
        };
        (this.r = new com.facebook.ads.internal.x.a((View)this, 1, this.s)).a(250);
        this.v = new b(this.A, this.q, this.B, this.p.a());
        this.L = new c(this.A, this.q, this.p, this.C, this.r, this.t);
        if (!com.facebook.ads.internal.view.s.a && this.B == null) {
            throw new AssertionError();
        }
        this.B.setVideoProgressReportIntervalMs(p5.b());
        com.facebook.ads.internal.w.b.x.a((View)this.B, -16777216);
        this.B.getEventBus().a(this.l, this.m, this.n, this.o);
    }
    
    private void b() {
        final com.facebook.ads.internal.view.i.c.f x = this.x;
        int visibility;
        if (this.z.get()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        x.setVisibility(visibility);
    }
    
    static /* synthetic */ void d(final s s) {
        s.M = true;
        if (s.A != null) {
            final FrameLayout frameLayout = new FrameLayout(s.A);
            frameLayout.setLayoutParams((ViewGroup$LayoutParams)s.j);
            x.a((View)frameLayout, -1509949440);
            s.w.addView((View)frameLayout, 0);
        }
        x.a((ViewGroup)s.w);
        if (s.B != null) {
            s.B.d();
            s.B.setVisibility(4);
        }
        if (s.I != null) {
            if (s.I.a()) {
                s.I.b();
                new Handler(Looper.getMainLooper()).postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (com.facebook.ads.internal.view.s.this.I != null) {
                            com.facebook.ads.internal.view.s.this.I.setCloseButtonStyle(com.facebook.ads.internal.view.i.a.a);
                            com.facebook.ads.internal.view.s.this.I.a(true);
                        }
                    }
                }, 1000L);
            }
            else {
                s.I.a(true);
                s.I.setCloseButtonStyle(com.facebook.ads.internal.view.i.a.a);
            }
            s.I.c();
        }
        x.a((View)s.B, s.H, (View)s.x, (View)s.u);
        final Pair<c.a, View> c = s.L.c();
        switch (s$2.a[((c.a)c.first).ordinal()]) {
            default: {}
            case 1: {
                x.a(new View[] { (View)s.D });
                s.w.addView((View)c.second, (ViewGroup$LayoutParams)s.j);
            }
            case 2: {
                if (s.D != null) {
                    s.D.setVisibility(0);
                    s.D.a();
                }
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
                relativeLayout$LayoutParams.setMargins(0, s.g, 0, 0);
                relativeLayout$LayoutParams.addRule(2, s.D.getId());
                s.w.addView((View)c.second, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                s.t.a();
            }
            case 3: {
                x.a(new View[] { (View)s.D });
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(-1, -2);
                relativeLayout$LayoutParams2.addRule(15);
                relativeLayout$LayoutParams2.setMargins(s.d, s.d, s.d, s.d);
                s.w.addView((View)c.second, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
                s.t.a();
            }
            case 4: {
                final AudienceNetworkActivity audienceNetworkActivity = s.O.get();
                if (audienceNetworkActivity != null) {
                    s.K = audienceNetworkActivity.getRequestedOrientation();
                    audienceNetworkActivity.setRequestedOrientation(1);
                }
                s.w.removeAllViews();
                x.b((View)s.I);
                s.w.addView((View)c.second, (ViewGroup$LayoutParams)s.j);
                ((com.facebook.ads.internal.view.f.b)c.second).c();
            }
        }
    }
    
    private void setUpContentLayoutForVideo(final int n) {
        this.w.removeAllViews();
        this.w.addView((View)this.B, (ViewGroup$LayoutParams)com.facebook.ads.internal.view.s.j);
        if (this.D != null) {
            com.facebook.ads.internal.w.b.x.a((View)this.D);
            this.D.a(n);
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -2);
            relativeLayout$LayoutParams.addRule(12);
            this.D.setPadding(com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d);
            this.w.addView((View)this.D, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        }
        if (this.H != null) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(com.facebook.ads.internal.view.s.f, com.facebook.ads.internal.view.s.f);
            relativeLayout$LayoutParams2.addRule(10);
            relativeLayout$LayoutParams2.addRule(11);
            this.H.setPadding(com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d, com.facebook.ads.internal.view.s.d);
            this.w.addView((View)this.H, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(com.facebook.ads.internal.view.s.h, com.facebook.ads.internal.view.s.h);
        relativeLayout$LayoutParams3.addRule(10);
        relativeLayout$LayoutParams3.addRule(11);
        relativeLayout$LayoutParams3.setMargins(com.facebook.ads.internal.view.s.b, com.facebook.ads.internal.view.s.b + com.facebook.ads.internal.view.s.g, com.facebook.ads.internal.view.s.b, com.facebook.ads.internal.view.s.c);
        this.w.addView((View)this.x, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        this.b();
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams4 = new RelativeLayout$LayoutParams(-1, -2);
        relativeLayout$LayoutParams4.addRule(12);
        this.w.addView((View)this.u, (ViewGroup$LayoutParams)relativeLayout$LayoutParams4);
    }
    
    public void a() {
        if (this.B != null) {
            this.B.g();
            this.B.l();
        }
        if (this.r != null) {
            this.r.c();
        }
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        boolean b = false;
        if (this.B == null || this.C == null) {
            return;
        }
        this.O = new WeakReference<AudienceNetworkActivity>(audienceNetworkActivity);
        if (this.B != null) {
            this.B.d();
            this.B.a(new com.facebook.ads.internal.view.i.c.k(this.A));
            this.B.a(this.x);
            this.B.a(this.u);
            this.F = new l(this.A, true);
            (this.G = new View(this.A)).setLayoutParams((ViewGroup$LayoutParams)com.facebook.ads.internal.view.s.j);
            com.facebook.ads.internal.w.b.x.a(this.G, -1509949440);
            final d d = new d(this.G, com.facebook.ads.internal.view.i.c.d.a.c, true);
            this.B.addView(this.G);
            this.B.a(d);
            final d d2 = new d((View)this.F, com.facebook.ads.internal.view.i.c.d.a.c, true);
            this.B.a(this.F);
            this.B.a(d2);
            final Context a = this.A;
            final int e = com.facebook.ads.internal.view.s.e;
            final h y = this.y;
            final com.facebook.ads.internal.s.c q = this.q;
            final a c = this.C;
            final boolean b2 = this.L.b() == com.facebook.ads.internal.view.g.c.a.d;
            if (this.L.b() == com.facebook.ads.internal.view.g.c.a.d) {
                b = true;
            }
            (this.D = new com.facebook.ads.internal.view.g.a(a, e, y, q, c, b2, b, this.r, this.t)).setInfo(this.p);
            this.E = new d((View)this.D, com.facebook.ads.internal.view.i.c.d.a.c, true);
            this.B.a(this.E);
            if (this.L.a() && this.p.j().c() > 0) {
                (this.H = new j(this.A, this.p.j().c(), -12286980)).setButtonMode(com.facebook.ads.internal.view.i.c.j.a.b);
                this.H.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (com.facebook.ads.internal.view.s.this.H != null && com.facebook.ads.internal.view.s.this.H.a() && com.facebook.ads.internal.view.s.this.H.getSkipSeconds() != 0 && com.facebook.ads.internal.view.s.this.B != null) {
                            com.facebook.ads.internal.view.s.this.B.f();
                        }
                    }
                });
                this.B.a(this.H);
            }
            else if (!this.L.a()) {
                i.a a2;
                if (com.facebook.ads.internal.r.a.K(this.A)) {
                    a2 = com.facebook.ads.internal.view.i.a.b;
                }
                else {
                    a2 = com.facebook.ads.internal.view.i.a.a;
                }
                (this.I = new i(this.A, this.C, a2)).a(this.p.f(), this.p.a(), this.p.j().c());
                if (this.p.j().c() <= 0) {
                    this.I.b();
                }
                if (this.L.b() != com.facebook.ads.internal.view.g.c.a.d) {
                    this.I.c();
                }
                this.I.setToolbarListener((i.b)new i.b() {
                    @Override
                    public void a() {
                        if (s.this.t.a(s.this.getContext())) {
                            final HashMap<String, String> hashMap = new HashMap<String, String>();
                            s.this.r.a(hashMap);
                            hashMap.put("touch", k.a(s.this.t.e()));
                            s.this.q.i(s.this.p.a(), hashMap);
                        }
                        else {
                            if (!s.this.M && s.this.B != null) {
                                s.this.M = true;
                                s.this.B.f();
                                return;
                            }
                            if (s.this.M && s.this.C != null) {
                                s.this.C.a(aa.c.a());
                            }
                        }
                    }
                });
                this.B.a(this.I);
            }
        }
        audienceNetworkActivity.addBackButtonInterceptor(this.k);
        String videoURI;
        if (!TextUtils.isEmpty((CharSequence)this.p.j().b())) {
            videoURI = this.p.j().b();
        }
        else {
            videoURI = this.p.j().a();
        }
        this.B.setVideoURI(videoURI);
        this.setUpContentLayoutForVideo(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.addView((View)this.w, (ViewGroup$LayoutParams)com.facebook.ads.internal.view.s.j);
        if (this.I != null) {
            com.facebook.ads.internal.w.b.x.a((View)this.I);
            this.I.a(this.y, true);
            if (com.facebook.ads.internal.f.a.a(this.getContext(), true)) {
                this.I.a(this.p.f(), this.p.a());
            }
            this.addView((View)this.I, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, com.facebook.ads.internal.view.s.g));
        }
        this.setLayoutParams((ViewGroup$LayoutParams)com.facebook.ads.internal.view.s.j);
        this.C.a((View)this);
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a_(final boolean n) {
        if (this.B == null || this.B.m()) {
            return;
        }
        this.J = this.B.getVideoStartReason();
        this.N = n;
        this.B.a(false);
    }
    
    public void b(final boolean b) {
        if (this.B != null && !this.B.n() && this.B.getState() != com.facebook.ads.internal.view.i.d.d.g && this.J != null && (!this.N || b)) {
            this.B.a(this.J);
        }
    }
    
    public int getCurrentPosition() {
        if (this.B != null) {
            return this.B.getCurrentPositionInMillis();
        }
        return 0;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        if (this.D != null) {
            this.D.a(configuration.orientation);
        }
    }
    
    public void onDestroy() {
        this.a();
        if (this.B != null) {
            this.B.getEventBus().b(this.l, this.m, this.n, this.o);
        }
        if (!TextUtils.isEmpty((CharSequence)this.p.a())) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.r.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.t.e()));
            this.q.l(this.p.a(), hashMap);
        }
        if (this.I != null) {
            this.I.setToolbarListener(null);
        }
        if (this.K != null && this.O.get() != null) {
            this.O.get().setRequestedOrientation((int)this.K);
        }
        this.v.a();
        this.B = null;
        this.L.e();
        this.H = null;
        this.D = null;
        this.E = null;
        this.C = null;
        this.A = null;
        this.u.a();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.t.a(motionEvent, (View)this, (View)this);
        return super.onInterceptTouchEvent(motionEvent);
    }
    
    @VisibleForTesting
    void setEndCardController(final c l) {
        this.L = l;
    }
    
    public void setListener(final a a) {
    }
}
