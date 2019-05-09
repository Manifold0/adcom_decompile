package com.facebook.ads.internal.view.p064g;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1873e.C1871a;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2598w;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p029x.C2630a;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.component.C2300g;
import com.facebook.ads.internal.view.component.C2305k;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import com.facebook.ads.internal.view.p055a.C2191a.C2190a;
import com.facebook.ads.internal.view.p055a.C2192b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.facebook.ads.internal.view.g.b */
public class C2355b extends C2300g {
    /* renamed from: b */
    private static final int f5675b = ((int) (48.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5676c = ((int) (40.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5677d = ((int) (16.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5678e = ((int) (56.0f * C2600x.f6420b));
    /* renamed from: f */
    private static final int f5679f = ((int) (200.0f * C2600x.f6420b));
    /* renamed from: g */
    private C1887q f5680g;
    /* renamed from: h */
    private final C2085c f5681h;
    /* renamed from: i */
    private final C2630a f5682i;
    /* renamed from: j */
    private final C2598w f5683j;
    /* renamed from: k */
    private final Map<String, String> f5684k = new HashMap();
    /* renamed from: l */
    private RelativeLayout f5685l;
    /* renamed from: m */
    private C2305k f5686m;
    /* renamed from: n */
    private ImageView f5687n;
    /* renamed from: o */
    private C2481l f5688o;
    /* renamed from: p */
    private ObjectAnimator f5689p;
    /* renamed from: q */
    private boolean f5690q;
    /* renamed from: r */
    private boolean f5691r;
    /* renamed from: s */
    private boolean f5692s;
    /* renamed from: t */
    private boolean f5693t = false;
    /* renamed from: u */
    private boolean f5694u;
    @Nullable
    /* renamed from: v */
    private C1996c f5695v;
    @Nullable
    /* renamed from: w */
    private C1994a f5696w;

    /* renamed from: com.facebook.ads.internal.view.g.b$1 */
    class C23541 implements C1802e {
        /* renamed from: a */
        final /* synthetic */ C2355b f5674a;

        C23541(C2355b c2355b) {
            this.f5674a = c2355b;
        }

        /* renamed from: a */
        public void mo5349a(boolean z) {
            this.f5674a.f5691r = z;
            this.f5674a.m6075e();
        }
    }

    public C2355b(Context context, C1887q c1887q, C2085c c2085c, C2630a c2630a, C2598w c2598w, C1789a c1789a) {
        super(context);
        this.f5680g = c1887q;
        this.f5681h = c2085c;
        this.f5682i = c2630a;
        this.f5683j = c2598w;
        this.f5687n = new C2299f(context);
        this.f5688o = new C2481l(context, true);
        this.f5688o.setClickable(false);
        this.f5687n.setScaleType(ScaleType.CENTER_CROP);
        addView(this.f5687n, new LayoutParams(-1, -1));
        new C2252d(this.f5687n).m5771a().m5773a(new C23541(this)).m5775a(this.f5680g.m4366j().m4245g());
        String a = c1887q.mo5384a();
        View relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setPadding(f5677d, f5677d, f5677d, f5677d);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        addView(relativeLayout);
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{0, -872415232});
        gradientDrawable.setCornerRadius(0.0f);
        C2600x.m6681a(relativeLayout, gradientDrawable);
        this.f5685l = new RelativeLayout(getContext());
        C2600x.m6679a(this.f5685l);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(12);
        relativeLayout.addView(this.f5685l, layoutParams);
        this.f5686m = new C2305k(getContext(), a, this.f5680g.m4363g().m4270f(), c1789a);
        this.f5686m.m5930a(this.f5680g.m4363g().m4265a(), true, 22, -1);
        this.f5686m.m5932b(this.f5680g.m4363g().m4268d(), false, 14, -1);
        this.f5686m.m5933c(this.f5680g.m4363g().m4271g(), false, 14, -1);
        this.f5686m.m5934d(this.f5680g.m4363g().m4269e(), false, 14, -1);
        this.f5685l.addView(this.f5686m, new LayoutParams(-1, -2));
        ImageView c2299f = new C2299f(getContext());
        layoutParams = new LayoutParams(f5675b, f5675b);
        layoutParams.addRule(2, this.f5685l.getId());
        c2299f.setLayoutParams(layoutParams);
        c2299f.setFullCircleCorners(this.f5680g.m4363g().m4270f().equals(C1871a.PAGE_POST));
        relativeLayout.addView(c2299f);
        new C2252d(c2299f).m5772a(f5675b, f5675b).m5775a(this.f5680g.m4362f().m4329b());
        m6074c(this.f5692s);
    }

    /* renamed from: b */
    private void m6073b(C1996c c1996c, C1994a c1994a) {
        View a;
        boolean z = getWidth() >= f5679f && getHeight() >= f5679f;
        if (z) {
            String j;
            C2603b c2603b;
            int i;
            if (c1994a == C1994a.REPORT) {
                j = C1993a.m4797j(getContext());
                c2603b = C2603b.REPORT_AD;
                i = -552389;
            } else {
                j = C1993a.m4796i(getContext());
                c2603b = C2603b.HIDE_AD;
                i = -13272859;
            }
            a = new C2190a(getContext()).m5619a(j).m5622b(C1993a.m4798k(getContext())).m5624c(c1996c.m4815b()).m5620a(false).m5618a(c2603b).m5616a(i).m5623b(false).m5625c(false).m5627e(false).m5621a();
        } else {
            a = getAdHiddenViewTextOnly();
        }
        C2600x.m6680a(a, -1);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13);
        a.setLayoutParams(layoutParams);
        removeAllViews();
        addView(a);
    }

    /* renamed from: c */
    private void m6074c(boolean z) {
        LayoutParams layoutParams = (LayoutParams) this.f5686m.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(f5676c, f5676c);
        if (z) {
            layoutParams.rightMargin = 0;
            layoutParams2.topMargin = f5677d;
            layoutParams2.rightMargin = f5677d;
            layoutParams2.addRule(11);
            layoutParams2.addRule(10);
            addView(this.f5688o, layoutParams2);
            return;
        }
        layoutParams.rightMargin = f5678e;
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        this.f5685l.addView(this.f5688o, layoutParams2);
    }

    /* renamed from: e */
    private void m6075e() {
        if (this.f5690q && this.f5691r) {
            this.f5682i.m6771a(this.f5684k);
            this.f5684k.put("touch", C2581k.m6645a(this.f5683j.m6676e()));
            this.f5684k.put("is_cyoa", Boolean.TRUE.toString());
            this.f5681h.mo5486o(this.f5680g.mo5384a(), this.f5684k);
        }
    }

    private View getAdHiddenViewTextOnly() {
        TextView textView = new TextView(getContext());
        C2600x.m6687a(textView, true, 14);
        textView.setText(C1993a.m4798k(getContext()));
        textView.setGravity(17);
        return textView;
    }

    /* renamed from: a */
    public void m6076a(int i) {
        this.f5684k.put("ad_intro_position", String.valueOf(i));
    }

    /* renamed from: a */
    public void m6077a(C1996c c1996c, C1994a c1994a) {
        this.f5693t = true;
        this.f5695v = c1996c;
        this.f5696w = c1994a;
        m6073b(c1996c, c1994a);
    }

    /* renamed from: a */
    public void m6078a(boolean z) {
        this.f5686m.m5931a(z);
    }

    /* renamed from: a */
    public void m6079a(boolean z, int i) {
        float f = 0.99f;
        if (this.f5689p != null) {
            this.f5689p.cancel();
        }
        float f2 = z ? 1.01f : 0.99f;
        if (!z) {
            f = 1.01f;
        }
        r1 = new PropertyValuesHolder[2];
        r1[0] = PropertyValuesHolder.ofFloat("scaleX", new float[]{f2, f});
        r1[1] = PropertyValuesHolder.ofFloat("scaleY", new float[]{f2, f});
        this.f5689p = ObjectAnimator.ofPropertyValuesHolder(this, r1);
        this.f5689p.setInterpolator(new FastOutLinearInInterpolator());
        this.f5689p.setDuration((long) i);
        this.f5689p.setRepeatCount(-1);
        this.f5689p.setRepeatMode(2);
        this.f5689p.start();
        this.f5694u = false;
    }

    /* renamed from: a */
    public boolean m6080a() {
        return this.f5693t;
    }

    /* renamed from: b */
    public void m6081b() {
        if (this.f5689p != null && !this.f5694u) {
            if (VERSION.SDK_INT >= 19) {
                this.f5689p.pause();
            } else {
                this.f5689p.cancel();
            }
        }
    }

    /* renamed from: b */
    public void m6082b(boolean z) {
        int i = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.width = z ? -1 : 0;
        if (!z) {
            i = -1;
        }
        layoutParams.height = i;
    }

    /* renamed from: c */
    public void m6083c() {
        if (this.f5689p != null && !this.f5694u) {
            if (VERSION.SDK_INT >= 19) {
                this.f5689p.resume();
            } else {
                this.f5689p.start();
            }
        }
    }

    /* renamed from: d */
    public void m6084d() {
        if (this.f5689p != null) {
            this.f5689p.cancel();
        }
        this.f5694u = true;
    }

    public C1887q getAdDataBundle() {
        return this.f5680g;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && m6080a()) {
            removeAllViews();
            m6073b(this.f5695v, this.f5696w);
        }
    }

    public void setAdReportingFlowListener(C2192b c2192b) {
        this.f5686m.setAdReportingFlowListener(c2192b);
    }

    public void setShouldPlayButtonOnTop(boolean z) {
        if (z != this.f5692s) {
            this.f5692s = z;
            C2600x.m6689b(this.f5688o);
            m6074c(this.f5692s);
        }
    }

    public void setViewability(boolean z) {
        this.f5690q = z;
        m6075e();
    }
}
