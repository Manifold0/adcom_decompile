package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p037f.C1993a;
import com.facebook.ads.internal.p037f.C1995b.C1994a;
import com.facebook.ads.internal.p037f.C1996c;
import com.facebook.ads.internal.p051s.C2085c;

/* renamed from: com.facebook.ads.internal.view.a.h */
public class C2209h extends C2195c {
    /* renamed from: c */
    private static final int f5116c = ((int) (4.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5117d = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5118e = ((int) (44.0f * C2600x.f6420b));
    /* renamed from: f */
    private final LinearLayout f5119f;
    /* renamed from: g */
    private final ImageView f5120g = new ImageView(getContext());
    /* renamed from: h */
    private final HorizontalScrollView f5121h;
    /* renamed from: i */
    private final LinearLayout f5122i;

    /* renamed from: com.facebook.ads.internal.view.a.h$1 */
    class C22031 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2209h f5105a;

        C22031(C2209h c2209h) {
            this.f5105a = c2209h;
        }

        public void onClick(View view) {
            this.f5105a.b.mo5535a();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.h$5 */
    class C22075 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2209h f5112a;

        C22075(C2209h c2209h) {
            this.f5112a = c2209h;
        }

        public void onClick(View view) {
            this.f5112a.b.mo5535a();
        }
    }

    public C2209h(Context context, C2085c c2085c, String str, int i, int i2) {
        super(context, c2085c, str);
        this.f5120g.setPadding(f5117d, f5117d, f5117d, f5117d);
        this.f5120g.setScaleType(ScaleType.FIT_CENTER);
        this.f5120g.setColorFilter(-10459280);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5118e, f5118e);
        layoutParams.gravity = 16;
        this.f5122i = new LinearLayout(getContext());
        this.f5122i.setOrientation(0);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.gravity = 17;
        this.f5121h = new HorizontalScrollView(getContext());
        this.f5121h.setHorizontalScrollBarEnabled(false);
        this.f5121h.setLayoutParams(layoutParams2);
        this.f5121h.addView(this.f5122i, layoutParams2);
        this.f5119f = new LinearLayout(getContext());
        this.f5119f.setOrientation(0);
        C2600x.m6680a(this.f5119f, -218103809);
        this.f5119f.setMotionEventSplittingEnabled(false);
        this.f5119f.addView(this.f5120g, layoutParams);
        this.f5119f.addView(this.f5121h, layoutParams2);
        addView(this.f5119f, new FrameLayout.LayoutParams(i, i2));
    }

    /* renamed from: a */
    public void mo5542a(C1996c c1996c, C1994a c1994a) {
        C2600x.m6683a(this.f5119f);
        this.f5120g.setImageBitmap(C2604c.m6697a(C2603b.BACK_ARROW));
        this.f5120g.setOnClickListener(new C22075(this));
        this.f5122i.removeAllViews();
        this.f5121h.fullScroll(17);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, f5116c, f5116c, f5116c);
        for (final C1996c c1996c2 : c1996c.m4817d()) {
            final View c2197f = new C2197f(getContext());
            c2197f.m5676a(c1996c2.m4815b(), null);
            c2197f.setOnClickListener(new OnClickListener(this) {
                /* renamed from: c */
                final /* synthetic */ C2209h f5115c;

                public void onClick(View view) {
                    c2197f.m5675a();
                    this.f5115c.b.mo5537a(c1996c2);
                }
            });
            this.f5122i.addView(c2197f, layoutParams);
        }
    }

    /* renamed from: b */
    public void mo5543b(C1996c c1996c, C1994a c1994a) {
        this.f5120g.setOnClickListener(null);
        TextView textView = new TextView(getContext());
        C2600x.m6687a(textView, true, 14);
        textView.setText(C1993a.m4798k(getContext()));
        textView.setGravity(17);
        C2600x.m6683a(this.f5119f);
        this.f5119f.removeAllViews();
        this.f5119f.addView(textView, new LinearLayout.LayoutParams(-1, -1));
        textView.setClickable(true);
    }

    /* renamed from: c */
    void mo5544c() {
        C2600x.m6690c(this);
        C2600x.m6689b(this);
    }

    /* renamed from: d */
    public void mo5545d() {
        this.f5120g.setImageBitmap(C2604c.m6697a(C2603b.CROSS));
        this.f5120g.setOnClickListener(new C22031(this));
        final View c2197f = new C2197f(getContext());
        c2197f.m5676a(C1993a.m4788b(getContext()), C2603b.HIDE_AD);
        c2197f.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2209h f5107b;

            public void onClick(View view) {
                c2197f.m5675a();
                this.f5107b.b.mo5536a(C1994a.HIDE);
            }
        });
        final View c2197f2 = new C2197f(getContext());
        c2197f2.m5676a(C1993a.m4792e(getContext()), C2603b.REPORT_AD);
        c2197f2.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2209h f5109b;

            public void onClick(View view) {
                c2197f2.m5675a();
                this.f5109b.b.mo5536a(C1994a.REPORT);
            }
        });
        final View c2197f3 = new C2197f(getContext());
        c2197f3.m5676a(C1993a.m4799l(getContext()), C2603b.AD_CHOICES_ICON);
        c2197f3.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2209h f5111b;

            public void onClick(View view) {
                c2197f3.m5675a();
                this.f5111b.b.mo5541d();
            }
        });
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, f5116c, f5116c, f5116c);
        C2600x.m6683a(this.f5119f);
        this.f5122i.removeAllViews();
        this.f5122i.addView(c2197f, layoutParams);
        this.f5122i.addView(c2197f2, layoutParams);
        this.f5122i.addView(c2197f3, layoutParams);
    }

    /* renamed from: e */
    boolean mo5546e() {
        return true;
    }
}
