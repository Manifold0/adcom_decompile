package com.facebook.ads.internal.view.component.p058a;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p027a.C1850i;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C2504i;
import com.facebook.ads.internal.view.C2504i.C2282b;
import com.facebook.ads.internal.view.C2504i.C2383a;
import com.facebook.ads.internal.view.C2551u;
import com.facebook.ads.internal.view.component.C2295a.C2258a;
import com.facebook.ads.internal.view.p022i.p067c.C2456f;
import com.facebook.ads.internal.view.p056b.C2230b;
import com.facebook.ads.internal.view.p056b.C2239f;
import com.facebook.ads.internal.view.p056b.C2239f.C2224a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.component.a.h */
class C2287h extends C2286l {
    /* renamed from: a */
    private static final int f5388a = ((int) (C2600x.f6420b * 4.0f));
    /* renamed from: b */
    private final C2293o f5389b;
    @Nullable
    /* renamed from: c */
    private final C2291m f5390c;
    /* renamed from: d */
    private final FrameLayout f5391d;
    /* renamed from: e */
    private final C2085c f5392e;
    /* renamed from: f */
    private final String f5393f;
    /* renamed from: g */
    private final C2630a f5394g;
    /* renamed from: h */
    private final C2598w f5395h;
    /* renamed from: i */
    private final String f5396i;
    /* renamed from: j */
    private final C2239f f5397j;
    /* renamed from: k */
    private final C2456f f5398k;
    /* renamed from: l */
    private final int f5399l;
    /* renamed from: m */
    private boolean f5400m = true;
    /* renamed from: n */
    private boolean f5401n;
    /* renamed from: o */
    private WeakReference<C2551u> f5402o;

    /* renamed from: com.facebook.ads.internal.view.component.a.h$1 */
    class C22831 implements C2282b {
        /* renamed from: a */
        final /* synthetic */ C2287h f5382a;

        C22831(C2287h c2287h) {
            this.f5382a = c2287h;
        }

        /* renamed from: a */
        public void mo5562a() {
            if (this.f5382a.f5402o.get() != null) {
                ((C2551u) this.f5382a.f5402o.get()).m6594a();
                if (this.f5382a.f5389b.getVisibility() != 0) {
                    Map hashMap = new HashMap();
                    this.f5382a.f5394g.m6771a(hashMap);
                    hashMap.put("touch", C2581k.m6645a(this.f5382a.f5395h.m6676e()));
                    this.f5382a.f5392e.mo5489r(this.f5382a.f5393f, hashMap);
                }
            }
        }
    }

    public C2287h(C2279e c2279e, C1876h c1876h, int i, int i2, final C1850i c1850i, boolean z) {
        super(c2279e.m5850a());
        this.f5392e = c2279e.m5851b();
        this.f5398k = (C2456f) c2279e.m5858i();
        this.f5394g = c2279e.m5854e();
        this.f5393f = c2279e.m5856g().m4307c();
        this.f5395h = c2279e.m5855f();
        this.f5396i = c2279e.m5856g().m4303a().m4331d();
        this.f5399l = c2279e.m5860k();
        C2600x.m6680a((View) this, 0);
        View linearLayout = new LinearLayout(c2279e.m5850a());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        addView(linearLayout, layoutParams);
        View c2504i = new C2504i(c2279e.m5850a(), c2279e.m5852c(), C2383a.CROSS);
        c2504i.setCloseButtonStyle(C2383a.DOWN_ARROW);
        c2504i.m6455a(c2279e.m5856g().m4303a(), c2279e.m5856g().m4307c(), 0);
        c2504i.m6453a(c1876h, true);
        c2504i.m6461b(false);
        c2504i.setToolbarListener(new C22831(this));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.f5391d = new FrameLayout(c2279e.m5850a());
        linearLayout.addView(this.f5391d, layoutParams2);
        C2600x.m6680a(this.f5391d, -433903825);
        LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -2);
        if (c2279e.m5860k() == 1) {
            this.f5391d.addView(c2504i, layoutParams3);
            this.f5391d.setVisibility(4);
        }
        c2504i = new FrameLayout(c2279e.m5850a());
        layoutParams3 = new FrameLayout.LayoutParams(-1, f5388a);
        final View c2230b = new C2230b(c2279e.m5850a(), null, 16842872);
        c2504i.addView(c2230b, layoutParams2);
        layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        this.f5397j = new C2239f(c2279e.m5850a(), new C2224a(this) {
            /* renamed from: c */
            final /* synthetic */ C2287h f5385c;

            /* renamed from: a */
            public void mo5548a(int i) {
                if (this.f5385c.f5400m) {
                    c2230b.setProgress(i);
                }
            }

            /* renamed from: a */
            public void mo5549a(String str) {
                this.f5385c.f5400m = true;
                c2504i.setVisibility(0);
            }

            /* renamed from: b */
            public void mo5550b(String str) {
            }

            /* renamed from: c */
            public void mo5551c(String str) {
                c2230b.setProgress(100);
                this.f5385c.f5400m = false;
                c2504i.setVisibility(8);
            }
        });
        this.f5397j.addView(c2504i, layoutParams3);
        linearLayout.addView(this.f5397j, layoutParams2);
        layoutParams.gravity = 17;
        this.f5389b = new C2293o(c2279e, c1876h, i, i2, new C2258a(this) {
            /* renamed from: b */
            final /* synthetic */ C2287h f5387b;

            /* renamed from: a */
            public void mo5563a() {
                this.f5387b.m5882a(c1850i);
            }
        });
        this.f5390c = this.f5389b.f5422a.getSwipeUpCtaButton();
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, (c2279e.m5860k() != 1 ? 1 : null) != null ? 0 : C2504i.f6089a, 0, 0);
        addView(this.f5389b, layoutParams);
        if (z) {
            m5882a(c1850i);
        }
    }

    /* renamed from: a */
    private void m5882a(C1850i c1850i) {
        if (this.f5389b.getVisibility() == 0) {
            Map hashMap = new HashMap();
            this.f5394g.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f5395h.m6676e()));
            c1850i.m4166a(hashMap);
            this.f5389b.setVisibility(4);
            this.f5397j.loadUrl(c1850i.m4167c().toString());
            c1850i.mo5376a();
        }
    }

    /* renamed from: a */
    private boolean m5884a(MotionEvent motionEvent, @Nullable View view) {
        boolean z = true;
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (motionEvent.getX() < ((float) iArr[0]) || motionEvent.getX() > ((float) (iArr[0] + view.getWidth()))) {
            boolean z2 = false;
        } else {
            int i = 1;
        }
        if (motionEvent.getY() < ((float) iArr[1]) || motionEvent.getY() > ((float) (iArr[1] + view.getHeight()))) {
            boolean z3 = false;
        } else {
            int i2 = 1;
        }
        if (this.f5389b.getVisibility() == 0) {
            int i3 = 1;
        } else {
            boolean z4 = false;
        }
        if (i == 0 || r3 == 0 || r4 == 0) {
            z = false;
        }
        return z;
    }

    /* renamed from: a */
    public void mo5564a() {
        if (this.f5389b.getVisibility() == 0) {
            if (this.f5390c != null) {
                this.f5390c.performClick();
            }
            Map hashMap = new HashMap();
            this.f5394g.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f5395h.m6676e()));
            this.f5392e.mo5488q(this.f5393f, hashMap);
        }
        this.f5391d.setVisibility(0);
        ((C2551u) getParent()).m6595b();
    }

    /* renamed from: a */
    public void mo5565a(MotionEvent motionEvent) {
        boolean a = m5884a(motionEvent, this.f5398k);
        if (!a) {
            this.f5395h.m6671a(motionEvent, this, this);
        }
        if (motionEvent.getAction() == 0) {
            this.f5401n = m5884a(motionEvent, this.f5390c);
        } else if (motionEvent.getAction() != 1) {
        } else {
            if (a) {
                this.f5398k.performClick();
            } else if (this.f5390c != null && m5884a(motionEvent, this.f5390c) && this.f5401n) {
                this.f5390c.performClick();
            } else {
                Map hashMap = new HashMap();
                this.f5394g.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(this.f5395h.m6676e()));
                this.f5392e.mo5475d(this.f5393f, hashMap);
            }
        }
    }

    /* renamed from: a */
    public void m5894a(C1880l c1880l) {
        this.f5389b.m5908a(c1880l.m4315a().m4266b(), c1880l.m4315a().m4267c(), this.f5396i, false, false);
    }

    /* renamed from: b */
    public void mo5566b() {
        this.f5391d.setVisibility(4);
    }

    /* renamed from: c */
    public boolean mo5567c() {
        if (this.f5399l != 1 || this.f5402o.get() == null) {
            if (this.f5399l != 2 || !this.f5397j.canGoBack()) {
                return false;
            }
            this.f5397j.goBack();
            return true;
        } else if (((C2551u) this.f5402o.get()).m6596c()) {
            return false;
        } else {
            if (this.f5397j.canGoBack()) {
                this.f5397j.goBack();
            } else if (this.f5402o.get() != null) {
                ((C2551u) this.f5402o.get()).m6594a();
            }
            return true;
        }
    }

    /* renamed from: d */
    public boolean mo5568d() {
        return this.f5389b.getVisibility() != 0;
    }

    /* renamed from: e */
    public void mo5569e() {
        this.f5397j.onPause();
    }

    /* renamed from: f */
    public void mo5570f() {
        this.f5397j.onResume();
    }

    /* renamed from: g */
    public void mo5571g() {
        this.f5397j.destroy();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof C2551u) {
            this.f5402o = new WeakReference((C2551u) getParent());
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        mo5565a(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
