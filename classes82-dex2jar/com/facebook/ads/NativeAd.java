// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.t.f;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.view.View;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import com.facebook.ads.internal.protocol.e;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.adapters.i;
import android.content.Context;

public class NativeAd extends NativeAdBase
{
    protected NativeAd(final Context context, final i i, final d d) {
        super(context, i, d);
        this.a(e.j);
    }
    
    public NativeAd(final Context context, final String s) {
        super(context, s);
        this.a(e.j);
    }
    
    NativeAd(final NativeAdBase nativeAdBase) {
        super(nativeAdBase);
    }
    
    NativeAd(final com.facebook.ads.internal.t.e e) {
        super(e);
    }
    
    String a() {
        return this.f().r();
    }
    
    void a(final NativeAdView.Type type) {
        this.f().a(type.a());
    }
    
    String b() {
        return this.f().s();
    }
    
    VideoAutoplayBehavior c() {
        return VideoAutoplayBehavior.fromInternalAutoplayBehavior(this.f().t());
    }
    
    List<NativeAd> d() {
        if (this.f().u() == null) {
            return null;
        }
        final ArrayList<NativeAd> list = new ArrayList<NativeAd>();
        final Iterator<com.facebook.ads.internal.t.e> iterator = this.f().u().iterator();
        while (iterator.hasNext()) {
            list.add(new NativeAd(iterator.next()));
        }
        return list;
    }
    
    NativeAdView.Type e() {
        if (this.f().x() == null) {
            return null;
        }
        return NativeAdView.Type.a(this.f().x());
    }
    
    public AdCreativeType getAdCreativeType() {
        if (!TextUtils.isEmpty((CharSequence)this.f().r())) {
            return AdCreativeType.VIDEO;
        }
        if (this.f().u() != null && !this.f().u().isEmpty()) {
            return AdCreativeType.CAROUSEL;
        }
        if (this.f().j() != null && !TextUtils.isEmpty((CharSequence)this.f().j().a())) {
            return AdCreativeType.IMAGE;
        }
        return AdCreativeType.UNKNOWN;
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView) {
        this.registerViewForInteraction(view, mediaView, (AdIconView)null);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final ImageView imageView) {
        this.registerViewForInteraction(view, mediaView, imageView, null);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final ImageView imageView, @Nullable final List<View> list) {
        if (imageView != null) {
            com.facebook.ads.internal.t.e.a(this.f().i(), imageView);
        }
        this.registerViewForInteraction(view, mediaView, (MediaView)null, list);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final AdIconView adIconView) {
        this.registerViewForInteraction(view, mediaView, adIconView, null);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final MediaView mediaView2, @Nullable final List<View> list) {
        if (mediaView != null) {
            mediaView.setNativeAd(this);
        }
        if (mediaView2 != null) {
            mediaView2.a(this, false);
        }
        if (list != null) {
            this.f().a(view, mediaView, list);
            return;
        }
        this.f().a(view, mediaView);
    }
    
    public void registerViewForInteraction(final View view, final MediaView mediaView, @Nullable final List<View> list) {
        this.registerViewForInteraction(view, mediaView, (MediaView)null, list);
    }
    
    public enum AdCreativeType
    {
        CAROUSEL, 
        IMAGE, 
        UNKNOWN, 
        VIDEO;
    }
}
