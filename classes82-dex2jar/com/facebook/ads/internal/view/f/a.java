// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.f;

import java.lang.ref.WeakReference;
import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.facebook.ads.internal.adapters.b.n;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup$LayoutParams;
import com.facebook.ads.internal.view.c.e;
import android.widget.TextView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.ImageView;
import com.facebook.ads.internal.view.c.d;
import android.view.View;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.LinearLayout;
import com.facebook.ads.internal.view.component.j;
import com.facebook.ads.internal.view.component.f;
import com.facebook.ads.internal.adapters.b.o;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;

public class a extends RelativeLayout
{
    public static final int a;
    private static final int b;
    private static final int c;
    private static final RelativeLayout$LayoutParams d;
    private final o e;
    private f f;
    private j g;
    private LinearLayout h;
    
    static {
        b = (int)(x.b * 16.0f);
        c = (int)(x.b * 16.0f);
        a = (int)(72.0f * x.b);
        d = new RelativeLayout$LayoutParams(-1, -1);
    }
    
    public a(final Context context, final o e) {
        super(context);
        this.e = e;
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        x.a((View)(this.f = new f(this.getContext())), 0);
        this.f.setRadius(50);
        new d(this.f).a().a(this.e.b().b());
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.f.a.a, com.facebook.ads.internal.view.f.a.a);
        (this.g = new j(this.getContext(), this.e.e().a(), true, false, true)).a(this.e.c().a(), this.e.c().b(), null, false, true);
        this.g.getDescriptionTextView().setAlpha(0.8f);
        this.g.setAlignment(17);
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams2.setMargins(0, com.facebook.ads.internal.view.f.a.c, 0, com.facebook.ads.internal.view.f.a.c / 2);
        (this.h = new LinearLayout(this.getContext())).setGravity(17);
        this.h.setPadding(com.facebook.ads.internal.view.f.a.c, com.facebook.ads.internal.view.f.a.c / 2, com.facebook.ads.internal.view.f.a.c, com.facebook.ads.internal.view.f.a.c / 2);
        final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(-2, -2);
        linearLayout$LayoutParams3.setMargins(0, com.facebook.ads.internal.view.f.a.c / 2, 0, 0);
        final n j = this.e.f().j();
        final TextView textView = new TextView(this.getContext());
        textView.setTextColor(-1);
        x.a(textView, false, 16);
        textView.setText((CharSequence)j.d());
        final LinearLayout$LayoutParams linearLayout$LayoutParams4 = new LinearLayout$LayoutParams(-2, -2);
        final ImageView imageView = new ImageView(this.getContext());
        new d(imageView).a().a(new a(imageView)).a(j.b());
        final LinearLayout$LayoutParams linearLayout$LayoutParams5 = new LinearLayout$LayoutParams(com.facebook.ads.internal.view.f.a.b, com.facebook.ads.internal.view.f.a.b);
        linearLayout$LayoutParams5.setMargins(0, 0, com.facebook.ads.internal.view.f.a.c / 2, 0);
        this.h.addView((View)imageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams5);
        this.h.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams4);
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(100.0f);
        gradientDrawable.setColor(469762047);
        x.a((View)this.h, (Drawable)gradientDrawable);
        linearLayout.addView((View)this.f, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)this.g, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
        linearLayout.addView((View)this.h, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
        x.a((View)this, -14473425);
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)com.facebook.ads.internal.view.f.a.d);
        this.a((View)this.f, 150);
        this.a((View)this.g, 170);
        this.a((View)this.h, 190);
    }
    
    private void a(final View view, final int n) {
        view.setTranslationY((float)n);
        view.setScaleY(0.75f);
        view.setScaleX(0.75f);
        view.animate().translationYBy((float)(-n)).scaleX(1.0f).scaleY(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)new DecelerateInterpolator(2.0f));
    }
    
    private static class a implements e
    {
        final WeakReference<ImageView> a;
        
        private a(final ImageView imageView) {
            this.a = new WeakReference<ImageView>(imageView);
        }
        
        @Override
        public void a(final boolean b) {
            if (!b && this.a.get() != null) {
                this.a.get().setVisibility(8);
            }
        }
    }
}
