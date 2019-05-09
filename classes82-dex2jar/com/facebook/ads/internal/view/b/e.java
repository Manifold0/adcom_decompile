// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.b;

import android.net.Uri;
import android.text.TextUtils;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.text.TextUtils$TruncateAt;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import android.annotation.TargetApi;
import android.widget.LinearLayout;

@TargetApi(19)
public class e extends LinearLayout
{
    private TextView a;
    private TextView b;
    private Drawable c;
    
    public e(final Context context) {
        super(context);
        final float density = this.getResources().getDisplayMetrics().density;
        this.setOrientation(1);
        this.a = new TextView(this.getContext());
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(-1, -2);
        this.a.setTextColor(-16777216);
        this.a.setTextSize(2, 20.0f);
        this.a.setEllipsize(TextUtils$TruncateAt.END);
        this.a.setSingleLine(true);
        this.a.setVisibility(8);
        this.addView((View)this.a, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.b = new TextView(this.getContext());
        final LinearLayout$LayoutParams linearLayout$LayoutParams2 = new LinearLayout$LayoutParams(-1, -2);
        this.b.setAlpha(0.5f);
        this.b.setTextColor(-16777216);
        this.b.setTextSize(2, 15.0f);
        this.b.setCompoundDrawablePadding((int)(density * 5.0f));
        this.b.setEllipsize(TextUtils$TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setVisibility(8);
        this.addView((View)this.b, (ViewGroup$LayoutParams)linearLayout$LayoutParams2);
    }
    
    private Drawable getPadlockDrawable() {
        if (this.c == null) {
            this.c = (Drawable)new BitmapDrawable(this.getContext().getResources(), com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.b));
        }
        return this.c;
    }
    
    public void setSubtitle(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            this.b.setText((CharSequence)null);
            this.b.setVisibility(8);
            return;
        }
        final Uri parse = Uri.parse(s);
        this.b.setText((CharSequence)parse.getHost());
        final TextView b = this.b;
        Drawable padlockDrawable;
        if ("https".equals(parse.getScheme())) {
            padlockDrawable = this.getPadlockDrawable();
        }
        else {
            padlockDrawable = null;
        }
        b.setCompoundDrawablesRelativeWithIntrinsicBounds(padlockDrawable, (Drawable)null, (Drawable)null, (Drawable)null);
        this.b.setVisibility(0);
    }
    
    public void setTitle(final String text) {
        if (TextUtils.isEmpty((CharSequence)text)) {
            this.a.setText((CharSequence)null);
            this.a.setVisibility(8);
            return;
        }
        this.a.setText((CharSequence)text);
        this.a.setVisibility(0);
    }
}
