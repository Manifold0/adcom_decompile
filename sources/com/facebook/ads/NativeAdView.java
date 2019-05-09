package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.facebook.ads.internal.p017t.C2118k;
import com.facebook.ads.internal.p025w.p026b.C2582l;
import com.facebook.ads.internal.p025w.p026b.C2600x;

public class NativeAdView {

    public enum Type {
        HEIGHT_300(C2118k.HEIGHT_300),
        HEIGHT_400(C2118k.HEIGHT_400);
        
        /* renamed from: a */
        private final C2118k f3800a;

        private Type(C2118k c2118k) {
            this.f3800a = c2118k;
        }

        /* renamed from: a */
        static Type m4107a(C2118k c2118k) {
            return c2118k == C2118k.HEIGHT_300 ? HEIGHT_300 : c2118k == C2118k.HEIGHT_400 ? HEIGHT_400 : null;
        }

        /* renamed from: a */
        C2118k m4108a() {
            return this.f3800a;
        }

        public int getHeight() {
            return this.f3800a.m5382b();
        }

        public int getValue() {
            return this.f3800a.m5382b();
        }

        public int getWidth() {
            return this.f3800a.m5381a();
        }
    }

    public static View render(Context context, NativeAd nativeAd, Type type) {
        return render(context, nativeAd, type, null);
    }

    public static View render(Context context, NativeAd nativeAd, Type type, @Nullable NativeAdViewAttributes nativeAdViewAttributes) {
        C2582l.m6647a(context, "context must be not null");
        C2582l.m6647a(nativeAd, "nativeAd must be not null");
        C2582l.m6647a(type, "type must be not null");
        NativeAdViewAttributes adViewAttributes = nativeAd.isNativeConfigEnabled() ? nativeAd.getAdViewAttributes() : nativeAdViewAttributes;
        if (adViewAttributes == null) {
            adViewAttributes = new NativeAdViewAttributes();
        }
        nativeAd.m4082a(type);
        View mediumRectTemplateLayout = new MediumRectTemplateLayout(context, nativeAd, adViewAttributes.m4109a());
        mediumRectTemplateLayout.setLayoutParams(new LayoutParams(-1, (int) (C2600x.f6420b * ((float) type.getHeight()))));
        return mediumRectTemplateLayout;
    }
}
