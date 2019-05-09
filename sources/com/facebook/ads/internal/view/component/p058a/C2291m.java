package com.facebook.ads.internal.view.component.p058a;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.adapters.p030b.C1880l;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p069c.C2603b;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.view.component.C2295a;
import com.facebook.ads.internal.view.component.C2295a.C2258a;
import java.util.HashMap;

/* renamed from: com.facebook.ads.internal.view.component.a.m */
public class C2291m extends FrameLayout {
    /* renamed from: a */
    private static final int f5409a = ((int) (C2600x.f6420b * 21.0f));
    /* renamed from: b */
    private static final int f5410b = ((int) (C2600x.f6420b * 8.0f));
    /* renamed from: c */
    private static final int f5411c = ((int) (C2600x.f6420b * 3.0f));
    /* renamed from: d */
    private final C2295a f5412d;

    public C2291m(C2279e c2279e, String str, C1876h c1876h, C2258a c2258a) {
        super(c2279e.m5850a());
        View linearLayout = new LinearLayout(c2279e.m5850a());
        addView(linearLayout, new LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        linearLayout.setClickable(false);
        View imageView = new ImageView(c2279e.m5850a());
        imageView.setImageBitmap(C2604c.m6697a(C2603b.BACK_ARROW));
        imageView.setRotation(90.0f);
        imageView.setClickable(false);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1);
        gradientDrawable.setShape(1);
        imageView.setBackgroundDrawable(gradientDrawable);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(f5409a, f5409a);
        layoutParams.bottomMargin = f5410b;
        imageView.setPadding(f5411c, f5411c, f5411c, f5411c);
        layoutParams.gravity = 1;
        linearLayout.addView(imageView, layoutParams);
        imageView = new TextView(c2279e.m5850a());
        imageView.setTextSize(14.0f);
        imageView.setText(str);
        imageView.setTypeface(Typeface.defaultFromStyle(1));
        imageView.setTextColor(c1876h.m4286a(true));
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        layoutParams.bottomMargin = f5410b;
        linearLayout.addView(imageView, layoutParams);
        imageView.setClickable(false);
        this.f5412d = new C2295a(c2279e.m5850a(), true, false, "com.facebook.ads.interstitial.clicked", null, c2279e.m5851b(), c2279e.m5852c(), c2279e.m5854e(), c2279e.m5855f());
        this.f5412d.m5918a(((C1880l) c2279e.m5856g().m4308d().get(0)).m4316b(), c2279e.m5856g().m4307c(), new HashMap(), c2258a);
        addView(this.f5412d, new LayoutParams(-1, -1));
    }

    public boolean performClick() {
        return this.f5412d.performClick();
    }
}
