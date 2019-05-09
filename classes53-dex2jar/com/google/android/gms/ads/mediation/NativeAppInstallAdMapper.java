// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public class NativeAppInstallAdMapper extends NativeAdMapper
{
    private String zzbhw;
    private List<NativeAd.Image> zzbhx;
    private String zzbhy;
    private String zzbia;
    private double zzbib;
    private String zzbic;
    private String zzbid;
    private NativeAd.Image zzdfo;
    
    public final String getBody() {
        return this.zzbhy;
    }
    
    public final String getCallToAction() {
        return this.zzbia;
    }
    
    public final String getHeadline() {
        return this.zzbhw;
    }
    
    public final NativeAd.Image getIcon() {
        return this.zzdfo;
    }
    
    public final List<NativeAd.Image> getImages() {
        return this.zzbhx;
    }
    
    public final String getPrice() {
        return this.zzbid;
    }
    
    public final double getStarRating() {
        return this.zzbib;
    }
    
    public final String getStore() {
        return this.zzbic;
    }
    
    public final void setBody(final String zzbhy) {
        this.zzbhy = zzbhy;
    }
    
    public final void setCallToAction(final String zzbia) {
        this.zzbia = zzbia;
    }
    
    public final void setHeadline(final String zzbhw) {
        this.zzbhw = zzbhw;
    }
    
    public final void setIcon(final NativeAd.Image zzdfo) {
        this.zzdfo = zzdfo;
    }
    
    public final void setImages(final List<NativeAd.Image> zzbhx) {
        this.zzbhx = zzbhx;
    }
    
    public final void setPrice(final String zzbid) {
        this.zzbid = zzbid;
    }
    
    public final void setStarRating(final double zzbib) {
        this.zzbib = zzbib;
    }
    
    public final void setStore(final String zzbic) {
        this.zzbic = zzbic;
    }
}
