package com.facebook.ads.internal.p033b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.adapters.C1897d;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p028u.C2122b;
import com.facebook.ads.internal.p028u.C2129c;
import com.facebook.ads.internal.p028u.C2129c.C1856b;
import com.facebook.ads.internal.p028u.C2133f;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p045n.C2051a;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2087d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.ads.internal.protocol.C2074g;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.b.c */
public abstract class C1946c implements C1856b {
    /* renamed from: i */
    private static final String f4261i = C1946c.class.getSimpleName();
    /* renamed from: j */
    private static final Handler f4262j = new Handler(Looper.getMainLooper());
    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: k */
    private static C2129c f4263k;
    /* renamed from: l */
    private static C1897d f4264l;
    /* renamed from: a */
    volatile boolean f4265a;
    /* renamed from: b */
    protected final Context f4266b;
    /* renamed from: c */
    protected C1786a f4267c;
    /* renamed from: d */
    View f4268d;
    @Nullable
    /* renamed from: e */
    AdAdapter f4269e;
    /* renamed from: f */
    public AdAdapter f4270f;
    /* renamed from: g */
    public final C2085c f4271g;
    /* renamed from: h */
    public final C1943a f4272h;
    /* renamed from: m */
    private final C2129c f4273m;
    /* renamed from: n */
    private final C1897d f4274n;
    /* renamed from: o */
    private C2046c f4275o;
    /* renamed from: p */
    private C2122b f4276p;

    /* renamed from: com.facebook.ads.internal.b.c$2 */
    class C19492 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1946c f4279a;

        C19492(C1946c c1946c) {
            this.f4279a = c1946c;
        }

        public void run() {
            try {
                C1946c.m4605a(this.f4279a);
            } catch (Exception e) {
                C2625a.m6741b(this.f4279a.f4266b, "api", C2626b.f6552q, e);
            }
        }
    }

    static {
        C2567c.m6620a();
    }

    public C1946c(Context context, C1943a c1943a) {
        this.f4266b = context.getApplicationContext();
        this.f4272h = c1943a;
        if (f4263k != null) {
            this.f4273m = f4263k;
        } else {
            this.f4273m = new C2129c(this.f4266b);
        }
        this.f4273m.m5424a((C1856b) this);
        if (f4264l != null) {
            this.f4274n = f4264l;
        } else {
            this.f4274n = new C1897d();
        }
        try {
            CookieManager.getInstance();
            if (VERSION.SDK_INT < 21) {
                CookieSyncManager.createInstance(this.f4266b);
            }
        } catch (Throwable e) {
            Log.w(f4261i, "Failed to initialize CookieManager.", e);
        }
        C2051a.m4997b(this.f4266b);
        this.f4271g = C2087d.m5183a(this.f4266b);
    }

    /* renamed from: a */
    static /* synthetic */ void m4605a(C1946c c1946c) {
        c1946c.f4269e = null;
        C2046c c2046c = c1946c.f4275o;
        C2044a e = c2046c.m4975e();
        if (e == null) {
            c1946c.f4267c.mo5326a(C2065a.m5036a(AdErrorType.NO_FILL, ""));
            return;
        }
        String a = e.m4963a();
        AdAdapter a2 = c1946c.f4274n.m4382a(c2046c.m4970a().m4981b());
        if (a2 == null) {
            Log.e(f4261i, "Adapter does not exist: " + a);
            c1946c.m4622i();
        } else if (c1946c.f4272h.m4598a() != a2.getPlacementType()) {
            c1946c.f4267c.mo5326a(C2065a.m5036a(AdErrorType.INTERNAL_ERROR, ""));
        } else {
            c1946c.f4269e = a2;
            C2047d a3 = c2046c.m4970a();
            Map hashMap = new HashMap();
            hashMap.put("data", e.m4966c());
            hashMap.put("definition", a3);
            hashMap.put("placementId", c1946c.f4272h.f4247a);
            hashMap.put(AudienceNetworkActivity.REQUEST_TIME, Long.valueOf(a3.m4980a()));
            hashMap.put("data_model_type", e.m4965b());
            if (c1946c.f4276p == null) {
                c1946c.f4267c.mo5326a(C2065a.m5036a(AdErrorType.UNKNOWN_ERROR, "environment is empty"));
                return;
            }
            c1946c.mo5420a(a2, c2046c, e, hashMap);
        }
    }

    /* renamed from: a */
    protected abstract void mo5419a();

    /* renamed from: a */
    void m4607a(AdAdapter adAdapter) {
        if (adAdapter != null) {
            adAdapter.onDestroy();
        }
    }

    /* renamed from: a */
    protected abstract void mo5420a(AdAdapter adAdapter, C2046c c2046c, C2044a c2044a, Map<String, Object> map);

    /* renamed from: a */
    public void m4609a(C1786a c1786a) {
        this.f4267c = c1786a;
    }

    /* renamed from: a */
    public synchronized void mo5379a(final C2065a c2065a) {
        m4623j().post(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1946c f4281b;

            public void run() {
                this.f4281b.f4267c.mo5326a(c2065a);
            }
        });
    }

    /* renamed from: a */
    public synchronized void mo5380a(final C2133f c2133f) {
        if (C2078a.m5082U(this.f4266b)) {
            C2065a c = mo5429c();
            if (c != null) {
                Log.e(AudienceNetworkAds.TAG, c.m5039b());
                mo5379a(c);
            }
        }
        m4623j().post(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C1946c f4278b;

            public void run() {
                C2046c a = c2133f.mo5506a();
                if (a == null || a.m4970a() == null) {
                    throw new IllegalStateException("invalid placement in response");
                }
                this.f4278b.f4275o = a;
                this.f4278b.m4622i();
            }
        });
    }

    /* renamed from: a */
    protected void mo5421a(String str) {
        try {
            this.f4276p = this.f4272h.m4599a(this.f4266b, new C2074g(this.f4266b, str, this.f4272h.f4247a, this.f4272h.f4248b));
            this.f4273m.m5422a(this.f4276p);
        } catch (C2066b e) {
            mo5379a(C2065a.m5037a(e));
        }
    }

    /* renamed from: a */
    public void m4613a(boolean z) {
        if (z || this.f4265a) {
            m4607a(this.f4270f);
            this.f4273m.m5421a();
            this.f4268d = null;
            this.f4265a = false;
        }
    }

    /* renamed from: b */
    public C2047d m4614b() {
        return this.f4275o == null ? null : this.f4275o.m4970a();
    }

    /* renamed from: b */
    public void m4615b(String str) {
        mo5421a(str);
    }

    @Nullable
    /* renamed from: c */
    C2065a mo5429c() {
        EnumSet enumSet = this.f4272h.f4250d;
        return (enumSet == null || enumSet.contains(CacheFlag.NONE) || m4617d()) ? null : new C2065a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }

    /* renamed from: d */
    boolean m4617d() {
        boolean z = VERSION.SDK_INT < 24 || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted() || NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted("127.0.0.1");
        if (!z) {
            C2625a.m6741b(this.f4266b, "cache", C2626b.f6532W, new Exception("Cleartext http is not allowed."));
        }
        return z;
    }

    /* renamed from: e */
    public void m4618e() {
        if (this.f4270f == null) {
            C2625a.m6741b(this.f4266b, "api", C2626b.f6540e, new C2066b(AdErrorType.NO_ADAPTER_ON_START, "Adapter is null on startAd"));
            this.f4267c.mo5326a(C2065a.m5036a(AdErrorType.INTERNAL_ERROR, AdErrorType.INTERNAL_ERROR.getDefaultErrorMessage()));
        } else if (this.f4265a) {
            C2625a.m6741b(this.f4266b, "api", C2626b.f6538c, new C2066b(AdErrorType.AD_ALREADY_STARTED, "ad already started"));
            this.f4267c.mo5326a(C2065a.m5036a(AdErrorType.AD_ALREADY_STARTED, AdErrorType.AD_ALREADY_STARTED.getDefaultErrorMessage()));
        } else {
            if (!TextUtils.isEmpty(this.f4270f.getClientToken())) {
                this.f4271g.mo5472b(this.f4270f.getClientToken());
            }
            this.f4265a = true;
            mo5419a();
        }
    }

    /* renamed from: f */
    public void m4619f() {
        m4613a(false);
    }

    /* renamed from: g */
    public boolean m4620g() {
        return this.f4275o == null || this.f4275o.m4977g();
    }

    /* renamed from: h */
    public long m4621h() {
        return this.f4275o != null ? this.f4275o.m4978h() : -1;
    }

    /* renamed from: i */
    synchronized void m4622i() {
        f4262j.post(new C19492(this));
    }

    /* renamed from: j */
    Handler m4623j() {
        return f4262j;
    }
}
