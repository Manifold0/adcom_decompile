// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import java.util.Map;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;
import com.google.android.gms.ads.VideoController;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public class UnifiedNativeAdMapper
{
    private Bundle extras;
    private VideoController zzbkz;
    private String zzceo;
    private String zzdfq;
    private List<NativeAd.Image> zzdfr;
    private NativeAd.Image zzdfs;
    private String zzdft;
    private String zzdfu;
    private Double zzdfv;
    private String zzdfw;
    private String zzdfx;
    private boolean zzdfy;
    private View zzdfz;
    private View zzdga;
    private Object zzdgb;
    private boolean zzdgc;
    private boolean zzdgd;
    
    public UnifiedNativeAdMapper() {
        this.extras = new Bundle();
    }
    
    public View getAdChoicesContent() {
        return this.zzdfz;
    }
    
    public final String getAdvertiser() {
        return this.zzdfu;
    }
    
    public final String getBody() {
        return this.zzceo;
    }
    
    public final String getCallToAction() {
        return this.zzdft;
    }
    
    public final Bundle getExtras() {
        return this.extras;
    }
    
    public final String getHeadline() {
        return this.zzdfq;
    }
    
    public final NativeAd.Image getIcon() {
        return this.zzdfs;
    }
    
    public final List<NativeAd.Image> getImages() {
        return this.zzdfr;
    }
    
    public final boolean getOverrideClickHandling() {
        return this.zzdgd;
    }
    
    public final boolean getOverrideImpressionRecording() {
        return this.zzdgc;
    }
    
    public final String getPrice() {
        return this.zzdfx;
    }
    
    public final Double getStarRating() {
        return this.zzdfv;
    }
    
    public final String getStore() {
        return this.zzdfw;
    }
    
    public final VideoController getVideoController() {
        return this.zzbkz;
    }
    
    public void handleClick(final View view) {
    }
    
    public boolean hasVideoContent() {
        return this.zzdfy;
    }
    
    public void recordImpression() {
    }
    
    public void setAdChoicesContent(final View zzdfz) {
        this.zzdfz = zzdfz;
    }
    
    public final void setAdvertiser(final String zzdfu) {
        this.zzdfu = zzdfu;
    }
    
    public final void setBody(final String zzceo) {
        this.zzceo = zzceo;
    }
    
    public final void setCallToAction(final String zzdft) {
        this.zzdft = zzdft;
    }
    
    public final void setExtras(final Bundle extras) {
        this.extras = extras;
    }
    
    public void setHasVideoContent(final boolean zzdfy) {
        this.zzdfy = zzdfy;
    }
    
    public final void setHeadline(final String zzdfq) {
        this.zzdfq = zzdfq;
    }
    
    public final void setIcon(final NativeAd.Image zzdfs) {
        this.zzdfs = zzdfs;
    }
    
    public final void setImages(final List<NativeAd.Image> zzdfr) {
        this.zzdfr = zzdfr;
    }
    
    public void setMediaView(final View zzdga) {
        this.zzdga = zzdga;
    }
    
    public final void setOverrideClickHandling(final boolean zzdgd) {
        this.zzdgd = zzdgd;
    }
    
    public final void setOverrideImpressionRecording(final boolean zzdgc) {
        this.zzdgc = zzdgc;
    }
    
    public final void setPrice(final String zzdfx) {
        this.zzdfx = zzdfx;
    }
    
    public final void setStarRating(final Double zzdfv) {
        this.zzdfv = zzdfv;
    }
    
    public final void setStore(final String zzdfw) {
        this.zzdfw = zzdfw;
    }
    
    public void trackViews(final View view, final Map<String, View> map, final Map<String, View> map2) {
    }
    
    public void untrackView(final View view) {
    }
    
    public final void zza(final VideoController zzbkz) {
        this.zzbkz = zzbkz;
    }
    
    public final Object zzbh() {
        return this.zzdgb;
    }
    
    public final void zzl(final Object zzdgb) {
        this.zzdgb = zzdgb;
    }
    
    public final View zzvy() {
        return this.zzdga;
    }
}
