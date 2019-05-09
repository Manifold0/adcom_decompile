package com.facebook.ads.internal.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p063f.C2351b;
import com.facebook.ads.internal.view.p063f.C2351b.C2349c;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.m */
public class C2516m extends C2323o {
    /* renamed from: d */
    private final C1879k f6138d;
    /* renamed from: e */
    private final BackButtonInterceptor f6139e = new C25141(this);
    /* renamed from: f */
    private C2351b f6140f;
    /* renamed from: g */
    private boolean f6141g;

    /* renamed from: com.facebook.ads.internal.view.m$1 */
    class C25141 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2516m f6132a;

        C25141(C2516m c2516m) {
            this.f6132a = c2516m;
        }

        public boolean interceptBackButton() {
            return !this.f6132a.f6141g;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.m$a */
    private static class C2515a implements C2349c {
        /* renamed from: a */
        private final WeakReference<Activity> f6133a;
        /* renamed from: b */
        private final WeakReference<C2516m> f6134b;
        /* renamed from: c */
        private final C1879k f6135c;
        /* renamed from: d */
        private final C2085c f6136d;
        /* renamed from: e */
        private final WeakReference<C1789a> f6137e;

        C2515a(Activity activity, C2516m c2516m, C1879k c1879k, C2085c c2085c, C1789a c1789a) {
            this.f6133a = new WeakReference(activity);
            this.f6134b = new WeakReference(c2516m);
            this.f6135c = c1879k;
            this.f6136d = c2085c;
            this.f6137e = new WeakReference(c1789a);
        }

        /* renamed from: e */
        private void m6482e() {
            if (this.f6133a.get() != null) {
                ((Activity) this.f6133a.get()).finish();
            }
        }

        /* renamed from: a */
        public void mo5591a() {
        }

        /* renamed from: a */
        public void mo5592a(C2630a c2630a, C2598w c2598w) {
            Map hashMap = new HashMap();
            if (!TextUtils.isEmpty(this.f6135c.m4307c())) {
                c2630a.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(c2598w.m6676e()));
                this.f6136d.mo5470a(this.f6135c.m4307c(), hashMap);
                if (this.f6137e.get() != null) {
                    ((C1789a) this.f6137e.get()).mo5335a("com.facebook.ads.interstitial.impression.logged");
                }
            }
        }

        /* renamed from: a */
        public void mo5593a(boolean z) {
            if (this.f6134b.get() != null && ((C2516m) this.f6134b.get()).f6140f.getAdWebView() != null && this.f6137e.get() != null) {
                C2248a adWebView = ((C2516m) this.f6134b.get()).f6140f.getAdWebView();
                C2295a c2295a = new C2295a(((C2516m) this.f6134b.get()).getContext(), true, false, "com.facebook.ads.interstitial.clicked", this.f6135c.m4306b().m4212a(), this.f6136d, (C1789a) this.f6137e.get(), adWebView.getViewabilityChecker(), adWebView.getTouchDataRecorder());
                c2295a.m5919a(((C1880l) this.f6135c.m4308d().get(0)).m4316b(), this.f6135c.m4307c(), new HashMap(), z);
                c2295a.performClick();
            }
        }

        /* renamed from: b */
        public void mo5594b() {
            if (this.f6134b.get() != null) {
                ((C2516m) this.f6134b.get()).f6141g = true;
            }
        }

        /* renamed from: c */
        public void mo5595c() {
            m6482e();
        }

        /* renamed from: d */
        public void mo5596d() {
            if (this.f6137e.get() != null) {
                ((C1789a) this.f6137e.get()).mo5335a("com.facebook.ads.interstitial.error");
            }
            m6482e();
        }
    }

    public C2516m(Context context, C2085c c2085c, C1879k c1879k, C1789a c1789a) {
        super(context, c2085c, c1789a);
        this.f6138d = c1879k;
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        super.m5988a(audienceNetworkActivity, this.f6138d);
        audienceNetworkActivity.addBackButtonInterceptor(this.f6139e);
        C1885o a = C1885o.m4345a(this.f6138d);
        this.f6140f = new C2351b(audienceNetworkActivity, a, getAdEventManager(), getAudienceNetworkListener(), new C2515a(audienceNetworkActivity, this, this.f6138d, getAdEventManager(), getAudienceNetworkListener()), a.m4352f().m4241c() > 0, true);
        m5987a(this.f6140f, true, 1);
        this.b.setVisibility(8);
        this.f6140f.m6052c();
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    public void a_(boolean z) {
        this.f6140f.m6054e();
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f6140f.m6053d();
    }

    public void onDestroy() {
        C2598w c2598w = null;
        super.onDestroy();
        if (!TextUtils.isEmpty(this.f6138d.m4307c())) {
            C2248a adWebView = this.f6140f.getAdWebView();
            C2630a viewabilityChecker = adWebView != null ? adWebView.getViewabilityChecker() : null;
            if (adWebView != null) {
                c2598w = adWebView.getTouchDataRecorder();
            }
            Map hashMap = new HashMap();
            if (viewabilityChecker != null) {
                viewabilityChecker.m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(c2598w.m6676e()));
            }
            this.a.mo5483l(this.f6138d.m4307c(), hashMap);
        }
        this.f6140f.m6055f();
    }
}
