// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbc extends zzed
{
    private final BaseImplementation$ResultHolder<Connections.StartAdvertisingResult> zzcb;
    
    zzbc(final BaseImplementation$ResultHolder<Connections.StartAdvertisingResult> baseImplementation$ResultHolder) {
        this.zzcb = (BaseImplementation$ResultHolder<Connections.StartAdvertisingResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder);
    }
    
    @Override
    public final void zza(final zzez zzez) {
        final Status zzb = zza(zzez.getStatusCode());
        if (zzb.isSuccess()) {
            this.zzcb.setResult((Object)new zzbb(zzb, zzez.getLocalEndpointName()));
            return;
        }
        this.zzcb.setFailedResult(zzb);
    }
}
