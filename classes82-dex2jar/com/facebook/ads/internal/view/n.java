// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.MotionEvent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.content.Intent;
import com.facebook.ads.internal.view.component.a.g;
import java.util.Map;
import android.text.TextUtils;
import java.util.HashMap;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.i.b.m;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.e;
import android.content.Context;
import com.facebook.ads.internal.view.i.c;
import java.util.concurrent.atomic.AtomicBoolean;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.h.b;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.view.i.a;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.AudienceNetworkActivity;

public class n extends o
{
    private boolean A;
    private boolean B;
    private final AudienceNetworkActivity.BackButtonInterceptor d;
    private final f e;
    private final l f;
    private final j g;
    private final d h;
    private final com.facebook.ads.internal.view.i.b.n i;
    private final a j;
    private final com.facebook.ads.internal.view.i.c.o k;
    private final com.facebook.ads.internal.view.i.c.f l;
    private final k m;
    private final com.facebook.ads.internal.adapters.b.l n;
    private final com.facebook.ads.internal.x.a o;
    private final a.a p;
    private final w q;
    @Nullable
    private final b r;
    private final AtomicBoolean s;
    private final AtomicBoolean t;
    private final c u;
    @Nullable
    private AudienceNetworkActivity v;
    @Nullable
    private com.facebook.ads.internal.view.i.a.a w;
    @Nullable
    private com.facebook.ads.internal.view.component.a.l x;
    private boolean y;
    private boolean z;
    
    public n(final Context context, final com.facebook.ads.internal.s.c c, final k m, @Nullable final b r, final a.a a) {
        super(context, c, a);
        this.d = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                final boolean b = false;
                if (com.facebook.ads.internal.view.n.this.x == null || !com.facebook.ads.internal.view.n.this.x.c()) {
                    final boolean b2 = b;
                    if (com.facebook.ads.internal.view.n.this.b.a()) {
                        return b2;
                    }
                }
                return true;
            }
        };
        this.e = new f() {
            @Override
            public void a(final e e) {
                if (com.facebook.ads.internal.view.n.this.getAudienceNetworkListener() != null) {
                    com.facebook.ads.internal.view.n.this.getAudienceNetworkListener().a("videoInterstitalEvent", e);
                }
                if (!com.facebook.ads.internal.view.n.this.y) {
                    com.facebook.ads.internal.view.n.this.j.g();
                    com.facebook.ads.internal.view.n.this.j.l();
                    com.facebook.ads.internal.view.n.this.y = true;
                }
                if (com.facebook.ads.internal.view.n.this.v != null) {
                    com.facebook.ads.internal.view.n.this.v.finish();
                }
            }
        };
        this.f = new l() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.k k) {
                if (com.facebook.ads.internal.view.n.this.getAudienceNetworkListener() != null) {
                    com.facebook.ads.internal.view.n.this.getAudienceNetworkListener().a("videoInterstitalEvent", k);
                }
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                if (com.facebook.ads.internal.view.n.this.getAudienceNetworkListener() != null) {
                    com.facebook.ads.internal.view.n.this.getAudienceNetworkListener().a("videoInterstitalEvent", i);
                }
            }
        };
        this.h = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                com.facebook.ads.internal.view.n.this.s.set(true);
                if (com.facebook.ads.internal.view.n.this.getAudienceNetworkListener() != null) {
                    com.facebook.ads.internal.view.n.this.getAudienceNetworkListener().a("videoInterstitalEvent", c);
                }
            }
        };
        this.i = new com.facebook.ads.internal.view.i.b.n() {
            @Override
            public void a(final m m) {
                n.this.B = true;
                if (!n.this.y) {
                    n.this.t.set(n.this.j.k());
                    n.this.a();
                }
                if (n.this.getAudienceNetworkListener() != null) {
                    n.this.getAudienceNetworkListener().a("videoInterstitalEvent", m);
                }
                n.this.o.a();
            }
        };
        this.q = new w();
        this.s = new AtomicBoolean(false);
        this.t = new AtomicBoolean(false);
        this.y = false;
        this.z = false;
        this.A = false;
        this.B = false;
        (this.j = new a(this.getContext())).setVideoProgressReportIntervalMs(m.h());
        com.facebook.ads.internal.w.b.x.a((View)this.j);
        com.facebook.ads.internal.w.b.x.a((View)this.j, 0);
        this.m = m;
        this.n = this.m.d().get(0);
        this.r = r;
        this.k = new com.facebook.ads.internal.view.i.c.o(this.getContext());
        this.l = new com.facebook.ads.internal.view.i.c.f(context);
        this.j.getEventBus().a(this.f, this.g, this.h, this.e, this.i);
        this.setupPlugins(this.n);
        this.p = new a.a() {
            @Override
            public void a() {
                if (!n.this.q.b()) {
                    n.this.q.a();
                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    if (!TextUtils.isEmpty((CharSequence)n.this.m.c())) {
                        n.this.o.a(hashMap);
                        hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(n.this.q.e()));
                        n.this.a(hashMap);
                        n.this.a.a(n.this.m.c(), hashMap);
                        if (n.this.getAudienceNetworkListener() != null) {
                            n.this.getAudienceNetworkListener().a("com.facebook.ads.interstitial.impression.logged");
                        }
                    }
                }
            }
        };
        (this.o = new com.facebook.ads.internal.x.a((View)this, 1, this.p)).a(m.f());
        this.o.b(m.g());
        this.u = new com.facebook.ads.internal.view.i.b(this.getContext(), this.a, this.j, this.m.c());
        final a j = this.j;
        final String a2 = this.n.c().a();
        String c2;
        final String s = c2 = "";
        if (this.r != null) {
            c2 = s;
            if (a2 != null) {
                c2 = this.r.c(a2);
            }
        }
        String videoURI = c2;
        if (TextUtils.isEmpty((CharSequence)c2)) {
            videoURI = a2;
        }
        j.setVideoURI(videoURI);
    }
    
    private void a() {
        final com.facebook.ads.internal.view.i.c.f l = this.l;
        int visibility;
        if (this.t.get()) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        l.setVisibility(visibility);
    }
    
    private void setUpContent(final int n) {
        final com.facebook.ads.internal.view.component.a.e a = new com.facebook.ads.internal.view.component.a.e.a(this.getContext(), this.a, this.getAudienceNetworkListener(), this.m, (View)this.j, this.o, this.q).a(com.facebook.ads.internal.view.i.a).b(n).a(this.k).a((View)this.l).a();
        final com.facebook.ads.internal.view.component.a.c a2 = com.facebook.ads.internal.view.component.a.d.a(a);
        this.a();
        this.x = com.facebook.ads.internal.view.component.a.g.a(a, com.facebook.ads.internal.w.b.x.a.heightPixels - a2.getExactMediaHeightIfAvailable(), com.facebook.ads.internal.w.b.x.a.widthPixels - a2.getExactMediaWidthIfAvailable(), this.A);
        u.a a3 = null;
        if (this.x != null) {
            a3 = new u.a() {
                @Override
                public void a() {
                    if (com.facebook.ads.internal.view.n.this.j.m() && !com.facebook.ads.internal.view.n.this.j.n()) {
                        com.facebook.ads.internal.view.n.this.j.a(com.facebook.ads.internal.view.i.a.a.c);
                    }
                    com.facebook.ads.internal.view.n.this.x.b();
                }
                
                @Override
                public void b() {
                    com.facebook.ads.internal.view.n.this.x.a();
                    com.facebook.ads.internal.view.n.this.j.a(false);
                }
            };
        }
        this.a(a2, this.x, a3, a2.getExactMediaHeightIfAvailable(), com.facebook.ads.internal.w.b.x.a.widthPixels - a2.getExactMediaWidthIfAvailable(), a2.a(), n);
    }
    
    private void setupPlugins(final com.facebook.ads.internal.adapters.b.l l) {
        this.j.d();
        this.j.a(this.k);
        this.j.a(this.l);
        if (!TextUtils.isEmpty((CharSequence)l.c().g())) {
            final com.facebook.ads.internal.view.i.c.g g = new com.facebook.ads.internal.view.i.c.g(this.getContext());
            this.j.a(g);
            g.setImage(l.c().g());
        }
        final com.facebook.ads.internal.view.i.c.l i = new com.facebook.ads.internal.view.i.c.l(this.getContext(), true);
        this.j.a(i);
        com.facebook.ads.internal.view.i.c.d.a a;
        if (l.c().e()) {
            a = com.facebook.ads.internal.view.i.c.d.a.c;
        }
        else {
            a = com.facebook.ads.internal.view.i.c.d.a.a;
        }
        this.j.a(new com.facebook.ads.internal.view.i.c.d((View)i, a, true));
        this.j.a(new com.facebook.ads.internal.view.i.c.k(this.getContext()));
        this.j.a(this.b);
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity v) {
        super.a(v, this.m);
        this.v = v;
        this.setUpContent(v.getResources().getConfiguration().orientation);
        this.v.addBackButtonInterceptor(this.d);
        final com.facebook.ads.internal.adapters.b.l l = this.m.d().get(0);
        final a j = this.j;
        float volume;
        if (l.c().f()) {
            volume = 0.0f;
        }
        else {
            volume = 1.0f;
        }
        j.setVolume(volume);
        if (l.c().e()) {
            this.j.a(com.facebook.ads.internal.view.i.a.a.c);
        }
        if (l.c().c() > 0) {
            this.postDelayed((Runnable)new Runnable() {
                @Override
                public void run() {
                    if (!com.facebook.ads.internal.view.n.this.B) {
                        com.facebook.ads.internal.view.n.this.b.a(true);
                    }
                }
            }, (long)com.facebook.ads.internal.r.a.ad(this.getContext()));
        }
    }
    
    public void a(final Bundle bundle) {
    }
    
    public void a_(final boolean z) {
        if (this.x != null) {
            this.x.e();
        }
        if (this.y || this.j.m()) {
            return;
        }
        this.w = this.j.getVideoStartReason();
        this.z = z;
        this.j.a(false);
    }
    
    public void b(final boolean b) {
        if (this.x != null) {
            this.x.f();
        }
        if (!this.y && !this.j.n() && (this.j.getState() != com.facebook.ads.internal.view.i.d.d.c || this.j.getVideoStartReason() != com.facebook.ads.internal.view.i.a.a.a) && this.j.getState() != com.facebook.ads.internal.view.i.d.d.g && this.w != null && (!this.z || b)) {
            this.j.a(this.w);
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        this.removeAllViews();
        com.facebook.ads.internal.w.b.x.b((View)this.j);
        com.facebook.ads.internal.w.b.x.b((View)this.k);
        com.facebook.ads.internal.w.b.x.b((View)this.l);
        if (this.x != null) {
            com.facebook.ads.internal.w.b.x.b((View)this.x);
            this.A = this.x.d();
        }
        this.setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }
    
    @Override
    public void onDestroy() {
        if (!this.y) {
            if (!this.s.get()) {
                this.j.f();
            }
            if (this.m != null && !TextUtils.isEmpty((CharSequence)this.m.c())) {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                this.o.a(hashMap);
                hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.q.e()));
                this.a.l(this.m.c(), hashMap);
            }
            this.j.g();
            this.j.l();
            this.y = true;
        }
        if (this.x != null) {
            this.x.g();
        }
        this.o.c();
        this.v = null;
        super.onDestroy();
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.q.a(motionEvent, (View)this, (View)this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
