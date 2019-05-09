package com.facebook.ads.internal;

import android.content.Context;
import android.os.Handler;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1837q;
import com.facebook.ads.internal.adapters.C1897d;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.p025w.p026b.C1839y;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p025w.p026b.C2583m;
import com.facebook.ads.internal.p025w.p026b.C2588q;
import com.facebook.ads.internal.p025w.p073i.C2627a;
import com.facebook.ads.internal.p028u.C2122b;
import com.facebook.ads.internal.p028u.C2129c;
import com.facebook.ads.internal.p028u.C2129c.C1856b;
import com.facebook.ads.internal.p028u.C2133f;
import com.facebook.ads.internal.p043m.C2044a;
import com.facebook.ads.internal.p043m.C2046c;
import com.facebook.ads.internal.p045n.C2051a;
import com.facebook.ads.internal.p045n.C2057d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2087d;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.protocol.C2074g;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.a */
public class C1857a implements C1856b {
    /* renamed from: a */
    private final Context f3873a;
    /* renamed from: b */
    private final String f3874b;
    /* renamed from: c */
    private final C2129c f3875c = new C2129c(this.f3873a);
    /* renamed from: d */
    private final C1897d f3876d;
    /* renamed from: e */
    private final C2070e f3877e;
    /* renamed from: f */
    private final AdSize f3878f;
    /* renamed from: g */
    private final int f3879g;
    /* renamed from: h */
    private boolean f3880h;
    /* renamed from: i */
    private final Handler f3881i;
    /* renamed from: j */
    private final Runnable f3882j;
    /* renamed from: k */
    private final C2085c f3883k;
    /* renamed from: l */
    private C1834a f3884l;
    /* renamed from: m */
    private C2046c f3885m;
    /* renamed from: n */
    private String f3886n;

    /* renamed from: com.facebook.ads.internal.a$a */
    public interface C1834a {
        /* renamed from: a */
        void mo5370a(C2065a c2065a);

        /* renamed from: a */
        void mo5371a(List<C1924i> list);
    }

    /* renamed from: com.facebook.ads.internal.a$b */
    private static final class C1840b extends C1839y<C1857a> {
        public C1840b(C1857a c1857a) {
            super(c1857a);
        }

        public void run() {
            C1857a c1857a = (C1857a) m4138a();
            if (c1857a != null) {
                if (C2627a.m6743a(c1857a.f3873a)) {
                    c1857a.m4184a();
                } else {
                    c1857a.f3881i.postDelayed(c1857a.f3882j, 5000);
                }
            }
        }
    }

    static {
        C2567c.m6620a();
    }

    public C1857a(Context context, String str, C2070e c2070e, AdSize adSize, int i) {
        this.f3873a = context;
        this.f3874b = str;
        this.f3877e = c2070e;
        this.f3878f = adSize;
        this.f3879g = i;
        this.f3875c.m5424a((C1856b) this);
        this.f3876d = new C1897d();
        this.f3880h = true;
        this.f3881i = new Handler();
        this.f3882j = new C1840b(this);
        this.f3883k = C2087d.m5183a(this.f3873a);
        C2051a.m4997b(this.f3873a);
    }

    /* renamed from: d */
    private List<C1924i> m4183d() {
        C2046c c2046c = this.f3885m;
        C2044a e = c2046c.m4975e();
        final List<C1924i> arrayList = new ArrayList(c2046c.m4974d());
        for (C2044a c2044a = e; c2044a != null; c2044a = c2046c.m4975e()) {
            AdAdapter a = this.f3876d.m4382a(AdPlacementType.NATIVE);
            if (a != null && a.getPlacementType() == AdPlacementType.NATIVE) {
                Map hashMap = new HashMap();
                hashMap.put("data", c2044a.m4966c());
                hashMap.put("definition", c2046c.m4970a());
                final C1924i c1924i = (C1924i) a;
                c1924i.m4494a(this.f3873a, new C1837q(this) {
                    /* renamed from: c */
                    final /* synthetic */ C1857a f3831c;

                    /* renamed from: a */
                    public void mo5372a(C1924i c1924i) {
                        arrayList.add(c1924i);
                    }

                    /* renamed from: a */
                    public void mo5373a(C1924i c1924i, C2065a c2065a) {
                    }

                    /* renamed from: b */
                    public void mo5374b(C1924i c1924i) {
                    }

                    /* renamed from: c */
                    public void mo5375c(C1924i c1924i) {
                    }
                }, this.f3883k, hashMap, NativeAdBase.getViewTraversalPredicate());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m4184a() {
        try {
            this.f3875c.m5422a(new C2122b(this.f3873a, new C2057d(this.f3873a, false), this.f3874b, this.f3878f != null ? new C2583m(this.f3878f.getHeight(), this.f3878f.getWidth()) : null, this.f3877e, null, this.f3879g, AdSettings.isTestMode(this.f3873a), AdSettings.isChildDirected(), new C2074g(this.f3873a, null, null, null), C2588q.m6654a(C2078a.m5068G(this.f3873a)), this.f3886n));
        } catch (C2066b e) {
            mo5379a(C2065a.m5037a(e));
        }
    }

    /* renamed from: a */
    public void m4185a(C1834a c1834a) {
        this.f3884l = c1834a;
    }

    /* renamed from: a */
    public void mo5379a(C2065a c2065a) {
        if (this.f3880h) {
            this.f3881i.postDelayed(this.f3882j, TapjoyConstants.SESSION_ID_INACTIVITY_TIME);
        }
        if (this.f3884l != null) {
            this.f3884l.mo5370a(c2065a);
        }
    }

    /* renamed from: a */
    public void mo5380a(C2133f c2133f) {
        C2046c a = c2133f.mo5506a();
        if (a == null) {
            throw new IllegalStateException("no placement in response");
        }
        if (this.f3880h) {
            long c = a.m4970a().m4982c();
            if (c == 0) {
                c = TapjoyConstants.SESSION_ID_INACTIVITY_TIME;
            }
            this.f3881i.postDelayed(this.f3882j, c);
        }
        this.f3885m = a;
        List d = m4183d();
        if (this.f3884l == null) {
            return;
        }
        if (d.isEmpty()) {
            this.f3884l.mo5370a(C2065a.m5036a(AdErrorType.NO_FILL, ""));
        } else {
            this.f3884l.mo5371a(d);
        }
    }

    /* renamed from: a */
    public void m4188a(String str) {
        this.f3886n = str;
    }

    /* renamed from: b */
    public void m4189b() {
    }

    /* renamed from: c */
    public void m4190c() {
        this.f3880h = false;
        this.f3881i.removeCallbacks(this.f3882j);
    }
}
