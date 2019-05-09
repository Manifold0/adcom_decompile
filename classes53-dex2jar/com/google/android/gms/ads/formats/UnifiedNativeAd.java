// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.ads.VideoController;
import java.util.List;
import android.os.Bundle;

public abstract class UnifiedNativeAd
{
    public abstract void cancelUnconfirmedClick();
    
    public abstract void destroy();
    
    public abstract NativeAd.AdChoicesInfo getAdChoicesInfo();
    
    public abstract String getAdvertiser();
    
    public abstract String getBody();
    
    public abstract String getCallToAction();
    
    public abstract Bundle getExtras();
    
    public abstract String getHeadline();
    
    public abstract NativeAd.Image getIcon();
    
    public abstract List<NativeAd.Image> getImages();
    
    public abstract String getMediationAdapterClassName();
    
    public abstract String getPrice();
    
    public abstract Double getStarRating();
    
    public abstract String getStore();
    
    public abstract VideoController getVideoController();
    
    @KeepForSdk
    public abstract void performClick(final Bundle p0);
    
    @KeepForSdk
    public abstract boolean recordImpression(final Bundle p0);
    
    @KeepForSdk
    public abstract void reportTouchEvent(final Bundle p0);
    
    public abstract void setUnconfirmedClickListener(final UnconfirmedClickListener p0);
    
    protected abstract Object zzbe();
    
    public abstract Object zzbh();
    
    public interface OnUnifiedNativeAdLoadedListener
    {
        void onUnifiedNativeAdLoaded(final UnifiedNativeAd p0);
    }
    
    public interface UnconfirmedClickListener
    {
        void onUnconfirmedClickCancelled();
        
        void onUnconfirmedClickReceived(final String p0);
    }
}
