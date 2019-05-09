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
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;

@zzadh
public final class zzym extends zzya
{
    private final NativeAppInstallAdMapper zzbuy;
    
    public zzym(final NativeAppInstallAdMapper zzbuy) {
        this.zzbuy = zzbuy;
    }
    
    public final String getBody() {
        return this.zzbuy.getBody();
    }
    
    public final String getCallToAction() {
        return this.zzbuy.getCallToAction();
    }
    
    public final Bundle getExtras() {
        return this.zzbuy.getExtras();
    }
    
    public final String getHeadline() {
        return this.zzbuy.getHeadline();
    }
    
    public final List getImages() {
        final List<NativeAd.Image> images = this.zzbuy.getImages();
        if (images != null) {
            final ArrayList<zzon> list = new ArrayList<zzon>();
            for (final NativeAd.Image image : images) {
                list.add(new zzon(image.getDrawable(), image.getUri(), image.getScale()));
            }
            return list;
        }
        return null;
    }
    
    public final boolean getOverrideClickHandling() {
        return this.zzbuy.getOverrideClickHandling();
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.zzbuy.getOverrideImpressionRecording();
    }
    
    public final String getPrice() {
        return this.zzbuy.getPrice();
    }
    
    public final double getStarRating() {
        return this.zzbuy.getStarRating();
    }
    
    public final String getStore() {
        return this.zzbuy.getStore();
    }
    
    public final zzlo getVideoController() {
        if (this.zzbuy.getVideoController() != null) {
            return this.zzbuy.getVideoController().zzbc();
        }
        return null;
    }
    
    public final void recordImpression() {
        this.zzbuy.recordImpression();
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) {
        this.zzbuy.trackViews((View)ObjectWrapper.unwrap(objectWrapper), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper2), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper3));
    }
    
    public final void zzj(final IObjectWrapper objectWrapper) {
        this.zzbuy.handleClick((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final zzpw zzjz() {
        final NativeAd.Image icon = this.zzbuy.getIcon();
        if (icon != null) {
            return new zzon(icon.getDrawable(), icon.getUri(), icon.getScale());
        }
        return null;
    }
    
    public final void zzk(final IObjectWrapper objectWrapper) {
        this.zzbuy.trackView((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final IObjectWrapper zzke() {
        return null;
    }
    
    public final zzps zzkf() {
        return null;
    }
    
    public final void zzl(final IObjectWrapper objectWrapper) {
        this.zzbuy.untrackView((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final IObjectWrapper zzmv() {
        final View adChoicesContent = this.zzbuy.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)adChoicesContent);
    }
    
    public final IObjectWrapper zzmw() {
        final View zzvy = this.zzbuy.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)zzvy);
    }
}
