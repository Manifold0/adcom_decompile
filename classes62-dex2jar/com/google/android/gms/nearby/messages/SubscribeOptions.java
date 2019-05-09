// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;

public final class SubscribeOptions
{
    public static final SubscribeOptions DEFAULT;
    private final Strategy zzfk;
    private final MessageFilter zzfz;
    @Nullable
    private final SubscribeCallback zzga;
    public final boolean zzgb;
    public final int zzgc;
    
    static {
        DEFAULT = new Builder().build();
    }
    
    private SubscribeOptions(final Strategy zzfk, final MessageFilter zzfz, @Nullable final SubscribeCallback zzga, final boolean zzgb, final int zzgc) {
        this.zzfk = zzfk;
        this.zzfz = zzfz;
        this.zzga = zzga;
        this.zzgb = zzgb;
        this.zzgc = zzgc;
    }
    
    @Nullable
    public final SubscribeCallback getCallback() {
        return this.zzga;
    }
    
    public final MessageFilter getFilter() {
        return this.zzfz;
    }
    
    public final Strategy getStrategy() {
        return this.zzfk;
    }
    
    @Override
    public final String toString() {
        final String value = String.valueOf(this.zzfk);
        final String value2 = String.valueOf(this.zzfz);
        return new StringBuilder(String.valueOf(value).length() + 36 + String.valueOf(value2).length()).append("SubscribeOptions{strategy=").append(value).append(", filter=").append(value2).append('}').toString();
    }
    
    public static class Builder
    {
        private Strategy zzfk;
        private MessageFilter zzfz;
        @Nullable
        private SubscribeCallback zzga;
        
        public Builder() {
            this.zzfk = Strategy.DEFAULT;
            this.zzfz = MessageFilter.INCLUDE_ALL_MY_TYPES;
        }
        
        public SubscribeOptions build() {
            return new SubscribeOptions(this.zzfk, this.zzfz, this.zzga, false, 0, null);
        }
        
        public Builder setCallback(final SubscribeCallback subscribeCallback) {
            this.zzga = (SubscribeCallback)Preconditions.checkNotNull((Object)subscribeCallback);
            return this;
        }
        
        public Builder setFilter(final MessageFilter zzfz) {
            this.zzfz = zzfz;
            return this;
        }
        
        public Builder setStrategy(final Strategy zzfk) {
            this.zzfk = zzfk;
            return this;
        }
    }
}
