// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.internal;

import com.google.android.gms.common.internal.Objects;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InternalTokenResult
{
    private String zza;
    
    @KeepForSdk
    public InternalTokenResult(@Nullable final String zza) {
        this.zza = zza;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof InternalTokenResult && Objects.equal((Object)this.zza, (Object)((InternalTokenResult)o).zza);
    }
    
    @Nullable
    @KeepForSdk
    public String getToken() {
        return this.zza;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zza });
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper((Object)this).add("token", (Object)this.zza).toString();
    }
}
