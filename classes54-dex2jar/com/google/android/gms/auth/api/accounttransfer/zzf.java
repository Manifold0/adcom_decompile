// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

final class zzf extends zza<byte[]>
{
    private final /* synthetic */ zze zzaq;
    
    zzf(final zze zzaq, final AccountTransferClient.zzb zzb) {
        this.zzaq = zzaq;
        super(zzb);
    }
    
    @Override
    public final void zza(final byte[] result) {
        ((AccountTransferClient.zzb<byte[]>)this.zzaq).setResult(result);
    }
}
