package com.facebook.ads.internal.view.component.p058a;

import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.component.C2303j;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.component.a.c */
public abstract class C2259c extends RelativeLayout {
    /* renamed from: a */
    static final int f5322a = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: b */
    static final int f5323b = ((int) (28.0f * C2600x.f6420b));
    /* renamed from: c */
    private final C2303j f5324c;
    /* renamed from: d */
    private final C2295a f5325d;
    /* renamed from: e */
    private final C2085c f5326e;

    protected C2259c(C2279e c2279e, C1876h c1876h, boolean z) {
        super(c2279e.m5850a());
        this.f5326e = c2279e.m5851b();
        this.f5325d = new C2295a(c2279e.m5850a(), mo5558d(), mo5556e(), "com.facebook.ads.interstitial.clicked", c1876h, c2279e.m5851b(), c2279e.m5852c(), c2279e.m5854e(), c2279e.m5855f());
        C2600x.m6679a(this.f5325d);
        this.f5324c = new C2303j(getContext(), c1876h, z, mo5559b(), mo5561c());
        C2600x.m6679a(this.f5324c);
    }

    /* renamed from: a */
    public void mo5560a(C1880l c1880l, String str, double d) {
        C2303j c2303j = this.f5324c;
        String b = c1880l.m4315a().m4266b();
        String c = c1880l.m4315a().m4267c();
        boolean z = !mo5555a() && d > 0.0d && d < 1.0d;
        c2303j.m5924a(b, c, null, false, z);
        this.f5325d.m5917a(c1880l.m4316b(), str, new HashMap());
    }

    /* renamed from: a */
    public abstract boolean mo5555a();

    /* renamed from: b */
    protected boolean mo5559b() {
        return true;
    }

    /* renamed from: c */
    protected boolean mo5561c() {
        return true;
    }

    /* renamed from: d */
    protected boolean mo5558d() {
        return true;
    }

    /* renamed from: e */
    protected boolean mo5556e() {
        return true;
    }

    public C2085c getAdEventManager() {
        return this.f5326e;
    }

    protected C2295a getCtaButton() {
        return this.f5325d;
    }

    public int getExactMediaHeightIfAvailable() {
        return 0;
    }

    public int getExactMediaWidthIfAvailable() {
        return 0;
    }

    protected C2303j getTitleDescContainer() {
        return this.f5324c;
    }
}
