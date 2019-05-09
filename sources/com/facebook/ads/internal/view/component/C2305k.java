package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1873e.C1871a;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p055a.C2192b;

/* renamed from: com.facebook.ads.internal.view.component.k */
public class C2305k extends LinearLayout {
    /* renamed from: a */
    private static final int f5465a = ((int) (14.0f * C2600x.f6420b));
    /* renamed from: b */
    private static final int f5466b = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: c */
    private static final int f5467c = ((int) (10.0f * C2600x.f6420b));
    /* renamed from: d */
    private static final int f5468d = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: e */
    private static final int f5469e = ((int) (17.0f * C2600x.f6420b));
    /* renamed from: f */
    private TextView f5470f = new TextView(getContext());
    /* renamed from: g */
    private TextView f5471g;
    /* renamed from: h */
    private TextView f5472h;
    /* renamed from: i */
    private TextView f5473i;
    /* renamed from: j */
    private ImageView f5474j;
    /* renamed from: k */
    private ImageView f5475k;
    /* renamed from: l */
    private TextView f5476l;
    /* renamed from: m */
    private TextView f5477m;
    /* renamed from: n */
    private TextView f5478n;
    /* renamed from: o */
    private LinearLayout f5479o;
    /* renamed from: p */
    private final LinearLayout f5480p;
    /* renamed from: q */
    private final String f5481q;
    /* renamed from: r */
    private final C1871a f5482r;
    /* renamed from: s */
    private final C1789a f5483s;
    @Nullable
    /* renamed from: t */
    private C2192b f5484t;

    /* renamed from: com.facebook.ads.internal.view.component.k$1 */
    class C23041 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2305k f5464a;

        C23041(C2305k c2305k) {
            this.f5464a = c2305k;
        }

        public void onClick(View view) {
            this.f5464a.f5483s.mo5337a(this.f5464a.f5481q, false, this.f5464a.f5484t);
        }
    }

    public C2305k(Context context, String str, C1871a c1871a, C1789a c1789a) {
        super(context);
        setOrientation(1);
        this.f5481q = str;
        this.f5482r = c1871a;
        this.f5483s = c1789a;
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = f5466b;
        this.f5470f.setLayoutParams(layoutParams);
        addView(this.f5470f);
        this.f5480p = new LinearLayout(context);
        this.f5480p.setOrientation(0);
        this.f5480p.setGravity(16);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = f5466b / 2;
        addView(this.f5480p, layoutParams);
        LinearLayout linearLayout = this.f5480p;
        this.f5472h = new TextView(getContext());
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = f5466b / 2;
        this.f5472h.setLayoutParams(layoutParams2);
        this.f5474j = new ImageView(getContext());
        this.f5474j.setScaleType(ScaleType.FIT_CENTER);
        this.f5474j.setColorFilter(-1);
        this.f5474j.setImageBitmap(C2604c.m6697a(C2603b.RATINGS));
        linearLayout.addView(this.f5474j, new LinearLayout.LayoutParams(f5465a, f5465a));
        linearLayout.addView(this.f5472h);
        this.f5476l = m5926a();
        this.f5480p.addView(this.f5476l);
        LinearLayout linearLayout2 = this.f5480p;
        this.f5473i = new TextView(getContext());
        this.f5473i.setEllipsize(TruncateAt.END);
        this.f5473i.setMaxLines(1);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = f5466b / 2;
        this.f5473i.setLayoutParams(layoutParams);
        this.f5475k = new ImageView(getContext());
        this.f5475k.setScaleType(ScaleType.FIT_CENTER);
        this.f5475k.setColorFilter(-1);
        this.f5475k.setImageBitmap(C2604c.m6697a(this.f5482r.equals(C1871a.CONTEXTUAL_APP) ? C2603b.GOOGLE : C2603b.GLOBE));
        linearLayout2.addView(this.f5475k, new LinearLayout.LayoutParams(f5465a, f5465a));
        linearLayout2.addView(this.f5473i);
        this.f5478n = m5926a();
        this.f5480p.addView(this.f5478n);
        linearLayout = this.f5480p;
        this.f5471g = new TextView(getContext());
        this.f5471g.setEllipsize(TruncateAt.END);
        this.f5471g.setMaxLines(1);
        this.f5471g.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(this.f5471g);
        this.f5477m = m5926a();
        this.f5480p.addView(this.f5477m);
        linearLayout = this.f5480p;
        this.f5479o = new LinearLayout(getContext());
        this.f5479o.setOrientation(0);
        this.f5479o.setGravity(16);
        linearLayout.addView(this.f5479o, new LinearLayout.LayoutParams(-2, -1));
        View imageView = new ImageView(getContext());
        imageView.setImageBitmap(C2604c.m6697a(C2603b.INFO_ICON));
        imageView.setColorFilter(-1);
        this.f5479o.addView(imageView, new LinearLayout.LayoutParams(f5465a, f5465a));
        imageView = new ImageView(getContext());
        imageView.setImageBitmap(C2604c.m6697a(C2603b.AD_CHOICES_ICON));
        imageView.setColorFilter(-1);
        layoutParams2 = new LinearLayout.LayoutParams(f5465a, f5465a);
        layoutParams2.leftMargin = f5467c;
        this.f5479o.addView(imageView, layoutParams2);
        this.f5479o.setOnClickListener(new C23041(this));
        C2600x.m6682a(this, this.f5479o, f5468d, f5469e);
    }

    /* renamed from: a */
    private TextView m5926a() {
        TextView textView = new TextView(getContext());
        textView.setText("·");
        textView.setTextColor(-1);
        C2600x.m6687a(textView, false, 14);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.leftMargin = f5466b;
        layoutParams.rightMargin = f5466b;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /* renamed from: a */
    public void m5930a(String str, boolean z, int i, int i2) {
        this.f5470f.setText(str);
        this.f5470f.setTextColor(i2);
        C2600x.m6687a(this.f5470f, z, i);
        this.f5470f.setMaxLines(2);
        this.f5470f.setEllipsize(TruncateAt.END);
    }

    /* renamed from: a */
    public void m5931a(boolean z) {
        if (z) {
            if (!TextUtils.isEmpty(this.f5472h.getText())) {
                this.f5474j.setVisibility(0);
                this.f5472h.setVisibility(0);
                this.f5476l.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.f5471g.getText())) {
                this.f5471g.setVisibility(0);
                this.f5477m.setVisibility(0);
            }
            this.f5475k.setVisibility(8);
            this.f5473i.setVisibility(8);
            this.f5478n.setVisibility(8);
            return;
        }
        if (!TextUtils.isEmpty(this.f5473i.getText())) {
            this.f5475k.setVisibility(0);
            this.f5473i.setVisibility(0);
            this.f5478n.setVisibility(0);
        }
        this.f5474j.setVisibility(8);
        this.f5472h.setVisibility(8);
        this.f5476l.setVisibility(8);
        this.f5471g.setVisibility(8);
        this.f5477m.setVisibility(8);
    }

    /* renamed from: b */
    public void m5932b(String str, boolean z, int i, int i2) {
        int i3 = 8;
        this.f5472h.setText(str);
        this.f5472h.setTextColor(i2);
        C2600x.m6687a(this.f5472h, z, i);
        this.f5474j.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.f5472h.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.f5476l;
        if (!TextUtils.isEmpty(str)) {
            i3 = 0;
        }
        textView.setVisibility(i3);
    }

    /* renamed from: c */
    public void m5933c(String str, boolean z, int i, int i2) {
        int i3 = 8;
        this.f5473i.setText(str);
        this.f5473i.setTextColor(i2);
        C2600x.m6687a(this.f5473i, z, i);
        this.f5475k.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        this.f5473i.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.f5478n;
        if (!TextUtils.isEmpty(str)) {
            i3 = 0;
        }
        textView.setVisibility(i3);
    }

    /* renamed from: d */
    public void m5934d(String str, boolean z, int i, int i2) {
        int i3 = 8;
        this.f5471g.setText(str);
        this.f5471g.setTextColor(i2);
        C2600x.m6687a(this.f5471g, z, i);
        this.f5471g.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        TextView textView = this.f5477m;
        if (!TextUtils.isEmpty(str)) {
            i3 = 0;
        }
        textView.setVisibility(i3);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            int size = MeasureSpec.getSize(0);
            this.f5480p.measure(size, size);
            size = this.f5480p.getMeasuredWidth();
            int i5 = size - i3;
            if (i5 > 0) {
                this.f5473i.setMaxWidth(this.f5473i.getWidth() - i5);
                this.f5471g.setMaxWidth(this.f5471g.getWidth() - i5);
                return;
            }
            this.f5473i.setMaxWidth(size);
            this.f5471g.setMaxWidth(size);
        }
    }

    public void setAdReportingFlowListener(C2192b c2192b) {
        this.f5484t = c2192b;
    }
}
