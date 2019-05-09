package com.facebook.ads.internal.view.p063f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1884n;
import com.facebook.ads.internal.adapters.p030b.C1885o;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.component.C2299f;
import com.facebook.ads.internal.view.component.C2303j;
import com.facebook.ads.internal.view.p019c.C1802e;
import com.facebook.ads.internal.view.p019c.C2252d;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.lang.ref.WeakReference;

/* renamed from: com.facebook.ads.internal.view.f.a */
public class C2342a extends RelativeLayout {
    /* renamed from: a */
    public static final int f5608a = ((int) (72.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5609b = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: c */
    private static final int f5610c = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: d */
    private static final LayoutParams f5611d = new LayoutParams(-1, -1);
    /* renamed from: e */
    private final C1885o f5612e;
    /* renamed from: f */
    private C2299f f5613f = new C2299f(getContext());
    /* renamed from: g */
    private C2303j f5614g;
    /* renamed from: h */
    private LinearLayout f5615h;

    /* renamed from: com.facebook.ads.internal.view.f.a$a */
    private static class C2341a implements C1802e {
        /* renamed from: a */
        final WeakReference<ImageView> f5607a;

        private C2341a(ImageView imageView) {
            this.f5607a = new WeakReference(imageView);
        }

        /* renamed from: a */
        public void mo5349a(boolean z) {
            if (!z && this.f5607a.get() != null) {
                ((ImageView) this.f5607a.get()).setVisibility(8);
            }
        }
    }

    public C2342a(Context context, C1885o c1885o) {
        super(context);
        this.f5612e = c1885o;
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        C2600x.m6680a(this.f5613f, 0);
        this.f5613f.setRadius(50);
        new C2252d(this.f5613f).m5771a().m5775a(this.f5612e.m4348b().m4329b());
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(f5608a, f5608a);
        this.f5614g = new C2303j(getContext(), this.f5612e.m4351e().m4212a(), true, false, true);
        this.f5614g.m5924a(this.f5612e.m4349c().m4265a(), this.f5612e.m4349c().m4266b(), null, false, true);
        this.f5614g.getDescriptionTextView().setAlpha(0.8f);
        this.f5614g.setAlignment(17);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(0, f5610c, 0, f5610c / 2);
        this.f5615h = new LinearLayout(getContext());
        this.f5615h.setGravity(17);
        this.f5615h.setPadding(f5610c, f5610c / 2, f5610c, f5610c / 2);
        ViewGroup.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(0, f5610c / 2, 0, 0);
        C1884n j = this.f5612e.m4352f().m4248j();
        TextView textView = new TextView(getContext());
        textView.setTextColor(-1);
        C2600x.m6687a(textView, false, 16);
        textView.setText(j.m4337d());
        ViewGroup.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        ImageView imageView = new ImageView(getContext());
        new C2252d(imageView).m5771a().m5773a(new C2341a(imageView)).m5775a(j.m4335b());
        ViewGroup.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(f5609b, f5609b);
        layoutParams5.setMargins(0, 0, f5610c / 2, 0);
        this.f5615h.addView(imageView, layoutParams5);
        this.f5615h.addView(textView, layoutParams4);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        C2600x.m6681a(this.f5615h, gradientDrawable);
        linearLayout.addView(this.f5613f, layoutParams);
        linearLayout.addView(this.f5614g, layoutParams2);
        linearLayout.addView(this.f5615h, layoutParams3);
        C2600x.m6680a((View) this, -14473425);
        addView(linearLayout, f5611d);
        m6026a(this.f5613f, IronSourceConstants.REWARDED_VIDEO_DAILY_CAPPED);
        m6026a(this.f5614g, 170);
        m6026a(this.f5615h, FacebookRequestErrorClassification.EC_INVALID_TOKEN);
    }

    /* renamed from: a */
    private void m6026a(View view, int i) {
        view.setTranslationY((float) i);
        view.setScaleY(0.75f);
        view.setScaleX(0.75f);
        view.animate().translationYBy((float) (-i)).scaleX(1.0f).scaleY(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator(2.0f));
    }
}
