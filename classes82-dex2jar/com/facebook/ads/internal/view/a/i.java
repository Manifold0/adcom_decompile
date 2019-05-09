// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.a;

import android.text.TextUtils;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;

public class i extends LinearLayout
{
    public static final LinearLayout$LayoutParams a;
    private static final int b;
    private static final int c;
    private static final int d;
    private final LinearLayout e;
    private final ImageView f;
    private final TextView g;
    
    static {
        b = (int)(8.0f * x.b);
        c = (int)(14.5 * x.b);
        d = (int)(20.0f * x.b);
        a = new LinearLayout$LayoutParams(-1, -2);
    }
    
    public i(final Context context) {
        super(context);
        (this.f = new ImageView(context)).setColorFilter(-10459280);
        final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(i.d, i.d);
        layoutParams.gravity = 16;
        this.f.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        (this.e = new LinearLayout(context)).setOrientation(1);
        this.e.setPadding(i.b * 2, 0, 0, 0);
        this.e.setLayoutParams((ViewGroup$LayoutParams)i.a);
        x.a(this.g = new TextView(context), true, 16);
        this.g.setTextColor(-14934495);
        this.e.addView((View)this.g, (ViewGroup$LayoutParams)i.a);
        this.setOrientation(0);
        this.addView((View)this.f);
        this.addView((View)this.e);
    }
    
    public void a(final b b, final String text, final String text2) {
        this.f.setImageBitmap(com.facebook.ads.internal.w.c.c.a(b));
        this.g.setText((CharSequence)text);
        if (!TextUtils.isEmpty((CharSequence)text2)) {
            final TextView textView = new TextView(this.getContext());
            x.a(textView, false, 14);
            textView.setTextColor(-10459280);
            textView.setText((CharSequence)text2);
            this.e.addView((View)textView, (ViewGroup$LayoutParams)i.a);
            this.setPadding(0, i.b, 0, i.b);
            return;
        }
        this.setPadding(0, i.c, 0, i.c);
    }
}
