package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.p017t.C2097a;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p017t.C2118k;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.view.p052h.C2096c;
import com.facebook.ads.internal.view.p052h.C2371b;

public class BannerTemplateLayout extends NativeAdLayout {
    /* renamed from: a */
    private static final int f3718a = ((int) (C2600x.f6420b * 280.0f));
    /* renamed from: b */
    private static final int f3719b = ((int) (C2600x.f6420b * 375.0f));
    /* renamed from: c */
    private final C2096c f3720c;

    BannerTemplateLayout(Context context, NativeBannerAd nativeBannerAd, C2117j c2117j) {
        super(context);
        MediaView mediaView = new MediaView(getContext());
        AdOptionsView adOptionsView = new AdOptionsView(getContext(), nativeBannerAd, this);
        adOptionsView.setIconColor(c2117j.m5367c());
        C2118k a = nativeBannerAd.m4124a().m4127a();
        if (a == C2118k.HEIGHT_50) {
            this.f3720c = new C2371b(context, nativeBannerAd.m4078f(), c2117j, a, mediaView, adOptionsView);
            setMinWidth(f3718a);
        } else {
            this.f3720c = new C2097a(context, nativeBannerAd.m4078f(), adOptionsView, null, mediaView, a, c2117j);
            setMinWidth(f3718a);
            setMaxWidth(f3719b);
        }
        C2600x.m6680a((View) this, c2117j.m5363b());
        nativeBannerAd.registerViewForInteraction(this, mediaView, this.f3720c.getViewsForInteraction());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.f3720c.getView(), layoutParams);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3720c.mo5496a();
    }
}
