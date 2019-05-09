package com.facebook.ads.internal.p017t;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.adapters.C1837q;
import com.facebook.ads.internal.adapters.C1895c;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.adapters.C1938p;
import com.facebook.ads.internal.adapters.C1938p.C1936a;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2577g;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p025w.p068a.C2564b;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p033b.C1943a;
import com.facebook.ads.internal.p033b.C1960g;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2087d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.aa;
import com.facebook.ads.internal.view.ab;
import com.facebook.ads.internal.view.p019c.C2251c;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p055a.C2195c;
import com.facebook.ads.internal.view.p055a.C2196d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/* renamed from: com.facebook.ads.internal.t.e */
public class C2114e {
    /* renamed from: b */
    private static final String f4837b = C2114e.class.getSimpleName();
    /* renamed from: c */
    private static WeakHashMap<View, WeakReference<C2114e>> f4838c = new WeakHashMap();
    /* renamed from: h */
    private static C2011b f4839h;
    /* renamed from: A */
    private C2118k f4840A;
    /* renamed from: B */
    private boolean f4841B;
    /* renamed from: C */
    private boolean f4842C;
    /* renamed from: D */
    private boolean f4843D;
    /* renamed from: E */
    private boolean f4844E;
    @Nullable
    /* renamed from: F */
    private C2251c f4845F;
    /* renamed from: G */
    private C2104d f4846G;
    /* renamed from: H */
    private C1936a f4847H;
    /* renamed from: I */
    private String f4848I;
    /* renamed from: J */
    private View f4849J;
    @Nullable
    /* renamed from: a */
    protected C1924i f4850a;
    /* renamed from: d */
    private final Context f4851d;
    /* renamed from: e */
    private final String f4852e;
    /* renamed from: f */
    private final String f4853f;
    /* renamed from: g */
    private final C2011b f4854g;
    @Nullable
    /* renamed from: i */
    private C1828h f4855i;
    /* renamed from: j */
    private final C1825c f4856j;
    /* renamed from: k */
    private C1960g f4857k;
    /* renamed from: l */
    private volatile boolean f4858l;
    @Nullable
    /* renamed from: m */
    private C2047d f4859m;
    /* renamed from: n */
    private C2070e f4860n;
    @Nullable
    /* renamed from: o */
    private View f4861o;
    @Nullable
    /* renamed from: p */
    private NativeAdLayout f4862p;
    @Nullable
    /* renamed from: q */
    private C1783f f4863q;
    /* renamed from: r */
    private final List<View> f4864r;
    /* renamed from: s */
    private OnTouchListener f4865s;
    /* renamed from: t */
    private C2630a f4866t;
    /* renamed from: u */
    private C1858a f4867u;
    /* renamed from: v */
    private WeakReference<C1858a> f4868v;
    /* renamed from: w */
    private final C2598w f4869w;
    @Nullable
    /* renamed from: x */
    private C1938p f4870x;
    /* renamed from: y */
    private C2112a f4871y;
    /* renamed from: z */
    private ab f4872z;

    /* renamed from: com.facebook.ads.internal.t.e$c */
    public interface C1825c {
        /* renamed from: a */
        boolean mo5362a(View view);
    }

    /* renamed from: com.facebook.ads.internal.t.e$1 */
    class C21061 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ C2114e f4827a;

        /* renamed from: com.facebook.ads.internal.t.e$1$1 */
        class C21051 implements C1837q {
            /* renamed from: a */
            final /* synthetic */ C21061 f4826a;

            C21051(C21061 c21061) {
                this.f4826a = c21061;
            }

            /* renamed from: a */
            public void mo5372a(C1924i c1924i) {
            }

            /* renamed from: a */
            public void mo5373a(C1924i c1924i, C2065a c2065a) {
            }

            /* renamed from: b */
            public void mo5374b(C1924i c1924i) {
            }

            /* renamed from: c */
            public void mo5375c(C1924i c1924i) {
                if (this.f4826a.f4827a.f4855i != null) {
                    this.f4826a.f4827a.f4855i.mo5366c();
                }
            }
        }

        C21061(C2114e c2114e) {
            this.f4827a = c2114e;
        }

        /* renamed from: a */
        public void mo5323a() {
            if (this.f4827a.f4855i != null) {
                this.f4827a.f4855i.mo5366c();
            }
        }

        /* renamed from: a */
        public void mo5325a(AdAdapter adAdapter) {
            if (this.f4827a.f4857k != null) {
                this.f4827a.f4857k.m4618e();
            }
        }

        /* renamed from: a */
        public void mo5501a(C1924i c1924i) {
            this.f4827a.m5282a(c1924i, true);
            if (this.f4827a.f4855i != null && c1924i.m4524x() != null) {
                C1837q c21051 = new C21051(this);
                for (C2114e a : c1924i.m4524x()) {
                    a.m5314a(c21051);
                }
            }
        }

        /* renamed from: a */
        public void mo5326a(C2065a c2065a) {
            if (this.f4827a.f4855i != null) {
                this.f4827a.f4855i.mo5364a(c2065a);
            }
        }

        /* renamed from: b */
        public void mo5327b() {
            throw new IllegalStateException("Native ads manager their own impressions.");
        }
    }

    /* renamed from: com.facebook.ads.internal.t.e$3 */
    class C21083 implements aa {
        /* renamed from: a */
        final /* synthetic */ C2114e f4831a;

        C21083(C2114e c2114e) {
            this.f4831a = c2114e;
        }

        /* renamed from: a */
        public void mo5502a(int i) {
            if (this.f4831a.f4850a != null) {
                this.f4831a.f4850a.m4493a(i);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.t.e$4 */
    class C21094 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2114e f4832a;

        C21094(C2114e c2114e) {
            this.f4832a = c2114e;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (!this.f4832a.f4869w.m6673b()) {
                this.f4832a.f4869w.m6670a();
                this.f4832a.f4866t.m6774c();
                if (!(this.f4832a.f4868v == null || this.f4832a.f4868v.get() == null)) {
                    ((C1858a) this.f4832a.f4868v.get()).mo5381a();
                }
                if (this.f4832a.f4870x != null && this.f4832a.f4861o != null && this.f4832a.f4863q != null) {
                    this.f4832a.f4870x.m4571a(this.f4832a.f4861o);
                    this.f4832a.f4870x.m4574a(this.f4832a.f4863q);
                    this.f4832a.f4870x.m4575a(this.f4832a.f4840A);
                    this.f4832a.f4870x.m4579a(this.f4832a.f4841B);
                    this.f4832a.f4870x.m4580b(this.f4832a.f4842C);
                    this.f4832a.f4870x.m4582d(this.f4832a.f4843D);
                    this.f4832a.f4870x.m4581c(C2114e.m5300o(this.f4832a));
                    this.f4832a.f4870x.m4572a(this.f4832a.f4847H);
                    this.f4832a.f4870x.m4583e(this.f4832a.f4844E);
                    this.f4832a.f4870x.m4573a(C2196d.m5671a(this.f4832a.f4862p));
                    this.f4832a.f4870x.m4576a(this.f4832a.f4848I);
                    this.f4832a.f4870x.m4369a();
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.t.e$a */
    private class C2112a implements OnClickListener, OnLongClickListener, OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2114e f4835a;

        /* renamed from: com.facebook.ads.internal.t.e$a$1 */
        class C21101 implements DialogInterface.OnClickListener {
            /* renamed from: a */
            final /* synthetic */ C2112a f4833a;

            C21101(C2112a c2112a) {
                this.f4833a = c2112a;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Map a = this.f4833a.m5272a();
                a.put("is_two_step", "true");
                this.f4833a.m5275a(a);
            }
        }

        /* renamed from: com.facebook.ads.internal.t.e$a$2 */
        class C21112 implements DialogInterface.OnClickListener {
            /* renamed from: a */
            final /* synthetic */ C2112a f4834a;

            C21112(C2112a c2112a) {
                this.f4834a = c2112a;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (this.f4834a.f4835a.f4850a != null) {
                    this.f4834a.f4835a.f4850a.m4499b(this.f4834a.m5272a());
                }
            }
        }

        private C2112a(C2114e c2114e) {
            this.f4835a = c2114e;
        }

        /* renamed from: a */
        private Map m5272a() {
            Map hashMap = new HashMap();
            hashMap.put("touch", C2581k.m6645a(this.f4835a.f4869w.m6676e()));
            if (this.f4835a.f4840A != null) {
                hashMap.put("nti", String.valueOf(this.f4835a.f4840A.m5383c()));
            }
            if (this.f4835a.f4841B) {
                hashMap.put("nhs", String.valueOf(this.f4835a.f4841B));
            }
            this.f4835a.f4866t.m6771a(hashMap);
            return hashMap;
        }

        /* renamed from: a */
        private void m5275a(Map<String, String> map) {
            if (this.f4835a.f4850a != null) {
                this.f4835a.f4850a.m4504e(map);
            }
        }

        public void onClick(View view) {
            if (!this.f4835a.f4869w.m6675d()) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            int F = C2078a.m5067F(this.f4835a.f4851d);
            if (F < 0 || this.f4835a.f4869w.m6674c() >= ((long) F)) {
                if (this.f4835a.f4869w.m6672a(this.f4835a.f4851d)) {
                    if (this.f4835a.f4850a != null) {
                        this.f4835a.f4850a.m4502d(m5272a());
                    }
                } else if (C2078a.m5095e(this.f4835a.f4851d)) {
                    if (this.f4835a.f4850a != null) {
                        this.f4835a.f4850a.m4501c(m5272a());
                    }
                    C2577g.m6637a(new C21101(this), new C21112(this), C2564b.m6613a());
                } else {
                    m5275a(m5272a());
                }
            } else if (this.f4835a.f4869w.m6673b()) {
                Log.e("FBAudienceNetworkLog", "Clicks happened too fast.");
            } else {
                Log.e("FBAudienceNetworkLog", "Ad cannot be clicked before it is viewed.");
            }
        }

        public boolean onLongClick(View view) {
            boolean z = false;
            if (this.f4835a.f4861o == null || this.f4835a.f4845F == null) {
                return false;
            }
            this.f4835a.f4845F.setBounds(0, 0, this.f4835a.f4861o.getWidth(), this.f4835a.f4861o.getHeight());
            C2251c u = this.f4835a.f4845F;
            if (!this.f4835a.f4845F.m5768a()) {
                z = true;
            }
            u.m5767a(z);
            return true;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f4835a.f4869w.m6671a(motionEvent, this.f4835a.f4861o, view);
            return this.f4835a.f4865s != null && this.f4835a.f4865s.onTouch(view, motionEvent);
        }
    }

    /* renamed from: com.facebook.ads.internal.t.e$b */
    private class C2113b extends C1895c {
        /* renamed from: a */
        final /* synthetic */ C2114e f4836a;

        private C2113b(C2114e c2114e) {
            this.f4836a = c2114e;
        }

        /* renamed from: a */
        public void mo5392a() {
            if (this.f4836a.f4855i != null) {
                this.f4836a.f4855i.mo5367d();
            }
        }

        /* renamed from: b */
        public void mo5503b() {
        }
    }

    public C2114e(Context context, C1924i c1924i, C2047d c2047d, C1825c c1825c) {
        this(context, null, c1825c);
        this.f4850a = c1924i;
        this.f4859m = c2047d;
        this.f4858l = true;
        this.f4849J = new View(context);
    }

    public C2114e(Context context, String str, C1825c c1825c) {
        this.f4853f = UUID.randomUUID().toString();
        this.f4860n = C2070e.NATIVE_UNKNOWN;
        this.f4864r = new ArrayList();
        this.f4869w = new C2598w();
        this.f4842C = false;
        this.f4843D = false;
        this.f4846G = C2104d.ALL;
        this.f4847H = C1936a.ALL;
        this.f4851d = context;
        this.f4852e = str;
        this.f4856j = c1825c;
        if (f4839h != null) {
            this.f4854g = f4839h;
        } else {
            this.f4854g = new C2011b(context);
        }
        this.f4849J = new View(context);
    }

    public C2114e(C2114e c2114e) {
        this(c2114e.f4851d, null, c2114e.f4856j);
        this.f4859m = c2114e.f4859m;
        this.f4850a = c2114e.f4850a;
        this.f4858l = true;
        this.f4849J = new View(this.f4851d);
    }

    /* renamed from: A */
    private boolean m5278A() {
        return this.f4850a != null && this.f4850a.m4490B();
    }

    /* renamed from: B */
    private void m5279B() {
        if (!TextUtils.isEmpty(m5340p())) {
            C2615g.m6721a(new C2615g(), this.f4851d, Uri.parse(m5340p()), m5346v());
        }
    }

    /* renamed from: C */
    private void m5280C() {
        for (View view : this.f4864r) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
            view.setOnLongClickListener(null);
        }
        this.f4864r.clear();
    }

    /* renamed from: a */
    private void m5282a(@Nullable final C1924i c1924i, final boolean z) {
        if (c1924i != null) {
            if (this.f4846G.equals(C2104d.ALL)) {
                if (c1924i.m4512l() != null) {
                    this.f4854g.m4844a(c1924i.m4512l().m5352a(), c1924i.m4512l().m5354c(), c1924i.m4512l().m5353b());
                }
                if (!this.f4860n.equals(C2070e.NATIVE_BANNER)) {
                    if (c1924i.m4513m() != null) {
                        this.f4854g.m4844a(c1924i.m4513m().m5352a(), c1924i.m4513m().m5354c(), c1924i.m4513m().m5353b());
                    }
                    if (c1924i.m4524x() != null) {
                        for (C2114e c2114e : c1924i.m4524x()) {
                            if (c2114e.m5334j() != null) {
                                this.f4854g.m4844a(c2114e.m5334j().m5352a(), c2114e.m5334j().m5354c(), c2114e.m5334j().m5353b());
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(c1924i.m4520t())) {
                        this.f4854g.m4843a(c1924i.m4520t());
                    }
                }
            }
            this.f4854g.m4842a(new C1832a(this) {
                /* renamed from: c */
                final /* synthetic */ C2114e f4830c;

                /* renamed from: a */
                public void mo5368a() {
                    this.f4830c.f4850a = c1924i;
                    if (this.f4830c.f4855i != null) {
                        if (this.f4830c.f4846G.equals(C2104d.ALL) && !this.f4830c.m5278A()) {
                            this.f4830c.f4855i.mo5363a();
                        }
                        if (z) {
                            this.f4830c.f4855i.mo5365b();
                        }
                    }
                }

                /* renamed from: b */
                public void mo5369b() {
                    if (this.f4830c.f4850a != null) {
                        this.f4830c.f4850a.m4500c();
                        this.f4830c.f4850a = null;
                    }
                    if (this.f4830c.f4855i != null) {
                        this.f4830c.f4855i.mo5364a(C2065a.m5036a(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public static void m5284a(C2115g c2115g, ImageView imageView) {
        if (c2115g != null && imageView != null) {
            new C2252d(imageView).m5772a(c2115g.m5354c(), c2115g.m5353b()).m5775a(c2115g.m5352a());
        }
    }

    /* renamed from: a */
    private void m5285a(List<View> list, View view) {
        if (this.f4856j != null && this.f4856j.mo5362a(view)) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                m5285a((List) list, viewGroup.getChildAt(i));
            }
            return;
        }
        list.add(view);
    }

    /* renamed from: b */
    private void m5287b(View view, C1783f c1783f, List<View> list) {
        int i = 0;
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        } else if (m5330f()) {
            if (this.f4862p != null) {
                this.f4862p.clearAdReportingLayout();
            }
            if (c1783f == null) {
                if (this.f4860n == C2070e.NATIVE_UNKNOWN) {
                    if (this.f4855i != null) {
                        this.f4855i.mo5364a(new C2065a(AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD, "MediaView is missing."));
                    }
                    if (AdInternalSettings.isDebugBuild()) {
                        Log.e(f4837b, "MediaView is missing.");
                        return;
                    }
                    return;
                }
                if (this.f4855i != null) {
                    this.f4855i.mo5364a(new C2065a(AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD, "AdIconView is missing."));
                }
                if (AdInternalSettings.isDebugBuild()) {
                    Log.e(f4837b, "AdIconView is missing.");
                }
            } else if (c1783f.getAdContentsView() != null) {
                if (this.f4861o != null) {
                    Log.w(f4837b, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                    m5350z();
                }
                if (f4838c.containsKey(view) && ((WeakReference) f4838c.get(view)).get() != null) {
                    Log.w(f4837b, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                    ((C2114e) ((WeakReference) f4838c.get(view)).get()).m5350z();
                }
                this.f4871y = new C2112a();
                this.f4861o = view;
                this.f4863q = c1783f;
                if (view instanceof ViewGroup) {
                    this.f4872z = new ab(view.getContext(), new C21083(this));
                    ((ViewGroup) view).addView(this.f4872z);
                }
                List<View> arrayList = new ArrayList(list);
                if (this.f4849J != null) {
                    arrayList.add(this.f4849J);
                }
                for (View view2 : arrayList) {
                    this.f4864r.add(view2);
                    view2.setOnClickListener(this.f4871y);
                    view2.setOnTouchListener(this.f4871y);
                    if (C2078a.m5091b(view2.getContext())) {
                        view2.setOnLongClickListener(this.f4871y);
                    }
                }
                this.f4850a.m4495a(view, (List) arrayList);
                int f = this.f4859m != null ? this.f4859m.m4985f() : (this.f4857k == null || this.f4857k.m4614b() == null) ? 1 : this.f4857k.m4614b().m4985f();
                this.f4867u = new C21094(this);
                View adContentsView = c1783f != null ? c1783f.getAdContentsView() : this.f4861o;
                int g = this.f4859m != null ? this.f4859m.m4986g() : (this.f4857k == null || this.f4857k.m4614b() == null) ? 0 : this.f4857k.m4614b().m4986g();
                this.f4866t = new C2630a(adContentsView, f, g, true, this.f4867u);
                C2630a c2630a = this.f4866t;
                if (this.f4859m != null) {
                    i = this.f4859m.m4987h();
                } else if (this.f4850a != null) {
                    i = this.f4850a.m4510j();
                } else if (!(this.f4857k == null || this.f4857k.m4614b() == null)) {
                    i = this.f4857k.m4614b().m4987h();
                }
                c2630a.m6770a(i);
                C2630a c2630a2 = this.f4866t;
                int i2 = this.f4859m != null ? this.f4859m.m4988i() : this.f4850a != null ? this.f4850a.m4511k() : (this.f4857k == null || this.f4857k.m4614b() == null) ? 1000 : this.f4857k.m4614b().m4988i();
                c2630a2.m6772b(i2);
                this.f4870x = new C1938p(this.f4851d, new C2113b(), this.f4866t, this.f4850a);
                this.f4870x.m4577a((List) arrayList);
                f4838c.put(view, new WeakReference(this));
                if (C2078a.m5091b(this.f4851d)) {
                    this.f4845F = new C2251c();
                    this.f4845F.m5766a(this.f4852e);
                    this.f4845F.m5770b(this.f4851d.getPackageName());
                    this.f4845F.m5765a(this.f4866t);
                    if (this.f4850a.m4526z() > 0) {
                        this.f4845F.m5763a(this.f4850a.m4526z(), this.f4850a.m4525y());
                    }
                    if (this.f4859m != null) {
                        this.f4845F.m5764a(this.f4859m.m4980a());
                    } else if (!(this.f4857k == null || this.f4857k.m4614b() == null)) {
                        this.f4845F.m5764a(this.f4857k.m4614b().m4980a());
                    }
                    this.f4861o.getOverlay().add(this.f4845F);
                }
            } else if (this.f4855i != null) {
                this.f4855i.mo5364a(new C2065a(AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD, "ad media type is not supported."));
            }
        } else {
            Log.e(f4837b, "Ad not loaded");
        }
    }

    /* renamed from: o */
    static /* synthetic */ boolean m5300o(C2114e c2114e) {
        return c2114e.m5344t() == C2119l.ON;
    }

    @Nullable
    /* renamed from: a */
    public C1924i m5308a() {
        return this.f4850a;
    }

    @Nullable
    /* renamed from: a */
    public String m5309a(String str) {
        return !m5330f() ? null : this.f4850a.m4492a(str);
    }

    /* renamed from: a */
    public void m5310a(OnTouchListener onTouchListener) {
        this.f4865s = onTouchListener;
    }

    /* renamed from: a */
    public void m5311a(View view, C1783f c1783f) {
        List arrayList = new ArrayList();
        m5285a(arrayList, view);
        m5287b(view, c1783f, arrayList);
    }

    /* renamed from: a */
    public void m5312a(View view, C1783f c1783f, List<View> list) {
        m5287b(view, c1783f, list);
    }

    /* renamed from: a */
    public void m5313a(@Nullable NativeAdLayout nativeAdLayout) {
        this.f4862p = nativeAdLayout;
    }

    /* renamed from: a */
    public void m5314a(C1837q c1837q) {
        if (this.f4850a != null) {
            this.f4850a.m4496a(c1837q);
        }
    }

    /* renamed from: a */
    public void m5315a(C2070e c2070e) {
        this.f4860n = c2070e;
    }

    /* renamed from: a */
    public void m5316a(C2104d c2104d, String str) {
        if (this.f4858l) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.f4858l = true;
        this.f4846G = c2104d;
        if (c2104d.equals(C2104d.NONE)) {
            this.f4847H = C1936a.NONE;
        }
        C1943a c1943a = new C1943a(this.f4852e, this.f4860n, this.f4860n == C2070e.NATIVE_UNKNOWN ? AdPlacementType.NATIVE : AdPlacementType.NATIVE_BANNER, null, 1);
        c1943a.m4601a(c2104d);
        c1943a.m4602a(this.f4848I);
        this.f4857k = new C1960g(this.f4851d, c1943a);
        this.f4857k.m4609a(new C21061(this));
        this.f4857k.m4615b(str);
    }

    /* renamed from: a */
    public void m5317a(C1828h c1828h) {
        this.f4855i = c1828h;
    }

    /* renamed from: a */
    public void m5318a(C2118k c2118k) {
        this.f4840A = c2118k;
    }

    /* renamed from: a */
    public void m5319a(C1858a c1858a) {
        this.f4868v = new WeakReference(c1858a);
    }

    /* renamed from: a */
    public void m5320a(boolean z) {
        this.f4841B = z;
    }

    /* renamed from: a */
    public void m5321a(boolean z, boolean z2) {
        if (z) {
            if (!(!this.f4846G.equals(C2104d.NONE) || m5278A() || this.f4855i == null)) {
                this.f4855i.mo5363a();
            }
            if (this.f4866t != null) {
                this.f4866t.m6769a();
                return;
            }
            return;
        }
        if (this.f4866t != null) {
            this.f4866t.m6774c();
        }
        if (this.f4855i != null && z2) {
            this.f4855i.mo5364a(C2065a.m5036a(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
        }
    }

    /* renamed from: b */
    public void m5322b(String str) {
        this.f4848I = str;
    }

    /* renamed from: b */
    public void m5323b(boolean z) {
        this.f4844E = z;
    }

    /* renamed from: b */
    public boolean m5324b() {
        return this.f4857k == null || this.f4857k.m4620g();
    }

    /* renamed from: c */
    public void m5325c() {
        if (this.f4846G.equals(C2104d.NONE)) {
            this.f4847H = C1936a.MANUAL;
        }
        this.f4846G = C2104d.ALL;
        m5282a(this.f4850a, false);
    }

    /* renamed from: c */
    public void m5326c(boolean z) {
        this.f4842C = z;
    }

    /* renamed from: d */
    public void m5327d() {
        if (this.f4857k != null) {
            this.f4857k.m4613a(true);
            this.f4857k = null;
        }
    }

    /* renamed from: d */
    public void m5328d(boolean z) {
        this.f4843D = z;
    }

    /* renamed from: e */
    public String m5329e() {
        return this.f4852e;
    }

    /* renamed from: f */
    public boolean m5330f() {
        return this.f4850a != null && this.f4850a.m4489A();
    }

    /* renamed from: g */
    public boolean m5331g() {
        return m5330f() && this.f4850a.m4505e();
    }

    /* renamed from: h */
    public boolean m5332h() {
        return this.f4850a != null && this.f4850a.m4506f();
    }

    @Nullable
    /* renamed from: i */
    public C2115g m5333i() {
        return !m5330f() ? null : this.f4850a.m4512l();
    }

    @Nullable
    /* renamed from: j */
    public C2115g m5334j() {
        return !m5330f() ? null : this.f4850a.m4513m();
    }

    @Nullable
    /* renamed from: k */
    public C2117j m5335k() {
        return !m5330f() ? null : this.f4850a.m4514n();
    }

    @Nullable
    /* renamed from: l */
    public String m5336l() {
        return !m5330f() ? null : this.f4850a.m4515o();
    }

    @Nullable
    /* renamed from: m */
    public C2116i m5337m() {
        return !m5330f() ? null : this.f4850a.m4516p();
    }

    @Nullable
    /* renamed from: n */
    public String m5338n() {
        return !m5330f() ? null : this.f4853f;
    }

    @Nullable
    /* renamed from: o */
    public C2115g m5339o() {
        return !m5330f() ? null : this.f4850a.m4517q();
    }

    @Nullable
    /* renamed from: p */
    public String m5340p() {
        return !m5330f() ? null : this.f4850a.m4518r();
    }

    @Nullable
    /* renamed from: q */
    public String m5341q() {
        return !m5330f() ? null : this.f4850a.m4519s();
    }

    @Nullable
    /* renamed from: r */
    public String m5342r() {
        return (!m5330f() || TextUtils.isEmpty(this.f4850a.m4520t())) ? null : this.f4854g.m4846c(this.f4850a.m4520t());
    }

    @Nullable
    /* renamed from: s */
    public String m5343s() {
        return !m5330f() ? null : this.f4850a.m4521u();
    }

    @Nullable
    /* renamed from: t */
    public C2119l m5344t() {
        return !m5330f() ? C2119l.f4896a : this.f4850a.m4522v();
    }

    @Nullable
    /* renamed from: u */
    public List<C2114e> m5345u() {
        return !m5330f() ? null : this.f4850a.m4524x();
    }

    @Nullable
    /* renamed from: v */
    public String m5346v() {
        return !m5330f() ? null : this.f4850a.getClientToken();
    }

    /* renamed from: w */
    public void m5347w() {
        this.f4849J.performClick();
    }

    /* renamed from: x */
    public C2118k m5348x() {
        return this.f4840A;
    }

    /* renamed from: y */
    public void m5349y() {
        if (C1993a.m4787a(this.f4851d, false)) {
            C2195c a = C2196d.m5672a(this.f4851d, C2087d.m5183a(this.f4851d), m5346v(), this.f4862p);
            if (a == null) {
                m5279B();
                return;
            }
            this.f4862p.setAdReportingLayout(a);
            a.m5663a();
            return;
        }
        m5279B();
    }

    /* renamed from: z */
    public void m5350z() {
        if (this.f4861o != null && this.f4863q != null) {
            if (f4838c.containsKey(this.f4861o) && ((WeakReference) f4838c.get(this.f4861o)).get() == this) {
                if ((this.f4861o instanceof ViewGroup) && this.f4872z != null) {
                    ((ViewGroup) this.f4861o).removeView(this.f4872z);
                    this.f4872z = null;
                }
                if (this.f4850a != null) {
                    this.f4850a.m4500c();
                }
                if (this.f4845F != null && C2078a.m5091b(this.f4851d)) {
                    this.f4845F.m5769b();
                    this.f4861o.getOverlay().remove(this.f4845F);
                }
                f4838c.remove(this.f4861o);
                m5280C();
                this.f4861o = null;
                this.f4863q = null;
                if (this.f4866t != null) {
                    this.f4866t.m6774c();
                    this.f4866t = null;
                }
                this.f4870x = null;
                return;
            }
            throw new IllegalStateException("View not registered with this NativeAd");
        }
    }
}
