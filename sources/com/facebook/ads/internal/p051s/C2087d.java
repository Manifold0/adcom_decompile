package com.facebook.ads.internal.p051s;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p025w.p026b.C2585o;
import com.facebook.ads.internal.p025w.p057e.C2613e;
import com.facebook.ads.internal.p040j.C2022a;
import com.facebook.ads.internal.p040j.C2030d;
import com.facebook.ads.internal.p045n.C2051a;
import com.facebook.ads.internal.p048p.C2063a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2080a.C2079a;
import java.util.Map;

@UiThread
/* renamed from: com.facebook.ads.internal.s.d */
public class C2087d implements C2085c {
    /* renamed from: a */
    private static final String f4731a = C2087d.class.getSimpleName();
    /* renamed from: b */
    private static double f4732b;
    /* renamed from: c */
    private static String f4733c;
    /* renamed from: d */
    private static volatile boolean f4734d = false;
    @Nullable
    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: i */
    private static C2085c f4735i;
    /* renamed from: e */
    private final C2084b f4736e;
    /* renamed from: f */
    private final C2030d f4737f;
    /* renamed from: g */
    private final Context f4738g;
    @Nullable
    /* renamed from: h */
    private C2088e f4739h;

    private C2087d(Context context) {
        this.f4738g = context.getApplicationContext();
        this.f4737f = new C2030d(context);
        this.f4736e = new C2084b(context, new C2092i(context, this.f4737f));
        this.f4736e.m5156b();
        C2087d.m5186b(context);
    }

    /* renamed from: a */
    public static synchronized C2085c m5183a(Context context) {
        C2085c c2085c;
        synchronized (C2087d.class) {
            if (f4735i == null) {
                f4735i = new C2087d(context.getApplicationContext());
            }
            c2085c = f4735i;
        }
        return c2085c;
    }

    /* renamed from: a */
    private void m5184a(final C2080a c2080a) {
        if (c2080a.m5138g()) {
            if (this.f4739h != null) {
                this.f4739h.m5208a(c2080a);
            }
            this.f4737f.m4908a(c2080a.m5132a(), c2080a.m5139h().f4743c, c2080a.m5140i().toString(), c2080a.m5133b(), c2080a.m5134c(), c2080a.m5135d(), c2080a.m5136e(), new C2022a<String>(this) {
                /* renamed from: b */
                final /* synthetic */ C2087d f4730b;

                /* renamed from: a */
                public void mo5467a(int i, String str) {
                    super.mo5467a(i, str);
                }

                /* renamed from: a */
                public void m5181a(String str) {
                    super.mo5468a(str);
                    if (C2078a.m5116z(this.f4730b.f4738g)) {
                        C2063a.m5035a(this.f4730b.f4738g, c2080a.m5140i().toString(), str);
                    }
                    if (c2080a.m5137f()) {
                        this.f4730b.f4736e.m5155a();
                    } else {
                        this.f4730b.f4736e.m5156b();
                    }
                }
            });
            return;
        }
        Log.e(f4731a, "Attempting to log an invalid " + c2080a.m5140i() + " event.");
    }

    /* renamed from: b */
    private static synchronized void m5186b(Context context) {
        synchronized (C2087d.class) {
            if (!f4734d) {
                C2051a.m4997b(context);
                C2585o.m6651a();
                f4732b = C2585o.m6652b();
                f4733c = C2585o.m6653c();
                f4734d = true;
            }
        }
    }

    /* renamed from: a */
    public void mo5469a(String str) {
        new C2613e(this.f4738g).execute(new String[]{str});
    }

    /* renamed from: a */
    public void mo5470a(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.IMPRESSION).m5128a(true).m5129a());
        }
    }

    /* renamed from: a */
    public void mo5471a(String str, Map<String, String> map, String str2, C2089f c2089f) {
        m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(c2089f).m5125a(C2090g.m5209a(str2)).m5128a(true).m5129a());
    }

    /* renamed from: b */
    public void mo5472b(String str) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5124a(C2089f.DEFERRED).m5125a(C2090g.SHOW_AD_CALLED).m5128a(true).m5129a());
        }
    }

    /* renamed from: b */
    public void mo5473b(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.INVALIDATION).m5128a(false).m5129a());
        }
    }

    /* renamed from: c */
    public void mo5474c(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.OPEN_LINK).m5128a(true).m5129a());
        }
    }

    /* renamed from: d */
    public void mo5475d(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.OFF_TARGET_CLICK).m5128a(true).m5129a());
        }
    }

    /* renamed from: e */
    public void mo5476e(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.f4754k).m5128a(true).m5129a());
        }
    }

    /* renamed from: f */
    public void mo5477f(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.NATIVE_VIEW).m5128a(false).m5129a());
        }
    }

    /* renamed from: g */
    public void mo5478g(String str, Map<String, String> map) {
        m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.BROWSER_SESSION).m5128a(false).m5129a());
    }

    /* renamed from: h */
    public void mo5479h(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.STORE).m5128a(true).m5129a());
        }
    }

    /* renamed from: i */
    public void mo5480i(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.CLICK_GUARD).m5128a(true).m5129a());
        }
    }

    /* renamed from: j */
    public void mo5481j(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.TWO_STEP).m5128a(true).m5129a());
        }
    }

    /* renamed from: k */
    public void mo5482k(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.TWO_STEP_CANCEL).m5128a(true).m5129a());
        }
    }

    /* renamed from: l */
    public void mo5483l(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.CLOSE).m5128a(true).m5129a());
        }
    }

    /* renamed from: m */
    public void mo5484m(String str, Map<String, String> map) {
        m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.USER_RETURN).m5128a(true).m5129a());
    }

    /* renamed from: n */
    public void mo5485n(String str, Map<String, String> map) {
        m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.AD_REPORTING).m5128a(false).m5129a());
    }

    /* renamed from: o */
    public void mo5486o(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.PREVIEW_IMPRESSION).m5128a(true).m5129a());
        }
    }

    /* renamed from: p */
    public void mo5487p(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.DEFERRED).m5125a(C2090g.AD_SELECTION).m5128a(true).m5129a());
        }
    }

    /* renamed from: q */
    public void mo5488q(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.SWIPE_TO_CLICK).m5128a(true).m5129a());
        }
    }

    /* renamed from: r */
    public void mo5489r(String str, Map<String, String> map) {
        if (!TextUtils.isEmpty(str)) {
            m5184a(new C2079a().m5126a(str).m5123a(f4732b).m5130b(f4733c).m5127a((Map) map).m5124a(C2089f.IMMEDIATE).m5125a(C2090g.WATCH_AND_X_MINIMIZED).m5128a(true).m5129a());
        }
    }
}
