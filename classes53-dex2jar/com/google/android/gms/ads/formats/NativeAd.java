// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import android.net.Uri;
import android.graphics.drawable.Drawable;
import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Bundle;

public abstract class NativeAd
{
    public static final String ASSET_ADCHOICES_CONTAINER_VIEW = "1098";
    
    @KeepForSdk
    public abstract void performClick(final Bundle p0);
    
    @KeepForSdk
    public abstract boolean recordImpression(final Bundle p0);
    
    @KeepForSdk
    public abstract void reportTouchEvent(final Bundle p0);
    
    protected abstract Object zzbe();
    
    public abstract static class AdChoicesInfo
    {
        public abstract List<Image> getImages();
        
        public abstract CharSequence getText();
    }
    
    public abstract static class Image
    {
        public abstract Drawable getDrawable();
        
        public abstract double getScale();
        
        public abstract Uri getUri();
    }
}
