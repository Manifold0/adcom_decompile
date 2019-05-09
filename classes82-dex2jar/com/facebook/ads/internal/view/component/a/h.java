// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import android.view.MotionEvent;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.b.b;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import java.util.HashMap;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.View;
import com.facebook.ads.internal.a.i;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.u;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.view.b.f;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import com.facebook.ads.internal.s.c;
import android.widget.FrameLayout;
import android.support.annotation.Nullable;

class h extends l
{
    private static final int a;
    private final o b;
    @Nullable
    private final m c;
    private final FrameLayout d;
    private final c e;
    private final String f;
    private final a g;
    private final w h;
    private final String i;
    private final f j;
    private final com.facebook.ads.internal.view.i.c.f k;
    private final int l;
    private boolean m;
    private boolean n;
    private WeakReference<u> o;
    
    static {
        a = (int)(x.b * 4.0f);
    }
    
    public h(final e e, final com.facebook.ads.internal.adapters.b.h h, int a, final int n, final i i, final boolean b) {
        super(e.a());
        this.m = true;
        this.e = e.b();
        this.k = (com.facebook.ads.internal.view.i.c.f)e.i();
        this.g = e.e();
        this.f = e.g().c();
        this.h = e.f();
        this.i = e.g().a().d();
        this.l = e.k();
        x.a((View)this, 0);
        final LinearLayout linearLayout = new LinearLayout(e.a());
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
        final com.facebook.ads.internal.view.i j = new com.facebook.ads.internal.view.i(e.a(), e.c(), com.facebook.ads.internal.view.i.a.a);
        j.setCloseButtonStyle(com.facebook.ads.internal.view.i.a.c);
        j.a(e.g().a(), e.g().c(), 0);
        j.a(h, true);
        j.b(false);
        j.setToolbarListener((com.facebook.ads.internal.view.i.b)new com.facebook.ads.internal.view.i.b() {
            @Override
            public void a() {
                if (h.this.o.get() != null) {
                    ((u)h.this.o.get()).a();
                    if (h.this.b.getVisibility() != 0) {
                        final HashMap<String, String> hashMap = new HashMap<String, String>();
                        h.this.g.a(hashMap);
                        hashMap.put("touch", k.a(h.this.h.e()));
                        h.this.e.r(h.this.f, hashMap);
                    }
                }
            }
        });
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout.addView((View)(this.d = new FrameLayout(e.a())), (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        x.a((View)this.d, -433903825);
        final FrameLayout$LayoutParams frameLayout$LayoutParams2 = new FrameLayout$LayoutParams(-1, -2);
        if (e.k() == 1) {
            this.d.addView((View)j, (ViewGroup$LayoutParams)frameLayout$LayoutParams2);
            this.d.setVisibility(4);
        }
        final FrameLayout frameLayout = new FrameLayout(e.a());
        final FrameLayout$LayoutParams frameLayout$LayoutParams3 = new FrameLayout$LayoutParams(-1, com.facebook.ads.internal.view.component.a.h.a);
        final b b2 = new b(e.a(), null, 16842872);
        frameLayout.addView((View)b2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, -1);
        (this.j = new f(e.a(), (f.a)new f.a() {
            @Override
            public void a(final int progress) {
                if (h.this.m) {
                    b2.setProgress(progress);
                }
            }
            
            @Override
            public void a(final String s) {
                h.this.m = true;
                frameLayout.setVisibility(0);
            }
            
            @Override
            public void b(final String s) {
            }
            
            @Override
            public void c(final String s) {
                b2.setProgress(100);
                h.this.m = false;
                frameLayout.setVisibility(8);
            }
        })).addView((View)frameLayout, (ViewGroup$LayoutParams)frameLayout$LayoutParams3);
        linearLayout.addView((View)this.j, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        frameLayout$LayoutParams.gravity = 17;
        this.b = new o(e, h, a, n, new com.facebook.ads.internal.view.component.a.a() {
            @Override
            public void a() {
                com.facebook.ads.internal.view.component.a.h.this.a(i);
            }
        });
        this.c = this.b.a.getSwipeUpCtaButton();
        final FrameLayout$LayoutParams frameLayout$LayoutParams4 = new FrameLayout$LayoutParams(-1, -1);
        if (e.k() != 1) {
            a = 1;
        }
        else {
            a = 0;
        }
        if (a != 0) {
            a = 0;
        }
        else {
            a = com.facebook.ads.internal.view.i.a;
        }
        frameLayout$LayoutParams4.setMargins(0, a, 0, 0);
        this.addView((View)this.b, (ViewGroup$LayoutParams)frameLayout$LayoutParams4);
        if (b) {
            this.a(i);
        }
    }
    
    private void a(final i i) {
        if (this.b.getVisibility() == 0) {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.g.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.h.e()));
            i.a(hashMap);
            this.b.setVisibility(4);
            this.j.loadUrl(i.c().toString());
            i.a();
        }
    }
    
    private boolean a(final MotionEvent motionEvent, @Nullable final View view) {
        boolean b = true;
        if (view == null) {
            return false;
        }
        final int[] array = new int[2];
        view.getLocationOnScreen(array);
        boolean b2;
        if (motionEvent.getX() >= array[0] && motionEvent.getX() <= array[0] + view.getWidth()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3;
        if (motionEvent.getY() >= array[1] && motionEvent.getY() <= array[1] + view.getHeight()) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if (this.b.getVisibility() == 0) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (!b2 || !b3 || !b4) {
            b = false;
        }
        return b;
    }
    
    @Override
    public void a() {
        if (this.b.getVisibility() == 0) {
            if (this.c != null) {
                this.c.performClick();
            }
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.g.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.h.e()));
            this.e.q(this.f, hashMap);
        }
        this.d.setVisibility(0);
        ((u)this.getParent()).b();
    }
    
    @Override
    public void a(final MotionEvent motionEvent) {
        final boolean a = this.a(motionEvent, (View)this.k);
        if (!a) {
            this.h.a(motionEvent, (View)this, (View)this);
        }
        if (motionEvent.getAction() == 0) {
            this.n = this.a(motionEvent, (View)this.c);
        }
        else if (motionEvent.getAction() == 1) {
            if (a) {
                this.k.performClick();
                return;
            }
            if (this.c != null && this.a(motionEvent, (View)this.c) && this.n) {
                this.c.performClick();
                return;
            }
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            this.g.a(hashMap);
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(this.h.e()));
            this.e.d(this.f, hashMap);
        }
    }
    
    public void a(final com.facebook.ads.internal.adapters.b.l l) {
        this.b.a(l.a().b(), l.a().c(), this.i, false, false);
    }
    
    @Override
    public void b() {
        this.d.setVisibility(4);
    }
    
    @Override
    public boolean c() {
        if (this.l == 1 && this.o.get() != null) {
            if (this.o.get().c()) {
                return false;
            }
            if (this.j.canGoBack()) {
                this.j.goBack();
            }
            else if (this.o.get() != null) {
                this.o.get().a();
            }
            return true;
        }
        else {
            if (this.l == 2 && this.j.canGoBack()) {
                this.j.goBack();
                return true;
            }
            return false;
        }
    }
    
    @Override
    public boolean d() {
        return this.b.getVisibility() != 0;
    }
    
    @Override
    public void e() {
        this.j.onPause();
    }
    
    @Override
    public void f() {
        this.j.onResume();
    }
    
    @Override
    public void g() {
        this.j.destroy();
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.getParent() instanceof u) {
            this.o = new WeakReference<u>((u)this.getParent());
        }
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        this.a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
