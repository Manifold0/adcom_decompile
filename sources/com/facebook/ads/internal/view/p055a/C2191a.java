package com.facebook.ads.internal.view.p055a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p037f.C1993a;

/* renamed from: com.facebook.ads.internal.view.a.a */
public class C2191a extends RelativeLayout {
    /* renamed from: a */
    private static final int f5063a = ((int) (C2600x.f6420b * 16.0f));
    /* renamed from: b */
    private static final int f5064b = ((int) (8.0f * C2600x.f6420b));
    /* renamed from: c */
    private static final int f5065c = ((int) (44.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5066d = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: e */
    private static final int f5067e = (f5063a - f5066d);
    /* renamed from: f */
    private static final int f5068f = ((int) (75.0f * C2600x.f6420b));
    /* renamed from: g */
    private static final int f5069g = ((int) (25.0f * C2600x.f6420b));
    /* renamed from: h */
    private static final int f5070h = ((int) (45.0f * C2600x.f6420b));
    /* renamed from: i */
    private static final int f5071i = ((int) (15.0f * C2600x.f6420b));
    /* renamed from: j */
    private static final int f5072j = ((int) (C2600x.f6420b * 16.0f));
    @Nullable
    /* renamed from: k */
    private final C2193e f5073k;
    /* renamed from: l */
    private final int f5074l;
    /* renamed from: m */
    private final int f5075m;
    /* renamed from: n */
    private final boolean f5076n;

    /* renamed from: com.facebook.ads.internal.view.a.a$1 */
    class C21881 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2191a f5049a;

        C21881(C2191a c2191a) {
            this.f5049a = c2191a;
        }

        public void onClick(View view) {
            if (this.f5049a.f5073k == null) {
                return;
            }
            if (this.f5049a.f5076n) {
                this.f5049a.f5073k.mo5539b();
            } else {
                this.f5049a.f5073k.mo5538a(true);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.a$2 */
    class C21892 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2191a f5050a;

        C21892(C2191a c2191a) {
            this.f5050a = c2191a;
        }

        public void onClick(View view) {
            if (this.f5050a.f5073k != null) {
                this.f5050a.f5073k.mo5540c();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.a.a$a */
    public static class C2190a {
        /* renamed from: a */
        private final Context f5051a;
        /* renamed from: b */
        private C2193e f5052b;
        /* renamed from: c */
        private String f5053c;
        /* renamed from: d */
        private String f5054d;
        /* renamed from: e */
        private String f5055e;
        /* renamed from: f */
        private C2603b f5056f;
        /* renamed from: g */
        private int f5057g;
        /* renamed from: h */
        private boolean f5058h = true;
        /* renamed from: i */
        private boolean f5059i = true;
        /* renamed from: j */
        private boolean f5060j = true;
        /* renamed from: k */
        private boolean f5061k = true;
        /* renamed from: l */
        private boolean f5062l = true;

        public C2190a(Context context) {
            this.f5051a = context;
        }

        /* renamed from: a */
        public C2190a m5616a(int i) {
            this.f5057g = i;
            return this;
        }

        /* renamed from: a */
        public C2190a m5617a(C2193e c2193e) {
            this.f5052b = c2193e;
            return this;
        }

        /* renamed from: a */
        public C2190a m5618a(C2603b c2603b) {
            this.f5056f = c2603b;
            return this;
        }

        /* renamed from: a */
        public C2190a m5619a(String str) {
            this.f5053c = str;
            return this;
        }

        /* renamed from: a */
        public C2190a m5620a(boolean z) {
            this.f5058h = z;
            return this;
        }

        /* renamed from: a */
        public C2191a m5621a() {
            return new C2191a();
        }

        /* renamed from: b */
        public C2190a m5622b(String str) {
            this.f5054d = str;
            return this;
        }

        /* renamed from: b */
        public C2190a m5623b(boolean z) {
            this.f5059i = z;
            return this;
        }

        /* renamed from: c */
        public C2190a m5624c(String str) {
            this.f5055e = str;
            return this;
        }

        /* renamed from: c */
        public C2190a m5625c(boolean z) {
            this.f5060j = z;
            return this;
        }

        /* renamed from: d */
        public C2190a m5626d(boolean z) {
            this.f5061k = z;
            return this;
        }

        /* renamed from: e */
        public C2190a m5627e(boolean z) {
            this.f5062l = z;
            return this;
        }
    }

    private C2191a(C2190a c2190a) {
        View imageView;
        LayoutParams layoutParams;
        super(c2190a.f5051a);
        this.f5073k = c2190a.f5052b;
        this.f5074l = c2190a.f5059i ? f5068f : f5070h;
        this.f5075m = c2190a.f5059i ? f5069g : f5071i;
        this.f5076n = c2190a.f5061k;
        setClickable(true);
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        if (c2190a.f5058h) {
            imageView = new ImageView(getContext());
            imageView.setPadding(f5066d, f5066d, f5066d, f5066d);
            imageView.setScaleType(ScaleType.FIT_CENTER);
            imageView.setImageBitmap(C2604c.m6697a(C2603b.CROSS));
            imageView.setOnClickListener(new C21881(this));
            layoutParams = new LinearLayout.LayoutParams(f5065c, f5065c);
            layoutParams.setMargins(f5067e, f5067e, f5067e, f5067e);
            linearLayout.addView(imageView, layoutParams);
        }
        imageView = new ImageView(getContext());
        imageView.setPadding(this.f5075m, this.f5075m, this.f5075m, this.f5075m);
        imageView.setImageBitmap(C2604c.m6697a(c2190a.f5056f));
        imageView.setColorFilter(-1);
        layoutParams = new LinearLayout.LayoutParams(this.f5074l, this.f5074l);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(c2190a.f5057g);
        C2600x.m6681a(imageView, gradientDrawable);
        layoutParams.gravity = 17;
        layoutParams.setMargins(f5063a, 0, f5063a, f5063a);
        TextView textView = new TextView(getContext());
        C2600x.m6687a(textView, true, 20);
        textView.setTextColor(-14934495);
        textView.setText(c2190a.f5053c);
        textView.setGravity(17);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(f5063a, 0, f5063a, f5063a);
        TextView textView2 = new TextView(getContext());
        C2600x.m6687a(textView2, false, 16);
        textView2.setTextColor(-10459280);
        textView2.setText(c2190a.f5054d);
        textView2.setGravity(17);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(f5063a, 0, f5063a, f5063a);
        View linearLayout2 = new LinearLayout(getContext());
        linearLayout2.setOrientation(1);
        linearLayout2.setGravity(17);
        linearLayout2.addView(imageView, layoutParams);
        linearLayout2.addView(textView, layoutParams2);
        linearLayout2.addView(textView2, layoutParams3);
        if (c2190a.f5060j) {
            imageView = new C2197f(getContext());
            imageView.m5676a(c2190a.f5055e, C2603b.CHECKMARK);
            imageView.setSelected(true);
            linearLayout2.addView(imageView, new LinearLayout.LayoutParams(-2, -2));
        }
        imageView = getFooterView();
        C2600x.m6679a(linearLayout);
        C2600x.m6679a(linearLayout2);
        C2600x.m6679a(imageView);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(10);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(13);
        layoutParams4.addRule(3, linearLayout.getId());
        layoutParams4.addRule(2, imageView.getId());
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(12);
        layoutParams2.setMargins(f5063a, 0, f5063a, f5063a);
        addView(linearLayout, layoutParams);
        addView(linearLayout2, layoutParams4);
        addView(imageView, layoutParams2);
        imageView.setVisibility(c2190a.f5062l ? 0 : 8);
    }

    private View getFooterView() {
        View imageView = new ImageView(getContext());
        imageView.setImageBitmap(C2604c.m6697a(C2603b.SETTINGS));
        imageView.setColorFilter(-13272859);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(f5072j, f5072j);
        layoutParams.gravity = 17;
        TextView textView = new TextView(getContext());
        C2600x.m6687a(textView, false, 16);
        textView.setTextColor(-13272859);
        textView.setPadding(f5064b, f5064b, f5064b, f5064b);
        textView.setText(C1993a.m4795h(getContext()));
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setOnClickListener(new C21892(this));
        linearLayout.addView(imageView, layoutParams);
        linearLayout.addView(textView, layoutParams2);
        return linearLayout;
    }
}
