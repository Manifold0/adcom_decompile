// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzm extends zzn
{
    private final /* synthetic */ zzl zzag;
    
    zzm(final zzl zzag) {
        this.zzag = zzag;
    }
    
    @Override
    public final void zza(final boolean b) {
        final zzl zzag = this.zzag;
        Status status;
        if (b) {
            status = Status.RESULT_SUCCESS;
        }
        else {
            status = zzh.zzad;
        }
        zzag.setResult((Result)new zzq(status));
    }
}
