package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p055a.C2191a.C2190a;

/* renamed from: com.facebook.ads.internal.view.a.k */
public class C2219k extends C2195c {
    /* renamed from: c */
    private static final int f5147c = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5148d = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5149e = ((int) (44.0f * C2600x.f6420b));
    /* renamed from: f */
    private final ScrollView f5150f;
    /* renamed from: g */
    private final LinearLayout f5151g;
    /* renamed from: h */
    private final ImageView f5152h = new ImageView(getContext());

    /* renamed from: com.facebook.ads.internal.view.a.k$1 */
    class C22141 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2219k f5139a;

        C22141(C2219k c2219k) {
            this.f5139a = c2219k;
        }

        public void onClick(View view) {
            this.f5139a.b.mo5535a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.k$5 */
    class C22185 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2219k f5146a;

        C22185(C2219k c2219k) {
            this.f5146a = c2219k;
        }

        public void onClick(View view) {
            this.f5146a.b.mo5535a();
        }
    }

    public C2219k(Context context, C2085c c2085c, String str, int i, int i2) {
        super(context, c2085c, str);
        this.f5152h.setPadding(f5148d, f5148d, f5148d, f5148d);
        this.f5152h.setColorFilter(-10459280);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5149e, f5149e);
        layoutParams.gravity = 3;
        this.f5152h.setLayoutParams(layoutParams);
        this.f5150f = new ScrollView(getContext());
        this.f5150f.setFillViewport(true);
        C2600x.m6680a(this.f5150f, -218103809);
        this.f5151g = new LinearLayout(getContext());
        this.f5151g.setOrientation(1);
        this.f5151g.setPadding(f5147c, f5147c, f5147c, f5147c);
        this.f5150f.addView(this.f5151g, new FrameLayout.LayoutParams(-1, -1));
        addView(this.f5150f, new LinearLayout.LayoutParams(i, i2));
    }

    /* renamed from: a */
    void mo5542a(C1996c c1996c, C1994a c1994a) {
        View c2213j = new C2213j(getContext(), c1996c, this.b, (c1994a == C1994a.REPORT ? 1 : 0) != 0 ? C2603b.REPORT_AD : C2603b.HIDE_AD);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        this.f5152h.setImageBitmap(C2604c.m6697a(C2603b.BACK_ARROW));
        this.f5152h.setOnClickListener(new C22185(this));
        C2600x.m6683a(this.f5151g);
        this.f5150f.fullScroll(33);
        this.f5151g.removeAllViews();
        this.f5151g.addView(this.f5152h);
        this.f5151g.addView(c2213j, layoutParams);
    }

    /* renamed from: b */
    void mo5543b(C1996c c1996c, C1994a c1994a) {
        String j;
        C2603b c2603b;
        int i;
        this.f5152h.setOnClickListener(null);
        if (c1994a == C1994a.REPORT) {
            j = C1993a.m4797j(getContext());
            c2603b = C2603b.REPORT_AD;
            i = -552389;
        } else {
            j = C1993a.m4788b(getContext());
            c2603b = C2603b.HIDE_AD;
            i = -13272859;
        }
        View a = new C2190a(getContext()).m5617a(this.b).m5619a(j).m5622b(C1993a.m4798k(getContext())).m5624c(c1996c.m4815b()).m5620a(false).m5618a(c2603b).m5616a(i).m5623b(false).m5625c(false).m5621a();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.gravity = 17;
        layoutParams.weight = 1.0f;
        C2600x.m6683a(this.f5151g);
        this.f5150f.fullScroll(33);
        this.f5151g.removeAllViews();
        this.f5151g.addView(a, layoutParams);
    }

    /* renamed from: c */
    void mo5544c() {
        C2600x.m6690c(this);
        C2600x.m6689b(this);
    }

    /* renamed from: d */
    void mo5545d() {
        this.f5152h.setImageBitmap(C2604c.m6697a(C2603b.CROSS));
        this.f5152h.setOnClickListener(new C22141(this));
        final View c2197f = new C2197f(getContext());
        c2197f.m5676a(C1993a.m4788b(getContext()), C2603b.HIDE_AD);
        c2197f.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2219k f5141b;

            public void onClick(View view) {
                c2197f.m5675a();
                this.f5141b.b.mo5536a(C1994a.HIDE);
            }
        });
        final View c2197f2 = new C2197f(getContext());
        c2197f2.m5676a(C1993a.m4792e(getContext()), C2603b.REPORT_AD);
        c2197f2.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2219k f5143b;

            public void onClick(View view) {
                c2197f2.m5675a();
                this.f5143b.b.mo5536a(C1994a.REPORT);
            }
        });
        final View c2197f3 = new C2197f(getContext());
        c2197f3.m5676a(C1993a.m4799l(getContext()), C2603b.AD_CHOICES_ICON);
        c2197f3.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2219k f5145b;

            public void onClick(View view) {
                c2197f3.m5675a();
                this.f5145b.b.mo5541d();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(f5147c, f5147c, f5147c, f5147c);
        layoutParams.gravity = 17;
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, 0);
        layoutParams2.gravity = 17;
        layoutParams2.weight = 1.0f;
        C2600x.m6683a(this.f5151g);
        this.f5151g.removeAllViews();
        this.f5151g.addView(this.f5152h);
        this.f5151g.addView(linearLayout, layoutParams2);
        linearLayout.addView(c2197f, layoutParams);
        linearLayout.addView(c2197f2, layoutParams);
        linearLayout.addView(c2197f3, layoutParams);
    }

    /* renamed from: e */
    boolean mo5546e() {
        return true;
    }
}
