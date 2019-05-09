package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.AudienceNetworkActivity.BackButtonInterceptor;
import com.facebook.ads.internal.adapters.p030b.C1874f;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p025w.p026b.C2572e;
import com.facebook.ads.internal.p025w.p026b.C2572e.C2338a;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p029x.C2630a.C1858a;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2091h;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2302i;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import com.facebook.ads.internal.view.p055a.C2192b;
import com.facebook.ads.internal.view.p064g.C2355b;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.f */
public class C2352f extends RelativeLayout implements C1921a {
    /* renamed from: a */
    private static final LayoutParams f5648a = new LayoutParams(-1, -1);
    /* renamed from: b */
    private static final int f5649b = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5650c = ((int) (56.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5651d = ((int) (230.0f * C2600x.f6420b));
    /* renamed from: e */
    private final C1874f f5652e;
    /* renamed from: f */
    private final C1789a f5653f;
    /* renamed from: g */
    private final C2085c f5654g;
    /* renamed from: h */
    private final C2598w f5655h = new C2598w();
    /* renamed from: i */
    private final C2630a f5656i;
    /* renamed from: j */
    private final C1858a f5657j;
    /* renamed from: k */
    private final C2572e f5658k;
    /* renamed from: l */
    private final int f5659l;
    /* renamed from: m */
    private boolean f5660m;
    /* renamed from: n */
    private boolean f5661n;
    /* renamed from: o */
    private WeakReference<AudienceNetworkActivity> f5662o;
    /* renamed from: p */
    private final C2302i f5663p;
    /* renamed from: q */
    private final TextView f5664q;
    /* renamed from: r */
    private final LinearLayout f5665r;
    /* renamed from: s */
    private final BackButtonInterceptor f5666s = new C23321(this);

    /* renamed from: com.facebook.ads.internal.view.f$1 */
    class C23321 implements BackButtonInterceptor {
        /* renamed from: a */
        final /* synthetic */ C2352f f5595a;

        C23321(C2352f c2352f) {
            this.f5595a = c2352f;
        }

        public boolean interceptBackButton() {
            return true;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$2 */
    class C23332 extends C1858a {
        /* renamed from: a */
        final /* synthetic */ C2352f f5596a;

        C23332(C2352f c2352f) {
            this.f5596a = c2352f;
        }

        /* renamed from: a */
        public void mo5381a() {
            if (!this.f5596a.f5655h.m6673b()) {
                this.f5596a.f5655h.m6670a();
                for (int i = 0; i < this.f5596a.f5665r.getChildCount(); i++) {
                    if (this.f5596a.f5665r.getChildAt(i) instanceof C2355b) {
                        C2355b c2355b = (C2355b) this.f5596a.f5665r.getChildAt(i);
                        c2355b.m6076a(i);
                        c2355b.setViewability(true);
                    }
                }
                if (!this.f5596a.f5660m) {
                    this.f5596a.f5658k.m6629a();
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$3 */
    class C23343 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2352f f5597a;

        C23343(C2352f c2352f) {
            this.f5597a = c2352f;
        }

        public void onClick(View view) {
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$a */
    public static class C2335a extends C2061d {
        /* renamed from: a */
        private C1887q f5598a;

        public C2335a(C1887q c1887q) {
            this.f5598a = c1887q;
        }

        /* renamed from: a */
        public C1887q m6017a() {
            return this.f5598a;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$b */
    private static class C2336b implements OnClickListener, OnTouchListener {
        /* renamed from: a */
        final WeakReference<C2352f> f5599a;
        /* renamed from: b */
        final WeakReference<C2355b> f5600b;

        public C2336b(C2352f c2352f, C2355b c2355b) {
            this.f5599a = new WeakReference(c2352f);
            this.f5600b = new WeakReference(c2355b);
        }

        public void onClick(View view) {
            if (this.f5599a.get() != null && this.f5600b.get() != null && !((C2355b) this.f5600b.get()).m6080a()) {
                C2352f.m6057a((C2352f) this.f5599a.get(), ((C2355b) this.f5600b.get()).getAdDataBundle());
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f5599a.get() != null) {
                ((C2352f) this.f5599a.get()).getTouchDataRecorder().m6671a(motionEvent, (View) this.f5599a.get(), view);
            }
            return false;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$c */
    public static class C2337c implements C2192b {
        /* renamed from: a */
        final WeakReference<C2352f> f5601a;
        /* renamed from: b */
        final WeakReference<C2355b> f5602b;

        C2337c(C2352f c2352f, C2355b c2355b) {
            this.f5601a = new WeakReference(c2352f);
            this.f5602b = new WeakReference(c2355b);
        }

        /* renamed from: a */
        public void mo5583a() {
            C2352f c2352f = (C2352f) this.f5601a.get();
            if (c2352f != null) {
                c2352f.setIsAdReportingLayoutVisible(true);
                c2352f.m6067c(true);
            }
        }

        /* renamed from: a */
        public void mo5584a(C1996c c1996c, C1994a c1994a) {
            if (this.f5602b.get() != null) {
                ((C2355b) this.f5602b.get()).m6077a(c1996c, c1994a);
            }
        }

        /* renamed from: a */
        public void mo5585a(boolean z) {
            if (this.f5601a.get() != null) {
                ((C2352f) this.f5601a.get()).setIsAdReportingLayoutVisible(false);
                if (z) {
                    ((C2352f) this.f5601a.get()).m6063a();
                } else {
                    ((C2352f) this.f5601a.get()).m6067c(false);
                }
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.f$d */
    private static class C2339d implements C2338a {
        /* renamed from: a */
        private final WeakReference<C2352f> f5603a;
        /* renamed from: b */
        private final WeakReference<C2302i> f5604b;
        /* renamed from: c */
        private final C1874f f5605c;
        /* renamed from: d */
        private int f5606d;

        public C2339d(C2352f c2352f, C1874f c1874f, int i) {
            this.f5603a = new WeakReference(c2352f);
            this.f5604b = new WeakReference(c2352f.f5663p);
            this.f5605c = c1874f;
            this.f5606d = i;
        }

        /* renamed from: a */
        public void mo5586a() {
            if (this.f5603a.get() != null) {
                int i;
                LinearLayout b = ((C2352f) this.f5603a.get()).f5665r;
                int b2 = this.f5605c.m4280i().m4216b();
                if (((C2355b) b.getChildAt(b2)).m6080a()) {
                    i = 0;
                    while (i < b.getChildCount()) {
                        if (!((C2355b) b.getChildAt(i)).m6080a()) {
                            break;
                        }
                        i++;
                    }
                }
                i = b2;
                C2352f.m6057a((C2352f) this.f5603a.get(), (C1887q) this.f5605c.m4281j().get(i));
            }
        }

        /* renamed from: a */
        public void mo5587a(int i) {
            C2302i c2302i = (C2302i) this.f5604b.get();
            if (c2302i != null) {
                c2302i.setProgress(((this.f5606d - i) * 100) / this.f5606d);
                c2302i.setText(this.f5605c.m4276e().m4284a(String.valueOf(i)));
            }
        }
    }

    public C2352f(Context context, C1874f c1874f, C2085c c2085c, C1789a c1789a) {
        int i = 1;
        super(context);
        this.f5652e = c1874f;
        this.f5654g = c2085c;
        this.f5659l = this.f5652e.m4280i().m4215a() / 1000;
        this.f5653f = c1789a;
        this.f5657j = new C23332(this);
        this.f5656i = new C2630a(this, 1, this.f5657j);
        this.f5656i.m6770a(250);
        this.f5663p = new C2302i(context);
        C2600x.m6679a(this.f5663p);
        this.f5664q = new TextView(getContext());
        C2600x.m6679a(this.f5664q);
        this.f5665r = new LinearLayout(getContext());
        boolean z = getResources().getConfiguration().orientation == 1;
        this.f5663p.setProgress(0);
        this.f5663p.m5923a(false, Color.parseColor(this.f5652e.m4278g()), 14);
        this.f5663p.setText(this.f5652e.m4276e().m4284a(String.valueOf(this.f5659l)));
        C2600x.m6680a(this.f5663p, 0);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, f5650c);
        layoutParams.addRule(10);
        addView(this.f5663p, layoutParams);
        this.f5664q.setText(this.f5652e.m4276e().m4283a());
        C2600x.m6687a(this.f5664q, true, 32);
        this.f5664q.setTextColor(Color.parseColor(this.f5652e.m4279h()));
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(z ? f5651d : -1, -2);
        layoutParams2.setMargins(f5649b, 0, f5649b, f5649b / 2);
        layoutParams2.addRule(3, this.f5663p.getId());
        addView(this.f5664q, layoutParams2);
        this.f5665r.setPadding(f5649b / 2, f5649b / 2, f5649b / 2, f5649b / 2);
        LinearLayout linearLayout = this.f5665r;
        if (!z) {
            i = 0;
        }
        linearLayout.setOrientation(i);
        m6058a(z, this.f5652e.m4281j());
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, -1);
        layoutParams3.addRule(3, this.f5664q.getId());
        addView(this.f5665r, layoutParams3);
        C2600x.m6680a((View) this, Color.parseColor(this.f5652e.m4277f()));
        this.f5658k = new C2572e(this.f5659l, new C2339d(this, this.f5652e, this.f5659l));
        this.f5656i.m6769a();
    }

    /* renamed from: a */
    static /* synthetic */ void m6057a(C2352f c2352f, C1887q c1887q) {
        if (!c2352f.f5660m) {
            c2352f.f5660m = true;
            c2352f.f5658k.m6630b();
            if (c2352f.f5656i != null) {
                c2352f.f5656i.m6774c();
            }
            View view = new View(c2352f.getContext());
            view.setOnClickListener(new C23343(c2352f));
            c2352f.addView(view, new LayoutParams(-1, -1));
            C2091h c2091h = new C2091h();
            for (int i = 0; i < c2352f.f5665r.getChildCount(); i++) {
                C2355b c2355b = (C2355b) c2352f.f5665r.getChildAt(i);
                if (c2355b.getAdDataBundle() == c1887q) {
                    c2091h.m5214c(i);
                }
                c2355b.m6084d();
            }
            String a = c1887q.mo5384a();
            c2091h.m5215d((c2352f.f5659l - c2352f.f5658k.m6633e()) * 1000);
            c2091h.m5216e(c2352f.f5659l * 1000);
            c2091h.m5211a(c2352f.f5652e.m4281j().size());
            c2091h.m5212a(c2352f.f5658k.m6632d());
            c2091h.m5213b(c2352f.f5652e.m4280i().m4216b());
            Map hashMap = new HashMap();
            c2352f.f5656i.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(c2352f.f5655h.m6676e()));
            hashMap.put("ad_selection", C2581k.m6645a(c2091h.m5210a()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            c2352f.f5654g.mo5487p(a, hashMap);
            c1887q.m4206a(c2352f.f5652e.m4208b());
            c1887q.mo5386a(c2352f.f5652e.m4210c());
            C2600x.m6690c(c2352f);
            C2600x.m6689b(c2352f);
            c2352f.f5653f.mo5336a(aa.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.m6205a(), new C2335a(c1887q));
            if (c2352f.f5662o != null && c2352f.f5662o.get() != null) {
                ((AudienceNetworkActivity) c2352f.f5662o.get()).removeBackButtonInterceptor(c2352f.f5666s);
            }
        }
    }

    /* renamed from: a */
    private void m6058a(boolean z, List<C1887q> list) {
        this.f5665r.setWeightSum((float) list.size());
        Object obj = list.size() == 2 ? 1 : null;
        boolean z2 = list.size() >= 3 && !z;
        int i = 0;
        for (C1887q c2355b : list) {
            View c2355b2 = new C2355b(getContext(), c2355b, this.f5654g, this.f5656i, this.f5655h, this.f5653f);
            c2355b2.setShouldPlayButtonOnTop(z2);
            c2355b2.m6078a(this.f5652e.m4280i().m4218d());
            c2355b2.setCornerRadius(10);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(z ? -1 : 0, z ? 0 : -1);
            layoutParams.setMargins(f5649b / 2, f5649b / 2, f5649b / 2, f5649b / 2);
            layoutParams.weight = 1.0f;
            Object c2336b = new C2336b(this, c2355b2);
            c2355b2.setOnTouchListener(c2336b);
            c2355b2.setOnClickListener(c2336b);
            c2355b2.setAdReportingFlowListener(new C2337c(this, c2355b2));
            if (obj != null) {
                c2355b2.m6079a(i % 2 != 0, this.f5652e.m4280i().m4217c());
            }
            this.f5665r.addView(c2355b2, layoutParams);
            i++;
        }
    }

    /* renamed from: a */
    void m6063a() {
        int i = 1;
        for (int i2 = 0; i2 < this.f5665r.getChildCount(); i2++) {
            C2355b c2355b = (C2355b) this.f5665r.getChildAt(i2);
            i &= c2355b.m6080a();
            c2355b.m6084d();
        }
        if (i != 0 && this.f5653f != null) {
            this.f5653f.mo5335a(aa.REWARDED_VIDEO_END_ACTIVITY.m6205a());
        }
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        if (this.f5653f != null) {
            setLayoutParams(f5648a);
            this.f5653f.mo5333a((View) this);
            audienceNetworkActivity.addBackButtonInterceptor(this.f5666s);
            this.f5662o = new WeakReference(audienceNetworkActivity);
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
        this.f5658k.m6630b();
    }

    public void a_(boolean z) {
        this.f5658k.m6630b();
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        if (!this.f5660m) {
            if (z || !this.f5661n) {
                this.f5658k.m6629a();
            }
        }
    }

    /* renamed from: c */
    void m6067c(boolean z) {
        for (int i = 0; i < this.f5665r.getChildCount(); i++) {
            if (z) {
                ((C2355b) this.f5665r.getChildAt(i)).m6081b();
            } else {
                ((C2355b) this.f5665r.getChildAt(i)).m6083c();
            }
        }
    }

    final C2598w getTouchDataRecorder() {
        return this.f5655h;
    }

    protected void onConfigurationChanged(Configuration configuration) {
        boolean z = true;
        int i = 0;
        boolean z2 = configuration.orientation == 1;
        ((LayoutParams) this.f5664q.getLayoutParams()).width = z2 ? f5651d : -1;
        this.f5665r.setOrientation(z2 ? 1 : 0);
        if (this.f5652e.m4281j().size() < 3 || z2) {
            z = false;
        }
        while (i < this.f5665r.getChildCount()) {
            C2355b c2355b = (C2355b) this.f5665r.getChildAt(i);
            c2355b.m6082b(z2);
            c2355b.setShouldPlayButtonOnTop(z);
            i++;
        }
    }

    public void onDestroy() {
        this.f5658k.m6630b();
        if (this.f5656i != null) {
            this.f5656i.m6774c();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.f5655h.m6671a(motionEvent, this, this);
        if (motionEvent.getAction() == 1) {
            Map hashMap = new HashMap();
            this.f5656i.m6771a(hashMap);
            hashMap.put("touch", C2581k.m6645a(this.f5655h.m6676e()));
            hashMap.put("is_cyoa", Boolean.TRUE.toString());
            this.f5654g.mo5475d(((C1887q) this.f5652e.m4281j().get(0)).mo5384a(), hashMap);
        }
        return true;
    }

    void setIsAdReportingLayoutVisible(boolean z) {
        this.f5661n = z;
    }

    public void setListener(C1789a c1789a) {
    }
}
