// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.k;
import android.view.ViewGroup$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.b.l;
import android.view.View;
import android.content.Context;

public class NativeBannerAdView
{
    public static View render(final Context context, final NativeBannerAd nativeBannerAd, final Type type) {
        return render(context, nativeBannerAd, type, null);
    }
    
    public static View render(final Context context, final NativeBannerAd nativeBannerAd, final Type type, NativeAdViewAttributes adViewAttributes) {
        l.a(context, "context must be not null");
        l.a(nativeBannerAd, "nativeBannerAd must be not null");
        l.a(type, "type must be not null");
        if (nativeBannerAd.isNativeConfigEnabled()) {
            adViewAttributes = nativeBannerAd.getAdViewAttributes();
        }
        NativeAdViewAttributes nativeAdViewAttributes = adViewAttributes;
        if (adViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeBannerAd.a(type);
        final BannerTemplateLayout bannerTemplateLayout = new BannerTemplateLayout(context, nativeBannerAd, nativeAdViewAttributes.a());
        ((View)bannerTemplateLayout).setLayoutParams(new ViewGroup$LayoutParams(-1, (int)(x.b * type.getHeight())));
        return (View)bannerTemplateLayout;
    }
    
    public enum Type
    {
        HEIGHT_100(k.a), 
        HEIGHT_120(k.b), 
        HEIGHT_50(k.e);
        
        private final k a;
        
        private Type(final k a) {
            this.a = a;
        }
        
        static Type a(final k k) {
            Type height_50 = null;
            if (k == k.e) {
                height_50 = Type.HEIGHT_50;
            }
            else {
                if (k == k.a) {
                    return Type.HEIGHT_100;
                }
                if (k == k.b) {
                    return Type.HEIGHT_120;
                }
            }
            return height_50;
        }
        
        k a() {
            return this.a;
        }
        
        public int getHeight() {
            return this.a.b();
        }
        
        public int getValue() {
            return this.a.b();
        }
        
        public int getWidth() {
            return this.a.a();
        }
    }
}
