package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.ads.internal.adapters.p030b.C1876h;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.facebook.ads.internal.view.component.e */
public class C2298e extends LinearLayout {
    /* renamed from: a */
    private final int f5439a;
    /* renamed from: b */
    private final int f5440b;
    /* renamed from: c */
    private final int f5441c;
    /* renamed from: d */
    private int f5442d = -1;
    /* renamed from: e */
    private List<GradientDrawable> f5443e;

    public C2298e(Context context, C1876h c1876h, int i) {
        super(context);
        setOrientation(0);
        setGravity(17);
        float f = C2600x.f6420b;
        int i2 = (int) (8.0f * f);
        int i3 = (int) (6.0f * f);
        this.f5441c = (int) (f * 1.0f);
        this.f5439a = c1876h.m4286a(false);
        this.f5440b = ColorUtils.setAlphaComponent(this.f5439a, 128);
        this.f5443e = new ArrayList();
        for (int i4 = 0; i4 < i; i4++) {
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(1);
            gradientDrawable.setSize(i2, i2);
            gradientDrawable.setStroke(this.f5441c, 0);
            View imageView = new ImageView(context);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, i3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(gradientDrawable);
            this.f5443e.add(gradientDrawable);
            addView(imageView);
        }
        m5921a(0);
    }

    /* renamed from: a */
    public void m5921a(int i) {
        if (this.f5442d != i) {
            this.f5442d = i;
            for (int i2 = 0; i2 < this.f5443e.size(); i2++) {
                int i3;
                int i4;
                if (i2 == i) {
                    i3 = this.f5439a;
                    i4 = this.f5439a;
                } else {
                    i4 = 0;
                    i3 = this.f5440b;
                }
                ((GradientDrawable) this.f5443e.get(i2)).setStroke(this.f5441c, i4);
                ((GradientDrawable) this.f5443e.get(i2)).setColor(i3);
                ((GradientDrawable) this.f5443e.get(i2)).invalidateSelf();
            }
        }
    }
}
