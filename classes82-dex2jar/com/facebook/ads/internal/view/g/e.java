// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.g;

import com.facebook.ads.internal.view.c.d;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.RelativeLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import com.facebook.ads.internal.view.component.g;

class e extends g
{
    private final ImageView b;
    
    public e(final Context context) {
        super(context);
        (this.b = new ImageView(context)).setAdjustViewBounds(true);
        this.addView((View)this.b, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-2, -1));
    }
    
    public void a(final String s) {
        final d d = new d(this.b);
        d.a();
        d.a(s);
    }
}
