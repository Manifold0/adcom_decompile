package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.C1895c;
import com.facebook.ads.internal.adapters.C1939r;
import com.facebook.ads.internal.adapters.p030b.C1865b;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2587p;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p057e.C2613e;
import com.facebook.ads.internal.p025w.p057e.C2613e.C2358a;
import com.facebook.ads.internal.p025w.p057e.C2614f;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import com.facebook.ads.internal.view.p063f.C2351b;
import com.facebook.ads.internal.view.p063f.C2351b.C2349c;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

@TargetApi(16)
/* renamed from: com.facebook.ads.internal.view.r */
public class C2537r extends RelativeLayout implements C1921a, C2349c {
    /* renamed from: a */
    private final C2085c f6201a;
    /* renamed from: b */
    private final C1887q f6202b;
    /* renamed from: c */
    private final C1884n f6203c;
    /* renamed from: d */
    private final C1865b f6204d;
    /* renamed from: e */
    private int f6205e;
    @Nullable
    /* renamed from: f */
    private Context f6206f;
    @Nullable
    /* renamed from: g */
    private AudienceNetworkActivity f6207g;
    @Nullable
    /* renamed from: h */
    private C1789a f6208h;
    /* renamed from: i */
    private Executor f6209i = C2587p.f6372a;
    /* renamed from: j */
    private final BackButtonInterceptor f6210j = new C25331(this);
    /* renamed from: k */
    private boolean f6211k;
    /* renamed from: l */
    private C2351b f6212l;
    /* renamed from: m */
    private boolean f6213m;
    /* renamed from: n */
    private C1939r f6214n;

    /* renamed from: com.facebook.ads.internal.view.r$1 */
    class C25331 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2537r f6197a;

        C25331(C2537r c2537r) {
            this.f6197a = c2537r;
        }

        public boolean interceptBackButton() {
            return !this.f6197a.f6213m;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.r$2 */
    class C25342 extends C1895c {
        /* renamed from: a */
        final /* synthetic */ C2537r f6198a;

        C25342(C2537r c2537r) {
            this.f6198a = c2537r;
        }

        /* renamed from: a */
        public void mo5392a() {
            C2537r.m6542b(this.f6198a);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.r$a */
    private static class C2536a implements C2358a {
        /* renamed from: a */
        final WeakReference<C1789a> f6200a;

        private C2536a(WeakReference<C1789a> weakReference) {
            this.f6200a = weakReference;
        }

        /* renamed from: a */
        public void mo5589a() {
            if (this.f6200a.get() != null) {
                ((C1789a) this.f6200a.get()).mo5335a(aa.REWARD_SERVER_FAILED.m6205a());
            }
        }

        /* renamed from: a */
        public void mo5590a(C2614f c2614f) {
            if (this.f6200a.get() != null) {
                if (c2614f == null || !c2614f.m6720a()) {
                    ((C1789a) this.f6200a.get()).mo5335a(aa.REWARD_SERVER_FAILED.m6205a());
                } else {
                    ((C1789a) this.f6200a.get()).mo5335a(aa.REWARD_SERVER_SUCCESS.m6205a());
                }
            }
        }
    }

    public C2537r(Context context, C2085c c2085c, C1789a c1789a, C1887q c1887q) {
        super(context);
        this.f6206f = context;
        this.f6208h = c1789a;
        this.f6201a = c2085c;
        this.f6202b = c1887q;
        this.f6203c = c1887q.m4366j().m4248j();
        this.f6204d = c1887q.m4365i();
    }

    @NonNull
    /* renamed from: a */
    private C2295a m6540a(C2248a c2248a) {
        return new C2295a(this.f6206f, true, false, aa.REWARDED_VIDEO_AD_CLICK.m6205a(), this.f6204d.m4212a(), this.f6201a, this.f6208h, c2248a.getViewabilityChecker(), c2248a.getTouchDataRecorder());
    }

    /* renamed from: b */
    static /* synthetic */ void m6542b(C2537r c2537r) {
        if (c2537r.f6208h != null) {
            c2537r.f6208h.mo5335a(aa.REWARDED_VIDEO_IMPRESSION.m6205a());
        }
    }

    /* renamed from: a */
    public void mo5591a() {
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f6208h != null && this.f6206f != null) {
            this.f6207g = audienceNetworkActivity;
            this.f6207g.addBackButtonInterceptor(this.f6210j);
            this.f6205e = audienceNetworkActivity.getRequestedOrientation();
            switch (this.f6203c.m4339f()) {
                case PORTRAIT:
                    audienceNetworkActivity.setRequestedOrientation(1);
                    break;
                case LANDSCAPE:
                    audienceNetworkActivity.setRequestedOrientation(0);
                    break;
                case UNSPECIFIED:
                    audienceNetworkActivity.setRequestedOrientation(-1);
                    break;
            }
            View c2351b = new C2351b(this.f6206f, C1885o.m4346a(this.f6202b), this.f6201a, this.f6208h, this, true, false);
            this.f6212l = c2351b;
            addView(c2351b);
            this.f6208h.mo5333a((View) this);
            c2351b.m6052c();
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    /* renamed from: a */
    public void mo5592a(C2630a c2630a, C2598w c2598w) {
        C1939r c1939r;
        if (this.f6214n != null) {
            c1939r = this.f6214n;
        } else {
            this.f6214n = new C1939r(getContext(), this.f6201a, c2630a, c2598w, new C25342(this));
            this.f6214n.m4584a(this.f6202b);
            c1939r = this.f6214n;
        }
        c1939r.m4369a();
    }

    /* renamed from: a */
    public void mo5593a(boolean z) {
        this.f6211k = true;
        C2248a adWebView = this.f6212l.getAdWebView();
        if (adWebView != null) {
            C2295a a = m6540a(adWebView);
            a.m5919a(this.f6202b.m4364h(), this.f6202b.mo5384a(), new HashMap(), z);
            a.performClick();
        }
    }

    public void a_(boolean z) {
        this.f6212l.m6054e();
    }

    /* renamed from: b */
    public void mo5594b() {
        this.f6213m = true;
        CharSequence a = this.f6202b.m4367k().m4297a();
        if (!(this.f6206f == null && TextUtils.isEmpty(a))) {
            C2613e c2613e = new C2613e(this.f6206f, new HashMap());
            c2613e.m6718a(new C2536a(new WeakReference(this.f6208h)));
            c2613e.executeOnExecutor(this.f6209i, new String[]{a});
        }
        if (this.f6208h != null) {
            this.f6208h.mo5336a(aa.REWARDED_VIDEO_COMPLETE.m6205a(), new C2406c(0, 0));
        }
        C2248a adWebView = this.f6212l.getAdWebView();
        if (this.f6211k && adWebView != null) {
            m6540a(adWebView).m5920b(this.f6202b.m4364h(), this.f6202b.mo5384a(), new HashMap());
        }
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f6212l.m6053d();
    }

    /* renamed from: c */
    public void mo5595c() {
        if (this.f6208h != null) {
            this.f6208h.mo5335a(aa.REWARDED_VIDEO_END_ACTIVITY.m6205a());
        }
    }

    /* renamed from: d */
    public void mo5596d() {
        if (this.f6208h != null) {
            this.f6208h.mo5335a(aa.REWARDED_VIDEO_ERROR.m6205a());
        }
    }

    public void onDestroy() {
        if (this.f6207g != null) {
            this.f6207g.removeBackButtonInterceptor(this.f6210j);
            this.f6207g.setRequestedOrientation(this.f6205e);
        }
        C2248a adWebView = this.f6212l.getAdWebView();
        if (!(adWebView == null || TextUtils.isEmpty(this.f6202b.mo5384a()))) {
            Map hashMap = new HashMap();
            adWebView.getViewabilityChecker().m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(adWebView.getTouchDataRecorder().m6676e()));
            this.f6201a.mo5483l(this.f6202b.mo5384a(), hashMap);
        }
        this.f6212l.m6055f();
        this.f6208h = null;
        this.f6207g = null;
        this.f6206f = null;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f6212l.getAdWebView() != null) {
            if (z) {
                mo5406b(false);
            } else {
                a_(false);
            }
        }
    }

    public void setListener(C1789a c1789a) {
        this.f6208h = c1789a;
    }
}
