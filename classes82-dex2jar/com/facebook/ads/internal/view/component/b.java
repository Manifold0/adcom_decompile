// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import com.facebook.ads.internal.view.w;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.e;
import android.content.Context;
import com.facebook.ads.internal.view.v;
import android.widget.LinearLayout;

public class b extends LinearLayout
{
    private v a;
    private int b;
    
    public b(final Context context, final e e, final j j, final AdOptionsView adOptionsView) {
        int min = 21;
        super(context);
        this.setOrientation(1);
        this.setVerticalGravity(16);
        (this.a = new v(this.getContext(), 2)).setMinTextSize((float)(j.h() - 2));
        this.a.setText((CharSequence)e.a("headline"));
        j.a(this.a);
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(0);
        linearLayout.setGravity(16);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(0, -2);
        linearLayout$LayoutParams.weight = 1.0f;
        linearLayout.addView((View)this.a, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        linearLayout.addView((View)adOptionsView, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
        if (e.a("headline") != null) {
            min = Math.min(e.a("headline").length(), 21);
        }
        this.b = min;
        this.addView((View)linearLayout, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -2));
        final LinearLayout linearLayout2 = new LinearLayout(context);
        final w w = new w(context);
        w.setText((CharSequence)e.a("social_context"));
        j.b(w);
        linearLayout2.addView((View)w);
        this.addView((View)linearLayout2);
    }
    
    public int getMinVisibleTitleCharacters() {
        return this.b;
    }
    
    public TextView getTitleTextView() {
        return this.a;
    }
}
