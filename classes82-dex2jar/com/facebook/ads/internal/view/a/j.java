// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import java.util.Iterator;
import android.view.ViewGroup$LayoutParams;
import android.widget.TextView;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageView;
import android.text.TextUtils;
import android.widget.LinearLayout$LayoutParams;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.c.b;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.f.c;
import android.widget.LinearLayout;

public class j extends LinearLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private final c d;
    private final e e;
    
    static {
        a = (int)(40.0f * x.b);
        b = (int)(20.0f * x.b);
        c = (int)(10.0f * x.b);
    }
    
    j(final Context context, final c c, final e e, final b b) {
        this(context, c, e, null, b);
    }
    
    j(final Context context, final c d, final e e, @Nullable String c, final b b) {
        super(context);
        this.d = d;
        this.e = e;
        this.setOrientation(1);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        if (!TextUtils.isEmpty((CharSequence)c)) {
            final ImageView imageView = new ImageView(this.getContext());
            imageView.setColorFilter(-10459280);
            imageView.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.m));
            imageView.setPadding(0, j.c, j.c * 2, j.c);
            final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(j.a, j.a);
            imageView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    j.this.e.a();
                }
            });
            final TextView textView = new TextView(this.getContext());
            textView.setGravity(17);
            textView.setText((CharSequence)c);
            x.a(textView, true, 16);
            textView.setTextColor(-14934495);
            final LinearLayout$LayoutParams linearLayout$LayoutParams3 = new LinearLayout$LayoutParams(-1, -2);
            linearLayout$LayoutParams3.setMargins(0, 0, j.a, 0);
            linearLayout$LayoutParams3.gravity = 17;
            final LinearLayout linearLayout = new LinearLayout(this.getContext());
            linearLayout.setOrientation(0);
            linearLayout.addView((View)imageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
            linearLayout.addView((View)textView, (ViewGroup$LayoutParams)linearLayout$LayoutParams3);
            ((View)linearLayout).setPadding(0, 0, 0, 0);
            final View view = new View(this.getContext());
            view.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, 1));
            x.a(view, -10459280);
            this.addView((View)linearLayout, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
            this.addView(view);
        }
        if (!TextUtils.isEmpty((CharSequence)this.d.c())) {
            c = this.d.c();
            final ImageView imageView2 = new ImageView(this.getContext());
            imageView2.setColorFilter(-10459280);
            final LinearLayout$LayoutParams linearLayout$LayoutParams4 = new LinearLayout$LayoutParams(j.b, j.b);
            linearLayout$LayoutParams4.gravity = 16;
            imageView2.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b));
            final TextView textView2 = new TextView(this.getContext());
            x.a(textView2, true, 14);
            textView2.setTextColor(-10459280);
            final LinearLayout$LayoutParams linearLayout$LayoutParams5 = new LinearLayout$LayoutParams(-1, -2);
            textView2.setText((CharSequence)c);
            textView2.setPadding(j.c, 0, 0, 0);
            final LinearLayout linearLayout2 = new LinearLayout(this.getContext());
            linearLayout2.setOrientation(0);
            linearLayout2.addView((View)imageView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams4);
            linearLayout2.addView((View)textView2, (ViewGroup$LayoutParams)linearLayout$LayoutParams5);
            ((View)linearLayout2).setPadding(0, j.c, 0, j.c);
            this.addView((View)linearLayout2, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        }
        final View a = this.a();
        a.setPadding(0, j.c, 0, 0);
        this.addView(a, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    private View a() {
        final l l = new l(this.getContext());
        for (final c c : this.d.d()) {
            final f f = new f(this.getContext());
            f.a(c.b(), null);
            f.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    f.a();
                    j.this.e.a(c);
                }
            });
            l.addView((View)f);
        }
        return (View)l;
    }
}
