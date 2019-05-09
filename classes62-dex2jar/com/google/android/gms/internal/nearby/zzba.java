// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzba extends zzea
{
    private final BaseImplementation$ResultHolder<Status> zzcb;
    
    zzba(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) {
        this.zzcb = (BaseImplementation$ResultHolder<Status>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder);
    }
    
    @Override
    public final void zzc(final int n) {
        final Status zzb = zza(n);
        if (zzb.isSuccess()) {
            this.zzcb.setResult((Object)zzb);
            return;
        }
        this.zzcb.setFailedResult(zzb);
    }
}
