// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd;
import java.util.List;

public class NativeContentAdMapper extends NativeAdMapper
{
    private String zzbhw;
    private List<NativeAd.Image> zzbhx;
    private String zzbhy;
    private String zzbia;
    private String zzbim;
    private NativeAd.Image zzdfp;
    
    public final String getAdvertiser() {
        return this.zzbim;
    }
    
    public final String getBody() {
        return this.zzbhy;
    }
    
    public final String getCallToAction() {
        return this.zzbia;
    }
    
    public final String getHeadline() {
        return this.zzbhw;
    }
    
    public final List<NativeAd.Image> getImages() {
        return this.zzbhx;
    }
    
    public final NativeAd.Image getLogo() {
        return this.zzdfp;
    }
    
    public final void setAdvertiser(final String zzbim) {
        this.zzbim = zzbim;
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
    
    public final void setImages(final List<NativeAd.Image> zzbhx) {
        this.zzbhx = zzbhx;
    }
    
    public final void setLogo(final NativeAd.Image zzdfp) {
        this.zzdfp = zzdfp;
    }
}
