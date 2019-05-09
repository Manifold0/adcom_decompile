// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.adapters.b.l;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.LinearLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.widget.ImageView;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.component.a;
import android.widget.FrameLayout;

public class m extends FrameLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private final a d;
    
    static {
        a = (int)(x.b * 21.0f);
        b = (int)(x.b * 8.0f);
        c = (int)(x.b * 3.0f);
    }
    
    public m(final e e, final String text, final h h, final a.a a) {
        super(e.a());
        final LinearLayout linearLayout = new LinearLayout(e.a());
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        linearLayout.setOrientation(1);
        linearLayout.setClickable(false);
        final ImageView imageView = new ImageView(e.a());
        imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.m));
        imageView.setRotation(90.0f);
        imageView.setClickable(false);
        final GradientDrawable backgroundDrawable = new GradientDrawable();
        backgroundDrawable.setColor(-1);
        backgroundDrawable.setShape(1);
        imageView.setBackgroundDrawable((Drawable)backgroundDrawable);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(m.a, m.a);
        linearLayout$LayoutParams.bottomMargin = m.b;
        imageView.setPadding(m.c, m.c, m.c, m.c);
        linearLayout$LayoutParams.gravity = 1;
        linearLayout.addView((View)imageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        final TextView textView = new TextView(e.a());
        textView.setTextSize(14.0f);
        textView.setText((CharSequence)text);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        textView.setTextColor(h.a(true));
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams2.gravity = 1;
        linearLayout$LayoutParams2.bottomMargin = m.b;
        linearLayout.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        textView.setClickable(false);
        (this.d = new a(e.a(), true, false, "com.facebook.ads.interstitial.clicked", null, e.b(), e.c(), e.e(), e.f())).a(e.g().d().get(0).b(), e.g().c(), new HashMap<String, String>(), a);
        this.addView((View)this.d, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
    }
    
    public boolean performClick() {
        return this.d.performClick();
    }
}
