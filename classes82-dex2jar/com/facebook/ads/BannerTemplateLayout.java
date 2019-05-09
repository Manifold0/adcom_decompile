// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import java.util.List;
import android.view.View;
import com.facebook.ads.internal.t.a;
import com.facebook.ads.internal.view.h.b;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.t.j;
import android.content.Context;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.h.c;

public class BannerTemplateLayout extends NativeAdLayout
{
    private static final int a;
    private static final int b;
    private final c c;
    
    static {
        a = (int)(x.b * 280.0f);
        b = (int)(x.b * 375.0f);
    }
    
    BannerTemplateLayout(final Context context, final NativeBannerAd nativeBannerAd, final j j) {
        super(context);
        final MediaView mediaView = new MediaView(this.getContext());
        final AdOptionsView adOptionsView = new AdOptionsView(this.getContext(), nativeBannerAd, this);
        adOptionsView.setIconColor(j.c());
        final k a = nativeBannerAd.a().a();
        if (a == k.e) {
            this.c = new b(context, nativeBannerAd.f(), j, a, mediaView, adOptionsView);
            this.setMinWidth(BannerTemplateLayout.a);
        }
        else {
            this.c = new a(context, nativeBannerAd.f(), adOptionsView, null, mediaView, a, j);
            this.setMinWidth(BannerTemplateLayout.a);
            this.setMaxWidth(BannerTemplateLayout.b);
        }
        x.a((View)this, j.b());
        nativeBannerAd.registerViewForInteraction((View)this, mediaView, this.c.getViewsForInteraction());
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1);
        frameLayout$LayoutParams.gravity = 17;
        this.addView(this.c.getView(), (ViewGroup$LayoutParams)frameLayout$LayoutParams);
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.a();
    }
}
