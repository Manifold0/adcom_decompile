package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.adapters.p030b.C1865b;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2593t;
import com.facebook.ads.internal.p025w.p026b.C2593t.C2592a;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2504i.C2282b;
import com.facebook.ads.internal.view.C2504i.C2383a;
import com.facebook.ads.internal.view.C2551u.C2511a;
import com.facebook.ads.internal.view.component.p058a.C2259c;
import com.facebook.ads.internal.view.component.p058a.C2286l;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.o */
public abstract class C2323o extends RelativeLayout implements C1921a {
    /* renamed from: a */
    protected final C2085c f5542a;
    /* renamed from: b */
    protected final C2504i f5543b = new C2504i(getContext(), getAudienceNetworkListener(), C2383a.CROSS);
    /* renamed from: c */
    protected C1865b f5544c;
    /* renamed from: d */
    private final C1789a f5545d;
    /* renamed from: e */
    private final C2593t f5546e = new C2593t(this);
    /* renamed from: f */
    private String f5547f;

    protected C2323o(Context context, C2085c c2085c, C1789a c1789a) {
        super(context.getApplicationContext());
        this.f5542a = c2085c;
        this.f5545d = c1789a;
    }

    /* renamed from: a */
    private void mo5580a() {
        removeAllViews();
        C2600x.m6689b(this);
    }

    /* renamed from: a */
    protected void m5987a(View view, boolean z, int i) {
        this.f5546e.m6663a(C2592a.f6379a);
        mo5580a();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(0, z ? 0 : C2504i.f6089a, 0, 0);
        addView(view, layoutParams);
        C1876h a = i == 1 ? this.f5544c.m4212a() : this.f5544c.m4213b();
        layoutParams = new RelativeLayout.LayoutParams(-1, C2504i.f6089a);
        layoutParams.addRule(10);
        this.f5543b.m6453a(a, z);
        addView(this.f5543b, layoutParams);
        C2600x.m6680a((View) this, a.m4290d(z));
        if (this.f5545d != null) {
            this.f5545d.mo5334a((View) this, 0);
            if (z && VERSION.SDK_INT >= 19) {
                this.f5546e.m6663a(C2592a.FULL_SCREEN);
            }
        }
    }

    /* renamed from: a */
    public void m5988a(final AudienceNetworkActivity audienceNetworkActivity, C1879k c1879k) {
        this.f5546e.m6662a(audienceNetworkActivity.getWindow());
        this.f5544c = c1879k.m4306b();
        this.f5547f = c1879k.m4313i();
        this.f5543b.m6455a(c1879k.m4303a(), c1879k.m4307c(), ((C1880l) c1879k.m4308d().get(0)).m4317c().m4241c());
        this.f5543b.setToolbarListener(new C2282b(this) {
            /* renamed from: b */
            final /* synthetic */ C2323o f6177b;

            /* renamed from: a */
            public void mo5562a() {
                audienceNetworkActivity.finish();
            }
        });
        if (C1993a.m4787a(getContext(), true)) {
            this.f5543b.m6454a(c1879k.m4303a(), c1879k.m4307c());
        }
    }

    /* renamed from: a */
    protected void m5989a(C2259c c2259c, @Nullable C2286l c2286l, @Nullable C2511a c2511a, int i, int i2, boolean z, int i3) {
        m5987a(c2259c, z, i3);
        if (c2286l != null) {
            this.f5543b.setPageDetailsVisibility(4);
            this.f5546e.m6663a(C2592a.f6379a);
            if (i3 == 1) {
                View c2551u = new C2551u(getContext(), c2286l, i - C2504i.f6089a, 0);
                addView(c2551u);
                if (c2511a != null) {
                    c2551u.setDragListener(c2511a);
                    return;
                }
                return;
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, -1);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(C2600x.f6419a.widthPixels - i2, C2504i.f6089a);
            layoutParams2.addRule(10);
            this.f5543b.setLayoutParams(layoutParams2);
            layoutParams.addRule(11);
            c2259c.addView(c2286l, layoutParams);
        }
    }

    /* renamed from: a */
    protected final void m5990a(Map<String, String> map) {
        if (this.f5547f != null) {
            map.put("extra_hints", this.f5547f);
        }
    }

    protected C2085c getAdEventManager() {
        return this.f5542a;
    }

    protected C1789a getAudienceNetworkListener() {
        return this.f5545d;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        this.f5543b.m6463d();
        super.onConfigurationChanged(configuration);
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2323o f6179b;

            @RequiresApi(api = 16)
            public void onGlobalLayout() {
                this.f6179b.f5543b.m6464e();
                if (VERSION.SDK_INT < 16) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                } else {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

    public void onDestroy() {
        this.f5546e.m6661a();
        this.f5543b.setToolbarListener(null);
        mo5580a();
    }

    public void setListener(C1789a c1789a) {
    }
}
