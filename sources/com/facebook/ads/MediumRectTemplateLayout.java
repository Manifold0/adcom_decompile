package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.p017t.C2097a;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p025w.p026b.C2600x;

public class MediumRectTemplateLayout extends NativeAdLayout {
    /* renamed from: a */
    private static final int f3776a = ((int) (C2600x.f6420b * 280.0f));
    /* renamed from: b */
    private static final int f3777b = ((int) (C2600x.f6420b * 375.0f));
    /* renamed from: c */
    private final C2097a f3778c;

    MediumRectTemplateLayout(Context context, NativeAd nativeAd, C2117j c2117j) {
        super(context);
        MediaView mediaView = new MediaView(getContext());
        MediaView mediaView2 = new MediaView(getContext());
        AdOptionsView adOptionsView = new AdOptionsView(getContext(), nativeAd, this);
        adOptionsView.setIconColor(c2117j.m5367c());
        this.f3778c = new C2097a(context, nativeAd.m4078f(), adOptionsView, mediaView2, mediaView, nativeAd.m4086e().m4108a(), c2117j);
        setMinWidth(f3776a);
        setMaxWidth(f3777b);
        C2600x.m6680a((View) this, c2117j.m5363b());
        nativeAd.registerViewForInteraction((View) this, mediaView2, mediaView, this.f3778c.getViewsForInteraction());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.f3778c, layoutParams);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f3778c.mo5496a();
    }
}
