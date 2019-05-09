// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.f;
import android.support.annotation.Nullable;
import java.util.List;
import android.view.View;
import com.facebook.ads.internal.protocol.e;
import android.content.Context;

public class NativeBannerAd extends NativeAdBase
{
    public NativeBannerAd(final Context context, final String s) {
        super(context, s);
        this.a(e.k);
    }
    
    NativeBannerAdView.Type a() {
        if (this.f().x() == null) {
            return null;
        }
        return NativeBannerAdView.Type.a(this.f().x());
    }
    
    void a(final NativeBannerAdView.Type type) {
        this.f().a(type.a());
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView) {
        this.registerViewForInteraction(view, mediaView, null);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final List<View> list) {
        if (mediaView != null) {
            mediaView.a(this, true);
        }
        if (list != null) {
            this.f().a(view, mediaView, list);
            return;
        }
        this.f().a(view, mediaView);
    }
}
