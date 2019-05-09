package com.facebook.ads.internal.view.p064g;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p021o.C2060c;
import com.facebook.ads.internal.p025w.p026b.C2587p;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import com.facebook.ads.internal.p025w.p057e.C2613e;
import com.facebook.ads.internal.p025w.p057e.C2613e.C2358a;
import com.facebook.ads.internal.p025w.p057e.C2614f;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.component.C2303j;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p019c.C2248a.C1898b;
import com.facebook.ads.internal.view.p019c.C2248a.C1899c;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import com.facebook.ads.internal.view.p063f.C2351b;
import com.facebook.ads.internal.view.p063f.C2351b.C2349c;
import com.tapjoy.TJAdUnitConstants.String;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.facebook.ads.internal.view.g.c */
public class C2363c {
    /* renamed from: a */
    private static final String f5707a = C2363c.class.getSimpleName();
    /* renamed from: b */
    private static final int f5708b = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5709c = ((int) (72.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5710d = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: e */
    private C2295a f5711e;
    /* renamed from: f */
    private final Context f5712f;
    /* renamed from: g */
    private final C2085c f5713g;
    /* renamed from: h */
    private final C1887q f5714h;
    /* renamed from: i */
    private final String f5715i;
    /* renamed from: j */
    private final C1876h f5716j;
    /* renamed from: k */
    private final C2630a f5717k;
    /* renamed from: l */
    private final C2598w f5718l;
    /* renamed from: m */
    private Executor f5719m = C2587p.f6372a;
    @Nullable
    /* renamed from: n */
    private C1789a f5720n;
    @Nullable
    /* renamed from: o */
    private C2248a f5721o;
    @Nullable
    /* renamed from: p */
    private C1898b f5722p;

    /* renamed from: com.facebook.ads.internal.view.g.c$1 */
    class C23571 extends C1899c {
        /* renamed from: a */
        final /* synthetic */ C2363c f5698a;

        /* renamed from: com.facebook.ads.internal.view.g.c$1$1 */
        class C23561 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C23571 f5697a;

            C23561(C23571 c23571) {
                this.f5697a = c23571;
            }

            public void run() {
                if (this.f5697a.f5698a.f5721o == null || this.f5697a.f5698a.f5721o.m5725c()) {
                    Log.w(C2363c.f5707a, "Webview already destroyed, cannot activate");
                } else {
                    this.f5697a.f5698a.f5721o.loadUrl("javascript:" + this.f5697a.f5698a.f5714h.m4367k().m4300c());
                }
            }
        }

        C23571(C2363c c2363c) {
            this.f5698a = c2363c;
        }

        /* renamed from: a */
        public void mo5387a() {
            if (this.f5698a.f5721o != null && !TextUtils.isEmpty(this.f5698a.f5714h.m4367k().m4300c())) {
                this.f5698a.f5721o.post(new C23561(this));
            }
        }

        /* renamed from: a */
        public void mo5390a(String str, Map<String, String> map) {
            Uri parse = Uri.parse(str);
            if ("fbad".equals(parse.getScheme()) && parse.getAuthority().equals(String.CLOSE)) {
                C2363c.m6097a(this.f5698a);
                return;
            }
            if ("fbad".equals(parse.getScheme()) && C1843c.m4144a(parse.getAuthority()) && this.f5698a.f5720n != null) {
                this.f5698a.f5720n.mo5335a(aa.REWARDED_VIDEO_AD_CLICK.m6205a());
            }
            C1842b a = C1843c.m4142a(this.f5698a.f5712f, this.f5698a.f5713g, this.f5698a.f5714h.mo5384a(), parse, map);
            if (a != null) {
                try {
                    a.mo5376a();
                } catch (Throwable e) {
                    Log.e(C2363c.f5707a, "Error executing action", e);
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.g.c$2 */
    class C23592 implements C2358a {
        /* renamed from: a */
        final /* synthetic */ C2363c f5699a;

        C23592(C2363c c2363c) {
            this.f5699a = c2363c;
        }

        /* renamed from: a */
        public void mo5589a() {
            if (this.f5699a.f5720n != null) {
                this.f5699a.f5720n.mo5335a(aa.REWARD_SERVER_FAILED.m6205a());
            }
        }

        /* renamed from: a */
        public void mo5590a(C2614f c2614f) {
            if (this.f5699a.f5720n != null) {
                if (c2614f == null || !c2614f.m6720a()) {
                    this.f5699a.f5720n.mo5335a(aa.REWARD_SERVER_FAILED.m6205a());
                } else {
                    this.f5699a.f5720n.mo5335a(aa.REWARD_SERVER_SUCCESS.m6205a());
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.g.c$a */
    public enum C2361a {
        SCREENSHOTS,
        MARKUP,
        PLAYABLE,
        INFO
    }

    /* renamed from: com.facebook.ads.internal.view.g.c$b */
    private static class C2362b implements C2349c {
        /* renamed from: a */
        final WeakReference<C2363c> f5706a;

        private C2362b(C2363c c2363c) {
            this.f5706a = new WeakReference(c2363c);
        }

        /* renamed from: a */
        public void mo5591a() {
        }

        /* renamed from: a */
        public void mo5592a(C2630a c2630a, C2598w c2598w) {
        }

        /* renamed from: a */
        public void mo5593a(boolean z) {
            if (this.f5706a.get() != null) {
                ((C2363c) this.f5706a.get()).m6104g().performClick();
            }
        }

        /* renamed from: b */
        public void mo5594b() {
        }

        /* renamed from: c */
        public void mo5595c() {
            if (this.f5706a.get() != null) {
                C2363c.m6097a((C2363c) this.f5706a.get());
            }
        }

        /* renamed from: d */
        public void mo5596d() {
            mo5595c();
        }
    }

    public C2363c(Context context, C2085c c2085c, C1887q c1887q, C1789a c1789a, C2630a c2630a, C2598w c2598w) {
        this.f5712f = context;
        this.f5713g = c2085c;
        this.f5714h = c1887q;
        this.f5720n = c1789a;
        this.f5715i = C2060c.m5025a(this.f5714h.m4367k().m4299b());
        this.f5716j = this.f5714h.m4365i().m4212a();
        this.f5717k = c2630a;
        this.f5718l = c2598w;
    }

    /* renamed from: a */
    static /* synthetic */ void m6097a(C2363c c2363c) {
        if (c2363c.f5720n != null) {
            c2363c.f5720n.mo5335a(aa.REWARDED_VIDEO_END_ACTIVITY.m6205a());
        }
    }

    /* renamed from: g */
    private C2295a m6104g() {
        if (this.f5711e != null) {
            return this.f5711e;
        }
        this.f5711e = new C2295a(this.f5712f, true, false, aa.REWARDED_VIDEO_AD_CLICK.m6205a(), this.f5716j, this.f5713g, this.f5720n, this.f5717k, this.f5718l);
        this.f5711e.m5917a(this.f5714h.m4364h(), this.f5714h.mo5384a(), new HashMap());
        return this.f5711e;
    }

    /* renamed from: a */
    public boolean m6106a() {
        return m6107b() == C2361a.MARKUP;
    }

    /* renamed from: b */
    public C2361a m6107b() {
        C1884n j = this.f5714h.m4366j().m4248j();
        return (j == null || !j.m4342i()) ? !this.f5714h.m4367k().m4301d().isEmpty() ? C2361a.SCREENSHOTS : !TextUtils.isEmpty(this.f5715i) ? C2361a.MARKUP : C2361a.INFO : C2361a.PLAYABLE;
    }

    /* renamed from: c */
    public Pair<C2361a, View> m6108c() {
        C2361a b = m6107b();
        switch (b) {
            case MARKUP:
                this.f5722p = new C23571(this);
                this.f5721o = new C2248a(this.f5712f, new WeakReference(this.f5722p), 1);
                this.f5721o.loadDataWithBaseURL(C2610b.m6707a(), this.f5715i, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                return new Pair(b, this.f5721o);
            case SCREENSHOTS:
                RecyclerView recyclerView = new RecyclerView(this.f5712f);
                recyclerView.setLayoutManager(new LinearLayoutManager(this.f5712f, 0, false));
                recyclerView.setAdapter(new C2364d(this.f5714h.m4367k().m4301d(), f5708b));
                return new Pair(b, recyclerView);
            case PLAYABLE:
                return new Pair(b, new C2351b(this.f5712f, C1885o.m4346a(this.f5714h), this.f5713g, this.f5720n, new C2362b(), false, false));
            default:
                View c2303j = new C2303j(this.f5712f, this.f5716j, true, false, false);
                c2303j.m5924a(this.f5714h.m4363g().m4265a(), this.f5714h.m4363g().m4267c(), null, false, true);
                c2303j.setAlignment(17);
                View g = m6104g();
                ImageView c2299f = new C2299f(this.f5712f);
                C2600x.m6680a((View) c2299f, 0);
                c2299f.setRadius(50);
                new C2252d(c2299f).m5771a().m5775a(this.f5714h.m4362f().m4329b());
                LinearLayout linearLayout = new LinearLayout(this.f5712f);
                linearLayout.setOrientation(1);
                linearLayout.setGravity(17);
                linearLayout.addView(c2299f, new LayoutParams(f5709c, f5709c));
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.setMargins(0, f5710d, 0, f5710d);
                linearLayout.addView(c2303j, layoutParams);
                linearLayout.addView(g, layoutParams);
                return new Pair(b, linearLayout);
        }
    }

    /* renamed from: d */
    public void m6109d() {
        if (!TextUtils.isEmpty(this.f5714h.m4367k().m4297a())) {
            C2613e c2613e = new C2613e(this.f5712f, new HashMap());
            c2613e.m6718a(new C23592(this));
            c2613e.executeOnExecutor(this.f5719m, new String[]{r0});
        }
    }

    /* renamed from: e */
    public void m6110e() {
        if (this.f5721o != null) {
            this.f5721o.destroy();
            this.f5721o = null;
            this.f5722p = null;
        }
    }
}
