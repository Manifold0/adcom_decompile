package com.facebook.ads.internal.view.p022i.p067c;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2417u;
import com.facebook.ads.internal.view.p022i.p023b.C2418v;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.tonyodev.fetch.FetchConst;

@TargetApi(12)
/* renamed from: com.facebook.ads.internal.view.i.c.d */
public class C2451d implements C2390b {
    /* renamed from: a */
    private final C1814j f5913a;
    /* renamed from: b */
    private final C1812l f5914b;
    /* renamed from: c */
    private final C1818d f5915c;
    /* renamed from: d */
    private final C2418v f5916d;
    /* renamed from: e */
    private final Handler f5917e;
    /* renamed from: f */
    private final boolean f5918f;
    /* renamed from: g */
    private final boolean f5919g;
    /* renamed from: h */
    private View f5920h;
    @Nullable
    /* renamed from: i */
    private C2450a f5921i;
    @Nullable
    /* renamed from: j */
    private C2394a f5922j;
    /* renamed from: k */
    private boolean f5923k;

    /* renamed from: com.facebook.ads.internal.view.i.c.d$1 */
    class C24431 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2451d f5902a;

        C24431(C2451d c2451d) {
            this.f5902a = c2451d;
        }

        /* renamed from: a */
        public void m6277a(C2410i c2410i) {
            this.f5902a.m6285a(1, 0);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.d$2 */
    class C24442 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2451d f5903a;

        C24442(C2451d c2451d) {
            this.f5903a = c2451d;
        }

        /* renamed from: a */
        public void m6279a(C2411k c2411k) {
            if (!this.f5903a.f5923k) {
                return;
            }
            if (this.f5903a.f5921i == C2450a.FADE_OUT_ON_PLAY || this.f5903a.f5918f) {
                this.f5903a.f5921i = null;
                this.f5903a.f5920h.animate().alpha(0.0f).setDuration(500).setListener(new C24495(this.f5903a));
                return;
            }
            this.f5903a.m6285a(0, 8);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.d$3 */
    class C24453 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2451d f5904a;

        C24453(C2451d c2451d) {
            this.f5904a = c2451d;
        }

        /* renamed from: a */
        public void m6281a(C2406c c2406c) {
            if (this.f5904a.f5921i != C2450a.INVSIBLE) {
                this.f5904a.f5920h.setAlpha(1.0f);
                this.f5904a.f5920h.setVisibility(0);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.d$4 */
    class C24484 extends C2418v {
        /* renamed from: a */
        final /* synthetic */ C2451d f5907a;

        /* renamed from: com.facebook.ads.internal.view.i.c.d$4$1 */
        class C24471 extends AnimatorListenerAdapter {
            /* renamed from: a */
            final /* synthetic */ C24484 f5906a;

            /* renamed from: com.facebook.ads.internal.view.i.c.d$4$1$1 */
            class C24461 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C24471 f5905a;

                C24461(C24471 c24471) {
                    this.f5905a = c24471;
                }

                public void run() {
                    if (!this.f5905a.f5906a.f5907a.f5919g && this.f5905a.f5906a.f5907a.f5923k) {
                        this.f5905a.f5906a.f5907a.f5920h.animate().alpha(0.0f).setDuration(500).setListener(new C24495(this.f5905a.f5906a.f5907a));
                    }
                }
            }

            C24471(C24484 c24484) {
                this.f5906a = c24484;
            }

            public void onAnimationEnd(Animator animator) {
                this.f5906a.f5907a.f5917e.postDelayed(new C24461(this), FetchConst.DEFAULT_ON_UPDATE_INTERVAL);
            }
        }

        C24484(C2451d c2451d) {
            this.f5907a = c2451d;
        }

        /* renamed from: a */
        public void m6283a(C2417u c2417u) {
            if (this.f5907a.f5922j != null && c2417u.m6210a().getAction() == 0) {
                this.f5907a.f5917e.removeCallbacksAndMessages(null);
                this.f5907a.m6286a(new C24471(this));
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.d$5 */
    class C24495 extends AnimatorListenerAdapter {
        /* renamed from: a */
        final /* synthetic */ C2451d f5908a;

        C24495(C2451d c2451d) {
            this.f5908a = c2451d;
        }

        public void onAnimationEnd(Animator animator) {
            this.f5908a.f5920h.setVisibility(8);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.c.d$a */
    public enum C2450a {
        VISIBLE,
        INVSIBLE,
        FADE_OUT_ON_PLAY
    }

    public C2451d(View view, C2450a c2450a) {
        this(view, c2450a, false);
    }

    public C2451d(View view, C2450a c2450a, boolean z) {
        this(view, c2450a, z, false);
    }

    public C2451d(View view, @Nullable C2450a c2450a, boolean z, boolean z2) {
        this.f5913a = new C24431(this);
        this.f5914b = new C24442(this);
        this.f5915c = new C24453(this);
        this.f5916d = new C24484(this);
        this.f5923k = true;
        this.f5917e = new Handler();
        this.f5918f = z;
        this.f5919g = z2;
        m6297a(view, c2450a);
    }

    /* renamed from: a */
    private void m6285a(int i, int i2) {
        this.f5917e.removeCallbacksAndMessages(null);
        this.f5920h.clearAnimation();
        this.f5920h.setAlpha((float) i);
        this.f5920h.setVisibility(i2);
    }

    /* renamed from: a */
    private void m6286a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.f5920h.setVisibility(0);
        this.f5920h.animate().alpha(1.0f).setDuration(500).setListener(animatorListenerAdapter);
    }

    /* renamed from: a */
    public void m6297a(View view, C2450a c2450a) {
        this.f5921i = c2450a;
        this.f5920h = view;
        this.f5920h.clearAnimation();
        if (c2450a == C2450a.INVSIBLE) {
            this.f5920h.setAlpha(0.0f);
            this.f5920h.setVisibility(8);
            return;
        }
        this.f5920h.setAlpha(1.0f);
        this.f5920h.setVisibility(0);
    }

    /* renamed from: a */
    public void mo5597a(C2394a c2394a) {
        this.f5922j = c2394a;
        c2394a.getEventBus().m5029a(this.f5913a, this.f5914b, this.f5916d, this.f5915c);
    }

    /* renamed from: a */
    public boolean m6299a() {
        return this.f5923k;
    }

    /* renamed from: b */
    public void m6300b() {
        this.f5923k = false;
        m6286a(null);
    }

    /* renamed from: b */
    public void mo5598b(C2394a c2394a) {
        m6285a(1, 0);
        c2394a.getEventBus().m5031b(this.f5915c, this.f5916d, this.f5914b, this.f5913a);
        this.f5922j = null;
    }
}
