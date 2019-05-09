// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.formats;

import java.lang.annotation.Annotation;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class NativeAdOptions
{
    public static final int ADCHOICES_BOTTOM_LEFT = 3;
    public static final int ADCHOICES_BOTTOM_RIGHT = 2;
    public static final int ADCHOICES_TOP_LEFT = 0;
    public static final int ADCHOICES_TOP_RIGHT = 1;
    public static final int ORIENTATION_ANY = 0;
    public static final int ORIENTATION_LANDSCAPE = 2;
    public static final int ORIENTATION_PORTRAIT = 1;
    private final boolean zzvc;
    private final int zzvd;
    private final boolean zzve;
    private final int zzvf;
    private final VideoOptions zzvg;
    
    private NativeAdOptions(final Builder builder) {
        this.zzvc = builder.zzvc;
        this.zzvd = builder.zzvd;
        this.zzve = builder.zzve;
        this.zzvf = builder.zzvf;
        this.zzvg = builder.zzvg;
    }
    
    public final int getAdChoicesPlacement() {
        return this.zzvf;
    }
    
    public final int getImageOrientation() {
        return this.zzvd;
    }
    
    @Nullable
    public final VideoOptions getVideoOptions() {
        return this.zzvg;
    }
    
    public final boolean shouldRequestMultipleImages() {
        return this.zzve;
    }
    
    public final boolean shouldReturnUrlsForImageAssets() {
        return this.zzvc;
    }
    
    public @interface AdChoicesPlacement {
    }
    
    public static final class Builder
    {
        private boolean zzvc;
        private int zzvd;
        private boolean zzve;
        private int zzvf;
        private VideoOptions zzvg;
        
        public Builder() {
            this.zzvc = false;
            this.zzvd = -1;
            this.zzve = false;
            this.zzvf = 1;
        }
        
        public final NativeAdOptions build() {
            return new NativeAdOptions(this, null);
        }
        
        public final Builder setAdChoicesPlacement(@AdChoicesPlacement final int zzvf) {
            this.zzvf = zzvf;
            return this;
        }
        
        public final Builder setImageOrientation(final int zzvd) {
            this.zzvd = zzvd;
            return this;
        }
        
        public final Builder setRequestMultipleImages(final boolean zzve) {
            this.zzve = zzve;
            return this;
        }
        
        public final Builder setReturnUrlsForImageAssets(final boolean zzvc) {
            this.zzvc = zzvc;
            return this;
        }
        
        public final Builder setVideoOptions(final VideoOptions zzvg) {
            this.zzvg = zzvg;
            return this;
        }
    }
}
