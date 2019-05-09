// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import com.facebook.ads.internal.w.b.j;
import android.widget.ImageView$ScaleType;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.ImageView;
import com.facebook.ads.internal.t.f;

public class k extends f
{
    private static final int a;
    private final ImageView b;
    
    static {
        a = (int)(1.0f * x.b);
    }
    
    public k(final Context context) {
        super(context);
        (this.b = new com.facebook.ads.internal.view.x(context)).setScaleType(ImageView$ScaleType.CENTER_CROP);
        j.a((View)this.b, j.n);
        this.addView((View)this.b, new ViewGroup$LayoutParams(-1, -1));
        x.a((View)this.b, -2130706433);
        this.setPadding(k.a, k.a, k.a, k.a);
    }
    
    public View getAdContentsView() {
        return (View)this.b;
    }
    
    public ImageView getImageCardView() {
        return this.b;
    }
}
