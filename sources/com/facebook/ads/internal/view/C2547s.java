package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2504i.C2282b;
import com.facebook.ads.internal.view.C2504i.C2383a;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C1822f;
import com.facebook.ads.internal.view.p022i.p023b.C2373p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2413o;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d.C2450a;
import com.facebook.ads.internal.view.p022i.p067c.C2456f;
import com.facebook.ads.internal.view.p022i.p067c.C2473j;
import com.facebook.ads.internal.view.p022i.p067c.C2473j.C2472a;
import com.facebook.ads.internal.view.p022i.p067c.C2475k;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import com.facebook.ads.internal.view.p022i.p067c.C2491o;
import com.facebook.ads.internal.view.p063f.C2351b;
import com.facebook.ads.internal.view.p064g.C2353a;
import com.facebook.ads.internal.view.p064g.C2363c;
import com.facebook.ads.internal.view.p064g.C2363c.C2361a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.view.s */
public class C2547s extends RelativeLayout implements C1921a {
    /* renamed from: a */
    static final /* synthetic */ boolean f6225a = (!C2547s.class.desiredAssertionStatus());
    /* renamed from: b */
    private static final int f6226b = ((int) (12.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f6227c = ((int) (18.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f6228d = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f6229e = ((int) (72.0f * C2600x.f6420b));
    /* renamed from: f */
    private static final int f6230f = ((int) (C2600x.f6420b * 56.0f));
    /* renamed from: g */
    private static final int f6231g = ((int) (C2600x.f6420b * 56.0f));
    /* renamed from: h */
    private static final int f6232h = ((int) (28.0f * C2600x.f6420b));
    /* renamed from: i */
    private static final int f6233i = ((int) (20.0f * C2600x.f6420b));
    /* renamed from: j */
    private static final LayoutParams f6234j = new LayoutParams(-1, -1);
    @Nullable
    /* renamed from: A */
    private Context f6235A;
    @Nullable
    /* renamed from: B */
    private C2394a f6236B;
    @Nullable
    /* renamed from: C */
    private C1789a f6237C;
    @Nullable
    /* renamed from: D */
    private C2353a f6238D;
    @Nullable
    /* renamed from: E */
    private C2451d f6239E;
    @Nullable
    /* renamed from: F */
    private C2481l f6240F;
    @Nullable
    /* renamed from: G */
    private View f6241G;
    @Nullable
    /* renamed from: H */
    private C2473j f6242H;
    @Nullable
    /* renamed from: I */
    private C2504i f6243I;
    @Nullable
    /* renamed from: J */
    private C2389a f6244J;
    @Nullable
    /* renamed from: K */
    private Integer f6245K;
    /* renamed from: L */
    private C2363c f6246L;
    /* renamed from: M */
    private boolean f6247M = false;
    /* renamed from: N */
    private boolean f6248N = false;
    /* renamed from: O */
    private WeakReference<AudienceNetworkActivity> f6249O;
    /* renamed from: k */
    private final BackButtonInterceptor f6250k = new C25381(this);
    /* renamed from: l */
    private final C1818d f6251l = new C25403(this);
    /* renamed from: m */
    private final C1822f f6252m = new C25414(this);
    /* renamed from: n */
    private final C1810n f6253n = new C25425(this);
    /* renamed from: o */
    private final C2373p f6254o = new C25436(this);
    /* renamed from: p */
    private final C1887q f6255p;
    /* renamed from: q */
    private final C2085c f6256q;
    /* renamed from: r */
    private final C2630a f6257r;
    /* renamed from: s */
    private final C1858a f6258s;
    /* renamed from: t */
    private final C2598w f6259t = new C2598w();
    /* renamed from: u */
    private final C2491o f6260u;
    /* renamed from: v */
    private final C2423b f6261v;
    /* renamed from: w */
    private final RelativeLayout f6262w;
    /* renamed from: x */
    private final C2456f f6263x;
    /* renamed from: y */
    private final C1876h f6264y;
    /* renamed from: z */
    private final AtomicBoolean f6265z = new AtomicBoolean(false);

    /* renamed from: com.facebook.ads.internal.view.s$1 */
    class C25381 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2547s f6216a;

        C25381(C2547s c2547s) {
            this.f6216a = c2547s;
        }

        public boolean interceptBackButton() {
            return !this.f6216a.f6247M;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$3 */
    class C25403 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2547s f6218a;

        C25403(C2547s c2547s) {
            this.f6218a = c2547s;
        }

        /* renamed from: a */
        public void m6553a(C2406c c2406c) {
            if (this.f6218a.f6237C != null) {
                this.f6218a.f6246L.m6109d();
                C2547s.m6567d(this.f6218a);
                this.f6218a.f6237C.mo5336a(aa.REWARDED_VIDEO_COMPLETE.m6205a(), (C2061d) c2406c);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$4 */
    class C25414 extends C1822f {
        /* renamed from: a */
        final /* synthetic */ C2547s f6219a;

        C25414(C2547s c2547s) {
            this.f6219a = c2547s;
        }

        /* renamed from: a */
        public void m6555a(C2407e c2407e) {
            if (this.f6219a.f6237C != null) {
                this.f6219a.f6237C.mo5335a(aa.REWARDED_VIDEO_ERROR.m6205a());
            }
            this.f6219a.m6578a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$5 */
    class C25425 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2547s f6220a;

        C25425(C2547s c2547s) {
            this.f6220a = c2547s;
        }

        /* renamed from: a */
        public void m6557a(C2412m c2412m) {
            if (this.f6220a.f6236B != null) {
                this.f6220a.f6236B.m6157a(C2389a.USER_STARTED);
                this.f6220a.f6257r.m6769a();
                this.f6220a.f6265z.set(this.f6220a.f6236B.m6169k());
                this.f6220a.m6565b();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$6 */
    class C25436 extends C2373p {
        /* renamed from: a */
        final /* synthetic */ C2547s f6221a;

        C25436(C2547s c2547s) {
            this.f6221a = c2547s;
        }

        /* renamed from: a */
        public void m6559a(C2413o c2413o) {
            if (this.f6221a.f6236B != null && this.f6221a.f6239E != null && this.f6221a.f6236B.getDuration() - this.f6221a.f6236B.getCurrentPositionInMillis() <= 3000 && this.f6221a.f6239E.m6299a()) {
                this.f6221a.f6239E.m6300b();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$7 */
    class C25447 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2547s f6222a;

        C25447(C2547s c2547s) {
            this.f6222a = c2547s;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (!this.f6222a.f6259t.m6673b()) {
                this.f6222a.f6259t.m6670a();
                Map hashMap = new HashMap();
                if (!TextUtils.isEmpty(this.f6222a.f6255p.mo5384a())) {
                    this.f6222a.f6257r.m6771a(hashMap);
                    hashMap.put("touch", C2581k.m6645a(this.f6222a.f6259t.m6676e()));
                    if (this.f6222a.f6255p.m4211d() != null) {
                        hashMap.put("extra_hints", this.f6222a.f6255p.m4211d());
                    }
                    hashMap.put("is_cyoa", String.valueOf(this.f6222a.f6255p.m4368l()));
                    this.f6222a.f6256q.mo5470a(this.f6222a.f6255p.mo5384a(), hashMap);
                }
                if (this.f6222a.f6237C != null) {
                    this.f6222a.f6237C.mo5335a(aa.REWARDED_VIDEO_IMPRESSION.m6205a());
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$8 */
    class C25458 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2547s f6223a;

        C25458(C2547s c2547s) {
            this.f6223a = c2547s;
        }

        public void onClick(View view) {
            if (this.f6223a.f6242H != null && this.f6223a.f6242H.m6364a() && this.f6223a.f6242H.getSkipSeconds() != 0 && this.f6223a.f6236B != null) {
                this.f6223a.f6236B.m6164f();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.s$9 */
    class C25469 implements C2282b {
        /* renamed from: a */
        final /* synthetic */ C2547s f6224a;

        C25469(C2547s c2547s) {
            this.f6224a = c2547s;
        }

        /* renamed from: a */
        public void mo5562a() {
            if (this.f6224a.f6259t.m6672a(this.f6224a.getContext())) {
                Map hashMap = new HashMap();
                this.f6224a.f6257r.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(this.f6224a.f6259t.m6676e()));
                this.f6224a.f6256q.mo5480i(this.f6224a.f6255p.mo5384a(), hashMap);
            } else if (!this.f6224a.f6247M && this.f6224a.f6236B != null) {
                this.f6224a.f6247M = true;
                this.f6224a.f6236B.m6164f();
            } else if (this.f6224a.f6247M && this.f6224a.f6237C != null) {
                this.f6224a.f6237C.mo5335a(aa.REWARDED_VIDEO_END_ACTIVITY.m6205a());
            }
        }
    }

    public C2547s(Context context, C2085c c2085c, C2394a c2394a, C1789a c1789a, C1887q c1887q) {
        super(context);
        this.f6235A = context;
        this.f6237C = c1789a;
        this.f6236B = c2394a;
        this.f6256q = c2085c;
        this.f6255p = c1887q;
        this.f6264y = this.f6255p.m4365i().m4212a();
        this.f6262w = new RelativeLayout(context);
        this.f6260u = new C2491o(this.f6235A);
        this.f6263x = new C2456f(this.f6235A);
        new C2252d(this.f6262w, f6233i).m5771a().m5774a(C2078a.m5103m(this.f6235A)).m5775a(this.f6255p.m4366j().m4245g());
        this.f6258s = new C25447(this);
        this.f6257r = new C2630a(this, 1, this.f6258s);
        this.f6257r.m6770a(250);
        this.f6261v = new C2423b(this.f6235A, this.f6256q, this.f6236B, this.f6255p.mo5384a());
        this.f6246L = new C2363c(this.f6235A, this.f6256q, this.f6255p, this.f6237C, this.f6257r, this.f6259t);
        if (f6225a || this.f6236B != null) {
            this.f6236B.setVideoProgressReportIntervalMs(c1887q.m4208b());
            C2600x.m6680a(this.f6236B, (int) ViewCompat.MEASURED_STATE_MASK);
            this.f6236B.getEventBus().m5029a(this.f6251l, this.f6252m, this.f6253n, this.f6254o);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: b */
    private void m6565b() {
        this.f6263x.setVisibility(this.f6265z.get() ? 0 : 8);
    }

    /* renamed from: d */
    static /* synthetic */ void m6567d(C2547s c2547s) {
        c2547s.f6247M = true;
        if (c2547s.f6235A != null) {
            View frameLayout = new FrameLayout(c2547s.f6235A);
            frameLayout.setLayoutParams(f6234j);
            C2600x.m6680a(frameLayout, -1509949440);
            c2547s.f6262w.addView(frameLayout, 0);
        }
        C2600x.m6683a(c2547s.f6262w);
        if (c2547s.f6236B != null) {
            c2547s.f6236B.m6162d();
            c2547s.f6236B.setVisibility(4);
        }
        if (c2547s.f6243I != null) {
            if (c2547s.f6243I.m6458a()) {
                c2547s.f6243I.m6459b();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable(c2547s) {
                    /* renamed from: a */
                    final /* synthetic */ C2547s f6215a;

                    {
                        this.f6215a = r1;
                    }

                    public void run() {
                        if (this.f6215a.f6243I != null) {
                            this.f6215a.f6243I.setCloseButtonStyle(C2383a.CROSS);
                            this.f6215a.f6243I.m6457a(true);
                        }
                    }
                }, 1000);
            } else {
                c2547s.f6243I.m6457a(true);
                c2547s.f6243I.setCloseButtonStyle(C2383a.CROSS);
            }
            c2547s.f6243I.m6462c();
        }
        C2600x.m6688a(c2547s.f6236B, c2547s.f6242H, c2547s.f6263x, c2547s.f6260u);
        Pair c = c2547s.f6246L.m6108c();
        ViewGroup.LayoutParams layoutParams;
        switch ((C2361a) c.first) {
            case MARKUP:
                C2600x.m6688a(c2547s.f6238D);
                c2547s.f6262w.addView((View) c.second, f6234j);
                return;
            case SCREENSHOTS:
                if (c2547s.f6238D != null) {
                    c2547s.f6238D.setVisibility(0);
                    c2547s.f6238D.m6068a();
                }
                layoutParams = new LayoutParams(-1, -1);
                layoutParams.setMargins(0, f6231g, 0, 0);
                layoutParams.addRule(2, c2547s.f6238D.getId());
                c2547s.f6262w.addView((View) c.second, layoutParams);
                c2547s.f6259t.m6670a();
                return;
            case INFO:
                C2600x.m6688a(c2547s.f6238D);
                layoutParams = new LayoutParams(-1, -2);
                layoutParams.addRule(15);
                layoutParams.setMargins(f6228d, f6228d, f6228d, f6228d);
                c2547s.f6262w.addView((View) c.second, layoutParams);
                c2547s.f6259t.m6670a();
                return;
            case PLAYABLE:
                AudienceNetworkActivity audienceNetworkActivity = (AudienceNetworkActivity) c2547s.f6249O.get();
                if (audienceNetworkActivity != null) {
                    c2547s.f6245K = Integer.valueOf(audienceNetworkActivity.getRequestedOrientation());
                    audienceNetworkActivity.setRequestedOrientation(1);
                }
                c2547s.f6262w.removeAllViews();
                C2600x.m6689b(c2547s.f6243I);
                c2547s.f6262w.addView((View) c.second, f6234j);
                ((C2351b) c.second).m6052c();
                return;
            default:
                return;
        }
    }

    private void setUpContentLayoutForVideo(int i) {
        ViewGroup.LayoutParams layoutParams;
        this.f6262w.removeAllViews();
        this.f6262w.addView(this.f6236B, f6234j);
        if (this.f6238D != null) {
            C2600x.m6679a(this.f6238D);
            this.f6238D.m6069a(i);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.addRule(12);
            this.f6238D.setPadding(f6228d, f6228d, f6228d, f6228d);
            this.f6262w.addView(this.f6238D, layoutParams);
        }
        if (this.f6242H != null) {
            layoutParams = new LayoutParams(f6230f, f6230f);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.f6242H.setPadding(f6228d, f6228d, f6228d, f6228d);
            this.f6262w.addView(this.f6242H, layoutParams);
        }
        layoutParams = new LayoutParams(f6232h, f6232h);
        layoutParams.addRule(10);
        layoutParams.addRule(11);
        layoutParams.setMargins(f6226b, f6226b + f6231g, f6226b, f6227c);
        this.f6262w.addView(this.f6263x, layoutParams);
        m6565b();
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.f6262w.addView(this.f6260u, layoutParams);
    }

    /* renamed from: a */
    public void m6578a() {
        if (this.f6236B != null) {
            this.f6236B.m6165g();
            this.f6236B.m6170l();
        }
        if (this.f6257r != null) {
            this.f6257r.m6774c();
        }
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        boolean z = false;
        if (this.f6236B != null && this.f6237C != null) {
            this.f6249O = new WeakReference(audienceNetworkActivity);
            if (this.f6236B != null) {
                this.f6236B.m6162d();
                this.f6236B.m6158a(new C2475k(this.f6235A));
                this.f6236B.m6158a(this.f6263x);
                this.f6236B.m6158a(this.f6260u);
                this.f6240F = new C2481l(this.f6235A, true);
                this.f6241G = new View(this.f6235A);
                this.f6241G.setLayoutParams(f6234j);
                C2600x.m6680a(this.f6241G, -1509949440);
                C2390b c2451d = new C2451d(this.f6241G, C2450a.FADE_OUT_ON_PLAY, true);
                this.f6236B.addView(this.f6241G);
                this.f6236B.m6158a(c2451d);
                c2451d = new C2451d(this.f6240F, C2450a.FADE_OUT_ON_PLAY, true);
                this.f6236B.m6158a(this.f6240F);
                this.f6236B.m6158a(c2451d);
                Context context = this.f6235A;
                int i = f6229e;
                C1876h c1876h = this.f6264y;
                C2085c c2085c = this.f6256q;
                C1789a c1789a = this.f6237C;
                boolean z2 = this.f6246L.m6107b() == C2361a.INFO;
                if (this.f6246L.m6107b() == C2361a.INFO) {
                    z = true;
                }
                this.f6238D = new C2353a(context, i, c1876h, c2085c, c1789a, z2, z, this.f6257r, this.f6259t);
                this.f6238D.setInfo(this.f6255p);
                this.f6239E = new C2451d(this.f6238D, C2450a.FADE_OUT_ON_PLAY, true);
                this.f6236B.m6158a(this.f6239E);
                if (this.f6246L.m6106a() && this.f6255p.m4366j().m4241c() > 0) {
                    this.f6242H = new C2473j(this.f6235A, this.f6255p.m4366j().m4241c(), -12286980);
                    this.f6242H.setButtonMode(C2472a.SKIP_BUTTON_MODE);
                    this.f6242H.setOnClickListener(new C25458(this));
                    this.f6236B.m6158a(this.f6242H);
                } else if (!this.f6246L.m6106a()) {
                    this.f6243I = new C2504i(this.f6235A, this.f6237C, C2078a.m5072K(this.f6235A) ? C2383a.ARROWS : C2383a.CROSS);
                    this.f6243I.m6455a(this.f6255p.m4362f(), this.f6255p.mo5384a(), this.f6255p.m4366j().m4241c());
                    if (this.f6255p.m4366j().m4241c() <= 0) {
                        this.f6243I.m6459b();
                    }
                    if (this.f6246L.m6107b() != C2361a.INFO) {
                        this.f6243I.m6462c();
                    }
                    this.f6243I.setToolbarListener(new C25469(this));
                    this.f6236B.m6158a(this.f6243I);
                }
            }
            audienceNetworkActivity.addBackButtonInterceptor(this.f6250k);
            this.f6236B.setVideoURI(!TextUtils.isEmpty(this.f6255p.m4366j().m4240b()) ? this.f6255p.m4366j().m4240b() : this.f6255p.m4366j().m4238a());
            setUpContentLayoutForVideo(audienceNetworkActivity.getResources().getConfiguration().orientation);
            addView(this.f6262w, f6234j);
            if (this.f6243I != null) {
                C2600x.m6679a(this.f6243I);
                this.f6243I.m6453a(this.f6264y, true);
                if (C1993a.m4787a(getContext(), true)) {
                    this.f6243I.m6454a(this.f6255p.m4362f(), this.f6255p.mo5384a());
                }
                addView(this.f6243I, new LayoutParams(-1, f6231g));
            }
            setLayoutParams(f6234j);
            this.f6237C.mo5333a((View) this);
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    public void a_(boolean z) {
        if (this.f6236B != null && !this.f6236B.m6171m()) {
            this.f6244J = this.f6236B.getVideoStartReason();
            this.f6248N = z;
            this.f6236B.m6160a(false);
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        if (this.f6236B != null && !this.f6236B.m6172n() && this.f6236B.getState() != C2501d.PLAYBACK_COMPLETED && this.f6244J != null) {
            if (!this.f6248N || z) {
                this.f6236B.m6157a(this.f6244J);
            }
        }
    }

    public int getCurrentPosition() {
        return this.f6236B != null ? this.f6236B.getCurrentPositionInMillis() : 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.f6238D != null) {
            this.f6238D.m6069a(configuration.orientation);
        }
    }

    public void onDestroy() {
        m6578a();
        if (this.f6236B != null) {
            this.f6236B.getEventBus().m5031b(this.f6251l, this.f6252m, this.f6253n, this.f6254o);
        }
        if (!TextUtils.isEmpty(this.f6255p.mo5384a())) {
            Map hashMap = new HashMap();
            this.f6257r.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f6259t.m6676e()));
            this.f6256q.mo5483l(this.f6255p.mo5384a(), hashMap);
        }
        if (this.f6243I != null) {
            this.f6243I.setToolbarListener(null);
        }
        if (!(this.f6245K == null || this.f6249O.get() == null)) {
            ((AudienceNetworkActivity) this.f6249O.get()).setRequestedOrientation(this.f6245K.intValue());
        }
        this.f6261v.m6244a();
        this.f6236B = null;
        this.f6246L.m6110e();
        this.f6242H = null;
        this.f6238D = null;
        this.f6239E = null;
        this.f6237C = null;
        this.f6235A = null;
        this.f6260u.m6405a();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f6259t.m6671a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }

    @VisibleForTesting
    void setEndCardController(C2363c c2363c) {
        this.f6246L = c2363c;
    }

    public void setListener(C1789a c1789a) {
    }
}
