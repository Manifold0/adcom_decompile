// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;

public final class PublishOptions
{
    public static final PublishOptions DEFAULT;
    private final Strategy zzfk;
    @Nullable
    private final PublishCallback zzfl;
    
    static {
        DEFAULT = new Builder().build();
    }
    
    private PublishOptions(final Strategy zzfk, @Nullable final PublishCallback zzfl) {
        this.zzfk = zzfk;
        this.zzfl = zzfl;
    }
    
    @Nullable
    public final PublishCallback getCallback() {
        return this.zzfl;
    }
    
    public final Strategy getStrategy() {
        return this.zzfk;
    }
    
    public static class Builder
    {
        private Strategy zzfk;
        @Nullable
        private PublishCallback zzfl;
        
        public Builder() {
            this.zzfk = Strategy.DEFAULT;
        }
        
        public PublishOptions build() {
            return new PublishOptions(this.zzfk, this.zzfl, null);
        }
        
        public Builder setCallback(final PublishCallback publishCallback) {
            this.zzfl = (PublishCallback)Preconditions.checkNotNull((Object)publishCallback);
            return this;
        }
        
        public Builder setStrategy(final Strategy strategy) {
            this.zzfk = (Strategy)Preconditions.checkNotNull((Object)strategy);
            return this;
        }
    }
}
