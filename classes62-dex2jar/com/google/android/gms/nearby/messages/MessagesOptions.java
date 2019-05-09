// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public class MessagesOptions implements Api$ApiOptions$Optional
{
    @Nullable
    public final String zzff;
    public final boolean zzfg;
    public final int zzfh;
    public final String zzfi;
    public final String zzfj;
    
    private MessagesOptions(final Builder builder) {
        this.zzff = null;
        this.zzfg = false;
        this.zzfh = builder.zzfh;
        this.zzfi = null;
        this.zzfj = null;
    }
    
    public static class Builder
    {
        private int zzfh;
        
        public Builder() {
            this.zzfh = -1;
        }
        
        public MessagesOptions build() {
            return new MessagesOptions(this, null);
        }
        
        public Builder setPermissions(final int zzfh) {
            this.zzfh = zzfh;
            return this;
        }
    }
}
