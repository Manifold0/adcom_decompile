package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2551u.C2511a;
import com.facebook.ads.internal.view.component.p058a.C2259c;
import com.facebook.ads.internal.view.component.p058a.C2276d;
import com.facebook.ads.internal.view.component.p058a.C2279e;
import com.facebook.ads.internal.view.component.p058a.C2279e.C2278a;
import com.facebook.ads.internal.view.component.p058a.C2281g;
import com.facebook.ads.internal.view.component.p058a.C2286l;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2422c;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C1822f;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d.C2450a;
import com.facebook.ads.internal.view.p022i.p067c.C2456f;
import com.facebook.ads.internal.view.p022i.p067c.C2459g;
import com.facebook.ads.internal.view.p022i.p067c.C2475k;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import com.facebook.ads.internal.view.p022i.p067c.C2491o;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.view.n */
public class C2526n extends C2323o {
    /* renamed from: A */
    private boolean f6151A = false;
    /* renamed from: B */
    private boolean f6152B = false;
    /* renamed from: d */
    private final BackButtonInterceptor f6153d = new C25171(this);
    /* renamed from: e */
    private final C1822f f6154e = new C25182(this);
    /* renamed from: f */
    private final C1812l f6155f = new C25193(this);
    /* renamed from: g */
    private final C1814j f6156g = new C25204(this);
    /* renamed from: h */
    private final C1818d f6157h = new C25215(this);
    /* renamed from: i */
    private final C1810n f6158i = new C25226(this);
    /* renamed from: j */
    private final C2394a f6159j = new C2394a(getContext());
    /* renamed from: k */
    private final C2491o f6160k;
    /* renamed from: l */
    private final C2456f f6161l;
    /* renamed from: m */
    private final C1879k f6162m;
    /* renamed from: n */
    private final C1880l f6163n;
    /* renamed from: o */
    private final C2630a f6164o;
    /* renamed from: p */
    private final C1858a f6165p;
    /* renamed from: q */
    private final C2598w f6166q = new C2598w();
    @Nullable
    /* renamed from: r */
    private final C2011b f6167r;
    /* renamed from: s */
    private final AtomicBoolean f6168s = new AtomicBoolean(false);
    /* renamed from: t */
    private final AtomicBoolean f6169t = new AtomicBoolean(false);
    /* renamed from: u */
    private final C2422c f6170u;
    @Nullable
    /* renamed from: v */
    private AudienceNetworkActivity f6171v;
    @Nullable
    /* renamed from: w */
    private C2389a f6172w;
    @Nullable
    /* renamed from: x */
    private C2286l f6173x;
    /* renamed from: y */
    private boolean f6174y = false;
    /* renamed from: z */
    private boolean f6175z = false;

    /* renamed from: com.facebook.ads.internal.view.n$1 */
    class C25171 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2526n f6142a;

        C25171(C2526n c2526n) {
            this.f6142a = c2526n;
        }

        public boolean interceptBackButton() {
            return (this.f6142a.f6173x != null ? this.f6142a.f6173x.mo5567c() : false) || !this.f6142a.b.m6458a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$2 */
    class C25182 extends C1822f {
        /* renamed from: a */
        final /* synthetic */ C2526n f6143a;

        C25182(C2526n c2526n) {
            this.f6143a = c2526n;
        }

        /* renamed from: a */
        public void m6496a(C2407e c2407e) {
            if (this.f6143a.getAudienceNetworkListener() != null) {
                this.f6143a.getAudienceNetworkListener().mo5336a("videoInterstitalEvent", (C2061d) c2407e);
            }
            if (!this.f6143a.f6174y) {
                this.f6143a.f6159j.m6165g();
                this.f6143a.f6159j.m6170l();
                this.f6143a.f6174y = true;
            }
            if (this.f6143a.f6171v != null) {
                this.f6143a.f6171v.finish();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$3 */
    class C25193 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2526n f6144a;

        C25193(C2526n c2526n) {
            this.f6144a = c2526n;
        }

        /* renamed from: a */
        public void m6498a(C2411k c2411k) {
            if (this.f6144a.getAudienceNetworkListener() != null) {
                this.f6144a.getAudienceNetworkListener().mo5336a("videoInterstitalEvent", (C2061d) c2411k);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$4 */
    class C25204 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2526n f6145a;

        C25204(C2526n c2526n) {
            this.f6145a = c2526n;
        }

        /* renamed from: a */
        public void m6500a(C2410i c2410i) {
            if (this.f6145a.getAudienceNetworkListener() != null) {
                this.f6145a.getAudienceNetworkListener().mo5336a("videoInterstitalEvent", (C2061d) c2410i);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$5 */
    class C25215 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2526n f6146a;

        C25215(C2526n c2526n) {
            this.f6146a = c2526n;
        }

        /* renamed from: a */
        public void m6502a(C2406c c2406c) {
            this.f6146a.f6168s.set(true);
            if (this.f6146a.getAudienceNetworkListener() != null) {
                this.f6146a.getAudienceNetworkListener().mo5336a("videoInterstitalEvent", (C2061d) c2406c);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$6 */
    class C25226 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ C2526n f6147a;

        C25226(C2526n c2526n) {
            this.f6147a = c2526n;
        }

        /* renamed from: a */
        public void m6504a(C2412m c2412m) {
            this.f6147a.f6152B = true;
            if (!this.f6147a.f6174y) {
                this.f6147a.f6169t.set(this.f6147a.f6159j.m6169k());
                this.f6147a.m6509a();
            }
            if (this.f6147a.getAudienceNetworkListener() != null) {
                this.f6147a.getAudienceNetworkListener().mo5336a("videoInterstitalEvent", (C2061d) c2412m);
            }
            this.f6147a.f6164o.m6769a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$7 */
    class C25237 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2526n f6148a;

        C25237(C2526n c2526n) {
            this.f6148a = c2526n;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (!this.f6148a.f6166q.m6673b()) {
                this.f6148a.f6166q.m6670a();
                Map hashMap = new HashMap();
                if (!TextUtils.isEmpty(this.f6148a.f6162m.m4307c())) {
                    this.f6148a.f6164o.m6771a(hashMap);
                    hashMap.put("touch", C2581k.m6645a(this.f6148a.f6166q.m6676e()));
                    this.f6148a.m5990a(hashMap);
                    this.f6148a.a.mo5470a(this.f6148a.f6162m.m4307c(), hashMap);
                    if (this.f6148a.getAudienceNetworkListener() != null) {
                        this.f6148a.getAudienceNetworkListener().mo5335a("com.facebook.ads.interstitial.impression.logged");
                    }
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$8 */
    class C25248 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2526n f6149a;

        C25248(C2526n c2526n) {
            this.f6149a = c2526n;
        }

        public void run() {
            if (!this.f6149a.f6152B) {
                this.f6149a.b.m6457a(true);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.n$9 */
    class C25259 implements C2511a {
        /* renamed from: a */
        final /* synthetic */ C2526n f6150a;

        C25259(C2526n c2526n) {
            this.f6150a = c2526n;
        }

        /* renamed from: a */
        public void mo5636a() {
            if (this.f6150a.f6159j.m6171m() && !this.f6150a.f6159j.m6172n()) {
                this.f6150a.f6159j.m6157a(C2389a.AUTO_STARTED);
            }
            this.f6150a.f6173x.mo5566b();
        }

        /* renamed from: b */
        public void mo5637b() {
            this.f6150a.f6173x.mo5564a();
            this.f6150a.f6159j.m6160a(false);
        }
    }

    public C2526n(Context context, C2085c c2085c, C1879k c1879k, @Nullable C2011b c2011b, C1789a c1789a) {
        super(context, c2085c, c1789a);
        this.f6159j.setVideoProgressReportIntervalMs(c1879k.m4312h());
        C2600x.m6679a(this.f6159j);
        C2600x.m6680a(this.f6159j, 0);
        this.f6162m = c1879k;
        this.f6163n = (C1880l) this.f6162m.m4308d().get(0);
        this.f6167r = c2011b;
        this.f6160k = new C2491o(getContext());
        this.f6161l = new C2456f(context);
        this.f6159j.getEventBus().m5029a(this.f6155f, this.f6156g, this.f6157h, this.f6154e, this.f6158i);
        setupPlugins(this.f6163n);
        this.f6165p = new C25237(this);
        this.f6164o = new C2630a(this, 1, this.f6165p);
        this.f6164o.m6770a(c1879k.m4310f());
        this.f6164o.m6772b(c1879k.m4311g());
        this.f6170u = new C2423b(getContext(), this.a, this.f6159j, this.f6162m.m4307c());
        C2394a c2394a = this.f6159j;
        String a = this.f6163n.m4317c().m4238a();
        String str = "";
        if (!(this.f6167r == null || a == null)) {
            str = this.f6167r.m4846c(a);
        }
        if (TextUtils.isEmpty(str)) {
            str = a;
        }
        c2394a.setVideoURI(str);
    }

    /* renamed from: a */
    private void m6509a() {
        this.f6161l.setVisibility(this.f6169t.get() ? 0 : 8);
    }

    private void setUpContent(int i) {
        C2279e a = new C2278a(getContext(), this.a, getAudienceNetworkListener(), this.f6162m, this.f6159j, this.f6164o, this.f6166q).m5845a(C2504i.f6089a).m5849b(i).m5847a(this.f6160k).m5846a(this.f6161l).m5848a();
        C2259c a2 = C2276d.m5833a(a);
        m6509a();
        this.f6173x = C2281g.m5865a(a, C2600x.f6419a.heightPixels - a2.getExactMediaHeightIfAvailable(), C2600x.f6419a.widthPixels - a2.getExactMediaWidthIfAvailable(), this.f6151A);
        C2511a c2511a = null;
        if (this.f6173x != null) {
            c2511a = new C25259(this);
        }
        m5989a(a2, this.f6173x, c2511a, a2.getExactMediaHeightIfAvailable(), C2600x.f6419a.widthPixels - a2.getExactMediaWidthIfAvailable(), a2.mo5555a(), i);
    }

    private void setupPlugins(C1880l c1880l) {
        this.f6159j.m6162d();
        this.f6159j.m6158a(this.f6160k);
        this.f6159j.m6158a(this.f6161l);
        if (!TextUtils.isEmpty(c1880l.m4317c().m4245g())) {
            C2390b c2459g = new C2459g(getContext());
            this.f6159j.m6158a(c2459g);
            c2459g.setImage(c1880l.m4317c().m4245g());
        }
        C2390b c2481l = new C2481l(getContext(), true);
        this.f6159j.m6158a(c2481l);
        this.f6159j.m6158a(new C2451d(c2481l, c1880l.m4317c().m4243e() ? C2450a.FADE_OUT_ON_PLAY : C2450a.VISIBLE, true));
        this.f6159j.m6158a(new C2475k(getContext()));
        this.f6159j.m6158a(this.b);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.m5988a(audienceNetworkActivity, this.f6162m);
        this.f6171v = audienceNetworkActivity;
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
        this.f6171v.addBackButtonInterceptor(this.f6153d);
        C1880l c1880l = (C1880l) this.f6162m.m4308d().get(0);
        this.f6159j.setVolume(c1880l.m4317c().m4244f() ? 0.0f : 1.0f);
        if (c1880l.m4317c().m4243e()) {
            this.f6159j.m6157a(C2389a.AUTO_STARTED);
        }
        if (c1880l.m4317c().m4241c() > 0) {
            postDelayed(new C25248(this), (long) C2078a.ad(getContext()));
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    public void a_(boolean z) {
        if (this.f6173x != null) {
            this.f6173x.mo5569e();
        }
        if (!this.f6174y && !this.f6159j.m6171m()) {
            this.f6172w = this.f6159j.getVideoStartReason();
            this.f6175z = z;
            this.f6159j.m6160a(false);
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        if (this.f6173x != null) {
            this.f6173x.mo5570f();
        }
        if (!this.f6174y && !this.f6159j.m6172n()) {
            if ((this.f6159j.getState() != C2501d.PREPARED || this.f6159j.getVideoStartReason() != C2389a.NOT_STARTED) && this.f6159j.getState() != C2501d.PLAYBACK_COMPLETED && this.f6172w != null) {
                if (!this.f6175z || z) {
                    this.f6159j.m6157a(this.f6172w);
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        C2600x.m6689b(this.f6159j);
        C2600x.m6689b(this.f6160k);
        C2600x.m6689b(this.f6161l);
        if (this.f6173x != null) {
            C2600x.m6689b(this.f6173x);
            this.f6151A = this.f6173x.mo5568d();
        }
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!this.f6174y) {
            if (!this.f6168s.get()) {
                this.f6159j.m6164f();
            }
            if (!(this.f6162m == null || TextUtils.isEmpty(this.f6162m.m4307c()))) {
                Map hashMap = new HashMap();
                this.f6164o.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(this.f6166q.m6676e()));
                this.a.mo5483l(this.f6162m.m4307c(), hashMap);
            }
            this.f6159j.m6165g();
            this.f6159j.m6170l();
            this.f6174y = true;
        }
        if (this.f6173x != null) {
            this.f6173x.mo5571g();
        }
        this.f6164o.m6774c();
        this.f6171v = null;
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f6166q.m6671a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
