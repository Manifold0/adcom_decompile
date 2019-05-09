package com.facebook.ads.internal.view.p063f;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.internal.adapters.p030b.C1865b;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.p025w.p026b.C2572e;
import com.facebook.ads.internal.p025w.p026b.C2572e.C2338a;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2504i;
import com.facebook.ads.internal.view.C2504i.C2282b;
import com.facebook.ads.internal.view.C2504i.C2383a;
import com.facebook.ads.internal.view.component.C2294c;
import com.facebook.ads.internal.view.component.C2303j;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p019c.C2248a.C1898b;
import com.facebook.ads.internal.view.p019c.C2248a.C1899c;
import com.facebook.ads.internal.view.p019c.C2248a.C2243d;
import com.tapjoy.TJAdUnitConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.facebook.ads.internal.view.f.b */
public class C2351b extends RelativeLayout implements C2243d, C2338a {
    /* renamed from: a */
    private static final int f5625a = ((int) (64.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final LayoutParams f5626b = new LayoutParams(-1, -1);
    /* renamed from: c */
    private static final int f5627c = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5628d = ((int) (12.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5629e = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: f */
    private static final float f5630f = ((float) ((int) (4.0f * C2600x.f6420b)));
    /* renamed from: g */
    private final C1885o f5631g;
    /* renamed from: h */
    private final C1884n f5632h;
    /* renamed from: i */
    private final C1865b f5633i;
    /* renamed from: j */
    private final C2085c f5634j;
    /* renamed from: k */
    private final C2504i f5635k;
    /* renamed from: l */
    private final AtomicBoolean f5636l = new AtomicBoolean();
    /* renamed from: m */
    private final C2572e f5637m;
    /* renamed from: n */
    private final C2572e f5638n;
    /* renamed from: o */
    private final boolean f5639o;
    /* renamed from: p */
    private WeakReference<C2248a> f5640p;
    /* renamed from: q */
    private C1898b f5641q;
    /* renamed from: r */
    private C2294c f5642r;
    /* renamed from: s */
    private C2342a f5643s;
    /* renamed from: t */
    private RelativeLayout f5644t;
    /* renamed from: u */
    private boolean f5645u = false;
    /* renamed from: v */
    private Toast f5646v;
    @Nullable
    /* renamed from: w */
    private C2349c f5647w;

    /* renamed from: com.facebook.ads.internal.view.f.b$1 */
    class C23431 implements C2338a {
        /* renamed from: a */
        final /* synthetic */ C2351b f5616a;

        C23431(C2351b c2351b) {
            this.f5616a = c2351b;
        }

        /* renamed from: a */
        public void mo5586a() {
            this.f5616a.m6047g();
        }

        /* renamed from: a */
        public void mo5587a(int i) {
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$2 */
    class C23442 implements C2282b {
        /* renamed from: a */
        final /* synthetic */ C2351b f5617a;

        C23442(C2351b c2351b) {
            this.f5617a = c2351b;
        }

        /* renamed from: a */
        public void mo5562a() {
            if (this.f5617a.f5647w != null) {
                this.f5617a.f5647w.mo5595c();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$3 */
    class C23453 extends C1899c {
        /* renamed from: a */
        final /* synthetic */ C2351b f5618a;

        C23453(C2351b c2351b) {
            this.f5618a = c2351b;
        }

        /* renamed from: a */
        public void mo5389a(int i, @Nullable String str) {
            this.f5618a.f5645u = true;
            if (this.f5618a.f5640p.get() != null) {
                ((C2248a) this.f5618a.f5640p.get()).setVisibility(4);
            }
            if (this.f5618a.f5647w != null) {
                this.f5618a.f5647w.mo5596d();
            }
        }

        /* renamed from: b */
        public void mo5391b() {
            if (this.f5618a.f5636l.compareAndSet(false, true) && this.f5618a.f5640p.get() != null && this.f5618a.f5647w != null) {
                C2248a c2248a = (C2248a) this.f5618a.f5640p.get();
                this.f5618a.f5647w.mo5592a(c2248a.getViewabilityChecker(), c2248a.getTouchDataRecorder());
                this.f5618a.f5637m.m6629a();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$4 */
    class C23464 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2351b f5619a;

        C23464(C2351b c2351b) {
            this.f5619a = c2351b;
        }

        public void run() {
            C2351b.m6046f(this.f5619a);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$a */
    private static class C2347a implements OnClickListener {
        /* renamed from: a */
        final WeakReference<C2351b> f5620a;

        C2347a(C2351b c2351b) {
            this.f5620a = new WeakReference(c2351b);
        }

        public void onClick(View view) {
            if (this.f5620a.get() != null) {
                C2351b.m6048g((C2351b) this.f5620a.get());
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$b */
    private static class C2348b implements OnTouchListener {
        /* renamed from: a */
        final WeakReference<C2248a> f5621a;
        /* renamed from: b */
        final C2085c f5622b;
        /* renamed from: c */
        final C1885o f5623c;

        private C2348b(C2248a c2248a, C2085c c2085c, C1885o c1885o) {
            this.f5621a = new WeakReference(c2248a);
            this.f5622b = c2085c;
            this.f5623c = c1885o;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f5621a.get() != null && motionEvent.getAction() == 1) {
                Map hashMap = new HashMap();
                ((C2248a) this.f5621a.get()).getViewabilityChecker().m6771a(hashMap);
                hashMap.put("touch", C2581k.m6645a(((C2248a) this.f5621a.get()).getTouchDataRecorder().m6676e()));
                this.f5622b.mo5475d(this.f5623c.m4353g(), hashMap);
            }
            return false;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$c */
    public interface C2349c {
        /* renamed from: a */
        void mo5591a();

        /* renamed from: a */
        void mo5592a(C2630a c2630a, C2598w c2598w);

        /* renamed from: a */
        void mo5593a(boolean z);

        /* renamed from: b */
        void mo5594b();

        /* renamed from: c */
        void mo5595c();

        /* renamed from: d */
        void mo5596d();
    }

    /* renamed from: com.facebook.ads.internal.view.f.b$d */
    private class C2350d {
        /* renamed from: a */
        final /* synthetic */ C2351b f5624a;

        private C2350d(C2351b c2351b) {
            this.f5624a = c2351b;
        }

        @JavascriptInterface
        public void onCTAClick() {
            C2351b.m6048g(this.f5624a);
        }
    }

    public C2351b(Context context, C1885o c1885o, C2085c c2085c, C1789a c1789a, C2349c c2349c, boolean z, boolean z2) {
        super(context);
        this.f5631g = c1885o;
        this.f5632h = c1885o.m4352f().m4248j();
        this.f5633i = c1885o.m4351e();
        this.f5634j = c2085c;
        this.f5647w = c2349c;
        this.f5635k = new C2504i(context, c1789a, C2383a.CROSS);
        this.f5639o = z2;
        this.f5637m = new C2572e(z ? this.f5632h.m4336c() : 0, this);
        this.f5638n = new C2572e(this.f5632h.m4341h() ? 2 : 0, new C23431(this));
        this.f5635k.m6453a(this.f5633i.m4212a(), true);
        this.f5635k.setShowPageDetails(false);
        this.f5635k.m6455a(this.f5631g.m4348b(), this.f5631g.m4353g(), this.f5632h.m4336c());
        this.f5635k.setToolbarListener(new C23442(this));
        C2600x.m6679a(this.f5635k);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10);
        this.f5635k.setLayoutParams(layoutParams);
        this.f5643s = new C2342a(getContext(), this.f5631g);
        setLayoutParams(f5626b);
        C2600x.m6680a((View) this, this.f5633i.m4212a().m4290d(true));
        addView(this.f5643s, f5626b);
        C2600x.m6680a((View) this, -14473425);
        setLayoutParams(f5626b);
    }

    /* renamed from: a */
    private static TextView m6038a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof TextView) {
                return (TextView) childAt;
            }
            if (childAt instanceof ViewGroup) {
                C2351b.m6038a((ViewGroup) childAt);
            }
        }
        return null;
    }

    /* renamed from: b */
    private void m6042b(int i) {
        if (this.f5646v != null) {
            this.f5646v.setGravity(49, 0, f5625a);
            CharSequence valueOf = String.valueOf(i);
            TextView a = C2351b.m6038a((ViewGroup) this.f5646v.getView());
            if (a != null) {
                a.setText(this.f5632h.m4338e().replace("[secs]", valueOf));
                a.setGravity(17);
            }
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m6046f(C2351b c2351b) {
        if (c2351b.f5646v == null || c2351b.f5646v.getView().getWindowVisibility() != 0) {
            c2351b.f5646v = Toast.makeText(c2351b.getContext(), c2351b.f5632h.m4338e(), 1);
            c2351b.m6042b(c2351b.f5637m.m6633e());
            c2351b.f5646v.show();
        }
    }

    /* renamed from: g */
    private void m6047g() {
        if (this.f5647w != null) {
            this.f5647w.mo5591a();
        }
        this.f5644t = new RelativeLayout(getContext());
        C2600x.m6679a(this.f5644t);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(f5627c, f5628d, f5627c, f5628d);
        layoutParams.addRule(12);
        this.f5644t.setLayoutParams(layoutParams);
        View c2294c = new C2294c(getContext(), true, false, this.f5633i.m4212a());
        c2294c.setButtonColor(452984831);
        c2294c.setText(this.f5631g.m4350d().m4296b());
        c2294c.getBackground().setAlpha(0);
        C2600x.m6679a(c2294c);
        c2294c.setOnClickListener(new C2347a(this));
        c2294c.setTextSize(14.0f);
        c2294c.setIncludeFontPadding(false);
        c2294c.setPadding(f5629e, f5629e, f5629e, f5629e);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(11);
        c2294c.setLayoutParams(layoutParams2);
        if (!this.f5639o) {
            c2294c.setVisibility(8);
        }
        this.f5642r = c2294c;
        C2294c c2294c2 = this.f5642r;
        c2294c = new C2303j(getContext(), this.f5631g.m4351e().m4212a(), true, 16, 14, 0);
        C2600x.m6679a(c2294c);
        c2294c.m5924a(this.f5631g.m4349c().m4265a(), this.f5631g.m4349c().m4266b(), null, false, true);
        TextView descriptionTextView = c2294c.getDescriptionTextView();
        descriptionTextView.setAlpha(0.8f);
        descriptionTextView.setMaxLines(1);
        descriptionTextView.setEllipsize(TruncateAt.END);
        descriptionTextView = c2294c.getTitleTextView();
        descriptionTextView.setMaxLines(1);
        descriptionTextView.setEllipsize(TruncateAt.END);
        layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(0, c2294c2.getId());
        layoutParams2.setMargins(0, 0, f5627c, 0);
        c2294c.setLayoutParams(layoutParams2);
        LayoutParams layoutParams3 = (LayoutParams) this.f5642r.getLayoutParams();
        layoutParams3.addRule(6, c2294c.getId());
        layoutParams3.addRule(8, c2294c.getId());
        this.f5641q = new C23453(this);
        View c2248a = new C2248a(getContext(), new WeakReference(this.f5641q), 10);
        c2248a.setLogMultipleImpressions(false);
        c2248a.setWaitForAssetsToLoad(true);
        c2248a.setCheckAssetsByJavascriptBridge(false);
        c2248a.setWebViewTimeoutInMillis(this.f5632h.m4340g());
        c2248a.setRequestId(this.f5631g.m4347a());
        WebSettings settings = c2248a.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
        }
        this.f5640p = new WeakReference(c2248a);
        c2248a.loadUrl(getMarkupUrl());
        c2248a.setOnTouchListener(new C2348b(c2248a, this.f5634j, this.f5631g));
        c2248a.addJavascriptInterface(new C2350d(), "FbPlayableAd");
        c2248a.setCornerRadius(f5630f);
        C2600x.m6680a((View) this, -14473425);
        ViewGroup.LayoutParams layoutParams4 = new LayoutParams(-1, -1);
        layoutParams4.setMargins(f5627c, 0, f5627c, 0);
        layoutParams4.addRule(3, this.f5635k.getId());
        layoutParams4.addRule(2, this.f5644t.getId());
        c2248a.setLayoutParams(layoutParams4);
        c2248a.setVisibility(4);
        c2248a.setOnAssetsLoadedListener(this);
        this.f5644t.addView(c2294c);
        this.f5644t.addView(this.f5642r);
        addView(this.f5635k);
        addView(c2248a);
        addView(this.f5644t);
        this.f5635k.setVisibility(4);
        c2248a.setVisibility(4);
        c2248a.setTranslationY(50.0f);
        this.f5644t.setVisibility(4);
        this.f5644t.setTranslationY(200.0f);
    }

    /* renamed from: g */
    static /* synthetic */ void m6048g(C2351b c2351b) {
        boolean z = (c2351b.f5639o || c2351b.f5637m.m6632d()) ? false : true;
        if (c2351b.f5647w != null) {
            c2351b.f5647w.mo5593a(z);
        }
        if (z) {
            new Handler(Looper.getMainLooper()).post(new C23464(c2351b));
        }
    }

    private String getMarkupUrl() {
        return !TextUtils.isEmpty(this.f5632h.m4343j()) ? this.f5632h.m4343j() : this.f5632h.m4333a();
    }

    /* renamed from: a */
    public void mo5586a() {
        if (this.f5647w != null) {
            this.f5647w.mo5594b();
        }
        this.f5635k.m6457a(true);
        if (!this.f5639o) {
            C2600x.m6684a((ViewGroup) this, (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL);
            this.f5642r.setVisibility(0);
        }
    }

    /* renamed from: a */
    public void mo5587a(int i) {
        this.f5635k.setProgress((1.0f - (((float) i) / ((float) this.f5632h.m4336c()))) * 100.0f);
        m6042b(i);
    }

    /* renamed from: b */
    public void mo5588b() {
        if (!this.f5645u && this.f5640p.get() != null) {
            C2248a adWebView = getAdWebView();
            if (adWebView != null) {
                C2600x.m6683a((ViewGroup) this);
                adWebView.setVisibility(0);
                C2600x.m6689b(this.f5643s);
                this.f5635k.setVisibility(0);
                this.f5644t.setVisibility(0);
                adWebView.animate().setStartDelay(100).setDuration(300).translationYBy(-50.0f);
                this.f5644t.animate().setStartDelay(100).setDuration(300).translationYBy(-200.0f);
            }
        }
    }

    /* renamed from: c */
    public void m6052c() {
        if (this.f5632h.m4341h()) {
            this.f5638n.m6629a();
            return;
        }
        removeAllViews();
        m6047g();
    }

    /* renamed from: d */
    public void m6053d() {
        if (!this.f5638n.m6632d()) {
            this.f5638n.m6629a();
        } else if (!this.f5637m.m6631c()) {
            this.f5637m.m6629a();
        }
    }

    /* renamed from: e */
    public void m6054e() {
        this.f5638n.m6630b();
        this.f5637m.m6630b();
    }

    /* renamed from: f */
    public void m6055f() {
        this.f5638n.m6630b();
        this.f5637m.m6630b();
        this.f5635k.setToolbarListener(null);
        C2248a c2248a = this.f5640p != null ? (C2248a) this.f5640p.get() : null;
        if (c2248a != null) {
            c2248a.removeJavascriptInterface("FbPlayableAd");
        }
        this.f5647w = null;
        this.f5646v = null;
    }

    public C2248a getAdWebView() {
        return this.f5640p != null ? (C2248a) this.f5640p.get() : null;
    }
}
