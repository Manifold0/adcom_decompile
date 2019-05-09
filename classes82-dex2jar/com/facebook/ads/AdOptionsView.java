// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.w.b.j;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.LinearLayout$LayoutParams;
import com.facebook.ads.internal.w.c.c;
import com.facebook.ads.internal.w.c.b;
import android.widget.ImageView$ScaleType;
import android.support.annotation.Nullable;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AdOptionsView extends LinearLayout
{
    private static final int a;
    private static final int b;
    private final ImageView c;
    private final ImageView d;
    
    static {
        a = (int)(x.b * 23.0f);
        b = (int)(x.b * 4.0f);
    }
    
    public AdOptionsView(final Context context, final NativeAdBase nativeAdBase, @Nullable final NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, nativeAdLayout, Orientation.HORIZONTAL, 23);
    }
    
    public AdOptionsView(final Context context, final NativeAdBase nativeAdBase, @Nullable final NativeAdLayout nativeAdLayout, final Orientation orientation, int max) {
        super(context);
        (this.c = new ImageView(context)).setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.c.setPadding(AdOptionsView.b, AdOptionsView.b, AdOptionsView.b, AdOptionsView.b);
        this.c.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.i));
        (this.d = new ImageView(context)).setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.d.setPadding(AdOptionsView.b, AdOptionsView.b, AdOptionsView.b, AdOptionsView.b);
        this.d.setImageBitmap(com.facebook.ads.internal.w.c.c.a(com.facebook.ads.internal.w.c.b.f));
        int orientation2;
        if (orientation == Orientation.HORIZONTAL) {
            orientation2 = 0;
        }
        else {
            orientation2 = 1;
        }
        this.setOrientation(orientation2);
        this.setIconColor(-10459280);
        max = Math.max(AdOptionsView.a, (int)(x.b * max));
        final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(max, max);
        this.addView((View)this.c, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        this.addView((View)this.d, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
        nativeAdBase.f().a(nativeAdLayout);
        if (nativeAdBase.isAdLoaded() && !nativeAdBase.g().g()) {
            this.setVisibility(8);
            return;
        }
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                nativeAdBase.f().y();
            }
        });
        j.a((View)this, j.o);
    }
    
    public void setIconColor(final int n) {
        this.c.setColorFilter(n);
        this.d.setColorFilter(n);
    }
    
    public void setLayoutParams(final ViewGroup$LayoutParams layoutParams) {
        layoutParams.width = -2;
        layoutParams.height = -2;
        super.setLayoutParams(layoutParams);
    }
    
    public enum Orientation
    {
        HORIZONTAL, 
        VERTICAL;
    }
}
