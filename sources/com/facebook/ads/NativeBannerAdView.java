package com.facebook.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.facebook.ads.internal.p017t.C2118k;
import com.facebook.ads.internal.p025w.p026b.C2582l;
import com.facebook.ads.internal.p025w.p026b.C2600x;

public class NativeBannerAdView {

    public enum Type {
        HEIGHT_50(C2118k.HEIGHT_50),
        HEIGHT_100(C2118k.HEIGHT_100),
        HEIGHT_120(C2118k.HEIGHT_120);
        
        /* renamed from: a */
        private final C2118k f3818a;

        private Type(C2118k c2118k) {
            this.f3818a = c2118k;
        }

        /* renamed from: a */
        static Type m4126a(C2118k c2118k) {
            return c2118k == C2118k.HEIGHT_50 ? HEIGHT_50 : c2118k == C2118k.HEIGHT_100 ? HEIGHT_100 : c2118k == C2118k.HEIGHT_120 ? HEIGHT_120 : null;
        }

        /* renamed from: a */
        C2118k m4127a() {
            return this.f3818a;
        }

        public int getHeight() {
            return this.f3818a.m5382b();
        }

        public int getValue() {
            return this.f3818a.m5382b();
        }

        public int getWidth() {
            return this.f3818a.m5381a();
        }
    }

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type) {
        return render(context, nativeBannerAd, type, null);
    }

    public static View render(Context context, NativeBannerAd nativeBannerAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        C2582l.m6647a(context, "context must be not null");
        C2582l.m6647a(nativeBannerAd, "nativeBannerAd must be not null");
        C2582l.m6647a(type, "type must be not null");
        NativeAdViewAttributes adViewAttributes = nativeBannerAd.isNativeConfigEnabled() ? nativeBannerAd.getAdViewAttributes() : nativeAdViewAttributes;
        if (adViewAttributes == null) {
            adViewAttributes = new NativeAdViewAttributes();
        }
        nativeBannerAd.m4125a(type);
        View bannerTemplateLayout = new BannerTemplateLayout(context, nativeBannerAd, adViewAttributes.m4109a());
        bannerTemplateLayout.setLayoutParams(new LayoutParams(-1, (int) (C2600x.f6420b * ((float) type.getHeight()))));
        return bannerTemplateLayout;
    }
}
