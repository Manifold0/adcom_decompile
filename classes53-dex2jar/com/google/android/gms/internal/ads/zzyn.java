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
import com.google.android.gms.ads.mediation.NativeContentAdMapper;

@zzadh
public final class zzyn extends zzyd
{
    private final NativeContentAdMapper zzbuz;
    
    public zzyn(final NativeContentAdMapper zzbuz) {
        this.zzbuz = zzbuz;
    }
    
    public final String getAdvertiser() {
        return this.zzbuz.getAdvertiser();
    }
    
    public final String getBody() {
        return this.zzbuz.getBody();
    }
    
    public final String getCallToAction() {
        return this.zzbuz.getCallToAction();
    }
    
    public final Bundle getExtras() {
        return this.zzbuz.getExtras();
    }
    
    public final String getHeadline() {
        return this.zzbuz.getHeadline();
    }
    
    public final List getImages() {
        final List<NativeAd.Image> images = this.zzbuz.getImages();
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
        return this.zzbuz.getOverrideClickHandling();
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.zzbuz.getOverrideImpressionRecording();
    }
    
    public final zzlo getVideoController() {
        if (this.zzbuz.getVideoController() != null) {
            return this.zzbuz.getVideoController().zzbc();
        }
        return null;
    }
    
    public final void recordImpression() {
        this.zzbuz.recordImpression();
    }
    
    public final void zzb(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) {
        this.zzbuz.trackViews((View)ObjectWrapper.unwrap(objectWrapper), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper2), (Map<String, View>)ObjectWrapper.unwrap(objectWrapper3));
    }
    
    public final void zzj(final IObjectWrapper objectWrapper) {
        this.zzbuz.handleClick((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final void zzk(final IObjectWrapper objectWrapper) {
        this.zzbuz.trackView((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final IObjectWrapper zzke() {
        return null;
    }
    
    public final zzps zzkf() {
        return null;
    }
    
    public final zzpw zzkg() {
        final NativeAd.Image logo = this.zzbuz.getLogo();
        if (logo != null) {
            return new zzon(logo.getDrawable(), logo.getUri(), logo.getScale());
        }
        return null;
    }
    
    public final void zzl(final IObjectWrapper objectWrapper) {
        this.zzbuz.untrackView((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    public final IObjectWrapper zzmv() {
        final View adChoicesContent = this.zzbuz.getAdChoicesContent();
        if (adChoicesContent == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)adChoicesContent);
    }
    
    public final IObjectWrapper zzmw() {
        final View zzvy = this.zzbuz.zzvy();
        if (zzvy == null) {
            return null;
        }
        return ObjectWrapper.wrap((Object)zzvy);
    }
}
