// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.dynamic.ObjectWrapper;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Iterator;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

@zzadh
public final class zzze extends zzyg
{
    private final UnifiedNativeAdMapper zzbvh;
    
    public zzze(final UnifiedNativeAdMapper zzbvh) {
        this.zzbvh = zzbvh;
    }
    
    public final String getAdvertiser() {
        return this.zzbvh.getAdvertiser();
    }
    
    public final String getBody() {
        return this.zzbvh.getBody();
    }
    
    public final String getCallToAction() {
        return this.zzbvh.getCallToAction();
    }
    
    public final Bundle getExtras() {
        return this.zzbvh.getExtras();
    }
    
    public final String getHeadline() {
        return this.zzbvh.getHeadline();
    }
    
    public final List getImages() {
        final List<NativeAd.Image> images = this.zzbvh.getImages();
        final ArrayList<zzon> list = new ArrayList<zzon>();
        if (images != null) {
            for (final NativeAd.Image image : images) {
                list.add(new zzon(image.getDrawable(), image.getUri(), image.getScale()));
            }
        }
        return list;
    }
    
    public final boolean getOverrideClickHandling() {
        return this.zzbvh.getOverrideClickHandling();
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.zzbvh.getOverrideImpressionRecording();
    }
    
    public final String getPrice() {
        return this.zzbvh.getPrice();
    }
    
    public final double getStarRating() {
        if (this.zzbvh.getStarRating() != null) {
            return this.zzbvh.getStarRating();
        }
        return -1.0;
    }
    
    public final String getStore() {
        return this.zzbvh.getStore();
    }
    
    public final zzlo getVideoController() {
        if (this.zzbvh.getVideoController() != null) {
            return this.zzbvh.getVideoController().zzbc();
        }
        return null;
    }
    
    public final void recordImpression() {
        this.zzbvh.recordImpression();
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) {
        this.zzbvh.trackViews((View)ObjectWrapper.unwrap(objectWrapper), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper2), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper3));
    }
    
    public final void zzj(final IObjectWrapper objectWrapper) {
        this.zzbvh.handleClick((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final zzpw zzjz() {
        final NativeAd.Image icon = this.zzbvh.getIcon();
        if (icon != null) {
            return new zzon(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }
    
    public final IObjectWrapper zzke() {
        final Object zzbh = this.zzbvh.zzbh();
        if (zzbh == null) {
            return null;
        }
        return ObjectWrapper.wrap(zzbh);
    }
    
    public final zzps zzkf() {
        return null;
    }
    
    public final void zzl(final IObjectWrapper objectWrapper) {
        this.zzbvh.untrackView((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final IObjectWrapper zzmv() {
        final View adChoicesContent = this.zzbvh.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)adChoicesContent);
    }
    
    public final IObjectWrapper zzmw() {
        final View zzvy = this.zzbvh.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)zzvy);
    }
}
