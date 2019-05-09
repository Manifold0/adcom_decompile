// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzac extends zzak
{
    private final BaseImplementation$ResultHolder<Status> zzcq;
    
    public zzac(final BaseImplementation$ResultHolder<Status> zzcq) {
        this.zzcq = zzcq;
    }
    
    @Override
    public final void zza(final zzad zzad) {
        this.zzcq.setResult((Object)zzad.getStatus());
    }
}
