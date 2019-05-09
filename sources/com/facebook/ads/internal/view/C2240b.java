package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p057e.C2610b;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p056b.C2229a;
import com.facebook.ads.internal.view.p056b.C2229a.C2222a;
import com.facebook.ads.internal.view.p056b.C2230b;
import com.facebook.ads.internal.view.p056b.C2233c.C2232a;
import com.facebook.ads.internal.view.p056b.C2239f;
import com.facebook.ads.internal.view.p056b.C2239f.C2224a;

@TargetApi(19)
/* renamed from: com.facebook.ads.internal.view.b */
public class C2240b implements C1921a {
    /* renamed from: a */
    private static final String f5210a = C2240b.class.getSimpleName();
    /* renamed from: b */
    private final AudienceNetworkActivity f5211b;
    /* renamed from: c */
    private final C2229a f5212c;
    /* renamed from: d */
    private final C2239f f5213d;
    /* renamed from: e */
    private final C2230b f5214e;
    /* renamed from: f */
    private final C2085c f5215f;
    /* renamed from: g */
    private final BackButtonInterceptor f5216g = new C22211(this);
    /* renamed from: h */
    private String f5217h;
    /* renamed from: i */
    private String f5218i;
    /* renamed from: j */
    private long f5219j;
    /* renamed from: k */
    private boolean f5220k = true;
    /* renamed from: l */
    private long f5221l = -1;
    /* renamed from: m */
    private boolean f5222m = true;

    /* renamed from: com.facebook.ads.internal.view.b$1 */
    class C22211 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2240b f5156a;

        C22211(C2240b c2240b) {
            this.f5156a = c2240b;
        }

        public boolean interceptBackButton() {
            if (!this.f5156a.f5213d.canGoBack()) {
                return false;
            }
            this.f5156a.f5213d.goBack();
            return true;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.b$3 */
    class C22253 implements C2224a {
        /* renamed from: a */
        final /* synthetic */ C2240b f5159a;

        C22253(C2240b c2240b) {
            this.f5159a = c2240b;
        }

        /* renamed from: a */
        public void mo5548a(int i) {
            if (this.f5159a.f5220k) {
                this.f5159a.f5214e.setProgress(i);
            }
        }

        /* renamed from: a */
        public void mo5549a(String str) {
            this.f5159a.f5220k = true;
            this.f5159a.f5212c.setUrl(str);
        }

        /* renamed from: b */
        public void mo5550b(String str) {
            this.f5159a.f5212c.setTitle(str);
        }

        /* renamed from: c */
        public void mo5551c(String str) {
            this.f5159a.f5214e.setProgress(100);
            this.f5159a.f5220k = false;
        }
    }

    public C2240b(final AudienceNetworkActivity audienceNetworkActivity, C2085c c2085c, C1789a c1789a) {
        this.f5211b = audienceNetworkActivity;
        this.f5215f = c2085c;
        int i = (int) (2.0f * C2600x.f6420b);
        this.f5212c = new C2229a(audienceNetworkActivity);
        this.f5212c.setId(View.generateViewId());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.f5212c.setLayoutParams(layoutParams);
        this.f5212c.setListener(new C2222a(this) {
            /* renamed from: b */
            final /* synthetic */ C2240b f5158b;

            /* renamed from: a */
            public void mo5547a() {
                audienceNetworkActivity.finish();
            }
        });
        c1789a.mo5333a(this.f5212c);
        this.f5213d = new C2239f(audienceNetworkActivity);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.f5212c.getId());
        layoutParams.addRule(12);
        this.f5213d.setLayoutParams(layoutParams);
        this.f5213d.setListener(new C22253(this));
        c1789a.mo5333a(this.f5213d);
        this.f5214e = new C2230b(audienceNetworkActivity, null, 16842872);
        layoutParams = new RelativeLayout.LayoutParams(-1, i);
        layoutParams.addRule(3, this.f5212c.getId());
        this.f5214e.setLayoutParams(layoutParams);
        this.f5214e.setProgress(0);
        c1789a.mo5333a(this.f5214e);
        audienceNetworkActivity.addBackButtonInterceptor(this.f5216g);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f5221l < 0) {
            this.f5221l = System.currentTimeMillis();
        }
        if (bundle == null) {
            this.f5217h = intent.getStringExtra(AudienceNetworkActivity.BROWSER_URL);
            this.f5218i = intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN);
            this.f5219j = intent.getLongExtra(AudienceNetworkActivity.HANDLER_TIME, -1);
        } else {
            this.f5217h = bundle.getString(AudienceNetworkActivity.BROWSER_URL);
            this.f5218i = bundle.getString(AudienceNetworkActivity.CLIENT_TOKEN);
            this.f5219j = bundle.getLong(AudienceNetworkActivity.HANDLER_TIME, -1);
        }
        String str = this.f5217h != null ? this.f5217h : "about:blank";
        this.f5212c.setUrl(str);
        this.f5213d.loadUrl(str);
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
        bundle.putString(AudienceNetworkActivity.BROWSER_URL, this.f5217h);
    }

    public void a_(boolean z) {
        this.f5213d.onPause();
        if (this.f5222m) {
            this.f5222m = false;
            this.f5215f.mo5478g(this.f5218i, new C2232a(this.f5213d.getFirstUrl()).m5710a(this.f5219j).m5712b(this.f5221l).m5713c(this.f5213d.getResponseEndMs()).m5714d(this.f5213d.getDomContentLoadedMs()).m5715e(this.f5213d.getScrollReadyMs()).m5716f(this.f5213d.getLoadFinishMs()).m5717g(System.currentTimeMillis()).m5711a().m5718a());
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f5213d.onResume();
    }

    public void onDestroy() {
        this.f5211b.removeBackButtonInterceptor(this.f5216g);
        C2610b.m6708a(this.f5213d);
        this.f5213d.destroy();
    }

    public void setListener(C1789a c1789a) {
    }
}
