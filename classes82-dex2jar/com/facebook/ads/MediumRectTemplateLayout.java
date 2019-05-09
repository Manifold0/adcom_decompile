// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import java.util.List;
import android.view.View;
import com.facebook.ads.internal.t.j;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.t.a;

public class MediumRectTemplateLayout extends NativeAdLayout
{
    private static final int a;
    private static final int b;
    private final a c;
    
    static {
        a = (int)(x.b * 280.0f);
        b = (int)(x.b * 375.0f);
    }
    
    MediumRectTemplateLayout(final Context context, final NativeAd nativeAd, final j j) {
        super(context);
        final MediaView mediaView = new MediaView(this.getContext());
        final MediaView mediaView2 = new MediaView(this.getContext());
        final AdOptionsView adOptionsView = new AdOptionsView(this.getContext(), nativeAd, this);
        adOptionsView.setIconColor(j.c());
        this.c = new a(context, nativeAd.f(), adOptionsView, mediaView2, mediaView, nativeAd.e().a(), j);
        this.setMinWidth(MediumRectTemplateLayout.a);
        this.setMaxWidth(MediumRectTemplateLayout.b);
        x.a((View)this, j.b());
        nativeAd.registerViewForInteraction((View)this, mediaView2, mediaView, this.c.getViewsForInteraction());
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        frameLayout$LayoutParams.gravity = 17;
        this.addView((View)this.c, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.a();
    }
}
