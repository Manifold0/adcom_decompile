// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth-api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzo extends zzg
{
    private BaseImplementation$ResultHolder<Status> zzap;
    
    zzo(final BaseImplementation$ResultHolder<Status> zzap) {
        this.zzap = zzap;
    }
    
    @Override
    public final void zzc(final Status result) {
        this.zzap.setResult((Object)result);
    }
}
