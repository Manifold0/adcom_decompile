// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component;

import android.view.View;
import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import com.facebook.ads.internal.w.b.x;
import android.widget.TextView;
import android.widget.LinearLayout;

public class i extends LinearLayout
{
    private static final int a;
    private static final int b;
    private static final int c;
    private final CircularProgressView d;
    private final TextView e;
    
    static {
        a = (int)(16.0f * x.b);
        b = (int)(14.0f * x.b);
        c = ColorUtils.setAlphaComponent(-1, 77);
    }
    
    public i(final Context context) {
        super(context);
        this.setOrientation(0);
        this.setGravity(16);
        (this.d = new CircularProgressView(context)).setPadding(i.a, i.a, i.a, i.a);
        this.d.setProgress(0.0f);
        this.a(i.c, -1);
        this.e = new TextView(context);
        this.a(false, -1, i.b);
        this.addView((View)this.d);
        this.addView((View)this.e);
    }
    
    public void a(final int n, final int n2) {
        this.d.a(n, n2);
    }
    
    public void a(final boolean b, final int textColor, final int n) {
        x.a(this.e, b, n);
        this.e.setTextColor(textColor);
    }
    
    public void setProgress(final int n) {
        this.d.setProgressWithAnimation((float)n);
    }
    
    public void setText(final String text) {
        this.e.setText((CharSequence)text);
    }
}
