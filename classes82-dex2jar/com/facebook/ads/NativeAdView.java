// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.k;
import android.view.ViewGroup$LayoutParams;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.b.l;
import android.support.annotation.Nullable;
import android.view.View;
import android.content.Context;

public class NativeAdView
{
    public static View render(final Context context, final NativeAd nativeAd, final Type type) {
        return render(context, nativeAd, type, null);
    }
    
    public static View render(final Context context, final NativeAd nativeAd, final Type type, @Nullable NativeAdViewAttributes adViewAttributes) {
        l.a(context, "context must be not null");
        l.a(nativeAd, "nativeAd must be not null");
        l.a(type, "type must be not null");
        if (nativeAd.isNativeConfigEnabled()) {
            adViewAttributes = nativeAd.getAdViewAttributes();
        }
        NativeAdViewAttributes nativeAdViewAttributes = adViewAttributes;
        if (adViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeAd.a(type);
        final MediumRectTemplateLayout mediumRectTemplateLayout = new MediumRectTemplateLayout(context, nativeAd, nativeAdViewAttributes.a());
        ((View)mediumRectTemplateLayout).setLayoutParams(new ViewGroup$LayoutParams(-1, (int)(x.b * type.getHeight())));
        return (View)mediumRectTemplateLayout;
    }
    
    public enum Type
    {
        HEIGHT_300(k.c), 
        HEIGHT_400(k.d);
        
        private final k a;
        
        private Type(final k a) {
            this.a = a;
        }
        
        static Type a(final k k) {
            Type height_300 = null;
            if (k == k.c) {
                height_300 = Type.HEIGHT_300;
            }
            else if (k == k.d) {
                return Type.HEIGHT_400;
            }
            return height_300;
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
