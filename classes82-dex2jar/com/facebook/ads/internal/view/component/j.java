// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.text.TextUtils;
import android.support.annotation.Nullable;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import android.text.TextUtils$TruncateAt;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.adapters.b.h;
import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;
import android.widget.LinearLayout;

public class j extends LinearLayout
{
    private static final float a;
    private static final int b;
    private static final int c;
    private final TextView d;
    private final TextView e;
    private final TextView f;
    
    static {
        a = Resources.getSystem().getDisplayMetrics().density;
        b = (int)(6.0f * j.a);
        c = (int)(8.0f * j.a);
    }
    
    public j(final Context context, final h h, final boolean b, final int n, final int n2, final int n3) {
        super(context);
        this.setOrientation(1);
        x.a(this.d = new TextView(context), true, n);
        this.d.setTextColor(h.c(b));
        this.d.setEllipsize(TextUtils$TruncateAt.END);
        this.d.setLineSpacing((float)j.b, 1.0f);
        (this.f = new TextView(context)).setTextColor(h.a(b));
        x.a(this.e = new TextView(context), false, n2);
        this.e.setTextColor(h.b(b));
        this.e.setEllipsize(TextUtils$TruncateAt.END);
        this.e.setLineSpacing((float)j.b, 1.0f);
        this.addView((View)this.d, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -2));
        this.addView((View)this.f, (ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -2));
        this.f.setVisibility(8);
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        linearLayout$LayoutParams.setMargins(0, n3, 0, 0);
        this.addView((View)this.e, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    public j(final Context context, final h h, final boolean b, final boolean b2, final boolean b3) {
        int n;
        if (b2) {
            n = 18;
        }
        else {
            n = 22;
        }
        int n2;
        if (b2) {
            n2 = 14;
        }
        else {
            n2 = 16;
        }
        int c;
        if (b3) {
            c = j.c / 2;
        }
        else {
            c = j.c;
        }
        this(context, h, b, n, n2, c);
    }
    
    public void a(String text, String text2, @Nullable final String text3, final boolean b, final boolean b2) {
        boolean b3 = false;
        final int n = 1;
        boolean b4;
        if (!TextUtils.isEmpty((CharSequence)text)) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        if (!TextUtils.isEmpty((CharSequence)text2)) {
            b3 = true;
        }
        final TextView d = this.d;
        if (!b4) {
            text = text2;
        }
        d.setText((CharSequence)text);
        if (text3 != null) {
            this.f.setText((CharSequence)text3);
        }
        final TextView e = this.e;
        if (!b4) {
            text2 = "";
        }
        e.setText((CharSequence)text2);
        if (!b4 || !b3) {
            final TextView d2 = this.d;
            int maxLines;
            if (b) {
                maxLines = 2;
            }
            else if (b2) {
                maxLines = 4;
            }
            else {
                maxLines = 3;
            }
            d2.setMaxLines(maxLines);
            return;
        }
        final TextView d3 = this.d;
        int maxLines2;
        if (b) {
            maxLines2 = 1;
        }
        else {
            maxLines2 = 2;
        }
        d3.setMaxLines(maxLines2);
        final TextView e2 = this.e;
        int maxLines3;
        if (b) {
            maxLines3 = n;
        }
        else if (b2) {
            maxLines3 = 3;
        }
        else {
            maxLines3 = 2;
        }
        e2.setMaxLines(maxLines3);
    }
    
    public void a(final boolean b, final int gravity) {
        if (b) {
            this.f.setGravity(gravity);
            this.f.setVisibility(0);
            return;
        }
        this.f.setVisibility(8);
    }
    
    public TextView getDescriptionTextView() {
        return this.e;
    }
    
    public TextView getTitleTextView() {
        return this.d;
    }
    
    public void setAlignment(final int n) {
        this.d.setGravity(n);
        this.e.setGravity(n);
    }
    
    public void setDescriptionGravity(final int gravity) {
        this.e.setGravity(gravity);
    }
    
    public void setTitleGravity(final int gravity) {
        this.d.setGravity(gravity);
    }
}
