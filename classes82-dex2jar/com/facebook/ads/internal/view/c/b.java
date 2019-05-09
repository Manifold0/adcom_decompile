// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.c;

import android.graphics.drawable.Drawable;
import com.facebook.ads.internal.w.b.x;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import com.facebook.ads.internal.w.b.j;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.ImageView$ScaleType;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;
import android.widget.FrameLayout;

public class b extends FrameLayout
{
    private final ImageView a;
    private int b;
    private int c;
    
    public b(final Context context) {
        super(context);
        this.a = new ImageView(context);
        this.a();
    }
    
    public b(final Context context, final AttributeSet set) {
        super(context, set);
        this.a = new ImageView(context, set);
        this.a();
    }
    
    public b(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.a = new ImageView(context, set, n);
        this.a();
    }
    
    @TargetApi(21)
    public b(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.a = new ImageView(context, set, n, n2);
        this.a();
    }
    
    private void a() {
        this.a.setScaleType(ImageView$ScaleType.FIT_XY);
        this.addView((View)this.a, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        j.a((View)this.a, j.n);
    }
    
    public void a(final Bitmap bitmap, final Bitmap bitmap2) {
        if (bitmap2 != null) {
            x.a((View)this, (Drawable)new BitmapDrawable(this.getContext().getResources(), bitmap2));
        }
        else {
            x.a((View)this, 0);
        }
        if (bitmap != null) {
            this.b = bitmap.getWidth();
            this.c = bitmap.getHeight();
            this.a.setImageBitmap(Bitmap.createBitmap(bitmap));
            return;
        }
        this.a.setImageDrawable((Drawable)null);
    }
    
    public ImageView getBodyImageView() {
        return this.a;
    }
    
    protected void onLayout(final boolean b, int n, int n2, int n3, int n4) {
        final int n5 = n3 - n;
        final int n6 = n4 - n2;
        if (this.b <= 0 || this.c <= 0) {
            super.onLayout(b, n, n2, n3, n4);
            return;
        }
        final float min = Math.min(n5 / (float)this.b, n6 / (float)this.c);
        n3 = (int)(this.b * min);
        n4 = (int)(min * this.c);
        n += n5 / 2;
        n2 += n6 / 2;
        this.a.layout(n - n3 / 2, n2 - n4 / 2, n + n3 / 2, n2 + n4 / 2);
    }
}
