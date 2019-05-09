package com.facebook.ads.internal.p017t;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.C2532p;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p022i.C2502d;
import com.facebook.ads.internal.view.p022i.C2502d.C2098a;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import com.facebook.ads.internal.view.p022i.p067c.C2464h;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.t.c */
public class C2103c {
    /* renamed from: a */
    private static final String f4807a = C2103c.class.getSimpleName();
    /* renamed from: b */
    private final C2459g f4808b;
    /* renamed from: c */
    private final C2630a f4809c;
    /* renamed from: d */
    private final C1858a f4810d;
    /* renamed from: e */
    private final View f4811e;
    /* renamed from: f */
    private final C2098a f4812f = new C20991(this);
    @Nullable
    /* renamed from: g */
    private C2532p f4813g;
    @Nullable
    /* renamed from: h */
    private C1795a f4814h;
    /* renamed from: i */
    private Context f4815i;
    /* renamed from: j */
    private boolean f4816j;
    /* renamed from: k */
    private boolean f4817k;
    /* renamed from: l */
    private boolean f4818l;
    /* renamed from: m */
    private final AtomicBoolean f4819m = new AtomicBoolean(false);
    /* renamed from: n */
    private final AtomicBoolean f4820n = new AtomicBoolean(false);
    /* renamed from: o */
    private C2119l f4821o = C2119l.f4896a;

    /* renamed from: com.facebook.ads.internal.t.c$a */
    public interface C1795a {
        /* renamed from: a */
        void mo5338a(boolean z);
    }

    /* renamed from: com.facebook.ads.internal.t.c$1 */
    class C20991 implements C2098a {
        /* renamed from: a */
        final /* synthetic */ C2103c f4803a;

        C20991(C2103c c2103c) {
            this.f4803a = c2103c;
        }

        /* renamed from: a */
        public void mo5499a() {
            this.f4803a.f4820n.set(true);
            if (this.f4803a.f4814h != null) {
                this.f4803a.f4814h.mo5338a(this.f4803a.f4819m.get());
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.t.c$2 */
    class C21002 implements C1802e {
        /* renamed from: a */
        final /* synthetic */ C2103c f4804a;

        C21002(C2103c c2103c) {
            this.f4804a = c2103c;
        }

        /* renamed from: a */
        public void mo5349a(boolean z) {
            this.f4804a.f4819m.set(z);
            if (this.f4804a.f4820n.get() && this.f4804a.f4814h != null) {
                this.f4804a.f4814h.mo5338a(z);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.t.c$3 */
    class C21013 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2103c f4805a;

        C21013(C2103c c2103c) {
            this.f4805a = c2103c;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f4805a.f4813g != null && motionEvent.getAction() == 1) {
                this.f4805a.f4813g.mo5638a();
            }
            return true;
        }
    }

    /* renamed from: com.facebook.ads.internal.t.c$4 */
    class C21024 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2103c f4806a;

        C21024(C2103c c2103c) {
            this.f4806a = c2103c;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (this.f4806a.f4813g != null) {
                if (!this.f4806a.f4818l && (this.f4806a.f4817k || C2103c.m5247g(this.f4806a))) {
                    C2103c.m5238a(this.f4806a, C2389a.AUTO_STARTED);
                }
                this.f4806a.f4817k = false;
                this.f4806a.f4818l = false;
            }
        }

        /* renamed from: b */
        public void mo5500b() {
            if (this.f4806a.f4813g != null) {
                this.f4806a.f4813g.m6163e();
            }
        }
    }

    public C2103c(Context context, View view) {
        this.f4815i = context;
        this.f4811e = view;
        this.f4808b = new C2459g(context);
        this.f4810d = new C21024(this);
        this.f4809c = new C2630a(this.f4811e, 50, true, this.f4810d);
        m5246g();
    }

    /* renamed from: a */
    static /* synthetic */ void m5238a(C2103c c2103c, C2389a c2389a) {
        if (c2103c.f4813g != null) {
            c2103c.f4813g.m6157a(c2389a);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(f4807a, "MediaViewVideo is null; unable to find it.");
        }
    }

    /* renamed from: g */
    private void m5246g() {
        float f = C2600x.f6420b;
        int i = (int) (2.0f * f);
        int i2 = (int) (f * 25.0f);
        C2390b c2464h = new C2464h(this.f4815i);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        c2464h.setPadding(i, i2, i2, i);
        c2464h.setLayoutParams(layoutParams);
        for (i = 0; i < ((ViewGroup) this.f4811e).getChildCount(); i++) {
            View childAt = ((ViewGroup) this.f4811e).getChildAt(0);
            if (childAt instanceof C2532p) {
                this.f4813g = (C2532p) childAt;
                break;
            }
        }
        if (this.f4813g != null) {
            this.f4813g.m6158a(this.f4808b);
            this.f4813g.m6158a(c2464h);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.e(f4807a, "Unable to find MediaViewVideo child.");
        }
        this.f4809c.m6770a(0);
        this.f4809c.m6772b(250);
    }

    /* renamed from: g */
    static /* synthetic */ boolean m5247g(C2103c c2103c) {
        return (c2103c.f4813g == null || c2103c.f4813g.getState() == C2501d.PLAYBACK_COMPLETED || c2103c.f4821o != C2119l.ON) ? false : true;
    }

    /* renamed from: h */
    private void m5248h() {
        if (this.f4811e.getVisibility() == 0 && this.f4816j && this.f4811e.hasWindowFocus()) {
            this.f4809c.m6769a();
            return;
        }
        if (this.f4813g != null && this.f4813g.getState() == C2501d.PAUSED) {
            this.f4818l = true;
        }
        this.f4809c.m6774c();
    }

    /* renamed from: a */
    public void m5249a() {
        this.f4821o = C2119l.f4896a;
        if (this.f4813g != null) {
            ((C2502d) this.f4813g.getVideoView()).setViewImplInflationListener(null);
        }
    }

    /* renamed from: a */
    public void m5250a(C2114e c2114e) {
        m5251a(c2114e, null);
    }

    /* renamed from: a */
    public void m5251a(C2114e c2114e, @Nullable C1795a c1795a) {
        this.f4817k = false;
        this.f4818l = false;
        this.f4814h = c1795a;
        if (this.f4813g != null) {
            ((C2502d) this.f4813g.getVideoView()).setViewImplInflationListener(this.f4812f);
        }
        C2459g c2459g = this.f4808b;
        String a = (c2114e == null || c2114e.m5334j() == null) ? null : c2114e.m5334j().m5352a();
        c2459g.m6325a(a, new C21002(this));
        this.f4821o = c2114e.m5344t();
        this.f4809c.m6769a();
    }

    /* renamed from: b */
    public void m5252b() {
        if (this.f4813g != null) {
            this.f4813g.getVideoView().setOnTouchListener(new C21013(this));
        }
    }

    /* renamed from: c */
    public void m5253c() {
        this.f4816j = true;
        m5248h();
    }

    /* renamed from: d */
    public void m5254d() {
        this.f4816j = false;
        m5248h();
    }

    /* renamed from: e */
    public void m5255e() {
        m5248h();
    }

    /* renamed from: f */
    public void m5256f() {
        m5248h();
    }
}
