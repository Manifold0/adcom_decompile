// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.MotionEvent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.content.Intent;
import java.util.Iterator;
import android.view.View$OnTouchListener;
import android.widget.LinearLayout$LayoutParams;
import java.util.List;
import com.facebook.ads.internal.o.d;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.s.h;
import android.view.View$OnClickListener;
import com.facebook.ads.internal.adapters.b.q;
import android.view.ViewGroup$LayoutParams;
import android.graphics.Color;
import android.view.View;
import com.facebook.ads.internal.view.g.b;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.view.component.i;
import com.facebook.ads.AudienceNetworkActivity;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.w.b.e;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.s.c;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;

public class f extends RelativeLayout implements com.facebook.ads.internal.view.a
{
    private static final RelativeLayout$LayoutParams a;
    private static final int b;
    private static final int c;
    private static final int d;
    private final com.facebook.ads.internal.adapters.b.f e;
    private final com.facebook.ads.internal.view.a.a f;
    private final com.facebook.ads.internal.s.c g;
    private final w h;
    private final com.facebook.ads.internal.x.a i;
    private final com.facebook.ads.internal.x.a.a j;
    private final e k;
    private final int l;
    private boolean m;
    private boolean n;
    private WeakReference<AudienceNetworkActivity> o;
    private final i p;
    private final TextView q;
    private final LinearLayout r;
    private final AudienceNetworkActivity.BackButtonInterceptor s;
    
    static {
        a = new RelativeLayout$LayoutParams(-1, -1);
        b = (int)(16.0f * x.b);
        c = (int)(56.0f * x.b);
        d = (int)(230.0f * x.b);
    }
    
    public f(final Context context, final com.facebook.ads.internal.adapters.b.f e, final com.facebook.ads.internal.s.c g, final com.facebook.ads.internal.view.a.a f) {
        final int n = 1;
        super(context);
        this.h = new w();
        this.s = new AudienceNetworkActivity.BackButtonInterceptor() {
            @Override
            public boolean interceptBackButton() {
                return true;
            }
        };
        this.e = e;
        this.g = g;
        this.l = this.e.i().a() / 1000;
        this.f = f;
        this.j = new com.facebook.ads.internal.x.a.a() {
            @Override
            public void a() {
                if (!f.this.h.b()) {
                    f.this.h.a();
                    for (int i = 0; i < f.this.r.getChildCount(); ++i) {
                        if (f.this.r.getChildAt(i) instanceof b) {
                            final b b = (b)f.this.r.getChildAt(i);
                            b.a(i);
                            b.setViewability(true);
                        }
                    }
                    if (!f.this.m) {
                        f.this.k.a();
                    }
                }
            }
        };
        (this.i = new com.facebook.ads.internal.x.a((View)this, 1, this.j)).a(250);
        x.a((View)(this.p = new i(context)));
        x.a((View)(this.q = new TextView(this.getContext())));
        this.r = new LinearLayout(this.getContext());
        final boolean b = this.getResources().getConfiguration().orientation == 1;
        this.p.setProgress(0);
        this.p.a(false, Color.parseColor(this.e.g()), 14);
        this.p.setText(this.e.e().a(String.valueOf(this.l)));
        x.a((View)this.p, 0);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, com.facebook.ads.internal.view.f.c);
        relativeLayout$LayoutParams.addRule(10);
        this.addView((View)this.p, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.q.setText((CharSequence)this.e.e().a());
        x.a(this.q, true, 32);
        this.q.setTextColor(Color.parseColor(this.e.h()));
        int d;
        if (b) {
            d = com.facebook.ads.internal.view.f.d;
        }
        else {
            d = -1;
        }
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout$LayoutParams(d, -2);
        relativeLayout$LayoutParams2.setMargins(com.facebook.ads.internal.view.f.b, 0, com.facebook.ads.internal.view.f.b, com.facebook.ads.internal.view.f.b / 2);
        relativeLayout$LayoutParams2.addRule(3, this.p.getId());
        this.addView((View)this.q, (ViewGroup$LayoutParams)relativeLayout$LayoutParams2);
        this.r.setPadding(com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2);
        final LinearLayout r = this.r;
        int orientation;
        if (b) {
            orientation = n;
        }
        else {
            orientation = 0;
        }
        r.setOrientation(orientation);
        this.a(b, this.e.j());
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout$LayoutParams3.addRule(3, this.q.getId());
        this.addView((View)this.r, (ViewGroup$LayoutParams)relativeLayout$LayoutParams3);
        x.a((View)this, Color.parseColor(this.e.f()));
        this.k = new e(this.l, (e.a)new d(this, this.e, this.l));
        this.i.a();
    }
    
    static /* synthetic */ void a(final f f, final q q) {
        if (!f.m) {
            f.m = true;
            f.k.b();
            if (f.i != null) {
                f.i.c();
            }
            final View view = new View(f.getContext());
            view.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                }
            });
            f.addView(view, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            final h h = new h();
            for (int i = 0; i < f.r.getChildCount(); ++i) {
                final com.facebook.ads.internal.view.g.b b = (com.facebook.ads.internal.view.g.b)f.r.getChildAt(i);
                if (b.getAdDataBundle() == q) {
                    h.c(i);
                }
                b.d();
            }
            final String a = q.a();
            h.d((f.l - f.k.e()) * 1000);
            h.e(f.l * 1000);
            h.a(f.e.j().size());
            h.a(f.k.d());
            h.b(f.e.i().b());
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            f.i.a(hashMap);
            hashMap.put("touch", k.a(f.h.e()));
            hashMap.put("ad_selection", k.a(h.a()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            f.g.p(a, hashMap);
            q.a(f.e.b());
            q.a(f.e.c());
            x.c((View)f);
            x.b((View)f);
            f.f.a(aa.k.a(), new a(q));
            if (f.o != null && f.o.get() != null) {
                f.o.get().removeBackButtonInterceptor(f.s);
            }
        }
    }
    
    private void a(final boolean b, final List<q> list) {
        this.r.setWeightSum((float)list.size());
        int n;
        if (list.size() == 2) {
            n = 1;
        }
        else {
            n = 0;
        }
        final boolean shouldPlayButtonOnTop = list.size() >= 3 && !b;
        final Iterator<q> iterator = list.iterator();
        int n2 = 0;
        while (iterator.hasNext()) {
            final com.facebook.ads.internal.view.g.b b2 = new com.facebook.ads.internal.view.g.b(this.getContext(), iterator.next(), this.g, this.i, this.h, this.f);
            b2.setShouldPlayButtonOnTop(shouldPlayButtonOnTop);
            b2.a(this.e.i().d());
            b2.setCornerRadius(10);
            int n3;
            if (b) {
                n3 = -1;
            }
            else {
                n3 = 0;
            }
            int n4;
            if (b) {
                n4 = 0;
            }
            else {
                n4 = -1;
            }
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(n3, n4);
            linearLayout$LayoutParams.setMargins(com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2, com.facebook.ads.internal.view.f.b / 2);
            linearLayout$LayoutParams.weight = 1.0f;
            final b b3 = new b(this, b2);
            b2.setOnTouchListener((View$OnTouchListener)b3);
            b2.setOnClickListener((View$OnClickListener)b3);
            b2.setAdReportingFlowListener(new c(this, b2));
            if (n != 0) {
                b2.a(n2 % 2 != 0, this.e.i().c());
            }
            this.r.addView((View)b2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            ++n2;
        }
    }
    
    void a() {
        boolean b = true;
        for (int i = 0; i < this.r.getChildCount(); ++i) {
            final com.facebook.ads.internal.view.g.b b2 = (com.facebook.ads.internal.view.g.b)this.r.getChildAt(i);
            b &= b2.a();
            b2.d();
        }
        if (b && this.f != null) {
            this.f.a(aa.c.a());
        }
    }
    
    public void a(final Intent intent, final Bundle bundle, final AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f == null) {
            return;
        }
        this.setLayoutParams((ViewGroup$LayoutParams)com.facebook.ads.internal.view.f.a);
        this.f.a((View)this);
        audienceNetworkActivity.addBackButtonInterceptor(this.s);
        this.o = new WeakReference<AudienceNetworkActivity>(audienceNetworkActivity);
    }
    
    public void a(final Bundle bundle) {
        this.k.b();
    }
    
    public void a_(final boolean b) {
        this.k.b();
    }
    
    public void b(final boolean b) {
        if (!this.m && (b || !this.n)) {
            this.k.a();
        }
    }
    
    void c(final boolean b) {
        for (int i = 0; i < this.r.getChildCount(); ++i) {
            if (b) {
                ((com.facebook.ads.internal.view.g.b)this.r.getChildAt(i)).b();
            }
            else {
                ((com.facebook.ads.internal.view.g.b)this.r.getChildAt(i)).c();
            }
        }
    }
    
    final w getTouchDataRecorder() {
        return this.h;
    }
    
    protected void onConfigurationChanged(final Configuration configuration) {
        boolean shouldPlayButtonOnTop = true;
        final int n = 0;
        final boolean b = configuration.orientation == 1;
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.q.getLayoutParams();
        int d;
        if (b) {
            d = com.facebook.ads.internal.view.f.d;
        }
        else {
            d = -1;
        }
        relativeLayout$LayoutParams.width = d;
        final LinearLayout r = this.r;
        int orientation;
        if (b) {
            orientation = 1;
        }
        else {
            orientation = 0;
        }
        r.setOrientation(orientation);
        int i;
        if (this.e.j().size() >= 3 && !b) {
            i = n;
        }
        else {
            shouldPlayButtonOnTop = false;
            i = n;
        }
        while (i < this.r.getChildCount()) {
            final com.facebook.ads.internal.view.g.b b2 = (com.facebook.ads.internal.view.g.b)this.r.getChildAt(i);
            b2.b(b);
            b2.setShouldPlayButtonOnTop(shouldPlayButtonOnTop);
            ++i;
        }
    }
    
    public void onDestroy() {
        this.k.b();
        if (this.i != null) {
            this.i.c();
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.h.a(motionEvent, (View)this, (View)this);
        if (motionEvent.getAction() == 1) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.i.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.h.e()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            this.g.d(this.e.j().get(0).a(), hashMap);
        }
        return true;
    }
    
    void setIsAdReportingLayoutVisible(final boolean n) {
        this.n = n;
    }
    
    public void setListener(final com.facebook.ads.internal.view.a.a a) {
    }
    
    public static class a extends d
    {
        private q a;
        
        public a(final q a) {
            this.a = a;
        }
        
        public q a() {
            return this.a;
        }
    }
    
    private static class b implements View$OnClickListener, View$OnTouchListener
    {
        final WeakReference<f> a;
        final WeakReference<com.facebook.ads.internal.view.g.b> b;
        
        public b(final f f, final com.facebook.ads.internal.view.g.b b) {
            this.a = new WeakReference<f>(f);
            this.b = new WeakReference<com.facebook.ads.internal.view.g.b>(b);
        }
        
        public void onClick(final View view) {
            if (this.a.get() != null && this.b.get() != null && !this.b.get().a()) {
                com.facebook.ads.internal.view.f.a(this.a.get(), this.b.get().getAdDataBundle());
            }
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            if (this.a.get() != null) {
                this.a.get().getTouchDataRecorder().a(motionEvent, this.a.get(), view);
            }
            return false;
        }
    }
    
    public static class c implements com.facebook.ads.internal.view.a.b
    {
        final WeakReference<f> a;
        final WeakReference<b> b;
        
        c(final f f, final b b) {
            this.a = new WeakReference<f>(f);
            this.b = new WeakReference<b>(b);
        }
        
        @Override
        public void a() {
            final f f = this.a.get();
            if (f != null) {
                f.setIsAdReportingLayoutVisible(true);
                f.c(true);
            }
        }
        
        @Override
        public void a(final com.facebook.ads.internal.f.c c, final com.facebook.ads.internal.f.b.a a) {
            if (this.b.get() != null) {
                this.b.get().a(c, a);
            }
        }
        
        @Override
        public void a(final boolean b) {
            if (this.a.get() != null) {
                this.a.get().setIsAdReportingLayoutVisible(false);
                if (!b) {
                    this.a.get().c(false);
                    return;
                }
                this.a.get().a();
            }
        }
    }
    
    private static class d implements e.a
    {
        private final WeakReference<f> a;
        private final WeakReference<i> b;
        private final com.facebook.ads.internal.adapters.b.f c;
        private int d;
        
        public d(final f f, final com.facebook.ads.internal.adapters.b.f c, final int d) {
            this.a = new WeakReference<f>(f);
            this.b = new WeakReference<i>(f.p);
            this.c = c;
            this.d = d;
        }
        
        @Override
        public void a() {
            if (this.a.get() != null) {
                final LinearLayout b = this.a.get().r;
                final int b2 = this.c.i().b();
            Label_0073:
                while (true) {
                    if (((com.facebook.ads.internal.view.g.b)b.getChildAt(b2)).a()) {
                        for (int i = 0; i < b.getChildCount(); ++i) {
                            if (!((com.facebook.ads.internal.view.g.b)b.getChildAt(i)).a()) {
                                break Label_0073;
                            }
                        }
                    }
                    Label_0110: {
                        break Label_0110;
                        final int i;
                        com.facebook.ads.internal.view.f.a(this.a.get(), this.c.j().get(i));
                        return;
                    }
                    int i = b2;
                    continue Label_0073;
                }
            }
        }
        
        @Override
        public void a(final int n) {
            final i i = this.b.get();
            if (i != null) {
                i.setProgress((this.d - n) * 100 / this.d);
                i.setText(this.c.e().a(String.valueOf(n)));
            }
        }
    }
}
