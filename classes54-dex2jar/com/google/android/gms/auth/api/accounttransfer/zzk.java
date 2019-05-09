// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.zzs;

final class zzk extends zzs
{
    private final /* synthetic */ AccountTransferClient.zzc zzay;
    
    zzk(final AccountTransferClient.zzc zzay) {
        this.zzay = zzay;
    }
    
    @Override
    public final void onFailure(final Status status) {
        ((AccountTransferClient.zzb)this.zzay).zza(status);
    }
    
    @Override
    public final void zzd() {
        ((AccountTransferClient.zzb<Void>)this.zzay).setResult(null);
    }
}
