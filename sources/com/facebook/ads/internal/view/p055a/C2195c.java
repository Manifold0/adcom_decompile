package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.facebook.ads.internal.p025w.p057e.C2615g;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p037f.C1995b;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.C1921a.C1789a;

/* renamed from: com.facebook.ads.internal.view.a.c */
public abstract class C2195c extends FrameLayout {
    /* renamed from: a */
    boolean f5078a;
    /* renamed from: b */
    protected final C2193e f5079b;
    /* renamed from: c */
    private final C2085c f5080c;
    /* renamed from: d */
    private final String f5081d;
    @Nullable
    /* renamed from: e */
    private final C1921a f5082e;
    @Nullable
    /* renamed from: f */
    private final C1789a f5083f;
    @Nullable
    /* renamed from: g */
    private C2192b f5084g;
    /* renamed from: h */
    private int f5085h;
    /* renamed from: i */
    private C1995b f5086i;
    /* renamed from: j */
    private C1994a f5087j;
    /* renamed from: k */
    private C1996c f5088k;

    /* renamed from: com.facebook.ads.internal.view.a.c$1 */
    class C21941 implements C2193e {
        /* renamed from: a */
        final /* synthetic */ C2195c f5077a;

        C21941(C2195c c2195c) {
            this.f5077a = c2195c;
        }

        /* renamed from: a */
        public void mo5535a() {
            if (this.f5077a.f5088k == null) {
                mo5538a(false);
                return;
            }
            this.f5077a.f5085h = this.f5077a.f5085h - 1;
            if (this.f5077a.f5088k.m4818e() == null) {
                this.f5077a.m5658g();
            } else {
                C2195c.m5649a(this.f5077a, this.f5077a.f5088k.m4818e());
            }
        }

        /* renamed from: a */
        public void mo5536a(C1994a c1994a) {
            this.f5077a.f5085h = this.f5077a.f5085h + 1;
            this.f5077a.f5087j = c1994a;
            C2195c.m5649a(this.f5077a, this.f5077a.f5087j == C1994a.HIDE ? C1993a.m4791d(this.f5077a.getContext()) : C1993a.m4794g(this.f5077a.getContext()));
        }

        /* renamed from: a */
        public void mo5537a(C1996c c1996c) {
            this.f5077a.f5085h = this.f5077a.f5085h + 1;
            this.f5077a.f5086i.m4805a(c1996c.m4813a());
            if (c1996c.m4817d().isEmpty()) {
                C2195c.m5651b(this.f5077a, c1996c);
                if (this.f5077a.f5084g != null) {
                    this.f5077a.f5084g.mo5584a(c1996c, this.f5077a.f5087j);
                    return;
                }
                return;
            }
            C2195c.m5649a(this.f5077a, c1996c);
        }

        /* renamed from: a */
        public void mo5538a(boolean z) {
            this.f5077a.mo5544c();
            if (this.f5077a.f5082e != null) {
                this.f5077a.f5082e.mo5406b(true);
            }
            if (this.f5077a.f5084g != null) {
                this.f5077a.f5084g.mo5585a(z);
            }
            if (!z) {
                this.f5077a.m5656f();
            }
        }

        /* renamed from: b */
        public void mo5539b() {
            if (this.f5077a.f5083f != null) {
                this.f5077a.f5083f.mo5335a("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW");
            }
        }

        /* renamed from: c */
        public void mo5540c() {
            if (!TextUtils.isEmpty(C1993a.m4801n(this.f5077a.getContext()))) {
                C2615g.m6721a(new C2615g(), this.f5077a.getContext(), Uri.parse(C1993a.m4801n(this.f5077a.getContext())), this.f5077a.f5081d);
            }
            this.f5077a.f5086i.m4809c();
        }

        /* renamed from: d */
        public void mo5541d() {
            this.f5077a.mo5544c();
            if (this.f5077a.f5082e != null) {
                this.f5077a.f5082e.mo5406b(true);
            }
            if (!TextUtils.isEmpty(C1993a.m4800m(this.f5077a.getContext()))) {
                C2615g.m6721a(new C2615g(), this.f5077a.getContext(), Uri.parse(C1993a.m4800m(this.f5077a.getContext())), this.f5077a.f5081d);
            }
            this.f5077a.f5086i.m4808b();
            this.f5077a.m5656f();
        }
    }

    public C2195c(Context context, C2085c c2085c, String str) {
        this(context, c2085c, str, null, null);
    }

    public C2195c(Context context, C2085c c2085c, String str, @Nullable C1921a c1921a, @Nullable C1789a c1789a) {
        super(context);
        this.f5085h = 0;
        this.f5087j = C1994a.NONE;
        this.f5088k = null;
        this.f5079b = new C21941(this);
        this.f5080c = c2085c;
        this.f5082e = c1921a;
        this.f5083f = c1789a;
        this.f5081d = str;
    }

    /* renamed from: a */
    static /* synthetic */ void m5649a(C2195c c2195c, C1996c c1996c) {
        c2195c.f5088k = c1996c;
        c2195c.f5086i.m4807a(c2195c.f5087j, c2195c.f5085h);
        c2195c.mo5542a(c1996c, c2195c.f5087j);
    }

    /* renamed from: b */
    static /* synthetic */ void m5651b(C2195c c2195c, C1996c c1996c) {
        c2195c.f5086i.m4806a(c2195c.f5087j);
        c2195c.mo5543b(c1996c, c2195c.f5087j);
        if (c2195c.mo5546e()) {
            c2195c.m5656f();
        }
    }

    /* renamed from: f */
    private void m5656f() {
        if (this.f5086i.m4811e()) {
            this.f5080c.mo5485n(this.f5081d, this.f5086i.m4810d());
            this.f5086i.m4812f();
        }
    }

    /* renamed from: g */
    private void m5658g() {
        this.f5088k = null;
        this.f5086i.m4804a();
        mo5545d();
    }

    /* renamed from: a */
    public void m5663a() {
        this.f5086i = new C1995b();
        if (this.f5082e != null) {
            this.f5082e.a_(true);
        }
        m5658g();
        if (this.f5084g != null) {
            this.f5084g.mo5583a();
        }
    }

    /* renamed from: a */
    abstract void mo5542a(C1996c c1996c, C1994a c1994a);

    /* renamed from: a */
    public void m5665a(boolean z) {
        this.f5078a = z;
    }

    /* renamed from: b */
    public void m5666b() {
        m5656f();
    }

    /* renamed from: b */
    abstract void mo5543b(C1996c c1996c, C1994a c1994a);

    /* renamed from: c */
    abstract void mo5544c();

    /* renamed from: d */
    abstract void mo5545d();

    /* renamed from: e */
    abstract boolean mo5546e();

    public void setAdReportingFlowListener(@Nullable C2192b c2192b) {
        this.f5084g = c2192b;
    }
}
