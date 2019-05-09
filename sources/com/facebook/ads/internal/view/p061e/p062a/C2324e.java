package com.facebook.ads.internal.view.p061e.p062a;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2310d;
import com.facebook.ads.internal.view.C2323o;
import com.facebook.ads.internal.view.component.C2298e;
import com.facebook.ads.internal.view.p061e.p062a.C2318c.C2317a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.e.a.e */
public class C2324e extends C2323o {
    /* renamed from: d */
    private static final int f5548d = ((int) (48.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5549e = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: f */
    private static final int f5550f = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: g */
    private static final int f5551g = ((int) (56.0f * C2600x.f6420b));
    /* renamed from: h */
    private static final int f5552h = ((int) (12.0f * C2600x.f6420b));
    /* renamed from: i */
    private final C2598w f5553i = new C2598w();
    @Nullable
    /* renamed from: j */
    private C2011b f5554j;
    @Nullable
    /* renamed from: k */
    private LinearLayout f5555k;
    /* renamed from: l */
    private String f5556l;
    /* renamed from: m */
    private List<C2316b> f5557m;
    /* renamed from: n */
    private C2315a f5558n;
    @Nullable
    /* renamed from: o */
    private C2298e f5559o;
    @Nullable
    /* renamed from: p */
    private C2310d f5560p;
    /* renamed from: q */
    private C2630a f5561q;
    /* renamed from: r */
    private C1858a f5562r;
    /* renamed from: s */
    private int f5563s;
    /* renamed from: t */
    private int f5564t;

    /* renamed from: com.facebook.ads.internal.view.e.a.e$1 */
    class C23211 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2324e f5540a;

        C23211(C2324e c2324e) {
            this.f5540a = c2324e;
        }

        /* renamed from: a */
        public void mo5381a() {
            Map hashMap = new HashMap();
            if (!this.f5540a.f5553i.m6673b()) {
                this.f5540a.f5553i.m6670a();
                if (this.f5540a.getAudienceNetworkListener() != null) {
                    this.f5540a.getAudienceNetworkListener().mo5335a("com.facebook.ads.interstitial.impression.logged");
                }
                if (!TextUtils.isEmpty(this.f5540a.f5556l)) {
                    this.f5540a.f5561q.m6771a(hashMap);
                    hashMap.put("touch", C2581k.m6645a(this.f5540a.f5553i.m6676e()));
                    this.f5540a.m5990a(hashMap);
                    this.f5540a.a.mo5470a(this.f5540a.f5556l, hashMap);
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.e.a.e$2 */
    class C23222 implements C2317a {
        /* renamed from: a */
        final /* synthetic */ C2324e f5541a;

        C23222(C2324e c2324e) {
            this.f5541a = c2324e;
        }

        /* renamed from: a */
        public void mo5579a(int i) {
            if (this.f5541a.f5559o != null) {
                this.f5541a.f5559o.m5921a(i);
            }
        }
    }

    public C2324e(Context context, C2085c c2085c, @Nullable C2011b c2011b, C1789a c1789a) {
        super(context, c2085c, c1789a);
        this.f5554j = c2011b;
    }

    /* renamed from: a */
    public void mo5580a() {
        if (this.f5555k != null) {
            this.f5555k.removeAllViews();
            this.f5555k = null;
        }
        if (this.f5560p != null) {
            this.f5560p.removeAllViews();
            this.f5560p = null;
        }
        if (this.f5559o != null) {
            this.f5559o.removeAllViews();
            this.f5559o = null;
        }
    }

    /* renamed from: a */
    public void m6000a(int i, @Nullable Bundle bundle) {
        int min;
        int i2;
        int i3;
        this.f5555k = new LinearLayout(getContext());
        if (i == 1) {
            this.f5555k.setGravity(17);
        } else {
            this.f5555k.setGravity(48);
        }
        this.f5555k.setLayoutParams(new LayoutParams(-1, -1));
        this.f5555k.setOrientation(1);
        int i4 = C2600x.f6419a.widthPixels;
        int i5 = C2600x.f6419a.heightPixels;
        if (i == 1) {
            min = Math.min(i4 - (f5549e * 4), i5 / 2);
            i2 = (i4 - min) / 8;
            i3 = i2 * 4;
        } else {
            min = i5 - ((f5551g + f5548d) + (f5549e * 2));
            i2 = f5549e;
            i3 = i2 * 2;
        }
        this.f5562r = new C23211(this);
        this.f5561q = new C2630a(this, 1, this.f5562r);
        this.f5561q.m6770a(this.f5563s);
        this.f5561q.m6772b(this.f5564t);
        this.f5560p = new C2310d(getContext());
        this.f5560p.setLayoutParams(new LayoutParams(-1, -2));
        this.f5558n = new C2315a(this.f5560p, i, this.f5557m, this.f5561q, bundle);
        this.f5560p.setAdapter(new C2318c(this.f5557m, this.a, this.f5554j, this.f5561q, this.f5553i, getAudienceNetworkListener(), i == 1 ? this.c.m4212a() : this.c.m4213b(), this.f5556l, min, i2, i3, i, this.f5558n));
        if (i == 1) {
            C2315a c2315a = this.f5558n;
            new PagerSnapHelper().attachToRecyclerView(this.f5560p);
            c2315a.m5966a(new C23222(this));
            this.f5559o = new C2298e(getContext(), this.c.m4212a(), this.f5557m.size());
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, f5550f);
            layoutParams.setMargins(0, f5552h, 0, 0);
            this.f5559o.setLayoutParams(layoutParams);
        }
        this.f5555k.addView(this.f5560p);
        if (this.f5559o != null) {
            this.f5555k.addView(this.f5559o);
        }
        m5987a(this.f5555k, false, i);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        C1879k c1879k = (C1879k) intent.getSerializableExtra("ad_data_bundle");
        super.m5988a(audienceNetworkActivity, c1879k);
        this.f5556l = c1879k.m4307c();
        this.f5563s = c1879k.m4310f();
        this.f5564t = c1879k.m4311g();
        List d = c1879k.m4308d();
        this.f5557m = new ArrayList(d.size());
        for (int i = 0; i < d.size(); i++) {
            this.f5557m.add(new C2316b(i, d.size(), (C1880l) d.get(i)));
        }
        m6000a(audienceNetworkActivity.getResources().getConfiguration().orientation, bundle);
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
        if (this.f5558n != null) {
            this.f5558n.m5965a(bundle);
        }
    }

    public void a_(boolean z) {
        if (this.f5558n != null) {
            this.f5558n.m5964a();
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f5558n.m5967b();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Bundle bundle = new Bundle();
        mo5404a(bundle);
        mo5580a();
        m6000a(configuration.orientation, bundle);
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        if (!TextUtils.isEmpty(this.f5556l)) {
            Map hashMap = new HashMap();
            this.f5561q.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f5553i.m6676e()));
            this.a.mo5483l(this.f5556l, hashMap);
        }
        mo5580a();
        this.f5561q.m6774c();
        this.f5561q = null;
        this.f5562r = null;
        this.f5557m = null;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.f5553i.m6671a(motionEvent, this, this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
