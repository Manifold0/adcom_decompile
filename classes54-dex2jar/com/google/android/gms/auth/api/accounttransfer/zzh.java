// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

final class zzh extends zza<DeviceMetaData>
{
    private final /* synthetic */ zzg zzas;
    
    zzh(final zzg zzas, final AccountTransferClient.zzb zzb) {
        this.zzas = zzas;
        super(zzb);
    }
    
    @Override
    public final void zza(final DeviceMetaData result) {
        ((AccountTransferClient.zzb<DeviceMetaData>)this.zzas).setResult(result);
    }
}
