package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2551u.C2511a;
import com.facebook.ads.internal.view.component.p058a.C2259c;
import com.facebook.ads.internal.view.component.p058a.C2276d;
import com.facebook.ads.internal.view.component.p058a.C2279e;
import com.facebook.ads.internal.view.component.p058a.C2279e.C2278a;
import com.facebook.ads.internal.view.component.p058a.C2281g;
import com.facebook.ads.internal.view.component.p058a.C2286l;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.l */
public class C2513l extends C2323o {
    /* renamed from: d */
    private final C1879k f6126d;
    /* renamed from: e */
    private final C2630a f6127e;
    /* renamed from: f */
    private final C2598w f6128f = new C2598w();
    /* renamed from: g */
    private final C1858a f6129g;
    @Nullable
    /* renamed from: h */
    private C2286l f6130h;
    /* renamed from: i */
    private boolean f6131i = false;

    /* renamed from: com.facebook.ads.internal.view.l$1 */
    class C25081 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2513l f6122a;

        C25081(C2513l c2513l) {
            this.f6122a = c2513l;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (!this.f6122a.f6128f.m6673b()) {
                this.f6122a.f6128f.m6670a();
                Map hashMap = new HashMap();
                this.f6122a.f6127e.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(this.f6122a.f6128f.m6676e()));
                this.f6122a.m5990a(hashMap);
                this.f6122a.a.mo5470a(this.f6122a.f6126d.m4307c(), hashMap);
                if (this.f6122a.getAudienceNetworkListener() != null) {
                    this.f6122a.getAudienceNetworkListener().mo5335a("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.l$2 */
    class C25092 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2513l f6123a;

        C25092(C2513l c2513l) {
            this.f6123a = c2513l;
        }

        public boolean interceptBackButton() {
            return this.f6123a.f6130h != null && this.f6123a.f6130h.mo5567c();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.l$3 */
    class C25103 implements C1802e {
        /* renamed from: a */
        final /* synthetic */ C2513l f6124a;

        C25103(C2513l c2513l) {
            this.f6124a = c2513l;
        }

        /* renamed from: a */
        public void mo5349a(boolean z) {
            if (z) {
                this.f6124a.f6127e.m6769a();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.l$4 */
    class C25124 implements C2511a {
        /* renamed from: a */
        final /* synthetic */ C2513l f6125a;

        C25124(C2513l c2513l) {
            this.f6125a = c2513l;
        }

        /* renamed from: a */
        public void mo5636a() {
            this.f6125a.f6130h.mo5566b();
        }

        /* renamed from: b */
        public void mo5637b() {
            this.f6125a.f6130h.mo5564a();
        }
    }

    public C2513l(Context context, C1879k c1879k, C2085c c2085c, C1789a c1789a) {
        super(context, c2085c, c1789a);
        this.f6126d = c1879k;
        this.f6129g = new C25081(this);
        this.f6127e = new C2630a(this, 100, this.f6129g);
        this.f6127e.m6770a(c1879k.m4310f());
    }

    private void setUpContent(int i) {
        C1880l c1880l = (C1880l) this.f6126d.m4308d().get(0);
        ImageView imageView = new ImageView(getContext());
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setAdjustViewBounds(true);
        C2252d a = new C2252d(imageView).m5772a(c1880l.m4317c().m4247i(), c1880l.m4317c().m4246h());
        a.m5773a(new C25103(this));
        a.m5775a(c1880l.m4317c().m4245g());
        C2279e a2 = new C2278a(getContext(), this.a, getAudienceNetworkListener(), this.f6126d, imageView, this.f6127e, this.f6128f).m5845a(C2504i.f6089a).m5849b(i).m5848a();
        C2259c a3 = C2276d.m5833a(a2);
        this.f6130h = C2281g.m5865a(a2, C2600x.f6419a.heightPixels - a3.getExactMediaHeightIfAvailable(), C2600x.f6419a.widthPixels - a3.getExactMediaWidthIfAvailable(), this.f6131i);
        C2511a c2511a = null;
        if (this.f6130h != null) {
            c2511a = new C25124(this);
        }
        m5989a(a3, this.f6130h, c2511a, a3.getExactMediaHeightIfAvailable(), C2600x.f6419a.widthPixels - a3.getExactMediaWidthIfAvailable(), a3.mo5555a(), i);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.m5988a(audienceNetworkActivity, this.f6126d);
        audienceNetworkActivity.addBackButtonInterceptor(new C25092(this));
        setUpContent(audienceNetworkActivity.getResources().getConfiguration().orientation);
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    public void a_(boolean z) {
        if (this.f6130h != null) {
            this.f6130h.mo5569e();
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        if (this.f6130h != null) {
            this.f6130h.mo5570f();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        removeAllViews();
        if (this.f6130h != null) {
            C2600x.m6689b(this.f6130h);
            this.f6131i = this.f6130h.mo5568d();
        }
        setUpContent(configuration.orientation);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        if (!(this.f6126d == null || TextUtils.isEmpty(this.f6126d.m4307c()))) {
            Map hashMap = new HashMap();
            this.f6127e.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f6128f.m6676e()));
            this.a.mo5483l(this.f6126d.m4307c(), hashMap);
        }
        this.f6127e.m6774c();
        if (this.f6130h != null) {
            this.f6130h.mo5571g();
        }
        super.onDestroy();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f6128f.m6671a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
