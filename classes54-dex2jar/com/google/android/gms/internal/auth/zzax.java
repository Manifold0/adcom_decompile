// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import javax.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nonnull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.proxy.ProxyApi;

public final class zzax implements SpatulaHeaderResult
{
    private Status mStatus;
    private String zzci;
    
    public zzax(@Nonnull final Status status) {
        this.mStatus = (Status)Preconditions.checkNotNull((Object)status);
    }
    
    public zzax(@Nonnull final String s) {
        this.zzci = (String)Preconditions.checkNotNull((Object)s);
        this.mStatus = Status.RESULT_SUCCESS;
    }
    
    @Nullable
    @Override
    public final String getSpatulaHeader() {
        return this.zzci;
    }
    
    @Nullable
    public final Status getStatus() {
        return this.mStatus;
    }
}
