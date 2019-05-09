// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class VideoOptions
{
    private final boolean zzuz;
    private final boolean zzva;
    private final boolean zzvb;
    
    private VideoOptions(final Builder builder) {
        this.zzuz = builder.zzuz;
        this.zzva = builder.zzva;
        this.zzvb = builder.zzvb;
    }
    
    public VideoOptions(final zzmu zzmu) {
        this.zzuz = zzmu.zzato;
        this.zzva = zzmu.zzatp;
        this.zzvb = zzmu.zzatq;
    }
    
    public final boolean getClickToExpandRequested() {
        return this.zzvb;
    }
    
    public final boolean getCustomControlsRequested() {
        return this.zzva;
    }
    
    public final boolean getStartMuted() {
        return this.zzuz;
    }
    
    public static final class Builder
    {
        private boolean zzuz;
        private boolean zzva;
        private boolean zzvb;
        
        public Builder() {
            this.zzuz = true;
            this.zzva = false;
            this.zzvb = false;
        }
        
        public final VideoOptions build() {
            return new VideoOptions(this, null);
        }
        
        public final Builder setClickToExpandRequested(final boolean zzvb) {
            this.zzvb = zzvb;
            return this;
        }
        
        public final Builder setCustomControlsRequested(final boolean zzva) {
            this.zzva = zzva;
            return this;
        }
        
        public final Builder setStartMuted(final boolean zzuz) {
            this.zzuz = zzuz;
            return this;
        }
    }
}
