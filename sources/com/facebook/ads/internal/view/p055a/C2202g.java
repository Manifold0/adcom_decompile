package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.os.Build.VERSION;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p055a.C2191a.C2190a;

/* renamed from: com.facebook.ads.internal.view.a.g */
public class C2202g extends C2195c {
    /* renamed from: c */
    private static final int f5103c = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: d */
    private final RelativeLayout f5104d = new RelativeLayout(getContext());

    /* renamed from: com.facebook.ads.internal.view.a.g$1 */
    class C21981 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2202g f5099a;

        C21981(C2202g c2202g) {
            this.f5099a = c2202g;
        }

        public void onClick(View view) {
            this.f5099a.b.mo5538a(false);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.g$2 */
    class C21992 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2202g f5100a;

        C21992(C2202g c2202g) {
            this.f5100a = c2202g;
        }

        public void onClick(View view) {
            this.f5100a.b.mo5536a(C1994a.HIDE);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.g$3 */
    class C22003 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2202g f5101a;

        C22003(C2202g c2202g) {
            this.f5101a = c2202g;
        }

        public void onClick(View view) {
            this.f5101a.b.mo5536a(C1994a.REPORT);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.g$4 */
    class C22014 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2202g f5102a;

        C22014(C2202g c2202g) {
            this.f5102a = c2202g;
        }

        public void onClick(View view) {
            this.f5102a.b.mo5541d();
        }
    }

    C2202g(Context context, C2085c c2085c, String str, C1921a c1921a, C1789a c1789a) {
        super(context, c2085c, str, c1921a, c1789a);
        addView(this.f5104d, new LayoutParams(-1, -1));
        C2600x.m6680a(this.f5104d, -1728053248);
        this.f5104d.setOnClickListener(new C21981(this));
    }

    /* renamed from: b */
    private static LayoutParams m5677b(boolean z) {
        LayoutParams layoutParams = new LayoutParams(-1, z ? -1 : -2);
        layoutParams.addRule(12);
        return layoutParams;
    }

    /* renamed from: f */
    private void m5678f() {
        if (VERSION.SDK_INT >= 21) {
            Transition transitionSet = new TransitionSet();
            transitionSet.setOrdering(0);
            transitionSet.addTransition(new ChangeBounds()).addTransition(new Explode());
            C2600x.m6685a((ViewGroup) this, transitionSet);
            return;
        }
        C2600x.m6683a((ViewGroup) this);
    }

    /* renamed from: a */
    public void mo5542a(C1996c c1996c, C1994a c1994a) {
        boolean z = c1994a == C1994a.REPORT;
        View c2213j = new C2213j(getContext(), c1996c, this.b, z ? C1993a.m4792e(getContext()) : C1993a.m4788b(getContext()), z ? C2603b.REPORT_AD : C2603b.HIDE_AD);
        c2213j.setClickable(true);
        C2600x.m6680a(c2213j, -1);
        c2213j.setPadding(f5103c * 2, f5103c, f5103c * 2, f5103c);
        m5678f();
        this.f5104d.removeAllViews();
        this.f5104d.addView(c2213j, C2202g.m5677b(false));
    }

    /* renamed from: b */
    public void mo5543b(C1996c c1996c, C1994a c1994a) {
        if (c1994a != C1994a.NONE) {
            boolean z = c1994a == C1994a.REPORT;
            View a = new C2190a(getContext()).m5617a(this.b).m5619a(z ? C1993a.m4797j(getContext()) : C1993a.m4796i(getContext())).m5622b(C1993a.m4798k(getContext())).m5624c(c1996c.m4815b()).m5618a(z ? C2603b.REPORT_AD : C2603b.HIDE_AD).m5616a(z ? -552389 : -13272859).m5626d(this.a).m5621a();
            C2600x.m6680a(a, -1);
            C2600x.m6683a((ViewGroup) this);
            this.f5104d.removeAllViews();
            this.f5104d.addView(a, C2202g.m5677b(true));
        }
    }

    /* renamed from: c */
    public void mo5544c() {
        C2600x.m6690c(this);
        this.f5104d.removeAllViews();
        C2600x.m6689b(this);
    }

    /* renamed from: d */
    public void mo5545d() {
        C1996c d = C1993a.m4791d(getContext());
        View c2210i = new C2210i(getContext());
        c2210i.m5689a(C2603b.HIDE_AD, C1993a.m4788b(getContext()), C1993a.m4790c(getContext()));
        c2210i.setOnClickListener(new C21992(this));
        C1996c g = C1993a.m4794g(getContext());
        View c2210i2 = new C2210i(getContext());
        c2210i2.m5689a(C2603b.REPORT_AD, C1993a.m4792e(getContext()), C1993a.m4793f(getContext()));
        c2210i2.setOnClickListener(new C22003(this));
        View c2210i3 = new C2210i(getContext());
        c2210i3.m5689a(C2603b.AD_CHOICES_ICON, C1993a.m4799l(getContext()), "");
        c2210i3.setOnClickListener(new C22014(this));
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setClickable(true);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(f5103c * 2, f5103c, f5103c * 2, f5103c);
        C2600x.m6680a(linearLayout, -1);
        if (!d.m4817d().isEmpty()) {
            linearLayout.addView(c2210i, layoutParams);
        }
        if (!g.m4817d().isEmpty()) {
            linearLayout.addView(c2210i2, layoutParams);
        }
        linearLayout.addView(c2210i3, layoutParams);
        m5678f();
        this.f5104d.removeAllViews();
        this.f5104d.addView(linearLayout, C2202g.m5677b(false));
    }

    /* renamed from: e */
    boolean mo5546e() {
        return false;
    }
}
