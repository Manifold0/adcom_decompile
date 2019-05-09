// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView;
import java.util.ArrayList;
import android.support.v4.graphics.ColorUtils;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.adapters.b.h;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import java.util.List;
import android.widget.LinearLayout;

public class e extends LinearLayout
{
    private final int a;
    private final int b;
    private final int c;
    private int d;
    private List<GradientDrawable> e;
    
    public e(final Context context, final h h, final int n) {
        super(context);
        this.d = -1;
        this.setOrientation(0);
        this.setGravity(17);
        final float b = x.b;
        final int n2 = (int)(8.0f * b);
        final int n3 = (int)(6.0f * b);
        this.c = (int)(b * 1.0f);
        this.a = h.a(false);
        this.b = ColorUtils.setAlphaComponent(this.a, 128);
        this.e = new ArrayList<GradientDrawable>();
        for (int i = 0; i < n; ++i) {
            final GradientDrawable imageDrawable = new GradientDrawable();
            imageDrawable.setShape(1);
            imageDrawable.setSize(n2, n2);
            imageDrawable.setStroke(this.c, 0);
            final ImageView imageView = new ImageView(context);
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, n3, 0);
            layoutParams.gravity = 17;
            imageView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            imageView.setImageDrawable((Drawable)imageDrawable);
            this.e.add(imageDrawable);
            this.addView((View)imageView);
        }
        this.a(0);
    }
    
    public void a(final int d) {
        if (this.d != d) {
            this.d = d;
            for (int i = 0; i < this.e.size(); ++i) {
                int color;
                int a;
                if (i == d) {
                    color = this.a;
                    a = this.a;
                }
                else {
                    color = this.b;
                    a = 0;
                }
                this.e.get(i).setStroke(this.c, a);
                this.e.get(i).setColor(color);
                this.e.get(i).invalidateSelf();
            }
        }
    }
}
